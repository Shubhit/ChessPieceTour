package com.sample.project.chesstour.service;

import com.sample.project.chesstour.exceptions.InvalidStartLocationException;
import com.sample.project.chesstour.exceptions.TourNotFoundException;
import com.sample.project.chesstour.models.Board;
import com.sample.project.chesstour.models.Coordinates;
import com.sample.project.chesstour.models.Piece;
import com.sample.project.chesstour.models.Square;

import java.util.List;
import java.util.Objects;

public class TourFinderService {

    private static NextSquareService nextSquareService = NextSquareService.getInstance();

    private static TourFinderService instance = new TourFinderService();

    public static TourFinderService getInstance() {

        return instance;

    }

    private TourFinderService() {}

    /**
     * Generates the legal moves using warnsdorff's heuristics.
     * Throws exception if not possible.
     *
     * @param piece Chess piece
     * @param chessBoard board on which tour will be performed
     * @return Board with each space marked with its visit position in tour.
     */
    public Board performTour(Piece piece, Board chessBoard){

        if (!isValidPosition(piece.getPosition(), chessBoard.getRowCount(), chessBoard.getColCount())) {

            throw new InvalidStartLocationException();

        }

        markInitialPositionSquare(piece, chessBoard);

        return findTour(piece, chessBoard);

    }

    /**
     * Returns whether a position lies within board boundary.
     *
     * @param position coordinates of position under consideration
     * @param rowCount board dimension
     * @param colCount board dimension
     * @return if position is valid with respect to constraints.
     */
    private boolean isValidPosition(Coordinates position, Integer rowCount, Integer colCount) {

        return position.getRow() >= 0
                && position.getRow() < rowCount
                && position.getCol() >= 0
                && position.getCol() < colCount;

    }

    /**
     * Returns a tour on the board for the chess piece.
     *
     * @param piece chess piece for which tour is to be performed
     * @param chessBoard board on which tour will be performed.
     * @return complete tour on chess board cells.
     */
    private Board findTour(Piece piece, Board chessBoard) {

        Square nextSquare = null, currSquare = getInitialSquare(piece, chessBoard);

        for (int i = 0; i < chessBoard.getRowCount() * chessBoard.getColCount() - 1; ++i) {

            nextSquare = nextSquareService.findNextSquare(chessBoard, piece.getMoves(), currSquare);

            if (nextSquare == null) {

                throw new TourNotFoundException();

            }

            currSquare = nextSquare;

        }

        if(!areNeighbours(piece.getMoves(), nextSquare, currSquare)) {

            throw new TourNotFoundException();

        }

        return chessBoard;

    }

    /**
     * Returns whether a target position can be reached
     * from initial position given the set of legal moves.
     *
     * @param moves allowed moves
     * @param square initial position
     * @param neighbourSquare target position
     * @return if target position is reachable from initial position
     */
    private boolean areNeighbours(List<Coordinates> moves, Square square, Square neighbourSquare) {

        return moves.stream()
                .anyMatch(move -> ((square.getRow() + move.getRow()) == neighbourSquare.getRow())
                && ((square.getColumn() + move.getCol()) == neighbourSquare.getColumn()))
                || (Objects.equals(square.getRow(), neighbourSquare.getRow())
                && Objects.equals(square.getColumn(), neighbourSquare.getColumn()));

    }

    /**
     * Marks the initial position of the piece on
     * board as visited and assigns it tour position.
     *
     * @param piece chess piece for which tour is to be performed
     * @param chessBoard board on which tour will be performed.
     */
    private void markInitialPositionSquare(Piece piece, Board chessBoard) {

        Square initialSquare = getInitialSquare(piece, chessBoard);

        initialSquare.setTourPosition(1);

    }


    /**
     * Returns the square from where tour starts
     * on the chessboard for the piece.
     *
     * @param piece chess piece for which tour is to be found.
     * @param chessBoard board on which tour will be marked.
     * @return square initial position of piece on chessboard.
     */
    private Square getInitialSquare(Piece piece, Board chessBoard){

        return chessBoard.getSquares()[piece.getPosition().getRow()][piece.getPosition().getCol()];

    }

}
