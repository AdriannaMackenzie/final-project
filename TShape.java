package models;

import java.awt.*;

/**
 * TShape.java:
 * Creates a T-shaped tetronimo
 *
 * @author Adrianna MacKenzie
 * @version 1.0
 *
 * @see Point
 */
public class TShape extends Tetronimo
{
    /**
     * Creates the T-shaped tetronimo in default orientation
     * Initial shape:
     *  ###
     *   #
     */
    public TShape()
    {
        super(Color.MAGENTA);

        super.r1.setLocation( 0, 0 );
        super.r2.setLocation( Tetronimo.SIZE, 0 );
        super.r3.setLocation( Tetronimo.SIZE * 2, 0 );
        super.r4.setLocation( Tetronimo.SIZE, Tetronimo.SIZE );

        super.add( r1 );
        super.add( r2 );
        super.add( r3 );
        super.add( r4 );
    }

    /**
     * Rotates the T-shaped tetronimo through all 4 orientations
     * Cycles through: flat top -> right side -> upside down -> left side
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
            case 1: // Default: flat top
                super.r1.setLocation( 0, 0 );
                super.r2.setLocation( Tetronimo.SIZE, 0 );
                super.r3.setLocation( Tetronimo.SIZE * 2, 0 );
                super.r4.setLocation( Tetronimo.SIZE, Tetronimo.SIZE );
                break;
            case 2: // Right side
                super.r1.setLocation( Tetronimo.SIZE, 0 );
                super.r2.setLocation( 0, Tetronimo.SIZE );
                super.r3.setLocation( Tetronimo.SIZE, Tetronimo.SIZE );
                super.r4.setLocation( Tetronimo.SIZE, Tetronimo.SIZE * 2 );
                break;
            case 3: // Upside down
                super.r1.setLocation( Tetronimo.SIZE, 0 );
                super.r2.setLocation( 0, Tetronimo.SIZE );
                super.r3.setLocation( Tetronimo.SIZE, Tetronimo.SIZE );
                super.r4.setLocation( Tetronimo.SIZE * 2, Tetronimo.SIZE );
                break;
            case 0: // Left side
                super.r1.setLocation( 0, 0 );
                super.r2.setLocation( 0, Tetronimo.SIZE );
                super.r3.setLocation( Tetronimo.SIZE, Tetronimo.SIZE );
                super.r4.setLocation( 0, Tetronimo.SIZE * 2 );
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
        if( rotation == 1 || rotation == 3 )
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
        if( rotation == 1 || rotation == 3 )
        {
            return Tetronimo.SIZE * 3;
        }
        else
        {
            return Tetronimo.SIZE * 2;
        }
    }
}
