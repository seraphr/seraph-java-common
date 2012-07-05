package jp.seraphr.common;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

public class OptionTest {
    @Test
    public void testNone() {
        Option<Integer> tNone = Option.none();

        assertThat(tNone.getOrNull(), is(nullValue()));
        assertThat(tNone.getOrElse(10), is(equalTo(10)));
    }

    @Test
    public void testSome() {
        Option<Integer> tSome = Option.some(20);

        assertThat(tSome.getOrNull(), is(equalTo(20)));
        assertThat(tSome.getOrElse(10), is(equalTo(20)));
    }

    @Test
    public void testNoneWithMatcher(){
        Option<Integer> tNone = Option.none();

        tNone.match(new OptionMatcher<Integer>() {

            @Override
            public void matchSome(Integer aElem) {
                fail();
            }

            @Override
            public void matchNone() {
            }
        });
    }

    @Test
    public void testSomeWithMatcher(){
        Option<String> tSome = Option.some("abcd");

        tSome.match(new OptionMatcher<String>() {

            @Override
            public void matchSome(String aElem) {
                assertThat(aElem, is(equalTo("abcd")));
            }

            @Override
            public void matchNone() {
                fail();
            }
        });
    }
}
