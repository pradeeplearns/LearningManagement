package config;

import java.util.ArrayList;
import java.util.List;
import model.Content;
import model.CourseData;

public class CourseFill {
  private static final String VID_URL = "https://www.youtube.com/embed/";

  public static CourseData getCourse(String courseID) {
    switch (courseID) {
      case "java101" :
        return java101();
      case "html101" :
        return html101();
      case "java102" :
        return java102();
      case "digm101" :
        return digm101();
      case "js101" :
        return js101();
      default:
        break;
    }

    return null;
  }

  public static CourseData java101() {
    CourseData courseData = new CourseData();

    courseData.setCourseId("java101");
    courseData.setTitle("Java Programming for Beginners");
    courseData.setMetaData("Java is a general-purpose computer programming language that is concurrent,"
                           + " class-based, object-oriented, and specifically designed to have as few "
                           + "implementation dependencies as possible.");

    courseData.setContents(setJavaContent());

    return courseData;
  }

  private static List<Content> setJavaContent() {
    List<Content> contents = new ArrayList<>();
    Content content = new Content();
    content.setTitle("Java Overview");
    List<String> paras = new ArrayList<>();
    paras.add("Java is a high-level programming language originally developed by Sun Microsystems and released in 1995. Java runs on a variety of platforms, such as Windows, Mac OS, and the various versions of UNIX. This tutorial gives a complete understanding of Java.\n"
              + "\n"
              + "This reference will take you through simple and practical approaches while learning Java Programming language.");

    paras.add("Before you start practicing various types of examples given in this reference, we assume that you are already aware about computer programs and computer programming languages.\n"
              + "\n");

    paras.add("Java programming language was originally developed by Sun Microsystems which was initiated by James Gosling and released in 1995 as core component of Sun Microsystems' Java platform");

    content.setParagraph(paras);

    List<String> vids = new ArrayList<>();
    vids.add(VID_URL + "Hl-zzrqQoSE");
    vids.add(VID_URL + "5u8rFbpdvds");

    content.setVidUrl(vids);

    contents.add(content);
    contents.add(content);
    contents.add(content);
    contents.add(content);
    contents.add(content);

    return contents;
  }

  public static CourseData html101() {
    CourseData courseData = new CourseData();

    courseData.setCourseId("html101");
    courseData.setTitle("HTML for Beginners");
    courseData.setMetaData("Java is a general-purpose computer programming language that is concurrent,"
                           + " class-based, object-oriented, and specifically designed to have as few "
                           + "implementation dependencies as possible.");

    List<Content> contents = new ArrayList<>();
    Content content = new Content();
    content.setTitle("Java Overview");
    List<String> paras = new ArrayList<>();
    paras.add("Java is a high-level programming language originally developed by Sun Microsystems and released in 1995. Java runs on a variety of platforms, such as Windows, Mac OS, and the various versions of UNIX. This tutorial gives a complete understanding of Java.\n"
              + "\n"
              + "This reference will take you through simple and practical approaches while learning Java Programming language.");

    paras.add("Before you start practicing various types of examples given in this reference, we assume that you are already aware about computer programs and computer programming languages.\n"
              + "\n");

    paras.add("Java programming language was originally developed by Sun Microsystems which was initiated by James Gosling and released in 1995 as core component of Sun Microsystems' Java platform");

    content.setParagraph(paras);

    List<String> vids = new ArrayList<>();
    vids.add(VID_URL + "Hl-zzrqQoSE");
    vids.add(VID_URL + "5u8rFbpdvds");

    content.setVidUrl(vids);

    contents.add(content);

    courseData.setContents(contents);

    return courseData;
  }

