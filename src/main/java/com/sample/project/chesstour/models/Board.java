package com.sample.project.chesstour.models;

/**
 * Represents a chess board.
 */
public class Board {

    private Integer rowCount;

    private Integer colCount;

    private Square[][] squares;

    public Board(int rowCount, int cols) {

        this.rowCount = rowCount;

        this.colCount = cols;

        initializeBoard();

    }

    private void initializeBoard() {

        this.squares = new Square[rowCount][colCount];

        for (int i = 0; i < rowCount; i++) {

            for (int j = 0; j < colCount; j++) {

                squares[i][j] = new Square(i, j);

            }

        }

    }

    public Integer getRowCount() {

        return rowCount;

    }

    public void setRowCount(Integer rowCount) {

        this.rowCount = rowCount;

    }

    public Integer getColCount() {

        return colCount;

    }

    public void setColCount(Integer colCount) {

        this.colCount = colCount;

    }

    public Square[][] getSquares() {

        return squares;

    }

    public void setSquares(Square[][] squares) {

        this.squares = squares;

    }

    public Square getSquare(Coordinates coordinates) {

        return squares[coordinates.getRow()][coordinates.getCol()];

    }

}
