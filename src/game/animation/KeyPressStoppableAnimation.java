package game.animation;
// ID: 209083682

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * key press stoppable animation that is a decorator for an animation class.
 * runs until is stopped by a given key press
 */
public class KeyPressStoppableAnimation implements Animation {
    private final KeyboardSensor keyboardSensor;
    private final String key;
    private final Animation animation;

    /**
     * creates a new animation the is stoppable by a key press.
     * @param sensor a given keyboard sensor to detect the keypress
     * @param key the given key that should stop the animation
     * @param animation the given animation to run until the keypress
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboardSensor = sensor;
        this.key = key;
        this.animation = animation;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
    }

    @Override
    public boolean shouldStop() {
        return this.keyboardSensor.isPressed(key);
    }
}
