package ge.vakhtang.rgsm.configs;

import lombok.Getter;

@Getter
public enum CommandLiterals {
    PLAY("play"),
    JOIN("join"),
    DISCONNECT("disconnect"),
    SKIP("skip"),
    CLEAR("clear");

    private final String literal;

    CommandLiterals(String literal) {
        this.literal = literal;
    }

}
