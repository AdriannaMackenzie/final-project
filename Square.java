package models;

import java.awt.*;

/**
 * Square.java:
 * Creates a square-shaped tetronimo
 *
 * @author Adrianna MacKenzie
 * @version 1.0
 *
 * @see Point
 */
public class Square extends Tetronimo
{
    /**
     * Creates the square-shaped tetronimo
     * Shape (unchanging):
     * ##
     * ##
     */
    public Square()
    {
        super(Color.YELLOW);

        super.r1.setLocation( 0, 0 );
        super.r2.setLocation( Tetronimo.SIZE, 0 );
        super.r3.setLocation( 0, Tetronimo.SIZE );
        super.r4.setLocation( Tetronimo.SIZE, Tetronimo.SIZE );

        super.add( r1 );
        super.add( r2 );
        super.add( r3 );
        super.add( r4 );
    }

    /**
     * Rotates the square tetronimo (no visual change since it's symmetric)
     * Still increments rotation counter for consistency with base class
     */
    @Override
    public void rotate()
    {
        super.rotate();
        // Square doesn't change when rotated, so no position updates needed
    }

    /**
     * Gets the height of the tetronimo (always 2 blocks for square)
     *
     * @return The height of the tetronimo in pixels
     */
    @Override
    public int getHeight()
    {
        return Tetronimo.SIZE * 2;
    }

    /**
     * Gets the width of the tetronimo (always 2 blocks for square)
     *
     * @return The width of the tetronimo in pixels
     */
    @Override
    public int getWidth()
    {
        return Tetronimo.SIZE * 2;
    }
}
