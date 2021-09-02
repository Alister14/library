package pl.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.YearMonth;


public class TopMenuButtonsController {

    public static final String CALENDAR_FXML = "/fxml/Calendar.fxml";
    public static final String ADD_BORROWS_FXML = "/fxml/AddBorrow.fxml";
    public static final String LIST_BORROWS_FXML = "/fxml/ListBorrows.fxml";
    public static final String ADD_READERS_FXML = "/fxml/AddReaders.fxml";
    public static final String LIST_READERS_FXML = "/fxml/ListReaders.fxml";
    public static final String ADD_BOOK_FXML = "/fxml/AddBook.fxml";
    public static final String LIST_BOOKS_FXML = "/fxml/ListBooks.fxml";
    public static final String FXML_LIST_AUTHORS_FXML = "/fxml/ListAuthors.fxml";
    public static final String ADD_AUTHOR_FXML = "/fxml/AddAuthor.fxml";
    public static final String SAVE_LOAD_FXML = "/fxml/SaveLoad.fxml";
    @FXML
    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    void openListBooks() {
        mainController.setCenter(LIST_BOOKS_FXML);//Ustawiamcentralnie formatke
    }

    @FXML
    public void addBook() {
        mainController.setCenter(ADD_BOOK_FXML);//Ustawiam centralnie formatke
    }

    @FXML
    void openListReaders() {
        mainController.setCenter(LIST_READERS_FXML);//Ustawiam centralnie formatke
    }

    @FXML
    public void addReader() {
        mainController.setCenter(ADD_READERS_FXML);//Ustawiam centralnie formatke
    }

    @FXML
    void openListBorrows() {
        mainController.setCenter(LIST_BORROWS_FXML);//Ustawiam centralnie formatke
    }

    @FXML
    public void addBorrow() {
        mainController.setCenter(ADD_BORROWS_FXML);//Ustawiam centralnie formatke AddBook
    }

    @FXML
    public void calendar() {
        mainController.setCenter(CALENDAR_FXML);//Ustawiam centralnie formatke AddBook
    }

    public void openListAuthors() {
        mainController.setCenter(FXML_LIST_AUTHORS_FXML);//Ustawiam centralnie formatke
    }

    public void addAuthor() {
        mainController.setCenter(ADD_AUTHOR_FXML);//Ustawiam centralnie formatke
    }

    public void saveLoad() {
        mainController.setCenter(SAVE_LOAD_FXML);//Ustawiam centralnie formatke
    }


    public void displayCalendar(ActionEvent event) {
        Stage calendar = new Stage();
        calendar.setTitle("Kalendarz zwrotów");
        calendar.setScene(new Scene(new FullCalendarView(YearMonth.now()).getView()));
        calendar.show();
    }


}
