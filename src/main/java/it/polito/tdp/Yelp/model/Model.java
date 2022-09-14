package it.polito.tdp.Yelp.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.Yelp.db.YelpDAO;

public class Model {
	
	private Graph<User, DefaultWeightedEdge> grafo;
	private List<User> utenti;
	int max = 0;
	
	public String creaGrafo(int minRevisioni, int anno) {
		
		// CREAZIONE GRAFO
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		YelpDAO dao = new YelpDAO();
		this.utenti = dao.getUsersWithReviews(minRevisioni);  //UN PASSAGGIO  IN PIU' NPER POTER DEBUGGARE CON FACILITA'
		Graphs.addAllVertices(this.grafo, this.utenti);
		
		// CREAZIONE  ARCHI
		for(User u1 : this.utenti) {
			for(User u2 : this.utenti) {
				if(!u1.equals(u2) && u1.getUserId().compareTo(u2.getUserId())<0) {
					int sim  = dao.calcolaSimilarita(u1, u2, anno);
					if(sim > 0) {
						Graphs.addEdge(this.grafo, u1, u2, sim);
					}
				}
			}			
		}
		
		return "Grafo creato con " +this.grafo.vertexSet().size()+
				" vertici e " +this.grafo.edgeSet().size()+ " archi\n";
	}
	
	public List<User> utentiPiuSimili(User u) {
		
		
		
		// TROVO IL PESO MASSIMO TRA GLI ARCHI ESISTENTI
		for(DefaultWeightedEdge e : this.grafo.edgesOf(u)) {
			if(this.grafo.getEdgeWeight(e) > max) {
				max = (int)this.grafo.getEdgeWeight(e);
			}
		}
		
		// TROVO I VERTICI CORRISPONDENTI A TALI ARCHI
		List<User> result = new ArrayList<>();
		for(DefaultWeightedEdge e : this.grafo.edgesOf(u)) {
			if((int)this.grafo.getEdgeWeight(e) == max) {
				User u2 = Graphs.getOppositeVertex(this.grafo, e, u); //Trova il vertice opposto a U
				result.add(u2);
			}
		}
		return result;
	}
	
	
	public List<User> getUser() {
		return this.utenti;
	}
	
	public int getPesoArco() {
		return max;
		
	}
	
	

}
