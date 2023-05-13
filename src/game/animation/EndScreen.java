package game.animation;
// ID: 209083682

import biuoop.DrawSurface;
import game.Counter;

import java.awt.Color;

/**
 * end screen class that display you win or game over if won or lost the game.
 */
public class EndScreen implements Animation {
    private final boolean won;
    private final Counter score;

    /**
     * creates an end screen with the score, if won is true then a win screen and if false then game over screen.
     * @param won the boolean that says if won or lost
     * @param score the given final score of the player
     */
    public EndScreen(boolean won, Counter score) {
        this.won = won;
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.black);
        if (won) {
            d.drawText(175, 100, "You Win!", 100);
            //draw text  You Win!
        } else {
            //draw text  Game Over.
            d.drawText(125, 100, "Game Over.", 100);
        }
        d.drawText(75, 300, "Your Score is: ", 100);
        d.drawText(300, 500, Integer.toString(this.score.getValue()), 100);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}