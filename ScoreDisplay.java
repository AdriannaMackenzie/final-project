package views;

import wheelsunh.users.Rectangle;
import wheelsunh.users.TextBox;
import java.awt.*;

/**
 * ScoreDisplay.java:
 * GUI component to display the current score and game statistics
 *
 * @author Student
 * @version 1.0
 */
public class ScoreDisplay {
    private TextBox scoreLabel;
    private TextBox scoreValue;
    private TextBox piecesLabel;
    private TextBox piecesValue;
    private Rectangle background;
    
    private static final int DISPLAY_X = 300; // Position to the right of game board
    private static final int DISPLAY_Y = 50;
    private static final int DISPLAY_WIDTH = 70;
    private static final int DISPLAY_HEIGHT = 50;
    
    /**
     * Constructor to create the score display
     */
    public ScoreDisplay() {
        // Create background
        this.background = new Rectangle();
        this.background.setLocation(DISPLAY_X, DISPLAY_Y);
        this.background.setSize(DISPLAY_WIDTH, DISPLAY_HEIGHT);
        this.background.setFillColor(Color.LIGHT_GRAY);
        this.background.setFrameColor(Color.BLACK);
        
        // Create score value
        this.scoreValue = new TextBox();
        this.scoreValue.setLocation(DISPLAY_X + 20, DISPLAY_Y + 20);
        this.scoreValue.setSize(50,20);
        this.scoreValue.setText("0");
        this.scoreValue.setColor(Color.LIGHT_GRAY);
    }
    
    /**
     * Updates the score display with current values
     * @param score Current score
     * @param pieces Current number of pieces placed
     */
    public void updateScore(int score, int pieces) {
        this.scoreValue.setText(String.valueOf(score));
        this.piecesValue.setText(String.valueOf(pieces));
    }
    
    /**
     * Hides the score display
     */
    public void hide() {
        this.background.hide();
        this.scoreLabel.hide();
        this.scoreValue.hide();
        this.piecesLabel.hide();
        this.piecesValue.hide();
    }
    
    /**
     * Shows the score display
     */
    public void show() {
        this.background.show();
        this.scoreLabel.show();
        this.scoreValue.show();
        this.piecesLabel.show();
        this.piecesValue.show();
    }
}