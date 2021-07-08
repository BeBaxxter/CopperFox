import java.util.HashMap;

/**
 * The responder class represents a response generator object.
 * It is used to generate an automatic response.
 *
 * @author     Michael Kolling and David J. Barnes
 * @author     Robin Wettstädt and Lucas Carmohn
 * @version    0.2
 */
public class Responder {

    int lastUsedRandomNumber;
    HashMap<String,String> responses = new HashMap<>();
    HashMap<String,String> secondClassResponses = new HashMap<>();

    /**
     * Construct a Responder and call the fill methods.
     */
    public Responder() {
        fillResponses();
        fillSecondClassResponses();
    }

    /**
     * fills the HashMap responses with default values.
     */
    public void fillResponses () {
        responses.put("problem", "That sounds interesting. Tell me more...");
        responses.put("working", "Have you tried turning it off and on again?");
        responses.put("tried", "Did you really try your hardest before asking me?");
        responses.put("search", "Let me Google that really quick...");
        responses.put("idea", "Have you ever heard of StackOverflow?");
    }

    /**
     * fills the HashMap secondClassResponses with default values.
     */
    public void fillSecondClassResponses () {
        responses.put("why", "You wouldn't get it...");
        responses.put("how", "This again? I need a raise...");
        responses.put("who", "This is non of my business.");
        responses.put("where", "The hell would i know where?");
        responses.put("find", "I searched desperately as well, but nothing in sight...");
    }

    /**
     * generate a response correlating to the input based on the HashMap
     * @param the user input as a String
     * @return a response picked from the HashMap or the default
     */
    public String generateActualResponse(String input) {
        
        // first level response finding
        for (String key : responses.keySet()) {
            if(input.contains(key)) {
                return responses.get(key);
            }
        }

        // second level response finding
        for (String key : secondClassResponses.keySet()) {
            if(input.contains(key)) {
                return secondClassResponses.get(key);
            }
        }

        // default response
        return generateRandomResponse();
    }


    /**
     * Generate a random response.
     * @return A string that should be displayed as the random response
     */
    public String generateRandomResponse() {
        int min = 1;
        int max = 5;
        int randomNumber = (int) Math.floor(Math.random() * (max - min + 1) + min);

        while (lastUsedRandomNumber == randomNumber) {
            randomNumber = (int) Math.floor(Math.random() * (max - min + 1) + min);
        }

        lastUsedRandomNumber = randomNumber;
        switch (randomNumber) {
            case 1: return "Is your System plugged in?";
            case 2: return "Oh that is interesting!";
            case 3: return "What a shame...";
            case 4: return "I have never heard about that before.";
            case 5: return "Did you know that the sun is 147 million kilometers away?";
            default: return "I don't know that either...";
        }
    }
}
