package jp.seraphr.common;

/**
 * 0引数関数を表すインターフェースです。
 *
 * @param <_Ret>
 */
public interface Function0<_Ret> {
    public _Ret apply();
}
