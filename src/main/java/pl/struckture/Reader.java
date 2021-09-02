package pl.struckture;

import java.sql.Date;

public class Reader {
    private Integer ReaderId;
    private String FirstName;
    private String SurName;
    private Integer PhoneNumber;
    private Date BirthDate;
    private String Email;

    public Reader(Integer readerId, String firstName, String surName, Integer phoneNumber, Date birthDate, String email) {
        ReaderId = readerId;
        FirstName = firstName;
        SurName = surName;
        PhoneNumber = phoneNumber;
        BirthDate = birthDate;
        Email = email;
    }

    public Integer getReaderId() {
        return ReaderId;
    }

    public void setReaderId(Integer readerId) {
        ReaderId = readerId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getSurName() {
        return SurName;
    }

    public void setSurName(String surName) {
        SurName = surName;
    }

    public Integer getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public Date getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(Date birthDate) {
        BirthDate = birthDate;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @Override
    public String toString() {
        return "\nReader{" +
                ", FirstName='" + FirstName + '\'' +
                ", SurName='" + SurName + '\'' +
                ", PhoneNumber=" + PhoneNumber +
                ", BirthDate=" + BirthDate +
                ", Email='" + Email + '\'' +
                '}';
    }
}
