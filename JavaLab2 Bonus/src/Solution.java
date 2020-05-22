import java.util.*;
/**
 * @author Ionita Andra Paula grupa 2A7
 * Laborator 2 Bonus
 */
public class Solution {
    private Problem problem;

    public Solution(Problem problem) {
        this.problem = problem;
    }


	 //algoritm simplu de alocare a calatoriilor 

    public void getSolution(Problem problem) {
        ArrayList<Client> clientArray = new ArrayList<Client>();
        ArrayList<Client> clientArray2 =  new ArrayList<Client>();
        Set<Integer> mySet = new HashSet<Integer>();
        for (Client c : this.problem.getClients()) {
            //System.out.println(c.getOrder());
            int size=mySet.size();
            mySet.add(c.getOrder());
            int newSize=mySet.size();
            if(newSize>size)
                clientArray.add(c);
        }

        System.out.println(mySet);
        System.out.println("Solutia greedy pentru problema: ");
        System.out.println(clientArray);
    }
}
