package ru.est0y.diceChess.domain.board;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.PersistenceCreator;
import ru.est0y.diceChess.domain.AbstractPiece;
import ru.est0y.diceChess.domain.Square;
import ru.est0y.diceChess.domain.dice.PieceLocation;
import ru.est0y.diceChess.services.PlayersPiecesUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ToString
public class DiceChessBoardImpl implements DiceChessBoard {
    private final Board board;

    private final Map<Class<? extends AbstractPiece>, List<PieceLocation>> player1PieceLocations;

    private final Map<Class<? extends AbstractPiece>, List<PieceLocation>> player2PieceLocations;


    @Autowired
    public DiceChessBoardImpl(Board board, PlayersPiecesUtils playersPiecesUtils) {
        this.board = board;
        this.player1PieceLocations = playersPiecesUtils.getPlayerPiecesLocation(1, board);
        this.player2PieceLocations = playersPiecesUtils.getPlayerPiecesLocation(2, board);
    }

    @PersistenceCreator
    public DiceChessBoardImpl(Board board,
                              Map<Class<? extends AbstractPiece>, List<PieceLocation>> player1PieceLocations,
                              Map<Class<? extends AbstractPiece>, List<PieceLocation>> player2PieceLocations) {
        this.board = board;
        this.player1PieceLocations = player1PieceLocations;
        this.player2PieceLocations = player2PieceLocations;
    }

    @Override
    public Optional<AbstractPiece> getPiece(Square square) {
        return board.getPiece(square);
    }

    @Override
    public void putPiece(Square square, AbstractPiece abstractPiece) {
        if (abstractPiece.getTeam() == 1) {
            player1PieceLocations.computeIfAbsent(abstractPiece.getClass(),
                    (k) -> new ArrayList<>());
            player1PieceLocations.get(abstractPiece.getClass()).add(new PieceLocation(square, abstractPiece));
        } else if (abstractPiece.getTeam() == 2) {
            player2PieceLocations.computeIfAbsent(abstractPiece.getClass(),
                    (k) -> new ArrayList<>());
            player2PieceLocations.get(abstractPiece.getClass()).add(new PieceLocation(square, abstractPiece));
        } else {
            throw new IllegalArgumentException("team:" + abstractPiece.getTeam());
        }
        board.putPiece(square, abstractPiece);
    }

    @Override
    public void remove(Square square) {
        var optionalPiece = board.getPiece(square);
        if (optionalPiece.isEmpty()) {
            return;
        }
        var abstractPiece = optionalPiece.get();
        if (abstractPiece.getTeam() == 1) {

            var list = new ArrayList<>(player1PieceLocations.get(abstractPiece.getClass())
                    .stream().filter(pl -> !pl.getSquare().equals(square)).toList());
            player1PieceLocations.put(abstractPiece.getClass(), list);
        } else if (abstractPiece.getTeam() == 2) {
            var list = new ArrayList<>(player2PieceLocations.get(abstractPiece.getClass())
                    .stream().filter(pl -> !pl.getSquare().equals(square)).toList());
            player2PieceLocations.put(abstractPiece.getClass(), list);
        } else {
            throw new IllegalArgumentException("team:" + abstractPiece.getTeam());
        }

        board.remove(square);
    }

    @Override
    public BoardSize getSize() {
        return board.getSize();
    }

    @Override
    public Board clone() {
        var pl1 = new HashMap<Class<? extends AbstractPiece>, List<PieceLocation>>();
        for (Map.Entry<Class<? extends AbstractPiece>, List<PieceLocation>> entry : player1PieceLocations.entrySet()) {
            pl1.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
        var pl2 = new HashMap<Class<? extends AbstractPiece>, List<PieceLocation>>();
        for (Map.Entry<Class<? extends AbstractPiece>, List<PieceLocation>> entry : player2PieceLocations.entrySet()) {
            pl2.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
        return new DiceChessBoardImpl(board.clone(),
                pl1,
                pl2);
    }

    @Override
    public Map<Class<? extends AbstractPiece>, List<PieceLocation>> getPlayerPieces(int team) {
        if (team == 1) {
            return player1PieceLocations;
        } else if (team == 2) {
            return player2PieceLocations;
        } else {
            throw new IllegalArgumentException("team:" + team);
        }
    }
}
