package book.app;

import exceptions.BookException;
import model.*;

import java.text.ParseException;
import java.util.*;
import static book_user_validator.ValidationForBookAndUser.convertDate;
import static book_user_validator.ValidationForBookAndUser.validateBookName;

public class BookOperation {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private BookOperation(){

    }
    static void addBook(Map<String, List<Book>> bookStore, Map<Integer, User> owners, User user) throws ParseException, BookException {
        System.out.println("Enter the Name of Book");
        String name = scanner.next();
        if (!validateBookName(name)) {
            throw new BookException("wrong bookname format");
        }
        int isbn = random.nextInt();
        System.out.println("Enter the number of authors you want to add");
        int number = scanner.nextInt();
        Set<String> authors = new HashSet<>();
        for (int index = 1; index <= number; index++) {
            authors.add(scanner.next());
        }
        Date date = convertDate(java.time.LocalDate.now().toString());
        System.out.println("Enter the two keywords");
        Set<String> key = new HashSet<>();
        key.add(scanner.next());
        key.add(scanner.next());
        Book myBook = new Book(isbn, name, authors, date, key, BookStatus.AVAILABLE.getName());
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
        owners.put(myBook.getIsbn(), new User(user.getUserId(), user.getFirstName(), user.getLastName(), user.getMobileNumber(), " ", user.getEmail(), " "));
    }



    static boolean requestBook(User user, Map<Integer, User> owners, Map<String, String> request, Map<String, List<Book>> bookStore, Map<Integer, User> bookShare) {
        System.out.println("Enter the name of the Book you want to request");
        String bookName = scanner.next();
        BookService ser = new BookService();
        boolean isSuccess = false;
        if (bookStore.containsKey(bookName)) {
            List<Book> requestList = bookStore.get(bookName);
            for (Book b : requestList) {
                System.out.println(b);
            }
            System.out.println("Enter the isbn number of book to request book to owner");
            int isbn = scanner.nextInt();
            if (owners.containsKey(isbn) && !bookShare.containsKey(isbn) && ser.checkStatusToRequest(requestList, isbn).equalsIgnoreCase(BookStatus.AVAILABLE.getName())) {
                System.out.println(" owner details   " + owners.get(isbn));
                System.out.println("press r to request book to owner he will contact you by email for further process");
                String req = scanner.next();
                if (req.equalsIgnoreCase("r")) {
                    isSuccess = true;
                    request.put(owners.get(isbn).getEmail(), "Hi, I am " + user.getFirstName() + " " + user.getLastName() + " I need book from you and book isbn number is " + isbn + " please contact me on my number    " + user.getMobileNumber());
                    ser.changeBookStatus(bookName, isbn, bookStore, BookStatus.TAKEN);
                    bookShare.put(isbn, new User(user.getUserId(), user.getFirstName(), user.getLastName(), user.getMobileNumber(), "", user.getEmail(), ""));
                }
            }
        }
        return isSuccess;
    }



    static boolean returnBook(Map<String, String> request, Map<String, List<Book>> bookStore, Map<Integer, User> bookShare, Map<Integer, User> owners, User user) {
        System.out.println("Enter the name of book you want to return");
        String select = scanner.next();
        BookService ser = new BookService();
        boolean isSuccess = false;
        if (bookStore.containsKey(select)) {
            System.out.println(" please enter the isbn number of your book");
            int isbn = scanner.nextInt();
            if (bookShare.containsKey(isbn)) {
                System.out.println("Hello   " + bookShare.get(isbn).getFirstName() + " Thanks for visiting we will mark book status returned");
                ser.changeBookStatus(select, isbn, bookStore, BookStatus.AVAILABLE);
                bookShare.remove(isbn);
                isSuccess = true;
                request.put(owners.get(isbn).getEmail(), "Hi    " + owners.get(isbn).getFirstName() + " ,   " + user.getFirstName() + " " + "Returned book with isbn  " + isbn);
            }
        }
        return isSuccess;
    }


    static void menu(){
        System.out.println("Please choose the below Options");
        System.out.println("1:     To add the new BOOK                        2:     To get book Information by Name of the Book ");
        System.out.println("3:     To Get Book from the store by author       4:      To get Book Information by key word          ");
        System.out.println("5:     To Request a book                          6:       To Return book.                           ");
        System.out.println("7:     To exit");
    }
}
