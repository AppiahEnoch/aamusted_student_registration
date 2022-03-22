package aamustedRegister;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class userName {
String name;
String password;
String confirm;
ImageView imageView=new ImageView();

Statement st;
String qry;
ResultSet rs;

ShareData m=ShareData.getInstance();

    @FXML
    private Button bte;

    @FXML
    private PasswordField pf1;
    @FXML
    private PasswordField pf;

    @FXML
    private TextField tf1;



    @FXML
    private TextField tf;

    @FXML
    private Button bte1;


    @FXML
    private TextField tfUsername;

    @FXML
    void checkPassword(KeyEvent event) {

    }

    @FXML
    void delete(ActionEvent event) {
        tf.clear();
        pf.clear();
        tf1.clear();
        pf1.clear();
    tfUsername.clear();

    }

    @FXML
    void hidePassword(MouseEvent event) {
        tf.setVisible(false);
        pf.setVisible(true);
    }

    @FXML
    void showPassword(MouseEvent event) {
        tf.setText(pf.getText().trim());
        tf.setVisible(true);
        pf.setVisible(false);
    }

    @FXML
    void hidePasswordConfirm(MouseEvent event) {
        tf1.setVisible(false);
        pf1.setVisible(true);
    }

    @FXML
    void showPasswordConfirm(MouseEvent event) {
        tf1.setText(pf1.getText().trim());
        tf1.setVisible(true);
        pf1.setVisible(false);
    }

    @FXML
    void submit(ActionEvent event) {
     if (!setFocus()){

         if (validate()){

             Task task=new Task() {
                 @Override
                 protected Object call() throws Exception {

                     try {
                     insertUserDetails();
                     }
                     catch (Exception e){
                         e.printStackTrace();
                     }

                     return null;
                 }

             };
             ExecutorService executorService= Executors.newSingleThreadExecutor();
             executorService.execute(task);
             executorService.shutdown();



         }

     }
    }


private boolean validate(){

        if (confirm.equals(password)){
            return true;
        }
        else {
            Alert alert=new Alert(Alert.AlertType.ERROR) ;
            alert.setHeaderText("Password Mismatch.");
            alert.setContentText("Your Confirmation Do Not Match your Password.\n " +
                    "Make sure they are Same and try " +
                    "Again! ");
            Image icon = new Image(getClass().getResourceAsStream("rr.png"));
            imageView.setImage(icon);

            alert.setGraphic(imageView);
            alert.showAndWait();


        }

        return false;
}


    private boolean setFocus(){
      name=tfUsername.getText().trim();
      password=pf.getText().trim();
      confirm=pf1.getText().trim();

      if (name.isEmpty()){
          tfUsername.requestFocus();
          return true;
      }
      else if (password.isEmpty()){
          pf.requestFocus();
          return true;
      }
      else if (confirm.isEmpty()){
          pf1.requestFocus();
          return true;
      }

     return false;
    }


    public void insertUserDetails() {
       m. openConn();





        try {
            qry = "INSERT  INTO currentUser (name,password) values(" +
                    "'" +name  + "','" + password + "')";
            st = ShareData .conn .createStatement();
            st.executeUpdate(qry);
            m.closeConn();
        } catch (Exception e) {
            e.printStackTrace();
             m.closeConn();

        }
    }

}
