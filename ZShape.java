package models;

import java.awt.*;

/**
 * ZShape.java:
 * Creates a Z-shaped tetronimo
 *
 * @author Adrianna MacKenzie
 * @version 1.0
 *
 * @see Point
 */
public class ZShape extends Tetronimo
{
    /**
     * Creates the Z-shaped tetronimo in default orientation
     * Initial shape:
     * ##
     *  ##
     */
    public ZShape()
    {
        super(Color.RED);

        super.r1.setLocation( 0, 0 );
        super.r2.setLocation( Tetronimo.SIZE, 0 );
        super.r3.setLocation( Tetronimo.SIZE, Tetronimo.SIZE );
        super.r4.setLocation( Tetronimo.SIZE * 2, Tetronimo.SIZE );

        super.add( r1 );
        super.add( r2 );
        super.add( r3 );
        super.add( r4 );
    }

    /**
     * Rotates the Z-shaped tetronimo through 2 orientations
     * Alternates between horizontal and vertical reverse zigzag patterns
     */
    @Override
    public void rotate()
    {
        super.rotate();

        Point curLoc = super.getLocation();
        super.setLocation( 0, 0 );

        if( super.curRotation % 2 == 1 )
        {
            // Horizontal Z
            super.r1.setLocation( 0, 0 );
            super.r2.setLocation( Tetronimo.SIZE, 0 );
            super.r3.setLocation( Tetronimo.SIZE, Tetronimo.SIZE );
            super.r4.setLocation( Tetronimo.SIZE * 2, Tetronimo.SIZE );
        }
        else
        {
            // Vertical Z
            super.r1.setLocation( Tetronimo.SIZE, 0 );
            super.r2.setLocation( Tetronimo.SIZE, Tetronimo.SIZE );
            super.r3.setLocation( 0, Tetronimo.SIZE );
            super.r4.setLocation( 0, Tetronimo.SIZE * 2 );
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
