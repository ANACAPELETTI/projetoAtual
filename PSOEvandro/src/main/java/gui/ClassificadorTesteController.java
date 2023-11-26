package gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entity.ImageEntity;
import entity.PSOEntity;
import util.Bias;
import util.FullConected;
import util.ImageReader;
import util.Kernels;
import functions.Classification;
import functions.Convolute;
import functions.FullyConected;
import functions.Import;
import functions.Pooling;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import util.LoadView;
import util.PSOUtils;

public class ClassificadorTesteController {
	ImageReader imageReader = new ImageReader();
	Import importe = new Import();
	LoadView loadView = new LoadView();
	PSOEntity psoEntity = new PSOEntity();
	
	@FXML
	AnchorPane anchorPane;
	
	public AnchorPane getAnchorPane() {
		return anchorPane;
	}

	public void setAnchorPane(AnchorPane anchorPane) {
		this.anchorPane = anchorPane;
	}

	@FXML
	Button botao = new Button();
	
	@FXML
	public void selectFile() throws IOException {
		String caminho = "";
		
		ExtensionFilter image = new ExtensionFilter("Imagem (*.png)", "*.png", "*.jfif", "*.jpeg", "*.jpg");
		FileChooser fileChooser = new FileChooser();

		fileChooser.getExtensionFilters().addAll(image);

		File selectedFile = fileChooser.showOpenDialog(null);
		
		if (selectedFile != null) {
			caminho = selectedFile.getPath();
		}
		if (caminho != "") {
			try {
				FileInputStream file = new FileInputStream(new File(caminho));
				List<Double> imageMatriz = imageReader.imageReader(selectedFile.getAbsolutePath());
				List<ImageEntity> listImageMatriz = new ArrayList<ImageEntity>();
				ImageEntity imageEntity = new ImageEntity(imageMatriz, selectedFile.getName().charAt(0));
				listImageMatriz.add(imageEntity);
				Image imagem = new Image(selectedFile.toURI().toString());
				
				loadView.loadView("/gui/Resultado.fxml", (ResultadoController controller) -> {
	                controller.setPane3(anchorPane);
	                controller.setImage(imagem);

	                Platform.runLater(() -> {
	                    try {
							classificaTexto(anchorPane, listImageMatriz, imagem);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                });
	            }, anchorPane);
			} catch (FileNotFoundException e) {
			}
		}
	}
	
	@FXML
	void handleFileOverEvent(DragEvent event) {
	    Dragboard db = event.getDragboard();
	    if (db.hasFiles()) {
	        event.acceptTransferModes(TransferMode.COPY);
	    } else{
	        event.consume();
	    }
	}
	
	@FXML
	void handleFileDroppedEvent(DragEvent event) throws IOException { //função que pega a imagem do usuário, quando ele arrastar ela
	    Dragboard db = event.getDragboard();
	    String caminho = "";
	    File file2 = db.getFiles().get(0);
	    if (file2 != null) {
			caminho = file2.getPath();
		}
	    if (caminho != "") {
			try {
				FileInputStream file = new FileInputStream(new File(caminho));
				List<Double> imageMatriz = imageReader.imageReader(file2.getAbsolutePath());
				List<ImageEntity> listImageMatriz = new ArrayList<ImageEntity>();
				ImageEntity imageEntity = new ImageEntity(imageMatriz, file2.getName().charAt(0));
				listImageMatriz.add(imageEntity);
				Image imagem = new Image(file2.toURI().toString());
				
				loadView.loadView("/gui/Resultado.fxml", (ResultadoController controller) -> {
	                controller.setPane3(anchorPane);
	                controller.setImage(imagem);

	                Platform.runLater(() -> {
	                    try {
							classificaTexto(anchorPane, listImageMatriz, imagem);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                });
	            }, anchorPane);
			} catch (FileNotFoundException e) {
			}
		}
	}
	
	public void classificaTexto(AnchorPane anchorPane, List<ImageEntity> listImageMatriz, Image imagem) throws InterruptedException {
		System.out.println("Classifica texto");
		System.out.println(Bias.listaDeMatrizes().get(0));
		psoEntity.setBiasParticle(Bias.listaDeMatrizes());
		
		int[] pooling1 = {2, 2}; //pooling mínimo, com tamanho 2
		
		int kernelsSize = 13;
		int nkernels = 10;
		
		List<int[]> listaPoolings = new ArrayList<int[]>(Arrays.asList(pooling1, pooling1));
		
		List<Integer> listaOrdemOperacoes = new ArrayList<Integer>(Arrays.asList(1, 0, 1));
		List<List<List<float[][]>>> kernels = new ArrayList<List<List<float[][]>>>();
		
		psoEntity.setKernelsSize(kernelsSize);

		psoEntity.setListaPoolings(listaPoolings);
		psoEntity.setListaOrdemOperacoes(listaOrdemOperacoes);
		
		int nKernelsLayers = 0;
		int nLayers = listaOrdemOperacoes.size();
		for (int i = 0; i < nLayers; i++) {
			if (listaOrdemOperacoes.get(i) == 0) {
				nKernelsLayers++;
			}
		}

		psoEntity.setKernels(Kernels.listaDeMatrizes()); 
		
		List<List<List<Double>>> Layers = new ArrayList<List<List<Double>>>();
		List<List<Double>> layer0 = new ArrayList<List<Double>>();
		for (int image = 0; image < listImageMatriz.size(); image++) {
			layer0.add(listImageMatriz.get(image).getData());
		}
		Layers.add(layer0);

		List<List<List<Double>>> intermediaryLayers = PSOUtils.createVelocities(nkernels, nLayers + 1, kernelsSize);

		for (int layer = 0; layer < intermediaryLayers.size(); layer++) {
			Layers.add(intermediaryLayers.get(layer));
		}

		psoEntity.setLayers(Layers);
		
		int convolutionalIndex = 0;
		int poolingIndex = 0;

		for (int operacao = 0; operacao < listaOrdemOperacoes.size(); operacao++) {

			if (listaOrdemOperacoes.get(operacao) == 0) {
				Convolute.convolveLayer(psoEntity, operacao, convolutionalIndex);
				convolutionalIndex++;
			} else if (listaOrdemOperacoes.get(operacao) == 1) {

				Pooling.poolingLayer(psoEntity, operacao, poolingIndex);
				poolingIndex++;
			}

		}
		
		int lastLayerSize = psoEntity.getLayers().get(psoEntity.getLayers().size() - 2).size();
		int firstLayerSize = psoEntity.getLayers().get(0).size();

		int nClassificacoes = lastLayerSize / firstLayerSize;

		List<List<Double>> finalLayer = new ArrayList<List<Double>>();

		for (int i = 0; i < firstLayerSize; i++) {
			List<Double> fullyList = new ArrayList<Double>();
			for (int j = 0; j < nClassificacoes; j++) {

				fullyList.add(psoEntity.getLayers().get(psoEntity.getLayers().size() - 2)
						.get(i * (nClassificacoes) + j).get(0));
			}
			finalLayer.add(fullyList);
		}

		psoEntity.setLayer(finalLayer, psoEntity.getLayers().size() - 1);

		psoEntity.setFullyConectedLayerParticle(FullConected.listaDeMatrizes());

		List<List<Double>> listFullyConnectedLayer = new ArrayList<List<Double>>();
		for (int i = 0; i < finalLayer.size(); i++) {
			listFullyConnectedLayer
					.add(FullyConected.fullyConected(finalLayer.get(i), psoEntity.getFullyConectedLayerParticle()));
		}

		List<Character> classification = new ArrayList<Character>();

		List<Double> erros = new ArrayList<Double>();

		double sumErros = 0.0;

		for (int i = 0; i < finalLayer.size(); i++) {
			classification.add(Classification.classifica(listFullyConnectedLayer.get(i)));
			if (classification.get(i) != listImageMatriz.get(i).getLabel()) {
				erros.add(1.0);
				sumErros += 1.0;
			} else {
				erros.add(0.0);
			}
		}

		psoEntity.setErrors(erros);
		
		psoEntity.setErroGlobal(sumErros);
	        loadView.loadView("/gui/Resultado.fxml", (ResultadoController controller) -> {
	            controller.setPane3(anchorPane);
	            controller.setImage(imagem);
	            try {
					controller.setText(String.valueOf(Classification.classifica(listFullyConnectedLayer.get(0))));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }, anchorPane);
	}
}
