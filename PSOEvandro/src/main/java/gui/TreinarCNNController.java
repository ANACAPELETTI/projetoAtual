package gui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import entity.ImageEntity;
import entity.PSOEntity;
import functions.CreatePSO;
import functions.IteratePSO;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import util.Export;
import util.LoadImages;
import util.PSOUtils;

public class TreinarCNNController {
	
	boolean imagemCarregada = false;
	List<ImageEntity> listaImages;
	List<String> listaListaResultado = new ArrayList<String>(); 
	ObservableList <String> observableList;
	Thread firstThread;
	
	@FXML
	private AnchorPane pane3;
	
	@FXML
	private Label labelOrdem;
	
	@FXML
	private TextField textFieldOrdem;
	
	@FXML
	private TextField textFieldParticula;
	
	@FXML
	private TextField textFieldIteracao;
	
	@FXML
	private TextField textFieldKernels;
	
	@FXML
	private TextField textFieldTamPoolings;
	
	@FXML
	private TextField textFieldTamKernel;
	
	@FXML
	private Button tooltipHelp;
	
	@FXML
	private Button tooltipHelp2;
	
	@FXML
	private Button reiniciar;
	
	@FXML
	private Button pararTreinamento;
	
	@FXML
	private Button iniciarTreinamento;
	
	@FXML
	private ListView<String> listViewResult;
	
	@FXML
	private Button carregarImagens;
	
	@FXML
	public ScrollPane scrollPaneMaster;
	
	@FXML
	private Label labelAcuracia;
	
	@FXML
	Label labelVertical = new Label();
	
	@FXML
	Label labelHorizontal = new Label();
	
	@FXML
	GridPane matrizConfusao = new GridPane();
	
	@FXML
	GridPane matrizConfusaoMaster = new GridPane();
	
	private TextField[] textFields;
	
