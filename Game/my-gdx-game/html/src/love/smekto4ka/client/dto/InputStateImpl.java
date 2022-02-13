package love.smekto4ka.client.dto;

import love.smekto4ka.obj.InputState;

public class InputStateImpl implements InputState {


    public InputStateImpl() {
        setType("state");
        setLeftPress(false);
        setReitPress(false);
        setDownPress(false);
        setUpPress(false);
        setFirePressed( false);
        setAngle( 0);
    }

    public InputStateImpl(boolean leftPress, boolean reitPress, boolean downPress, boolean upPress, boolean firePressed, float angle) {
        this();
        setLeftPress(leftPress);
        setReitPress(reitPress);
       setDownPress(downPress);
        setUpPress(upPress);
        setFirePressed( firePressed);
       setAngle( angle);
    }

    @Override
    public native boolean isLeftPress() /*-{
        return this.leftPress;
    }-*/;

    @Override
    public native void setLeftPress(boolean leftPress) /*-{
        this.leftPress = leftPress;
    }-*/;

    @Override
    public native boolean isReitPress() /*-{
        return this.reitPress;
    }-*/;

    @Override
    public native void setReitPress(boolean reitPress) /*-{
        this.reitPress = reitPress;
    }-*/;

    @Override
    public native boolean isDownPress() /*-{
        return this.downPress;
    }-*/;

    @Override
    public native void setDownPress(boolean downPress) /*-{
        this.downPress = downPress;
    }-*/;

    @Override
    public native boolean isUpPress() /*-{
        return this.upPress;
    }-*/;

    @Override
    public native void setUpPress(boolean upPress) /*-{
        this.upPress = upPress;
    }-*/;

    @Override
    public native boolean isFirePressed() /*-{
        return this.firePressed;
    }-*/;

    @Override
    public native void setFirePressed(boolean firePressed) /*-{
        this.firePressed = firePressed;
    }-*/;

    @Override
    public native float getAngle() /*-{
        return this.angle;
    }-*/;

    @Override
    public native void setAngle(float angle) /*-{
        this.angle = angle;
    }-*/;

    public native String getType() /*-{
    return this.type;
    }-*/;

    public native void setType(String type) /*-{
        this.type = type;
    }-*/;
}