  public static CourseData java102() {
    CourseData courseData = new CourseData();

    courseData.setCourseId("java102");
    courseData.setTitle("Java Programming for Professionals");
    courseData.setMetaData("Java is a general-purpose computer programming language that is concurrent,"
                           + " class-based, object-oriented, and specifically designed to have as few "
                           + "implementation dependencies as possible.");

    List<Content> contents = new ArrayList<>();
    Content content = new Content();
    content.setTitle("Java Overview");
    List<String> paras = new ArrayList<>();
    paras.add("Java is a high-level programming language originally developed by Sun Microsystems and released in 1995. Java runs on a variety of platforms, such as Windows, Mac OS, and the various versions of UNIX. This tutorial gives a complete understanding of Java.\n"
              + "\n"
              + "This reference will take you through simple and practical approaches while learning Java Programming language.");

    paras.add("Before you start practicing various types of examples given in this reference, we assume that you are already aware about computer programs and computer programming languages.\n"
              + "\n");

    paras.add("Java programming language was originally developed by Sun Microsystems which was initiated by James Gosling and released in 1995 as core component of Sun Microsystems' Java platform");

    content.setParagraph(paras);

    List<String> vids = new ArrayList<>();
    vids.add(VID_URL + "Hl-zzrqQoSE");
    vids.add(VID_URL + "5u8rFbpdvds");

    content.setVidUrl(vids);

    contents.add(content);

    courseData.setContents(contents);

    return courseData;
  }

  public static CourseData digm101() {
    CourseData courseData = new CourseData();

    courseData.setCourseId("digm101");
    courseData.setTitle("Digital Marketing");
    courseData.setMetaData("Java is a general-purpose computer programming language that is concurrent,"
                           + " class-based, object-oriented, and specifically designed to have as few "
                           + "implementation dependencies as possible.");

    List<Content> contents = new ArrayList<>();
    Content content = new Content();
    content.setTitle("Java Overview");
    List<String> paras = new ArrayList<>();
    paras.add("Java is a high-level programming language originally developed by Sun Microsystems and released in 1995. Java runs on a variety of platforms, such as Windows, Mac OS, and the various versions of UNIX. This tutorial gives a complete understanding of Java.\n"
              + "\n"
              + "This reference will take you through simple and practical approaches while learning Java Programming language.");

    paras.add("Before you start practicing various types of examples given in this reference, we assume that you are already aware about computer programs and computer programming languages.\n"
              + "\n");

    paras.add("Java programming language was originally developed by Sun Microsystems which was initiated by James Gosling and released in 1995 as core component of Sun Microsystems' Java platform");

    content.setParagraph(paras);

    List<String> vids = new ArrayList<>();
    vids.add(VID_URL + "Hl-zzrqQoSE");
    vids.add(VID_URL + "5u8rFbpdvds");

    content.setVidUrl(vids);

    contents.add(content);

    courseData.setContents(contents);

    return courseData;
  }

  public static CourseData js101() {
    CourseData courseData = new CourseData();

    courseData.setCourseId("js101");
    courseData.setTitle("JavaScript for Beginners");
    courseData.setMetaData("Java is a general-purpose computer programming language that is concurrent,"
                           + " class-based, object-oriented, and specifically designed to have as few "
                           + "implementation dependencies as possible.");

    List<Content> contents = new ArrayList<>();
    Content content = new Content();
    content.setTitle("Java Overview");
    List<String> paras = new ArrayList<>();
    paras.add("Java is a high-level programming language originally developed by Sun Microsystems and released in 1995. Java runs on a variety of platforms, such as Windows, Mac OS, and the various versions of UNIX. This tutorial gives a complete understanding of Java.\n"
              + "\n"
              + "This reference will take you through simple and practical approaches while learning Java Programming language.");

    paras.add("Before you start practicing various types of examples given in this reference, we assume that you are already aware about computer programs and computer programming languages.\n"
              + "\n");

    paras.add("Java programming language was originally developed by Sun Microsystems which was initiated by James Gosling and released in 1995 as core component of Sun Microsystems' Java platform");

    content.setParagraph(paras);

    List<String> vids = new ArrayList<>();
    vids.add(VID_URL + "Hl-zzrqQoSE");
    vids.add(VID_URL + "5u8rFbpdvds");

    content.setVidUrl(vids);

    contents.add(content);

    courseData.setContents(contents);

    return courseData;
  }
}
