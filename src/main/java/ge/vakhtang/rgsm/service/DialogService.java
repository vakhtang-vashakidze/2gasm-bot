package ge.vakhtang.rgsm.service;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface DialogService {
    void processEvent(MessageReceivedEvent event);
}
