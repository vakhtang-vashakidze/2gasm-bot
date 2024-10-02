package ge.vakhtang.rgsm.commands;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import static ge.vakhtang.rgsm.configs.CommandLiterals.DISCONNECT;

@Component
public class DisconnectCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (!event.getName().equals(DISCONNECT.getLiteral())) {
            return;
        }
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
