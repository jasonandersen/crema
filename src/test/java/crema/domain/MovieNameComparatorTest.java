package crema.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Testing the {@link HasNameComparator} class.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class MovieNameComparatorTest {

    private Movie movie1 = null;

    private Movie movie2 = null;

    private HasNameComparator comparator = new HasNameComparator();

    @Test
    public void testBothNull() {
        assertEquals(0, comparator.compare(movie1, movie2));
    }

    @Test
    public void testMovie1Null() {
        movie2 = new Movie();
        movie2.setName("aaaaa");
        assertEquals(-1, comparator.compare(movie1, movie2));
    }

    @Test
    public void testMovie2Null() {
        movie1 = new Movie();
        movie1.setName("aaaaa");
        assertEquals(1, comparator.compare(movie1, movie2));
    }

    @Test
    public void testMovie1First() {
        movie1 = new Movie();
        movie1.setName("aaaa");
        movie2 = new Movie();
        movie2.setName("zzzz");
        assertTrue(comparator.compare(movie1, movie2) < 0);
    }

    @Test
    public void testMovie2First() {
        movie1 = new Movie();
        movie1.setName("zzzzz");
        movie2 = new Movie();
        movie2.setName("aaaaa");
        assertTrue(comparator.compare(movie1, movie2) > 0);
    }

}
