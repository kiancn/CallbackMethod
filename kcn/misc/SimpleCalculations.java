package kcn.misc;

public class SimpleCalculations
{
    public void NumberSquared(int number)
    {
        System.out.println ( "\n " + number + " squared is " + number * number );
    }

    public void NumberAbsolute(int number)
    {
        System.out.println ( "\n " + number + " has absolute value of " + Math.abs ( number ) );
    }

    public String returnString(String stringObject)
    {
        return stringObject;
    }

    public Int2D makeIntCoordinate(int x, int y)
    {
        return new Int2D ( x, y );
    }

    public int addNumberInArray(int[] numbers)
    {
        int outputNumber = 0;

        for(int i = 0; i < numbers.length; i++)
            {
            outputNumber += numbers[i];
            }
        return outputNumber;
    }

    public int coordinateMean(Int2D[] coords)
    {
        Int2D countCoordinate = new Int2D ();

        for(int i = 0; i < coords.length; i++)
            {
            countCoordinate.translate ( coords[i].x(),coords[i].y());
            }
        return countCoordinate.x () + countCoordinate.y();
    }

    public void longRounds(long count, long rounds)
    {
        PrintTestTime ( count, System.currentTimeMillis () );
        for(int c = 0; c < rounds; c++){ count++; }
        PrintTestTime ( count, System.currentTimeMillis () );
    }

    public void PrintTestTime(long count, long l)
    {
        System.out.printf ( "[Testing int time]  FrameCount:%n  %-10d at %-10d%n", count, l );
    }


}
