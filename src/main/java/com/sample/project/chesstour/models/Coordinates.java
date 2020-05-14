package com.sample.project.chesstour.models;

/**
 * Represents a position.
 */
public class Coordinates {

    private Integer row;

    private Integer col;

    public Coordinates(int row, int col) {

        this.row = row;

        this.col = col;

    }


    public Integer getRow() {

        return row;

    }

    public void setRow(int row) {

        this.row = row;

    }


    public Integer getCol() {

        return col;

    }

    public void setCol(int col) {

        this.col = col;

    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {

            return true;

        }

        if (!(o instanceof Coordinates)) {

            return false;

        }

        Coordinates other = (Coordinates) o;

        return getRow().equals(other.getRow()) && getCol().equals(other.getCol());

    }

    @Override
    public int hashCode() {

        int result = getRow().hashCode();

        result = 31 * result + getCol().hashCode();

        return result;

    }

}
