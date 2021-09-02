package pl.struckture;

import java.time.LocalDate;
import java.time.Period;

public class DataBorrow {

    private LocalDate dataOfLoan = LocalDate.now();
    private int dayOfLoan = 0;
    private int monthOfLoan = 1;
    private int yearOfLoan = 0;
    private Period timeOfLoan = Period.of(yearOfLoan, monthOfLoan, dayOfLoan);
    private LocalDate dataOfReturn = dataOfLoan.plus(timeOfLoan);
    private Period timeLeftToReturn = Period.between(dataOfLoan, dataOfReturn);


    public DataBorrow() {
        this.timeOfLoan = Period.of(yearOfLoan, monthOfLoan, dayOfLoan);
        this.dataOfReturn = dataOfLoan.plus(timeOfLoan);
        this.timeLeftToReturn = Period.between(dataOfLoan, dataOfReturn);
    }

    public LocalDate getDataOfLoan() {
        return dataOfLoan;
    }

    public void setDataOfLoan(LocalDate dataOfLoan) {
        this.dataOfLoan = dataOfLoan;
    }

    public int getDayOfLoan() {
        return dayOfLoan;
    }

    public void setDayOfLoan(int dayOfLoan) {
        this.dayOfLoan = dayOfLoan;
    }

    public int getMonthOfLoan() {
        return monthOfLoan;
    }

    public void setMonthOfLoan(int monthOfLoan) {
        this.monthOfLoan = monthOfLoan;
    }

    public int getYearOfLoan() {
        return yearOfLoan;
    }

    public void setYearOfLoan(int yearOfLoan) {
        this.yearOfLoan = yearOfLoan;
    }

    public Period getTimeOfLoan() {
        return timeOfLoan;
    }

    public void setTimeOfLoan(Period timeOfLoan) {
        this.timeOfLoan = timeOfLoan;
    }

    public LocalDate getDataOfReturn() {
        return dataOfReturn;
    }

    public void setDataOfReturn(LocalDate dataOfReturn) {
        this.dataOfReturn = dataOfReturn;
    }

    public Period getTimeLeftToReturn() {
        return timeLeftToReturn;
    }

    public void setTimeLeftToReturn(Period timeLeftToReturn) {
        this.timeLeftToReturn = timeLeftToReturn;
    }

    public void addTimeToDelivery() {
        int year = 0;
        int month = 1;
        int day = 0;

        Period addTime = Period.of(year, month, day);
        dataOfReturn = dataOfReturn.plus(addTime);
        timeLeftToReturn = Period.between(LocalDate.now(), dataOfReturn);
        timeOfLoan = Period.between(dataOfLoan, dataOfReturn);
    }

    @Override
    public String toString() {

        return
                "Data wypożyczenia " + dataOfLoan +
                        " czas wypozyczenia :" + timeOfLoan +
                        " data zwrotu :" + dataOfReturn +
                        " czas pozostaly do zwrotu :" + timeLeftToReturn +
                        '\n';
    }
}
