package org.example.trabajo;

import Estructuras.ListaEnlazed;
import Individuo.Individuo;
import Recursos.Recursos;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import Recursos.Agua;
public class PantallaFinalControler implements Initializable {
    @FXML
    private Button cerrar;
    private ImageView arbol;
    private Stage stage;
    private Agua agua;
    TableroController tablero;

    public void setStage(Stage stage) {
        this.stage= stage;

    }
    public void pantallafinal() {
        int longevidad = 0;
        int mutaciones = 0;
        for (int i = 0; i < tablero.getCeldas().getNumeroElementos(); i++) {
            if (!tablero.getCeldas().getElemento(i).getData().getIndividuoListaEnlazed().isVacia())
                for (int k = tablero.getCeldas().getElemento(i).getData().getIndividuoListaEnlazed().getNumeroElementos() - 1; k > -1; k--) {

                    if (longevidad < tablero.getCeldas().getElemento(i).getData().getIndividuoListaEnlazed().getElemento(k).getData().getMuerte()) {
                        longevidad = tablero.getCeldas().getElemento(i).getData().getIndividuoListaEnlazed().getElemento(k).getData().getMuerte();
                    }
                    if (mutaciones < tablero.getCeldas().getElemento(i).getData().getIndividuoListaEnlazed().getElemento(k).getData().getClonacion()) {
                        mutaciones = tablero.getCeldas().getElemento(i).getData().getIndividuoListaEnlazed().getElemento(k).getData().getClonacion();
                    }
                }
        }

    }
    private TreeItem<Integer> creaTreesitems( Individuo individuo){
        TreeItem<Integer> treeItem=new TreeItem<>(individuo.getIdentificador());
        return treeItem;
    }
    @FXML
    public void arbolWiner(Individuo individuo){
        Individuo rotativo=individuo;
        Stage primarystage=new Stage();
        ListaEnlazed<Individuo> ancestros= new ListaEnlazed<>();
        while(rotativo.getGeneracion().getMadre()!=null&&rotativo.getGeneracion().getPadre()!=null){
            ancestros.add(rotativo.getGeneracion().getMadre());
            ancestros.add(rotativo.getGeneracion().getPadre());
        }


        for(int i=0; i<ancestros.getNumeroElementos(); i++){
            if(ancestros.getElemento(i).getData().getIdentificador()!=individuo.getIdentificador()){
                TreeItem<Integer> treeItem1= new TreeItem<>(ancestros.getElemento(i).getData().getIdentificador());
                treeItem1.getChildren().add(treeItem1);
            }
        }
        while(individuo.getGeneracion().getPadre()!=null && individuo.getGeneracion().getMadre()!=null){
            ancestros.add(individuo.getGeneracion().getPadre());
            ancestros.add(individuo.getGeneracion().getMadre());

        }
        TreeItem<Integer> rootItem= creaTreesitems(individuo);
        TreeView<Integer> treeView= new TreeView<>(rootItem);

        rootItem.setExpanded(true);
        VBox vbox = new VBox(treeView);
        Scene scene= new Scene(vbox,300,250);
        primarystage.setTitle("Arbol genealogico");
        primarystage.setScene(scene);
        primarystage.show();

    }
    @FXML
    protected void onCerrarclick(){
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
