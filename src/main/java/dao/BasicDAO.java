package dao;

import java.util.List;

public interface BasicDAO<T, I> {
    void create(T t);

    T read(I id);

    List<T> readAll();

    void update(T t);

    void delete(I id);
}
