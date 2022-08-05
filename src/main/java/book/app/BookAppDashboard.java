package book.app;

import exceptions.BookException;
import model.*;

import java.util.*;


public class BookAppDashboard {
    public void dashboard(User user, Map<Integer, User> owners, Map<String, String> request, Map<String, List<Book>> bookStore) {
        Map<Integer, User> bookShare = new HashMap<>();
        boolean logout = false;
        Scanner scanner = new Scanner(System.in);
        BookService ser = new BookService();
        while (!logout) {

            BookOperation.menu();

            try {

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:

                        BookOperation.addBook(bookStore, owners, user);

                        break;
                    case 2:
                        System.out.println("Enter the Name of the Book for which you want the information of the Book");

                        String bookeyName = scanner.next();
                        List<Book> books = ser.searchByValue(bookStore, bookeyName, "bybook");
                        if(!books.isEmpty()) {
                            System.out.println(books);
                        }else {
                            throw new BookException(" no books by this name");
                        }

                        break;
                    case 3:

                        System.out.println("Enter the name of the author");
                        String authorName = scanner.next();
                        List<Book> sr = ser.searchByValue(bookStore, authorName,"author");
                        if (!sr.isEmpty()) {
                            for (Book b : sr) {
                                System.out.println(b);
                            }
                        } else {
                            throw new BookException(" no such books");
                        }

                        break;
                    case 4:

                        System.out.println("Enter the name of the key");
                        String key = scanner.next();
                        List<Book> byKeyResult = ser.searchByValue(bookStore, key,"key");
                        if (!byKeyResult.isEmpty()) {
                            for (Book b : byKeyResult) {
                                System.out.println(b);
                            }
                        } else {
                            throw new BookException("no book by key");
                        }
                        break;

                    case 5:

                        if (BookOperation.requestBook(user, owners, request, bookStore, bookShare)) {
                            System.out.println(" request sent successfully");
                        } else {
                            throw new BookException(" no such books");
                        }
                        break;

                    case 6:

                        if (BookOperation.returnBook(request, bookStore, bookShare, owners, user)) {
                            System.out.println(" book return successfull message sent to owner");
                        } else {
                            throw new BookException(" error in retrun");
                        }
                        break;

                    case 7:

                        logout = true;
                        break;

                    default:

                        System.out.println(" Invalid Choice");
                        break;

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
