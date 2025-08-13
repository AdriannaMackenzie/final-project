package models;

import java.awt.*;

/**
 * LShape.java:
 * Creates an L-shaped tetronimo
 *
 * @author Adrianna MacKenzie
 * @version 1.0
 *
 * @see Point
 */
public class LShape extends Tetronimo
{
    /**
     * Creates the L-shaped tetronimo in default orientation
     * Initial shape:
     * #
     * #
     * ##
     */
    public LShape()
    {
        super(Color.ORANGE);

        super.r1.setLocation( 0, 0 );
        super.r2.setLocation( 0, Tetronimo.SIZE );
        super.r3.setLocation( 0, Tetronimo.SIZE * 2 );
        super.r4.setLocation( Tetronimo.SIZE, Tetronimo.SIZE * 2 );

        super.add( r1 );
        super.add( r2 );
        super.add( r3 );
        super.add( r4 );
    }

    /**
     * Rotates the L-shaped tetronimo through all 4 orientations
     * Cycles through: vertical with bottom right -> horizontal with top right -> 
     * vertical with top left -> horizontal with bottom left
     */
    @Override
    public void rotate()
    {
        super.rotate();

        Point curLoc = super.getLocation();
        super.setLocation( 0, 0 );

        int rotation = super.curRotation % 4;
        
        switch( rotation )
        {
            case 1: // Default: vertical with bottom right
                super.r1.setLocation( 0, 0 );
                super.r2.setLocation( 0, Tetronimo.SIZE );
                super.r3.setLocation( 0, Tetronimo.SIZE * 2 );
                super.r4.setLocation( Tetronimo.SIZE, Tetronimo.SIZE * 2 );
                break;
            case 2: // Horizontal with top right
                super.r1.setLocation( 0, 0 );
                super.r2.setLocation( Tetronimo.SIZE, 0 );
                super.r3.setLocation( Tetronimo.SIZE * 2, 0 );
                super.r4.setLocation( 0, Tetronimo.SIZE );
                break;
            case 3: // Vertical with top left
                super.r1.setLocation( 0, 0 );
                super.r2.setLocation( Tetronimo.SIZE, 0 );
                super.r3.setLocation( Tetronimo.SIZE, Tetronimo.SIZE );
                super.r4.setLocation( Tetronimo.SIZE, Tetronimo.SIZE * 2 );
                break;
            case 0: // Horizontal with bottom left
                super.r1.setLocation( Tetronimo.SIZE * 2, 0 );
                super.r2.setLocation( 0, Tetronimo.SIZE );
                super.r3.setLocation( Tetronimo.SIZE, Tetronimo.SIZE );
                super.r4.setLocation( Tetronimo.SIZE * 2, Tetronimo.SIZE );
                break;
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
        int rotation = this.curRotation % 4;
        if( rotation == 2 || rotation == 0 )
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
        int rotation = this.curRotation % 4;
        if( rotation == 2 || rotation == 0 )
        {
            return Tetronimo.SIZE * 3;
        }
        else
        {
            return Tetronimo.SIZE * 2;
        }
    }
}
