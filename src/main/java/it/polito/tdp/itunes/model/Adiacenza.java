package it.polito.tdp.itunes.model;

public class Adiacenza implements Comparable<Adiacenza>{
	
	Album a;
	double bilancio;
	
	
	public Adiacenza(Album a, double bilancio) {
		super();
		this.a = a;
		this.bilancio = bilancio;
	}


	public Album getA() {
		return a;
	}


	public void setA(Album a) {
		this.a = a;
	}


	public double getBilancio() {
		return bilancio;
	}


	public void setBilancio(double bilancio) {
		this.bilancio = bilancio;
	}


	@Override
	public int compareTo(Adiacenza other) {
		// TODO Auto-generated method stub
		return (int) (other.bilancio-this.bilancio);
	}
	
	

}
