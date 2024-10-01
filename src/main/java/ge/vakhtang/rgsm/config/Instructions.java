package ge.vakhtang.rgsm.config;

import lombok.Getter;

@Getter
public enum Instructions {
    PLAY("play"),

    JOIN("join"),

    DISCONNECT("disconnect");

    private final String literal;

    Instructions(String literal) {
        this.literal = literal;
    }

}
