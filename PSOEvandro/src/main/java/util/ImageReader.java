package util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class ImageReader {

	public List<Double> imageReader(String caminhoImage) throws IOException {

		BufferedImage image = ImageIO.read(new File(caminhoImage));
		int imageH = (int) image.getHeight();
		int imageW = (int) image.getWidth();

		List<Double> imageGray = new ArrayList<Double>();

		for (int i = 0; i < imageH; i++) {
			for (int j = 0; j < imageW; j++) {

				Color c = new Color(image.getRGB(j, i));
				double gray = c.getRed() * 0.3 + c.getGreen() * 0.59 + c.getBlue() * 0.11; // fórmula do Funck
				if(gray > 50) {
					gray = 255;
				}else {
					gray = 0;
				}
				imageGray.add((gray)); 
				
			}
		}

		return imageGray;
	}

}
