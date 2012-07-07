package jp.seraphr.common;

/**
 * 条件を表すインターフェースです。
 *
 * @param <T> 条件判定に使用するオブジェクトの型
 */
public interface Predicate<T> {
    /**
     * 引数に与えられたオブジェクトが条件をみたすのであればtrue、そうでないならfalseを返します。
     *
     * @param aTarget 条件判定を行う対象オブジェクト
     * @return 条件を満たす場合true
     *
     */
    public boolean apply(T aTarget);
}
