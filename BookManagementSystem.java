import java.util.ArrayList;
import java.util.Scanner;

class BookManagementSystem {
    private ArrayList<Book> list = new ArrayList<Book>();
    public void displayMenu() {
        {Integer choice = null;
            do {
                System.out.println("Menu\n 1.Add a book 2.Booklist 3.Delete book 4.Update book 5.Exit \n Enter your choice ");
                Scanner scanner = new Scanner(System.in);
                choice = scanner.nextInt();
                scanner.nextLine();
                handle(choice);
            } while (choice != 5);
        }
    }

    private void handle(Integer choice) {
        switch (choice) {
            case 1:
                addBook(list);
                break;
            case 2:
                bookList(list);
                break;
            case 3:
                deleteBook(list);
                break;
            case 4:
                updateBook(list);
                break;
            case 5:
                System.out.println("Exit");
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    public void addBook(ArrayList<Book> list) {//validation
        Book newbook = new Book();
        System.out.print("Enter book name: ");
        Scanner sc = new Scanner(System.in);
        newbook.name = sc.nextLine();
        System.out.print("Enter author name: ");
        newbook.author = sc.nextLine();
        boolean duplicate = false;//Check if a book with the same name and author already exists
        if (duplicate) {
            System.out.println("This book already exists. Please enter a different book.");
        } else {
            System.out.print("Enter price: ");
            newbook.price = sc.nextDouble();
            list.add(newbook);
            System.out.println("Book added successfully!");
        }
    }

    public boolean validateName(Book newbook) {
        boolean exists = list.stream()
                .anyMatch(b -> b.name.equalsIgnoreCase(newbook.name));
        if (exists) {
            System.out.println("A book with this name already exists.");
        }
        return exists;
    }

    public void bookList(ArrayList<Book> list) {//book empty or not
        try {
            if (list.isEmpty()) {
                throw new BookListException.BookInLibrary("No books in the library.");
            } else {
                System.out.println("Books in Library:");
                list.forEach(System.out::println);
            }
        } catch (BookListException.BookInLibrary e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteBook(ArrayList<Book> list) {
        System.out.print("Enter book name to delete: ");
        Scanner sc = new Scanner(System.in);
        String newName = sc.nextLine();
        boolean removed = list.removeIf(b -> b.name.equalsIgnoreCase(newName));
        if (removed) {
            System.out.println(" Book deleted successfully");
        } else {
            System.out.println(" Book not found to delete");
        }
    }

    public void updateBook(ArrayList<Book> list) {
        String searchName;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter book name to update: ");
        searchName = sc.nextLine();
        boolean found = false;
        for (Book b : list) {
            if (b.name.equalsIgnoreCase(searchName)) {
                found = true;
                displayBook(b, sc);
            }
        }
        if (!found) {
            try {
                throw new BookUpdateException.BookNotFoundException("Book '" + searchName + "' not found");
            } catch (BookUpdateException.BookNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void displayBook(Book b, Scanner sc) {
        int choice1;
        do {
            System.out.print("Menu\n 1.Edit book name 2.Edit author name 3.Edit price 4.Exit \nEnter your choice: ");
            choice1 = sc.nextInt();
            sc.nextLine();
            editMenuBook(choice1, b, sc);
        } while (choice1 != 4);
    }

    private void editMenuBook(Integer choice1, Book b, Scanner sc) {
        switch (choice1) {
            case 1:
                System.out.println("Enter edited name:");
                b.name = sc.nextLine();
                break;
            case 2:
                System.out.println("Enter edited author name:");
                b.author = sc.nextLine();
                break;
            case 3:
                System.out.println("Enter edited price:");
                b.price = sc.nextDouble();
                break;
            case 4:
                System.out.println("Exit");
                break;
            default:
                System.out.println("Invalid choice");
        }
    }
}