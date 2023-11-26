package gui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entity.ImageEntity;
import entity.PSOEntity;
import functions.CreatePSO;
import functions.IteratePSO;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import util.LoadImages;
import util.PSOUtils;

public class TreinarCNNController {
	
	boolean imagemCarregada = false;
	List<ImageEntity> listaImages;
	List<String> listaListaResultado = new ArrayList<String>(); 
	ObservableList <String> observableList;
	
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

	public void setPane3(AnchorPane pane3) {
		this.pane3 = pane3;
	}
	
	public AnchorPane getPane3() {
		return this.pane3;
	}

	@FXML
	public void Reiniciar() {
		Tooltip.install(tooltipHelp, new Tooltip("Utilize 0 para convolução e 1 para pooling,\nseparados por vírgula."));
		Tooltip.install(tooltipHelp2, new Tooltip("Digite os tamanhos dos poolings separados por vírgula"));
		disableButtons();
		textFieldTamKernel.clear();
		textFieldTamPoolings.clear();
		textFieldKernels.clear();
		textFieldIteracao.clear();
		textFieldParticula.clear();
		textFieldOrdem.clear();
		listViewResult.getItems().clear();
	}
	
	@FXML
	public void pararTreinamento() {
		
	}
	
	@FXML
	public void iniciarTreinamento() throws InterruptedException {
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
		
		Thread firstThread = new Thread(() -> {
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
				listViewResult.setItems(observableList);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				}
			});
	
			Platform.runLater(() -> {
		});

		try {
			firstThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		firstThread.start();
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
}
