package love.smekto4ka;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import love.smekto4ka.adapter.KeyboardAdapter;
import love.smekto4ka.obj.InputState;
import love.smekto4ka.obj.MessageSender;
import love.smekto4ka.obj.Panzer;

public class Started extends ApplicationAdapter {
    SpriteBatch batch;
    private Panzer me;
    private Array<Panzer> enemies = new Array<Panzer>();
    private final KeyboardAdapter inputProcessor ;
private MessageSender messageSender;
    public Started(InputState inputState) {
        inputProcessor = new KeyboardAdapter(inputState);
    }

    @Override
    public void create() {
        Gdx.input.setInputProcessor(inputProcessor);
        batch = new SpriteBatch();
        me = new Panzer(100, 200);
        for (int i = 0; i < 15; i++) {
            enemies.add(new Panzer(MathUtils.random(Gdx.graphics.getWidth()),
                    MathUtils.random(Gdx.graphics.getHeight()),
                    "img.png"));
        }
    }

    @Override
    public void render() {
        me.moveTo(inputProcessor.getDirection());
        me.rotateTo(inputProcessor.getMousePosition());
        ScreenUtils.clear(1, 1, 1, 1);
        batch.begin();
        render(batch);
        batch.end();
    }

    public void render(SpriteBatch batch) {
        me.render(batch);
        for (Panzer enemy : enemies) {
            enemy.rotateTo(me.getPosition());
            enemy.render(batch);
        }

    }

    @Override
    public void dispose() {
        batch.dispose();
        me.dispose();
    }

    public void setMessageSender(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void handleTimer() {
        if(inputProcessor != null){
            InputState inputState = inputProcessor.updateAndGetInputState(me.getOrigin());
            messageSender.sendMessage(inputState);
        }
    }
}
