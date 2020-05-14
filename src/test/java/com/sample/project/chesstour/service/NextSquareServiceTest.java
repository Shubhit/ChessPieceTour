package com.sample.project.chesstour.service;

import com.sample.project.chesstour.models.Board;
import com.sample.project.chesstour.models.Coordinates;
import com.sample.project.chesstour.models.Square;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class NextSquareServiceTest {

    private static Board chessBoard;
    private static Square currSquare;
    private static List<Coordinates> moves;

    @Before
    public void init() {

        chessBoard = new Board(10, 10);

        currSquare = chessBoard.getSquare(new Coordinates(4,5));

        currSquare.setTourPosition(1);

        moves = Arrays.asList(new Coordinates(3, 0), new Coordinates(0, -3),
                new Coordinates(-3, 0), new Coordinates(0, 3), new Coordinates(2, 2),
                new Coordinates(2, -2), new Coordinates(-2, -2), new Coordinates(-2, 2));

    }

    /**
     * Asserts that returned square is a neighbour of current square
     * and its tour position is next to current square's tour position.
     *
     */
    @Test
    public void successFindNextSquare() {

        Square nextSquare = NextSquareService.getInstance().findNextSquare(chessBoard, moves, currSquare);

        assertTrue(moves.stream().anyMatch(move -> Objects.deepEquals(nextSquare, applyMove(chessBoard, currSquare, move)))
                && Objects.equals(currSquare.getTourPosition() + 1, nextSquare.getTourPosition()));

    }

    private Square applyMove(Board chessBoard, Square currSquare, Coordinates move) {

        return chessBoard.getSquare(new Coordinates(currSquare.getRow() + move.getRow(), currSquare.getColumn() + move.getCol()));

    }


    /**
     * Asserts that next square is not found if all neighbours have already been visited.
     *
     */
    @Test
    public void failedFindNextSquare() {

        int tourPos = currSquare.getTourPosition();

        for (Coordinates move : moves) {

            applyMove(chessBoard, currSquare, move).setTourPosition(++tourPos);

        }

        Square nextSquare = NextSquareService.getInstance().findNextSquare(chessBoard, moves, currSquare);

        assertNull(nextSquare);

    }

}