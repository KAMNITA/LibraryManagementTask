package org.example.Users;


public class Faculty extends User {
    private static final int maxBooks = 10;
    private static final int borrowDays = 30;
    private static final double finePerDay = 0.50;

    public Faculty(String name, String userId, String email) {
        super(name, userId, email);
    }

    public Faculty() {

    }

    @Override
    public int getMaxBooks() { return maxBooks; }

    @Override
    public int getBorrowDays() { return borrowDays; }

    @Override
    public double getFinePerDay() { return finePerDay; }
}