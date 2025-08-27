package jamal.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTime {

    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma"));
    }

    public static boolean isUpcoming(LocalDateTime startDate) {
        return LocalDateTime.now().isBefore(startDate);
    }

    public static boolean isOverdue(LocalDateTime endDate) {
        return LocalDateTime.now().isAfter(endDate);
    }

    public static boolean isOngoing(LocalDateTime startDate, LocalDateTime endDate) {
        return LocalDateTime.now().isAfter(startDate) && LocalDateTime.now().isBefore(endDate);
    }

    public static boolean isOngoing(LocalDateTime endDate) {
        return LocalDateTime.now().isBefore(endDate);
    }
}
