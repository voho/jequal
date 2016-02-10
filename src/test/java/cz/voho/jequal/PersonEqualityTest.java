package cz.voho.jequal;

import cz.voho.jequal.beans.Employee;
import cz.voho.jequal.beans.Person;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PersonEqualityTest extends AbstractEqualityTest {
    private static final Equality<Person> EQUALITY_ON_NAME_WITH_SUB_TYPES = Equality
            .onType(Person.class)
            .orAnySubType()
            .checkEquality(Person::getFirstName)
            .checkEquality(Person::getLastName)
            .define();

    private static final Equality<Person> EQUALITY_ON_NAME_WITHOUT_SUB_TYPES = Equality
            .onType(Person.class)
            .checkEquality(Person::getFirstName)
            .checkEquality(Person::getLastName)
            .define();

    @Test
    public void testNullHashCode() {
        assertEquals(0, EQUALITY_ON_NAME_WITH_SUB_TYPES.hashCode(null));
        assertEquals(0, EQUALITY_ON_NAME_WITHOUT_SUB_TYPES.hashCode(null));
    }

    @Test
    public void testNotEqualToNull() {
        Person p1 = new Person();
        p1.setFirstName("John");
        p1.setLastName("Doe");

        Object p2 = null;

        assertNotEqualsThoroughly(p1, p2, EQUALITY_ON_NAME_WITHOUT_SUB_TYPES, EQUALITY_ON_NAME_WITH_SUB_TYPES);
    }

    @Test
    public void testNotEqualToDifferentTypes() {
        Person p1 = new Person();
        p1.setFirstName("John");
        p1.setLastName("Doe");

        String p2 = "Hello!";

        assertNotEqualsThoroughly(p1, p2, EQUALITY_ON_NAME_WITHOUT_SUB_TYPES, EQUALITY_ON_NAME_WITH_SUB_TYPES);
    }

    @Test
    public void testEqualSameType() {
        Person p1 = new Person();
        p1.setFirstName("John");
        p1.setLastName("Doe");

        Person p2 = new Person();
        p2.setFirstName("John");
        p2.setLastName("Doe");

        assertEqualsThoroughly(p1, p2, EQUALITY_ON_NAME_WITHOUT_SUB_TYPES, EQUALITY_ON_NAME_WITH_SUB_TYPES);
    }

    @Test
    public void testEqualSubType() {
        Person p1 = new Person();
        p1.setFirstName("John");
        p1.setLastName("Doe");

        Employee p2 = new Employee();
        p2.setFirstName("John");
        p2.setLastName("Doe");
        p2.setRole("Programmer");

        assertEqualsThoroughly(p1, p2, EQUALITY_ON_NAME_WITH_SUB_TYPES);
        assertNotEqualsThoroughly(p1, p2, EQUALITY_ON_NAME_WITHOUT_SUB_TYPES);
    }

    @Test
    public void testNonEqualsSameType() {
        Person p1 = new Person();
        p1.setFirstName("John");
        p1.setLastName("Doe");

        Person p2 = new Person();
        p2.setFirstName("John");
        p2.setLastName("XXX");

        assertNotEqualsThoroughly(p1, p2, EQUALITY_ON_NAME_WITHOUT_SUB_TYPES, EQUALITY_ON_NAME_WITH_SUB_TYPES);
    }

    @Test
    public void testNonEqualsSubType() {
        Person p1 = new Person();
        p1.setFirstName("John");
        p1.setLastName("Doe");

        Employee p2 = new Employee();
        p2.setFirstName("John");
        p2.setLastName("XXX");
        p2.setRole("Programmer");

        assertNotEqualsThoroughly(p1, p2, EQUALITY_ON_NAME_WITHOUT_SUB_TYPES, EQUALITY_ON_NAME_WITH_SUB_TYPES);
    }
}
