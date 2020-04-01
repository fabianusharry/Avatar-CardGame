package com.avatarduel.model;

public class Land {
  private String name;
  private String description;
  private Element element;
  private String imgPath;

  public Land() {
    this.name = "";
    this.description = "";
    this.element = Element.AIR;
    this.imgPath = "card/image/no-image.png";
  }

  public Land(String name, String description, Element element, String imgPath) {
    this.name = name;
    this.description = description;
    this.element = element;
    this.imgPath = imgPath;
    System.out.println(imgPath);
  }

  public String getName() { return name; }
  public String getDescription() { return description; }
  public Element getElement() { return element; }
  public String getImgPath() { return imgPath; }
}