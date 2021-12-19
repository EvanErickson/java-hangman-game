import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Hangman {
    public static void main(String[] args) throws FileNotFoundException {

        //Find number of players
        Scanner keyboard = new Scanner(System.in);
        System.out.println("1 or 2 players?");
        String players = keyboard.nextLine();


        // We need to decide on the word for the hangman game.
        // If you only have 1 player, we will randomly generate a word for you.
        String word;
        if (players.equals("1")){
            //Similar to fs in javascript. Parse through a file.
            Scanner scanner = new Scanner(new File("/Users/zhangyiwan/Downloads/words_alpha.txt"));

            // Create words array and use Scanner to parse through the giant word file.
            List<String> words = new ArrayList<>();
            while(scanner.hasNext()){
                words.add(scanner.nextLine());
            }

            // Create random word from words array
            Random rand = new Random();
            word = words.get(rand.nextInt(words.size()));
        } else {
            System.out.println("Player 1, please enter your word: ");
            word = keyboard.nextLine();
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("Ready for player 2. Good luck!");
        }





        List<Character> playerGuesses = new ArrayList<>();

        int wrongCount = 0;
        while(true){

            printHangedMan(wrongCount);

            if(wrongCount >= 6){
                finalMethod(keyboard, word);
                break;
            }
            printWordState(word, playerGuesses);
            if (!getPlayerGuess(keyboard, word, playerGuesses)){
                wrongCount++;
            }

            if (printWordState(word, playerGuesses)){
                System.out.println("You won the game!!!");
                break;
            }


        }
    }

    private static void finalMethod(Scanner keyboard, String word) {
        System.out.println("Please enter your guess for the word:");
        if(keyboard.nextLine().equals(word)){
            System.out.println("You won the game!!!");
            return;
        } else {
            System.out.println("You lose!");
            System.out.println("The word was: " + word);
            return;
        }
    }

    private static void printHangedMan(int wrongCount) {
        System.out.println(" -------");
        System.out.println(" |     |");

        if (wrongCount >= 1) {
            System.out.println(" O");
        }
        if (wrongCount >= 2) {
            System.out.print("\\ ");
            if (wrongCount >= 3) {
                System.out.print("/");
                System.out.println("");
            } else {
                System.out.println("");
            }
        }
        if (wrongCount >= 4) {
            System.out.println(" |");
        }

        if (wrongCount >= 5) {
            System.out.print("/ ");
            if (wrongCount >= 6) {
                System.out.println("\\");
            } else {
                System.out.println("");
            }
        }
    }


    private static boolean getPlayerGuess(Scanner keyboard, String word, List<Character> playerGuesses) {
        System.out.println("Please enter a letter:");
        //Gets the next string input from the user
        String letterGuess = keyboard.nextLine();
        //Add the player guess char to the playerGuesses array
        playerGuesses.add(letterGuess.charAt(0));
        return word.contains(letterGuess);
    }

    private static boolean printWordState(String word, List<Character> playerGuesses) {
        int correctCount = 0;
        for (int i = 0; i < word.length(); i++){
            if (playerGuesses.contains(word.charAt(i))){
               System.out.print(word.charAt(i));
               correctCount++;
            } else {
                System.out.print("-");
            }
        }
        System.out.println();
        return (word.length() == correctCount);
    }



    
}
