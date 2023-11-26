package gui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import util.LoadView;

public class MainViewController implements Initializable{
	
	boolean isMenuOpen = false;
	
	@FXML
    private AnchorPane pane2, pane3;

    @FXML
    private ImageView exit, menu;
    
    public void initialize(URL location, ResourceBundle resources) {
        exit.setOnMouseClicked(event -> {
            System.exit(0);
        });
        
        TranslateTransition translateTransition5 = new TranslateTransition(Duration.seconds(0.5),pane2);
        translateTransition5.setByX(-900);
        translateTransition5.play();
    }
	
	@FXML
	Button novaTela;
	
	@FXML
	Button TreinarCNN;
	
	@FXML
	Button classificador;
	
	@FXML
	public void classificar () {
    	LoadView loadView = new LoadView();
        loadView.loadView("/gui/DevView.fxml", (DevViewController controller) -> {}, pane3 );
	}
	
	@FXML
	public void onClick () {
		if(isMenuOpen) {
            TranslateTransition translateTransition3 = new TranslateTransition(Duration.seconds(0.5),pane2);
            translateTransition3.setByX(-900);
            translateTransition3.play();
            
            TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(0.5),pane3);
            pane3.setPrefWidth(pane3.getWidth() - 90);
            translateTransition2.setByX( - 90);
            translateTransition2.play();
            isMenuOpen = false;
		}
	}
	
	@FXML
	public void openOnClick () {
		if(!isMenuOpen) {
		    TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5),pane2);
		    translateTransition1.setByX(+900);
		    translateTransition1.play();
		    
		    TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(0.5),pane3);
		    pane3.setPrefWidth(pane3.getWidth() + 90);
		    translateTransition2.setByX( + 90);
		    translateTransition2.play();
		    isMenuOpen = true;
		} else {
			TranslateTransition translateTransition3 = new TranslateTransition(Duration.seconds(0.5),pane2);
            translateTransition3.setByX(-900);
            translateTransition3.play();
            
            TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(0.5),pane3);
            pane3.setPrefWidth(pane3.getWidth() - 90);
            translateTransition2.setByX( - 90);
            translateTransition2.play();
            isMenuOpen = false;
		}
	}
	
	
	@FXML
	public void classificarTeste () {
		LoadView loadView = new LoadView();
        loadView.loadView("/gui/ClassificadorTeste.fxml", (ClassificadorTesteController controller) -> {
        	controller.setAnchorPane(pane3);
        }, pane3 );
	}
	
	
	@FXML
	public void abreNavegador () {
		try {
		    Desktop.getDesktop().browse(new URL("https://r4dmx7.csb.app").toURI());
		} catch (IOException e) {
		    e.printStackTrace();
		} catch (URISyntaxException e) {
		    e.printStackTrace();
		}
	}
	
	@FXML
	public void treinarCNN () {
		LoadView loadView = new LoadView();
        loadView.loadView("/gui/treinarCNN.fxml", (TreinarCNNController controller) ->  {
        	controller.Reiniciar();
        }, pane3 );
	}
}
