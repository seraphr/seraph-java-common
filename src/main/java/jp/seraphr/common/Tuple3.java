package jp.seraphr.common;

/**
 * 3つの値の組み合わせを表します。
 *
 * @param <_E1> 一つ目の値の型
 * @param <_E2> 二つ目の値の型
 * @param <_E3> ３つ目の値の型
 */
public class Tuple3<_E1, _E2, _E3> {
    public Tuple3(_E1 aE1, _E2 aE2, _E3 aE3) {
        super();
        mE1 = aE1;
        mE2 = aE2;
        mE3 = aE3;
    }

    public static <_El1, _El2, _El3> Tuple3<_El1, _El2, _El3> create(_El1 aE1, _El2 aE2, _El3 aE3) {
        return new Tuple3<_El1, _El2, _El3>(aE1, aE2, aE3);
    }

    private _E1 mE1;
    private _E2 mE2;
    private _E3 mE3;

    /**
     * @return e1
     */
    public _E1 get1() {
        return mE1;
    }

    /**
     * @return e2
     */
    public _E2 get2() {
        return mE2;
    }

    /**
     * @return e3
     */
    public _E3 get3() {
        return mE3;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return get1().hashCode();
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object aObj) {
        if (!(aObj instanceof Tuple3))
            return false;

        Tuple3<?, ?, ?> tObj = (Tuple3<?, ?, ?>) aObj;

        return tObj.get1().equals(this.get1()) && tObj.get2().equals(this.get2()) && tObj.get3().equals(this.get3());
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "(" + get1().toString() + "," + get2().toString() + "," + get3().toString() + ")";
    }

}
