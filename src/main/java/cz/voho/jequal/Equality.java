package cz.voho.jequal;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class Equality<T> {
    private final List<Function<T, Object>> valueExtractors;

    @SafeVarargs
    public Equality(final Function<T, Object>... valueExtractors) {
        this.valueExtractors = Arrays.asList(valueExtractors);
    }

    public boolean equals(final T first, final Object second) {
        Objects.requireNonNull(first);

        if (second == null) {
            return false;
        }
        if (first == second) {
            return true;
        }
        try {
            final T secondTyped = (T) second;

            for (final Function<T, Object> valueExtractor : valueExtractors) {
                final Object firstValue = valueExtractor.apply(first);
                final Object secondValue = valueExtractor.apply(secondTyped);

                if (!Objects.equals(firstValue, secondValue)) {
                    return false;
                }
            }

            return true;
        } catch (final ClassCastException e) {
            return false;
        }
    }

    public int hashCode(final T first) {
        Objects.requireNonNull(first);

        int result = 1;

        for (final Function<T, Object> valueExtractor : valueExtractors) {
            final Object firstValue = valueExtractor.apply(first);
            result = 31 * result + Objects.hashCode(firstValue);
        }

        return result;
    }
}
