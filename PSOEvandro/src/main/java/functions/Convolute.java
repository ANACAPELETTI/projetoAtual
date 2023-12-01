package functions;

import java.util.ArrayList;
import java.util.List;

import entity.PSOEntity;

public class Convolute {

	public static List<Double> convolveLists(List<Double> layer, List<Double> kernel, double bias) {

		List<Double> output = new ArrayList<Double>();

		int layerSize = (int) Math.sqrt(layer.size());
		int kernelSize = (int) Math.sqrt(kernel.size());

		for (int i = 0; i < layerSize - kernelSize +1; i++) {

			for (int j = 0; j < layerSize - kernelSize + 1; j++) {

				double valor = 0.0;

				for (int k = 0; k < kernelSize; k++) {

					for (int l = 0; l < kernelSize; l++) {
						valor += layer.get(i * (layerSize) + j + k * (layerSize) + l) * kernel.get(k * kernelSize + l);

					}

				}
				output.add(valor+bias);

			}

		}

		return output;
	}

	public static void convolveLayer(PSOEntity psoEntity, int layerIndex, int kernelsIndex) {

		List<List<Double>> newLayer = new ArrayList<List<Double>>();
		
		for (int i = 0; i < psoEntity.getLayers().get(layerIndex).size(); i++) {
			for (int j = 0; j < psoEntity.getKernels().get(kernelsIndex).size(); j++) {
				
				newLayer.add(convolveLists(psoEntity.getLayers().get(layerIndex).get(i),
						psoEntity.getKernels().get(kernelsIndex).get(j),psoEntity.getBiasParticle().get(kernelsIndex)));
			}
		}
		
		psoEntity.setLayer(newLayer, layerIndex + 1);
	}

}
