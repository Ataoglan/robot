package com.ataoglan.robot.input;

import java.util.List;

public class Inputs {
    private int gridX;
    private int gridY;
    private List<RobotInput> robotInputs;

    public int getGridX() {
        return gridX;
    }

    public void setGridX(int gridX) {
        this.gridX = gridX;
    }

    public int getGridY() {
        return gridY;
    }

    public void setGridY(int gridY) {
        this.gridY = gridY;
    }

    public List<RobotInput> getRobotInputs() {
        return robotInputs;
    }

    public void setRobotInputs(List<RobotInput> robotInputs) {
        this.robotInputs = robotInputs;
    }
}
