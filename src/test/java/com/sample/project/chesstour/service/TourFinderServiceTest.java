package com.sample.project.chesstour.service;

import com.sample.project.chesstour.exceptions.InvalidStartLocationException;
import com.sample.project.chesstour.exceptions.TourNotFoundException;
import com.sample.project.chesstour.models.Board;
import com.sample.project.chesstour.models.Coordinates;
import com.sample.project.chesstour.models.Piece;
import com.sample.project.chesstour.models.Square;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

public class TourFinderServiceTest {

    private static Board chessBoard;

    private static Piece piece;

    @Before
    public void init() {

        chessBoard = new Board(10, 10);

        List<Coordinates> moves = Arrays.asList(new Coordinates(3, 0), new Coordinates(0, -3),
                new Coordinates(-3, 0), new Coordinates(0, 3), new Coordinates(2, 2),
                new Coordinates(2, -2), new Coordinates(-2, -2), new Coordinates(-2, 2));

        piece = new Piece(4, 5, moves);

    }

    /**
     * Asserts that the tour has all the cells available on the board.
     *
     */
    @Test
    public void successPerformTour() {

        Board tour = null;

        while(tour == null) {

            try {

                tour = TourFinderService.getInstance().performTour(piece, chessBoard);

            } catch (TourNotFoundException ignored) {}

        }

        int countOfUnvisitedSquare = 0;

        for(int i = 0; i < chessBoard.getRowCount(); i++) {

            for(int j = 0; j < chessBoard.getColCount(); j++) {

                if(Objects.equals((tour.getSquares()[i][j]).getTourPosition(), Square.DEFAULT_TOUR_POSITION)) {

                    countOfUnvisitedSquare++;

                }

            }

        }

        assertEquals(0, countOfUnvisitedSquare);

    }

    /**
     * Asserts that exception is raised when invalid
     * initial chess piece position is provided.
     *
     */
    @Test(expected = InvalidStartLocationException.class)
    public void failedPerformTourDueToIncorrectInitialPosition() {

        piece.setPosition(new Coordinates(10, 10));

        TourFinderService.getInstance().performTour(piece, chessBoard);

    }

    /**
     * Asserts that exception is raised when tour
     * is not complete due to unavailability of
     * unvisited neighbour cells on the chess board.
     *
     */
    @Test(expected = TourNotFoundException.class)
    public void failedPerformTourDueToNoUnvisitedNeighbourFound() {

        Square currSquare = chessBoard.getSquare(piece.getPosition());

        currSquare.setTourPosition(1);

        int tourPos = currSquare.getTourPosition();

        for (Coordinates move : piece.getMoves()) {

            applyMove(chessBoard, currSquare, move).setTourPosition(++tourPos);

        }

        TourFinderService.getInstance().performTour(piece, chessBoard);

    }

    private Square applyMove(Board chessBoard, Square currSquare, Coordinates move) {

        return chessBoard.getSquare(new Coordinates(currSquare.getRow() + move.getRow(), currSquare.getColumn() + move.getCol()));

    }

}