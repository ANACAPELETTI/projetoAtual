package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import entity.PSOEntity;

public class PSOUtils {

	//*static long seed = 12345;

	public static List<List<List<Double>>> createKernels(int nKernels, int nLayers, int kernelSize) {
		

		List<List<List<Double>>> newKernels = new ArrayList<List<List<Double>>>();

		for (int particle = 0; particle < nLayers; particle++) {
			List<List<Double>> newParticle = new ArrayList<List<Double>>();
			for (int layer = 0; layer < nKernels; layer++) {
				List<Double> newLayer = new ArrayList<Double>();
				for (int kernel = 0; kernel < kernelSize * kernelSize; kernel++) {
					//Random random = new Random(seed);
					Random random = new Random();
					newLayer.add(10*random.nextGaussian());
				}
				newParticle.add(newLayer);
			}
			newKernels.add(newParticle);
		}

		return newKernels;
	}

	public static List<List<List<Double>>> createVelocities(int nKernels, int nLayers, int kernelSize) {
		List<List<List<Double>>> newKernels = new ArrayList<List<List<Double>>>();

		for (int particle = 0; particle < nLayers; particle++) {
			List<List<Double>> newParticle = new ArrayList<List<Double>>();
			for (int layer = 0; layer < nKernels; layer++) {
				List<Double> newLayer = new ArrayList<Double>();
				for (int kernel = 0; kernel < kernelSize * kernelSize; kernel++) {
					
					newLayer.add(0.0);
				}
				newParticle.add(newLayer);
			}
			newKernels.add(newParticle);
		}

		return newKernels;
	}
	
	public static List<Double> createBias(int nLayers, int kernelSize) {

		List<Double> biasList = new ArrayList<Double>();

		for (int i = 0; i < nLayers; i++) {
		
				Random random = new Random();
				biasList.add(10*random.nextGaussian());
			
		}

		return biasList;

	}

	public static List<Double> createBiasVelocity(int nLayers, int kernelSize) {

		List<Double> biasList = new ArrayList<Double>();

		for (int i = 0; i < nLayers; i++) {
			
		
			
			biasList.add(0.0);
		}

		return biasList;

	}


	public static List<Double> createFullyConected(int lastLayerSize, int nClassifications) {

		List<Double> lastLayer = new ArrayList<Double>();

		for (int i = 0; i < lastLayerSize * nClassifications; i++) {

			Random random = new Random();
			//Random random = new Random(seed);
			lastLayer.add(random.nextGaussian());

		}

		return lastLayer;

	}

	public static List<Double> createFullyConectedVelocity(int lastLayerSize, int nClassifications) {

		List<Double> lastLayer = new ArrayList<Double>();

		for (int i = 0; i < lastLayerSize * nClassifications; i++) {

			lastLayer.add(0.0);

		}

		return lastLayer;

	}

	public static List<Double> atualizaParticula(List<Double>  particula, List<Double>  velocidade, boolean melhor) {
		
		int m = particula.size();
	
	
		for (int i = 0; i < m; i++) {
			if(!melhor) {
					particula.set(i, particula.get(i) + velocidade.get(i));
			}
			
		}
		return particula;
	}

