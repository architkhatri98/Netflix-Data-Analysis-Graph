/*
  Function that will read the input file from the user and ask 
  what Adjacency criteria has to be made then build a Adjacency list
  and return it.

  Adjacency list is of the form HashSet<Integer>[] 
*/

package AOA_completeFinal;
import java.util.*;
import java.io.*;
public class Graph_make
 {
    
    public static HashSet<Integer>[] readFileAndCreateAdjList() {
        HashMap<Integer, HashSet<Pair<Integer, Integer>>> map_og = new HashMap<>();  // map_og= map of movie-id,Set of pair of cust and rating
        HashMap<Integer,Integer> index_map = new HashMap<>();    // index-mapping (3rd hashmap)
        HashMap<Integer,HashSet<Integer>> sec_map = new HashMap<>();   // second hashmap for cust_id and set of m-ids
        HashSet<Integer>[] dummy = new HashSet[1];
        dummy[0] = new HashSet<Integer>();
        Scanner scn = new Scanner(System.in);

        System.out.println("Enter the file name you want to read: ");
        String filename = scn.next();

        int cur_m_id = -1;
        int index = 0;
        try {
        FileInputStream myObj = new FileInputStream(filename);
        Scanner myReader = new Scanner(myObj);
        int totalEnteries = 7500;
        while (myReader.hasNextLine() && totalEnteries-- >= 0) {
            //if (totalEnteries % 1000 == 0) System.out.println("Enteries found: " + totalEnteries);
            String data = myReader.nextLine();
            int len = data.length();
            if(data.charAt(len-1)==':'){
            String[] movie_id_separator = new String[1];
            movie_id_separator = data.split(":");
            String current_m_id = movie_id_separator[0];
            cur_m_id = Integer.parseInt(current_m_id);
            map_og.put(cur_m_id,new HashSet<Pair<Integer,Integer>>());
            }
            else{
            String[] Comma_Separator = new String[3];
            Comma_Separator = data.split(",");
            String cust_id = Comma_Separator[0];
            int c_id = Integer.parseInt(cust_id);

            // for index mapping 
            if(!index_map.containsKey(c_id)){                       
            index_map.put(c_id,index);
            index++;
            }
            
            // for hashmap of cust_id and set of movie_id
            HashSet<Integer> movie_set = new HashSet<>();           
            movie_set.add(cur_m_id);
            if(!sec_map.containsKey(c_id)){
            sec_map.put(c_id,movie_set);
            }
            else{
                HashSet<Integer> helper_set = new HashSet<>();
                helper_set = sec_map.get(c_id);
                helper_set.add(cur_m_id);
                sec_map.put(c_id,helper_set);
            }
            
            String rating = Comma_Separator[1];
            int rate = Integer.parseInt(rating);
            
            // for map_og
            HashSet<Pair<Integer,Integer>> helper_map_og = new HashSet<>();
            
            helper_map_og = map_og.get(cur_m_id);
            helper_map_og.add(new Pair(c_id,rate));
            map_og.put(cur_m_id,helper_map_og);
            }
            //System.out.println(data);
        }
        myReader.close();
        } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        System.exit(1);
        //e.printStackTrace();
        }

        System.out.println("Enter the criteria that you want to run");
        System.out.println("Press '1' for first criteria  -- Two Customers have watched the same movie --");
        System.out.println("Press '2' for second criteria -- Two Customers have watched the same movie and rated the movie 4 or 5 star --");
        System.out.println("Press '3' for third criteria  -- Two Customers have watched the same movie and rated the movie between 1 to 3--");
        String choice = scn.next();
        HashSet<Integer>[] adjList;

        switch(choice) {
            case "1": {
                adjList = makeAdjFirst(map_og, index_map, sec_map);
                break;
            }
            case "2": {
                adjList = makeAdjSecond(map_og, index_map, sec_map);
                break;
            }
            case "3": {
                adjList = makeAdjThird(map_og, index_map, sec_map);
                break;
            }
            default: {
                return dummy;
            }
        }

        return adjList;

    }

  public static HashSet<Integer>[] makeAdjFirst(HashMap<Integer, HashSet<Pair<Integer, Integer>>> map_og,HashMap<Integer,Integer> index_map, HashMap<Integer,HashSet<Integer>> sec_map) {
    HashSet<Integer>[] adjList = new HashSet[index_map.size()];
    //int hashMapEnteries = 0;
    for(int key: sec_map.keySet()){
      HashSet<Integer> set = sec_map.get(key);
      //System.out.println(key);
      for(int i: set){
          HashSet<Pair<Integer,Integer>> set_2 = map_og.get(i);
          for(Pair<Integer,Integer> p : set_2){
              int cust_id = p.getFirst();
              if(cust_id!= key){
                //System.out.println(cust_id);
                //System.out.println(adjList[index_map.get(key)]);
                HashSet<Integer> set1 = new HashSet<Integer>();
                //hashMapEnteries++;
              /* if (hashMapEnteries % 5000 == 0) {
                System.out.println("Enteries done: " + hashMapEnteries);
              } */
                if (adjList[index_map.get(key)] == null) {
                  set1.add(index_map.get(cust_id));
                  adjList[index_map.get(key)] = set1;
                }
                else {
                  adjList[index_map.get(key)].add(index_map.get(cust_id)); //822109
                }
              }
          }
      } 
    }

    return adjList;
  }

  public static HashSet<Integer>[] makeAdjSecond(HashMap<Integer, HashSet<Pair<Integer, Integer>>> map_og,HashMap<Integer,Integer> index_map, HashMap<Integer,HashSet<Integer>> sec_map) {
    HashSet<Integer>[] adjList2 = new HashSet[index_map.size()]; //Total Nodes?

    
    for(int key: sec_map.keySet()){
        HashSet<Integer> set = sec_map.get(key);
        //System.out.println(key);
        for(int i: set){
            HashSet<Pair<Integer,Integer>> set_2 = map_og.get(i);
            for(Pair<Integer,Integer> p : set_2){
                int cust_id = p.getFirst();
                int curr_custRating = p.getSecond();
                if (curr_custRating >= 4) {
                  if(cust_id!= key){
                    //System.out.println(cust_id);
                    //System.out.println(adjList[index_map.get(key)]);
                    HashSet<Integer> set1 = new HashSet<Integer>();
                    if (adjList2[index_map.get(key)] == null) {
                      set1.add(index_map.get(cust_id));
                      adjList2[index_map.get(key)] = set1;
                    }
                    else {
                      adjList2[index_map.get(key)].add(index_map.get(cust_id)); //822109
                    }
                  }
                }              
            }
        }
    }
    return adjList2;
  }

  public static HashSet<Integer>[] makeAdjThird(HashMap<Integer, HashSet<Pair<Integer, Integer>>> map_og,HashMap<Integer,Integer> index_map, HashMap<Integer,HashSet<Integer>> sec_map) {
      
    HashSet<Integer>[] adjList3 = new HashSet[index_map.size()];
    
    for(int key: sec_map.keySet()){
      HashSet<Integer> set = sec_map.get(key);
      //System.out.println(key);
      for(int i: set){
          HashSet<Pair<Integer,Integer>> set_2 = map_og.get(i);
          for(Pair<Integer,Integer> p : set_2){
              int cust_id = p.getFirst();
              int curr_custRating = p.getSecond();
              if (curr_custRating <= 3) {
                if(cust_id!= key){
                  //System.out.println(cust_id);
                  //System.out.println(adjList[index_map.get(key)]);
                  HashSet<Integer> set1 = new HashSet<Integer>();
                  if (adjList3[index_map.get(key)] == null) {
                    set1.add(index_map.get(cust_id));
                    adjList3[index_map.get(key)] = set1;
                  }
                  else {
                    adjList3[index_map.get(key)].add(index_map.get(cust_id)); //822109
                  }
                }
              }              
          }
      }
    }
    return adjList3;
  }

}


