import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Hangman {
    public static void main(String[] args) throws FileNotFoundException {

        //Similar to fs in javascript. Parse through a file.
        Scanner scanner = new Scanner(new File("/Users/zhangyiwan/Downloads/words_alpha.txt"));
        //Get user input
        Scanner keyboard = new Scanner(System.in);

        // Create words array and use Scanner to parse through the giant word file.
        List<String> words = new ArrayList<>();
        while(scanner.hasNext()){
            words.add(scanner.nextLine());
        }

        // Create random word from words array
        Random rand = new Random();
        String word = words.get(rand.nextInt(words.size()));
        System.out.println(word);


        List<Character> playerGuesses = new ArrayList<>();

        int wrongCount = 0;
        while(true){

            System.out.println(" -------");
            System.out.println(" |     |");

            if (wrongCount >= 1) {
                System.out.println(" O");
            }
            if (wrongCount >= 2) {
                System.out.print("\\ ");
                if (wrongCount >= 3) {
                    System.out.print("/");
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
                    System.out.print("\\");
                } else {
                    System.out.println("");
                }
            }

            printWordState(word, playerGuesses);
            if (!getPlayerGuess(keyboard, word, playerGuesses)){
                wrongCount++;
            }
            if (printWordState(word, playerGuesses)){
                System.out.println("You won the game!!!");
                break;
            }

            System.out.println("Please enter your guess for the word:");
            if(keyboard.nextLine().equals(word)){
                System.out.println("You won the game!!!");
                break;
            } else {
                System.out.println("Nope! Try again!");
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
