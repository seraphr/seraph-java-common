package jp.seraphr.common;

/**
 * {@linkplain Option#match(OptionMatcher)}の引数となるマッチャーインターフェースです。
 *
 * @param <_Elem>
 */
public interface OptionMatcher<_Elem> {
    /**
     * Someにマッチした場合に呼び出されるメソッドです。
     *
     * @param aElem Someが保持する値
     */
    public void matchSome(_Elem aElem);

    /**
     * Noneにマッチした場合に呼び出されるメソッドです。
     *
     */
    public void matchNone();
}
