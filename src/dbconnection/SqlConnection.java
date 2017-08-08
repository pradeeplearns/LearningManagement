package dbconnection;

import com.fasterxml.jackson.core.JsonProcessingException;
import config.LMSConstants;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import jdk.nashorn.internal.parser.JSONParser;
import model.Course;
import model.UserData;

import com.fasterxml.jackson.databind.ObjectMapper;


public class SqlConnection {
  // JDBC driver name and database URL
  final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  final String DB_URL = "jdbc:mysql://localhost:3306/";

  //  Database credentials
  final String USER = "root";
  final String PASS = "root";

  public Connection conn;
  PreparedStatement pst;

  public Connection connectDB() {
    try {
      //Class.forName(JDBC_DRIVER);

      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      System.out.println("Creating statement...");

      return conn;
    } catch ( SQLException e) {
      System.out.println(e);
    }
    return conn;
  }

  public void createDataBase() {
    SqlConnection con = new SqlConnection();
    try {
      pst = con.connectDB().prepareStatement("create database if not exists "+ LMSConstants.dbase+" DEFAULT CHARACTER SET utf8 \n"
                                              + "  DEFAULT COLLATE utf8_general_ci");
      pst.execute();

      pst = con.connectDB().prepareStatement("CREATE TABLE if not exists "+LMSConstants.dbase+".`Login` (\n"
                                             + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                                             + "  `UsrName` VARCHAR(40) NOT NULL,\n"
                                             + "  `Password` VARCHAR(20) NOT NULL,\n"
                                             + "  PRIMARY KEY (`UsrName`),\n"
                                             + "  UNIQUE INDEX `Id` (`Id` ASC));");
      pst.execute();

      pst = con.connectDB().prepareStatement("CREATE TABLE if not exists "+LMSConstants.dbase+".`User` (\n"
                                             + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                                             + "  `UsrName` VARCHAR(40) NOT NULL,\n"
                                             + "  `UserData` text,\n"
                                             + "  PRIMARY KEY (`UsrName`),\n"
                                             + "  UNIQUE INDEX `Id` (`Id` ASC));");
      pst.execute();

      pst = con.connectDB().prepareStatement("CREATE TABLE if not exists "+LMSConstants.dbase+".`Course` (\n"
                                             + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                                             + "  `CourseId` VARCHAR(20) NOT NULL,\n"
                                             + "  `CourseData` TEXT NOT NULL,\n"
                                             + "  PRIMARY KEY (`CourseId`),\n"
                                             + "  UNIQUE INDEX `Id` (`Id` ASC));");
      pst.execute();

      //Adding data
      setData();

      System.out.println("Create Database Successfully");

    } catch (SQLException ex) {
      System.err.println(ex);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }

  //Dummy data for User table
  private void setData() throws SQLException, JsonProcessingException {
    UserData userData = new UserData();
    userData.setName("Example name");
    userData.setProfession("SDE");
    userData.setEmail("xyz@gmail.com");
    userData.setContactNo("9986998699");

    Course course = new Course();
    course.setCourseId("java101");
    course.setCourseName("Java");

    Course course1 = new Course();
    course1.setCourseId("html101");
    course1.setCourseName("HTML");

    Course course2 = new Course();
    course2.setCourseId("java102");
    course2.setCourseName("Advanced Java");

    Course course3 = new Course();
    course3.setCourseId("js101");
    course3.setCourseName("JavaScript");

    Course course4 = new Course();
    course4.setCourseId("digm101");
    course4.setCourseName("Digital Marketing");

    List<Course> courses = new ArrayList<>();
    courses.add(course);
    courses.add(course1);
    courses.add(course2);
    courses.add(course3);
    courses.add(course4);

    userData.setCourses(courses);

    ObjectMapper objectMapper = new ObjectMapper();
    String jsonInString = objectMapper.writeValueAsString(userData);

    String name = "pradex";
    System.out.println(jsonInString);

    Connection conn = connectDB();
    PreparedStatement pst = conn.prepareStatement("INSERT INTO " + LMSConstants.dbase + ".User VALUES (?, ?, ?)");
    pst.setInt(1, 101);
    pst.setString(2, name);
    pst.setString(3, jsonInString);
    pst.execute();
  }

}
