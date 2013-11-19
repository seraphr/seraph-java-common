package jp.seraphr.common;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class LazyValueTest {

    @Test
    public void test() {
        final int tThreadCount = 100;
        final List<Object> tCallList = new ArrayList<Object>();

        final LazyValue<Integer> tValue = new LazyValue<Integer>(new Function0<Integer>() {
            @Override
            public Integer apply() {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    fail();
                }

                tCallList.add(new Object());
                return 100;
            }
        });

        List<Thread> tThreads = new ArrayList<Thread>();
        for (int i = 0; i < tThreadCount; i++) {
            tThreads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    assertThat(tValue.get(), is(100));
                    assertThat(tValue.get(), is(100));
                    assertThat(tValue.get(), is(100));
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

        assertThat(tCallList.size(), is(1));
    }

}
