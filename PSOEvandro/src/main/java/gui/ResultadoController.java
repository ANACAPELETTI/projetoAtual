package gui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import functions.Import;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import util.Kernels;
import util.LoadView;

public class ResultadoController {
	Import importe = new Import();
	Kernels kernelNovo = new Kernels();

	@FXML
	private AnchorPane pane3;

	public void setPane3(AnchorPane pane3) {
		this.pane3 = pane3;
	}
	
	public AnchorPane getPane3() {
		return this.pane3;
	}

	@FXML
	Button btReiniciar;

	@FXML
	Button btCopiar = new Button();

	@FXML
	Button btExportar = new Button();

	@FXML
	GridPane grid = new GridPane();

	@FXML
	ImageView imagemView = new ImageView();

	@FXML
	ScrollPane scrollPane = new ScrollPane();

	@FXML
	private TextField texto;
	
	public void setImage(Image image) {
		imagemView.setImage(image);
		imagemView.setPreserveRatio(true);
	}
	
	public void setText(String textoResultado) {
		texto.setText(textoResultado);
	}

	@FXML
	public void Reiniciar() {
		LoadView loadView = new LoadView();
		loadView.loadView("/gui/ClassificadorTeste.fxml", (ClassificadorTesteController controller) -> {
			controller.setAnchorPane(pane3);
		}, pane3);
	}

	@FXML
	public void copiarTexto() {
		String textoParaCopiar = texto.getText();
		Clipboard clipboard = Clipboard.getSystemClipboard();
		ClipboardContent content = new ClipboardContent();
		content.putString(textoParaCopiar);
		clipboard.setContent(content);
	}

	@FXML
	public void exportarTexto() {
		String textoParaExportar = texto.getText();
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Arquivos de Texto (*.txt)", "*.txt"));

		String nomePadrao = "TextoExportado.txt";
		fileChooser.setInitialFileName(nomePadrao);

		File file = fileChooser.showSaveDialog(null);

		if (file != null) {
			salvarTextoNoArquivo(textoParaExportar, file);
		}
	}

	private void salvarTextoNoArquivo(String texto, File file) {
		try (FileWriter writer = new FileWriter(file)) {
			writer.write(texto);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
