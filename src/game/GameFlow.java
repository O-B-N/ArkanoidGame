package game;
// ID: 209083682

import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.animation.AnimationRunner;
import game.animation.EndScreen;
import game.animation.KeyPressStoppableAnimation;
import game.levels.LevelInformation;
import java.util.List;

/**
 * a gameFlow class that run all the levels it gets by order of level information list.
 */
public class GameFlow {

    private final biuoop.KeyboardSensor keyboardSensor;
    private final AnimationRunner animationRunner;
    private final GUI gui;

    /**
     * builds a new game flow object.
     * @param ar the accepted animation runner
     * @param ks the given keyboard sensor
     * @param gui the given gui to run
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.gui = gui;
    }

    /**
     * builds a new game flow object.
     */
    public GameFlow() {
        int width = 800, height = 600;
        this.gui = new GUI("Arkanoid game", width, height);
        this.animationRunner = new AnimationRunner(this.gui, 60, new biuoop.Sleeper());
        this.keyboardSensor = gui.getKeyboardSensor();
    }

    /**
     * run all levels that in the level information list by order.
     * @param levels the given list of level information
     */
    public void runLevels(List<LevelInformation> levels) {
        Counter score = new Counter();
        Counter lives = new Counter(10);
        EndScreen end = new EndScreen(true, score);
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, score, lives);
            level.initialize();

            while (!level.shouldStop()) {
                level.run();
            }

            if (level.ballsLeft() == 0) {
                end = new EndScreen(false, score);
                break;
            }

        }
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space", end));
        this.gui.close();
        System.exit(0);
    }

}


