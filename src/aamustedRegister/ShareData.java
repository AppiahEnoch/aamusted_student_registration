package aamustedRegister;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import org.h2.tools.Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShareData {
    static   Path  pathTodesktop=null;
    private static ShareData instance;

    public int data=0;

    private ShareData(){

    }

    synchronized public static  ShareData getInstance(){
        if (instance==null){
            instance=new ShareData();
        }
        return instance;
    }

    static Connection conn=null;
    Statement st;
    String qry;

    public  boolean H2con() {

        String url2 = "jdbc:h2:~/aamustedDB";  // embedded
        String smartSales = "Smart";
        //    String url2 = "jdbc:h2:./Database/mydb if not exists,root,root";  // embedded
        //String url2 = "jdbc:h2:tcp://localhost/Database/mydb.db, root,root";
        try {

            conn = DriverManager.getConnection("jdbc:h2:~/aamustedDB","aamusted","aamusted");
            st = conn.createStatement();


            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

  public  void openMainConn(){
        try {
            if ( conn==null){
                H2con();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public  void openConn(){
        try {
            if ( conn.isClosed()){
                H2con();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public void closeConn(){
        try {
            conn.close();
        }
        catch (Exception e){

        }
    }




    public boolean createTables(){




        String user = "CREATE TABLE IF NOT EXISTS " +
                "currentUser (name varchar(255),password varchar(255))";

        String grades= "CREATE TABLE IF NOT EXISTS " +
                "grade(ctr int, grade varchar(5), credit varchar(5)" +
                ", point varchar(5),cp  varchar(5), remark varchar(30))";


        String gpa= "CREATE TABLE IF NOT EXISTS " +
                "tbGPA (userGPA varchar(10), totalCredit  varchar(30), totalPoints varchar(100)" +
                ", totalCP varchar(100), UserClass varchar(30), time timestamp  DEFAULT CURRENT_TIMESTAMP)";

        String userView = "CREATE or replace view userData as select * from currentUser,grade,tbGPA";


        Statement st;

        try {

      openConn();
            st=conn.createStatement();
            st.execute(user);
           // st.execute(grades);
          //  st.execute(gpa);
         //   st.execute(userView);



             closeConn();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("DATABASE ERROR:"+e.getMessage());
            alert.showAndWait();
            try {
                conn.close();
            }
            catch (Exception ee){

            }
        }




        return false;
    }








}
