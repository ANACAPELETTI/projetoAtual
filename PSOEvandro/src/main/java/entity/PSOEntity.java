package entity;

import java.util.ArrayList;
import java.util.List;

public class PSOEntity {

	private int nLayers;
	private int nKernelsLayer;
	private int kernelsSize;
	private double erroGlobal;
	private boolean isMelhorGlobal;

	private List<List<List<Double>>> Kernels;
	private List<List<List<Double>>> melhorLocalKernels;
	private List<List<List<Double>>> Velocities;
	private List<Double> fullyConectedLayer;
	private List<Double> fullyConectedLayerParticle;
	private List<Double> fullyConectedVelocity;
	private List<Double> fullyConectedMelhorLocal;
	
	private List<List<Double>> biasParticle;
	private List<List<Double>> biasVelocity;
	private List<List<Double>> biasMelhorLocal;
	
	private List<Double> Errors;
	private List<List<List<Double>>> Layers;

	private List<int[]> listaPoolings;
	private List<Integer> listaOrdemOperacoes;

	public int getnLayers() {
		return nLayers;
	}

	public void setnLayers(int nLayers) {
		this.nLayers = nLayers;
	}

	public int getnKernelsLayer() {
		return nKernelsLayer;
	}

	public void setnKernelsLayer(int nKernelsLayer) {
		this.nKernelsLayer = nKernelsLayer;
	}

	public boolean isMelhorGlobal() {
		return isMelhorGlobal;
	}

	public void setMelhorGlobal(boolean isMelhorGlobal) {
		this.isMelhorGlobal = isMelhorGlobal;
	}

	public List<List<List<Double>>> getKernels() {
		return new ArrayList<List<List<Double>>>(Kernels);
	}

	public void setKernels(List<List<List<Double>>> kernels) {
		Kernels = kernels;
	}

	public List<List<List<Double>>> getMelhorLocalKernels() {
		return new ArrayList<List<List<Double>>>(melhorLocalKernels);
	}

	public void setMelhorLocalKernels(List<List<List<Double>>> melhorLocalKernels) {
		this.melhorLocalKernels = melhorLocalKernels;
	}

	public List<List<List<Double>>> getVelocities() {
		return new ArrayList<List<List<Double>>>(Velocities);
	}

	public void setVelocities(List<List<List<Double>>> velocities) {
		Velocities = velocities;
	}

	public List<Double> getErrors() {
		return new ArrayList<Double>(Errors);
	}

	public void setErrors(List<Double> errors) {
		Errors = errors;
	}

	public List<List<List<Double>>> getLayers() {
		return new ArrayList<List<List<Double>>>(Layers);
	}

	public void setLayers(List<List<List<Double>>> layers) {
		Layers = layers;
	}

	public List<int[]> getListaPoolings() {
		return new ArrayList<int[]>(listaPoolings);
	}

	public void setListaPoolings(List<int[]> listaPoolings) {
		this.listaPoolings = listaPoolings;
	}

	public List<Integer> getListaOrdemOperacoes() {
		return new ArrayList<Integer>(listaOrdemOperacoes);
	}

	public void setListaOrdemOperacoes(List<Integer> listaOrdemOperacoes) {
		this.listaOrdemOperacoes = listaOrdemOperacoes;
	}
	
	public void setLayer(List<List<Double>> newLayer, int index) {
		this.Layers.set(index, newLayer);
	}

	public int getKernelsSize() {
		return kernelsSize;
	}

	public void setKernelsSize(int kernelsSize) {
		this.kernelsSize = kernelsSize;
	}

	public List<Double> getFullyConectedLayer() {
		return new ArrayList<Double>(fullyConectedLayer);
	}

	public void setFullyConectedLayer(List<Double> fullyConectedLayer) {
		this.fullyConectedLayer = fullyConectedLayer;
	}

	public List<Double> getFullyConectedVelocity() {
		return new ArrayList<Double>(fullyConectedVelocity);
	}

	public void setFullyConectedVelocity(List<Double> fullyConectedVelocity) {
		this.fullyConectedVelocity = fullyConectedVelocity;
	}

	public List<Double> getFullyConectedMelhorLocal() {
		return new ArrayList<Double>(fullyConectedMelhorLocal);
	}

	public void setFullyConectedMelhorLocal(List<Double> fullyConectedMelhorLocal) {
		this.fullyConectedMelhorLocal = fullyConectedMelhorLocal;
	}

	public List<Double> getFullyConectedLayerParticle() {
		return new ArrayList<Double>(fullyConectedLayerParticle);
	}

	public void setFullyConectedLayerParticle(List<Double> fullyConectedLayerParticle) {
		this.fullyConectedLayerParticle = fullyConectedLayerParticle;
	}

	@Override
	public String toString() {
		return "PSOEntity\n [isMelhorGlobal=" + isMelhorGlobal + ",\n Kernels=" + Kernels + ",\n fullyConectedLayer="
				+ fullyConectedLayer + ",\n fullyConectedLayerParticle=" + fullyConectedLayerParticle + ",\n Errors="
				+ Errors + ",\n Layers=" + Layers + "]";
	}

	public double getErroGlobal() {
		return erroGlobal;
	}

	public void setErroGlobal(double erroGlobal) {
		this.erroGlobal = erroGlobal;
	}

	public ArrayList<List<Double>> getBiasParticle() {
		return new ArrayList<List<Double>>(biasParticle);
	}

	public void setBiasParticle(List<List<Double>> biasParticle) {
		this.biasParticle = biasParticle;
	}

	public ArrayList<List<Double>> getBiasVelocity() {
		return new ArrayList<List<Double>>(biasVelocity);
	}

	public void setBiasVelocity(List<List<Double>> biasVelocity) {
		this.biasVelocity = biasVelocity;
	}

	public ArrayList<List<Double>> getBiasMelhorLocal() {
		return new ArrayList<List<Double>>(biasMelhorLocal);
	}

	public void setBiasMelhorLocal(List<List<Double>> biasMelhorLocal) {
		this.biasMelhorLocal = biasMelhorLocal;
	}

	
	
}
