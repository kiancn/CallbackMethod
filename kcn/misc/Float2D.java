package kcn.misc;

public class Float2D
{
    private float x;
    private float y;

    public Float2D()
    {
        this.x = 0;
        this.y = 0;
    }

    public Float2D(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    /* Static methods for V2I manipulation */
    public static Float2D subtract(Float2D A, Float2D B)
    {
        float newX = A.x - B.x;
        float newY = A.y - B.y;

        return new Float2D ( newX, newY);
    }

    public static Float2D add(Float2D A, Float2D B)
    {
        float newX = A.x + B.x;
        float newY = A.y + B.y;
        return new Float2D ( newX, newY);
    }

    public String toString()
    {
        return "[" + x + "][" + y + "]";
    }

    /** Instance methods **/

    /*Setter methods*/
    public void x(float x) { this.x = x; }
    public void y(float y) { this.y = y; }

    public void set(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /* Getter methods */
    public float x() {return this.x;}
    public float y() {return this.y;}

    /*  */

    /* Method returns the values of the V2F as a 2-d array of floats. overkill */
    public float[][] get()
    {
        return new float[][]{{this.x}, {this.y}};
    }

    /* Method changes the fields x and y by increments supplied by user */
    public void translate(float xInput, float yInput)
    {
        this.x += xInput;
        this.y = yInput;
    }

}
