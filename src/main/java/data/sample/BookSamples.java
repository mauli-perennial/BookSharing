package data.sample;

import org.store.book.Book;
import org.store.book.BookStatus;

import java.util.*;

import static validation.ValidationForBookAndUser.convertDate;

public class BookSamples {
    private BookSamples() {
    }
    public static List<Book> sampleReadyBook(){
        ArrayList<Book> list = new ArrayList<>();
        try{
            List<String> authors = new ArrayList<>(Arrays.asList("mauli","kunal"));
            List<String> keyWord = new ArrayList<>(Arrays.asList("sad","happy"));
            list.add(new Book(1,"rama",authors,convertDate("12-02-2022"),keyWord, BookStatus.AVAILABLE.getName()));
        }catch(Exception e){

            System.out.println("error in the sample date of the books");
        }
        return list;
    }
public static Map<String,List<Book>> dataBook(List<Book> book){
        HashMap<String,List<Book>> books = new HashMap<>();
        for(Book myBooks : book){
            List<Book> bookList = new ArrayList<>();
            bookList.add(myBooks);
            books.put(myBooks.getBookName(),bookList);
        }
        return books;
}

}
