package kcn.datatypes;/* Vector2Int is an object type that represents a 2-d coordinate
 * Apparantly, this type of class is called a JavaBean:
 * https://www.javatpoint.com/java-bean
 *  */

public class Int2D
{

    private int x;
    private int y;

    public Int2D()
    {
        this.x = 0;
        this.y = 0;
    }

    public Int2D(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /* Static methods for V2I manipulation */

    public static Int2D subtract(Int2D A, Int2D B)
    {
        int newX = A.x - B.x;
        int newY = A.y - B.y;

        return new Int2D ( newX, newY);
    }

    public static Int2D add(Int2D A, Int2D B)
    {
        int newX = A.x + B.x;
        int newY = A.y + B.y;

        return new Int2D ( newX, newY);
    }

    /**
     * Instance methods
     **/

    public String toString() { return "[" + x + "][" + y + "]"; }

    /* Instance methods */

    public int x(){
        return x;
    }

    public int y(){
        return y;
    }

    public void setX(int x) { this.x = x; }

    public void setY(int y) { this.y = y; }

    public void set(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /* Method changes the object's fields x and y by increments supplied by user */
    public void translate(int xInput, int yInput)
    {
        this.x += xInput;
        this.y += yInput;
    }

    /** This snip is copied and implemented directly from **/



}
