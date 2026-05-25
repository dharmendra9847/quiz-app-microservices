package com.quiz.myapp.services;

import java.util.Scanner;

public class QuestionService {

    // Array to store Questions
    Question[] questions = new Question[5];

    // Array to store user answers
    String[] selection = new String[5];

    public QuestionService() {

        questions[0] = new Question(1, "size of int", "4", "2", "32", "1", "4");
        questions[1] = new Question(2, "size of double", "8", "2", "4", "32", "8");
        questions[2] = new Question(3, "size of char", "1", "2", "32", "64", "2");
        questions[3] = new Question(4, "size of long", "32", "8", "2", "64", "8");
        questions[4] = new Question(5, "size of boolean", "1", "4", "16", "64", "1");
    }



    public void playQuiz(){

        Scanner sc = new Scanner(System.in);
        int a = 0;

        for (Question q : questions) {

            System.out.println("Question no." + q.getId() + " : " + q.getQuestion() + "?");

            for (int i = 0; i < q.options.length; i++) {
                System.out.println((i + 1) + ". " + q.options[i]);
            }

            System.out.print("Enter your answer: ");

            // Save user input in the array
            selection[a] = sc.nextLine();
            a++;
        }

        sc.close();
    }

    // public void printScore() {
    //     int score = 0;

    //     for (int j = 0; j < questions.length; j++) {
    //         Question q = questions[j];

    //         // Assuming you have a getAnswer() method
    //         String answer = q.getAnswer();
    //         String userSelection = selection[j];

    //         if (answer.equals(userSelection)) {
    //             score++;
    //         }
    //     }
    //     System.out.println("Your final score is: " + score + "/" + questions.length);
    // }

    public void printScore() {
        int score = 0;
        System.out.println("\n--- Result Summary ---");

        for (int j = 0; j < questions.length; j++) {
            Question q = questions[j];
            String correctAnswer = q.getAnswer();
            String userSelection = selection[j];

            System.out.print("Q" + q.getId() + ": ");

            if (correctAnswer.equals(userSelection)) {
                System.out.println("Correct! (Your answer: " + userSelection + ")");
                score++;
            } else {
                System.out.println("Wrong!");
                System.out.println("   -> Your answer: " + userSelection);
                System.out.println("   -> Correct answer: " + correctAnswer);

                // Adding specific explanations
                String explanation = getExplanation(q.getQuestion());
                System.out.println("   -> Why? " + explanation);
            }
            System.out.println();
        }
        System.out.println("Final Score: " + score + "/" + questions.length);
    }

    // Helper method for explanations
    private String getExplanation(String question) {
        if (question.contains("int")) return "In Java, an 'int' is 32-bit (4 bytes).";
        if (question.contains("double")) return "A 'double' is a 64-bit floating point (8 bytes).";
        if (question.contains("char")) return "Java uses Unicode for characters, which takes 16-bit (2 bytes).";
        if (question.contains("long")) return "A 'long' is a 64-bit integer (8 bytes).";
        if (question.contains("boolean")) return "While JVM dependent, a boolean is conceptually 1 byte.";
        return "Check Java Documentation for primitive sizes.";
    }
}
