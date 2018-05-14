package com.kolotree.common;

import java.util.List;
import java.util.Optional;

public abstract class ValueObject<T extends ValueObject> {

    public abstract List<Object> GetEqualityComponents();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }

        if (o instanceof ValueObject<?>) {

            ValueObject<?> other = (ValueObject<?>) o;

            if (GetEqualityComponents().size() != other.GetEqualityComponents().size()) {
                return false;
            }

            for (int i = 0; i < GetEqualityComponents().size(); i++) {
                Object o1 = GetEqualityComponents().get(i);
                Object o2 = other.GetEqualityComponents().get(i);

                if (!o1.equals(o2)) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return GetEqualityComponents().stream()
                .map(Object::hashCode)
                .reduce(1, (current, obj) ->
                        current * 23 + Optional.of(obj).map(Object::hashCode).orElse(0));
    }
}
