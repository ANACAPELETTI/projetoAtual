package gui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entity.ImageEntity;
import entity.PSOEntity;
import functions.Classification;
import functions.Convolute;
import functions.CreatePSO;
import functions.FullyConected;
import functions.Import;
import functions.Pooling;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import util.LoadImages;

public class DevViewController {
	boolean imagemCarregada = false;
	List<ImageEntity> listaImages;
	PSOEntity psoEntity = new PSOEntity();
	
	@FXML
	Button criaMatrizButton = new Button();
	
	@FXML
	Button importaExcelButton = new Button();
	
	@FXML
	Button carregarImagensButton = new Button();
	
	@FXML
	private Label labelAcuracia;
	
	@FXML
	Label labelVertical = new Label();
	
	@FXML
	Label labelHorizontal = new Label();
	
	@FXML
	Label labelNumImagens = new Label();
	
	@FXML
	GridPane matrizConfusao = new GridPane();
	
	@FXML
	GridPane matrizConfusaoMaster = new GridPane();
	
	@FXML
	private TextField textFieldOrdem;
	
	@FXML
	private TextField textFieldKernels;
	
	@FXML
	private TextField textFieldTamPoolings;
	
	@FXML
	private TextField textFieldTamKernel;
	
	private TextField[] textFields;
	
	public void initialize() {
	    textFields = new TextField[]{textFieldKernels, textFieldTamKernel, textFieldTamPoolings, textFieldOrdem};

	    for (int i = 0; i < textFields.length - 1; i++) {
	        int currentIndex = i;
	        int nextIndex = i + 1;

	        textFields[currentIndex].setOnKeyPressed(event -> {
	            if (event.getCode() == KeyCode.TAB) {
	                event.consume();
	                textFields[nextIndex].requestFocus();
	            }
	        });
	    }

	    // Último campo de texto para voltar ao primeiro campo
	    textFields[textFields.length - 1].setOnKeyPressed(event -> {
	        if (event.getCode() == KeyCode.TAB) {
	            event.consume();
	            textFields[0].requestFocus();
	        }
	    });
	}
	
	@FXML
    public void criarMatrizConfusao() throws InterruptedException {
        exibirMatriz();
    }
	
	@FXML
    public void importarExcel() throws NumberFormatException, Exception {
		String caminho = "";
		
		ExtensionFilter image = new ExtensionFilter("Arquivo excel (*.xlsx)", "*.xlsx");
		FileChooser fileChooser = new FileChooser();

		fileChooser.getExtensionFilters().addAll(image);

		File selectedFile = fileChooser.showOpenDialog(null);
		
		if (selectedFile != null) {
			caminho = selectedFile.getPath();
		}
		
		if (caminho != "") {
			psoEntity.setKernels(Import.loadExcelKernels(caminho, Integer.valueOf(textFieldTamKernel.getText()), Integer.valueOf(textFieldKernels.getText())));
			psoEntity.setBiasParticle(Import.loadExcelBias(caminho));
			psoEntity.setFullyConectedLayerParticle(Import.loadExcelFullyConected(caminho));
		}
    }
	
	@FXML
    public void carregarImagens() throws IOException {
		String caminho = "";
		DirectoryChooser directoryChooser = new DirectoryChooser();
		File selectedFile = directoryChooser.showDialog(null);
		
		listaImages = LoadImages.loadImages(selectedFile.getAbsolutePath());
		if(listaImages.size() > 0) {
			enableButtons();
			labelNumImagens.setText(String.valueOf(listaImages.size()));
		}
	}
	
	public void enableButtons() {
		imagemCarregada = true;
		criaMatrizButton.setDisable(false);
	}
	
	public void disableButtons() {
		imagemCarregada = false;
		criaMatrizButton.setDisable(true);
	}
	 
	double larguraFixa = 40.0;
	
