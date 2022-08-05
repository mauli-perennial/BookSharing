package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class Book {
    private int isbn;
    private String bookName;
    private Set<String> bookAuthor;
    private Set<String> keyWords;
    private Date publishDate;

    private String status;

    public static final SimpleDateFormat sdf;

    static {
        sdf = new SimpleDateFormat("yyyy-MM-dd");
    }

    public Book(int isbn, String bookName, Set<String> bookAuthor, Date publishDate, Set<String> keyWords, String status) {
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


    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Set<String> getBookAuthor() {
        return bookAuthor;
    }


    public Set<String> getKeyWords() {
        return keyWords;
    }


    public int getIsbn() {
        return isbn;
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
