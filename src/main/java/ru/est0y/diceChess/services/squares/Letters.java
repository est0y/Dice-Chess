package ru.est0y.diceChess.services.squares;

public enum Letters {
    A('a', 1),
    B('b', 2),
    C('c', 3),
    D('d', 4),
    E('e', 5),
    F('f', 6),
    G('g', 7),
    H('h', 8);

    private final int number;

    private final char letter;

    Letters(char letter, int number) {
        this.number = number;
        this.letter = letter;
    }

    public int getNumber() {
        return number;
    }

    public char getLetter() {
        return letter;
    }
}
