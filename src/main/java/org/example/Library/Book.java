package org.example.Library;

public class Book {
    private String name;
    private String author;
    private boolean availability;
    public Book(String name, String author){
        this.name=name;
        this.name = author;
        this.availability=true;
    }
    public boolean isAvailable(){
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

//    public String getGenre() {
//        return genre;
//    }
//
//    public void setGenre(String genre) {
//        this.genre = genre;
//    }
}