	@FXML
	public void exibirMatriz() throws InterruptedException {
		String ordemString = textFieldOrdem.getText();
		List<String> listaStringOrdem = Arrays.asList(ordemString.split(",")); 
		List<Integer> listaOrdemInt = new ArrayList<Integer>();
		listaStringOrdem.forEach(k -> {
			listaOrdemInt.add(Integer.valueOf(k));
		});
		
		psoEntity.setListaOrdemOperacoes(listaOrdemInt);
		int nConv = 0, nPool = 0;
		for(int i = 0; i < listaOrdemInt.size(); i++) {
			if(listaOrdemInt.get(i) == 0) {
				nConv++;
			}else {
				nPool++;
			}
		}
		
		List<int[]> poolingsList = new ArrayList<int[]>();
		
		String poolingString = textFieldTamPoolings.getText();
		List<String> listaPoolingString = Arrays.asList(poolingString.split(",")); 
		List<Integer> listaPoolingInt = new ArrayList<Integer>();
		listaPoolingString.forEach(k -> {
			listaPoolingInt.add(Integer.valueOf(k));
		});
		
		for(int i = 0; i < listaPoolingInt.size(); i++) {
			int[] pooling = {
				0, listaPoolingInt.get(i)	
			};
			poolingsList.add(pooling);
		}
		
		psoEntity.setListaPoolings(poolingsList);
		
		int nKernels = Integer.valueOf(textFieldKernels.getText());

		int kernelsSize = Integer.valueOf(textFieldTamKernel.getText());

		int nClassifications = 10; //10 números a ser classificados

		//List<PSOEntity> listPSOEntity = new ArrayList<PSOEntity>();
		
		//listPSOEntity.add(psoEntity);
		double omega = 0.9;
		double omegaF = 0.7;
		
		int convolutionalIndex = 0;
		int poolingIndex = 0;
		
		List<PSOEntity> psoEntityList = CreatePSO.createPSO(1, Integer.valueOf(textFieldKernels.getText().toString()), Integer.valueOf(textFieldTamKernel.getText().toString()), 
				10, listaImages, poolingsList, listaOrdemInt);
		
		psoEntityList.get(0).setBiasParticle(psoEntity.getBiasParticle());
		psoEntityList.get(0).setFullyConectedLayerParticle(psoEntity.getFullyConectedLayerParticle());
		psoEntityList.get(0).setKernels(psoEntity.getKernels());
		
		for (int operacao = 0; operacao < psoEntityList.get(0).getListaOrdemOperacoes().size(); operacao++) {

			if (psoEntityList.get(0).getListaOrdemOperacoes().get(operacao) == 0) {
				Convolute.convolveLayer(psoEntityList.get(0), operacao, convolutionalIndex);
				convolutionalIndex++;
			} else if (psoEntityList.get(0).getListaOrdemOperacoes().get(operacao) == 1) {

				Pooling.poolingLayer(psoEntityList.get(0), operacao, poolingIndex);
				poolingIndex++;
			}

		}

		int lastLayerSize = psoEntityList.get(0).getLayers()
				.get(psoEntityList.get(0).getLayers().size() - 2).size();
		int firstLayerSize = psoEntityList.get(0).getLayers().get(0).size();

		int nClassificacoes = lastLayerSize / firstLayerSize;

		List<List<Double>> finalLayer = new ArrayList<List<Double>>();

		for (int i = 0; i < firstLayerSize; i++) {
			List<Double> fullyList = new ArrayList<Double>();
			for (int j = 0; j < nClassificacoes; j++) {

				fullyList.add(psoEntityList.get(0).getLayers()
						.get(psoEntityList.get(0).getLayers().size() - 2).get(i * (nClassificacoes) + j)
						.get(0));
			}
			finalLayer.add((fullyList));
		}

		psoEntityList.get(0).setLayer(finalLayer, psoEntityList.get(0).getLayers().size() - 1);

		List<List<Double>> listFullyConnectedLayer = new ArrayList<List<Double>>();
		for (int i = 0; i < finalLayer.size(); i++) {
			listFullyConnectedLayer.add(FullyConected.fullyConected(finalLayer.get(i),
					psoEntityList.get(0).getFullyConectedLayerParticle()));
		}
		
		System.out.println(psoEntityList.get(0).getLayers().get(0).size());
		System.out.println(psoEntityList.get(0).getLayers().get(1).size());
		System.out.println(psoEntityList.get(0).getLayers().get(2).size());
		System.out.println(psoEntityList.get(0).getLayers().get(3).size());
		System.out.println(psoEntityList.get(0).getLayers().get(4).size());
		
		List<Character> classification = new ArrayList<Character>();

		List<Double> erros = new ArrayList<Double>();

		double sumErros = 0.0;

		for (int i = 0; i < finalLayer.size(); i++) {

			classification.add(Classification.classifica(listFullyConnectedLayer.get(i)));
			if (classification.get(i) != listaImages.get(i).getLabel()) {
				erros.add(1.0);
				sumErros += 1.0;
			} else {
				erros.add(0.0);
			}
		}

		psoEntityList.get(0).setLetrasClassificadas(classification);
		psoEntityList.get(0).setErroGlobal(sumErros);

		psoEntity.setLetrasClassificadas(classification);
		exibirMatriz(psoEntity, listaImages);
		labelAcuracia.setText(String.format("%.2f", (100*(listaImages.size() - psoEntityList.get(0).getErroGlobal())/listaImages.size())) + "%");
	}
	
