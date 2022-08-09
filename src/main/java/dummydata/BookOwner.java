package dummydata;

import model.Book;
import model.User;
import model.UserBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dummydata.UserSample.sampleReadyUser;
import static dummydata.UserSample.userData;

public class BookOwner {
    private BookOwner(){

    }
    public static final String EMAIL = "mauli.satav@gmail.com";
    public static Map<Integer, User> getBookOwner() {
        Map<String, User> myUsers = userData(sampleReadyUser());
        Map<String, List<Book>> bookStore = BookSamples.dataBook(BookSamples.sampleReadyBook());
        Map<Integer, User> bookOwner = new HashMap<>();
        List<List<Book>> sortBooks = new ArrayList<>(bookStore.values());
        List<Book> sortedBooks = new ArrayList<>();
        for (List<Book> book : sortBooks) {
            sortedBooks.addAll(book);
        }
        for (Book book : sortedBooks) {
            bookOwner.put(book.getIsbn(), new User(new UserBuilder(myUsers.get(EMAIL).getUserId(),myUsers.get(EMAIL).getFirstName(),myUsers.get(EMAIL).getLastName(),myUsers.get(EMAIL).getMobileNumber(),myUsers.get(EMAIL).getEmail(),"")));
        }
        return bookOwner;
    }
}
