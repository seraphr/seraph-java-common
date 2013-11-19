package jp.seraphr.common;

/**
 * 遅延初期化される値を表します。
 * 値は、コンストラクタに与えられたSupplierにより、初めて{@linkplain #get()}が呼び出された時に、1度だけ初期化されます。
 *
 * @param <T>
 */
public class LazyValue<T> {
    public LazyValue(Function0<T> aSupplier){
        mSupplier = aSupplier;
    }

    private Function0<T> mSupplier;
    private T mValue = null;
    private final Object SYNC = new Object();

    public T get(){
        if(mValue == null){
            synchronized (SYNC) {
                if(mValue == null){
                    mValue = mSupplier.apply();
                }
            }
        }

        return mValue;
    }
}
