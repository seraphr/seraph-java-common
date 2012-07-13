package jp.seraphr.common;

/**
 * 値を持たない可能性がある型を表します。
 * 値を持たない場合、{@linkplain None}型のインスタンスでそれを表します。
 *
 * @param <_Elem> 値の型
 */
public abstract class Option<_Elem> {

    private static None<?> NONE = new None<Object>();

    /**
     * 引数に与えられたオブジェクトを元にOptionのインスタンスを返します。
     * 引数に与えられた値がnullであれば{@linkplain None}のインスタンスを、そうでなければ{@linkplain Some}のインスタンスを返します。
     *
     * @param aElement 元となるオブジェクト
     * @return aElementに対応したOptionのインスタンス
     */
    public static <_E> Option<_E> some(_E aElement) {
        if (aElement == null)
            return none();
        else
            return new Some<_E>(aElement);
    }

    /**
     * {@linkplain None}のインスタンスを返します。
     *
     * @return Noneのインスタンス
     */
    @SuppressWarnings("unchecked")
    public static <_E> Option<_E> none() {
        return (Option<_E>) NONE;
    }

    /**
     * このオブジェクトが{@linkplain Some}であれば、ラップしている値を、そうでなければnullを返します。
     *
     * @return ラップしている値 もしくはnull
     */
    public _Elem getOrNull(){
        return getOrElse(null);
    }

    /**
     * このオブジェクトなNoneであればtrueを返します。
     *
     * @see #isSome()
     * @return
     */
    public abstract boolean isNone();

    /**
     * このオブジェクトがSomeであればtrueを返します。
     *
     * @see Option#isNone()
     * @return
     */
    public boolean isSome(){
        return !isNone();
    }

    /**
     * このオブジェクトが{@linkplain Some}のインスタンスであれば、ラップしている値を、そうでなければ引数に与えられた値を返します。
     *
     * @param aElse このオブジェクトが{@linkplain None}である場合に返す値
     * @return ラップしている値、もしくは引数に与えられた値
     */
    public abstract _Elem getOrElse(_Elem aElse);

    /**
     * このオブジェクトが{@linkplain Some}のインスタンスであれば{@linkplain OptionMatcher#matchSome(Object)}を呼び出します。
     * そうでなければ、{@linkplain OptionMatcher#matchNone()}を呼び出します。
     *
     * @param aMatcher {@linkplain OptionMatcher#matchSome(Object)}・{@linkplain OptionMatcher#matchNone()}の呼び出しレシーバとなるオブジェクト
     */
    public abstract void match(OptionMatcher<_Elem> aMatcher);

    /**
     * nullではない、何らかの値を保持していることを表します。
     *
     * @param <_E>
     */
    private static class Some<_E> extends Option<_E> {
        public Some(_E aElement) {
            mElement = aElement;
        }

        private _E mElement;

        @Override
        public _E getOrElse(_E aElse) {
            return mElement;
        }

        @Override
        public void match(OptionMatcher<_E> aMatcher) {
            aMatcher.matchSome(mElement);
        }

        @Override
        public boolean isNone() {
            return false;
        }
    }

    /**
     * 値が存在しないことを表します。
     *
     * @param <_E>
     */
    private static class None<_E> extends Option<_E> {

        @Override
        public _E getOrElse(_E aElse) {
            return aElse;
        }

        @Override
        public void match(OptionMatcher<_E> aMatcher) {
            aMatcher.matchNone();
        }

        @Override
        public boolean isNone() {
            return true;
        }
    }
}
