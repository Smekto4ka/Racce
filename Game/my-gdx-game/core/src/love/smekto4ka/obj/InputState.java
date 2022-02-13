package love.smekto4ka.obj;

public interface InputState {
    boolean isLeftPress();

    void setLeftPress(boolean leftPress);

    boolean isReitPress();

    void setReitPress(boolean reitPress);

    boolean isDownPress();

    void setDownPress(boolean downPress);

    boolean isUpPress();

    void setUpPress(boolean upPress);

    boolean isFirePressed();

    void setFirePressed(boolean firePressed);

    float getAngle();

    void setAngle(float angle);
     String getType() ;

     void setType(String type) ;

}
