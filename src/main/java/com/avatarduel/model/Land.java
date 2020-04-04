package com.avatarduel.model;

public class Land extends Card {

  public Land() {
    super();
  }

  public Land(String name, String description, Element element) {
    super(name, description, element);
  }

  public void addPower() {
    //notify player to update element power (?)
  }

  @Override
  public int activate(String attr) {


    return 0;
  }
}