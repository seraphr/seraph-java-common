package jp.seraphr.common;

/**
 * 一つの引数を受け取る、何らかの処理を表すインターフェースです。
 *
 * @param <_Arg>
 */
public interface Action<_Arg> {
    public void apply(_Arg aArg);
}
