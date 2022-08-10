package book.app;

import exceptions.BookNotPresentException;
import main.application.TestBookApp;
import model.*;

import java.util.*;
import java.util.logging.Logger;


public class BookAppDashboard {
    private static final String AUTHOR = "author";

    private static final String KEY = "key";
    private static final String BOOK = "book";
    public static final Logger log = Logger.getLogger(String.valueOf(TestBookApp.class));
    public static final ResourceBundle bundle = ResourceBundle.getBundle("menu", Locale.CANADA_FRENCH);
    public static final BookOperation operation = new BookOperation();

    public  void dashboard(User user, Map<Integer, User> owners, Map<String, String> request, Map<String, List<Book>> bookStore) {
        Map<Integer, User> bookShare = new HashMap<>();
        boolean logout = false;
        Scanner scanner = new Scanner(System.in);
        BookService service = new BookService();

        while (!logout) {

            operation.menu();

            try {

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:

                        operation.addBook(bookStore, owners, user);


                        break;
                    case 2:
                        log.info(bundle.getString("bookName"));
                        String bookeyName = scanner.next();
                        List<Book> books = service.searchByValue(bookStore, bookeyName, BOOK);
                        if (!books.isEmpty()) {
                            log.info(books.toString());
                        } else {
                            throw new BookNotPresentException(" no books by this name");
                        }

                        break;
                    case 3:

                        log.info(bundle.getString("authorName"));
                        String authorName = scanner.next();
                        List<Book> list = service.searchByValue(bookStore, authorName, AUTHOR);
                        if (!list.isEmpty()) {
                            for (Book book : list) {
                                log.info(book.toString());
                            }
                        } else {
                            throw new BookNotPresentException(" no such books");
                        }

                        break;
                    case 4:

                        log.info(bundle.getString("keyWord"));
                        String key = scanner.next();
                        List<Book> byKeyResult = service.searchByValue(bookStore, key, KEY);
                        if (!byKeyResult.isEmpty()) {
                            for (Book book : byKeyResult) {
                                log.info(book.toString());
                            }
                        } else {
                            throw new BookNotPresentException("no book by key");
                        }
                        break;

                    case 5:

                        if (operation.requestBook(user, owners, request, bookStore, bookShare)) {
                            log.info(bundle.getString("requestSent"));
                        } else {
                            throw new BookNotPresentException(" no such books");
                        }
                        break;

                    case 6:

                        if (operation.returnBook(request, bookStore, bookShare, owners, user)) {
                            log.info(bundle.getString("returnMessage"));
                        } else {
                            throw new BookNotPresentException(" error in retrun");
                        }
                        break;

                    case 7:

                        logout = true;
                        break;

                    default:

                        log.info(" Invalid Choice");
                        break;

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
