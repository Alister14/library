package pl.Data;

import pl.struckture.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

public class DatabaseHandling {
    private static LibraryDatabase libraryDatabase;
    private static final String databaseURL = "jdbc:ucanaccess://src/main/resources/database/Biblio.accdb";

    public static void ReadDatabase() {

        ArrayList<Author> authors = readAuthorsFromDatabase();
        ArrayList<Book> books = readBooksFromDatabase();
        ArrayList<Reader> readers = readReadersFromDatabase();
        ArrayList<Borrows> borrows = readBorrowsFromDatabase();


        /** changing rows in database */


        libraryDatabase = new LibraryDatabase(books, authors, borrows, readers);

        BorrowsToXml bo = new BorrowsToXml();
        //  bo.serialize(libraryDatabase);

        for (Borrows borrow : borrows)
            borrow.setNameAndTitle();
        //bo.deserialize("");

        // System.out.println(libraryDatabase.toString());
    }

    private static ArrayList<Book> readBooksFromDatabase() {
        ArrayList<Book> books = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(databaseURL)) {
            String sql = "SELECT * FROM Book";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                Integer id = result.getInt("BookId");
                String tittle = result.getString("Title");
                String publisher = result.getString("Publisher");
                Integer publishingYear = result.getInt("PublishingYear");
                Boolean available = result.getBoolean("Available");
                Integer authorId = result.getInt("AuthorId");
                Integer quantity = result.getInt("Quantity");

                books.add(new Book(id, tittle, publisher, publishingYear, available, authorId, quantity));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return books;
    }

    private static ArrayList<Author> readAuthorsFromDatabase() {
        ArrayList<Author> authors = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(databaseURL)) {
            String sql = "SELECT * FROM Author";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                Integer id = result.getInt("AuthorId");
                String firstname = result.getString("FirstName");
                String surname = result.getString("SurName");
                Date birthh = result.getDate("BirthDate");
                authors.add(new Author(firstname, surname, birthh, id));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return authors;
    }

    private static ArrayList<Reader> readReadersFromDatabase() {
        ArrayList<Reader> readers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(databaseURL)) {
            String sql = "SELECT * FROM Reader";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                Integer id = result.getInt("ReaderId");
                String firstname = result.getString("FirstName");
                String surname = result.getString("SurName");
                Integer phoneNumber = result.getInt("PhoneNumber");
                Date birthDate = result.getDate("BirthDate");
                String email = result.getString("Email");
                readers.add(new Reader(id, firstname, surname, phoneNumber, birthDate, email));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return readers;
    }

    private static ArrayList<Borrows> readBorrowsFromDatabase() {
        ArrayList<Borrows> borrows = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(databaseURL)) {
            String sql = "SELECT * FROM Borrows";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                int borrowId = result.getInt("BorrowId");
                Date borrowDate = result.getDate("BorrowDate");
                Date plannedReturnDate = result.getDate("PlannedReturnDate");
                Integer bookId = result.getInt("BookId");
                Integer readerId = result.getInt("ReaderId");
                borrows.add(new Borrows(borrowId, borrowDate, plannedReturnDate, bookId, readerId));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return borrows;
    }

