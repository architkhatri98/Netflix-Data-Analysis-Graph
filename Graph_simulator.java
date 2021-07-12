// Contains 6 functions to generate adj list on user provided criteria

package AOA_completeFinal;
import java.util.*;
import java.util.Scanner;

public class Graph_simulator {
    
    //N-Cycle 
    public static HashSet<Integer>[] nCycle (int n) {

        HashSet<Integer>[] adjList = new HashSet[n];

        for (int i = 0; i < n; i++) {
            adjList[i] = new HashSet<>();
        }        
        if (n == 1) return adjList;
        adjList[0].add(n-1);
        adjList[0].add(1);
        adjList[n-1].add(0);
        adjList[n-1].add(n-2);
        for (int u = 1; u < n-1; u++) { 
                int v1 = u-1;
                int v2 = u+1;
                adjList[u].add(v1);
                adjList[u].add(v2);
                adjList[v1].add(u);
                adjList[v2].add(u);
        }
        return adjList;
    }

    // Complete Graph
    public static HashSet<Integer>[] Complete_graph (int n) {

        HashSet<Integer>[] adjList = new HashSet[n];

        for (int i = 0; i < n; i++) {
            adjList[i] = new HashSet<>();
        }
        
        for (int u = 0; u < n; u++) { 
            for(int v=u+1;v<n;v++){
                adjList[u].add(v);
                adjList[v].add(u);
            }
        }
        return adjList;
    }

    //Empty Graph
    public static HashSet<Integer>[] empty_graph (int n) {
        HashSet<Integer>[] adjList = new HashSet[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new HashSet<>();
        }
        return adjList;
    }

    //Heap
    public static HashSet<Integer>[] heap (int n) {

        HashSet<Integer>[] adjList = new HashSet[n];
        if (n < 1) return adjList;
        if (n == 1) {
            adjList[0] = new HashSet<>();
            return adjList;
        }

        for (int i = 0; i < n; i++) {
            adjList[i] = new HashSet<>();
        }
        for (int u = 0; u < n; u++) { 
          int v1 = (u-1)/2;
          int v2 = 2*u+1;
          int v3= 2*u+2;
          if(v1>=0 && v1<n && u!=0){
              adjList[u].add(v1);
          }
          if(v2>=0 && v2<n){
            adjList[u].add(v2);
        }
        if(v3>=0 && v3<n){
            adjList[u].add(v3);
        }
        }
        return adjList;
    }

    //truncated heap
    public static HashSet<Integer>[] trunc_heap (int n,int m) {

        HashSet<Integer>[] adjList = new HashSet[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new HashSet<>();
        }

        for (int u = m; u < n; u++) { 
          int v1 = (u-1)/2;
          int v2 = 2*u+1;
          int v3= 2*u+2;
          if(v1>=m && v1<n && u!=m){
              adjList[u].add(v1);
          }
          if(v2>=m && v2<n){
            adjList[u].add(v2);
        }
        if(v3>=m && v3<n){
            adjList[u].add(v3);
        }
        }
        return adjList;
    }

    //equivalence mod k
    public static HashSet<Integer>[] equivalence (int n,int k) {

        HashSet<Integer>[] adjList = new HashSet[n];
       
        for (int i = 0; i < n; i++) {
            adjList[i] = new HashSet<>();
        }
        for (int u = 0; u < n; u++) { 
            for(int v=u+1;v<n;v++){
                if((v-u)%k==0){
                    adjList[u].add(v);
                    adjList[v].add(u);
                }
            }
        }
        return adjList;
    }
    

    public static void printAdj(HashSet<Integer>[] adjList) {
        for (int i = 0; i < adjList.length; i++) {
            System.out.print("i = " + i + " ");
            System.out.println(adjList[i]);
        }
    }
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter number of nodes: ");
        int n = scn.nextInt();
        System.out.println("Enter k for equivalence ");
        int k = scn.nextInt();

        HashSet<Integer>[] adjList = equivalence(n,k);
        printAdj(adjList);
    }

}