package main.calculator.sets;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * The main part of the calculator doing the calculations.
 * 
 * @author  lcarmohn, xauber
 * @version 0.1
 */
public class CalcEngineSets {

    /**
     * Declaration
     */
    Set<String> setA = new HashSet<>();
    Set<String> setB = new HashSet<>();
    Set<String> setResult = new HashSet<>();

    /**
     * Constructor
     */
    public CalcEngineSets(){
        super();
    }

    /**
     * Getter and Setter
     */
    public void setSetA(Set<String> setA) {
        this.setA = setA;
    }

    public Set<String> getSetA() {
        return setA;
    }

    public void setSetB(Set<String> setB) {
        this.setB = setB;
    }

    public Set<String> getSetB() {
        return setB;
    }

    public void setSetResult(Set<String> setResult) {
        this.setResult = setResult;
    }

    public Set<String> getSetResult() {
        return setResult;
    }

    /**
     * Parse given String to set
     * @param input as string
     * @return result as Set
     */
    public Set<String> parseStringToSet(String input){
        String[] inputArray = input.split(Pattern.quote(","));
        Set<String> result = new HashSet<>();

        for(String element : inputArray) {
            result.add(element);
        }
        return result;
    }

    /**
     * Clear all input fields
     */
    public void clear() {
        setA.clear();
        setB.clear();
        setResult.clear();
    }

    /**
     * Add set a and b to result
     */
    public void union() {

        setResult.clear();

        setResult.addAll(setA);
        setResult.addAll(setB);
    }

    /**
     * Stores the intersection of two sets to result
     */
    public void intersection() {
        for (String element : setA){
            if (setB.contains(element)){
                setResult.add(element);
            }
        }
    }

    /**
     * Stores the Subtracts of two sets to result
     */
    public void subtraction() {

        setResult.clear();
        setResult.addAll(setA);

        for (String element : setB){
            setResult.remove(element);
        }

    }

    /**
     * Push set a to set b
     */
    public void pushSetAtoB() {
        setB.addAll(setA);
    }

    /**
     * Push set b to set a
     */
    public void pushSetBtoA() {
        setA.addAll(setB);
    }

    /**
     * Set length of a to result
     */
    public void lengthA() {

        setResult.clear();
        setResult.add(String.valueOf(setA.size()));

    }

    /**
     * Set length of b to result
     */
    public void lengthB() {

        setResult.clear();
        setResult.add(String.valueOf(setB.size()));

    }

    /**
     * @return The title of this calculation engine.
     */
    public String getTitle(){
        return "Java main.Calculator";
    }

    /**
     * @return The author of this engine.
     */
    public String getAuthor(){
        return "David J. Barnes and Michael Kolling";
    }

}
