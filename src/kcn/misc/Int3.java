package kcn.misc;



public class Int3{
    public int a;
    public int b;
    public int c;

    public Int3(int a, int b, int c)
    {
        this.a = a;
        this.b = b;
        this.c = c;

        System.out.println("Bing!");
    }

    @Override
    public String toString()
    {
        return "Int3{" +
               "a=" + a +
               ", b=" + b +
               ", c=" + c +
               '}';
    }
}
