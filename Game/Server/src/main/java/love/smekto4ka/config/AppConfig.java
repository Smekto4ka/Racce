package love.smekto4ka.config;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import love.smekto4ka.GameLoop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public HeadlessApplication getApplication(GameLoop gameLoop){
        return new HeadlessApplication(gameLoop);
    }
}
