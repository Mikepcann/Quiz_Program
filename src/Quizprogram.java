/**
 * Quizprogram - Write a description here.
 * 
 * @author Michael Cann 000872135
 * @version March 28/2021
 */
// new comment in the test branch
// new test comment
import java.io.File;
import java.util.Scanner;
public class Quizprogram {
    public static void main(String[] args) throws Exception{
        // Write your Code here!!!
        Scanner in = new Scanner(System.in);
        //Variables
        int numQuestions;
        int correntAns = 0;
        double score;			
        String questionTopic, questionLine, optionLine, questionType;

        //Start of game
        System.out.println("* * * * * Welcome to Quizzer! * * * * *\n");
        System.out.print("Please enter your quiz question file path: ");
        String filePath = in.next();
        //file location for ease of use c:/temp/JavaQuestions.txt
        Scanner fileReader = new Scanner(new File(filePath));

        //set up number of questions and the topic
        numQuestions = Integer.parseInt(fileReader.next());
        questionTopic = fileReader.nextLine();
        System.out.printf("%nYou will have %d questions on the topic of %s.%nPress <Enter> when you are ready"
            + " to start...%n%n", numQuestions, questionTopic);
        in.nextLine();
        String enterKey = in.nextLine();//feature added to trigger start with enter key
        
        //loop through doc for the correct number of questions
        for(int i = 1; i <= numQuestions; i++) {
            questionType =fileReader.next();
            questionLine = fileReader.nextLine();
            optionLine = fileReader.nextLine();

            if(questionType.equalsIgnoreCase("mc")) {
                correntAns += doMCQuestion(i,questionLine,optionLine, in);//pass # of the question, the question and the list of possible answers
            } else {
                correntAns += doFBQuestion(i, questionLine, optionLine, in);//pass # of the question and the fill in the blank answer
            }			
        }						
        score = ((double)correntAns/numQuestions) * 100.0; //calculate score
        //Let the user know the game is complete and show them there score. 

        System.out.println("Quiz Complete.");
        System.out.printf("Your score was %d out of %d or %.1f%%.%n",correntAns, numQuestions, score );
        System.out.println("Good-bye!");

        fileReader.close();			
    }
    //Method to process the Multiple choice questions
    public static int doMCQuestion(int qNum, String question, String answers, Scanner sc) {
 
        int correctAns=0;
        //multiple choice question method
        System.out.printf("Question #%d. Multiple Choice%n%s%n",qNum,question.trim());

        //process answers
        String[] answerArray = answers.split(",");
        for(int i =0;i <answerArray.length; i++) {

            //check for right answer and assign it to correct answer
            if(answerArray[i].startsWith("*")) {
                correctAns = (i+1);
                //add line to remove the * from the array and trim
                answerArray[i] = answerArray[i].substring(1);
            }	
          //display answers
            System.out.printf("%4d) %s%n",(i+1),answerArray[i]);
        }		

        
        //Get answer from user
        System.out.print("Enter your response (1-4): ");
        int userAns = sc.nextInt();

        if(userAns == correctAns) {
            System.out.println("That's correct!");
            System.out.println("----------------------------------------------------------------------\n"); //line at end of Q
            return 1;
        } else {
            System.out.println("Sorry, that's incorrect.");
            System.out.println("----------------------------------------------------------------------\n"); //line at end of Q
            return 0;
        }
    }
    //Method to process the Fill in the Blank Questions
    public static int doFBQuestion(int qNum, String question, String answers, Scanner sc) {
        //Fill in the blank question method
        System.out.printf("Question #%d. Fill in the Blank%n%s%n",qNum,question.trim());		

        //Get answer from user
        System.out.print("Enter the missing word: ");
        String userAns = sc.next();

        //return result to main method to keep score		
        if(userAns.equals(answers)) {
            System.out.println("That's correct!");
            System.out.println("----------------------------------------------------------------------\n"); //line at end of Q
            return 1;
        } else {
            System.out.println("Sorry, that's incorrect.");
            System.out.println("----------------------------------------------------------------------\n"); //line at end of Q
            return 0;
        }				
    }
}