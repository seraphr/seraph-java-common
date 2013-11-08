package jp.seraphr.common;

/**
 * _Source型から_Dest型への関数を表します。
 *
 * @param <_Source> 変換元の型
 * @param <_Dest> 変換先の型
 */
public interface Function<_Source, _Dest> {
    /**
     *
     * @param aSource 変換元オブジェクト
     * @return 変換済みオブジェクト
     */
    public _Dest convert(_Source aSource);
}
