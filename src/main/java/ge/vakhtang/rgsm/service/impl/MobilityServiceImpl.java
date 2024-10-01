package ge.vakhtang.rgsm.service.impl;

import ge.vakhtang.rgsm.service.MobilityService;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.channel.unions.AudioChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Service;

@Service
public class MobilityServiceImpl implements MobilityService {

    @Override
    public void tryJoiningBotInCurrentVoiceChannel(MessageReceivedEvent event) throws Exception {
        Guild guild = event.getChannel().asGuildMessageChannel().getGuild();//server
        GuildVoiceState voiceState = event.getMember().getVoiceState();
        if (voiceState.inAudioChannel()) {
            if (guild.getAudioManager().isConnected()) {
                event.getChannel().sendMessage("Wassup baby! Put it down!").queue();
                AudioChannelUnion currentChannelOfCommander = voiceState.getChannel();
                guild.getAudioManager().openAudioConnection(currentChannelOfCommander);
            }
        } else {
            event.getChannel().sendMessage("I can't bounce with you dude. You're not in a channel").queue();
            throw new Exception();
        }
    }

    @Override
    public void tryDisconnectingBotFromCurrentVoiceChannel(MessageReceivedEvent event) {
        Guild guild = event.getChannel().asGuildMessageChannel().getGuild();//server
        GuildVoiceState voiceState = event.getMember().getVoiceState();
        if (voiceState.inAudioChannel()) {
            guild.getAudioManager().closeAudioConnection();
            event.getChannel().sendMessage("Disconnecting... cya!").queue();
        } else {
            if (guild.getAudioManager().isConnected()) {
                event.getChannel().sendMessage("You're not in a channel dude. I'm bouncing with others.").queue();
            } else {
                event.getChannel().sendMessage("I'm already disconnected and alone.").queue();
            }
        }
    }

}
