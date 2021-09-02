package pl.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.Data.DatabaseHandling;
import pl.struckture.Borrows;
import pl.utils.DialogsUtils;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Locale;

public class ListBorrowsController {
    ArrayList<Borrows> borrows = DatabaseHandling.getBorrows();
    @FXML
    private TextField filterByTitleField;

    @FXML
    private TextField filterByClientField;

    @FXML
    private TableView<Borrows> borrowsTableView;

    @FXML
    private TableColumn<Borrows, String> titleColumn;

    @FXML
    private TableColumn<Borrows, String> nameReaderColumn;

    @FXML
    private TableColumn<Borrows, Date> dataBorrowColumn;

    @FXML
    private TableColumn<Borrows, Date> dataReturnPlanedColumn;

    public void returnBook() {

        Date returnData = borrowsTableView.getSelectionModel().getSelectedItem().getPlannedReturnDate();
        long date = ChronoUnit.DAYS.between(LocalDate.parse(java.time.LocalDate.now().toString()), LocalDate.parse(returnData.toString()));
        if (date < 0) {
            System.out.println("Opóżnienie " + Math.abs(date));
            DialogsUtils.punishDialog(Math.abs(date));
        } else System.out.println("Oddane przed czasem " + date);
        for (Borrows borrow : DatabaseHandling.getBorrows()) {
            if (borrow.getNameAndTitle().toLowerCase(Locale.ROOT).matches(borrowsTableView.getSelectionModel().getSelectedItem().getNameAndTitle().toLowerCase(Locale.ROOT)))
                ;
            {
                DatabaseHandling.returnBook(Date.valueOf(java.time.LocalDate.now()), borrow.getBorrowId());
            }
        }

    }


    public void initialize() {
        titleColumn.setCellValueFactory(new PropertyValueFactory<Borrows, String>("Book"));
        nameReaderColumn.setCellValueFactory(new PropertyValueFactory<Borrows, String>("Client"));
        dataBorrowColumn.setCellValueFactory(new PropertyValueFactory<Borrows, Date>("BorrowDate"));
        dataReturnPlanedColumn.setCellValueFactory(new PropertyValueFactory<Borrows, Date>("PlannedReturnDate"));
        borrowsTableView.getItems().setAll(borrows);
    }

    public void filter() {
        ArrayList<Borrows> temp = new ArrayList<>();
        for (Borrows borrow : borrows) {
            if (borrow.getBook().toLowerCase(Locale.ROOT).contains(filterByTitleField.getText().toLowerCase(Locale.ROOT)) &&
                    borrow.getClient().toLowerCase(Locale.ROOT).contains(filterByClientField.getText().toLowerCase(Locale.ROOT)))
                temp.add(borrow);
        }
        borrowsTableView.getItems().setAll(temp);
    }


}

