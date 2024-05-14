/*import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class Bienevnidacontrol  implements Initializable {
     void click(ActionEvent event )throws Exception{
        Stage stage=new Stage();
         FXMLLoader fxmlLoader=new FXMLLoader();
        File fichero= new File();
        URL url= null;
        try{
            url=fichero.toURL();
        }catch (MalformedURLException ex){
            throw new RuntimeException(ex);
        }
        fxmlLoader.setLocation(url);
        Scene scene=new Scene(fxmlLoader.load(),500,700);
        stage.setScene(scene);
        Pantallainiciocontrol p= fxmlLoader.getController();
        p.setStage(stage);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }
    @FXML
    void initialize(){
    }
    public void setStage(Stage stage){
         
    }
    
}*/
