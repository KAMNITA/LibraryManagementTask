package org.example.Users;

public class Student extends User {
    private static final int maxBooks = 3;
    private static final int borrowDays = 14;
    private static final double finePerDay = 0.50;
    public Student(String name, String userId, String email) {
        super(name, userId, email);
    }

    public Student() {
        super();
    }

    @Override
    public int getMaxBooks() { return maxBooks; }

    @Override
    public int getBorrowDays() { return borrowDays; }

    @Override
    public double getFinePerDay() { return finePerDay; }
}
