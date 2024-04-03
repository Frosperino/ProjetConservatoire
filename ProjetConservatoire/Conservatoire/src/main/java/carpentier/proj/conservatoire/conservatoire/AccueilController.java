/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package carpentier.proj.conservatoire.conservatoire;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import modele.DAO;
import modele.Eleve;

/**
 * FXML Controller class
 *
 * @author carpentier
 */
public class AccueilController implements Initializable {
    
    @FXML
    private Label cnx;

    @FXML
    private TextField login;
    
    @FXML
    private TextField mdp;
    
    @FXML
    private Label errorLabel;
    
    @FXML
    private Label lblNom;

    @FXML
    private Label lblPrenom;


    /**
     * Méthode qui permet la connexion dans l'application
     * @throws SQLException
     * @throws IOException 
     */
    @FXML
    private void login() throws SQLException, IOException {
        String loginString = login.getText();
        String mdpString = mdp.getText();
//        String loginString = "valca";
//        String mdpString = "pw";
//        login.setText(loginString);
//        mdp.setText(mdpString);

        if(loginString.isEmpty() || mdpString.isEmpty()) {
            Alert erreur = new Alert (Alert.AlertType.ERROR);
            erreur.setTitle("Erreur");
            erreur.setHeaderText("Connexion échoué car des champs sont vides");
            erreur.show();
            return;
        }
        CallableStatement callableStatement = DAO.getConnection().prepareCall("call getLogin(?);");
        callableStatement.setString(1, login.getText());
        ResultSet resultSet = callableStatement.executeQuery();
        
        CallableStatement cs = DAO.getConnection().prepareCall("call ChiffrerMotDePasse(?);");
        cs.setString(1, mdpString);
        ResultSet rs = cs.executeQuery();
        rs.next();
        
        
        if(resultSet.next() && rs.getString(1).equals(resultSet.getString("ELEMDP"))){
//            MenuController.invisLabel();
            App.setEleve(
                    new Eleve(
                            resultSet.getInt("ELENUM"),
                            resultSet.getInt("DISNUM"),
                            resultSet.getString("ELENOM"),
                            resultSet.getString("ELEPRENOM"),
                            resultSet.getInt("ELECYCLE"),
                            resultSet.getInt("ELEANNEECYCLE"),
                            resultSet.getString("ELELOGIN"),
                            resultSet.getString("ELEMDP")));
            login.setText("");
            mdp.setText("");
            lblNom.setText(App.getEleve().getElenom());
            lblPrenom.setText(App.getEleve().getEleprenom());
            Alert erreur = new Alert (Alert.AlertType.INFORMATION);
            erreur.setTitle("Succès");
            erreur.setHeaderText("Connexion réussi");
            erreur.show();
        }
        else {
            Alert erreur = new Alert (Alert.AlertType.ERROR);
            erreur.setTitle("Erreur");
            erreur.setHeaderText("Le mot de passe ou/et login est faux");
            erreur.show();
        }
        
                
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
