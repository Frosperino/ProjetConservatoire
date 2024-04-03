/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package carpentier.proj.conservatoire.conservatoire;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author carpentier
 */
public class MenuController implements Initializable {

    @FXML
    private Label horaire;

    @FXML
    private Label recherche_partition;

    @FXML
    private Label saisie_partition;
    
    @FXML
    private Label errorLabel;
    
    public void goSaisiePartition() throws IOException{
        App.setRoot("saisiePartition");
    }
    
    public void goRecherchePartition() throws IOException{
        App.setRoot("recherchePartition");
    }
    
    public void verifSaisie() throws IOException{
        errorLabel.setVisible(false);
        if(App.getEleve()== null ) {
            Alert erreur = new Alert (Alert.AlertType.ERROR);
            erreur.setTitle("Erreur");
            erreur.setHeaderText("Vous devez être connecté pour accéder a cette rubrique");
            erreur.show();
        }
        else{
            goSaisiePartition();
        }
    }
    
    public void verifRecherche() throws IOException{
        if(App.getEleve()== null ) {       
            Alert erreur = new Alert (Alert.AlertType.ERROR);
            erreur.setTitle("Erreur");
            erreur.setHeaderText("Vous devez être connecté pour accéder a cette rubrique");
            erreur.show();
        }
        else{
            goRecherchePartition();
        }
        
    }
    public void invisLabel(){
        errorLabel.setVisible(false);
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
}
