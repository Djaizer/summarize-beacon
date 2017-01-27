import org.junit.Test;

import static java.lang.String.format;
import static org.junit.Assert.*;

/**
 * Created by Home on 26.01.2017.
 */
public class NoiseOrganizerTests {

    private static final String OUTPUT_VALUE = "01F04FAA";
    private static final String EXPECTED_RESULT = "0,2\n1,1\nA,2\n4,1\nF,2\n";

    @Test
    public void noiseOrganizerTest() {
        final String[] ACTUAL_RESULT = {""};
        NoiseOrganizer noiseOrganizer = new NoiseOrganizer();
        noiseOrganizer.setOutputValue(OUTPUT_VALUE);
        noiseOrganizer.organizeAndPrintNoise();
        noiseOrganizer.getMap().forEach((k, v) -> ACTUAL_RESULT[0] += format("%s,%s\n", k, v));

        assertEquals(EXPECTED_RESULT, ACTUAL_RESULT[0]);
    }
}