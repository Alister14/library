package pl.struckture;

import pl.Data.DatabaseHandling;

public class Book {
    private Integer BooktId;
    private String Title;
    private String Publisher;
    private Integer PublishingYear;
    private Boolean Available;
    private Integer AuthorId;
    private Integer Quantity;

    public Book(Integer booktId, String title, String publisher, Integer publishingYear, Boolean available, Integer authorId, Integer quantity) {
        BooktId = booktId;
        Title = title;
        Publisher = publisher;
        PublishingYear = publishingYear;
        Available = available;
        AuthorId = authorId;
        Quantity = quantity;
    }

    public Integer getBooktId() {
        return BooktId;
    }

    public void setBooktId(Integer booktId) {
        BooktId = booktId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }

    public Integer getPublishingYear() {
        return PublishingYear;
    }

    public void setPublishingYear(Integer publishingYear) {
        PublishingYear = publishingYear;
    }

    public Boolean getAvailable() {
        return Available;
    }

    public void setAvailable(Boolean available) {
        Available = available;
    }

    public Integer getAuthorId() {
        return AuthorId;
    }

    public void setAuthorId(Integer authorId) {
        AuthorId = authorId;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    public String getAuthor() {
        return DatabaseHandling.getAuthorName(AuthorId);
    }

    @Override
    public String toString() {
        return "\nBook{" +
                "BooktId=" + BooktId +
                ", Title='" + Title + '\'' +
                ", Publisher='" + Publisher + '\'' +
                ", PublishingYear=" + PublishingYear +
                ", Available=" + Available +
                ", AuthorId=" + AuthorId +
                ", Quantity=" + Quantity +
                '}';
    }

}
