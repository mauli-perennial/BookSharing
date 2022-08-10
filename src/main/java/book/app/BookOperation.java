package book.app;

import book_user_validator.ValidationForBookAndUser;
import exceptions.BooKNameException;
import main.application.TestBookApp;
import model.*;

import java.text.ParseException;
import java.util.*;
import java.util.logging.Logger;

import static book_user_validator.ValidationForBookAndUser.convertDate;

public class BookOperation {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    public static final Logger log = Logger.getLogger(String.valueOf(TestBookApp.class));
    static ResourceBundle bundle = ResourceBundle.getBundle("menu", Locale.CANADA_FRENCH);
    public BookOperation(){

    }
    public  void addBook(Map<String, List<Book>> bookStore, Map<Integer, User> owners, User user) throws ParseException, BooKNameException {
        log.info(bundle.getString("bookName"));
        String name = scanner.next();
        ValidationForBookAndUser.validateBookName(name);
        int isbn = random.nextInt();
        log.info(bundle.getString("authorNumber"));
        int number = scanner.nextInt();
        Set<String> authors = new HashSet<>();
        for (int index = 1; index <= number; index++) {
            authors.add(scanner.next());
        }
        Date date = convertDate(java.time.LocalDate.now().toString());
        log.info(bundle.getString("keywords"));
        Set<String> key = new HashSet<>();
        key.add(scanner.next());
        key.add(scanner.next());
        Book myBook = new Book(isbn, name, authors, date, key,BookStatus.AVAILABLE.getName());
        if (bookStore.containsKey(name)) {
            List<Book> oldList = bookStore.get(name);
            List<Book> newList = oldList;
            newList.add(myBook);
            bookStore.replace(name, oldList, newList);
        } else {
            List<Book> book = new ArrayList<>();
            book.add(myBook);
            bookStore.put(name, book);
        }
        owners.put(myBook.getIsbn(), new User(new UserBuilder(user.getUserId(),user.getFirstName(),user.getLastName(),user.getMobileNumber(),user.getEmail(),"")));
    }



    public boolean requestBook(User user, Map<Integer, User> owners, Map<String, String> request, Map<String, List<Book>> bookStore, Map<Integer, User> bookShare) throws BooKNameException {
        log.info(bundle.getString("bookName"));
        String bookName = scanner.next();
        ValidationForBookAndUser.validateBookName(bookName);
        BookService ser = new BookService();
        boolean isSuccess = false;
        if (bookStore.containsKey(bookName)) {
            List<Book> requestList = bookStore.get(bookName);
            for (Book b : requestList) {
                System.out.println(b);
            }
            log.info(bundle.getString("isbn"));
            int isbn = scanner.nextInt();
            if (owners.containsKey(isbn) && !bookShare.containsKey(isbn) && ser.checkStatusToRequest(requestList, isbn).equalsIgnoreCase(BookStatus.AVAILABLE.getName())) {
                log.info(" owner details   " + owners.get(isbn));
                log.info("press r to request book to owner he will contact you by email for further process");
                String req = scanner.next();
                if (req.equalsIgnoreCase("r")) {
                    isSuccess = true;
                    String message = String.format("Hi, I am  %s  %s I need book from you and book isbn number is %d  please contact me on my number %d",user.getFirstName(),user.getLastName(),isbn,user.getMobileNumber());
                    request.put(owners.get(isbn).getEmail(),message);
                    ser.changeBookStatus(bookName, isbn, bookStore,BookStatus.TAKEN.getName());
                    bookShare.put(isbn, new User(new UserBuilder(user.getUserId(),user.getFirstName(),user.getLastName(),user.getMobileNumber(),user.getEmail(),"")));
                }
            }
        }
        return isSuccess;
    }



    public boolean returnBook(Map<String, String> request, Map<String, List<Book>> bookStore, Map<Integer, User> bookShare, Map<Integer, User> owners, User user) {
        log.info(bundle.getString("bookName"));
        String select = scanner.next();
        BookService ser = new BookService();
        boolean isSuccess = false;
        if (bookStore.containsKey(select)) {
            log.info(bundle.getString("isbn"));
            int isbn = scanner.nextInt();
            if (bookShare.containsKey(isbn)) {
                String message = String.format("Hello %s Thanks for visiting we will mark book status returned",bookShare.get(isbn).getFirstName());
                log.info(message);
                ser.changeBookStatus(select, isbn, bookStore,BookStatus.AVAILABLE.getName());
                bookShare.remove(isbn);
                isSuccess = true;
                String ownerMessage = String.format("Hi %s %s  Returned book with isbn %d",owners.get(isbn).getFirstName(),user.getFirstName(),isbn);
                request.put(owners.get(isbn).getEmail(),ownerMessage);
            }
        }
        return isSuccess;
    }

    public static void menu(){
        String  menu = String.format("Please choose the below Options %n %s %n %s %n %s %n %s %n %s %n %s %n %s", bundle.getString("add"),bundle.getString("info"),bundle.getString("authors"),bundle.getString("keys"),bundle.getString("request"),bundle.getString("returnBook"),bundle.getString("exits"));
        log.info(menu);
    }
}
