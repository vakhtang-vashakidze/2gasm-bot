package ge.vakhtang.rgsm.service.impl;

import ge.vakhtang.rgsm.service.MusicService;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import org.springframework.stereotype.Service;

@Service
public class MusicServiceImpl implements MusicService {

    @Override
    public void tryPlayingQuery(String query, MessageChannelUnion channel) {
        channel.sendMessage("Playing ".concat(query)).queue();

    }
}
