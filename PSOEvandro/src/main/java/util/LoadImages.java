package util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entity.ImageEntity;

public class LoadImages {

	public static List<ImageEntity> loadImages() throws IOException {
		File diretorio = new File(getPath());
		File[] arquivos = diretorio.listFiles();
		ImageReader imageReader = new ImageReader();

		List<ImageEntity> listImageEntity = new ArrayList<ImageEntity>();

		for (int j = 0; j < arquivos.length; j++) {
			List<Double> imageList = imageReader
					.imageReader("/images/" + arquivos[j].getName());
			char label = arquivos[j].getName().charAt(0);
			ImageEntity imageEntity = new ImageEntity(imageList, label);
			listImageEntity.add(imageEntity);
		}

		return listImageEntity;

	}
	
	public static String getPath() {
		
		String sysOs = System.getProperty("os.name");
		String[] newOs = sysOs.split(" ");
		String imagePath = "";
		
		if (newOs[0].contentEquals("Windows")) {
			//imagePath = "C:\\Users\\vandr\\Dropbox\\Evandro\\CNN-PSO\\src\\main\\java\\images";
			
			imagePath = "C:\\Users\\vandr\\Desktop\\PSOCNN\\src\\main\\java\\images";
		} else {
			imagePath = "/Users/evandronakajima/Library/CloudStorage/Dropbox/Evandro/CNN-PSO/src/main/java/images/";
		}
		
		return imagePath;
		
	}
	
	
	
	
}
