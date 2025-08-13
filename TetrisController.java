package controllers;

import models.*;
import views.TetrisBoard;
import models.TShape;
import models.LShape;
import models.JShape;
import models.SShape;
import models.ZShape;
import models.Square;
import wheelsunh.users.Rectangle;

import java.awt.*;
import java.util.Random;

/**
 * TetrisController.java:
 * Class to hold all of the game logic for tetris
 *
 * @author Professor Rossi
 * @version 1.0 July 24, 2020
 */
public class TetrisController
{
    private final TetrisBoard TETRIS_BOARD;
    private int score;
    private int piecesPlaced;
    private Color colorController[][];

    /**
     * Constructor to take in a tetris board so the controller and the board can communciate
     *
     * @param tetrisBoard A tetris board instance
     */
    public TetrisController( TetrisBoard tetrisBoard )
    {
        this.TETRIS_BOARD = tetrisBoard;
        this.score = 0;
        this.piecesPlaced = 0;
        this.colorController = new Color[TetrisBoard.WIDTH][TetrisBoard.HEIGHT];
        for (int i = 0; i < TetrisBoard.WIDTH; i++) {
            for (int j = 0; j < TetrisBoard.HEIGHT; j++) {
                colorController[i][j] = Color.WHITE;
            }
        }
    }

    /**
     * Randomly chooses the next tetronimo and returns it (INCOMPLETE)
     *
     * @return The next tetronimo to be played
     */
    public Tetronimo getNextTetromino()
    {
        Tetronimo tetronimo;
        Random rand = new Random();
        int choice = rand.nextInt(7);

        switch(choice) {
            case 0:
                tetronimo = new StraightLine();
                tetronimo.setLocation(TETRIS_BOARD.WIDTH / 2 * Tetronimo.SIZE, 0);
                break;
            case 1:
                tetronimo = new TShape();
                tetronimo.setLocation(TETRIS_BOARD.WIDTH / 2 * Tetronimo.SIZE, 0);
                break;
            case 2:
                tetronimo = new LShape();
                tetronimo.setLocation(TETRIS_BOARD.WIDTH / 2 * Tetronimo.SIZE, 0);
                break;
            case 3:
                tetronimo = new JShape();
                tetronimo.setLocation(TETRIS_BOARD.WIDTH / 2 * Tetronimo.SIZE, 0);
                break;
            case 4:
                tetronimo = new SShape();
                tetronimo.setLocation(TETRIS_BOARD.WIDTH / 2 * Tetronimo.SIZE, 0);
                break;
            case 5:
                tetronimo = new ZShape();
                tetronimo.setLocation(TETRIS_BOARD.WIDTH / 2 * Tetronimo.SIZE, 0);
                break;
            case 6:
                tetronimo = new Square();
                tetronimo.setLocation(TETRIS_BOARD.WIDTH / 2 * Tetronimo.SIZE, 0);
                break;
            default:
                tetronimo = new StraightLine();
                tetronimo.setLocation(TETRIS_BOARD.WIDTH / 2 * Tetronimo.SIZE, 0);
                break;
        }

        return tetronimo;
    }

    /**
     * Method to determine if the tetronimo has landed (INCOMPLETE)
     *
     * @param tetronimo The tetronimo to evaluate
     * @return True if the tetronimo has landed (on the bottom of the board or another tetronimo), false if it has not
     */
    public boolean tetronimoLanded( Tetronimo tetronimo, boolean[][] occupied ) {
        Rectangle[][] playingField = TETRIS_BOARD.getPlayingField();
        Rectangle[] blocks = tetronimo.getBlocks();

        for (Rectangle block : blocks) {
            // Convert current block position to array indices
            int arrayX = (block.getXLocation() - 40) / Tetronimo.SIZE;
            int arrayY = block.getYLocation() / Tetronimo.SIZE;

            System.out.println("Checking block at pixel (" + block.getXLocation() + "," + block.getYLocation() +
                    ") = array [" + arrayX + "," + arrayY + "]");

            // Check the rectangle directly below this block
            int belowY = arrayY + 1;

            // If below the bottom of the board, piece has landed
            if (belowY >= TetrisBoard.HEIGHT) {
                System.out.println("Hit bottom boundary at belowY=" + belowY);
                return true;
            }

            // If within bounds, check if the rectangle below is not white
            if (arrayX >= 0 && arrayX < TetrisBoard.WIDTH && belowY >= 0) {

                if (occupied[arrayX][belowY]) {
                    System.out.println("Collision detected with occupied block!");
                    return true;
                }
            }
        }

        return false;
    }


