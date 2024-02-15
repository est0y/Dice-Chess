package ru.est0y.diceChess.services.directions;

public class NotExistSquareException extends RuntimeException {
    public NotExistSquareException(int x, int y) {
        super(String.format("x:%d y:%d", x, y));
    }

    public NotExistSquareException(String square) {
        super(String.format("%s", square));
    }
}