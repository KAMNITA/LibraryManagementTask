package org.example.Library;

import org.example.Users.User;

import java.time.LocalDate;
import java.util.*;

public class Library implements LibraryOperations {

    private Map<String, Book> books;
    private Map<String, User> users;
    private List<BorrowingRecord> borrowingHistory;
    private Set<String> genres;

    public Library() {
        genres = new HashSet<>();
        books = new HashMap<>();
        users = new HashMap<>();
        borrowingHistory = new ArrayList<>();

    }

    @Override
    public void addBook(String title, String author, String isbn, String genre) {
        books.put(isbn, new Book(title, author));
        genres.add(genre);
    }

    @Override
    public boolean removeBook(String isbn) {
        if (books.get(isbn).isAvailable()) {
            books.remove(isbn);
            return true;
        }
        return false;
    }

    @Override
    public Book findBook(String isbn) {
        return null;
    }

    @Override
    public Book findBookByAuthor(String isbn) {
        return null;
    }

    @Override
    public Book findBookByName(String isbn) {
        return null;
    }

    @Override
    public List<BorrowingRecord> getOverdueBooks() {//просроченные книги
        return List.of();
    }

    @Override
    public List<Book> searchBooks(String query) {
        return List.of();
    }

    @Override
    public void registerUser(String name, String userId, String email, UserType type) {
        this.users.put(userId, type.createUser(name, userId, email));
    }

    @Override
    public User findUser(String userId) {
        return users.get(userId);
    }

    @Override
    public boolean borrowBook(String userId, String isbn) {
        return processBookTransaction(userId, isbn, true);
    }

    @Override
    public boolean returnBook(String userId, String isbn) {
        return processBookTransaction(userId, isbn, false);
    }

    private boolean processBookTransaction(String userId, String isbn, boolean isBorrowing) {
        User user = users.get(userId);
        Book book = books.get(isbn);
        if (user == null || book == null) {
            return false;
        }

        if (isBorrowing) {
            if (!book.isAvailable() || !user.canBorrow()) {
                return false;
            }
        } else {
            if (book.isAvailable() || !user.getBorrowedBooks().contains(isbn)) {
                return false;
            }
        }

        book.setAvailability(!isBorrowing);
        if (isBorrowing) {
            user.getBorrowedBooks().add(isbn);
        } else {
            user.getBorrowedBooks().remove(isbn);
        }

        borrowingHistory.add(new BorrowingRecord(book, user, LocalDate.now(), isBorrowing));

        return true;
    }

    public Map<String, Book> getBooks() {
        return books;
    }

    public void setBooks(Map<String, Book> books) {
        this.books = books;
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }

    public List<BorrowingRecord> getBorrowingHistory() {
        return borrowingHistory;
    }

    public void setBorrowingHistory(List<BorrowingRecord> borrowingHistory) {
        this.borrowingHistory = borrowingHistory;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }
}