    public void lockTetronimo(Tetronimo tetronimo, boolean[][] occupied) {
        Rectangle[][] playingField = TETRIS_BOARD.getPlayingField();
        Rectangle[] blocks = tetronimo.getBlocks();
        Color color = tetronimo.color;

        for (Rectangle block : blocks) {
            int arrayX = (block.getXLocation() - 40) / Tetronimo.SIZE;
            int arrayY = block.getYLocation() / Tetronimo.SIZE;

            if (arrayX >= 0 && arrayX < TetrisBoard.WIDTH &&
                    arrayY >= 0 && arrayY < TetrisBoard.HEIGHT) {
                occupied[arrayX][arrayY] = true;
                playingField[arrayX][arrayY].setColor(color);
                colorController[arrayX][arrayY] = color;
                playingField[arrayX][arrayY].setFrameColor(Color.BLACK);
                tetronimo.removeTetronimo();
            }
        }

        addScore(10); // 10 points per piece placed
        this.piecesPlaced++;

        // Clear any complete rows and award points
        clearRows(occupied);
    }


    /**
     * Clears any complete rows and awards points
     *
     * @param occupied The boolean array representing occupied spaces
     */
    public void clearRows(boolean[][] occupied) {
        Rectangle[][] playingField = TETRIS_BOARD.getPlayingField();
        int rowsCleared = 0;

        // Check each row from bottom to top
        for (int row = TetrisBoard.HEIGHT - 1; row >= 0; row--) {
            if (isRowComplete(occupied, row)) {
                //clearRow(occupied, row);
                dropRowsDown(occupied, row);
                rowsCleared++;
                row++; // Check the same row again since rows dropped down
            }
        }

        if (rowsCleared > 0) {
            int points;
            if (rowsCleared == 4) {
                points = 800; // Tetris bonus
            } else {
                points = rowsCleared * 100; // 100 points per line
            }
            addScore(points);
            System.out.println("Cleared " + rowsCleared + " rows! Points awarded: " + points + " Total score: " + this.score);
        }
    }

    /**
     * Checks if a row is completely filled
     *
     * @param occupied The boolean array representing occupied spaces
     * @param row The row to check
     * @return True if the row is complete, false otherwise
     */
    private boolean isRowComplete(boolean[][] occupied, int row) {
        for (int col = 0; col < TetrisBoard.WIDTH; col++) {
            if (!occupied[col][row]) {
                return false;
            }
        }
        return true;
    }


    /**
     * Clears a specific row
     *
     * @param occupied The boolean array representing occupied spaces
     * @param row The row to clear
     */
   /* private void clearRow(boolean[][] occupied, int row) {
        Rectangle[][] playingField = TETRIS_BOARD.getPlayingField();

        for (int col = 0; col < TetrisBoard.WIDTH; col++) {
            occupied[col][row] = false;
            playingField[col][row].setColor(Color.WHITE);
        }
    }*/

    /**
     * Drops all rows above the cleared row down by one
     *
     * @param occupied The boolean array representing occupied spaces
     * @param clearedRow The row that was cleared
     */
    private void dropRowsDown(boolean[][] occupied, int clearedRow) {
        Rectangle[][] playingField = TETRIS_BOARD.getPlayingField();
        boolean toDrop;
        Color colorDrop;

        // Move all rows above the cleared row down by one
        for (int row = clearedRow; row > 0; row--) {
            for (int col = 0; col < TetrisBoard.WIDTH; col++) {
                toDrop = occupied[col][row - 1];
                occupied[col][row] = toDrop;
                colorDrop = colorController[col][row - 1];
                System.out.println("Dropping row " + row + " down by one. Color: " + colorDrop);
                playingField[col][row].setFillColor(colorDrop);
                colorController[col][row] = colorDrop;
            }
        }

        // Clear the top row
        for (int col = 0; col < TetrisBoard.WIDTH; col++) {
            occupied[col][0] = false;
            playingField[col][0].setFillColor(Color.WHITE);
        }
    }

