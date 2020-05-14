package com.sample.project.chesstour;

import com.sample.project.chesstour.exceptions.InvalidStartLocationException;
import com.sample.project.chesstour.models.Piece;
import com.sample.project.chesstour.service.OutputService;
import com.sample.project.chesstour.service.TourService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ChessTourApplication {

    private static TourService tourService = TourService.getInstance();

    private static OutputService outputService = OutputService.getInstance();

    public static void main(String[] args) {

        printApplicationInfo();

        outputService.output("\nEnter initial position (in the following format:- x y) :");

        Scanner sc = new Scanner(System.in);

        int initialRow, initialCol;

        while (sc.hasNext()) {

            try {

                initialRow = sc.nextInt();

                initialCol = sc.nextInt();

                if (initialCol < 0 || initialRow < 0) {

                    throw new InvalidStartLocationException();

                }

            } catch (InvalidStartLocationException | InputMismatchException e) {

                outputService.output("INVALID INPUT RECEIVED!! EXITING!!");

                break;

            }

            ChessTourApplication app = new ChessTourApplication();

            app.performTourFromGivenInitialPosition(initialRow, initialCol);

            outputService.output("\nEnter initial position (in the following format:- x y) :");

        }

        sc.close();

    }

    private void performTourFromGivenInitialPosition(int initialRow, int initialCol) {

        Piece piece = getChessPiece(initialRow, initialCol);

        tourService.performTour(piece, 10, 10);

    }

    private Piece getChessPiece(int initialRow, int initialCol) {

        int[] rowMoves = {3, 2, 0, -2, -3, -2, 0, 2};

        int[] colMoves = {0, -2, -3, -2, 0, 2, 3, 2};

        return new Piece(rowMoves, colMoves, initialRow, initialCol);

    }

    private static void printApplicationInfo() {

        outputService.output("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        outputService.output("*  The application produces a chess piece's tour, starting    *");
        outputService.output("*  from initial position provided by user. The cell indexing  *");
        outputService.output("*  is zero based. The application makes use of Warnsdorff's   *");
        outputService.output("*  heuristic. If non-integer/negative inputs are provided,    *");
        outputService.output("*  the program exits.                                         *");
        outputService.output("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");

    }

}