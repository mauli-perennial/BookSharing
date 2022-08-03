package org.store.book;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Book {
    private int isbn;
    private String bookName;
    private List<String> bookAuthor;
    private List<String > keyWords;
    private Date publishDate;

    private String status;

    public static final SimpleDateFormat sdf;
    static {
        sdf = new SimpleDateFormat("yyyy-MM-dd");
    }

    public Book(int isbn, String bookName, List<String> bookAuthor,Date publishDate,List<String> keyWords,String status) {
        super();
        this.isbn = isbn;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.publishDate = publishDate;
        this.keyWords = keyWords;
        this.status = status;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public List<String> getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(List<String> bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public List<String> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(List<String> keyWords) {
        this.keyWords = keyWords;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn=" + isbn +
                ", bookName='" + bookName + '\'' +
                ", bookAuthor=" + bookAuthor +
                ", keyWords=" + keyWords +
                ", publishDate=" + publishDate +
                ", status='" + status + '\'' +
                '}';
    }
}
