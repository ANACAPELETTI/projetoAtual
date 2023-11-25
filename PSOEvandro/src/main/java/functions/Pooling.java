package functions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import entity.PSOEntity;

public class Pooling {

	public static List<Double> poolingLists(List<Double> layer, int poolingSize) throws InterruptedException {

		List<Double> output = new ArrayList<Double>();

		int layerSize = (int) Math.sqrt(layer.size());
	

		for (int i = 0; i < layerSize - poolingSize +1; i+=poolingSize) {

			for (int j = 0; j < layerSize - poolingSize +1; j+=poolingSize) {

				List<Double> subList = new ArrayList<Double>();

				for (int k = 0; k < poolingSize; k++) {

					for (int l = 0; l < poolingSize; l++) {
						
						subList.add(layer.get(i * (layerSize) + j + k * (layerSize) + l));
					
					}
					

				}
				output.add(Collections.max(subList));

			}

		}

		return output;
	}

	public static void poolingLayer(PSOEntity psoEntity, int layerIndex, int poolingIndex) throws InterruptedException {

		List<List<Double>> newLayer = new ArrayList<List<Double>>();
		
		int poolingSize = psoEntity.getListaPoolings().get(poolingIndex)[1];
	
		for (int i = 0; i < psoEntity.getLayers().get(layerIndex).size(); i++) {
			
				newLayer.add(poolingLists(psoEntity.getLayers().get(layerIndex).get(i), poolingSize));
				
		}
	//	System.out.println(psoEntity.getLayers().get(layerIndex).get(0).size());
		//System.out.println(newLayer.get(poolingIndex).size());
		//TimeUnit.SECONDS.sleep(1);
		psoEntity.setLayer(newLayer, layerIndex + 1);

	}
}
