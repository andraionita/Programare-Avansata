package KnapsackProblem;

/**
 * @author Ionita Andra Paula grupa 2A7
 * Laborator 3 Bonus

 * Intuitiv, pentru fiecare dintre cele n itemi, trebuie să luăm o singură decizie, indiferent dacă
 * lua, acel articol sau nu. Astfel, orice nod trebuie să reprezinte un element. De asemenea,
 * avem o capacitate legată de greutatea rucsacului, de aceea trebuie să menținem și noi
 * un câmp de greutate în fiecare nod care reprezintă greutatea elementelor incluse.
 * Marginile ar trebui să reprezinte valoarea acelui articol.
 */
public class GraphModel {
    Problem p1;

    public GraphModel(Problem p1) {
        this.p1 = p1;
    }

    public static int getMinDistance(double distance[], Boolean chosen[], int V) {
        double minim = Integer.MAX_VALUE;
        int index = -1;

        for (int v = 0; v < V; v++)
            if (chosen[v] == false && distance[v] <= minim) {
                minim = distance[v];
                index = v;
            }

        return index;
    }

    public static double[] findShortestPath(double matrix[][], int src, int V) {

        double distance[] = new double[V];
        Boolean chosen[] = new Boolean[V];
        for (int i = 0; i < V; i++) {
            distance[i] = Integer.MAX_VALUE;
            chosen[i] = false;
        }
        distance[src] = 0;
        for (int count = 0; count < V - 1; count++) {
            int u = getMinDistance(distance, chosen, V);
            chosen[u] = true;
            for (int v = 0; v < V; v++)
                if (chosen[v] != true && matrix[u][v] != -1 && distance[u] != Integer.MAX_VALUE && distance[u] + matrix[u][v] < distance[v])
                    distance[v] = distance[u] + matrix[u][v];
        }
        return distance;
    }

    public int getRows() {
        int nbItems = p1.getItems().size();
        int rows = 1;
        while (nbItems != 1) {
            rows = (rows - 1) * 2 + 3;
            nbItems--;
        }
        return rows;
    }

    public int getColumns() {
        int columns = 0;
        int nbItems = p1.getItems().size();
        while (nbItems != 0) {
            columns = columns * 2 + 2;
            nbItems--;
        }
        columns++;
        return columns;
    }

    public double[][] getProfitMatrix() {
        int rows = this.getRows();
        int columns = this.getColumns();
        double[][] matrix = new double[rows][columns];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                matrix[i][j] = -1;
        int count = 1;
        int rownum = 0;
        while (count < columns) {
            matrix[rownum][count] = 0;
            rownum++;
            count += 2;
        }
        count = 2;
        rownum = 0;
        int targetObject = 0;
        matrix[rownum][count] = p1.getItems().get(targetObject).profitFactor();
        count += 2;
        rownum++;
        targetObject++;
        int quantity = 2;
        int copy = quantity;
        while (count < columns) {
            matrix[rownum][count] = p1.getItems().get(targetObject).profitFactor();
            count += 2;
            rownum++;
            copy--;
            if (copy == 0) {
                quantity *= 2;
                copy = quantity;
                targetObject++;
            }
        }
        return matrix;
    }

    private double[][] getWeightMatrix() {
        int rows = this.getRows();
        int columns = this.getColumns();
        double[][] matrix = new double[rows][columns];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                matrix[i][j] = -1;
        int count = 1;
        int rownum = 0;
        while (count < columns) {
            matrix[rownum][count] = 0;
            rownum++;
            count += 2;
        }
        count = 2;
        rownum = 0;
        int targetObject = 0;
        matrix[rownum][count] = p1.getItems().get(targetObject).getWeight();
        count += 2;
        rownum++;
        targetObject++;
        int quantity = 2;
        int copy = quantity;
        while (count < columns) {
            matrix[rownum][count] = p1.getItems().get(targetObject).getWeight();
            count += 2;
            rownum++;
            copy--;
            if (copy == 0) {
                quantity *= 2;
                copy = quantity;
                targetObject++;
            }
        }
        return matrix;
    }

    public void printMatrix() {
        double[][] matrix = getProfitMatrix();
        int rows = this.getRows();
        int columns = this.getColumns();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++)
                System.out.print(matrix[i][j] + "\t");
            System.out.println();
        }
    }

    public void getSolution() {
        double[][] profitMatrix = getProfitMatrix();
        double[][] weightMatrix = getWeightMatrix();
        int rows = this.getRows();
        int nbCols = 0;
        int colsBack = 0;
        int nbItems = p1.getItems().size();
        while (nbItems != 0) {
            colsBack = nbCols;
            nbCols = nbCols * 2 + 2;
            nbItems--;
        }
        colsBack++;
        nbCols -= colsBack;

        double[] profit = findShortestPath(profitMatrix, 0, rows).clone();
        double[] weight = findShortestPath(weightMatrix, 0, rows).clone();
        int total = 0;


        double max = -5;
        double actualWeight = 0;
        for (int i = 0; i < profit.length; i++) {
            if (profit[i] > max && weight[i] <= p1.getKnapsack().getCapacity()) {
                max = profit[i];
                //System.out.println(max);
                actualWeight = weight[i];
                total += profit[i] * weight[i];
            }
        }
        System.out.println("Profitul maxim este  " + max + " iar greutatea totala a lor este " +
                (int) actualWeight);
    }
}