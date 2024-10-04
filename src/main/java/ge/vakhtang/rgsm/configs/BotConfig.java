package ge.vakhtang.rgsm.configs;

import jakarta.annotation.Resource;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Resource
public class BotConfig {

    private final String token;
    private final String command;

    public BotConfig(@Value("${2g.token}") String token, @Value("${2g.default-command}") String command) {
        this.token = token;
        this.command = command;
    }

    @Bean
    public String command() {
        return command;
    }

    @Bean
    public JDA mainBotJDA(ListenerAdapter[] listenerAdapters) {
        JDA jda = JDABuilder.createDefault(token)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)  // Enable message content intent
                .setActivity(Activity.customStatus("2G bouncing"))
                .addEventListeners(listenerAdapters)
                .build();
        jda.updateCommands().addCommands(
                Commands.slash("play", "2G plays song for you.")
                        .addOption(OptionType.STRING, "url", "song url to search", true),
                Commands.slash("skip", "2G skips song for you."),
                Commands.slash("clear", "2G clears song queue for you."),
                Commands.slash("join", "Joins 2G into voice channel"),
                Commands.slash("disconnect", "Disconnects 2G from voice channel.")
        ).queue();
        return jda;
    }
}
