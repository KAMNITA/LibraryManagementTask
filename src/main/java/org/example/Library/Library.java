package org.example.Library;

import org.example.Users.User;

import java.time.LocalDate;
import java.util.*;

public class Library implements LibraryOperations {
    private static int date = 0;//для простоты проверки
    private Map<String, Book> books;
    private Map<String, User> users;
    private List<BorrowingRecord> borrowingHistory;
    private Set<Book> overdueBooks;
    private Map<String, Set<String>> titleToIsbns = new HashMap<>();
    private Set<String> genres;

    public Library() {
        this.genres = new HashSet<>();
        this.books = new HashMap<>();
        this.users = new HashMap<>();
        this.overdueBooks = new HashSet<>();
        this.borrowingHistory = new ArrayList<>();

    }

    public static int getDate() {
        return date;
    }

    public static void setDate(int date) {
        Library.date = date;
    }


    @Override
    public void addBook(String title, String author, String isbn, String genre) {
        if (books.containsKey(isbn)) {
            return;
        }
        Book book = new Book(title, author);
        this.books.put(isbn, book);
        this.genres.add(genre);
        titleToIsbns.computeIfAbsent(title, k -> new HashSet<>()).add(isbn);

    }

    @Override
    public boolean removeBook(String isbn) {

        if (this.books.containsKey(isbn) && this.books.get(isbn).isAvailable()) {

            this.titleToIsbns.remove(this.books.get(isbn).getName());
            this.books.remove(isbn);
            this.titleToIsbns.get(this.books.get(isbn).getAuthor()).remove(isbn);
            this.overdueBooks.remove(isbn);

            return true;
        }
        return false;
    }

    @Override
    public Book findBook(String isbn) {
        return this.books.get(isbn);
    }

    @Override
    public Set<Book> findBookByName(String name) {//все книги с одинаковыми названиями
        Set<String> isbns = titleToIsbns.get(name);
        Set<Book> res = new HashSet<>();
        if (isbns == null) {
            return Set.of();
        }
        for (var s : isbns) {
            Book book = this.books.get(s);
            if (book != null) {
                res.add(book);
            }
        }
        return res;

    }

    @Override
    public Set<Book> findBookByAuthor(String author) {//считаем, что поиск по автору происходит редко, поэтому для него не делаем мапу
        Set<Book> res = new HashSet<>();
        this.books.forEach((k, v) -> {
            if (Objects.equals(v.getAuthor(), author)) {
                res.add(v);
            }
        });
        return res;
    }

    @Override
    public Set<Book> getOverdueBooks() {//просроченные книги
        return overdueBooks;
    }


    @Override
    public void registerUser(String name, String userId, String email, UserType type) {
        this.users.put(userId, type.createUser(name, userId, email));
    }

    @Override
    public User findUser(String userId) {
        return this.users.get(userId);
    }

    @Override
    public void goToNextDay() {
        ++date;
        books.forEach((str, b) -> {
            if (!b.isAvailable()) {
                b.decreaseDueTime();
                if (b.getDueTime() < 0) {
                    overdueBooks.add(b);
                }
            }
        });
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
        double fine = 0;
        User user = this.users.get(userId);
        Book book = this.books.get(isbn);
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
            this.books.get(isbn).setDueTime(user.getBorrowDays());
        } else {
            fine = book.getDueTime() < 0 ? Math.abs(book.getDueTime()) * user.getFinePerDay() : 0;
            user.getBorrowedBooks().remove(isbn);
        }

        this.borrowingHistory.add(new BorrowingRecord(book, user, date, isBorrowing, fine));

        return true;
    }

    public Map<String, Book> getBooks() {
        return this.books;
    }

    public void setBooks(Map<String, Book> books) {
        this.books = books;
    }

    public Map<String, User> getUsers() {
        return this.users;
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }

    public List<BorrowingRecord> getBorrowingHistory() {
        return this.borrowingHistory;
    }

    public void setBorrowingHistory(List<BorrowingRecord> borrowingHistory) {
        this.borrowingHistory = borrowingHistory;
    }

    public Set<String> getGenres() {
        return this.genres;
    }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }

    public Map<String, Set<String>> getTitleToIsbns() {
        return titleToIsbns;
    }

    public void setTitleToIsbns(Map<String, Set<String>> titleToIsbns) {
        this.titleToIsbns = titleToIsbns;
    }
}