package ge.vakhtang.rgsm.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import static ge.vakhtang.rgsm.configs.CommandLiterals.JOIN;
import static ge.vakhtang.rgsm.handlers.MobilityHandler.handleJoin;

@Component
public class JoinCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (!event.getName().equals(JOIN.getLiteral())) {
            return;
        }
        handleJoin(event);
    }
}
