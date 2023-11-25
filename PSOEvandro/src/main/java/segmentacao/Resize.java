package segmentacao;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Resize {
	BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
	    BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
	    Graphics2D graphics2D = resizedImage.createGraphics();
	    graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
	    graphics2D.dispose();
	    return resizedImage;
	}
	
	public static void main(String[] args) throws IOException {
		File diretorio = new File("src\\imagens\\corte");
        File[] arquivos = diretorio.listFiles();
		Resize resizeImage = new Resize();
		
		BufferedImage[] img = new  BufferedImage[arquivos.length];
		
		for(int i = 0 ; i < arquivos.length ; i++) {
			img[i] = ImageIO.read(arquivos[i]);
			BufferedImage resizedImg = resizeImage.resizeImage(img[i], 9, 9);
			File outputfile = new File("C:\\Users\\vandr\\Desktop\\teste\\"+arquivos[i].getName().charAt(0)+i+".png");
			ImageIO.write(resizedImg, "png", outputfile);
		}
		
		
		
	}
	
	
}
