package at.technikum.tour_planner.logger;

import at.technikum.tour_planner.TourApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.Loader;

public class Log4J2Wrapper implements ILoggerWrapper {
    private Logger logger;
    private LoggerStateBase state = new UninitializedState();

    @Override
    public void debug(String message) {
        this.state.debug(message);
    }
    @Override
    public void fatal(String message) {
        this.state.fatal(message);
    }
    @Override
    public void error(String message) {
        this.state.error(message);
    }
    @Override
    public void warn(String message) {
        this.state.warn(message);
    }
    @Override
    public void info(String message) {
        this.state.info(message);
    }

    public void initialize() {
        System.out.println("RES:" + TourApplication.class.getResource("log4j2.xml"));
        this.state = new InitializedState(LogManager.getLogger(this.getClass().getName()));
    }
}
