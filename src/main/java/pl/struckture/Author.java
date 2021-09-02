package pl.struckture;


import java.sql.Date;

public class Author {
    protected String FirstName;
    protected String SurName;
    protected Date BirthDate;
    protected Integer AuthorId;

    public Author(String FirstName, String SurName, Date BirthDate, Integer AuthorId) {
        this.FirstName = FirstName;
        this.SurName = SurName;
        this.BirthDate = BirthDate;
        this.AuthorId = AuthorId;
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

    public Date getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(Date birthDate) {
        BirthDate = birthDate;
    }

    public Integer getAuthorId() {
        return AuthorId;
    }

    public void setAuthorId(Integer authorId) {
        AuthorId = authorId;
    }
}
