package com.sample.project.chesstour.models;

import com.sample.project.chesstour.exceptions.InvalidAllowedMovesException;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a chess piece.
 */
public class Piece {

    private Coordinates position;

    //List of legal moves
    private final List<Coordinates> moves;

    public Piece(int[] rowMoves, int[] colMoves, int rowPos, int colPos) {

        position = new Coordinates(rowPos, colPos);

        moves = initMoves(rowMoves, colMoves);

    }

    public Piece(Coordinates position, List<Coordinates> moves) {

        this.position = position;

        this.moves = moves;

    }

    public Piece(int rowPos, int colPos, List<Coordinates> moves) {

        this.position = new Coordinates(rowPos, colPos);

        this.moves = moves;

    }

    private List<Coordinates> initMoves(int[] rowMoves, int[] colMoves) {

        if(rowMoves == null || colMoves == null || rowMoves.length != colMoves.length) {

            throw new InvalidAllowedMovesException();

        }

        List<Coordinates> allowedMoves = new ArrayList<>();

        for(int i = 0; i < rowMoves.length; i++){

            allowedMoves.add(new Coordinates(rowMoves[i], colMoves[i]));

        }

        return allowedMoves;

    }

    public void setPosition(Coordinates position) {

        this.position = position;

    }

    public List<Coordinates> getMoves() {

        return moves;

    }

    public Coordinates getPosition() {

        return position;

    }

}
