package jp.seraphr.common;

public interface ReturnableOptionMatcher<_Elem, _Result> {
    /**
     * Someにマッチした場合に呼び出されるメソッドです。
     *
     * @param aElem Someが保持する値
     * @return 何らかのメソッド返値
     */
    public _Result matchSome(_Elem aElem);

    /**
     * Noneにマッチした場合に呼び出されるメソッドです。
     *
     * @return 何らかのメソッド返値
     */
    public _Result matchNone();
}
