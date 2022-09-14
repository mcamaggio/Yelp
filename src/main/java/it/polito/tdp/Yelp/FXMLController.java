/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.Yelp;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.Yelp.model.Model;
import it.polito.tdp.Yelp.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cmbAnno"
    private ComboBox<Integer> cmbAnno; // Value injected by FXMLLoader

    @FXML // fx:id="cmbUtente"
    private ComboBox<User> cmbUtente; // Value injected by FXMLLoader

    @FXML // fx:id="txtN"
    private TextField txtN; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="txtX1"
    private TextField txtX1; // Value injected by FXMLLoader

    @FXML // fx:id="txtX2"
    private TextField txtX2; // Value injected by FXMLLoader

    @FXML
    void btnCreaGrafo(ActionEvent event) {
    	
    	txtResult.clear();
    	
    	String minReviewS = txtN.getText();
    	try {
    		int minReview = Integer.parseInt(minReviewS);
    		Integer anno = cmbAnno.getValue();
    		
    		if(anno == null) {
    			txtResult.setText("Devi selezionare un anno valido!\n");
        		return;
    		}
    		
    		String msg = model.creaGrafo(minReview, anno);
    		txtResult.setText(msg);
    		cmbUtente.getItems().clear();
    		cmbUtente.getItems().addAll(model.getUser());
    		
    	} catch(NumberFormatException e) {
    		txtResult.setText("Devi inserire un numero 'n' valido!\n");
    		return;
    	}

    }

    @FXML
    void btnSimula(ActionEvent event) {

    }

    @FXML
    void btnUtenteSimile(ActionEvent event) {
    	
    	User u = cmbUtente.getValue();
    	
    	if(u == null) {
			txtResult.setText("Devi selezionare un utente valido!\n");
    		return;
		}
    	
    	List<User> vicini = model.utentiPiuSimili(u);
    	for(User us : vicini) {
    		txtResult.appendText(us.toString()+"	Grado: "+model.getPesoArco()+"\n");
    	}
    	

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cmbAnno != null : "fx:id=\"cmbAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbUtente != null : "fx:id=\"cmbUtente\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtN != null : "fx:id=\"txtN\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtX1 != null : "fx:id=\"txtX1\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtX2 != null : "fx:id=\"txtX2\" was not injected: check your FXML file 'Scene.fxml'.";
        
        for(int anno = 2005; anno <= 2013; anno++) {
        	cmbAnno.getItems().add(anno);
        }

    }

	public void setModel(Model model) {
		this.model = model;
		
	}

}
