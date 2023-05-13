package game.animation;
// ID: 209083682

import biuoop.DrawSurface;
import biuoop.Sleeper;
import gui.SpriteCollection;
import java.awt.Color;

/**
 * a countdown animation that will count down from countFrom for numOfSeconds with gameScreen on the background.
 */
public class CountdownAnimation implements Animation {
    private final double numOfSeconds;
    private final int countFrom;
    private final SpriteCollection gameScreen;
    private int count;

    /**
     * creates a new countDownAnimation animation.
     * that will count down from countFrom for numOfSeconds with gameScreen on the background
     * @param numOfSeconds given number of seconds to run.
     * @param countFrom given number to count down from
     * @param gameScreen the given gamesmen in the background
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.count = countFrom;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        //d.setColor(Color.black);
        //d.drawText(377, 305, Integer.toString(count), 120);
        d.setColor(Color.BLUE);
        d.drawText(380, 300, Integer.toString(count), 100);

        // timing
        Sleeper sleeper = new Sleeper();
        long timePerCount = (long) (1000 * this.numOfSeconds / this.countFrom);
        long startTime = System.currentTimeMillis();
        long usedTime = System.currentTimeMillis() - startTime;
        long milliSecondLeftToSleep = timePerCount - usedTime;
        if (milliSecondLeftToSleep > 0 && this.count != this.countFrom) {
            sleeper.sleepFor(milliSecondLeftToSleep);
        }
        count--;
    }

    @Override
    public boolean shouldStop() {
        return this.count < 0;
    }
}