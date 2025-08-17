package org.example.Users;

public class Guest extends User {
    private static final int maxBooks = 1;
    private static final int borrowDays = 7;
    private static final double finePerDay = 0.50;

    public Guest(String name, String userId, String email) {
        super(name, userId, email);
    }

    public Guest() {

    }

    @Override
    public int getMaxBooks() {
        return maxBooks;
    }

    @Override
    public int getBorrowDays() {
        return borrowDays;
    }

    @Override
    public double getFinePerDay() {
        return finePerDay;
    }
}