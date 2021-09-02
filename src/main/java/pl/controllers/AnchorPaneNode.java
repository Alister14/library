package pl.controllers;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import pl.struckture.Borrows;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Create an anchor pane that can store additional data.
 */
public class AnchorPaneNode extends AnchorPane {

    // Date associated with this pane
    private LocalDate date;

    /**
     * Create a anchor pane node. Date is not assigned in the constructor.
     *
     * @param children children of the anchor pane
     */
    public AnchorPaneNode(Node... children) {
        super(children);
        // Add action handler for mouse clicked
        this.setOnMouseClicked(e -> print());
    }

    public void print() {
        ArrayList<Borrows> temp = new ArrayList<>();
        for (Borrows borrow : FullCalendarView.borrows) {
            if (borrow.getReturnDeadline().equals(Date.valueOf(date))) {
                temp.add(borrow);
            }
            FullCalendarView.borrowsTable.getItems().setAll(temp);
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}