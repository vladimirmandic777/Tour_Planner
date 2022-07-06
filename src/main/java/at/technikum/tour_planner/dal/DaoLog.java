package at.technikum.tour_planner.dal;

import java.net.URISyntaxException;
import java.util.List;

public interface DaoLog<T> {

    List<T> getAll();

    List<T> getLog(int id);

    T create();

    void update(T t, List<?> params);

    void update(T t) throws URISyntaxException;

    void delete(T t);
}
