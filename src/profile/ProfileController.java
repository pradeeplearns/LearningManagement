package profile;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.CourseFill;
import config.LMSConstants;
import course.CourseController;
import dbconnection.SqlConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Course;
import model.UserData;

public class ProfileController implements Initializable {

//  public TreeView treeView;
  public BorderPane borderPane;
  public AnchorPane profile;
  private PreparedStatement pst;
  private ResultSet rs;

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

  public void setupWindow(String userId) {
    Label welcome = new Label();
    welcome.setText("Welcome to your LMS");
    welcome.setStyle("-fx-font-size: 20pt; -fx-font-weight: bold; -fx-text-fill: brown");

    Label name = new Label("Name : ");
    Label user = new Label("Pradeep");

    HBox hBox = new HBox(10);
    hBox.setPadding(new Insets(20, 20, 20, 20));
    hBox.getChildren().addAll(name, user);

    Label contact = new Label("Contact : ");
    Label number = new Label("9999988888");

    HBox hBox1 = new HBox(10);
    hBox1.setPadding(new Insets(20, 20, 20, 20));
    hBox1.getChildren().addAll(contact, number);

    Label email = new Label("Email : ");
    Label emailId = new Label("xyz@example.com");

    HBox hBox2 = new HBox(10);
    hBox2.setPadding(new Insets(20, 20, 20, 20));
    hBox2.getChildren().addAll(email, emailId);

    Label profession = new Label("You are the : ");
    Label role = new Label("Engineer");

    HBox hBox3 = new HBox(10);
    hBox3.setPadding(new Insets(20, 20, 20, 20));
    hBox3.getChildren().addAll(profession, role);
    TilePane tile = null;
    try {
      tile = initProfile(userId);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    VBox vBox = new VBox(10);
    vBox.setPadding(new Insets(20, 20, 20, 20));

    vBox.getChildren().addAll(welcome, hBox, hBox1, hBox2, hBox3, tile);

    HBox boxy = new HBox(10);
    boxy.setAlignment(Pos.CENTER);
    boxy.getChildren().addAll(vBox);

    profile.getChildren().addAll(boxy);
  }

  public TilePane initProfile(String userId) throws SQLException {
    SqlConnection sqlConnection = new SqlConnection();
    Connection conn = sqlConnection.connectDB();

    String sql = "Select * from " + LMSConstants.dbase + ".User where UsrName=?";

    pst = conn.prepareStatement(sql);
    pst.setString(1, userId);

    rs = pst.executeQuery();

    TilePane grid = null;

    if (rs.next()) {
      ObjectMapper obj = new ObjectMapper();
      try {
        UserData userd = obj.readValue(rs.getString(3), UserData.class);

        grid = new TilePane();
        grid.setPrefRows(2);
        grid.setPrefColumns(3);
        grid.setHgap(10);
        grid.setVgap(10);

        List<Button> buttons = new ArrayList<>();

        for (Course cs : userd.getCourses()) {
          Button btn = new Button(cs.getCourseName());
          btn.setAlignment(Pos.CENTER);
          btn.setStyle("-fx-text-fill: blue; -fx-font-weight: bold; -fx-font-size: 15pt");
          btn.setMinWidth(250);
          btn.setMinHeight(100);
          btn.maxWidth(300);
          btn.maxHeight(150);
          buttons.add(btn);
          grid.getChildren().add(btn);
        }

        buttons.get(0).setOnAction(e -> {
          loadCourse(userd.getCourses().get(0).getCourseId());
        });

      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return grid;
  }

  private void loadCourse(String courseId) {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("/course/course.fxml"));
      loader.load();

      CourseController courseController = loader.getController();
      courseController.initData(CourseFill.getCourse(courseId));

      Scene scene = new Scene(loader.getRoot());
      Stage nStage = new Stage();
      nStage.setScene(scene);
      nStage.setMaximized(true);
      nStage.setTitle("Welcome to " + courseId);
      nStage.show();
      Stage stage = (Stage) profile.getScene().getWindow();
      stage.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
