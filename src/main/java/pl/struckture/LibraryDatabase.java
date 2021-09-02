package pl.struckture;

import java.util.ArrayList;

public class LibraryDatabase {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Author> authors = new ArrayList<>();
    private ArrayList<Borrows> borrows = new ArrayList<>();
    private ArrayList<Reader> readers = new ArrayList<>();

    public LibraryDatabase(ArrayList<Book> books, ArrayList<Author> authors, ArrayList<Borrows> borrows, ArrayList<Reader> readers) {
        this.books = books;
        this.authors = authors;
        this.borrows = borrows;
        this.readers = readers;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<Author> authors) {
        this.authors = authors;
    }

    public ArrayList<Borrows> getBorrows() {
        return borrows;
    }

    public void setBorrows(ArrayList<Borrows> borrows) {
        this.borrows = borrows;
    }

    public ArrayList<Reader> getReaders() {
        return readers;
    }

    public void setReaders(ArrayList<Reader> readers) {
        this.readers = readers;
    }

    public void addReader(Reader reader) {
        readers.add(reader);
    }

    @Override
    public String toString() {
        return "LibraryDatabase{" +
                "books=" + books +
                ",\n authors=" + authors +
                ",\n borrows=" + borrows +
                ",\n clients=" + readers +
                '}';
    }
}
