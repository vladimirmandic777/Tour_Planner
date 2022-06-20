package at.technikum.tour_planner.logger;

public class LoggerFactory {
    public static ILoggerWrapper getLogger(Class <?> clazz) {
        var logger = new Log4J2Wrapper();
        logger.initialize(clazz);
        return logger;
    }
}
