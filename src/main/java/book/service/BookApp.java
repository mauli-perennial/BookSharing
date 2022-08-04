package book.service;

import org.store.book.Book;
import org.store.book.BookStatus;
import org.store.book.OwnerDTO;
import org.store.book.User;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

import static validation.ValidationForBookAndUser.convertDate;
public class BookApp {
    public void bookAppDashboard(User user, Map<Integer,OwnerDTO> owners, Map<String,String> request, Map<String,List<Book>> bookStore){
        Scanner scanner = new Scanner(System.in);
        Map<Integer,OwnerDTO> bookShare = new HashMap<>();
        boolean logout = false;


        while (!logout) {
            System.out.println("Please choose the below Options");
            System.out.println("1:     To add the new BOOK                        2:     To get book Information by Name of the Book ");
            System.out.println("3:     To Get Book from the store by author       4:      To get Book Information by key word          ");
            System.out.println("5:     To Request a book                          6:       To Return book.                           ");
            System.out.println("7:     To exit");

            try {
                int choice = scanner.nextInt();
                if (choice == 1) {
                    System.out.println("Enter the Name of Book");
                    String name = scanner.next();
                    Book myBook = addBook(name);
                    owners.put(myBook.getIsbn(),new OwnerDTO(user.getFirstName(),user.getEmail()));
                    if(bookStore.containsKey(name)) {
                        List<Book> oldList = bookStore.get(name);
                        List<Book> newList = oldList;
                        newList.add(myBook);
                        bookStore.replace(name,oldList,newList);
                    }else {
                        List<Book> book = new ArrayList<>();
                        book.add(myBook);
                        bookStore.put(name, book);
                    }
                    continue;
                }
                 else if (choice == 2) {
                    System.out.println("Enter the Name of the Book for which you want the information of the Book");
                    String name = scanner.next();
                    if (bookStore.containsKey(name)) {
                        List<Book> bookSearch = bookStore.get(name);
                        for(int index= 0 ; index < bookSearch.size();index++){
                            System.out.println(index + 1 + "    Copy   "+  bookSearch.get(index));
                        }
                    }else{
                      System.out.println("Book not present in store");
                      continue;
                    }
                } else if (choice == 3) {
                    System.out.println("Enter the name of the author");
                    String name = scanner.next();
                    List<Book> sr = byAuthor(bookStore,name);
                    if(sr.size()>0){
                        for (Book b : sr){
                            System.out.println(b);
                        }
                    }else {
                        System.out.println(" no book by author");
                    }
                } else if (choice == 4) {
                    System.out.println("Enter the name of the key");
                    String key = scanner.next();
                    List<Book> sr = byKeyWord(bookStore,key);
                    if(sr.size()>0){
                        for (Book b : sr){
                            System.out.println(b);
                        }
                    }else {
                        System.out.println(" no book by key");
                    }
                } else if (choice == 5) {
                    System.out.println("Enter the name of the Book you want to request");
                    String bookName = scanner.next();
                    if (bookStore.containsKey(bookName)){
                        List<Book> requestList = bookStore.get(bookName);
                        for(Book b : requestList){
                            System.out.println(b);
                        }
                        System.out.println("Enter the isbn number of book to request book to owner");
                        int isbn= scanner.nextInt();
                        if(owners.containsKey(isbn) && !bookShare.containsKey(isbn) && checkStatusToRequest(requestList,isbn).equalsIgnoreCase(BookStatus.AVAILABLE.getName())){
                            System.out.println(" owner details   " + owners.get(isbn));
                            System.out.println("press r to request book to owner he will contact you by email for further process");
                            String req = scanner.next();
                            if(req.equalsIgnoreCase("r")){
                                request.put(owners.get(isbn).getOwnerEmail(),"Hi, I am " + user.getFirstName() + " " + user.getLastName() + " I need book from you and book isbn number is " +isbn + " please contact me on my number    " + user.getMobileNumber());
                                changeBookStatus(bookName,isbn,bookStore,BookStatus.TAKEN);
                                bookShare.put(isbn,new OwnerDTO(user.getFirstName(),user.getEmail()));
                            }
                        }
                    }else{
                        System.out.println("book not present in store to request or taken away");
                    }
                } else if(choice == 6) {
                     System.out.println("Enter the name of book you want to return");
                     String select = scanner.next();
                     if(bookStore.containsKey(select)){
                     System.out.println(" please enter the isbn number of your book");
                     int isbn = scanner.nextInt();
                     if (bookShare.containsKey(isbn)) {
                         System.out.println("Hello   " + bookShare.get(isbn).getOwnerName() + " Thanks for visiting we will mark book status returned");
                         changeBookStatus(select,isbn,bookStore,BookStatus.AVAILABLE);
                         bookShare.remove(isbn);
                         request.put(owners.get(isbn).getOwnerEmail(),"Hi    " + owners.get(isbn).getOwnerName() + " ,   " + user.getFirstName() + " " + "Returned book with isbn  " + isbn);
                     }
                     }else{
                         System.out.println(" no such books to return");
                     }
                } else if(choice == 7) {
                    logout = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    
    public Book addBook(String name) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int isbn = random.nextInt();
        System.out.println("Enter the number of authors you want to add");
        int number = scanner.nextInt();
        List<String > authors = new ArrayList<>();
        for(int index = 1; index<= number ; index++){
            authors.add(scanner.next());
        }
        Date date = convertDate(java.time.LocalDate.now().toString());
        System.out.println("Enter the two keywords");
        List<String>  key = new ArrayList<>();
        key.add(scanner.next());
        key.add(scanner.next());
        return  new Book(isbn,name,authors,date,key, BookStatus.AVAILABLE.getName());
    }




    public List<Book> byAuthor(Map<String,List<Book>> bookStore,String authorName){
        List<List<Book>> sortAuthor = bookStore.values().stream().collect(Collectors.toList());
        List<Book> sortedAuthor = new  ArrayList<>();
        List<Book> sortReturn = new ArrayList<>();
        for (List<Book> book : sortAuthor){
            sortedAuthor.addAll(book);
        }
        System.out.println(sortedAuthor.size());

        for(Book but : sortedAuthor){
            List<String> authors = but.getBookAuthor();
            for (String s : authors) {
                if (s.equalsIgnoreCase(authorName)) {
                    sortReturn.add(but);
                }
            }
        }
        return sortReturn;
    }



    public List<Book> byKeyWord(Map<String,List<Book>> bookStore,String authorName){
        List<List<Book>> sortKey = bookStore.values().stream().collect(Collectors.toList());
        List<Book> sortedKey = new  ArrayList<>();
        List<Book> sortReturnKey = new ArrayList<>();
        for (List<Book> book : sortKey){
            sortedKey.addAll(book);
        }
        for(Book book : sortedKey){
            List<String> authors = book.getKeyWords();
            for (String s : authors) {
                if (s.equalsIgnoreCase(authorName)) {
                    sortReturnKey.add(book);
                }
            }
        }
        return sortReturnKey;
    }



    public void changeBookStatus(String name, int isbn,Map<String,List<Book>> bookStore,BookStatus status){
        List<Book> booksByName = bookStore.get(name);
        for(Book b : booksByName){
            if(b.getIsbn() == isbn){
                if(status.equals(BookStatus.TAKEN)){
                    b.setStatus(BookStatus.TAKEN.getName());
                }else {
                    b.setStatus(BookStatus.AVAILABLE.getName());
                }
            }
        }
    }



    public String checkStatusToRequest(List<Book> books, int isbn){
        String status = "";
        for(Book book : books){
            if(isbn == book.getIsbn () && book.getStatus().equalsIgnoreCase(BookStatus.AVAILABLE.getName())){
                status = "Available";
            }
        }
        return status;
    }
}
