package jp.seraphr.common;

/**
 * 2つの型の間の同一性をチェックする関数を表します。
 *
 * @param <_Left>
 * @param <_Right>
 */
public interface Equivalence<_Left, _Right> {
    /**
     * aLeftとaRightを比較し、両者の関係が条件を満たすかどうかを調べて返します。
     *
     * @param aLeft
     * @param aRight
     * @return 等価とみなせる場合true
     */
    public boolean apply(_Left aLeft, _Right aRight);
}
