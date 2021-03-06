/**
 *
 */
package jp.seraphr.common;

/**
 * Not演算を行う{@linkplain Predicate}型です。
 */
public class NotPredicate<T> implements Predicate<T> {
    private NotPredicate(Predicate<T> aBase) {
        super();
        mBase = aBase;
    }

    public static <_E> NotPredicate<_E> create(Predicate<_E> aBase){
        return new NotPredicate<_E>(aBase);
    }

    private Predicate<T> mBase;

    @Override
    public boolean apply(T aTarget) {
        return !mBase.apply(aTarget);
    }

}
