/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package carpentier.proj.conservatoire.conservatoire;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import modele.DAO;
import modele.PartitionEleve;

/**
 * FXML Controller class
 *
 * @author carpentier
 */
public class RecherchePartitionController implements Initializable {
    @FXML
    private ListView<PartitionEleve> listePart;

    @FXML
    private TextField rentrezAut;
    
    private ArrayList<PartitionEleve> mesPartitions2;
    @FXML
    private Label lblNom2;

    @FXML
    private Label lblPrenom2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            lblNom2.setText(App.getEleve().getElenom());
            lblPrenom2.setText(App.getEleve().getEleprenom());
            String requete = "SELECT PARTITIONELEVE.PARNUM, NUMEROPAGECLASSEUR, PARNOM, PARAUTEUR FROM PARTITIONELEVE INNER JOIN PARTITIONS ON PARTITIONELEVE.PARNUM = PARTITIONS.PARNUM WHERE ELENUM = "+ App.getEleve().getElenum()+" ORDER BY NUMEROPAGECLASSEUR;";
            ResultSet rs = DAO.getStatement().executeQuery(requete);
            while(rs.next()){
                listePart.getItems().add(new PartitionEleve(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4)));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecherchePartitionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mesPartitions2 = new ArrayList<>(listePart.getItems());
    }
    
    /**
     * Permet de rechercher un auteur selon le texte rentré dans le TextField rentrezAut
     * Cette méthode est utilisée a chaque lettre tapée dans le TextField
     * @throws SQLException 
     */
    public void recherche() throws SQLException{
        String auteur = rentrezAut.getText();
        ArrayList<PartitionEleve> mesAuteurs = new ArrayList<>();
        for(PartitionEleve p : mesPartitions2){
            
            if(p.getParauteur().toLowerCase().contains(auteur.toLowerCase())){
                mesAuteurs.add(p);                    
            }
            listePart.getItems().clear();
            listePart.getItems().addAll(mesAuteurs);
        }
            
    }
        

        
}
    
    

