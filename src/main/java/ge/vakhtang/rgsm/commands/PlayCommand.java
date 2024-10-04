package ge.vakhtang.rgsm.commands;

import ge.vakhtang.rgsm.exceptions.BotAlreadyInDifferentChannelException;
import ge.vakhtang.rgsm.exceptions.BotNotInSameChannelException;
import ge.vakhtang.rgsm.plugins.music.JukeBox;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import static ge.vakhtang.rgsm.configs.CommandLiterals.PLAY;
import static ge.vakhtang.rgsm.handlers.MobilityHandler.handleJoin;

@Component
public class PlayCommand extends ListenerAdapter {
    private final JukeBox jukeBox;

    public PlayCommand(JukeBox jukeBox) {
        this.jukeBox = jukeBox;
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (!event.getName().equals(PLAY.getLiteral())) {
            return;
        }
        try {
            handleJoin(event);
            jukeBox.loadAndPlay(event.getChannel(), event.getOption("url").getAsString());
        } catch (BotNotInSameChannelException e) {
            throw new RuntimeException(e);
        } catch (BotAlreadyInDifferentChannelException e) {
            throw new RuntimeException(e);
        }
    }
}
