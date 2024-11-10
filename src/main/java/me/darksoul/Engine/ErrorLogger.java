package me.darksoul.Engine;

public class ErrorLogger {

    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String BOLD = "\u001B[1m";

    public enum LogLevel {
        ERROR(RED + BOLD + "[ERROR]" + RESET),
        WARNING(YELLOW + "[WARNING]" + RESET),
        INFO(BLUE + "[INFO]" + RESET),
        DEBUG("[DEBUG]");

        private final String label;

        LogLevel(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    public static void log(LogLevel level, String module, String message) {
        System.out.println(level.getLabel() + " [" + module + "] " + message);
    }

    public static void error(String module, String message) {
        log(LogLevel.ERROR, module, message);
    }

    public static void warning(String module, String message) {
        log(LogLevel.WARNING, module, message);
    }

    public static void info(String module, String message) {
        log(LogLevel.INFO, module, message);
    }

    public static void debug(String module, String message) {
        log(LogLevel.DEBUG, module, message);
    }

    public static class LogMessageBuilder {
        private final LogLevel level;
        private final StringBuilder message;
        private String module = "General";

        public LogMessageBuilder(LogLevel level) {
            this.level = level;
            this.message = new StringBuilder();
        }

        public LogMessageBuilder setModule(String module) {
            this.module = module;
            return this;
        }

        public LogMessageBuilder addMessage(String msgPart) {
            this.message.append(msgPart);
            return this;
        }

        public LogMessageBuilder addDetail(String label, String detail) {
            this.message.append("[").append(label).append(": ").append(detail).append("] ");
            return this;
        }

        public void log() {
            ErrorLogger.log(level, module, message.toString().trim());
        }
    }

    public static LogMessageBuilder createLog(LogLevel level) {
        return new LogMessageBuilder(level);
    }
}
