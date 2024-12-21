package filter;

// generic superclass
public interface AbstractFilter<T> {
    boolean accept(T entity);
}
