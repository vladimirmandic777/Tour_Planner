package at.technikum.tour_planner.dal;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> get(int id);

    List<T> getAll();

    T create();

    void update(T t, List<?> params);

    void update(T t) throws URISyntaxException;

    void delete(T t);
}
