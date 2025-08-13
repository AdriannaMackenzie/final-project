package models;

import wheelsunh.users.Animator;
import wheelsunh.users.Rectangle;
import wheelsunh.users.ShapeGroup;

import java.awt.*;

/**
 * Tetronimo.java:
 * An abstract class to model the base capaabilities of a tetronimo
 *
 * @author Professor Rossi
 * @version 1.0 July 24, 2020
 *
 * @see java.awt.Color
 */
public abstract class Tetronimo extends ShapeGroup
{
    /**
     * Constant to represent the size of the tetronimo
     */
    public static final int SIZE= 20;
    public Color color;
    protected Rectangle r1;
    protected Rectangle r2;
    protected Rectangle r3;
    protected Rectangle r4;

    protected int curRotation = 1;

    /**
     * Generates the four rectangles for the tetronino and puts them on the screen, they are at the default coordinates
     * to start
     */
    public Tetronimo(Color color)
    {
        super();

        this.color = color;

        this.r1 = new Rectangle();
        this.r1.setSize( Tetronimo.SIZE, Tetronimo.SIZE );
        this.r1.setFrameColor( Color.BLACK );
        this.r1.setFillColor( color );

        this.r2 = new Rectangle();
        this.r2.setSize( Tetronimo.SIZE, Tetronimo.SIZE );
        this.r2.setFrameColor( Color.BLACK );
        this.r2.setFillColor( color );

        this.r3 = new Rectangle();
        this.r3.setSize( Tetronimo.SIZE, Tetronimo.SIZE );
        this.r3.setFrameColor( Color.BLACK );
        this.r3.setFillColor( color );

        this.r4 = new Rectangle();
        this.r4.setSize( Tetronimo.SIZE, Tetronimo.SIZE );
        this.r4.setFrameColor( Color.BLACK );
        this.r4.setFillColor( color );
    }

    /**
     * Increments the rotation of the tetronimo, other classes need to override this to provide the full functionality
     */
    public void rotate()
    {
        this.curRotation++;
    }

    /**
     * Shifts the tetronimo left one row
     */
    public void shiftLeft()
    {
        super.setLocation( super.getXLocation() - Tetronimo.SIZE, super.getYLocation() );
    }

    /**
     * Shifts the tetronimo right one row
     */
    public void shiftRight()
    {
        super.setLocation( super.getXLocation() + Tetronimo.SIZE, super.getYLocation() );
    }

    /**
     * Returns an array of all four rectangle blocks that make up this tetronimo
     */
    public Rectangle[] getBlocks()
    {
        return new Rectangle[]{r1, r2, r3, r4};
    }

    public void removeTetronimo()
    {
        this.r1.hide();
        this.r2.hide();
        this.r3.hide();
        this.r4.hide();
    }
}

