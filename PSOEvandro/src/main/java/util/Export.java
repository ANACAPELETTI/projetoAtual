package util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Export {
	public static void exportExcel(List<List<List<Double>>> listaListaKernel) throws IOException {
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

		FileOutputStream fileOut = new FileOutputStream("C:\\Users\\vandr\\desktop\\ListaKernels.xlsx");
		workbook.write(fileOut);
		fileOut.close();
	}
}