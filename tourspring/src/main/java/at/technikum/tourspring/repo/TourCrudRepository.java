package at.technikum.tourspring.repo;

import at.technikum.tourspring.model.Tour;

import at.technikum.tourspring.model.TourLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface TourCrudRepository extends JpaRepository<Tour, Integer> {



    //TODO werden wrti nicht machen

    @Query("select " +
            "new at.technikum.tourspring.model.TourLog" +
            "(t.date,t.comment, avg(t.difficulty) , t.time, t.raiting) from log as t inner join tour as tt on t.id = tt.id)")
    List<TourLog> getTourLogs();


}
