package entity;

import java.util.Date;

/*
sushi object contain id, name, imagePath, short description, detail description
*/
public class Sushi {

    private int id;
    private String name;
    private String photoPath;
    private String description;
    private String fullDescription;
    private String author;
    private Date dateCreated;
    public Sushi() {
    }

    public Sushi(int id, String name, String photoPath, String description, String fullDescription) {
        this.id = id;
        this.name = name;
        this.photoPath = photoPath;
        this.description = description;
        this.fullDescription = fullDescription;
    }
    public Sushi(int id, String name, String photoPath, String description, String fullDescription, String author, Date dateCreated) {
        this.id = id;
        this.name = name;
        this.photoPath = photoPath;
        this.description = description;
        this.fullDescription = fullDescription;
        this.author = author;
        this.dateCreated = dateCreated;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
