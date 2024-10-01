package ge.vakhtang.rgsm.bots;

import ge.vakhtang.rgsm.service.DialogService;
import ge.vakhtang.rgsm.service.MusicService;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class MainBot extends ListenerAdapter {

    private final DialogService dialogService;
    private final MusicService musicService;

    public MainBot(DialogService dialogService, MusicService musicService) {
        this.dialogService = dialogService;
        this.musicService = musicService;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }

        dialogService.processEvent(event);
    }
}
