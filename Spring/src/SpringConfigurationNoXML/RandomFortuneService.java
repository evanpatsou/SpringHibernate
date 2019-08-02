package SpringConfigurationNoXML;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomFortuneService implements FortuneService {

    // create an array of strings
    private String[] data = {
        "Beware of the worlf in sheeps cloathing",
        "Diligence is the mother of good luck",
        "The journey is the reward"
    };

    // create a random number generator
    private Random myRandom = new Random();

    @Override
    public String getFortune() {
        // pick a random string from this array.
        int index = myRandom.nextInt(data.length);

        return data[index];
    }
}
