package ge.vakhtang.rgsm.handlers;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.channel.unions.AudioChannelUnion;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

public class MobilityHandler {
    public static void handleJoin(@NotNull SlashCommandInteractionEvent event) {
        Guild guild = event.getChannel().asGuildMessageChannel().getGuild();//server
        GuildVoiceState voiceState = event.getMember().getVoiceState();
        if (voiceState.inAudioChannel()) {
            if (!guild.getAudioManager().isConnected()) {
                event.getChannel().sendMessage("Wassup baby! Put it down!").queue();
                AudioChannelUnion currentChannelOfCommander = voiceState.getChannel();
                guild.getAudioManager().openAudioConnection(currentChannelOfCommander);
            }
        } else {
            event.getChannel().sendMessage("I can't bounce with you dude. You're not in a channel").queue();
        }
    }
}
