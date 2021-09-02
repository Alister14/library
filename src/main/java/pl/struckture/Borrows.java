package pl.struckture;

import pl.Data.DatabaseHandling;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Borrows {
    private Integer BorrowId;
    private Date BorrowDate;
    private Date PlannedReturnDate;
    private Integer BookId;
    private Integer ReaderID;
    private String NameAndTitle;

    public Borrows(Integer borrowId, Date borrowDate, Date plannedReturnDate, Integer bookId, Integer readerID) {
        BorrowId = borrowId;
        BorrowDate = borrowDate;
        BookId = bookId;
        ReaderID = readerID;
        PlannedReturnDate = plannedReturnDate;
    }

    public Integer getBorrowId() {
        return BorrowId;
    }

    public void setBorrowId(Integer borrowId) {
        BorrowId = borrowId;
    }

    public Date getBorrowDate() {
        return BorrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        BorrowDate = borrowDate;
    }


    public void setNameAndTitle() {
        for (Reader reader : DatabaseHandling.getReaders()) {
            if (reader.getReaderId() == ReaderID) {
                NameAndTitle = reader.getFirstName() + " " + reader.getSurName() + " ";
            }
        }

        for (Book book : DatabaseHandling.getBooks()) {
            if (book.getBooktId() == BookId) {
                NameAndTitle = NameAndTitle + book.getTitle();
            }
        }
    }

    public Integer getBookId() {
        return BookId;
    }


    public void setBookId(Integer bookId) {
        BookId = bookId;
    }

    public Integer getReaderID() {
        return ReaderID;
    }

    public void setReaderID(Integer readerID) {
        ReaderID = readerID;
    }

    public Date getReturnDeadline() {
        return PlannedReturnDate;
    }

    public Date getPlannedReturnDate() {
        return PlannedReturnDate;
    }

    public void setPlannedReturnDate(Date plannedReturnDate) {
        PlannedReturnDate = plannedReturnDate;
    }

    public String getNameAndTitle() {
        return NameAndTitle;
    }

    public String getClient() {
        return DatabaseHandling.getReaderName(ReaderID);
    }

    public String getBook() {
        return DatabaseHandling.getBookName(BookId);
    }


    public Integer getNumberOfDaysToDeadline() {
        return (int) ChronoUnit.DAYS.between(LocalDate.parse(LocalDate.now().toString()), LocalDate.parse(PlannedReturnDate.toString()));
    }
}
