package kcn.misc;/* we do square matrices */

import kcn.utility.TO;

public class CharMatrix
{

    public final int sideLength;
    private String[][] matrix; /* the matrix is a 2d-char array */
    private String baseFillerCharacter; /* the filler in the matrix */

    public CharMatrix(String fillerCharacter, int sideLength)
    {
        this.matrix = new String[sideLength][sideLength];
        this.baseFillerCharacter = fillerCharacter;
        this.sideLength = sideLength;

        fillMatrixWithFillers();
    }

    /* method fills matrix with given base char */
    int fillMatrixWithFillers()
    {
        int c = 0;

        for (int i = 0; i < sideLength; i++) {
            for (int j = 0; j < sideLength; j++) {
                matrix[i][j] = baseFillerCharacter;

                c++;
            }
        }
        return c;
    }

    public String[] constructMatrixLineRepresentation()
    {
        String[] output = new String[sideLength];

        for (int i = 0; i < sideLength; i++) {
            output[i] = "\n" + constructHorizontalLineOfMatrix(i);
        }
        return output;
    }

    public void drawMatrix()
    {
        String[] lines = constructMatrixLineRepresentation();

        String output = "";

        for (String line : lines) {
            output = String.join("", output, line);

        }
        System.out.print(output+TO.ANSI_HOME);

    }

    private String constructHorizontalLineOfMatrix(int verticalLineNumber)
    {
        String output = "";

        for (int i = 0; i < sideLength; i++) {
            output = String.join(" ", output, matrix[i][verticalLineNumber]);
        }
        return output;
    }

    boolean setElementInMatrix(int x, int y, String element)
    {
        if (x >= sideLength || y >= sideLength) return false;
        else {
            matrix[x][y] = element;
        }
        return false;
    }


    public void simpleMoveThrough() throws InterruptedException
    {
        int prevX = 0;
        int prevY = 0;

        for (int i = 0; i < sideLength; i++) {

            for (int j = 0; j < sideLength; j++) {
                //Thread.sleep(150);
                TO.refreshTerminal();
                setElementInMatrix(prevX, prevY, baseFillerCharacter);

                setElementInMatrix(i, j, TO.red("i"));

                prevX = i;
                prevY = j;

                drawMatrix();
                Thread.sleep(200);
            }
        }
    }


}
