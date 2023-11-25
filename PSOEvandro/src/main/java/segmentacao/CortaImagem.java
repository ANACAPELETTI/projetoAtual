package segmentacao;

import com.googlecode.javacv.cpp.opencv_core;
import java.io.File;
import java.io.IOException;
import static com.googlecode.javacv.cpp.opencv_core.cvSetImageROI;
import static com.googlecode.javacv.cpp.opencv_core.cvCopy;
import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_core.CvRect;
import com.googlecode.javacv.cpp.opencv_core.CvSize;
import static com.googlecode.javacv.cpp.opencv_core.IPL_DEPTH_8U;
import static com.googlecode.javacv.cpp.opencv_core.cvCloneImage;
import static com.googlecode.javacv.cpp.opencv_core.cvCreateImage;
import static com.googlecode.javacv.cpp.opencv_core.cvGet2D;
import static com.googlecode.javacv.cpp.opencv_core.cvGetMat;
import static com.googlecode.javacv.cpp.opencv_core.cvGetSize;
import static com.googlecode.javacv.cpp.opencv_highgui.CV_LOAD_IMAGE_GRAYSCALE;
import static com.googlecode.javacv.cpp.opencv_highgui.cvLoadImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CortaImagem {
	public static void main(String[] args) throws IOException {

		List<Integer> vetorColunas = new ArrayList<Integer>();
		List<Integer> vetorLinhas = new ArrayList<Integer>();
		double red, green, blue, gray;
		// Diretório onde estão armazenadas as imagens
		File diretorio = new File("src\\cortes");
		File[] arquivos = diretorio.listFiles();

		for (int i = 0; i < arquivos.length; i++) {

			// Carrega cada imagem do diret�rio
			IplImage imagemOriginal = cvLoadImage(diretorio.getAbsolutePath() + "\\" + arquivos[i].getName(),
					CV_LOAD_IMAGE_GRAYSCALE);
			CvSize tamanhoImagemOriginal = cvGetSize(imagemOriginal);
			// Imagem processada - tamanho, profundidade de cores e n�mero de canais de
			// cores
			IplImage imagemProcessada = cvCreateImage(tamanhoImagemOriginal, IPL_DEPTH_8U, 3);
			imagemProcessada = cvCloneImage(imagemOriginal);

			// Matriz multi-canal
			opencv_core.CvMat mtx = opencv_core.CvMat.createHeader(imagemProcessada.height(), imagemProcessada.width());
			cvGetMat(imagemProcessada, mtx, null, 0);


			// Varre por Coluna
			for (int largura = 0; largura < imagemProcessada.width(); largura++) { // essa linha percorre toda a linha
																					// do meio, coluna por coluna

				boolean vazio = false;
				int teste = 0;

				for (int k = 0; k < imagemProcessada.height(); k++) { // entao para primeira coluna vou olhar do pixel
																		// de cima at� o ultimo de baixo
					opencv_core.CvScalar scalarExtraiRgb = cvGet2D(imagemProcessada, k, largura);
					blue = scalarExtraiRgb.val(0);
					green = scalarExtraiRgb.val(1);
					red = scalarExtraiRgb.val(2);
					gray = Math.pow(
							(0.2126 * Math.pow(red, 2.2) + 0.7152 * Math.pow(green, 2.2) + 0.072 * Math.pow(blue, 2.2)),
							1 / 2.2);

					if (gray >= 10) {
						teste++;
					}
					;

				}
				;

				if (teste == imagemProcessada.height()) {
					vazio = true;
				}
				;

				if (vazio != true) {
					vetorColunas.add(largura);
				}
			}

			for (int largura = 0; largura < imagemProcessada.width(); largura++) { // essa linha percorre toda a linha
																					// do meio, coluna por coluna

				boolean vazio = false;
				int teste = 0;

				for (int k = 0; k < imagemProcessada.height(); k++) { // entao para primeira coluna vou olhar do pixel
																		// de cima at� o ultimo de baixo
					opencv_core.CvScalar scalarExtraiRgb = cvGet2D(imagemProcessada, k, largura);
					blue = scalarExtraiRgb.val(0);
					green = scalarExtraiRgb.val(1);
					red = scalarExtraiRgb.val(2);
					gray = Math.pow(
							(0.2126 * Math.pow(red, 2.2) + 0.7152 * Math.pow(green, 2.2) + 0.072 * Math.pow(blue, 2.2)),
							(1 / 2.2));

					if (gray >= 10) {
						teste++;
					}
					;

				}
				;

				if (teste == imagemProcessada.height()) {
					vazio = true;
				}
				;
				if (vazio != true) {
					vetorColunas.add(largura);
				}
			}

			// Varre por Linha
			for (int Altura = 0; Altura < imagemProcessada.height(); Altura++) { // essa linha percorre toda a coluna do
																					// meio, linha por linha

				boolean vazio = false;
				int teste = 0;

				for (int k = 0; k < imagemProcessada.width(); k++) { // entao para primeira coluna vou olhar do pixel de
																		// cima at� o ultimo de baixo
					opencv_core.CvScalar scalarExtraiRgb = cvGet2D(imagemProcessada, Altura, k);
					blue = scalarExtraiRgb.val(0);
					green = scalarExtraiRgb.val(1);
					red = scalarExtraiRgb.val(2);
					gray = Math.pow(
							(0.2126 * Math.pow(red, 2.2) + 0.7152 * Math.pow(green, 2.2) + 0.072 * Math.pow(blue, 2.2)),
							(1 / 2.2));

					if (gray >= 40) {
						teste++;
					}
					;

				}
				;

				if (teste == imagemProcessada.width()) {
					vazio = true;
				}
				;

				if (vazio != true) {
					vetorLinhas.add(Altura);
				}
			}

			int Xmin, Xmax, Ymin, Ymax;

			if (Collections.min(vetorColunas) > 0) {
				Xmin = Collections.min(vetorColunas);
			} else {
				Xmin = 0;
			}

			if (Collections.max(vetorColunas) < 50) {
				Xmax = Collections.max(vetorColunas) - Xmin + 1;
			} else {
				Xmax = 50;
			}

			if (Collections.min(vetorLinhas) > 0) {
				Ymin = Collections.min(vetorLinhas);
			} else {
				Ymin = 0;
			}

			if (Collections.max(vetorLinhas) < 50) {
				Ymax = Collections.max(vetorLinhas) - Ymin + 1;
			} else {
				Ymax = 50;
			}

			vetorLinhas.clear();
			vetorColunas.clear();

			CvRect r = new CvRect(Xmin, Ymin, Xmax, Ymax);
			cvSetImageROI(imagemProcessada, r);
			IplImage cropped = cvCreateImage(cvGetSize(imagemProcessada), imagemProcessada.depth(),
					imagemProcessada.nChannels());
			// Copy original image (only ROI) to the cropped image
			cvCopy(imagemProcessada, cropped);
			cvSaveImage("C:\\Users\\vandr\\eclipse-workspace\\Extrator\\src\\corte\\" + arquivos[i].getName().charAt(0)
					+ i + ".png", cropped);
			System.out.println(
					arquivos[i].getName().charAt(0) + "" + i + "," + Xmin + "," + Xmax + "," + Ymin + "," + Ymax);

		}

	}

}
