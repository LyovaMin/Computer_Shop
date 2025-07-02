package by.lyofchik.AppSpring.Service.EntityInterfaces;

import java.util.Optional;

public interface FindEntity<T> {
    public Optional<T> find(String name);
}
