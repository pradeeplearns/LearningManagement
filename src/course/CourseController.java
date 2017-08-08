package course;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import model.Content;
import model.CourseData;

public class CourseController implements Initializable {

  public AnchorPane anchorPane;
  public HBox hBox;
  public ScrollPane scroll;
  public VBox buttonSet;
  public VBox detail;
  private int numberOfSections;
  private CourseData courseData;

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

  public void initData(CourseData courseData) {
    //Fetch course content from db

    hBox.setPadding(new Insets(20, 20, 20, 20));

    buttonSet.setPadding(new Insets(20, 20, 20, 20));

    scroll.setFitToHeight(true);
    //scroll.setFitToWidth(true);
    scroll.setMinWidth(800);
    scroll.autosize();
    scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
    scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

    numberOfSections = courseData.getContents().size();

    List<Button> buttons = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      Button section1 = new Button("Section " + (i+1));
      section1.setAlignment(Pos.CENTER);
      section1.setStyle("-fx-text-fill: blue; -fx-font-weight: bold; -fx-font-size: 15pt");
      section1.setMinWidth(250);
      section1.setMinHeight(100);
      section1.maxWidth(300);
      section1.maxHeight(150);

      final int finalI = i;
      section1.setOnAction(e -> {
        detail.getChildren().clear();
        detail.getChildren().addAll(setData(courseData.getContents().get(finalI)));
        scroll.setContent(detail);
      });

      buttons.add(section1);
    }

    Label title = new Label(courseData.getTitle());
    title.setStyle("-fx-font-size: 20pt; -fx-font-weight: bold; -fx-text-fill: brown");

    Text text = new Text();
    text.setText(courseData.getMetaData());

    ImageView imageView = new ImageView(new Image("/image/java101.jpg"));

    detail.setPadding(new Insets(20, 20, 20, 20));
    detail.getChildren().addAll(title, text, imageView);

    buttonSet.getChildren().addAll(buttons);
  }

  private List<VBox> setData(Content contents) {
    Label title = new Label(contents.getTitle());
    title.setStyle("-fx-font-size: 20pt; -fx-font-weight: bold; -fx-text-fill: brown");

    List<VBox> vbFin = new ArrayList<>();
    VBox vb = new VBox(10);
    vb.getChildren().add(title);
    vbFin.add(vb);

    WebView webview = null;

    for (int i = 0; i < contents.getParagraph().size(); i++) {
      VBox vBox = new VBox(10);
      vBox.setPadding(new Insets(20, 20, 20, 20));
      vBox.setAlignment(Pos.CENTER);

      Text text = new Text(contents.getParagraph().get(i));
      text.setWrappingWidth(900);
      text.setStyle("-fx-font-size: 20pt");

      if (i < contents.getVidUrl().size()) {
        webview = new WebView();
        webview.getEngine().load(contents.getVidUrl().get(i));
        webview.setMinWidth(900);
        webview.setMaxWidth(900);
        webview.setMinHeight(600);
        webview.setMaxHeight(600);
      }
      vBox.getChildren().addAll(text, webview);
      vbFin.add(vBox);
    }

    return vbFin;
  }



}
