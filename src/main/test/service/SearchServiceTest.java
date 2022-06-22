package service;

import at.technikum.tour_planner.dal.Dao;
import at.technikum.tour_planner.model.TourFx;
import at.technikum.tour_planner.BAL.SearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Mock
    private Dao<TourFx> tourFxDao;

    private SearchService instance;

    @BeforeEach
    void init(){
        instance = new SearchService(tourFxDao);
    }


    @Test
    void testFindMatchingTours() {
        when(tourFxDao.getAll()).thenReturn(List.of(new TourFx(1,"Aa", "test", "austria", "Vienna","car", 10,"10","ja", null)));

        final var foundItems = instance.findMatchingTours("Aa");

        assertEquals(1,foundItems.size());
    }
}
