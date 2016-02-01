package cz.voho.jequal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public abstract class AbstractEqualityTest {
    @SuppressWarnings("unchecked")
    protected void assertEqualsThoroughly(final Object a, final Object b, final Equality... equalities) {
        for (final Equality equality : equalities) {
            assertTrue(equality.equals(a, a));
            assertTrue(equality.equals(b, b));
            assertTrue(equality.equals(a, b));
            assertTrue(equality.equals(b, a));
            assertEquals(equality.hashCode(a), equality.hashCode(b));
        }
    }

    @SuppressWarnings("unchecked")
    protected void assertNotEqualsThoroughly(final Object a, final Object b, final Equality... equalities) {
        for (final Equality equality : equalities) {
            assertTrue(equality.equals(a, a));
            assertTrue(equality.equals(b, b));
            assertFalse(equality.equals(a, b));
            assertFalse(equality.equals(b, a));
            // (we know nothing about the hash code)
        }
    }
}
