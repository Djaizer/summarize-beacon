import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Home on 26.01.2017.
 */
public class TimeParser {

    private static final Pattern PATTERN = Pattern.compile(".*?(\\d+)\\s+(.*?)s?\\s.*?");
    private static final Map<String, Integer> FIELDS = new HashMap<String, Integer>() {{
        put("second", Calendar.SECOND);
        put("minute", Calendar.MINUTE);
        put("hour", Calendar.HOUR);
        put("day", Calendar.DATE);
        put("week", Calendar.WEEK_OF_YEAR);
        put("month", Calendar.MONTH);
        put("year", Calendar.YEAR);
    }};

    private long fromDate;
    private long toDate;

    public TimeParser(String durationExpression) {
        parseDurationExpression(durationExpression);
    }

    public boolean isDurationCorrect() {
        return (toDate - fromDate) >= 0;
    }

    public boolean isTheSameDates() {
        return (toDate - fromDate) < 100 && isDurationCorrect();
    }

    public long getFromDate() {
        return fromDate;
    }

    public long getToDate() {
        return toDate;
    }

    private void parseDurationExpression(String durationExp) {
        String from = durationExp.substring(durationExp.indexOf("--from"), durationExp.indexOf("--to"));
        String to = durationExp.substring(durationExp.indexOf("--to"));

        fromDate = calculateDate(from);
        toDate = calculateDate(to);
    }

    private long calculateDate(String dateExp) {
        Matcher m = PATTERN.matcher(dateExp);
        Calendar cal = Calendar.getInstance();
        while (m.find()) {
            {
                int amount = Integer.parseInt(m.group(1));
                String unit = m.group(2);
                cal.add(FIELDS.get(unit), -amount);
            }
        }
        return cal.getTimeInMillis();
    }
}