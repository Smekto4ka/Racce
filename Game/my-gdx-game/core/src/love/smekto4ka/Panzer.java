package love.smekto4ka;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Panzer {
    private final float size = 64;
    private final float halfSize = size / 2;

    private final Vector2 position = new Vector2();
    private final Texture texture;
    private final Vector2 angle = new Vector2();
    private final TextureRegion textureRegion;

    public Panzer(float x, float y) {
        this(x, y, "me.png");
    }

    public Panzer(float x, float y, String textureName) {

        this.texture = new Texture(textureName);
        textureRegion = new TextureRegion(texture);
        position.set(x, y);
    }

    public void render(SpriteBatch batch) {
        batch.draw(textureRegion,
                position.x,
                position.y,
                halfSize,//точка в картинке, вокруг которой она будет крутиться
                halfSize,
                size,
                size,
                1,//масштаб картинки
                1,
                angle.angleDeg() - 90); //картинка смотрит вверх, а по дефолту считается в право

    }

    public void dispose() {
        texture.dispose();
    }

    public void moveTo(Vector2 direction) {
        position.add(direction);
    }

    public void rotateTo(Vector2 mousePosition) {
        angle.set(mousePosition).sub(position.x + halfSize, position.y + halfSize); // так как надо учитывать , что координаты объекта идут от его нижнего левого угла, а нам нужен его центр
    }

    public Vector2 getPosition() {
        return position;
    }
}