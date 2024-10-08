package ge.vakhtang.rgsm.commands;

import ge.vakhtang.rgsm.exceptions.BotAlreadyInDifferentChannelException;
import ge.vakhtang.rgsm.exceptions.BotNotInSameChannelException;
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
        try {
            handleJoin(event);
        } catch (BotNotInSameChannelException e) {
            throw new RuntimeException(e);
        } catch (BotAlreadyInDifferentChannelException e) {
            throw new RuntimeException(e);
        }
    }
}
