package sem3.project.individual.misc;

import java.util.function.Function;

public final class MapObject
{
    private MapObject() {}

    public static <T1, T2> T2 transform(T1 initial, Function<T1, T2> mapFunction)
    {
        return mapFunction.apply(initial);
    }
}
