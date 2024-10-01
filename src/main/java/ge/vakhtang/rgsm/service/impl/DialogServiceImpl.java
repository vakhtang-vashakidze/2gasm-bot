package ge.vakhtang.rgsm.service.impl;

import ge.vakhtang.rgsm.service.DialogService;
import ge.vakhtang.rgsm.service.MobilityService;
import ge.vakhtang.rgsm.service.MusicService;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Service;

import static ge.vakhtang.rgsm.config.Instructions.*;

@Service
public class DialogServiceImpl implements DialogService {
    private final String command;
    private final MusicService musicService;
    private final MobilityService mobilityService;

    public DialogServiceImpl(String command, MusicService musicService, MobilityService mobilityService) {
        this.command = command;
        this.musicService = musicService;
        this.mobilityService = mobilityService;
    }

    @Override
    public void processEvent(MessageReceivedEvent event) {
        String rawMessage = event.getMessage().getContentRaw();
        if (rawMessage.isEmpty()) return;
        if (!rawMessage.startsWith(command)) return;

        String playInstruction = command.concat(PLAY.getLiteral());
        String disconnectInstruction = command.concat(DISCONNECT.getLiteral());
        String joinInstruction = command.concat(JOIN.getLiteral());

        if (rawMessage.startsWith(playInstruction)) {
            try {
                mobilityService.tryJoiningBotInCurrentVoiceChannel(event);
                String query = rawMessage.substring(playInstruction.length());
                musicService.tryPlayingQuery(query, event.getChannel());
            } catch (Exception ignored) {

            }
        } else if (rawMessage.startsWith(joinInstruction)) {
            try {
                mobilityService.tryJoiningBotInCurrentVoiceChannel(event);
            } catch (Exception ignored) {

            }
        } else if (rawMessage.startsWith(disconnectInstruction)) {
            mobilityService.tryDisconnectingBotFromCurrentVoiceChannel(event);
        }
    }
}
