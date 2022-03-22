package aamustedRegister;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class mainLogin {
    ShareData m=ShareData.getInstance();
    @FXML
    private Text txtName;

    @FXML
    private PasswordField pf;

    @FXML
    private TextField tf;

    @FXML
    private Button bte;

    @FXML
    void checkPassword(KeyEvent event) {
        System.out.println("check");
    }

    @FXML
    void delete(ActionEvent event) {
      tf.clear();
      pf.clear();
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
    void submit(ActionEvent event) {
        System.out.println("enter");
    }

    @FXML
   public void initialize(){
        txtName.setText(" ENOCH APPIAH");
    }
}
