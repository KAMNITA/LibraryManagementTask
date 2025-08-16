package org.example.Library;

import org.example.Users.User;

import java.time.LocalDate;
import java.util.Date;

public class BorrowingRecord {//class for logs
    private LocalDate date;
    private User user;
    private Book bookName;
    private boolean isBorrowing;
    public BorrowingRecord(Book bookName, User user, LocalDate date, boolean isBorrowing){
        this.bookName = bookName;
        this.user=user;
        this.date = date;
        this.isBorrowing=isBorrowing;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBookName() {
        return bookName;
    }

    public void setBookName(Book bookName) {
        this.bookName = bookName;
    }

    public boolean isBorrowing() {
        return isBorrowing;
    }

    public void setBorrowing(boolean Borrowing) {
        isBorrowing = Borrowing;
    }
}
