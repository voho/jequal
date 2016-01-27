package jequal;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class EqualityTest {
    @Test
    public void testNotEqualToNull() {
        Person p1 = new Person();
        p1.setFirstName("John");
        p1.setLastName("Doe");

        Object p2 = null;

        assertNotEquals(p1, p2);
        assertNotEquals(p2, p1);
    }

    @Test
    public void testNotEqualToDifferentTypes() {
        Person p1 = new Person();
        p1.setFirstName("John");
        p1.setLastName("Doe");

        String p2 = "Hello!";

        assertNotEquals(p1, p2);
        assertNotEquals(p2, p1);
    }

    @Test
    public void testEquality() {
        Person p1 = new Person();
        p1.setFirstName("John");
        p1.setLastName("Doe");

        Person p2 = new Person();
        p2.setFirstName("John");
        p2.setLastName("Doe");

        assertEquals(p1, p2);
        assertEquals(p2, p1);
        assertEquals(p1, p1);
        assertEquals(p2, p2);
    }
}
