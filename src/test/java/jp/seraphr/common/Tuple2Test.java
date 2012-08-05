package jp.seraphr.common;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

import org.junit.Test;

public class Tuple2Test {

    @Test
    public void test() {
        Tuple2<Integer, String> tTuple = Tuple2.create(111, "test");
        Tuple2<Integer, String> tTuple2 = Tuple2.create(111, "test");
        Tuple2<Integer, String> tTuple3 = Tuple2.create(112, "test");
        Tuple2<Integer, String> tTuple4 = Tuple2.create(111, "testes");

        assertThat(tTuple.get1(), is(111));
        assertThat(tTuple.get2(), is("test"));
        assertThat(tTuple.toString(), is("(111,test)"));
        assertThat(tTuple.hashCode(), is(tTuple2.hashCode()));
        assertThat(tTuple, is(tTuple2));
        assertThat(tTuple, is(not(tTuple3)));
        assertThat(tTuple, is(not(tTuple4)));
    }

}
