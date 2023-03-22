package sem3.project.individual.misc;

@FunctionalInterface
public interface MessageArgBuilder
{
    String construct(Object... args);
}
