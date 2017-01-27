import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Home on 27.01.2017.
 */
public class TimeParserTests {

    private static final String EXPRESSION_WITH_CORRECT_PARAMETERS =
            "summarize-beacon --from \"4 months 2 days 1 hour ago\" --to \"1 month 1 hour ago\"";
    private static final String EXPRESSION_WITH_SIMILAR_PARAMETERS =
            "summarize-beacon --from \"1 hour 3 months 1 day 1 hour ago\" --to \"1 hour 3 months 1 day 1 hour ago\"";
    private static final String EXPRESSION_WITH_REVERSED_PARAMETERS =
            "summarize-beacon --from \"1 month 1 hour ago\" --to \"4 months 2 days 1 hour ago\"";
    private static final String EXPRESSION_WITH_INCORRECT_DATA_TYPE =
            "summarize-beacon bla bla bla\"";

    @Test
    public void timeParserWithCorrectDatesTest() {
        TimeParser timeParser = new TimeParser(EXPRESSION_WITH_CORRECT_PARAMETERS);
        assertTrue(timeParser.isDurationCorrect());
    }

    @Test
    public void timeParserWithSimilarDatesTest() {
        TimeParser timeParser = new TimeParser(EXPRESSION_WITH_SIMILAR_PARAMETERS);
        assertTrue(timeParser.isTheSameDates());
    }

    @Test
    public void timeParserWithReversedDatesTest() {
        TimeParser timeParser = new TimeParser(EXPRESSION_WITH_REVERSED_PARAMETERS);
        assertFalse(timeParser.isDurationCorrect());
    }

    @Test(expected = StringIndexOutOfBoundsException.class)
    public void timeParserWithIncorrectDataTypeTest() {
        new TimeParser(EXPRESSION_WITH_INCORRECT_DATA_TYPE);
    }
}