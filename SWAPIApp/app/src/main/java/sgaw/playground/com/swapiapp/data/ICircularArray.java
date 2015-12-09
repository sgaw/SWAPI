package sgaw.playground.com.swapiapp.data;

/**
 * Exposed circular array interface used in this app.
 * @param <E>
 */
public interface ICircularArray<E> {
    int size();
    void addLast(E e);
    E get(int n);
}
