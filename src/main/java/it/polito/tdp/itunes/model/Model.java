package it.polito.tdp.itunes.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.itunes.db.ItunesDAO;

public class Model {

	private ItunesDAO dao;
	private Graph<Album, DefaultWeightedEdge> grafo;
	private Map<Integer, Album> albumIdMap;
	
	private int bestCntVertciciBilancioMaggioreA;
	private List<Album> bestCamminoAciclico;
	
	
	public Model() {
		this.dao = new ItunesDAO();
		
		this.albumIdMap = new HashMap<Integer, Album>();
		
		//Popoliamo l'identity map, in caso ci servisse dopo
		List<Album> albums = this.dao.getAllAlbums();
		for (Album a : albums) {
			this.albumIdMap.put(a.getAlbumId(), a);
		}
		
		
	}
	

	public void creaGrafo(int n) {
		// TODO Auto-generated method stub
		clearGraph();
		
		//costruzione di un nuovo grafo
		this.grafo = new SimpleDirectedWeightedGraph<Album, DefaultWeightedEdge>(DefaultWeightedEdge.class);


		//assegnazione dei vertici
		List<Album> vertici = this.dao.getVertici(n, albumIdMap);
		Graphs.addAllVertices(this.grafo, vertici);
	
		//assegnazione degli archi
	    for (int i=0; i<vertici.size(); i++)
	    	for (int j=i+1; j<vertici.size(); j++) {
	    		Album a1 = vertici.get(i);
	    		Album a2 = vertici.get(j);
	    		double peso = a1.getDurata()+a2.getDurata();
	    		if (peso > 4*n*1000) {
		    		if (a1.getDurata() < a2.getDurata())
		    			Graphs.addEdgeWithVertices(this.grafo, a1, a2, peso);
		    		else
		    			Graphs.addEdgeWithVertices(this.grafo, a2, a1, peso);
	    		}
	    	}
	    
	    
	
		
	}

	private void clearGraph() {
		// TODO Auto-generated method stub
		
		this.grafo = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		
	}


	public int getNVertici() {
		// TODO Auto-generated method stub
		return this.grafo.vertexSet().size();
	}

	public int getNArchi() {
		// TODO Auto-generated method stub
		return this.grafo.edgeSet().size();
	}
	
	/**
	 * Metodo che restituisce una lista di vertici dell'arco
	 * @return
	 */
	public List<Album> getVertici(){
		return new ArrayList<Album>(this.grafo.vertexSet());
	}


	public List<Adiacenza> analizzaAdiacenze(Album album) {
		// TODO Auto-generated method stub
		
		List<Album> successori = Graphs.successorListOf(this.grafo,album);
		
		List<Adiacenza> adiacenze = new ArrayList<>();

		double bilancio = 0;
		for (Album s: successori) {
			
			double sommaPesoUscente = 0;
			double sommaPesoEntrante = 0;
			
			
			for (DefaultWeightedEdge e : this.grafo.edgesOf(s)) {
				
				if (this.grafo.getEdgeSource(e).equals(s)) {
					 sommaPesoUscente += this.grafo.getEdgeWeight(e);	
				}
				else if (this.grafo.getEdgeTarget(e).equals(s)) {
					 sommaPesoEntrante += this.grafo.getEdgeWeight(e);			
				}
			}
		
			bilancio = sommaPesoEntrante-sommaPesoUscente;
								
			Adiacenza a = new Adiacenza(s, bilancio);
			adiacenze.add(a);
		}
		
				
		return adiacenze;
	
	}


	
	
}
