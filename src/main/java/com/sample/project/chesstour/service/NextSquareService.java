package com.sample.project.chesstour.service;

import com.sample.project.chesstour.models.Board;
import com.sample.project.chesstour.models.Coordinates;
import com.sample.project.chesstour.models.Square;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class NextSquareService {

    private static NextSquareService instance = new NextSquareService();

    private NextSquareService() {}

    public static NextSquareService getInstance() {

        return instance;

    }

    /**
     * Try all N adjacent of (*x, *y) starting from a random adjacent.
     * Find the adjacent with minimum degree.
     *
     * @param chessBoard current board
     * @param moves List of possible moves.
     * @param currSquare square under consideration
     * @return square next position on tour
     */
    public Square findNextSquare(Board chessBoard, List<Coordinates> moves, Square currSquare) {

        int minDegreeIndex = getMinDegreeMoveIndex(chessBoard, moves, currSquare);

        if (minDegreeIndex < 0) {

            return null;

        }

        Coordinates neighbourCoordinates = getCoordinatesOfNeighbour(currSquare, moves.get(minDegreeIndex));

        Square nextSquare = chessBoard.getSquare(neighbourCoordinates);

        nextSquare.setTourPosition(currSquare.getTourPosition() + 1);

        return nextSquare;

    }

    /**
     * Finds the index of move which would result in neighbour with minimum degree.
     *
     * @param chessBoard board with current partial tour
     * @param moves allowed moves for the piece
     * @param currSquare current position on tour
     * @return index of the move which results in neighbour with minimum degree.
     */
    private int getMinDegreeMoveIndex(Board chessBoard, List<Coordinates> moves, Square currSquare) {

        int start = ThreadLocalRandom.current().nextInt(1000) % moves.size();

        int currNeighbourDegree = Integer.MAX_VALUE, minDegree = Integer.MAX_VALUE, minDegreeIndex = -1;

        for (int count = 0; count < moves.size(); ++count) {

            int i = (start + count) % moves.size();

            if (isUnvisited(chessBoard, getCoordinatesOfNeighbour(currSquare, moves.get(i)))) {

                currNeighbourDegree = getDegree(chessBoard, moves, chessBoard.getSquare(getCoordinatesOfNeighbour(currSquare, moves.get(i))));

                if(currNeighbourDegree < minDegree) {

                    minDegreeIndex = i;

                    minDegree = currNeighbourDegree;

                }

            }

        }

        return minDegreeIndex;

    }

    /**
     * Returns coordinates of the neighbour when a move is applied to the current square
     *
     * @param currSquare current square
     * @param move move which is to be applied
     * @return coordinates of the neighbour
     */
    private Coordinates getCoordinatesOfNeighbour(Square currSquare, Coordinates move) {

        return new Coordinates(currSquare.getRow() + move.getRow(),
                currSquare.getColumn() + move.getCol());

    }

    /**
     * Returns degree of a square.
     *
     * @param chessBoard board with partial tour
     * @param moves allowed moves
     * @param square square for which degree is to be found.
     * @return degree of square.
     */
    private int getDegree(Board chessBoard, List<Coordinates> moves, Square square) {

        return (int)moves.stream().filter(move -> isUnvisited(chessBoard, getCoordinatesOfNeighbour(square, move))).count();

    }

    /**
     * Returns whether a the coordinates are valid and
     * if valid whether square at given coordinates is unvisited.
     *
     * @param chessBoard board with partial tour
     * @param coordinates coordinates of square under consideration
     * @return if square at given coordinate is unvisited
     */
    private boolean isUnvisited(Board chessBoard, Coordinates coordinates) {

        return coordinates.getRow() >= 0
                && coordinates.getRow() < chessBoard.getRowCount()
                && coordinates.getCol() >= 0
                && coordinates.getCol() < chessBoard.getColCount()
                && Objects.equals(chessBoard.getSquare(coordinates).getTourPosition(), Square.DEFAULT_TOUR_POSITION);

    }

}
