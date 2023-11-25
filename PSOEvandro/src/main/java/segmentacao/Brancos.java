package segmentacao;

import com.googlecode.javacv.cpp.opencv_core;
import java.io.File;
import java.io.IOException;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_core.CvSize;
import static com.googlecode.javacv.cpp.opencv_core.IPL_DEPTH_8U;
import static com.googlecode.javacv.cpp.opencv_core.cvCloneImage;
import static com.googlecode.javacv.cpp.opencv_core.cvCopy;
import static com.googlecode.javacv.cpp.opencv_core.cvCreateImage;
import static com.googlecode.javacv.cpp.opencv_core.cvGetMat;
import static com.googlecode.javacv.cpp.opencv_core.cvGetSize;
import static com.googlecode.javacv.cpp.opencv_core.cvSet2D;
import static com.googlecode.javacv.cpp.opencv_highgui.cvLoadImage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;


public class Brancos {
	public static void main(String[] args) throws IOException {

		File diretorio = new File("src\\imagens");
		File[] arquivos = diretorio.listFiles();

		for (int i = 0; i < arquivos.length; i++) {

			IplImage imagemOriginal = cvLoadImage(diretorio.getAbsolutePath() + "\\" + arquivos[i].getName());
			CvSize tamanhoImagemOriginal = cvGetSize(imagemOriginal);

			IplImage imagemProcessada = cvCreateImage(tamanhoImagemOriginal, IPL_DEPTH_8U, 3);
			imagemProcessada = cvCloneImage(imagemOriginal);

			opencv_core.CvMat mtx = opencv_core.CvMat.createHeader(imagemProcessada.height(), imagemProcessada.width());
			opencv_core.CvScalar scalarImagemProcessada = new opencv_core.CvScalar();
			cvGetMat(imagemProcessada, mtx, null, 0);

			for (int j = 0; j < imagemProcessada.height(); j++) {
				scalarImagemProcessada.setVal(0, 255);
				scalarImagemProcessada.setVal(1, 255);
				scalarImagemProcessada.setVal(2, 255);
				cvSet2D(mtx, j, 0, scalarImagemProcessada);
			}

			for (int j = 0; j < imagemProcessada.height(); j++) {
				scalarImagemProcessada.setVal(0, 255);
				scalarImagemProcessada.setVal(1, 255);
				scalarImagemProcessada.setVal(2, 255);
				cvSet2D(mtx, j, imagemProcessada.width() - 1, scalarImagemProcessada);
			}

			for (int j = 0; j < imagemProcessada.width(); j++) {
				scalarImagemProcessada.setVal(0, 255);
				scalarImagemProcessada.setVal(1, 255);
				scalarImagemProcessada.setVal(2, 255);
				cvSet2D(mtx, 0, j, scalarImagemProcessada);
			}

			for (int j = 0; j < imagemProcessada.width(); j++) {
				scalarImagemProcessada.setVal(0, 255);
				scalarImagemProcessada.setVal(1, 255);
				scalarImagemProcessada.setVal(2, 255);
				cvSet2D(mtx, imagemProcessada.height() - 1, j, scalarImagemProcessada);
			}

			imagemProcessada = new IplImage(mtx);

			cvCopy(mtx, imagemProcessada);
			cvSaveImage("C:\\Users\\vandr\\eclipse-workspace\\Extrator\\src\\cortes\\" + arquivos[i].getName().charAt(0)
					+ i + ".jpg", imagemProcessada);

		}
	}
}