	public void initialize() {
	    textFields = new TextField[]{textFieldParticula, textFieldIteracao, textFieldKernels, textFieldTamKernel, textFieldTamPoolings, textFieldOrdem};

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


	public void setPane3(AnchorPane pane3) {
		this.pane3 = pane3;
	}
	
	public AnchorPane getPane3() {
		return this.pane3;
	}

	@FXML
	public void Reiniciar() {
		Tooltip.install(tooltipHelp, new Tooltip("Utilize 0 para convolução e 1 para pooling,\nseparados por vírgula."));
		Tooltip.install(tooltipHelp2, new Tooltip("Digite os tamanhos dos poolings separados por vírgula."));
		disableButtons();
		textFieldTamKernel.clear();
		textFieldTamPoolings.clear();
		textFieldKernels.clear();
		textFieldIteracao.clear();
		textFieldParticula.clear();
		textFieldOrdem.clear();
		listViewResult.getItems().clear();
		matrizConfusao.getChildren().clear();
		matrizConfusaoMaster.setVisible(false);
		labelVertical.setVisible(false);
        labelHorizontal.setVisible(false);
	}
	
	@FXML
	public void pararTreinamento() {
		if(firstThread.isAlive()) {
			firstThread.currentThread();
			System.out.println("Thread viva");
		}
		System.out.println("Parar treino");
	}
	
	@FXML
	public void iniciarTreinamento() throws InterruptedException, IOException {
		String primeiroElemento = listaListaResultado.get(0);
		listaListaResultado.clear();
		listaListaResultado.add(primeiroElemento);
		observableList.clear();
		observableList.add(primeiroElemento);
		
		String ordemString = textFieldOrdem.getText();
		List<String> listaStringOrdem = Arrays.asList(ordemString.split(",")); 
		List<Integer> listaOrdemInt = new ArrayList<Integer>();
		listaStringOrdem.forEach(k -> {
			listaOrdemInt.add(Integer.valueOf(k));
		});
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

		int nParticulas = Integer.valueOf(textFieldParticula.getText());

		int nIteracoes = Integer.valueOf(textFieldIteracao.getText());

		int nKernels = Integer.valueOf(textFieldKernels.getText());

		int kernelsSize = Integer.valueOf(textFieldTamKernel.getText());

		int nClassifications = 10; //10 números a ser classificados

		List<PSOEntity> listPSOEntity = CreatePSO.createPSO(nParticulas, nKernels, kernelsSize, nClassifications,
				listaImages, poolingsList, listaOrdemInt);
		
		
		PSOUtils.setMelhorGlobal(listPSOEntity);
		double omega = 0.9;
		double omegaF = 0.7;
		CountDownLatch latch = new CountDownLatch(1);
		firstThread = new Thread(() -> {
			for(int iteracao = 0; iteracao < nIteracoes; iteracao++) {
				try {
					IteratePSO.refreshPSO(listPSOEntity,listaImages, omega);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				PSOUtils.setMelhorGlobal(listPSOEntity);
				
				double sum = 0.0;
				sum += Math.abs(listPSOEntity.get(0).getKernels().get(0).get(0).get(1)-listPSOEntity.get(1).getKernels().get(0).get(0).get(1));
				sum += Math.abs(listPSOEntity.get(1).getKernels().get(0).get(0).get(2)-listPSOEntity.get(2).getKernels().get(0).get(0).get(2));
				sum += Math.abs(listPSOEntity.get(3).getKernels().get(0).get(0).get(3)-listPSOEntity.get(5).getKernels().get(0).get(0).get(3));
				sum += Math.abs(listPSOEntity.get(4).getKernels().get(0).get(1).get(6)-listPSOEntity.get(2).getKernels().get(0).get(1).get(6));
				sum += Math.abs(listPSOEntity.get(5).getKernels().get(0).get(1).get(2)-listPSOEntity.get(0).getKernels().get(0).get(1).get(2));
				System.out.println("Erro partículas: "+ sum);
				//omega = omega+(omegaF-omega)*(iteracao/nIteracoes);
				if(sum < 0.0000001) {
					break;
				}
				listaListaResultado.add("Iteração: " + (iteracao + 1) + ", Melhor partícula: " + listPSOEntity.indexOf(achaAMelhorGlobal(listPSOEntity)) + ", Menor erro: " + 
				achaAMelhorGlobal(listPSOEntity).getErroGlobal());
				observableList = FXCollections.observableArrayList(listaListaResultado);
				Platform.runLater(() -> {
				    listViewResult.setItems(observableList); //Atualização do ListView
				});
				}
				latch.countDown();
			});
			
			Platform.runLater(() -> {
			});
		firstThread.start();
		try {
		    latch.await(); // Aguarda o término da primeira thread
		    //exibirMatriz(psoEntityEntrada, listImageEntity); // CORRIGIR
		    exibirMatriz(achaAMelhorGlobal(listPSOEntity), listaImages);
		    labelAcuracia.setText(String.format("%.2f", (100*(listaImages.size() - achaAMelhorGlobal(listPSOEntity).getErroGlobal())/listaImages.size())) + "%");
		    Export.exportExcel(achaAMelhorGlobal(listPSOEntity).getKernels(), achaAMelhorGlobal(listPSOEntity).getFullyConectedLayerParticle(), achaAMelhorGlobal(listPSOEntity).getBiasParticle());
		} catch (InterruptedException e) {
		}
	}
	
	@FXML
	public void foldChooser() throws IOException { //botão de carregar imagens
		String caminho = "";
		DirectoryChooser directoryChooser = new DirectoryChooser();
		File selectedFile = directoryChooser.showDialog(null);
		
		listaImages = LoadImages.loadImages(selectedFile.getAbsolutePath());
		if(listaImages.size() > 0) {
			enableButtons();
			listaListaResultado.add("Número de imagens carregadas: " + listaImages.size());
			observableList = FXCollections.observableArrayList(listaListaResultado);
			listViewResult.setItems(observableList);
		}
	}
	
	public void enableButtons() {
		imagemCarregada = true;
		pararTreinamento.setDisable(false);
		iniciarTreinamento.setDisable(false);
		reiniciar.setDisable(false);
	}
	
	public void disableButtons() {
		imagemCarregada = false;
		pararTreinamento.setDisable(true);
		iniciarTreinamento.setDisable(true);
		reiniciar.setDisable(true);
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
}
