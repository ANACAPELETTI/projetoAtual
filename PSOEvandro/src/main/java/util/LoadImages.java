package util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entity.ImageEntity;

public class LoadImages {

	public static List<ImageEntity> loadImages(String path) throws IOException {
		File diretorio = new File(path);
		File[] arquivos = diretorio.listFiles();
		ImageReader imageReader = new ImageReader();

		List<ImageEntity> listImageEntity = new ArrayList<ImageEntity>();
		for (int j = 0; j < arquivos.length; j++) {
			
			List<Double> imageList = imageReader
					.imageReader(path + "\\" + arquivos[j].getName());
			char label = arquivos[j].getName().charAt(0);
			ImageEntity imageEntity = new ImageEntity(imageList, label);
			listImageEntity.add(imageEntity);
		}
		return listImageEntity;

	}	
}
