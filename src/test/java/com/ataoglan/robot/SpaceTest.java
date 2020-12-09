package com.ataoglan.robot;

import com.ataoglan.robot.core.Direction;
import com.ataoglan.robot.core.Robot;
import com.ataoglan.robot.core.Space;
import com.ataoglan.robot.input.RobotInput;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;


public class SpaceTest {
    Space space;
    Robot robot0;
    Robot robot1;
    RobotInput robot0Input;
    RobotInput robot1Input;
    int x;
    int y;

    @BeforeEach
    void setup(){
        space = new Space();
        x = 5;
        y = 5;
        robot0 = new Robot(0, 0, 1, "N");
        robot1 = new Robot(1, 1, 1, "E");
        robot0Input = new RobotInput(0, 0, 1, "N", "LMLMLMLMLMM");
        robot1Input = new RobotInput(1, 1, 1, "E", "RMRMRMRMRMRM");
    }

    @Test
    void createSpaceTest(){
        space.createSpace(x,y);
        Assert.assertEquals(6, space.getSpace().length);
    }

    @Test
    void createRobot(){
        List<RobotInput> robotInputs = Arrays.asList(robot0Input, robot1Input);
        space.createSpace(x,y);
        space.createRobots(robotInputs);
        Assert.assertEquals(2, space.getRobotWithActions().size());

        Assert.assertEquals(robot0Input.getCommands().length(), space.getRobotWithActions().get(robot0).size());
        Assert.assertTrue(space.getRobotWithActions().entrySet().stream().anyMatch(entry -> entry.getKey().equals(robot1)));
        Assert.assertEquals(Direction.valueOf("E"),
                space.getRobotWithActions().entrySet().stream().filter(entry -> entry.getKey().equals(robot1))
                        .map(entry -> entry.getKey()).map(r -> r.getCoordinate().getDirection()).findFirst().orElse(null));
    }

    @Test
    void runCommands(){
        space.runCommands();
    }

    @Test
    void givenNumberGreaterThan5_thenReturnU(){
        String result = space.ozcan(10);

        Assert.assertEquals("u", result);
    }

    @Test
    void givenNumberLessThan5_thenReturnA(){
        String result = space.ozcan(4);

        Assert.assertEquals("a", result);
    }

    @Test
    void givenNumberEquals5_thenReturnX(){
        String result = space.ozcan(5);

        Assert.assertEquals("x", result);
    }
}
