package dao.interfaces;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<T> {
        void save(T e);
        Optional<T> findById(Long id);
        List<T> findAll();
        void update(T e);
        void delete(T e);
}
