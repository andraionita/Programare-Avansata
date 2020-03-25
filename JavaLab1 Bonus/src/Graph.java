/**
 * @author Ionita Andra Paula grupa 2A7
 * Laboratorul 1 Bonus
 * In clasa Graph am implementat metoda BFS care face o parcurgere in latime a nodurilor grafului.
 * S-a implemetat cu ajutorul listelor inlantuite.
 * BFS v-a returna o lista nodurilor parcurse si in ce ordine
 * Daca toate nodurile sunt returnate de BFS in vectorul mark inseamna ca graful este conex,
 * altfel trebuie afisate componentele conexe cu nodurile dintre ele. Facem acest lucru intr-un do while care
 * apeleaza functia BFS de mai multe ori incepand de la primul nod care nu se afla in componenta conexa anterioara. 
 * Trecem apoi prin vectorul de mark cu un for si afisam nodurile care apartin componentei conexe;
 */


import java.util.Queue;
import java.util.LinkedList;

public class Graph {
    public static int[] BFS(int[][] adjacencyMatrix, int vertexCount, int startVertex) {
        // array rezultat
        int[] mark = new int[vertexCount];

        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(startVertex);
        mark[startVertex] = 1;

        while (!queue.isEmpty()) {
            Integer current = queue.remove();

            for (int i = 0; i < vertexCount; ++i)
                if (adjacencyMatrix[current][i] == 1 && mark[i] == 0) {
                    mark[i] = 1;
                    queue.add(i);
                }
        }

        return mark;
    }


    public static void main(String[] args) {
        // se da matricea de adiacenta adjacencyMatrix[x][y]daca si numai daca exista un drum de la x la y
        int[][] adjacencyMatrix = new int[][]
                {
                        {1, 1, 1, 1, 0, 0, 0},
                        {1, 1, 1, 1, 0, 0, 0},
                        {1, 1, 1, 1, 0, 0, 0},
                        {1, 1, 1, 1, 1, 0, 0},
                        {1, 1, 1, 1, 0, 0, 0},
                        {0, 0, 0, 0, 0, 1, 1},
                        {0, 0, 0, 0, 0, 1, 1}
                };
        // Mark[i] e adevarat daca si numai daca nodul i apartine aceleasi componente conexe ca givenVertex

        int noVertices = 0;
        int[] connected = new int[7];
        int[] mark = new int[7];
	
        do {
            if (connected[noVertices] == 0) {
                mark = BFS(adjacencyMatrix, 7, noVertices);
              
                System.out.print("Componente conexe: " + noVertices + " ");

                for (int j = 0; j < 7; ++j)
                    if (mark[j] == 1 && j != noVertices) {
                        System.out.print(j + " ");
                        connected[j] = 1;
                    }
                System.out.println("");
            }
            noVertices++;
        } while (noVertices < 7);
    }
}