    /**
     * Method to determine if the tetromino can safely rotate, using wall kicks if necessary
     *
     * @param tetronimo The tetromino to evaluate for rotation
     * @param occupied The boolean array representing occupied spaces on the board
     * @return True if the tetromino is in a position safe to rotate, false otherwise
     */
    public boolean readyToRotate(Tetronimo tetronimo, boolean[][] occupied) {
        Rectangle[] blocks = tetronimo.getBlocks();

        // Continue checking and kicking until the tetromino is in a safe position
        while (true) {
            boolean needsKick = false;
            boolean kickRight = true; // Default kick direction

            // Check each block of the tetromino
            for (Rectangle block : blocks) {
                int arrayX = (block.getXLocation() - 40) / Tetronimo.SIZE;
                int arrayY = block.getYLocation() / Tetronimo.SIZE;

                // Skip if block is out of bounds vertically
                if (arrayY < 0 || arrayY >= TetrisBoard.HEIGHT) {
                    continue;
                }

                // If tetromino height is 4
                if(tetronimo.getHeight() == 4 * Tetronimo.SIZE) {

                    if (arrayX <= 2 || arrayX >= TetrisBoard.WIDTH - 3 ||
                            (arrayX > 2 && occupied[arrayX - 3][arrayY]) ||
                            (arrayX < TetrisBoard.WIDTH - 3 && occupied[arrayX + 3][arrayY])) {
                        needsKick = true;
                        // Determine kick direction
                        if (arrayX <= 2 || (arrayX > 2 && occupied[arrayX - 3][arrayY])) {
                            kickRight = true;
                        } else {
                            kickRight = false;
                        }
                        break;
                    }
                } else if (tetronimo.getHeight() > tetronimo.getWidth()) {
                    if (arrayX <= 0 || arrayX >= TetrisBoard.WIDTH - 1 ||
                            (arrayX > 0 && occupied[arrayX - 1][arrayY]) ||
                            (arrayX < TetrisBoard.WIDTH - 1 && occupied[arrayX + 1][arrayY])) {
                        needsKick = true;
                        // Determine kick direction - kick away from the obstacle
                        if (arrayX <= 0 || (arrayX > 0 && occupied[arrayX - 1][arrayY])) {
                            kickRight = true; // Kick right if obstacle is on left
                        } else {
                            kickRight = false; // Kick left if obstacle is on right
                        }
                        break;
                    }
                    }
            }

            // If no kick is needed, tetromino is ready to rotate
            if (!needsKick) {
                return true;
            }

            // Perform the kick
            kick(tetronimo, kickRight);
        }
    }

    /**
     * Method to "kick" (shift) a tetromino left or right
     *
     * @param tetronimo The tetromino to kick
     * @param kickRight True to kick right, false to kick left
     */
    public void kick(Tetronimo tetronimo, boolean kickRight) {
        if (kickRight) {
            tetronimo.shiftRight();
        } else {
            tetronimo.shiftLeft();
        }
    }

    /**
     * Method to check if the tetromino can safely move left
     *
     * @param tetronimo The tetromino to evaluate
     * @param occupied The boolean array representing occupied spaces on the board
     * @return True if the tetromino can move left without collision, false otherwise
     */
    public boolean canMoveLeft(Tetronimo tetronimo, boolean[][] occupied) {
        Rectangle[] blocks = tetronimo.getBlocks();

        for (Rectangle block : blocks) {
            int arrayX = (block.getXLocation() - 40) / Tetronimo.SIZE;
            int arrayY = block.getYLocation() / Tetronimo.SIZE;

            // Check if moving left would go out of bounds
            int newX = arrayX - 1;
            if (newX < 0) {
                return false;
            }

            // Skip if block is out of bounds vertically
            if (arrayY < 0 || arrayY >= TetrisBoard.HEIGHT) {
                continue;
            }

            // Check if the space to the left is occupied
            if (occupied[newX][arrayY]) {
                return false;
            }
        }

        return true;
    }

    /**
     * Method to check if the tetromino can safely move right
     *
     * @param tetronimo The tetromino to evaluate
     * @param occupied The boolean array representing occupied spaces on the board
     * @return True if the tetromino can move right without collision, false otherwise
     */
    public boolean canMoveRight(Tetronimo tetronimo, boolean[][] occupied) {
        Rectangle[] blocks = tetronimo.getBlocks();

        for (Rectangle block : blocks) {
            int arrayX = (block.getXLocation() - 40) / Tetronimo.SIZE;
            int arrayY = block.getYLocation() / Tetronimo.SIZE;

            // Check if moving right would go out of bounds
            int newX = arrayX + 1;
            if (newX >= TetrisBoard.WIDTH) {
                return false;
            }

            // Skip if block is out of bounds vertically
            if (arrayY < 0 || arrayY >= TetrisBoard.HEIGHT) {
                continue;
            }

            // Check if the space to the right is occupied
            if (occupied[newX][arrayY]) {
                return false;
            }
        }

        return true;
    }


    /**
     * Adds points to the current score
     *
     * @param points The points to add
     */
    public void addScore(int points) {
        this.score += points;
    }

    /**
     * Gets the current score
     *
     * @return The current score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Gets the total number of pieces placed
     *
     * @return The total pieces placed
     */
    public int getPiecesPlaced() {
        return this.piecesPlaced;
    }

}


