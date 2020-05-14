package com.sample.project.chesstour.service;

import com.sample.project.chesstour.exceptions.TourNotFoundException;
import com.sample.project.chesstour.models.Board;
import com.sample.project.chesstour.models.Piece;
import com.sample.project.chesstour.models.Square;

import java.text.DecimalFormat;

public class TourService {

    private static TourFinderService tourFinderService = TourFinderService.getInstance();

    private static OutputService outputService = OutputService.getInstance();

    private static TourService instance = new TourService();

    private static Integer MAX_RETRIES = 1000;

    public static TourService getInstance() {

        return instance;

    }

    private TourService() {}

    /**
     * Performs a tour for a chess piece on the given board and outputs the tour.
     *
     * @param chessPiece chess piece for which tour is to be performed.
     * @param boardRowCount board's vertical dimension
     * @param boardColumnCount board's horizontal dimension
     */
    public void performTour(Piece chessPiece, int boardRowCount, int boardColumnCount) {

        int retries = 0;

        while (retries < MAX_RETRIES) {

            try {

                retries++;

                Board tour = tourFinderService.performTour(chessPiece, new Board(boardRowCount, boardColumnCount));

                displayTour(tour);

                return;

            } catch (TourNotFoundException e) {

                outputService.output("Retrying: " + retries);

            } catch (Exception e) {

                outputService.output("Exception occurred while trying to find a tour." + e);

                return;

            }

        }

        outputService.output("Unable to find tour with given initial position.");

    }

    /**
     * Displays the chessboard with all the legal piece's moves
     *
     * @param board board with spaces marked with their tour position.
     */
    private void displayTour(Board board) {

        DecimalFormat twoDigits = new DecimalFormat("00");

        outputService.output("\nTour : ");

        for (Square[] squares : board.getSquares()) {

            StringBuilder sb = new StringBuilder();

            for (Square square : squares) {

                sb.append("   ").append(twoDigits.format(square.getTourPosition()));

            }

            outputService.output(sb.toString());

        }

    }

}
