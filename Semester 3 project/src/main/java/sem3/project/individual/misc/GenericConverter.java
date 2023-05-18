package sem3.project.individual.misc;

import java.util.function.Function;

public final class GenericConverter
{
    private GenericConverter() {}

    public static  <T, R> R transform(Function<T, R> transformer, T input)
    {
        return transformer.apply(input);
    }
}