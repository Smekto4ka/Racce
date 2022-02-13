package love.smekto4ka;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Started extends ApplicationAdapter {
    SpriteBatch batch;
    private Panzer me;
    private List<Panzer> enemies = new ArrayList<>();
    private KeyboardAdapter inputProcessor = new KeyboardAdapter();

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
}
