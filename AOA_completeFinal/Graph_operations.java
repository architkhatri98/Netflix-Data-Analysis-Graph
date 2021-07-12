package AOA_completeFinal;
import java.util.*;

public class Graph_operations {
    
    public static int countComponents(int nodes_count,HashSet<Integer>[] adjList) {
        if(adjList==null){
           System.out.println("No. of components present are:" + nodes_count);
           System.out.println("Every node itself is a component as there are no edges present");
           return 0;
       }
           int components = 0;
           boolean[] v = new boolean[nodes_count];
           for(int i=0; i<nodes_count; i++){
               if(!v[i]){
                   components++;
           HashSet<Integer> set = new HashSet<>();
                   dfs(i, v, adjList,set);
           System.out.println(set + "" + "Size of this component set is: " + set.size());
               }
           }
           System.out.println("No. of components present: " + components);
           return components;
       }
       
       public static void dfs(int n, boolean[] v, HashSet<Integer>[] adjList,HashSet<Integer> set){
           v[n] = true;
            set.add(n);
           for(int edge: adjList[n]){
               if(!v[edge]){
                   dfs(edge, v, adjList, set);
               }
           }
       }

       static Boolean isCyclic(int nodes_count, HashSet<Integer> []adjList) 
       { 
           if (adjList == null || nodes_count < 3) {
                System.out.println("There exists no cycle ");
                return false;
           }
           Boolean visited[] = new Boolean[nodes_count]; 
           for (int i = 0; i < nodes_count; i++) 
               visited[i] = false; 

           for (int u = 0; u < nodes_count; u++) 
           { 
               if (!visited[u]) 
                   if (isCyclicUtil(u, visited, -1,adjList)) {
                       System.out.println("Cycle Detected between following nodes:- ");
                       for (int i = 0; i < nodes_count; i++) {
                           if (visited[i] == true)
                               System.out.print(i + " ");
                       }
                       System.out.println();
                       return true; 
                   }
                       
           } 
           System.out.println("There exists no cycle ");
           return false;
       }
       
       static Boolean isCyclicUtil(int node, Boolean visited[], int parent, HashSet<Integer>[] adjList) 
       { 

           visited[node] = true; 
           Integer i;
           Iterator<Integer> it = 
                   adjList[node].iterator(); 
           while (it.hasNext()) 
           { 
               i = it.next(); 
               if (!visited[i]) 
               {
                   if (isCyclicUtil(i, visited, node,adjList)) {
                       visited[i] = true;
                       return true;
                   }
               } 
               else if (i != parent)
                   return true; 
           } 
           return false; 
       }
	
	static ArrayList<Pair<Integer, Integer>>[] dijkstraHelper(HashSet<Integer>[] adjList, int node_count) {
        ArrayList<Pair<Integer, Integer>>[] adjListDij = new ArrayList[node_count];

        for (int i = 0; i < node_count; i++) {
            adjListDij[i] = new ArrayList<Pair<Integer, Integer>>();
        }
        int i = 0;

        for (HashSet<Integer> edges: adjList) {
            for (int edge: edges) {
                adjListDij[i].add(new Pair(edge, 1));
            }
            i++;
        }

        return adjListDij;
    }

    static void printAdjDij(ArrayList<Pair<Integer, Integer>>[] adjListDij) {
        for (int i = 0; i < adjListDij.length; i++) {
            for (Pair<Integer, Integer> p: adjListDij[i]) {
                System.out.print(i + " ");
                System.out.println(p);
            }
            System.out.println();
        }
    }

    static void dijkstra(HashSet<Integer>[] adjList, int start) {
        if (adjList.length < 2) {
            System.out.println("There exists no Path");
            return;
        }
        ArrayList<Pair<Integer, Integer>>[] adjListDij = dijkstraHelper(adjList, adjList.length);
        List<Pair<Integer,Integer>> list = new ArrayList<>();
        int[] path = new int[adjListDij.length];
        int[] distance = new int[adjListDij.length];
        boolean[] is_added = new boolean[adjListDij.length];

        Arrays.fill(distance,Integer.MAX_VALUE);
        Arrays.fill(is_added,false);
        
        distance[start]=0; 
        path[start]=-1; 
        
        PriorityQueue<Pair<Integer,Integer>> q=new PriorityQueue<>((a,b) -> a.getSecond()-b.getSecond());
        q.add(new Pair<>(start,0));
        
        while(!q.isEmpty()){
            Pair<Integer,Integer> vertexPair=q.remove();
            Integer vertex=vertexPair.getFirst();

            List<Pair<Integer,Integer>> adjVertices=adjListDij[vertex];

            for(Pair<Integer,Integer> adjPair: adjVertices){
                int adjVertex=adjPair.getFirst();
                int weight=adjPair.getSecond();
                
                int newDistance=distance[vertex] + weight;
                if(distance[adjVertex]==-1 || distance[adjVertex]>newDistance){
                    distance[adjVertex]=newDistance;
                    path[adjVertex]=vertex;
                    q.add(new Pair<>(adjVertex,distance[adjVertex]));
                }
            }
        }
        
        printSolution(start, distance, path);
        System.out.println();

        /* System.out.println("Distance from "+start+" :");
        for(int i=0;i<adjListDij.length; i++){
            System.out.print("Distance to "+i+" is "+distance[i]);
            System.out.println(" from path "+path[i]);
        } */
    }
    
 private static void printSolution(int startVertex, 
                                      int[] distances, 
                                      int[] parents) 
    { 
        int nVertices = distances.length; 
        System.out.print("Vertex\t\t Distance\tPath"); 
          
        for (int vertexIndex = 0;  
                 vertexIndex < nVertices;  
                 vertexIndex++)  
        { 
            if (vertexIndex != startVertex)  
            { 
                System.out.print("\n" + startVertex + " -> "); 
                System.out.print(vertexIndex + " \t\t "); 
                if (distances[vertexIndex] == Integer.MAX_VALUE) {
                    System.out.print("Unreachable    "); 
                    System.out.print("\t");
                }
                else {
                    System.out.print(distances[vertexIndex] + "\t\t"); 
                    printPath(vertexIndex, parents); 
                }
            } 
        } 
    } 
  
    private static void printPath(int currentVertex, 
                                  int[] parents) 
    { 
        if (currentVertex == -1) 
        { 
            return; 
        } 
        printPath(parents[currentVertex], parents); 
        System.out.print(currentVertex + " "); 
    } 
    

}
