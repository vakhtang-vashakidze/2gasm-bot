package ge.vakhtang.rgsm.service;

import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface MobilityService {

    void tryJoiningBotInCurrentVoiceChannel(MessageReceivedEvent event) throws Exception;

    void tryDisconnectingBotFromCurrentVoiceChannel(MessageReceivedEvent event);
}
