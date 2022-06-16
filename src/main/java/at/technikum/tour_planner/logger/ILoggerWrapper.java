package at.technikum.tour_planner.logger;

public interface ILoggerWrapper {
    void debug(String message);
    void fatal(String message);
    void error(String message);
    void warn(String message);
    void info(String message);

}
