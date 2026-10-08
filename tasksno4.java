import java.util.Scanner;
import java.util.concurrent.*;

class Question {

    String question;
    String[] options;
    int correctAnswer;

    Question(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}

public class tasksno4 {

    
    static final int TIME_LIMIT = 10;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Question[] questions = {

            new Question(
                "Which language is used for Android development?",
                new String[]{"1. Java", "2. HTML", "3. CSS", "4. SQL"},
                1
            ),

            new Question(
                "Which keyword is used to create a class in Java?",
                new String[]{"1. function", "2. class", "3. create", "4. object"},
                2
            ),

            new Question(
                "Which method is the starting point of a Java program?",
                new String[]{"1. start()", "2. run()", "3. main()", "4. execute()"},
                3
            ),

            new Question(
                "Which data type is used for decimal values?",
                new String[]{"1. int", "2. double", "3. char", "4. boolean"},
                2
            ),

            new Question(
                "Which symbol is used to end a statement in Java?",
                new String[]{"1. :", "2. .", "3. ;", "4. ,"},
                3
            )
        };

        int score = 0;

        System.out.println("================================");
        System.out.println("       JAVA QUIZ APPLICATION");
        System.out.println("================================");
        System.out.println("You have " + TIME_LIMIT + " seconds for each question.");
        System.out.println();

        for (int i = 0; i < questions.length; i++) {

            Question q = questions[i];

            System.out.println("\nQuestion " + (i + 1) + ":");
            System.out.println(q.question);

            for (String option : q.options) {
                System.out.println(option);
            }

            System.out.println("You have " + TIME_LIMIT + " seconds.");

            ExecutorService executor = Executors.newSingleThreadExecutor();

            Future<String> future = executor.submit(() -> {
                System.out.print("Enter your answer (1-4): ");
                return sc.nextLine();
            });

            try {

                String answer = future.get(TIME_LIMIT, TimeUnit.SECONDS);

                int selectedAnswer = Integer.parseInt(answer);

                if (selectedAnswer == q.correctAnswer) {
                    System.out.println("Correct!");
                    score++;
                } else {
                    System.out.println("Wrong answer.");
                    System.out.println("Correct answer: " + q.correctAnswer);
                }

            } catch (TimeoutException e) {

                System.out.println("\nTime's up!");
                System.out.println("Correct answer: " + q.correctAnswer);

                future.cancel(true);

            } catch (NumberFormatException e) {

                System.out.println("Please enter a number between 1 and 4.");

            } catch (Exception e) {

                System.out.println("An error occurred.");

            } finally {
                executor.shutdownNow();
            }
        }

        System.out.println("\n================================");
        System.out.println("           QUIZ RESULT");
        System.out.println("================================");

        System.out.println("Total Questions: " + questions.length);
        System.out.println("Correct Answers: " + score);
        System.out.println("Wrong Answers: " + (questions.length - score));

        double percentage =
                ((double) score / questions.length) * 100;

        System.out.println("Percentage: " + percentage + "%");

        if (percentage >= 80) {
            System.out.println("Excellent performance!");
        } else if (percentage >= 60) {
            System.out.println("Good job!");
        } else if (percentage >= 40) {
            System.out.println("Keep practicing!");
        } else {
            System.out.println("You need more practice.");
        }

        sc.close();
    }
}
