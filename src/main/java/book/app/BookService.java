package book.app;

import model.Book;
import model.BookStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

 class BookService {
private static final String AUTHOR = "author";

private static final String KEY = "key";

     public List<Book> searchByValue(Map<String, List<Book>> bookStore, String value, String type) {
         List<Book> sortedBook = new ArrayList<>();
         for (List<Book> book : new ArrayList<>(bookStore.values())) {
             sortedBook.addAll(book);
         }
         List<Book> byAuthor;
         if (type.equals(AUTHOR)) {
             byAuthor = sortedBook.stream().filter(book -> book.getBookAuthor().stream().anyMatch((s -> s.contains(value)))).collect(Collectors.toList());
         } else if (type.equals(KEY)) {
             byAuthor = sortedBook.stream().filter(key -> key.getKeyWords().stream().anyMatch((s -> s.contains(value)))).collect(Collectors.toList());
         } else {
             byAuthor = sortedBook.stream().filter(book -> book.getBookName().contains(value)).collect(Collectors.toList());
         }
         return byAuthor;
     }




    public void changeBookStatus(String name, int isbn, Map<String, List<Book>> bookStore, String  status) {
        List<Book> booksByName = bookStore.get(name);
        for (Book book : booksByName) {
            if (book.getIsbn() == isbn) {
                if (status.equals(BookStatus.TAKEN.getName())) {
                    book.setStatus(status);
                } else {
                    book.setStatus(BookStatus.AVAILABLE.getName());
                }
            }
        }
    }



     public String checkStatusToRequest(List<Book> books, int isbn) {
         String status = "";
         for (Book book : books) {
             if (isbn == book.getIsbn() && book.getStatus().equalsIgnoreCase(BookStatus.AVAILABLE.getName())) {
                 status = "Available";
                 break;
             }
         }
         return status;
     }
}
