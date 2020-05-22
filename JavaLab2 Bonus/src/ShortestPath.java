import java.util.*;
import java.lang.*;
import java.io.*;
/**
 * @author Ionita Andra Paula grupa 2A7
 * Laborator 2 Bonus
 */
class ShortestPath {
	//aflam nodul cu valoarea costului minima dintre nodurile care nu au fost incare incluse
    static final int V = 9;

    int mincostance(int cost[], Boolean visited[]) {
        // Initialize min value 
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++)
            if (visited[v] == false && cost[v] <= min) {
                min = cost[v];
                min_index = v;
            }
        return min_index;
    }

    void printSolution(int cost[], int n) {
        System.out.println("Client   Costul turului");
        for (int i = 1; i < V; i++)
            System.out.println(i + " \t\t " + cost[i]);
    }
    
    void Dijkstra(int graph[][], int sourceVertex) {
        int u, copy=0;
        System.out.print("Clients in order: ");
        int cost[] = new int[V]; // costul minim de la sourceVertex la i  = output array

       //visited[i] va deveni true daca nodul i este inclus in MST sau costul minim din sourceVertex pana la i este gata
	   Boolean visited[] = new Boolean[V];
        
        for (int i = 0; i < V; i++) {
            cost[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

//costul de la nodul sursa la el insusi e 0

        cost[sourceVertex] = 0;

		//Cautam cel mai ieftin drum pentru toate nodurile
        for (int count = 0; count < V - 1; count++) {
         
			//alegem nodul cu costul minim din setul de noduri care nu a fost inca ales.
			//u este egal cu nodul sursa la prima iterare
            u = mincostance(cost, visited);

    
			//marcam nodurile pe masura ce sunt procesate
            visited[u] = true;
            if(graph[copy][u]!=-1)
                System.out.print(u + " -> ");
            else
            {
                System.out.println(" ");
                System.out.print(u + " -> ");
            }

			//actualizam costul nodurilor adiacente dintre nodurile alese
            for (int v = 0; v < V; v++) {
             
				//Actualizam costul[v] doar daca v nu este vizitat
				//aplicam in principiul algoritmul Dijkstra de la grafuri
                if (!visited[v] && graph[u][v] != -1 &&
                        cost[u] != Integer.MAX_VALUE && cost[u] + graph[u][v] < cost[v]) {
                    cost[v] = cost[u] + graph[u][v];
                }
            }
            copy=u;
        }
        u = mincostance(cost, visited);
        System.out.print(u);
        System.out.println("");
        printSolution(cost, V);
    }
}