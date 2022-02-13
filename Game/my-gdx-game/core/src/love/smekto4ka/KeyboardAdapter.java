package love.smekto4ka;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;

public class KeyboardAdapter extends InputAdapter {
    private boolean leftPress;
    private boolean reitPress;
    private boolean downPress;
    private boolean upPress;
private final Vector2 mousePosition = new Vector2();
    private final Vector2 direction = new Vector2();
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.A) leftPress = true;
        if (keycode == Input.Keys.W) upPress = true;
        if (keycode == Input.Keys.D) reitPress = true;
        if (keycode == Input.Keys.S) downPress = true;
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.A) leftPress = false;
        if (keycode == Input.Keys.W) upPress = false;
        if (keycode == Input.Keys.D) reitPress = false;
        if (keycode == Input.Keys.S) downPress = false;
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        mousePosition.set(screenX, Gdx.graphics.getHeight() - screenY);
        return false;
    }
    public Vector2 getDirection(){
        direction.set(0,0);
        if(leftPress) direction.add(-5 , 0);
        if(reitPress) direction.add(5,0);
        if(upPress) direction.add(0, 5);
        if(downPress) direction.add(0 , -5);
        return direction;
    }

    public Vector2 getMousePosition() {
        return mousePosition;
    }
}
