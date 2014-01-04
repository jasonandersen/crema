package crema.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

/**
 * Testing the {@link Attribute} class.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class AttributeTest {

    private Attribute attrib1;

    private Attribute attrib2;

    @Test
    public void testCompareNull() {
        attrib1 = new Attribute(AttributeType.DISPLAY_RESOLUTION, AttributeSource.FILE_NAME, "blah");
        attrib2 = null;
        assertEquals(-1, attrib1.compareTo(attrib2));
    }

    @Test
    public void testCompareDifferentSources1() {
        attrib1 = new Attribute(AttributeType.DISPLAY_RESOLUTION, AttributeSource.ROTTEN_TOMATOES, "blah");
        attrib2 = new Attribute(AttributeType.DISPLAY_RESOLUTION, AttributeSource.FILE_NAME, "blah");
        assertEquals(-1, attrib1.compareTo(attrib2));
    }

    @Test
    public void testCompareDifferentSources2() {
        attrib1 = new Attribute(AttributeType.DISPLAY_RESOLUTION, AttributeSource.FILE_NAME, "blah");
        attrib2 = new Attribute(AttributeType.DISPLAY_RESOLUTION, AttributeSource.ROTTEN_TOMATOES, "blah");
        assertEquals(1, attrib1.compareTo(attrib2));
    }

    @Test
    public void testCompareSameSourceDifferentType1() {
        attrib1 = new Attribute(AttributeType.RELEASE_YEAR, AttributeSource.FILE_NAME, "blah");
        attrib2 = new Attribute(AttributeType.RECORDING_SOURCE, AttributeSource.FILE_NAME, "blah");
        assertEquals(-1, attrib1.compareTo(attrib2));
    }

    @Test
    public void testCompareSameSourceDifferentType2() {
        attrib1 = new Attribute(AttributeType.RECORDING_SOURCE, AttributeSource.FILE_NAME, "blah");
        attrib2 = new Attribute(AttributeType.RELEASE_YEAR, AttributeSource.FILE_NAME, "blah");
        assertEquals(1, attrib1.compareTo(attrib2));
    }

    @Test
    public void testCompareSameTypeSameSource() {
        attrib1 = new Attribute(AttributeType.RECORDING_SOURCE, AttributeSource.FILE_NAME, "a");
        attrib2 = new Attribute(AttributeType.RECORDING_SOURCE, AttributeSource.FILE_NAME, "b");
        assertEquals(-1, attrib1.compareTo(attrib2));
    }

    @Test
    public void testCompareSameTypeSameSourceSameValue() {
        attrib1 = new Attribute(AttributeType.RECORDING_SOURCE, AttributeSource.FILE_NAME, "a");
        attrib2 = new Attribute(AttributeType.RECORDING_SOURCE, AttributeSource.FILE_NAME, "a");
        assertEquals(0, attrib1.compareTo(attrib2));
    }

    @Test
    public void testEqualsNull() {
        attrib1 = new Attribute(AttributeType.RECORDING_SOURCE, AttributeSource.FILE_NAME, "a");
        attrib2 = null;
        assertNotEquals(attrib1, attrib2);
    }

    @Test
    public void testEqualsNotSameType() {
        attrib1 = new Attribute(AttributeType.RECORDING_SOURCE, AttributeSource.FILE_NAME, "a");
        assertNotEquals(attrib1, "THIS IS NOT AN ATTRIBUTE");
    }

    @Test
    public void testEqualsSameTypeSameSourceSameValue() {
        attrib1 = new Attribute(AttributeType.RECORDING_SOURCE, AttributeSource.FILE_NAME, "a");
        attrib2 = new Attribute(AttributeType.RECORDING_SOURCE, AttributeSource.FILE_NAME, "a");
        assertEquals(attrib1, attrib2);
    }

    @Test
    public void testEqualsSameTypeSameSourceDifferentValue() {
        attrib1 = new Attribute(AttributeType.RECORDING_SOURCE, AttributeSource.FILE_NAME, "a");
        attrib2 = new Attribute(AttributeType.RECORDING_SOURCE, AttributeSource.FILE_NAME, "b");
        assertNotEquals(attrib1, attrib2);
    }

    @Test
    public void testEqualsSameTypeDifferentSourceSameValue() {
        attrib1 = new Attribute(AttributeType.RECORDING_SOURCE, AttributeSource.FILE_NAME, "a");
        attrib2 = new Attribute(AttributeType.DISPLAY_RESOLUTION, AttributeSource.FILE_NAME, "a");
        assertNotEquals(attrib1, attrib2);
    }

    @Test
    public void testHashCodeSameTypeSameSourceSameValue() {
        attrib1 = new Attribute(AttributeType.RECORDING_SOURCE, AttributeSource.FILE_NAME, "a");
        attrib2 = new Attribute(AttributeType.RECORDING_SOURCE, AttributeSource.FILE_NAME, "a");
        assertEquals(attrib1.hashCode(), attrib2.hashCode());
    }

    @Test
    public void testHashCodeSameTypeDifferentSourceSameValue() {
        attrib1 = new Attribute(AttributeType.RECORDING_SOURCE, AttributeSource.FILE_NAME, "a");
        attrib2 = new Attribute(AttributeType.DISPLAY_RESOLUTION, AttributeSource.FILE_NAME, "a");
        assertNotEquals(attrib1.hashCode(), attrib2.hashCode());
    }

    @Test
    public void testToString() {
        attrib1 = new Attribute(AttributeType.DISPLAY_RESOLUTION, AttributeSource.ROTTEN_TOMATOES, "blah");
        attrib1.toString();
    }

}
