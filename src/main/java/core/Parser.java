package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
/**
 * This class is thread safe.
 */
public class Parser {
	public static PrintWriter stdout = new PrintWriter(
		    new OutputStreamWriter(System.out, StandardCharsets.UTF_8),
		    true);
  private static File file;
  
  public synchronized static void setFile(File f) {file = f;}
  
  public synchronized File getFile() {return file;                                                                                                                        }
  
  public static String getContent() throws IOException {
    FileInputStream i = new FileInputStream(file);
    String output = "";
    int data;
    while ((data = i.read()) > 0) {output += (char) data;}
    return output;
  }
  
  public static String getContentWithoutUnicode() throws IOException {
    FileInputStream i = new FileInputStream(file);
    String output = "";
    int data;
    while ((data = i.read()) > 0) {
      if (data < 0x80) {output += (char) data;}}
    return output;
  }
  
  public void saveContent(String content) throws IOException {
    FileOutputStream o = new FileOutputStream(file);
    for (int i = 0; i < content.length(); i += 1) {
      o.write(content.charAt(i));}
  }
  public static void main(String[] args) throws IOException {
File dima=new File("./src/main/resources/d.txt");
	setFile(dima);
	PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out, "UTF-8"), true);
	writer.println("\u001B[1m \u2713 Check mark showed \u001B[1m");
	writer.println("გთხოვთ ახლავე გაიაროთ რეგისტრაცია Unicode-ის მეათე საერთაშორისო");
	writer.println(getContent());
	System.out.println();
	System.out.println(getContentWithoutUnicode());
}
}
