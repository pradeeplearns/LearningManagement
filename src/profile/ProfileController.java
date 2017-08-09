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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
  public HBox boxy;

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

  public void setupWindow(String userId) {
    Label welcome = new Label();
    welcome.setText("Welcome to your LMS");
    welcome.setStyle("-fx-font-size: 30pt; -fx-font-weight: bold; -fx-text-fill: brown");

    Label name = new Label("Name : ");
    Label user = new Label("Pradeep");
    name.setStyle("-fx-font-size: 15pt; -fx-font-weight: bold; -fx-text-fill: black");
    user.setStyle("-fx-font-size: 15pt; -fx-font-weight: bold; -fx-text-fill: black");

    Label contact = new Label("Contact : ");
    Label number = new Label("9999988888");
    contact.setStyle("-fx-font-size: 15pt; -fx-font-weight: bold; -fx-text-fill: black");
    number.setStyle("-fx-font-size: 15pt; -fx-font-weight: bold; -fx-text-fill: black");


    Label email = new Label("Email : ");
    Label emailId = new Label("xyz@example.com");
    email.setStyle("-fx-font-size: 15pt; -fx-font-weight: bold; -fx-text-fill: black");
    emailId.setStyle("-fx-font-size: 15pt; -fx-font-weight: bold; -fx-text-fill: black");

    Label profession = new Label("You are the : ");
    Label role = new Label("Engineer");
    profession.setStyle("-fx-font-size: 15pt; -fx-font-weight: bold; -fx-text-fill: black");
    role.setStyle("-fx-font-size: 15pt; -fx-font-weight: bold; -fx-text-fill: black");

    GridPane grid = new GridPane();
    grid.add(name, 0, 0);
    grid.add(user, 1, 0);
    grid.add(contact, 0, 1);
    grid.add(number, 1, 1);
    grid.add(email, 0, 2);
    grid.add(emailId, 1, 2);
    grid.add(profession, 0, 3);
    grid.add(role, 1, 3);

    grid.setHgap(10);
    grid.setVgap(10);
    grid.minWidth(600);
    grid.minHeight(400);

    ImageView imageView = new ImageView(new Image("/image/man.png"));
    imageView.minWidth(400);
    imageView.minHeight(400);

    HBox userD = new HBox(10);
    userD.setPadding(new Insets(20, 20, 20, 20));
    userD.getChildren().addAll(grid, imageView);

    Label courseTitle = new Label();
    courseTitle.setText("Please Select any Course for Learning!");
    courseTitle.setStyle("-fx-font-size: 20pt; -fx-font-weight: bold; -fx-text-fill: brown");

    TilePane tile = null;
    try {
      tile = initProfile(userId);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    VBox vBox = new VBox(10);
    vBox.setPadding(new Insets(20, 20, 20, 20));

    vBox.getChildren().addAll(welcome, userD, courseTitle, tile);
    vBox.setAlignment(Pos.CENTER);
    vBox.minWidth(1000);

    boxy.setAlignment(Pos.CENTER);
    boxy.getChildren().addAll(vBox);
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
