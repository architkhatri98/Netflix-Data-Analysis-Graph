/*

This is the main function that will run Graph Operations on Simulated Graphs.
This class contains UI (User Interface) that will ask input from user about
what type of graph does the user wants to make and then it will ask what 
type of operation does the user wants to run on the simulated graph created
by the user.

First of all, user has to make a choice by typing the number ex- '4' for Heap graph
between what type of graph to be made in the following choices:
1. N-Cycle graph -
    input number of nodes
2. Complete graph - 
    input number of nodes
3. Empty graph - 
    input number of nodes
4. Heap graph - 
    input number of nodes
5. Truncated Heap graph -
    input number of nodes
    input value of 'm'
6. Equivalence Mod K graph - 
    input number of nodes
    input value of 'k'

After this user will be given three choices between following operations:
1. Find Connected Components
2. Find Cycle in Graph
3. Find Shortest Path
    User has to input starting node here

After the desired operation has been executed, then the user shall be
redirected to operations again where he has a choice to run further
operations or go back to the previous menu by pressing any key.
Same follows for all the menus.

*/

package AOA_completeFinal;
import java.util.*;
public class Simulated_test{

    public static void UI_Operation(HashSet<Integer>[] adjList) {
/*
        UI function to select and run the desired Graph Operation on the simulated graph
*/

        Scanner scn = new Scanner(System.in);
        while (true) {
            System.out.println("Enter the operation you want to run on your graph: ");
            System.out.println("1: Find Connected Components");
            System.out.println("2: Find a Cycle in the Graph");
            System.out.println("3: Shortest Path between a Start node to all other nodes");
            System.out.println("Press any other key to go back");
            String choice = scn.next();
            switch(choice) {
                case "1": {
                    Graph_operations.countComponents(adjList.length, adjList);
                    break;
                }
                case "2": {
                    Graph_operations.isCyclic(adjList.length, adjList);
                    break;
                }
                case "3": {
                    System.out.println("Enter starting node: ");
                    int start = scn.nextInt();
                    Graph_operations.dijkstra(adjList, start);
                    break;
                }
                default: {return;}
            }
        }
    }

    private static void addEdges(int start, int end, HashSet<Integer>[] adjList) {
        adjList[start].add(end);
        adjList[end].add(start);
    }

    public static void UI_Manual() {
        /*
            UI function to help build the user a graph manually if desired.
        */
        Scanner scn = new Scanner(System.in);
        while (true) {
            //scn.reset();
            System.out.println("Press 1 to continue, press any other key to return to previous menu");
            String choice = scn.next();

            if (choice.charAt(0) == '1') {
                System.out.println("Enter number of nodes: ");
                int total_nodes = scn.nextInt();
                // create a function to create an array hashset of nodes (adjList)
                System.out.println("Enter number of edges: ");
                // create a function to user input edges in the above adjList
                int total_edges = scn.nextInt();
                // create a function to write edges in above adjList
                HashSet<Integer>[] adjList = new HashSet[total_nodes];

                for (int i = 0; i < total_nodes; i++) {
                    adjList[i] = new HashSet<>();
                }

                while (total_edges-- > 0) {
                    System.out.println("Enter source and destination (Both on the same line): ");
                    int source = scn.nextInt();
                    int destination = scn.nextInt();
                    addEdges(source, destination, adjList);
                }
                System.out.println("Going to graph operations: ");
                UI_Operation(adjList);
            }
            else {return;}
        }
    }


    public static void UI_Simulator() {
        /*
            UI function to select and build the desired simulated graph (adjacency list) and pass it to UI operations.
        */
        Scanner scn = new Scanner(System.in);
        while(true) {
            System.out.println("Enter the graph simulation you want to run: ");
            System.out.println("1: n-cycle graph");
            System.out.println("2: complete graph");
            System.out.println("3: empty graph");
            System.out.println("4: heap graph");
            System.out.println("5: truncated heap graph");
            System.out.println("6: equivalence mod k graph");
            System.out.println("Enter any key to go to previous menu");
            String choice = scn.next();

            switch(choice) {
                case "1": {
                    System.out.println("Enter number of nodes for N-Cycle");
                    int n = scn.nextInt();
                    //System.out.println("Select the operation: ");
                    if (n == 0) {
                        System.out.println("Number of nodes cannot be 0");                        
                    }
                    else 
                        UI_Operation(Graph_simulator.nCycle(n));
                    break;
                }
                case "2": {
                    System.out.println("Enter number of nodes for Complete Graph");
                    int n = scn.nextInt();
                    if (n == 0) {
                        System.out.println("Number of nodes cannot be 0");                        
                    }
                    else 
                        UI_Operation(Graph_simulator.Complete_graph(n));
                    break;
                }
                case "3": {
                    System.out.println("Enter number of nodes for Empty Graph");
                    int n = scn.nextInt();
                    if (n == 0) {
                        System.out.println("Number of nodes cannot be 0");                        
                    }
                    else 
                        UI_Operation(Graph_simulator.empty_graph(n));
                    break;
                }
                case "4": {
                    System.out.println("Enter number of nodes for Heap Graph");
                    int n = scn.nextInt();                
                    if (n == 0) {
                        System.out.println("Number of nodes cannot be 0");                        
                    }
                    else     
                        UI_Operation(Graph_simulator.heap(n));
                    break;
                }
                case "5": {
                    System.out.println("Enter the last node index 'n' (Starting from 1) for Truncated Heap Graph");
                    int n = scn.nextInt();
                    System.out.println("Enter 'm' for Truncated Heap Graph");
                    int m =  scn.nextInt();
                    if (n == 0) {
                        System.out.println("Number of nodes cannot be 0");                        
                    }
                    else 
                        UI_Operation(Graph_simulator.trunc_heap(n, m));
                    break;
                }
                case "6": {
                    System.out.println("Enter number of nodes for Equivalence Mod K Graph");
                    int n = scn.nextInt();
                    System.out.println("Enter the value of mod k");
                    int k = scn.nextInt();
                    if (n == 0) {
                        System.out.println("Number of nodes cannot be 0");                        
                    }
                    else 
                        UI_Operation(Graph_simulator.equivalence(n, k));
                    break;
                }
                default: {return;}
            }
        }        
    }


    public static void UI_Main() {
        /*
            Asks the user if they want to make a simulator graph or make a graph manually
        */
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Enter number from given choices:");
            System.out.println("1: Create Graph using simulator");
            System.out.println("2: Create a Graph manually");
            System.out.println("Enter any other key to exit");
            String choice = scan.nextLine();
            switch(choice) {
                //FILL IT
                case "1": {
                    UI_Simulator();
                    break;
                }
                case "2": {
                    UI_Manual();
                    break;
                }
                default: {return;}
            }
            //scan.next();
        }
    }
    public static void main(String[] args){
        // MAIN
        UI_Main();
    }
}