package love.smekto4ka.adapter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import love.smekto4ka.obj.InputState;

public class KeyboardAdapter extends InputAdapter {

private final Vector2 mousePosition = new Vector2();
    private final Vector2 direction = new Vector2();
    private final Vector2 angle = new Vector2();
    private final InputState inputState;

    public KeyboardAdapter(InputState inputState) {
        this.inputState = inputState;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.A) inputState.setLeftPress(true);
        if (keycode == Input.Keys.W) inputState.setUpPress(true);
        if (keycode == Input.Keys.D) inputState.setReitPress(true);
        if (keycode == Input.Keys.S) inputState.setDownPress(true);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.A) inputState.setLeftPress(false);
        if (keycode == Input.Keys.W) inputState.setUpPress(false);
        if (keycode == Input.Keys.D) inputState.setReitPress(false);
        if (keycode == Input.Keys.S) inputState.setDownPress(false);
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        inputState.setFirePressed(true);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        inputState.setFirePressed(false);
        return false;
    }

    public void updateMousePosition(){
        mousePosition.set(
                Gdx.input.getX(),
                Gdx.graphics.getHeight() - Gdx.input.getY()
        );
    }
    public Vector2 getDirection(){
        direction.set(0,0);
        if(inputState.isLeftPress()) direction.add(-5 , 0);
        if(inputState.isReitPress()) direction.add(5,0);
        if(inputState.isUpPress()) direction.add(0, 5);
        if(inputState.isDownPress()) direction.add(0 , -5);
        return direction;
    }

    public Vector2 getMousePosition() {
        updateMousePosition();
        return mousePosition;
    }
public InputState updateAndGetInputState(Vector2 playerOrigin){
        updateMousePosition();
        angle.set(mousePosition).sub(playerOrigin);
        inputState.setAngle(angle.angleDeg()-90);
        return inputState;

}

}
