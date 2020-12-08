package com.ataoglan.robot.core;

public enum Direction {
    N, E, S, W;

    public Direction getDirectionFromAction(Action action) {
        if (action == Action.L) {
            int currentOrdinal = this.ordinal();
            int newOrdinal = currentOrdinal - 1;
            if (newOrdinal < 0)
                return values()[values().length - 1];
            return values()[newOrdinal];
        } else if (action == Action.R) {
            int currentOrdinal = this.ordinal();
            int newOrdinal = currentOrdinal + 1;
            if (newOrdinal == values().length)
                return values()[0];
            return values()[newOrdinal];
        }
        return this;
    }
}
