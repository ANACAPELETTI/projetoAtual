package functions;

import java.util.ArrayList;
import java.util.List;
import entity.ImageEntity;
import entity.PSOEntity;
import util.PSOUtils;

public class CreatePSO {

	public static List<PSOEntity> createPSO(int nParticulas, int nKernels, int kernelsSize, int nClassifications,
			List<ImageEntity> imageList, List<int[]> listaPoolings, List<Integer> listaOrdemOperacoes)
			throws InterruptedException {

		List<PSOEntity> listPSOEntity = new ArrayList<PSOEntity>();

		int nLayers = listaOrdemOperacoes.size();
		int nKernelsLayers = 0;

		for (int i = 0; i < nLayers; i++) {
			if (listaOrdemOperacoes.get(i) == 0) {
				nKernelsLayers++;
			}
		}

		for (int particula = 0; particula < nParticulas; particula++) {

			PSOEntity psoEntity = new PSOEntity();

			psoEntity.setnLayers(nLayers);
			psoEntity.setnKernelsLayer(nKernelsLayers);
			psoEntity.setKernelsSize(kernelsSize);

			psoEntity.setListaPoolings(listaPoolings);
			psoEntity.setListaOrdemOperacoes(listaOrdemOperacoes);

			psoEntity.setKernels(PSOUtils.createKernels(nKernels, nKernelsLayers, kernelsSize));
			psoEntity.setVelocities(PSOUtils.createVelocities(nKernels, nKernelsLayers, kernelsSize));
			psoEntity.setMelhorLocalKernels(psoEntity.getKernels());

			psoEntity.setBiasParticle(PSOUtils.createBias(nKernelsLayers, kernelsSize));
			psoEntity.setBiasVelocity(PSOUtils.createBiasVelocity(nKernelsLayers, kernelsSize));
			psoEntity.setBiasParticle(psoEntity.getBiasParticle());
			
			List<List<List<Double>>> Layers = new ArrayList<List<List<Double>>>();
			List<List<Double>> layer0 = new ArrayList<List<Double>>();
			for (int image = 0; image < imageList.size(); image++) {
				layer0.add(imageList.get(image).getData());
			}
			Layers.add(layer0);

			List<List<List<Double>>> intermediaryLayers = PSOUtils.createVelocities(nKernels, nLayers + 1, kernelsSize);

			for (int layer = 0; layer < intermediaryLayers.size(); layer++) {
				Layers.add(intermediaryLayers.get(layer));
			}

			psoEntity.setLayers(Layers);

			int convolutionalIndex = 0;
			int poolingIndex = 0;

			for (int operacao = 0; operacao < listaOrdemOperacoes.size(); operacao++) {

				if (listaOrdemOperacoes.get(operacao) == 0) {
					Convolute.convolveLayer(psoEntity, operacao, convolutionalIndex);
					convolutionalIndex++;
				} else if (listaOrdemOperacoes.get(operacao) == 1) {

					Pooling.poolingLayer(psoEntity, operacao, poolingIndex);
					poolingIndex++;
				}

			}

			int lastLayerSize = psoEntity.getLayers().get(psoEntity.getLayers().size() - 2).size();
			int firstLayerSize = psoEntity.getLayers().get(0).size();

			int nClassificacoes = lastLayerSize / firstLayerSize;

			List<List<Double>> finalLayer = new ArrayList<List<Double>>();

			for (int i = 0; i < firstLayerSize; i++) {
				List<Double> fullyList = new ArrayList<Double>();
				for (int j = 0; j < nClassificacoes; j++) {

					fullyList.add(psoEntity.getLayers().get(psoEntity.getLayers().size() - 2)
							.get(i * (nClassificacoes) + j).get(0));
				}
				finalLayer.add(fullyList);
			}

			psoEntity.setLayer(finalLayer, psoEntity.getLayers().size() - 1);

			psoEntity.setFullyConectedLayerParticle(
					PSOUtils.createFullyConected(finalLayer.get(0).size(), nClassifications));
			psoEntity.setFullyConectedMelhorLocal(psoEntity.getFullyConectedLayerParticle());
			psoEntity.setFullyConectedVelocity(
					PSOUtils.createFullyConectedVelocity(finalLayer.get(0).size(), nClassifications));

			List<List<Double>> listFullyConnectedLayer = new ArrayList<List<Double>>();
			for (int i = 0; i < finalLayer.size(); i++) {
				listFullyConnectedLayer
						.add(FullyConected.fullyConected(finalLayer.get(i), psoEntity.getFullyConectedLayerParticle()));

			}

			List<Character> classification = new ArrayList<Character>();

			List<Double> erros = new ArrayList<Double>();

			double sumErros = 0.0;

			for (int i = 0; i < finalLayer.size(); i++) {

				classification.add(Classification.classifica(listFullyConnectedLayer.get(i)));
				if (classification.get(i) != imageList.get(i).getLabel()) {
					erros.add(1.0);
					sumErros += 1.0;
				} else {
					erros.add(0.0);
				}
			}

			psoEntity.setErrors(erros);
			
			psoEntity.setErroGlobal(sumErros);

			listPSOEntity.add(psoEntity);
		}
		return listPSOEntity;
	}

}

/*
 * private boolean isMelhorGlobal; private List<Double> Errors; private
 */