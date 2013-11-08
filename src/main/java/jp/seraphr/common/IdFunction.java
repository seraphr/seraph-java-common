/**
 *
 */
package jp.seraphr.common;

/**
 * 引数をそのまま返すFunction実装
 */
public class IdFunction<_T> implements Function<_T, _T> {
    @Override
    public _T convert(_T aSource) {
        return aSource;
    }
}
