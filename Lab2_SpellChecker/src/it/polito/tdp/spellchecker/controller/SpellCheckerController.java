package it.polito.tdp.spellchecker.controller;

//import java.awt.Color;
import java.net.URL;
import java.time.LocalDateTime;
//import java.text.MessageFormat;
import java.util.List;
import java.util.ResourceBundle;
//import javax.swing.*;



import java.awt.*;
import java.util.*;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
//import javafx.scene.effect.ColorInput;
import javafx.scene.effect.Effect;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.StringConverter;
import javafx.*;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class SpellCheckerController {
	
	Dictionary model = new Dictionary();

	List<Text> testi = new LinkedList<Text>();
	

   

	public void setModel(Dictionary model) {
		   	this.model = model;
		   	this.comboLanguage.getItems().addAll(Language.values());
	}

	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    
    @FXML
    private TextArea txtOutput;
    @FXML // fx:id="txtOutput1"
    private TextFlow txtOutput1; // Value injected by FXMLLoader

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
    	LocalDateTime now = LocalDateTime.now();
    	
    	if(this.comboLanguage.getValue()==null){this.txtMessage.setText("select a language");return;}
    	boolean errore=false;
    	this.txtMessage.clear();
    	    	
    	String lingua=this.comboLanguage.getValue().toString();
    	this.testi.clear();
    	this.txtOutput1.getChildren().clear();
    	
    	String testo = this.txtInput.getText();
    	testo=testo.toLowerCase();
    	
    	String[] vettore= testo.split("\\W");
    	
    	List<RichWord> risultato=model.traduzione(lingua,vettore);
    	   	
    	
    	for(RichWord rw:risultato){
    	   		if (rw.isCorretta()){
    			Text testoNero = new Text(rw.getParola()+" ");
    			testi.add(testoNero);
    			    			
    		}else{
    			errore=true;
    			Text testoRosso = new Text(rw.getParola()+" ");
    			testoRosso.setFill(Color.RED);
    			testi.add(testoRosso);
    		}
    	}
    	LocalDateTime now1 = LocalDateTime.now();
    	Long diffe= (long) (now1.getNano()-now.getNano());
    	
    	
    	this.txtOutput1.getChildren().addAll(testi);
    	if(errore){
    		String messaggio="Your text contains errors";
    		this.txtMessage.setStyle("-fx-text-fill: red");
    		this.txtMessage.setText(messaggio);
    		
    	}
    	this.txtTime.setText(" il tempo di elaborazione è:   "+(double)(diffe)/(1000000000)+" secondi");

    }

    @FXML
    void doClear(ActionEvent event) {
    	boolean errore=false;
    	this.txtMessage.clear();
    	this.txtInput.clear();
    	this.txtOutput1.getChildren().clear();
    	this.txtTime.clear();

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtMessage != null : "fx:id=\"txtMessage\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnSplell != null : "fx:id=\"btnSplell\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert comboLanguage != null : "fx:id=\"comboLanguage\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtTime != null : "fx:id=\"txtTime\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtOutput1 != null : "fx:id=\"txtOutput1\" was not injected: check your FXML file 'SpellChecker.fxml'.";

    }
}

