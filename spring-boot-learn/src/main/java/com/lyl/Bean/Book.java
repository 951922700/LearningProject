package com.lyl.Bean;

public class Book {
    private String bookName;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "bookName='" + bookName + '\'' +
                '}';
    }
}
