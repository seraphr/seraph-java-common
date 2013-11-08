package jp.seraphr.common;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

public class OptionTest {
    @Test
    public void testNone() {
        Option<Integer> tNone = Option.none();

        assertThat(tNone.getOrNull(), is(nullValue()));
        assertThat(tNone.getOrElse(10), is(equalTo(10)));
        assertThat(tNone.iterator().hasNext(), is(equalTo(false)));
    }

    @Test
    public void testSome() {
        Option<Integer> tSome = Option.some(20);

        assertThat(tSome.getOrNull(), is(equalTo(20)));
        assertThat(tSome.getOrElse(10), is(equalTo(20)));

        Iterator<Integer> tItr = tSome.iterator();
        assertThat(tItr.hasNext(), is(equalTo(true)));
        assertThat(tItr.next(), is(equalTo(20)));
        assertThat(tItr.hasNext(), is(equalTo(true)));
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

    @Test
    public void testNoneWithReturnableMatcher(){
        Option<Integer> tNone = Option.none();

        Integer tResult = tNone.match(new ReturnableOptionMatcher<Integer, Integer>() {

            @Override
            public Integer matchSome(Integer aElem) {
                return 1;
            }

            @Override
            public Integer matchNone() {
                return 0;
            }
        });

        assertThat(tResult, is(equalTo(0)));
    }

    @Test
    public void testSomeWithReturnableMatcher(){
        Option<String> tSome = Option.some("abcd");

        String tResult = tSome.match(new ReturnableOptionMatcher<String, String>() {

            @Override
            public String matchSome(String aElem) {
                return aElem;
            }

            @Override
            public String matchNone() {
                return "none";
            }
        });

        assertThat(tResult, is(equalTo("abcd")));

    }

    @Test
    public void testNoneWithMap(){
        Option<Integer> tNone = Option.none();

        Option<String> tResult = tNone.map(new Function<Integer, String>() {
            @Override
            public String convert(Integer aSource) {
                return aSource.toString();
            }
        });
        assertThat(tResult, is(equalTo(Option.<String>none())));
    }

    @Test
    public void testSomeWithMap(){
        Option<String> tSome = Option.some("abcd");

        Option<String> tResult = tSome.map(new Function<String, String>(){
            @Override
            public String convert(String aSource) {
                return aSource + aSource;
            }
        });

        Option<String> tExpected = Option.some("abcdabcd");

        assertThat(tResult, is(equalTo(tExpected)));
    }

    @Test
    public void testNoneWithFlatMap(){
        Option<Integer> tNone = Option.none();

        Option<String> tResult = tNone.flatMap(new Function<Integer, Option<String>>(){
            @Override
            public Option<String> convert(Integer aSource) {
                return Option.some(aSource.toString());
            }
        });
        assertThat(tResult, is(equalTo(Option.<String>none())));
    }

    @Test
    public void testSomeWithFlatMap(){
        Option<String> tSome = Option.some("abcd");

        Option<String> tResult = tSome.flatMap(new Function<String, Option<String>>(){
            @Override
            public Option<String> convert(String aSource) {
                return Option.some(aSource + aSource);
            }
        });

        Option<String> tExpected = Option.some("abcdabcd");
        assertThat(tResult, is(equalTo(tExpected)));
    }
}
