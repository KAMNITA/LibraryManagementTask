package org.example.Library;

public class Book {
    private String name;
    private String author;
    private boolean availability;
    private int dueTime;

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
        this.availability = true;
        this.dueTime = 0;
    }
//    public Book(String name, String author, int dueTime) {
//        this.name = name;
//        this.author = author;
//        this.availability = true;
//        this.dueTime = dueTime;
//    }

    public void decreaseDueTime(){
        dueTime--;
    }

    public boolean isAvailable() {
        return availability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }



    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", availability=" + availability +
                ", dueTime=" + dueTime +
                '}';
    }



    public int getDueTime() {
        return dueTime;
    }

    public void setDueTime(int dueTime) {
        this.dueTime = dueTime;
    }

//    public String getGenre() {
//        return genre;
//    }
//
//    public void setGenre(String genre) {
//        this.genre = genre;
//    }
}
