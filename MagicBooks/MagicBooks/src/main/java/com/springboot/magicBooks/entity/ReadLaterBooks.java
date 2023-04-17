package com.springboot.magicBooks.entity;

import javax.persistence.*;

@Entity
@Table(name="readlater")
public class ReadLaterBooks {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String useremail;
    private int bookid;
    public ReadLaterBooks() {
    }
    public ReadLaterBooks(int id, String useremail, int bookid) {
        super();
        this.id = id;
        this.useremail = useremail;
        this.bookid = bookid;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getUseremail() {
        return useremail;
    }
    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }
    public int getBookid() {
        return bookid;
    }
    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    @Override
    public String toString() {
        return "ReadLaterBooks{" +
                "id=" + id +
                ", useremail='" + useremail + '\'' +
                ", bookid=" + bookid +
                '}';
    }
}
