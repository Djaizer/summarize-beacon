import static java.lang.System.out;

/**
 * Created by Home on 26.01.2017.
 */
public class UserInteraction {

    public void printLastOrganizedOutput() {
        NoiseOrganizer.getInstance()
                .printLastOrganizedOutput();
    }

    public void printDemandOrganizedOutput(String timeDuration) {
        try {
            NoiseOrganizer.getInstance()
                    .wakeUpTimeParser(timeDuration)
                    .printDemandOrganizedOutput();
        } catch (StringIndexOutOfBoundsException e) {
            out.println("Sorry, bur your request is not understood. Please, try again. " +
                    "Example: 'summarize-beacon --from \"20 minutes ago\" --to \"9 minutes ago\'");
        }
    }
}