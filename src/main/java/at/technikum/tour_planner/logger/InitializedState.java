package at.technikum.tour_planner.logger;

import org.apache.logging.log4j.Logger;

public class InitializedState extends LoggerStateBase {

    private final Logger logger;

    public InitializedState(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void debug(String message) {
        this.logger.debug(message);
    }

    @Override
    public void fatal(String message) {
        this.logger.fatal(message);
    }

    @Override
    public void error(String message) {
        this.logger.error(message);
    }

    @Override
    public void warn(String message) {
        this.logger.warn(message);
    }
   @Override
    public void info(String message) {
        this.logger.info(message);
    }
}
