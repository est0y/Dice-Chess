package ru.est0y.diceChess.services.directions;

import org.springframework.stereotype.Service;
import ru.est0y.diceChess.domain.Direction;
import ru.est0y.diceChess.domain.Move;

@Service
public class DirectionChecker {
    public Direction check(Move move) {
        if (!isOffset(move)) {
            return Direction.UNKNOWN;
        }
        if (isDiagonalOffset(move)) {
            return determineDiagonalDirection(move);
        } else if (isVerticalOrHorizontal(move)) {
            return determineVerticalOrHorizontalDirection(move);
        } else {
            return Direction.UNKNOWN;
        }
    }

    private Direction determineDiagonalDirection(Move move) {
        var from = move.getFrom();
        var to = move.getTo();
        int diffX = to.getX() - from.getX();
        int diffY = to.getY() - from.getY();

        if (diffX > 0) {
            return (diffY > 0) ? Direction.RIGHT_UP : Direction.RIGHT_DOWN;
        } else {
            return (diffY > 0) ? Direction.LEFT_UP : Direction.LEFT_DOWN;
        }
    }

    private Direction determineVerticalOrHorizontalDirection(Move move) {
        var from = move.getFrom();
        var to = move.getTo();
        int diffX = to.getX() - from.getX();
        int diffY = to.getY() - from.getY();

        if (diffX == 0) {
            return (diffY > 0) ? Direction.UP : Direction.DOWN;
        } else {
            return (diffX > 0) ? Direction.RIGHT : Direction.LEFT;
        }
    }

    private boolean isDiagonalOffset(Move move) {

        var from = move.getFrom();
        var to = move.getTo();
        int diffX = Math.abs(from.getX() - to.getX());
        int diffY = Math.abs(from.getY() - to.getY());
        return diffX == diffY;
    }

    private boolean isVerticalOrHorizontal(Move move) {
        var from = move.getFrom();
        var to = move.getTo();
        int diffX = to.getX() - from.getX();
        int diffY = to.getY() - from.getY();
        return diffX == 0 || diffY == 0;
    }

    private boolean isOffset(Move move) {
        var from = move.getFrom();
        var to = move.getTo();
        return !from.equals(to);
    }
}
