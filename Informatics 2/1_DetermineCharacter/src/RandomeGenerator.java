import java.util.Random;

public class RandomeGenerator {

    public static void main (String args[]){

        String outputLine;
        for (int row = 1; row <= 40; row++){
            outputLine = "";
            for (int column = 1; column <= 40; column++){
                outputLine = outputLine +  determineCharacter(3,3);
            }
            System.out.println (outputLine);
        }

    }

    public static int determineCharacter (int column, int row) {

        return column;
    }


    public static void randomeExercise() {

        String[] easy = {"cigarParty", "dateFashion H", "squirrelPlay", "caughtSpeeding", "sortaSum", "alarmClock", "love", "in1To10", "specialEleven", "more20", "old35", "less20",
                "nearTen",  "teenSum", "answerCell", "teaParty", "fizzString", "fizzString2", "twoAsOne", "inOrder", "inOrderEqual", "lastDigit", "lessBy10", "withoutDoubles", "maxMod5", "redTicket", "greenTicket", "blueTicket",
                "shareDigit",  "sumLimit" };


        String[] medium = {"makeBricks", "loneSum", "luckySum", "noTeenSum",  "roundSum", "closeFar", "blackjack", "evenlySpaced", "makeChocolate"};

        String[] mediumArray = {"countEvens", "bigDiff", "centeredAverage", "sum13",  "sum67", "has22", "lucky13", "sum28", "more14", "only14", "fizzArray2",  "no14", "isEverywhere", "either24",
                "matchUp", "has77", "has12", "modThree", "haveThree", "twoTwo", "sameEnds", "tripleUp", "fizzArray3", "shiftLeft", "tenRun", "pre4", "post4", "notAlone", "zeroFront", "withoutTen", "zeroMax", "evenOdd", "fizzBuzz"
        };

        Random random = new Random();

        for(int i = 0; i <= 1; i++){
            System.out.println("Exercise: " + mediumArray[random.nextInt(33)]);
        }

    }


    public int sum67(int[] nums) {

        int complete_row = 0;
        boolean follow_row = false;

        for (int i = 0; i < nums.length; i++) {

            if(follow_row) {
                if(nums[i] == 7){
                    follow_row = false;
                }
            } else if(nums[i] == 6){
                follow_row = true;
            } else {
                complete_row = complete_row + nums[i];
            }
        }

        return complete_row;
    }


}
