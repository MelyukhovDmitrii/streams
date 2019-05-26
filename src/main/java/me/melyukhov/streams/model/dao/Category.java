package me.melyukhov.streams.model.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column(name = "picture_path")
    private String picturePath;

    @Column(name = "count_viewers")
    private int countViewers;

    @OneToMany(mappedBy = "id", fetch = FetchType.EAGER)
    private Set<Translation> translations;

    public Set<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(Set<Translation> translations) {
        this.translations = translations;
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

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public int getCountViewers() {
        return countViewers;
    }

    public void setCountViewers(int countViewers) {
        this.countViewers = countViewers;
    }
}
