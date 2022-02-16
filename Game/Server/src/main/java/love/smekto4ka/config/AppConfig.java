package love.smekto4ka.config;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import love.smekto4ka.GameLoop;
import love.smekto4ka.actors.Panzer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    /**
     * создаем неуий кусок сервера, который будет работать так же, как и гуи
     * что-то создавать, а после каждый раз делать рендеринг (которого нет))) ))
     * @param gameLoop
     * @return
     */
    @Bean
    public HeadlessApplication getApplication(GameLoop gameLoop){
        return new HeadlessApplication(gameLoop);
    }

    @Bean
    public Json getJson(){
        Json json = new Json();
        json.setOutputType(JsonWriter.OutputType.json);
        json.addClassTag("panzer", Panzer.class);
        return json;
    }
}
