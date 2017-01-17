package cz.voho.jequal;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public interface Equality<T> {
    static <T> Equality.Builder<T> onType(final Class<T> type) {
        return new Builder<T>(type);
    }

    boolean equals(final Object firstObject, final Object secondObject);

    int hashCode(final T object);

    final class Builder<T> {
        private final Class<T> type;
        private final List<Function<T, ?>> valueExtractors;
        private boolean allowSubTypes;

        private Builder(final Class<T> type) {
            this.type = type;
            valueExtractors = new LinkedList<>();
            allowSubTypes = false;
        }

        public Builder<T> allowingSubType() {
            allowSubTypes = true;
            return this;
        }

        public Builder<T> checkingEqualityOf(final Function<T, ?> valueExtractor) {
            valueExtractors.add(valueExtractor);
            return this;
        }

        public Equality<T> define() {
            return new Equality<T>() {
                public boolean equals(final Object firstObject, final Object secondObject) {
                    if (firstObject == secondObject) {
                        // both are the same instance
                        return true;
                    }
                    if (firstObject == null && secondObject == null) {
                        // both are NULLs
                        return true;
                    }
                    if (firstObject == null || secondObject == null) {
                        // one is NULL and the second one is not
                        return false;
                    }
                    if (allowSubTypes) {
                        if (!type.isInstance(firstObject) || !type.isInstance(secondObject)) {
                            // at least one object is not an instance of the desired type (or subtype)
                            return false;
                        }
                    } else {
                        if (!type.equals(firstObject.getClass()) || !type.equals(secondObject.getClass())) {
                            // at least one object is not an instance of the desired type
                            return false;
                        }
                    }

                    // now it is safe to do that
                    @SuppressWarnings("unchecked")
                    final T firstObjectTyped = (T) firstObject;
                    @SuppressWarnings("unchecked")
                    final T secondObjectTyped = (T) secondObject;

                    for (final Function<T, ?> valueExtractor : valueExtractors) {
                        final Object firstValue = valueExtractor.apply(firstObjectTyped);
                        final Object secondValue = valueExtractor.apply(secondObjectTyped);

                        if (!Objects.equals(firstValue, secondValue)) {
                            // this pair does not equal
                            return false;
                        }
                    }

                    // all pairs equal
                    return true;
                }

                public int hashCode(final T object) {
                    if (object == null) {
                        return 0;
                    }

                    int result = 1;

                    for (final Function<T, ?> valueExtractor : valueExtractors) {
                        final Object value = valueExtractor.apply(object);
                        result = 31 * result + Objects.hashCode(value);
                    }

                    return result;
                }
            };
        }
    }
}
