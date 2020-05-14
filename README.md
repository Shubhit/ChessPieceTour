# Chess Piece Tour

A chess piece tour is a path for a piece to visit all tiles on the chess board, following is a sample set of rules for movement. Assume the piece can start in any tile.

The three rules of movement for the piece are:

a) The piece can move 3 squares either North, East, South,or West.
b) The piece can move 2 squares diagonally: Northeast,Southeast, Southwest, or Northwest.
c) Each square can only be visited once.

## Solution:
There are several ways to find a piece's tour on a given board. I chose Warnsdorff's rule.

## Warnsdorff’s Rule:

Warnsdorff's rule is a heuristic for finding a single piece's tour. The piece is moved so that it always proceeds to the square from which the piece will have the fewest onward moves. When calculating the number of onward moves for each candidate square, we do not count moves that revisit any square already visited.


## Prerequisites:

For test and run this project you just need to have `mvn` command in your path.

## Assumptions:

Since Warnsdorff's rule is a heuristic, it might not be able to find a tour in the first iteration itself and hence have added retry logic with an upper limit of 100 retries.

For details of Warnsdorff's Rule: https://en.wikipedia.org/wiki/Knight%27s_tour

## Disclaimer:

Warnsdorff's rule does not perform well for boards of dimension > 50 * 50, as the random selection in tie-breaking scenario progressively leads to ~0 probability of successfully finding a tour. Multiple studies have been done that suggest various tie-breaking rules depending on size of the board and starting position of the piece, to improve the probability of successful identification of a tour.

## Run all tests:

There are several integration and unit tests for project, you can run them by this command:

    mvn test

## Run project:

In order to run this project you need to run this command:

    mvn clean compile exec:java -Dexec.mainClass="com.sample.project.chesstour.ChessTourApplication"

## Note

Project builds upon maven and java and assumes maven is pre installed. External libraries have been used in the project for build and packaging.


