package ge.vakhtang.rgsm.commands;

import ge.vakhtang.rgsm.plugins.music.JukeBox;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import static ge.vakhtang.rgsm.handlers.MobilityHandler.handleJoin;

@Component
public class PlayCommand extends ListenerAdapter {
    private final JukeBox jukeBox;

    public PlayCommand(JukeBox jukeBox) {
        this.jukeBox = jukeBox;
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (!event.getName().equals("play")) {
            return;
        }
        handleJoin(event);
        jukeBox.loadAndPlay(event.getChannel(), event.getOption("url").getAsString());
    }
}
