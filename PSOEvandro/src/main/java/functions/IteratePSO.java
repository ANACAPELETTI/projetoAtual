package functions;

import java.util.ArrayList;
import java.util.List;

import entity.ImageEntity;
import entity.PSOEntity;
import util.PSOUtils;

public class IteratePSO {

	public static void refreshPSO(List<PSOEntity> psoEntityList, List<ImageEntity> imageList, double omega)
			throws InterruptedException {

		PSOEntity melhorGlobal = new PSOEntity();

		for (int particle = 0; particle < psoEntityList.size(); particle++) {
			if (psoEntityList.get(particle).isMelhorGlobal()) {
				melhorGlobal = psoEntityList.get(particle);
				System.out.println("Melhor Posição Global: " + particle);
			}
		}

		for (int particle = 0; particle < psoEntityList.size(); particle++) {
			// atualiza velocidade
			for (int i = 0; i < psoEntityList.get(particle).getVelocities().size(); i++) {
				for (int j = 0; j < psoEntityList.get(particle).getVelocities().get(i).size(); j++) {
					PSOUtils.atualizaVelocidade(psoEntityList.get(particle).getVelocities().get(i).get(j),
							melhorGlobal.getKernels().get(i).get(j),
							psoEntityList.get(particle).getMelhorLocalKernels().get(i).get(j),
							psoEntityList.get(particle).getKernels().get(i).get(j), omega);
				}

			}

			// atualiza kernel
			for (int i = 0; i < psoEntityList.get(particle).getKernels().size(); i++) {
				for (int j = 0; j < psoEntityList.get(particle).getKernels().get(i).size(); j++) {
					PSOUtils.atualizaParticula(psoEntityList.get(particle).getKernels().get(i).get(j),
							psoEntityList.get(particle).getVelocities().get(i).get(j),
							psoEntityList.get(particle).isMelhorGlobal());
				}
			}

			// atualiza velocidade matriz final
			psoEntityList.get(particle).setFullyConectedVelocity(
					PSOUtils.newConnectedVelocity(psoEntityList.get(particle).getFullyConectedVelocity(),
							melhorGlobal.getFullyConectedLayerParticle(),
							psoEntityList.get(particle).getFullyConectedMelhorLocal(),
							psoEntityList.get(particle).getFullyConectedLayerParticle(), omega));

			// atualiza matriz final
			// psoEntityList.get(particle).setFullyConectedLayerParticle(PSOUtils.newConnected(psoEntityList.get(particle).getFullyConectedLayerParticle(),
			// melhorGlobal.getFullyConectedLayerParticle(),omega));
			psoEntityList.get(particle).setFullyConectedLayerParticle(
					PSOUtils.newConnected(psoEntityList.get(particle).getFullyConectedLayerParticle(),
							psoEntityList.get(particle).getFullyConectedVelocity(),
							psoEntityList.get(particle).isMelhorGlobal()));

			// atualiza bias velocity
			PSOUtils.atualizaBiasVelocidade(psoEntityList.get(particle).getBiasVelocity(),
					melhorGlobal.getBiasParticle(), psoEntityList.get(particle).getBiasMelhorLocal(),
					psoEntityList.get(particle).getBiasParticle(), omega);

			// atualiza bias
			PSOUtils.atualizaBias(		
			psoEntityList.get(particle).getBiasParticle(),
					psoEntityList.get(particle).getBiasVelocity(),
					psoEntityList.get(particle).isMelhorGlobal());

			int convolutionalIndex = 0;
			int poolingIndex = 0;
			for (int operacao = 0; operacao < psoEntityList.get(particle).getListaOrdemOperacoes().size(); operacao++) {

				if (psoEntityList.get(particle).getListaOrdemOperacoes().get(operacao) == 0) {
					Convolute.convolveLayer(psoEntityList.get(particle), operacao, convolutionalIndex);
					convolutionalIndex++;
				} else if (psoEntityList.get(particle).getListaOrdemOperacoes().get(operacao) == 1) {

					Pooling.poolingLayer(psoEntityList.get(particle), operacao, poolingIndex);
					poolingIndex++;
				}

			}

			int lastLayerSize = psoEntityList.get(particle).getLayers()
					.get(psoEntityList.get(particle).getLayers().size() - 2).size();
			int firstLayerSize = psoEntityList.get(particle).getLayers().get(0).size();

			int nClassificacoes = lastLayerSize / firstLayerSize;

			List<List<Double>> finalLayer = new ArrayList<List<Double>>();

			for (int i = 0; i < firstLayerSize; i++) {
				List<Double> fullyList = new ArrayList<Double>();
				for (int j = 0; j < nClassificacoes; j++) {

					fullyList.add(psoEntityList.get(particle).getLayers()
							.get(psoEntityList.get(particle).getLayers().size() - 2).get(i * (nClassificacoes) + j)
							.get(0));
				}
				finalLayer.add((fullyList));
			}

			psoEntityList.get(particle).setLayer(finalLayer, psoEntityList.get(particle).getLayers().size() - 1);

			List<List<Double>> listFullyConnectedLayer = new ArrayList<List<Double>>();
			for (int i = 0; i < finalLayer.size(); i++) {
				listFullyConnectedLayer.add(FullyConected.fullyConected(finalLayer.get(i),
						psoEntityList.get(particle).getFullyConectedLayerParticle()));

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

			psoEntityList.get(particle).setLetrasClassificadas(classification);
			
			if (sumErros < psoEntityList.get(particle).getErroGlobal()) {
				psoEntityList.get(particle).setMelhorLocalKernels(psoEntityList.get(particle).getKernels());
				psoEntityList.get(particle)
						.setFullyConectedMelhorLocal(psoEntityList.get(particle).getFullyConectedLayerParticle());
				psoEntityList.get(particle).setBiasMelhorLocal(psoEntityList.get(particle).getBiasParticle());
			}

			psoEntityList.get(particle).setErrors(erros);

			psoEntityList.get(particle).setErroGlobal(sumErros);
		}
	}

	private static List<Double> reLU(List<Double> x) {

		List<Double> newX = new ArrayList<Double>();
		double activation = 0.0;
		for (int i = 0; i < x.size(); i++) {
			if (x.get(i) > activation) {
				newX.add(x.get(i) / x.size());

			} else
				newX.add(0.0);
		}

		return newX;

	}

}
