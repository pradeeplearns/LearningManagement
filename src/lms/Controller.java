package lms;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller implements Initializable {

  public TextArea txt3;
  public TextArea txt2;
  public TextArea txt1 = new TextArea("Hello fellas");
  public GridPane gridpane;
  public VBox vbox1;
  public VBox vbox2;
  public VBox vbox3;
  public Menu login;
  public MenuItem login1;
  public MenuBar menubar;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    System.out.println("LMS is getting ready...");

    vbox1.setStyle(
        "-fx-background-image: url('http://media.boingboing.net/wp-content/uploads/2015/05/lean.jpg')");
    vbox2.setStyle(
        "-fx-background-image: url('http://media.boingboing.net/wp-content/uploads/2015/05/lean.jpg')");
    vbox3.setStyle(
        "-fx-background-image: url('http://media.boingboing.net/wp-content/uploads/2015/05/lean.jpg')");

    txt1.setEditable(false);
    txt1.setText("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release");
    txt1.setWrapText(true);
    txt1.setStyle("-fx-font-size: 20pt");

    txt2.setEditable(false);
    txt2.setText(
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release");
    txt2.setWrapText(true);
    txt2.setStyle("-fx-font-size: 20pt");

    txt3.setEditable(false);
    txt3.setText("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release");
    txt3.setWrapText(true);
    txt3.setStyle("-fx-font-size: 20pt");

  }

  public void clickedLogin(ActionEvent event) throws IOException {
    System.out.println("User Clicked on Login!");

    Stage stage;
    Parent root = null;
    stage = new Stage();
    root = FXMLLoader.load(getClass().getResource("../login/login.fxml"));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.setMaximized(true);
    stage.show();
    Stage pstage = (Stage) menubar.getScene().getWindow();
    pstage.close();
  }
}
