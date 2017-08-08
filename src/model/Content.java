package model;

import java.util.List;

public class Content {
  private String title;
  private List<String> paragraph;
  private List<String> vidUrl;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<String> getParagraph() {
    return paragraph;
  }

  public void setParagraph(List<String> paragraph) {
    this.paragraph = paragraph;
  }

  public List<String> getVidUrl() {
    return vidUrl;
  }

  public void setVidUrl(List<String> vidUrl) {
    this.vidUrl = vidUrl;
  }
}
