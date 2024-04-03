/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package carpentier.proj.conservatoire.conservatoire;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import modele.DAO;
import modele.Partitions;

/**
 * FXML Controller class
 *
 * @author carpentier
 */
public class SaisiePartitionController implements Initializable {
    @FXML
    private Label ajouterPart;

    @FXML
    private Label creerPart;

    @FXML
    private ListView<Partitions> listePart;

    @FXML
    private TextField nomAuteur;

    @FXML
    private TextField nomPart;
    
    @FXML
    private Label lblNomAut;
    @FXML
    private Label lblNomPart;
    
    @FXML 
    private Button btnCreerPart;
    
    @FXML
    private Label errorLabel;
    
    @FXML
    private Label lblNom1;

    @FXML
    private Label lblPrenom1;
    
    

    /**
     * Affiche les labels et TextField pour créer une partition
     */
    public void rentrerPartition(){
        lblNomPart.setVisible(true);
        nomAuteur.setVisible(true);
        nomPart.setVisible(true);
        lblNomAut.setVisible(true);
        btnCreerPart.setVisible(true);
        
        
    }
    /**
     * Permet d'obtenir le l'id le plus haut correspondant a une partition
     * @return
     * @throws SQLException 
     */
    public int getMaxParNum() throws SQLException{
        String requete = "select MAX(PARNUM) from PARTITIONS ;";
        ResultSet rs = DAO.getStatement().executeQuery(requete);
        rs.next();
        return rs.getInt(1) + 1;
    }
    /**
     * Ajoute une partition dans la bdd 
     * @throws SQLException 
     */
    public void creerPartition() throws SQLException{
        errorLabel.setVisible(false);
        if(nomPart.getText().isEmpty() || nomAuteur.getText().isEmpty()){
            Alert erreur = new Alert (Alert.AlertType.ERROR);
            erreur.setTitle("Erreur");
            erreur.setHeaderText("Le champ auteur et/ou nom partition est vide");
            erreur.show();
            return;
        }
        String requete = "INSERT INTO PARTITIONS(PARNUM, PARNOM, PARAUTEUR) VALUES("+ getMaxParNum() + ",?,?);";
        PreparedStatement cs = DAO.getConnection().prepareStatement(requete);
        cs.setString(1,nomPart.getText());
        cs.setString(2, nomAuteur.getText());
        String req = "SELECT PARNOM, PARAUTEUR FROM PARTITIONS WHERE PARNOM ='" + nomPart.getText() + "' AND PARAUTEUR = '"+nomAuteur.getText()+"' ;";
        ResultSet rs = DAO.getStatement().executeQuery(req);
        if(rs.next() == false){
            cs.executeUpdate();
            listePart.getItems().add(new Partitions(getMaxParNum() - 1, nomPart.getText(), nomAuteur.getText()));
        }
        else{
            errorLabel.setVisible(true);
        }
        
        
    }
    /**
     * Permet d'obtenir le numero de page maximum selon l'eleve connecté
     * @return
     * @throws SQLException 
     */
    public int getMaxPage() throws SQLException{
        String requete = "select MAX(NUMEROPAGECLASSEUR) from PARTITIONELEVE WHERE ELENUM = "+ App.getEleve().getElenum()+" ;";
        ResultSet rs = DAO.getStatement().executeQuery(requete);
        if(rs.next()){
            return rs.getInt(1) + 1;
        }
        else{
            return 1;
        }
        
    }
    /**
     * Ajoute au classeur la partition sélectionné dans la ListView
     * @throws SQLException 
     */
    public void ajouterClasseur() throws SQLException{
        if(listePart.getSelectionModel().getSelectedItem() == null){
            Alert erreur = new Alert (Alert.AlertType.ERROR);
            erreur.setTitle("Erreur");
            erreur.setHeaderText("Aucune partition selectionné");
            erreur.show(); 
            return;
        }
        
        String req = "SELECT ELENUM, PARNUM FROM PARTITIONELEVE WHERE ELENUM = ? AND PARNUM = ? ; ";
        PreparedStatement psv = DAO.getConnection().prepareStatement(req);
        psv.setInt(1,App.getEleve().getElenum());
        psv.setInt(2, listePart.getSelectionModel().getSelectedItem().getParnum());
        ResultSet rs = psv.executeQuery();
        
        if(rs.next()){
            Alert erreur = new Alert (Alert.AlertType.ERROR);
            erreur.setTitle("Erreur");
            erreur.setHeaderText("Partition déjà dans le classeur");
            erreur.show(); 
            return;
        }
      
        String requete = "INSERT INTO PARTITIONELEVE(ELENUM, PARNUM, NUMEROPAGECLASSEUR) VALUES(" + App.getEleve().getElenum()+ ",?,?);";
        PreparedStatement ps = DAO.getConnection().prepareStatement(requete);
        ps.setInt(1, listePart.getSelectionModel().getSelectedItem().getParnum());
        ps.setInt(2, getMaxPage());
        ps.executeUpdate();
        Alert succes = new Alert (Alert.AlertType.INFORMATION);
        succes.setTitle("Succès");
        succes.setHeaderText("La partition a été ajouté au classeur");
        succes.show();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            String req = "SELECT ELENOM, ELEPRENOM FROM ELEVE WHERE ELENUM = ?";
            PreparedStatement ps = DAO.getConnection().prepareStatement(req);
            ps.setInt(1, App.getEleve().getElenum());
            ResultSet rss = ps.executeQuery();
            rss.next();
            lblNom1.setText(rss.getString("ELENOM"));
            lblPrenom1.setText(rss.getString("ELEPRENOM"));
            String requete = "select * from PARTITIONS;";
            ResultSet rs = DAO.getStatement().executeQuery(requete);
            while(rs.next()){
                listePart.getItems().add(new Partitions(rs.getInt("PARNUM"),rs.getString("PARNOM"),rs.getString("PARAUTEUR")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SaisiePartitionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
