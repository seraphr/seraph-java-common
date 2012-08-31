package jp.seraphr.common;

import java.io.Serializable;

/**
 * 2つの値の組み合わせを表します。
 *
 * @param <_Element1>
 *            一つ目の値の型
 * @param <_Element2>
 *            二つ目の値の型
 */
public class Tuple2<_Element1, _Element2> implements Serializable {

    public Tuple2(_Element1 aElement1, _Element2 aElement2) {
        mElement1 = aElement1;
        mElement2 = aElement2;
    }

    /**
     * 引数に与えられた変数から、Tuple2のインスタンスを生成して返します。
     *
     * @param aElement1
     * @param aElement2
     * @return 生成されたインスタンス
     */
    public static <_E1, _E2> Tuple2<_E1, _E2> create(_E1 aElement1, _E2 aElement2) {
        return new Tuple2<_E1, _E2>(aElement1, aElement2);
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private _Element1 mElement1;
    private _Element2 mElement2;

    /**
     *
     * @return 一つ目の要素
     */
    public _Element1 get1() {
        return mElement1;
    }

    /**
     *
     * @return 二つ目の要素
     */
    public _Element2 get2() {
        return mElement2;
    }

    @Override
    public int hashCode() {
        return this.get1().hashCode();
    }

    @Override
    public boolean equals(Object aObj) {
        if (!(aObj instanceof Tuple2))
            return false;

        Tuple2<?, ?> tObj = (Tuple2<?, ?>) aObj;

        return tObj.get1().equals(this.get1()) && tObj.get2().equals(this.get2());
    }

    @Override
    public String toString() {
        return "(" + get1().toString() + "," + get2().toString() + ")";
    }
}