	public static List<Double> atualizaVelocidade(
			List<Double> velocidade, 
			List<Double> melhorGlobal, 
			List<Double> melhorLocal,
			List<Double> kernel, double omega) {
		int m = velocidade.size();
		
		double pesoLocal = 0.5;
		double pesoGlobal = 1.1;
		Random random = new Random();
		for (int i = 0; i < m; i++) {
			double newVelocity = velocidade.get(i) * omega
						+ pesoGlobal * random.nextFloat() * (melhorGlobal.get(i) - kernel.get(i))
						+ pesoLocal * random.nextFloat() * (melhorLocal.get(i) - kernel.get(i));
			velocidade.set(i, newVelocity);
			/*
			double newVelocity =
					+ pesoGlobal * random.nextFloat() * (melhorGlobal.get(i) - kernel.get(i))
					+ pesoLocal * random.nextFloat() * (melhorLocal.get(i) - kernel.get(i));
		velocidade.set(i, newVelocity);*/
			
		}
		return velocidade;
	}
	
public static List<Double> newConnectedVelocity(List<Double>  oldVelocity, List<Double> melhorGlobal, List<Double> melhorLocal, List<Double> particula, double omega) {
		
		double pesoGlobal = 2.0;
		double pesoLocal = 0.5;
		Random random = new Random();
		int size = oldVelocity.size();
		for (int i = 0; i < size; i++) {
		
			oldVelocity.set(i, omega*oldVelocity.get(i)
					+ pesoGlobal * random.nextFloat() * (melhorGlobal.get(i) - particula.get(i))
					+ pesoLocal * random.nextFloat() * (melhorLocal.get(i) - particula.get(i)));
			
		}

		return oldVelocity;
	}
	
	
	public static List<Double> newConnected(List<Double>  oldMatrix, List<Double> bestMatrix, boolean melhor) {
		
		
		int size = oldMatrix.size();
		for (int i = 0; i < size; i++) {
			if(!melhor) {
			oldMatrix.set(i,oldMatrix.get(i)+ bestMatrix.get(i));
			}
		}

		return oldMatrix;
	}
	
	/*
	public static List<Double> newConnected(List<Double>  oldMatrix, List<Double> bestMatrix, double omega) {
		
		double pesoGlobal = 0.7;
		Random random = new Random();
		int size = oldMatrix.size();
		for (int i = 0; i < size; i++) {
		
			oldMatrix.set(i,oldMatrix.get(i)+ omega*pesoGlobal * random.nextFloat() * (bestMatrix.get(i) - oldMatrix.get(i)));
			
		}

		return oldMatrix;
	}
	*/
	public static void setMelhorGlobal(List<PSOEntity> psoEntityList) {
		
		List<Double> errosGerais = new ArrayList<Double>();
		
		for(int i = 0; i < psoEntityList.size(); i++) {
			errosGerais.add(psoEntityList.get(i).getErroGlobal());
		}
		
		int melhorPosicao = errosGerais.indexOf(Collections.min(errosGerais));
		
		
		for(int i = 0; i < psoEntityList.size(); i++) {
			if(i == melhorPosicao) {
				psoEntityList.get(i).setMelhorGlobal(true);
				int nImages = psoEntityList.get(i).getLayers().get(0).size();
				System.out.println("Taxa de acertos: " + 100*( nImages- psoEntityList.get(i).getErroGlobal() )/nImages+"%");
			}else {
				psoEntityList.get(i).setMelhorGlobal(false);	
				
			}
		}
	}
	

	public static List<Double> atualizaBias(List<Double>  bias, List<Double>  biasVelocity, boolean melhor) {
		
		int m = bias.size();
	
	
		for (int i = 0; i < m; i++) {
			if(!melhor) {
				bias.set(i, bias.get(i) + biasVelocity.get(i));
			}
			
		}
		return bias;
	}

	public static List<Double> atualizaBiasVelocidade(
			List<Double> biasVelocity, 
			List<Double> melhorGlobal, 
			List<Double> melhorLocal,
			List<Double> bias, double omega) {
		int m = biasVelocity.size();
		
		double pesoLocal = 0.5;
		double pesoGlobal = 1.1;
		Random random = new Random();
		for (int i = 0; i < m; i++) {
			double newVelocity = biasVelocity.get(i) * omega
						+ pesoGlobal * random.nextFloat() * (melhorGlobal.get(i) - bias.get(i))
						+ pesoLocal * random.nextFloat() * (melhorLocal.get(i) - bias.get(i));
			biasVelocity.set(i, newVelocity);
			/*
			double newVelocity =
					+ pesoGlobal * random.nextFloat() * (melhorGlobal.get(i) - kernel.get(i))
					+ pesoLocal * random.nextFloat() * (melhorLocal.get(i) - kernel.get(i));
		velocidade.set(i, newVelocity);*/
			
		}
		return biasVelocity;
	}
	
}
