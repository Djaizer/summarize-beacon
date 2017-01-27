import java.util.*;

import static java.lang.String.format;
import static java.lang.System.out;
import static org.apache.commons.lang3.StringUtils.countMatches;

/**
 * Created by Home on 26.01.2017.
 */
public class NoiseOrganizer {

    private static final long SPAN = 120000;

    private Set<String> set = new HashSet<>();
    private Map<String, Integer> map = new HashMap<>();
    private String outputValue = "";
    private TimeParser timeParser;

    public void printLastOrganizedOutput() {
        refreshLastOutputValue();
        organizeAndPrintNoise();
    }

    public void printDemandOrganizedOutput() {
        if (!timeParser.isDurationCorrect()) {
            out.println("The date '--from' is closer than '--too'. Please type a correct dates or revere existed.");
            return;
        }
        if (timeParser.isTheSameDates()) {
            detOutputOnPreviousDate();
        } else {
            collectOutputsOnRelativeTime();
        }
        organizeAndPrintNoise();
    }

    public void organizeAndPrintNoise() {
        set.addAll(Arrays.asList(outputValue.split("")));
        set.forEach(ch -> map.put(ch, countMatches(outputValue, ch)));
        map.forEach((k, v) -> out.println(format("%s,%s", k, v)));
    }

    public NoiseOrganizer wakeUpTimeParser(String timeDuration) {
        timeParser = new TimeParser(timeDuration);
        return this;
    }

    public static NoiseOrganizer getInstance() {
        return new NoiseOrganizer();
    }

    public void setOutputValue(String outputValue) {
        this.outputValue = outputValue;
    }

    public Map<String, Integer> getMap() {
        return map;
    }

    private String refreshLastOutputValue() {
        return outputValue = XMLWorker.getLastOutputValue();
    }

    private void detOutputOnPreviousDate() {
        outputValue = XMLWorker.getPreviousOutputValues(timeParser.getFromDate());
    }

    private void collectOutputsOnRelativeTime() {
        for (long date = timeParser.getFromDate() + 60000; date <= timeParser.getToDate(); date += SPAN) {
            outputValue += XMLWorker.getPreviousAndDemandedOutputValues(date);
        }
    }
}