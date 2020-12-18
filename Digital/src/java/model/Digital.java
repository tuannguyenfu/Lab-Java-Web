/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 *
 * @author admin
 */
public class Digital {

    private int id;
    private String title;
    private String description;
    private String shortDes;
    private String image;
    private String author;
    private Timestamp timePost;

    public Digital() {
    }

    public Digital(int id, String title, String description, String shortDes, String image, String author, Timestamp timePost) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.shortDes = shortDes;
        this.image = image;
        this.author = author;
        this.timePost = timePost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitile(String titile) {
        this.title = titile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDes() {
        return shortDes;
    }

    public void setShortDes(String shortDes) {
        this.shortDes = shortDes;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Timestamp getTimePost() {
        return timePost;
    }

    public void setTimePost(Timestamp timePost) {
        this.timePost = timePost;
    }

    public String getDateFormat() {
        return new SimpleDateFormat("MMM dd yyyy - h:mma").format(this.timePost).replace("AM", "am").replace("PM", "pm");
    }


}
