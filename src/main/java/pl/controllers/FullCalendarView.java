package pl.controllers;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import pl.Data.DatabaseHandling;
import pl.struckture.Borrows;

import java.sql.Date;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;


public class FullCalendarView {

    public static ArrayList<Borrows> borrows = DatabaseHandling.getBorrows();
    public static TableView<Borrows> borrowsTable = new TableView<>();
    private ArrayList<AnchorPaneNode> allCalendarDays = new ArrayList<>(35);
    private final VBox view;
    private final Text calendarTitle;
    private YearMonth currentYearMonth;

    /**
     * Create a calendar view
     *
     * @param yearMonth year month to create the calendar of
     */
    public FullCalendarView(YearMonth yearMonth) {
        currentYearMonth = yearMonth;
        // Create the calendar grid pane
        TableColumn<Borrows, String> titleColumn = new TableColumn<>("Tytuł");
        TableColumn<Borrows, String> clientColumn = new TableColumn<>("Użytkownik");
        TableColumn<Borrows, Date> borrowDateColumn = new TableColumn<>("Data wypożyczenia");
        TableColumn<Borrows, Date> plannedReturnDateColumn = new TableColumn<>("Data planowanego zwrotu");
        TableColumn<Borrows, Date> returnDateColumn = new TableColumn<>("Data zwrotu");
        titleColumn.setCellValueFactory(new PropertyValueFactory<Borrows, String>("Book"));
        clientColumn.setCellValueFactory(new PropertyValueFactory<Borrows, String>("Client"));
        borrowDateColumn.setCellValueFactory(new PropertyValueFactory<Borrows, Date>("BorrowDate"));
        plannedReturnDateColumn.setCellValueFactory(new PropertyValueFactory<Borrows, Date>("PlannedReturnDate"));
        if (borrowsTable.getColumns().isEmpty()) {
            borrowsTable.getColumns().add(titleColumn);
            borrowsTable.getColumns().add(clientColumn);
            borrowsTable.getColumns().add(borrowDateColumn);
            borrowsTable.getColumns().add(plannedReturnDateColumn);
            borrowsTable.getColumns().add(returnDateColumn);
        }
        borrowsTable.getItems().setAll(borrows);

        GridPane calendar = new GridPane();
        calendar.setPrefSize(700, 400);
        calendar.setGridLinesVisible(true);
        // Create rows and columns with anchor panes for the calendar
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                AnchorPaneNode ap = new AnchorPaneNode();
                ap.setPrefSize(200, 200);
                calendar.add(ap, j, i);
                allCalendarDays.add(ap);
            }
        }
        // Days of the week labels
        Text[] dayNames = new Text[]{new Text("Poniedziałek"), new Text("Wtorek"),
                new Text("Środa"), new Text("Czwartek"), new Text("Piątek"),
                new Text("Sobota"), new Text("Niedziela")};
        GridPane dayLabels = new GridPane();
        dayLabels.setPrefWidth(600);
        Integer col = 0;
        for (Text txt : dayNames) {
            AnchorPane ap = new AnchorPane();
            ap.setPrefSize(200, 10);
            AnchorPane.setBottomAnchor(txt, 5.0);
            ap.getChildren().add(txt);
            dayLabels.add(ap, col++, 0);
        }
        // Create calendarTitle and buttons to change current month
        calendarTitle = new Text();
        Button previousMonth = new Button("<<");
        previousMonth.setOnAction(e -> previousMonth());
        Button nextMonth = new Button(">>");
        nextMonth.setOnAction(e -> nextMonth());
        HBox titleBar = new HBox(previousMonth, calendarTitle, nextMonth);
        titleBar.setSpacing(10.0);
        titleBar.setAlignment(Pos.BASELINE_CENTER);
        // Populate calendar with the appropriate day numbers
        populateCalendar(yearMonth);
        // Create the calendar view
        view = new VBox(titleBar, dayLabels, calendar, borrowsTable);
    }

    /**
     * Set the days of the calendar to correspond to the appropriate date
     *
     * @param yearMonth year and month of month to render
     */
    public void populateCalendar(YearMonth yearMonth) {
        // Get the date we want to start with on the calendar
        LocalDate calendarDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);
        // Dial back the day until it is MONDAY (unless the month starts on a sunday)
        while (!calendarDate.getDayOfWeek().toString().equals("MONDAY")) {
            calendarDate = calendarDate.minusDays(1);
        }
        // Populate the calendar with day numbers
        for (AnchorPaneNode ap : allCalendarDays) {
            if (ap.getChildren().size() != 0) {
                ap.getChildren().clear();
            }
            int todaysReturns = 0;
            for (Borrows borrow : borrows) {
                if (borrow.getReturnDeadline().equals(Date.valueOf(calendarDate))) {
                    todaysReturns++;
                }
            }
            ap.setStyle("-fx-background-color: #f7f7f7; -fx-border-width: 1px; -fx-border-color: #000000");
            Text day = new Text(String.valueOf(calendarDate.getDayOfMonth()));
            ap.setDate(calendarDate);
            AnchorPane.setTopAnchor(day, 5.0);
            AnchorPane.setLeftAnchor(day, 5.0);
            ap.getChildren().add(day);
            if (todaysReturns != 0) {
                ap.setStyle("-fx-background-color: #fcbe03");
                Text returns = new Text("Zwrotów dzisiaj:\n" + todaysReturns);
                AnchorPane.setBottomAnchor(returns, 9.0);
                AnchorPane.setRightAnchor(returns, 9.0);
                ap.getChildren().add(returns);
            }
            if (calendarDate.equals(LocalDate.now())) {
                ap.setStyle("-fx-border-color: #73d453; -fx-border-width: 3px");
            }

            calendarDate = calendarDate.plusDays(1);
        }
        // Change the title of the calendar
        calendarTitle.setText(getPLMonthName(yearMonth.getMonth().toString()) + " " + yearMonth.getYear());
    }

    /**
     * Move the month back by one. Repopulate the calendar with the correct dates.
     */
    private void previousMonth() {
        currentYearMonth = currentYearMonth.minusMonths(1);
        populateCalendar(currentYearMonth);
    }

    private String getPLMonthName(String month) {
        String plMonthName;
        plMonthName = switch (month) {
            case ("JANUARY") -> "STYCZEŃ";
            case ("FEBRUARY") -> "LUTY";
            case ("MARCH") -> "MARZEC";
            case ("APRIL") -> "KWIECIEŃ";
            case ("MAY") -> "MAJ";
            case ("JUNE") -> "CZERWIEC";
            case ("JULY") -> "LIPIEC";
            case ("AUGUST") -> "SIERPIEŃ";
            case ("SEPTEMBER") -> "WRZESIEŃ";
            case ("OCTOBER") -> "PAŹDZIERNIK";
            case ("NOVEMBER") -> "LISTOPAD";
            case ("DECEMBER") -> "GRUDZIEŃ";
            default -> throw new IllegalStateException("Unexpected value: " + month);
        };
        return plMonthName;
    }

    /**
     * Move the month forward by one. Repopulate the calendar with the correct dates.
     */
    private void nextMonth() {
        currentYearMonth = currentYearMonth.plusMonths(1);
        populateCalendar(currentYearMonth);
    }

    public VBox getView() {
        return view;
    }

    public ArrayList<AnchorPaneNode> getAllCalendarDays() {
        return allCalendarDays;
    }

    public void setAllCalendarDays(ArrayList<AnchorPaneNode> allCalendarDays) {
        this.allCalendarDays = allCalendarDays;
    }
}
