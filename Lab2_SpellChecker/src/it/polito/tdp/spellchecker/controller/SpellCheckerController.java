package it.polito.tdp.spellchecker.controller;

import java.awt.Color;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SpellCheckerController {
	
	Dictionary model = new Dictionary();
	Color rosso = Color.red;
	
	

    public Color getRosso() {
		return rosso;
	}

	public void setRosso(Color rosso) {
		this.rosso = rosso;
	}

	public void setModel(Dictionary model) {
		   	this.model = model;
		   	this.comboLanguage.getItems().addAll(Language.values());
	}

	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtMessage"
    private TextField txtMessage; // Value injected by FXMLLoader

    @FXML // fx:id="txtInput"
    private TextArea txtInput; // Value injected by FXMLLoader

    @FXML // fx:id="btnSplell"
    private Button btnSplell; // Value injected by FXMLLoader

    @FXML // fx:id="btnClear"
    private Button btnClear; // Value injected by FXMLLoader

    @FXML // fx:id="comboLanguage"
    private ComboBox<Language> comboLanguage; // Value injected by FXMLLoader

    @FXML // fx:id="txtTime"
    private TextField txtTime; // Value injected by FXMLLoader

    @FXML
    void doSpell(ActionEvent event) {
    	String risultatoS="";
    	
    	
    	String lingua=this.comboLanguage.getValue().toString();
    	
    	String testo = this.txtInput.getText();
    	String[] vettore= testo.split("\\W");
    	/*for(int i=0;i<vettore.length;i++){
    	System.out.println(vettore[i]+"\n");}*/
    	List<RichWord> risultato=model.traduzione(lingua,vettore);
    	for(RichWord rw:risultato){
    		if (rw.isCorretta()){
    			risultatoS=risultatoS+rw.getParola()+" ";
    		}else{
    			String colorato=rw.getParola();
    			
    			
    			
    			risultatoS=risultatoS+rw.getParola();
    		}
    	}

    }

    @FXML
    void doClear(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtMessage != null : "fx:id=\"txtMessage\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnSplell != null : "fx:id=\"btnSplell\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert comboLanguage != null : "fx:id=\"comboLanguage\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtTime != null : "fx:id=\"txtTime\" was not injected: check your FXML file 'SpellChecker.fxml'.";

    }
}

