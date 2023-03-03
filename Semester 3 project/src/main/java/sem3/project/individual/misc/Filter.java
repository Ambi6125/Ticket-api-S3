package sem3.project.individual.misc;

import java.util.Optional;

public class Filter
{
    public static <T> T single(Iterable<T> collection, Predicate<T> qualifier)
    {
        T findTargetPlaceholder = null;
        for(var v : collection)
        {
            if(qualifier.qualify(v))
            {
                if(findTargetPlaceholder == null)
                {
                    findTargetPlaceholder = v;
                }
                else
                {
                    throw new IllegalArgumentException("More than one qualifying element exists.");
                }
            }
        }

        return findTargetPlaceholder;
    }
}
