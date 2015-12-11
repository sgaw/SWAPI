package sgaw.playground.com.swapiapp.util;

import android.support.v4.util.CircularArray;

import sgaw.playground.com.swapiapp.util.ICircularArray;

/**
 * Wrapper for {@link android.support.v4.util.CircularArray} that has a mockable
 * interface because CircularArray is both final and gets stubbed out in unit tests.
 */
public class CircularArrayWrapper<E> implements ICircularArray<E> {
    CircularArray<E> mCircularArray;

    public CircularArrayWrapper() {
        mCircularArray = new CircularArray<>();
    }

    public CircularArrayWrapper(int capacity) {
        mCircularArray = new CircularArray<>(capacity);
    }
    @Override
    public int size() {
        return mCircularArray.size();
    }

    @Override
    public void addLast(E e) {
        mCircularArray.addLast(e);
    }

    @Override
    public E get(int n) {
        return mCircularArray.get(n);
    }
}