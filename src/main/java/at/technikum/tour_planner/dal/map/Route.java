package at.technikum.tour_planner.dal.map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Route {
    private RouteObject route;
    private Object info;
    public RouteObject getRoute() {
        return route;
    }
    public Object getInfo() {
        return info;
    }


}
