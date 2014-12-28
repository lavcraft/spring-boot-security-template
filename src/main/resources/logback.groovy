import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender

import static ch.qos.logback.classic.Level.DEBUG
import static ch.qos.logback.classic.Level.INFO

appender("CONSOLE", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%d [ %t ] %-55logger{13} | %m %n"
    }
}

def byDay = timestamp("yyyyMMdd")

logger("org.springframework.security", DEBUG)
logger("app.security", DEBUG)

root(INFO, ["CONSOLE"])