	private void exibirMatriz(PSOEntity psoEntityEntrada, List<ImageEntity> listImageEntity) {
		matrizConfusaoMaster.setVisible(true);
		matrizConfusao.setGridLinesVisible(true);
		labelVertical.setVisible(true);
        labelHorizontal.setVisible(true);
        // Limpe o GridPane antes de criar uma nova matriz
        matrizConfusao.getChildren().clear();
        matrizConfusao.getColumnConstraints().clear();
        matrizConfusao.getRowConstraints().clear();

        matrizConfusao.setGridLinesVisible(true);
        
        int[][] matriz = new int[12][12];
        
        List<Character> alphabet = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
        
        // Obtenha o número de linhas e colunas da matriz
        int linhas = 11;
        int colunas = 11;
        
        double larguraFixa = 35.0;
        
        for (int imagem = 0; imagem < psoEntityEntrada.getLetrasClassificadas().size(); imagem++) {
        	int i = alphabet.indexOf(psoEntityEntrada.getLetrasClassificadas().get(imagem));
	        int j = alphabet.indexOf(listImageEntity.get(imagem).getLabel());
	        matriz[i+1][j+1] += 1;
        }
        
        for (int linha = 0; linha < linhas; linha++) {
        	matriz[linha+1][0] = linha;
            for (int coluna = 0; coluna < colunas; coluna++) {
            	matriz[0][coluna+1] = coluna;
                Label label = new Label(String.valueOf(matriz[linha][coluna]));
                label.setMinSize(larguraFixa, larguraFixa);
                label.setMaxSize(larguraFixa, larguraFixa);
                label.setPrefSize(larguraFixa, larguraFixa);
                label.setAlignment(Pos.CENTER);

                matrizConfusao.add(label, coluna, linha); // Adiciona a label na coluna e linha corretas

                if (matrizConfusao.getColumnConstraints().size() <= coluna) { // Configuração para ajustar o tamanho de todas as colunas
                    matrizConfusao.getColumnConstraints().add(new ColumnConstraints(larguraFixa));
                } else {
                    matrizConfusao.getColumnConstraints().get(coluna).setMinWidth(larguraFixa);
                    matrizConfusao.getColumnConstraints().get(coluna).setMaxWidth(larguraFixa);
                    matrizConfusao.getColumnConstraints().get(coluna).setPrefWidth(larguraFixa);
                }
            }
            matrizConfusao.getRowConstraints().add(new RowConstraints(larguraFixa)); // Configuração para ajustar o tamanho de todas as linhas
        }
        
        colorirDiagonalPrincipal(matrizConfusao, 11);
    }
	
	private void colorirDiagonalPrincipal(GridPane gridPane, int tamanho) {
		for (Node node : gridPane.getChildren()) {
	        Integer columnIndex = GridPane.getColumnIndex(node);
	        Integer rowIndex = GridPane.getRowIndex(node);
	        
	        if (columnIndex != null && rowIndex != null && columnIndex.equals(rowIndex)) {
	            node.setStyle("-fx-background-color: #d3e8f1;");
	        }
	    }
	}
	
	public static PSOEntity achaAMelhorGlobal(List<PSOEntity> listaPsoEntity) {
		PSOEntity melhorGlobal = new PSOEntity();
		for (int i = 0; i < listaPsoEntity.size(); i++) {
			if (listaPsoEntity.get(i).isMelhorGlobal()) {
				melhorGlobal = listaPsoEntity.get(i);
			}
		}
		return melhorGlobal;
	}
}