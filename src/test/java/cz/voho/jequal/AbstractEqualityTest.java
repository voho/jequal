package cz.voho.jequal;

import org.junit.Ignore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Ignore
public abstract class AbstractEqualityTest {
    @SuppressWarnings("unchecked")
    protected void assertEqualsThoroughly(final Object a, final Object b, final Equality... equalities) {
        for (final Equality equality : equalities) {
            assertEqualsIsReflective(a, b, equality);
            assertTrue(equality.equals(a, b));
            assertTrue(equality.equals(b, a));
            assertEquals(equality.hashCode(a), equality.hashCode(b));

            // NULLs
            assertNotEqualToNullIfNotNull(a, equality);
            assertNotEqualToNullIfNotNull(b, equality);
            assertTrue(equality.equals(null, null));
        }
    }

    @SuppressWarnings("unchecked")
    protected void assertNotEqualsThoroughly(final Object a, final Object b, final Equality... equalities) {
        for (final Equality equality : equalities) {
            assertEqualsIsReflective(a, b, equality);
            assertFalse(equality.equals(a, b));
            assertFalse(equality.equals(b, a));
            // (we know nothing about the hash code)

            // NULLs
            assertNotEqualToNullIfNotNull(a, equality);
            assertNotEqualToNullIfNotNull(b, equality);
            assertTrue(equality.equals(null, null));
        }
    }

    private void assertEqualsIsReflective(Object a, Object b, Equality equality) {
        assertTrue(equality.equals(a, a));
        assertTrue(equality.equals(b, b));
    }

    private void assertNotEqualToNullIfNotNull(Object a, Equality equality) {
        if (a != null) {
            assertFalse(equality.equals(null, a));
            assertFalse(equality.equals(a, null));
        }
    }
}
