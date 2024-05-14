/*import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;
import java.io.File;
import java.net.MalformedURLException;

import static javafx.application.Application.launch;

public class Pantallainicio {
    public static void main(String[] args) {
        launch();
    }
public void start(Stage stage )throws Exception{
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
    BienvenidaControl p= fxmlLoader.getController();
    p.setStage(stage);
    stage.show();
}
}*/
