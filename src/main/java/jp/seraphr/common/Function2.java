package jp.seraphr.common;

/**
 * 2つの引数を元に、ひとつの値を返す関数を表します。
 *
 * @param <_Arg1> 第一引数型
 * @param <_Arg2> 第二引数型
 * @param <_Dest> 結果型
 */
public interface Function2<_Arg1, _Arg2, _Dest> {
    public _Dest apply(_Arg1 aArg1, _Arg2 aArg2);
}
