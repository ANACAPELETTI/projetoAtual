package util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Export {
	public static void exportExcel(List<List<List<Double>>> listaListaKernel, List<Double> fullyConnected, List<Double> bias) throws IOException {
		Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file
		List<Sheet> listaPlanilhas = new ArrayList<Sheet>();
		for (int i = 0; i < listaListaKernel.size(); i++) {
			Sheet sheet = workbook.createSheet("Lista" + i); // criando as planilhas de listas de kernels
			listaPlanilhas.add(sheet);
			int contador = 0;
			for (int j = 0; j < listaListaKernel.get(i).size(); j++) {
				sheet.createRow(contador);

				for (int k = 0; k < listaListaKernel.get(i).get(j).size(); k++) {

					listaPlanilhas.get(i).getRow(contador).createCell(k)
							.setCellValue(listaListaKernel.get(i).get(j).get(k));
				}
				contador++;

			}
		}
		
		Sheet sheetFully = workbook.createSheet("Fully"); // criando as planilhas de listas de kernels
		sheetFully.createRow(0);
		listaPlanilhas.add(sheetFully);
		for (int k = 0; k < fullyConnected.size(); k++) {

			listaPlanilhas.get(listaPlanilhas.size()-1).getRow(0).createCell(k)
					.setCellValue(fullyConnected.get(k));
			
		}
		
		Sheet sheetBias = workbook.createSheet("Bias"); // criando as planilhas de listas de kernels
		sheetBias.createRow(0);
		listaPlanilhas.add(sheetBias);
		for (int k = 0; k < bias.size(); k++) {

			listaPlanilhas.get(listaPlanilhas.size()-1).getRow(0).createCell(k)
					.setCellValue(bias.get(k));
			
		}

		FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Ana Capeletti\\Desktop\\ListaKernels.xlsx");
		workbook.write(fileOut);
		fileOut.close();
	}
}