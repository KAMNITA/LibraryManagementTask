package org.example.Users;

import java.util.ArrayList;
import java.util.List;

public abstract class User {
    protected String name;
    protected String userId;
    protected String email;
    protected List<String> borrowedBooks;

    public User(String name, String userId, String email) {
        this.name = name;
        this.userId = userId;
        this.email = email;
        this.borrowedBooks = new ArrayList<>();
    }

    public User() {

    }

    public User setBaseData(String name, String userId, String email) {
        this.name = name;
        this.userId = userId;
        this.email = email;
        this.borrowedBooks = new ArrayList<>();
        return this;
    }

    public abstract int getMaxBooks();

    public abstract int getBorrowDays();

    public abstract double getFinePerDay();

    public boolean canBorrow() {
        return this.borrowedBooks.size() < getMaxBooks();
    }

    public boolean Borrow(String bookName) {
        if (this.borrowedBooks.size() < this.getMaxBooks()) {
            this.borrowedBooks.add(bookName);
            return true;
        }
        return false;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<String> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}