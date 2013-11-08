/**
 *
 */
package jp.seraphr.common;

/**
 * 引数をそのまま返すFunction実装
 */
public class IdFunction<_T> implements Function<_T, _T> {
    private static final IdFunction<?> GENERIC_INSTANCE = new IdFunction<Object>();

    @SuppressWarnings("unchecked")
    public static <E> IdFunction<E> getInstance() {
        return (IdFunction<E>) GENERIC_INSTANCE;
    }

    @Override
    public _T apply(_T aSource) {
        return aSource;
    }
}
