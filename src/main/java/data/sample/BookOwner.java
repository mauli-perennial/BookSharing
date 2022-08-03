package data.sample;

import org.store.book.Book;
import org.store.book.OwnerDTO;
import org.store.book.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static data.sample.BookSamples.dataBook;
import static data.sample.BookSamples.sampleReadyBook;
import static data.sample.UserSample.sampleReadyUser;
import static data.sample.UserSample.userData;

public class BookOwner {
    public static Map<Integer, OwnerDTO> getBookOwner(){
        Map<String, User> myUsers = userData(sampleReadyUser());
        Map<String, List<Book>> bookStore = dataBook(sampleReadyBook());
        Map<Integer,OwnerDTO> bookOwner = new HashMap<>();
        List<List<Book>> sortBooks = bookStore.values().stream().collect(Collectors.toList());
        List<Book> sortedBooks = new ArrayList<>();
        for (List<Book> book : sortBooks){
            sortedBooks.addAll(book);
        }
        for(Book book : sortedBooks){
            bookOwner.put(book.getIsbn(),new OwnerDTO(myUsers.get("mauli.satav@perennialsys.com").getFirstName(),myUsers.get("mauli.satav@perennialsys.com").getEmail()));
        }
        return bookOwner;
    }
}
