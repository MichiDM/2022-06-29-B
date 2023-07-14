/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.itunes;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.itunes.model.Adiacenza;
import it.polito.tdp.itunes.model.Album;
import it.polito.tdp.itunes.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnAdiacenze"
    private Button btnAdiacenze; // Value injected by FXMLLoader

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnPercorso"
    private Button btnPercorso; // Value injected by FXMLLoader

    @FXML // fx:id="cmbA1"
    private ComboBox<Album> cmbA1; // Value injected by FXMLLoader

    @FXML // fx:id="cmbA2"
    private ComboBox<Album> cmbA2; // Value injected by FXMLLoader

    @FXML // fx:id="txtN"
    private TextField txtN; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="txtX"
    private TextField txtX; // Value injected by FXMLLoader

    @FXML
    void doCalcolaAdiacenze(ActionEvent event) {
    		
    	// controlli errore comboBox
    	Album album = this.cmbA1.getValue();
    	if (album==null) {
    	    this.txtResult.setText("Please select a album");
    	    return;
    	}
    	
    	List<Adiacenza> adiacenze = this.model.analizzaAdiacenze(album);
    	Collections.sort(adiacenze);
    	
    	for (Adiacenza a : adiacenze) {
    		
    		this.txtResult.appendText("\n"+ a.getA().getTitle() + ", " + String.format("%.2f",a.getBilancio()/1000) +"\n");
    		
    		
    	}
    	
    }

    @FXML
    void doCalcolaPercorso(ActionEvent event) {
    	
    	// controlli errore comboBox
    	Album album1 = this.cmbA1.getValue();
    	if (album1==null) {
    	    this.txtResult.setText("Please select a album");
    	    return;
    	}
    	
    	Album album2 = this.cmbA2.getValue();
    	if (album2==null) {
    	    this.txtResult.setText("Please select a album");
    	    return;
    	}
    	
      	// controlli errore numero intero
    	int x =0;
    	try {
    	    x = Integer.parseInt( this.txtN.getText() );
    	} catch(NumberFormatException e) {
    	    this.txtResult.setText("Invalid argument. x must be a integer!");
    	    return;
    	}
    	// controllo che il numero non sia negativo
    	if(x<0) {
    	  this.txtResult.setText("x must be a nonnegative integer.");
    	  return;
    	}
    	
    	
    	
    	
    	
    	
    	
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	
    	// controlli errore numero intero
    	int n =0;
    	try {
    	    n = Integer.parseInt( this.txtN.getText() );
    	} catch(NumberFormatException e) {
    	    this.txtResult.setText("Invalid argument. N must be a integer!");
    	    return;
    	}
    	// controllo che il numero non sia negativo
    	if(n<0) {
    	  this.txtResult.setText("N must be a nonnegative integer.");
    	  return;
    	}
    	
    	
    	// creo il grafo
    	this.model.creaGrafo(n);

    	this.txtResult.setText("Grafo creato con " + this.model.getNVertici() + " vertici e " + this.model.getNArchi()+ " archi\n");
    	
    	this.txtX.clear();
    	this.btnAdiacenze.setDisable(false);
    	this.btnPercorso.setDisable(false);
    	this.cmbA1.setDisable(false);
    	this.cmbA2.setDisable(false);
    	
    	this.cmbA1.getItems().clear();
    	this.cmbA2.getItems().clear();
    	List<Album> vertici = this.model.getVertici();
    	Collections.sort(vertici);
    	this.cmbA1.getItems().addAll(vertici);
    	this.cmbA2.getItems().addAll(vertici);
    	

    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnAdiacenze != null : "fx:id=\"btnAdiacenze\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnPercorso != null : "fx:id=\"btnPercorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbA1 != null : "fx:id=\"cmbA1\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbA2 != null : "fx:id=\"cmbA2\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtN != null : "fx:id=\"txtN\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtX != null : "fx:id=\"txtX\" was not injected: check your FXML file 'Scene.fxml'.";
        
    	//abilita i vari controlli della gui
    	this.txtN.clear();
    	this.txtResult.clear();
    	this.txtX.clear();
    	this.btnAdiacenze.setDisable(true);
    	this.btnPercorso.setDisable(true);
    	this.cmbA1.setDisable(true);
    	this.cmbA2.setDisable(true);
    	
        
        

    }

    
    public void setModel(Model model) {
    	this.model = model;
    }
}
