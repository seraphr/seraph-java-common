package jp.seraphr.common;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ParameterizedLazyValueTest {

    @Test
    public void test() {
        final int tThreadCount = 100;
        final List<String> tCallList = new ArrayList<String>();

        final ParameterizedLazyValue<String, Integer> tValue = ParameterizedLazyValue.create(new Function<String, Integer>() {
            @Override
            public Integer apply(String aParam) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    fail();
                }

                tCallList.add(aParam);
                return Integer.parseInt(aParam);
            }
        });

        List<Thread> tThreads = new ArrayList<Thread>();
        for (int i = 0; i < tThreadCount; i++) {
            tThreads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    assertThat(tValue.get("10"), is(10));
                    assertThat(tValue.get("20"), is(20));
                    assertThat(tValue.get("30"), is(30));
                    assertThat(tValue.get("30"), is(30));
                }
            }));
        }

        for (Thread tThread : tThreads) {
            tThread.run();
        }

        for (Thread tThread : tThreads) {
            try {
                tThread.join();
            } catch (InterruptedException e) {
                fail();
            }
        }

        assertThat(tCallList.size(), is(3));
        assertThat(tCallList, hasItems("10", "20", "30"));
    }

}
