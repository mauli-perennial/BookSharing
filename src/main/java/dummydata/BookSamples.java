package dummydata;

import model.Book;
import model.BookStatus;
import model.User;

import java.util.*;

import static book_user_validator.ValidationForBookAndUser.convertDate;

public class BookSamples {
    private BookSamples() {
    }

    public static List<Book> sampleReadyBook() {
        ArrayList<Book> list = new ArrayList<>();
        try {
            Set<String> authors = new HashSet<>(Arrays.asList("mauli", "kunal"));
            Set<String> keyWord = new HashSet<>(Arrays.asList("sad", "happy"));
            list.add(new Book(1, "rama", authors, convertDate("12-02-2022"), keyWord, BookStatus.AVAILABLE.getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static Map<String, List<Book>> dataBook(List<Book> book) {
        HashMap<String, List<Book>> books = new HashMap<>();
        for (Book myBooks : book) {
            List<Book> bookList = new ArrayList<>();
            bookList.add(myBooks);
            books.put(myBooks.getBookName(), bookList);
        }
        return books;
    }
}

