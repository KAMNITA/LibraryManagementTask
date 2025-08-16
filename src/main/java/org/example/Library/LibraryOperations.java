package org.example.Library;

import org.example.Users.Guest;
import org.example.Users.Student;
import org.example.Users.Faculty;
import org.example.Users.User;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public interface LibraryOperations {
    enum UserType {
        FACULTY(Faculty::new),
        GUEST(Guest::new),
        STUDENT(Student::new);

        private final Supplier<User> creator;

        UserType(Supplier<User> creator) {
            this.creator = creator;
        }

        public User createUser(String name, String userId, String email) {
            return creator.get().setBaseData(name, userId, email);
        }
        public static UserType fromString(String typeStr) {
            if (typeStr == null || typeStr.isEmpty()) {
                throw new IllegalArgumentException("UserType cannot be null or empty");
            }

            // Игнорируем регистр при сравнении (FACULTY = faculty = Faculty)
            String normalized = typeStr.trim().toUpperCase();

            try {
                return UserType.valueOf(normalized);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Unknown UserType: " + typeStr);
            }
        }
    }
    void addBook(String title, String author, String isbn, String genre);
    boolean removeBook(String isbn);
    Book findBook(String isbn);
    List<Book> searchBooks(String query);

    void registerUser(String name, String userId, String email, UserType type);
    User findUser(String userId);

    boolean borrowBook(String userId, String isbn);
    boolean returnBook(String userId, String isbn);

    public Book findBookByAuthor(String isbn);

    public Book findBookByName(String isbn);

    List<BorrowingRecord> getOverdueBooks();
}