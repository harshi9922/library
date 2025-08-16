import java.util.*;

class Book {
    String title, author, isbn;
    boolean isAvailable = true;

    Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return title + " by " + author + " (ISBN: " + isbn + ") - " +
               (isAvailable ? "Available" : "Borrowed");
    }
}

class User {
    String userId, name, email;
    List<Book> borrowedBooks = new ArrayList<>();

    User(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return userId + ": " + name + " (" + email + ")";
    }
}

class Library {
    List<Book> books = new ArrayList<>();
    List<User> users = new ArrayList<>();

    void addBook(Book b) {
        books.add(b);
        System.out.println("Book added: " + b.title);
    }

    void registerUser(User u) {
        users.add(u);
        System.out.println("User registered: " + u.name);
    }

    void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in library.");
            return;
        }
        for (Book b : books) System.out.println(b);
    }

    void displayUsers() {
        if (users.isEmpty()) {
            System.out.println("No users registered.");
            return;
        }
        for (User u : users) System.out.println(u);
    }

    void borrowBook(String isbn, String userId) {
        Book book = findBook(isbn);
        User user = findUser(userId);

        if (book != null && user != null) {
            if (book.isAvailable) {
                book.isAvailable = false;
                user.borrowedBooks.add(book);
                System.out.println(user.name + " borrowed " + book.title);
            } else {
                System.out.println("Sorry, this book is already borrowed.");
            }
        }
    }

    void returnBook(String isbn, String userId) {
        User user = findUser(userId);
        if (user != null) {
            for (Book b : user.borrowedBooks) {
                if (b.isbn.equals(isbn)) {
                    b.isAvailable = true;
                    user.borrowedBooks.remove(b);
                    System.out.println(user.name + " returned " + b.title);
                    return;
                }
            }
            System.out.println("This user did not borrow the book.");
        }
    }

    Book findBook(String isbn) {
        for (Book b : books) if (b.isbn.equals(isbn)) return b;
        System.out.println("Book not found.");
        return null;
    }

    User findUser(String userId) {
        for (User u : users) if (u.userId.equals(userId)) return u;
        System.out.println("User not found.");
        return null;
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. Register User");
            System.out.println("3. Display Books");
            System.out.println("4. Display Users");
            System.out.println("5. Borrow Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter author: ");
                    String author = sc.nextLine();
                    System.out.print("Enter ISBN: ");
                    String isbn = sc.nextLine();
                    library.addBook(new Book(title, author, isbn));
                    break;

                case 2:
                    System.out.print("Enter User ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();
                    library.registerUser(new User(id, name, email));
                    break;

                case 3:
                    library.displayBooks();
                    break;

                case 4:
                    library.displayUsers();
                    break;

                case 5:
                    System.out.print("Enter ISBN to borrow: ");
                    String isbnBorrow = sc.nextLine();
                    System.out.print("Enter User ID: ");
                    String userBorrow = sc.nextLine();
                    library.borrowBook(isbnBorrow, userBorrow);
                    break;

                case 6:
                    System.out.print("Enter ISBN to return: ");
                    String isbnReturn = sc.nextLine();
                    System.out.print("Enter User ID: ");
                    String userReturn = sc.nextLine();
                    library.returnBook(isbnReturn, userReturn);
                    break;

                case 7:
                    System.out.println("Exiting system. Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }
}
