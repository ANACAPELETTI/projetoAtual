package functions;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import entity.PSOEntity;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import util.Kernels;

public class Import {
	public static List<Double> loadExcelBias(String caminho) throws Exception {
		List<Double> kernels = new ArrayList<Double>();
		FileInputStream file = new FileInputStream(new File(caminho));

		Workbook workBook = new XSSFWorkbook(file);
		List<Sheet> listaPlanilhas = new ArrayList<Sheet>();

		Sheet sheet = workBook.getSheetAt(workBook.getNumberOfSheets() - 1);
		listaPlanilhas.add(sheet);
		List<List<Double>> listaKernel = new ArrayList<List<Double>>();
		int linha = 0;
		
		while (sheet.getRow(0).getCell(linha) != null) {
			System.out.println(sheet.getRow(0).getCell(linha));
			kernels.add(Double.valueOf(sheet.getRow(0).getCell(linha).toString()));
			linha++;
		}
		return kernels;
	}

	public static List<Double> loadExcelFullyConected(String caminho) throws Exception {
		List<Double> kernels = new ArrayList<Double>();
		FileInputStream file = new FileInputStream(new File(caminho));

		Workbook workBook = new XSSFWorkbook(file);
		List<Sheet> listaPlanilhas = new ArrayList<Sheet>();

		Sheet sheet = workBook.getSheetAt(workBook.getNumberOfSheets() - 2);
		listaPlanilhas.add(sheet);
		List<List<Double>> listaKernel = new ArrayList<List<Double>>();
		int linha = 0;
		while (sheet.getRow(0).getCell(linha) != null) {
			kernels.add(Double.valueOf(sheet.getRow(0).getCell(linha).toString()));
			linha++;
		}
		return kernels;
	}

	public static List<List<List<Double>>> loadExcelKernels(String caminho, int tamanhoKernel, int numeroKernels) throws Exception {
		FileInputStream file = new FileInputStream(new File(caminho));

		Workbook workBook = new XSSFWorkbook(file);
		List<Sheet> listaPlanilhas = new ArrayList<Sheet>();
			
			List<List<List<Double>>> particulasKernel = new ArrayList<List<List<Double>>>();

			for (int i = 0; i < workBook.getNumberOfSheets() -2; i++) {
				Sheet sheet = workBook.getSheetAt(i);
				listaPlanilhas.add(sheet);
				List<List<Double>> listaKernel = new ArrayList<List<Double>>();
				for (int j = 0; j < numeroKernels; j++) {
					List<Double> kernels = new ArrayList<Double>();
					for (int linha = 0; linha < tamanhoKernel*tamanhoKernel; linha++) {
							Cell cell = sheet.getRow(j).getCell(linha);
							kernels.add(Double.valueOf(cell.toString()));
					}
					listaKernel.add(kernels);
				}
				particulasKernel.add(listaKernel);
			}

		return particulasKernel;
	}
}