package ge.vakhtang.rgsm.service;

import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;

public interface MusicService {
    void tryPlayingQuery(String query, MessageChannelUnion channel);
}
