package jp.seraphr.common;

import java.io.Serializable;

/**
 * 値を持たない可能性がある型を表します。 値を持たない場合、{@linkplain None}型のインスタンスでそれを表します。
 *
 * @param <_Elem>
 *            値の型
 */
public abstract class Option<_Elem> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static None<?> NONE = new None<Object>();

    /**
     * 引数に与えられたオブジェクトを元にOptionのインスタンスを返します。 引数に与えられた値がnullであれば{@linkplain None}
     * のインスタンスを、そうでなければ{@linkplain Some}のインスタンスを返します。
     *
     * @param aElement
     *            元となるオブジェクト
     * @return aElementに対応したOptionのインスタンス
     */
    public static <_R ,_E extends _R> Option<_R> some(_E aElement) {
        if (aElement == null)
            return none();
        else
            return new Some<_R>(aElement);
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
    public _Elem getOrNull() {
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
    public boolean isSome() {
        return !isNone();
    }

    /**
     * このオブジェクトが{@linkplain Some}のインスタンスであれば、ラップしている値を、そうでなければ引数に与えられた値を返します。
     *
     * @param aElse
     *            このオブジェクトが{@linkplain None}である場合に返す値
     * @return ラップしている値、もしくは引数に与えられた値
     */
    public abstract _Elem getOrElse(_Elem aElse);

    /**
     * このオブジェクトが{@linkplain Some}のインスタンスであれば
     * {@linkplain OptionMatcher#matchSome(Object)}を呼び出します。 そうでなければ、
     * {@linkplain OptionMatcher#matchNone()}を呼び出します。
     *
     * @param aMatcher
     *            {@linkplain OptionMatcher#matchSome(Object)}・
     *            {@linkplain OptionMatcher#matchNone()}の呼び出しレシーバとなるオブジェクト
     */
    public abstract void match(OptionMatcher<_Elem> aMatcher);

    /**
     * このオブジェクトが{@linkplain Some}のインスタンスであれば
     * {@linkplain ReturnableOptionMatcher#matchSome(Object)}を呼び出します。 そうでなければ、
     * {@linkplain ReturnableOptionMatcher#matchNone()}を呼び出します。
     *
     * @param aMatcher コールバックオブジェクト
     * @return {@linkplain ReturnableOptionMatcher#matchSome(Object)}もしくは{@linkplain ReturnableOptionMatcher#matchNone()}の返値
     */
    public abstract <_Result> _Result match(ReturnableOptionMatcher<_Elem, _Result> aMatcher);

    /**
     * このオブジェクトがNoneであればNoneを返します。
     * このオブジェクトがSomeであれば、中身をaFuncによって変換したSomeを返します。
     *
     * @param aFunc 変換関数
     * @return aFuncによって変換されたOption
     */
    public abstract <_Dest> Option<_Dest> map(Function<_Elem, _Dest> aFunc);

    /**
     * このオブジェクトがNoneであればNoneを返します。
     * このオブジェクトがSomeであれば、中身に対しaFuncを適用して、適用結果を返します。
     *
     * @param aFunc 変換関数
     * @return aFuncによって変換されたOption
     */
    public abstract <_Dest> Option<_Dest> flatMap(Function<_Elem, Option<_Dest>> aFunc);

    /**
     * nullではない、何らかの値を保持していることを表します。
     *
     * @param <_E>
     */
    private static class Some<_E> extends Option<_E> {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

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
        public <_Result> _Result match(ReturnableOptionMatcher<_E, _Result> aMatcher) {
            return aMatcher.matchSome(mElement);
        }

        /**
         * @see jp.seraphr.common.Option#map(jp.seraphr.common.Function)
         */
        @Override
        public <_Dest> Option<_Dest> map(Function<_E, _Dest> aFunc) {
            return some(aFunc.convert(getOrNull()));
        }

        /**
         *
         * @see jp.seraphr.common.Option#flatMap(jp.seraphr.common.Function)
         */
        @Override
        public <_Dest> Option<_Dest> flatMap(Function<_E, Option<_Dest>> aFunc) {
            return aFunc.convert(getOrNull());
        }

        @Override
        public boolean isNone() {
            return false;
        }

        @Override
        public int hashCode() {
            return getOrNull().hashCode();
        }

        @Override
        public boolean equals(Object aObj) {
            if (!(aObj instanceof Some))
                return false;

            Some<?> tSome = (Some<?>) aObj;

            return tSome.getOrNull().equals(getOrNull());
        }

        @Override
        public String toString() {
            return "Some(" + getOrNull() + ")";
        }
    }

    /**
     * 値が存在しないことを表します。
     *
     * @param <_E>
     */
    private static class None<_E> extends Option<_E> {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        @Override
        public _E getOrElse(_E aElse) {
            return aElse;
        }

        @Override
        public void match(OptionMatcher<_E> aMatcher) {
            aMatcher.matchNone();
        }

        @Override
        public <_Result> _Result match(ReturnableOptionMatcher<_E, _Result> aMatcher) {
            return aMatcher.matchNone();
        }

        /**
         * @see jp.seraphr.common.Option#map(jp.seraphr.common.Function)
         */
        @Override
        public <_Dest> Option<_Dest> map(Function<_E, _Dest> aFunc) {
            return none();
        }

        /**
         * @see jp.seraphr.common.Option#flatMap(jp.seraphr.common.Function)
         */
        @Override
        public <_Dest> Option<_Dest> flatMap(Function<_E, Option<_Dest>> aFunc) {
            return none();
        }

        @Override
        public boolean isNone() {
            return true;
        }

        @Override
        public String toString() {
            return "None";
        }
    }
}
