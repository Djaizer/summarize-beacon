import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Home on 19.03.2016.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        UserInteraction interactor = new UserInteraction();
        interactor.printLastOrganizedOutput();

        String input = "test";
        while (!input.isEmpty()) {
            input = reader.readLine();
            System.out.println(input);
            interactor.printDemandOrganizedOutput(input);
        }
    }
}