package Graph_ShortestPaths;

import edu.princeton.cs.algs4.Topological;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.DirectedEdge;

// finding shortest path from vertex s to all other vertices in an edge weighted DAG

// we use topological sort to visit vertices instead of PQ in Dijkstra

// works for even -ve edge weights


public class AcyclicSP {
	
	private DirectedEdge[] edgeTo;
	private double[] distTo;
						 
	public AcyclicSP(EdgeWeightedDigraph G, int s){
		edgeTo = new DirectedEdge[G.V()];
		edgeTo[s] = null;
		
		distTo = new double[G.V()];
		for(int i=0; i<G.V(); i++)
			distTo[i] = Double.POSITIVE_INFINITY;
		distTo[s] = 0.0;
		
		Topological topological = new Topological(G);
		for ( int v : topological.order())
			for ( DirectedEdge e : G.adj(v))
				relax(e);		
	}
	
	private void relax(DirectedEdge e){
		int v = e.from(), w = e.to();
		
		if ( distTo[w] > distTo[v] + e.weight()){
			distTo[w] = distTo[v] + e.weight();
			edgeTo[w] = e;
		}		
	}
}