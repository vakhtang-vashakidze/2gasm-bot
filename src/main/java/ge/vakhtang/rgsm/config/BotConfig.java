package ge.vakhtang.rgsm.config;

import ge.vakhtang.rgsm.bots.MainBot;
import jakarta.annotation.Resource;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
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
    public JDA mainBotJDA(MainBot mainBot) {
        return JDABuilder.createDefault(token)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)  // Enable message content intent
                .setActivity(Activity.customStatus("2G bouncing"))
                .addEventListeners(mainBot)
                .build();
    }
}
