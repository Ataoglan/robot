package com.ataoglan.robot.core;



import com.ataoglan.robot.input.RobotInput;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Space {
    Object[][] space;
    Map<Robot, List<Action>> robotWithActions;

    public Object[][] getSpace() {
        return space;
    }

    public Map<Robot, List<Action>> getRobotWithActions() {
        return robotWithActions;
    }

    public void runCommands() {
        this.robotWithActions
                .forEach((r, actions) -> {
                    actions.forEach(a -> {
                        action(r, a);
                    });
                    System.out.println(String.format("Robot[%d] %d %d %s", r.getId(), r.getCoordinate().getX(), r.getCoordinate().getY(), r.getCoordinate().getDirection()));
                });

    }

    private void action(Robot robot, Action action) {
        if (action == Action.L || action == Action.R) {
            robot.changeDirection(action);
        } else if (action == Action.M) {
            robot.move(space);
        }
    }

    public void createSpace(int xMax, int yMax) {
        this.space = new Object[xMax+1][yMax+1];
        for (int x = 0; x < space.length; x++) {
            for (int y = 0; y < space[x].length; y++) {
                space[x][y] = String.format("%5s", ".");
            }
        }
    }

    private void locateRobotInSpace(Robot robot, int xCoordinate, int yCoordinate) {
        space[xCoordinate][yCoordinate] = robot;
    }

    public void createRobots(List<RobotInput> getRobotInputs){
        robotWithActions = new HashMap<>();
        getRobotInputs
                .forEach(ri -> {
                    Robot r = new Robot(ri.getRobotId(), ri.getX(), ri.getY(), ri.getDirection());
                    locateRobotInSpace(r, ri.getX(), ri.getY());
                    Stream<Character> cStream = IntStream.range(0, ri.getCommands().toCharArray().length).mapToObj(i -> ri.getCommands().toCharArray()[i]);
                    List<Action> actions = cStream.map(c -> Character.toString(c)).map(Action::valueOf).collect(Collectors.toList());
                    robotWithActions.put(r, actions);
                });
    }

}
