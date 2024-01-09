package src.clinic;

import java.time.LocalTime;

/**
 * Enum class BusinessHours
 * 
 * @version 1.00
 * @since 2024-01-07
 * @author Team 6
 */

public enum BusinessHours {
    OPEN_TIME_AM("09:00"), CLOSE_TIME_AM("12:00"), OPEN_TIME_PM("13:00"), CLOSE_TIME_PM("17:00");

    private LocalTime time;

    private BusinessHours(String timeToParse) {
        this.time = LocalTime.parse(timeToParse);
    }

    public LocalTime getTime() {
        return this.time;
    }
}
