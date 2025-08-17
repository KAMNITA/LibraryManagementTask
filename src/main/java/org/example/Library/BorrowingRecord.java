package org.example.Library;

import org.example.Users.User;

import java.time.LocalDate;
import java.util.Date;

public class BorrowingRecord {//class for logs
    private int date;//для упрощения тестирования - int
    private User user;
    private Book bookName;
    private boolean isBorrowing;//одалживает или возвращает
    private final double duty;
    public BorrowingRecord(Book bookName, User user, int date, boolean isBorrowing,double duty){
        this.bookName = bookName;
        this.user=user;
        this.date = date;
        this.isBorrowing=isBorrowing;
        this.duty=duty;
    }
    public BorrowingRecord(Book bookName, User user, int date, boolean isBorrowing ){
        this.bookName = bookName;
        this.user=user;
        this.date = date;
        this.isBorrowing=isBorrowing;
        this.duty = 0;
    }


    public int getDate() {
        return date;
    }

    public void setDate(int date) {
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

    @Override
    public String toString() {
        return " BorrowingRecord{" +
                "date=" + date +
                ", user=" + user +
                ", bookName=" + bookName +
                ", isBorrowing=" + isBorrowing + (duty==0 ?"": " user duty for this book = "+ duty + " денег ")+
                '}';
    }
}
