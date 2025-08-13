package models;

import java.awt.*;

/**
 * SShape.java:
 * Creates an S-shaped tetronimo
 *
 * @author Adrianna MacKenzie
 * @version 1.0
 *
 * @see Point
 */
public class SShape extends Tetronimo
{
    /**
     * Creates the S-shaped tetronimo in default orientation
     * Initial shape:
     *  ##
     * ##
     */
    public SShape()
    {
        super(Color.GREEN);

        super.r1.setLocation( Tetronimo.SIZE, 0 );
        super.r2.setLocation( Tetronimo.SIZE * 2, 0 );
        super.r3.setLocation( 0, Tetronimo.SIZE );
        super.r4.setLocation( Tetronimo.SIZE, Tetronimo.SIZE );

        super.add( r1 );
        super.add( r2 );
        super.add( r3 );
        super.add( r4 );
    }

    /**
     * Rotates the S-shaped tetronimo through 2 orientations
     * Alternates between horizontal and vertical zigzag patterns
     */
    @Override
    public void rotate()
    {
        super.rotate();

        Point curLoc = super.getLocation();
        super.setLocation( 0, 0 );

        if( super.curRotation % 2 == 1 )
        {
            // Horizontal S
            super.r1.setLocation( Tetronimo.SIZE, 0 );
            super.r2.setLocation( Tetronimo.SIZE * 2, 0 );
            super.r3.setLocation( 0, Tetronimo.SIZE );
            super.r4.setLocation( Tetronimo.SIZE, Tetronimo.SIZE );
        }
        else
        {
            // Vertical S
            super.r1.setLocation( 0, 0 );
            super.r2.setLocation( 0, Tetronimo.SIZE );
            super.r3.setLocation( Tetronimo.SIZE, Tetronimo.SIZE );
            super.r4.setLocation( Tetronimo.SIZE, Tetronimo.SIZE * 2 );
        }

        super.setLocation( curLoc );
    }

    /**
     * Gets the height of the tetronimo based on the current orientation
     *
     * @return The height of the tetronimo in pixels
     */
    @Override
    public int getHeight()
    {
        if( this.curRotation % 2 == 1 )
        {
            return Tetronimo.SIZE * 2;
        }
        else
        {
            return Tetronimo.SIZE * 3;
        }
    }

    /**
     * Gets the width of the tetronimo based on the current orientation
     *
     * @return The width of the tetronimo in pixels
     */
    @Override
    public int getWidth()
    {
        if( this.curRotation % 2 == 1 )
        {
            return Tetronimo.SIZE * 3;
        }
        else
        {
            return Tetronimo.SIZE * 2;
        }
    }
}