    public static void addNewReader(String firstname, String surname, Integer phoneNumber, Date birthDate, String email) {
        try (Connection connection = DriverManager.getConnection(databaseURL)) {
            String sql = "INSERT INTO Reader (FirstName, SurName, PhoneNumber, BirthDate, Email) VALUES ( ?, ?, ?, ?, ?) ";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, surname);
            preparedStatement.setInt(3, phoneNumber);
            preparedStatement.setDate(4, birthDate);
            preparedStatement.setString(5, email);

            int row = preparedStatement.executeUpdate();

            if (row > 0) {
                System.out.println("A row has in Reader been inserted successfully.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        libraryDatabase.setReaders(readReadersFromDatabase());
        System.out.println(libraryDatabase.getReaders().toString());
    }

    public static void addNewAuthor(String firstname, String surname, Date BirthData) {
        try (Connection connection = DriverManager.getConnection(databaseURL)) {
            String sql = "INSERT INTO Author (FirstName, SurName, BirthDate) VALUES ( ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, surname);
            preparedStatement.setDate(3, BirthData);

            int row = preparedStatement.executeUpdate();

            if (row > 0) {
                System.out.println("A row has in Authors been inserted successfully.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        libraryDatabase.setAuthors(readAuthorsFromDatabase());
        System.out.println(libraryDatabase.getAuthors().toString());
    }

    public static void addNewBook(String title, String publisher, Integer publishingYear, Boolean available, Integer authorId, Integer quantity) {
        try (Connection connection = DriverManager.getConnection(databaseURL)) {
            String sql = "INSERT INTO Book (Title, Publisher, PublishingYear, Available, AuthorId, Quantity) VALUES ( ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, publisher);
            preparedStatement.setInt(3, publishingYear);
            preparedStatement.setBoolean(4, available);
            preparedStatement.setInt(5, authorId);
            preparedStatement.setInt(6, quantity);

            int row = preparedStatement.executeUpdate();

            if (row > 0) {
                System.out.println("A row in Book has been inserted successfully.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        libraryDatabase.setBooks(readBooksFromDatabase());
        //System.out.println(libraryDatabase.getBooks().toString());
    }

    public static void addNewBorrow(Date borrowDate, Integer bookId, Integer readerId, Integer maxBorrowingDays) {
        try (Connection connection = DriverManager.getConnection(databaseURL)) {
            String sql = "INSERT INTO Borrows (BorrowDate, PlannedReturnDate, BookId, ReaderId) VALUES ( ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, borrowDate);
            preparedStatement.setDate(2, addDays(borrowDate, maxBorrowingDays));
            preparedStatement.setInt(3, bookId);
            preparedStatement.setInt(4, readerId);

            int row = preparedStatement.executeUpdate();

            if (row > 0) {
                System.out.println("A row in Borrows has been inserted successfully.");
            }
            for (Book book : libraryDatabase.getBooks()) {
                if (bookId == book.getBooktId()) {

                    sql = "SELECT * FROM Book";
                    Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    ResultSet rset = stmt.executeQuery(sql);
                    rset.absolute(bookId);
                    rset.updateInt("Quantity", (book.getQuantity() - 1));
                    if ((book.getQuantity() - 1) < 1) {
                        rset.updateBoolean("Available", false);
                    }
                    rset.updateRow();
                    System.out.println("Row Updated");
                }
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        libraryDatabase.setBorrows(readBorrowsFromDatabase());
        libraryDatabase.setBooks(readBooksFromDatabase());
    }

    public static String getAuthorName(Integer authorId) {
        String authorName = "";
        try (Connection connection = DriverManager.getConnection(databaseURL)) {
            String sql = "SELECT * FROM Author WHERE AuthorId = " + authorId;

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            if (result.next())
                authorName = result.getString("FirstName") + " " + result.getString("SurName");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return authorName;
    }

    public static String getReaderName(Integer readerID) {
        String readerName = "";
        try (Connection connection = DriverManager.getConnection(databaseURL)) {
            String sql = "SELECT * FROM Reader WHERE ReaderId = " + readerID;

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            if (result.next())
                readerName = result.getString("FirstName") + " " + result.getString("SurName");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return readerName;
    }

    public static String getBookName(Integer bookId) {
        String bookName = "";
        try (Connection connection = DriverManager.getConnection(databaseURL)) {
            String sql = "SELECT * FROM Book WHERE BookId = " + bookId;

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            if (result.next())
                bookName = result.getString("Title");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return bookName;
    }

    public static Date addDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return new Date(c.getTimeInMillis());
    }

    public static void returnBook(Date plannedReturnDate, Integer borrowId) {

        Integer bookIdToChange = null;

        for (Borrows i : libraryDatabase.getBorrows()) {
            if (i.getBorrowId() == borrowId) {
                bookIdToChange = i.getBookId();
                break;
            }
        }

        try (Connection connection = DriverManager.getConnection(databaseURL)) {

            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM Borrows";
            ResultSet rset = stmt.executeQuery(sql);
            rset.absolute(borrowId);
            rset.updateDate("PlannedReturnDate", plannedReturnDate);
            rset.updateRow();

            for (Book book : libraryDatabase.getBooks()) {
                if (bookIdToChange == book.getBooktId()) {
                    sql = "SELECT * FROM Book";
                    rset = stmt.executeQuery(sql);
                    rset.absolute(bookIdToChange);
                    rset.updateInt("Quantity", (book.getQuantity() + 1));
                    if (book.getQuantity() == 0) {
                        rset.updateBoolean("Available", true);
                    }
                    rset.updateRow();
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        libraryDatabase.setBorrows(readBorrowsFromDatabase());
        libraryDatabase.setBooks(readBooksFromDatabase());
    }

    public static ArrayList<Book> getBooks() {
        return libraryDatabase.getBooks();
    }

    public static ArrayList<Author> getAuthors() {
        return libraryDatabase.getAuthors();
    }

    public static ArrayList<Borrows> getBorrows() {
        return libraryDatabase.getBorrows();
    }

    public static ArrayList<Reader> getReaders() {
        return libraryDatabase.getReaders();
    }

}
