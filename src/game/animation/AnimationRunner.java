package game.animation;
// ID: 209083682

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * a animation runner class that runs a given animation.
 */
public class AnimationRunner {
    private final GUI gui;
    private final int framesPerSecond;
    private final Sleeper sleeper;

    /**
     * creates a new animation runner object from given gui, frames Per Second and a sleeper.
     * @param gui the given gui
     * @param frames the given frames per seconds
     * @param sleeper the given sleeper
     */
    public AnimationRunner(GUI gui, int frames, Sleeper sleeper) {
        this.gui = gui;
        this.framesPerSecond = frames;
        this.sleeper = sleeper;
    }

    /**
     * runs a given animation.
     * @param animation the given animation to run
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();
            animation.doOneFrame(d);
            this.gui.show(d);

            //timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
