package ge.vakhtang.rgsm.bots;

import ge.vakhtang.rgsm.service.DialogService;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class MainBot extends ListenerAdapter {

    private final DialogService dialogService;

    public MainBot(DialogService dialogService) {
        this.dialogService = dialogService;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }

        dialogService.processEvent(event);
    }
}
