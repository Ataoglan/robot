package com.ataoglan.robot;

import com.ataoglan.robot.core.Action;
import com.ataoglan.robot.core.Direction;
import com.ataoglan.robot.core.Robot;
import com.ataoglan.robot.core.Space;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RobotTest {
    Robot robot;
    Action action;
    Space space;
    @BeforeEach
    void setup(){
        robot = new Robot(0, 1, 1, "N");
        action = Action.L;
        space = new Space();
    }

    @Test
    void changeDirection(){
        robot.changeDirection(action);
        Assert.assertEquals(Direction.W, robot.getCoordinate().getDirection());
    }

    @Test
    void moveifDirectionN(){
        Robot robotN = robot;
        space.createSpace(5, 5);
        robotN.move(space.getSpace());
        Assert.assertEquals(robot.getCoordinate().getY(), robotN.getCoordinate().getY());
    }

    @Test
    void moveifDirectionS(){
        Robot robotN = robot;
        robotN.getCoordinate().setDirection(Direction.S);
        space.createSpace(5, 5);
        robotN.move(space.getSpace());
        Assert.assertEquals(robot.getCoordinate().getY(), robotN.getCoordinate().getY());
    }

    @Test
    void moveifDirectionE(){
        Robot robotN = robot;
        robotN.getCoordinate().setDirection(Direction.E);
        space.createSpace(5, 5);
        robotN.move(space.getSpace());
        Assert.assertEquals(robot.getCoordinate().getX(), robotN.getCoordinate().getX());
    }

    @Test
    void moveifDirectionW(){
        Robot robotN = robot;
        robotN.getCoordinate().setDirection(Direction.W);
        space.createSpace(5, 5);
        robotN.move(space.getSpace());
        Assert.assertEquals(robot.getCoordinate().getX(), robotN.getCoordinate().getX());
    }

}
