package com.ataoglan.robot.core;

import java.util.Objects;

public class Robot {
    private final int id;
    private final Coordinate coordinate;

    public Robot(int id, int coordinateX, int coordinateY, String direction) {
        this.id = id;
        this.coordinate = new Coordinate(coordinateX, coordinateY, Direction.valueOf(direction));
    }

    public int getId() {
        return id;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Robot robot = (Robot) o;
        return Objects.equals(id, robot.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void changeDirection(Action action) {
        getCoordinate().setDirection(getCoordinate().getDirection().getDirectionFromAction(action));
    }

    public void move(Object[][] space) {
        int previousX = getCoordinate().getX();
        int previousY = getCoordinate().getY();
        int newX = getCoordinate().getX();
        int newY = getCoordinate().getY();

        if (getCoordinate().getDirection() == Direction.N) {
            newY++;
        } else if (getCoordinate().getDirection() == Direction.S) {
            newY--;
        } else if (getCoordinate().getDirection() == Direction.W) {
            newX--;
        } else if (getCoordinate().getDirection() == Direction.E) {
            newX++;
        }

        if (newX <= space.length && newX >= 0 && newY >= 0 && newY <= space.length) {
            getCoordinate().setX(newX);
            getCoordinate().setY(newY);
            space[previousX][previousY] = String.format("%5s", ".");
            space[newX][newY] = this;
        }

    }

    @Override
    public String toString() {
        return String.format("%5s", getCoordinate().getDirection().name());
    }
}
