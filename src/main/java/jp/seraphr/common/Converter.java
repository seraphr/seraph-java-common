package jp.seraphr.common;

/**
 * _Source型から_Dest型への変換関数を表す型。
 *
 * もしかしたらFunctionって名前にするかも。
 *
 * @param <_Source> 変換元の型
 * @param <_Dest> 変換先の型
 */
public interface Converter<_Source, _Dest> {
    /**
     *
     * @param aSource 変換元オブジェクト
     * @return 変換済みオブジェクト
     */
    public _Dest convert(_Source aSource);
}
