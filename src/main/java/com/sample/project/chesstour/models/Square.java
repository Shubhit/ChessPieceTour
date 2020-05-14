package com.sample.project.chesstour.models;

import java.util.Objects;

/**
 * Represents a cell on a chess board.
 */
public class Square {

    private Coordinates location;

    private Integer tourPosition;

    public static Integer DEFAULT_TOUR_POSITION = -1;

    public Square(Integer row, Integer column) {

        this.location = new Coordinates(row, column);

        this.tourPosition = DEFAULT_TOUR_POSITION;
    }

    public Integer getRow() {

        return location.getRow();

    }

    public void setRow(Integer row) {

        this.location.setRow(row);

    }

    public Integer getColumn() {

        return location.getCol();

    }

    public void setColumn(Integer column) {

        this.location.setCol(column);

    }

    public Integer getTourPosition() {

        return tourPosition;

    }

    public void setTourPosition(Integer tourPosition) {

        this.tourPosition = tourPosition;

    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {

            return true;

        }

        if (!(o instanceof Square)) {

            return false;

        }

        Square square = (Square) o;

        return Objects.equals(getRow(), square.getRow())
                && Objects.equals(getColumn(), square.getColumn())
                && Objects.equals(getTourPosition(), square.getTourPosition());

    }

    @Override
    public int hashCode() {

        int result = getRow();

        result = 31 * result + getColumn();

        result = 31 * result + getTourPosition();

        return result;

    }

}
