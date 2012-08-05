package jp.seraphr.common;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

import org.junit.Test;

public class Tuple3Test {

    @Test
    public void test() {
        Tuple3<Integer, String, Boolean> tTuple1 = Tuple3.create(111, "test", true);
        Tuple3<Integer, String, Boolean> tTuple2 = Tuple3.create(111, "test", true);
        Tuple3<Integer, String, Boolean> tTuple3 = Tuple3.create(112, "test", true);
        Tuple3<Integer, String, Boolean> tTuple4 = Tuple3.create(111, "testes", true);
        Tuple3<Integer, String, Boolean> tTuple5 = Tuple3.create(111, "test", false);

        assertThat(tTuple1.get1(), is(111));
        assertThat(tTuple1.get2(), is("test"));
        assertThat(tTuple1.get3(), is(true));
        assertThat(tTuple1.toString(), is("(111,test,true)"));
        assertThat(tTuple1.hashCode(), is(tTuple2.hashCode()));
        assertThat(tTuple1, is(tTuple2));
        assertThat(tTuple1, is(not(tTuple3)));
        assertThat(tTuple1, is(not(tTuple4)));
        assertThat(tTuple1, is(not(tTuple5)));
    }

}
