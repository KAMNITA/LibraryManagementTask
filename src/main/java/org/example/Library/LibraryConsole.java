package org.example.Library;

import com.sun.net.httpserver.Authenticator;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LibraryConsole {
    private final static Pattern patternOfOne = Pattern.compile("\\s*([a-zA-Z0-9]+)\\s*$");
    private final static Pattern patternOfTwo = Pattern.compile(  "\\s*([a-zA-Z0-9]+)\\s+([a-zA-Z0-9]+)\\s+([a-zA-Z0-9]+)\\s*$");
    private final static Pattern patternOfThree = Pattern.compile("\\s*([a-zA-Z0-9]+)\\s+([a-zA-Z0-9]+)\\s+([a-zA-Z0-9]+)\\s*$");
    private final static Pattern patternOfFour = Pattern.compile("\\s*([a-zA-Z0-9]+)\\s+([a-zA-Z0-9]+)\\s+([a-zA-Z0-9]+)\\s+([a-zA-Z0-9]+)\\s*$");

    private final Library library;
    private final Scanner scanner = new Scanner(System.in);


    public LibraryConsole() {
        this.library = new Library();
    }


    public void run() {
        while (true) {
            showMenu();
            int choice;
            try {
                choice = getIntInput("Enter choice: ");
            } catch (NoSuchElementException e) {
                System.out.println("End of file");
                return;
            }

            switch (choice) {
                case 1:
                    handleBookManagement();
                    break;
                case 2:
                    handleUserManagement();
                    break;
                case 3:
                    handleBorrowing();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    void handleBookManagement() {
        while (true) {
            System.out.println("\n=== Book Management ===");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Find Book by ISBN");
            System.out.println("4. Find Books by Author");
            System.out.println("5. Find Books by Title");
            System.out.println("6. Show All Books");
            System.out.println("0. Back to Main Menu");

            int choice;
            String line;
            try {
                choice = getIntInput("Enter choice: ");
            } catch (NoSuchElementException e) {
                System.out.println("End of file");
                return;
            }

            switch (choice) {
                case 1 -> {
                    try {

                        line = scanner.nextLine();
                    } catch (NoSuchElementException e) {
                        System.out.println("End of file");
                        return;
                    }
                    Matcher matcher = patternOfFour.matcher(line);
                    if (matcher.matches()) {
                        library.addBook(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4));
                    } else {
                        System.out.println("Invalid input of add Book line");
                    }
                }
                case 2 -> {
                    try {

                        line = scanner.nextLine();
                    } catch (NoSuchElementException e) {
                        System.out.println("End of file");
                        return;
                    }
                    Matcher matcher = patternOfOne.matcher(line);
                    if (matcher.matches()) {
                        if (library.removeBook(matcher.group(1))) {
                            System.out.println("Remove successful");
                        } else {
                            System.out.println("This book is in use or does not exist");

                        }
                    } else {
                        System.out.println("Invalid input");
                    }
                }
                case 3 -> {
                    try {

                        line = scanner.nextLine();
                    } catch (NoSuchElementException e) {
                        System.out.println("End of file");
                        return;
                    }
                    Matcher matcher = patternOfOne.matcher(line);
                    if (matcher.matches()) {
                        System.out.println(library.findBook(matcher.group(1)));
                    } else {
                        System.out.println("Invalid input");
                    }
                }
                case 4 -> {
                    try {

                        line = scanner.nextLine();
                    } catch (NoSuchElementException e) {
                        System.out.println("End of file");
                        return;
                    }
                    Matcher matcher = patternOfOne.matcher(line);
                    if (matcher.matches()) {
                        System.out.println(library.findBookByAuthor(matcher.group(1)));
                    } else {
                        System.out.println("Invalid input");
                    }
                }
                case 5 -> {
                    try {

                        line = scanner.nextLine();
                    } catch (NoSuchElementException e) {
                        System.out.println("End of file");
                        return;
                    }
                    Matcher matcher = patternOfOne.matcher(line);
                    if (matcher.matches()) {
                        System.out.println(library.findBookByName(matcher.group(1)));
                    } else {
                        System.out.println("Invalid input");
                    }
                }

                case 6 -> System.out.println(library.getBooks());

                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    void handleUserManagement() {
        while (true) {
            System.out.println("\n=== User Management ===");
            System.out.println("1. Register User");
            System.out.println("2. Find User");
            System.out.println("3. List All Users");
            System.out.println("0. Back to Main Menu");

            int choice;
            String line;
            try {
                choice = getIntInput("Enter choice: ");
            } catch (NoSuchElementException e) {
                System.out.println("End of file");
                return;
            }

            switch (choice) {
                case 1 -> {
                    try {
                        line = scanner.nextLine();
                    } catch (NoSuchElementException e) {
                        System.out.println("End of file");
                        return;
                    }
                    Matcher matcher = patternOfFour.matcher(line);
                    if (matcher.matches()) {
                        try {
                            library.registerUser(matcher.group(1), matcher.group(2), matcher.group(3), LibraryOperations.UserType.fromString(matcher.group(4)));
                        } catch (RuntimeException e) {
                            System.out.println("Incorrect user type");
                        }
                    } else {
                        System.out.println("Invalid input ");
                    }
                }
                case 2 -> {
                    try {
                        line = scanner.nextLine();
                    } catch (NoSuchElementException e) {
                        System.out.println("End of file");
                        return;
                    }
                    Matcher matcher = patternOfOne.matcher(line);
                    if (matcher.matches()) {
                        System.out.println(library.findUser(matcher.group(1)));
                    } else {
                        System.out.println("Invalid input ");
                    }
                }
                case 3 -> {
                    System.out.println(library.getUsers());
                }
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    void handleBorrowing() {
        while (true) {
            System.out.println("\n=== Borrowing Operations ===");
            System.out.println("1. Borrow Book");
            System.out.println("2. Return Book");
            System.out.println("3. Overdue books");
            System.out.println("4. View Borrowing History");
            System.out.println("5. Go to next day");
            System.out.println("0. Back to Main Menu");
            int choice;
            String line;
            try {
                choice = getIntInput("Enter choice: ");
            } catch (NoSuchElementException e) {
                System.out.println("End of file");
                return;
            }

            switch (choice) {
                case 1 -> {
                    try {
                        line = scanner.nextLine();
                    } catch (NoSuchElementException e) {
                        System.out.println("End of file");
                        return;
                    }
                    Matcher matcher = patternOfTwo.matcher(line);
                    if (matcher.matches()) {
                        if(library.borrowBook(matcher.group(1), matcher.group(2))){
                            System.out.println("Success borrow ");
                        }else{
                            System.out.println("invalid borrow ");
                        }
                    } else {
                        System.out.println("Invalid input ");
                    }
                }
                case 2 -> {
                    try {
                        line = scanner.nextLine();
                    } catch (NoSuchElementException e) {
                        System.out.println("End of file");
                        return;
                    }
                    Matcher matcher = patternOfTwo.matcher(line);
                    if (matcher.matches()) {

                        if(library.returnBook(matcher.group(1), matcher.group(2))){
                            System.out.println("Success return ");
                        }else{
                            System.out.println("invalid return ");
                        }
                    } else {
                        System.out.println("Invalid input ");
                    }
                }
                case 3 ->System.out.println(library.getOverdueBooks());
                case 4 -> {
                        System.out.println(library.getBorrowingHistory());
                }
                case 5 ->{library.goToNextDay();}
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private void showMenu() {
        System.out.println("\n=== Library Management ===");
        System.out.println("1. Book Management");
        System.out.println("2. User Management");
        System.out.println("3. Borrowing Operations");
        System.out.println("0. Exit");
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {


                try {
                    System.out.print(prompt);
                    return Integer.parseInt(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                }
            } catch (NoSuchElementException e) {
                System.out.println("End of file");
                return 0;
            }
        }
    }
}