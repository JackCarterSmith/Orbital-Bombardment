package fr.jackcartersmith.ob.rendering;

public class LineDto
{
    public static AccelPositionsDto from;
    public static AccelPositionsDto to;

    public static AccelPositionsDto getFrom()
    {
        return from;
    }

    public static void setFrom(AccelPositionsDto from_in)
    {
        from = from_in;
    }

    public static AccelPositionsDto getTo()
    {
        return to;
    }

    public static void setTo(AccelPositionsDto to_in)
    {
        to = to_in;
    }
}
