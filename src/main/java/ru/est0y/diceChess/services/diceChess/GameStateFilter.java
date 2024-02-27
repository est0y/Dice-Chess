package ru.est0y.diceChess.services.diceChess;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.est0y.diceChess.services.IllegalMoveException;
import ru.est0y.diceChess.services.directions.MoveMaker;
import ru.est0y.diceChess.domain.AbstractPiece;
import ru.est0y.diceChess.domain.Game;
import ru.est0y.diceChess.domain.Move;
import ru.est0y.diceChess.domain.Player;
import ru.est0y.diceChess.domain.Square;
import ru.est0y.diceChess.domain.board.Board;
import ru.est0y.diceChess.domain.board.DiceChessBoard;
import ru.est0y.diceChess.domain.dice.PieceLocation;
import ru.est0y.diceChess.services.rules.legalMovement.LegalMovementManager;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GameStateFilter implements MoveFilter {


    private final MoveMaker moveMaker;

    private final LegalMovementManager legalMovementManager;


    @Override
    public void doFilter(Move move, Game game) {
        var classes = game.getLegalPieces();
        var player = game.getTurnHolder();
        var board = game.getBoard();
        if (classes.size() == 1) {
        } else if (classes.size() == 2) {
            if (isAllPiecesMayMove(classes.get(0), classes.get(1), board, player.getTeamNumber())) {
                var classesCopy = new ArrayList<>(classes);
                var attackingPieceClass = board.getPiece(move.getFrom()).orElseThrow().getClass();
                classesCopy.remove(attackingPieceClass);
                var clazz = classesCopy.get(0);
                remainingFigureMayMove(clazz, player, move, board);
            }
        } else {
            throw new IllegalStateException();
        }


    }

    private void remainingFigureMayMove(Class<? extends AbstractPiece> reamningPieceClass,
                                        Player turnHolder,
                                        Move move,
                                        DiceChessBoard board) {
        DiceChessBoard updatedBoard = (DiceChessBoard) moveMaker.makeMove(move, board.clone());
        List<PieceLocation> pieceLocations = updatedBoard.getPlayerPieces(turnHolder.getTeamNumber())
                .get(reamningPieceClass);
        if (pieceLocations == null) {
            return;
        }
        for (var pieceLocation : pieceLocations) {
            if (!getAllLegalMoves(pieceLocation, updatedBoard).isEmpty()) {
                return;
            }
        }
        throw new IllegalMoveException();
    }

    public boolean isAllPiecesMayMove(Class<? extends AbstractPiece> class1,
                                      Class<? extends AbstractPiece> class2,
                                      DiceChessBoard board, int team) {

        var playerPiecesLocations = board.getPlayerPieces(team);
        List<PieceLocation> pieceLocations1 = playerPiecesLocations.get(class1);
        List<PieceLocation> pieceLocations2 = playerPiecesLocations.get(class2);
        if (pieceLocations1 == null || pieceLocations2 == null) {
            return true;
        }
        for (var pieceMoves1 : pieceLocations1) {
            for (var pieceMoves2 : pieceLocations2) {
                if (isExistsMove(pieceMoves1, pieceMoves2, board)) {
                    return true;
                }
            }
        }
        return false;
    }


    public boolean isExistsMove(PieceLocation pieceLocation1,
                                PieceLocation pieceLocation2,
                                Board board) {
        if (hasLegalMoves(pieceLocation1, pieceLocation2, board)) {
            return true;
        }
        return hasLegalMoves(pieceLocation2, pieceLocation1, board);
    }

    private boolean hasLegalMoves(PieceLocation sourcePieceLocation,
                                  PieceLocation targetPieceLocation,
                                  Board board) {
        var moves = getAllLegalMoves(sourcePieceLocation, board);
        if (sourcePieceLocation.getSquare().equals(targetPieceLocation.getSquare())) {
            return moves.size() > 0;
        }
        for (var move : moves) {
            var updatedBoard = moveMaker.makeMove(sourcePieceLocation.getSquare(), move, board.clone());
            if (!getAllLegalMoves(targetPieceLocation, updatedBoard).isEmpty()) {
                return true;
            }
        }
        return false;
    }


    private List<Square> getAllLegalMoves(PieceLocation pieceLocation, Board board) {
        return legalMovementManager.getAllLegalMoves(pieceLocation.getSquare(), board);
    }
}