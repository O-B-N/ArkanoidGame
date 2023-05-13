// ID: 209083682

import biuoop.GUI;
import game.animation.*;
//import game.animation.AnimationRunner;
//import game.GameFlow;
import game.*;
import game.levels.*;
//import game.levels.AngryBirdLevel;
import game.levels.LevelInformation;
//import game.levels.EmpireStateLevel;
//import game.levels.DirectHitLevel;
//import game.levels.WideEasyLevel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Or Ben Naim
 * a class that create a game, initialize it and then run it.
 */
public class ArkanoidGame {

    /**
     * create game, initialize it and then run it.
     *
     * @param args the main method doesn't expect any arguments
     */
    public static void main(String[] args) {

        //create the default list of levels
        List<LevelInformation> list = new ArrayList<>();
        list.add(new DirectHitLevel());
        list.add(new WideEasyLevel());
        list.add(new EmpireStateLevel());
        list.add(new AngryBirdLevel());

        //create a map of the levels numbers in strings to the levels
        Map<String, LevelInformation> map = new HashMap<>();
        map.put("1", list.get(0));
        map.put("2", list.get(1));
        map.put("3", list.get(2));
        map.put("4", list.get(3));

        //GameFlow g = new GameFlow();
        int width = 800, height = 600;
        GUI gui = new GUI("Arkanoid game", width, height);
        AnimationRunner animationRunner = new AnimationRunner(gui, 60, new biuoop.Sleeper());
        GameFlow g = new GameFlow(animationRunner, gui.getKeyboardSensor(), gui);

        //put every level that have an argument in args in a new list
        List<LevelInformation> newList = new ArrayList<>();
        for (String arg : args) {
            if (map.containsKey(arg)) {
                newList.add(map.get(arg));
            }
        }

        //if there are levels in range from the argument run them else run all levels by order
        if (newList.isEmpty()) {
            g.runLevels(list);
        } else {
            g.runLevels(newList);
        }
    }
}