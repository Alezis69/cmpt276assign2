package com.cmpt276.cmpt276assign2.models;


import jakarta.persistence.*;

@Entity
@Table(name = "rectangles")
public class Rectangle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int width;
    private int height;
    private String colour;
    private String borderColour;
    private String borderStyle;

    public Rectangle() {   }

    public Rectangle(String name, int width, int height, String colour, String borderColour, String borderStyle) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.colour = colour;
        this.borderColour = borderColour;
        this.borderStyle = borderStyle;
    }

    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getColour() {
        return colour;
    }

    public String getBorderColour() {
        return borderColour;
    }

    public String getBorderStyle() {
        return borderStyle;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setBorderColour(String borderColour) {
        this.borderColour = borderColour;
    }

    public void setBorderStyle(String borderStyle) {
        this.borderStyle = borderStyle;
    }

    public void setId(int id) {
        this.id = id;
    }
}