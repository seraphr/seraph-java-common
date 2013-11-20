package jp.seraphr.common;

import java.util.HashMap;
import java.util.Map;

/**
 * パラメータによって遅延初期化される値を表します。
 *
 * @see LazyValue
 * @param <_Param>
 * @param <T>
 */
public class ParameterizedLazyValue<_Param, T> {
    public ParameterizedLazyValue(Function<_Param, T> aSupplier) {
        super();
        mSupplier = aSupplier;
    }

    public static <_P, _T> ParameterizedLazyValue<_P, _T> create(Function<_P, _T> aSupplier){
        return new ParameterizedLazyValue<_P, _T>(aSupplier);
    }

    private Map<_Param, T> mMap = new HashMap<_Param, T>();
    private Function<_Param, T> mSupplier;
    private final Object SYNC = new Object();

    public T get(_Param aParam){
        T tResult = mMap.get(aParam);
        if(tResult == null){
            synchronized (SYNC) {
                tResult = mMap.get(aParam);
                if(tResult == null){
                    tResult = mSupplier.apply(aParam);
                    mMap.put(aParam, tResult);
                }

            }
        }

        return tResult;
    }
}
