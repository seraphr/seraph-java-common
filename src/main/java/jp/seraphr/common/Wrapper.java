package jp.seraphr.common;

/**
 * 特定の型の値をWrapして保持するクラスです。
 *
 * @param <_Elem>
 */
public class Wrapper<_Elem> {
    public Wrapper(_Elem aValue) {
        super();
        mValue = aValue;
    }

    public static <_E> Wrapper<_E> create(_E aValue){
        return new Wrapper<_E>(aValue);
    }

    private _Elem mValue;

    public _Elem getValue() {
        return mValue;
    }

    public void setValue(_Elem aValue) {
        mValue = aValue;
    }
}
