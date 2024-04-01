//10/10 solved

import java.util.*;
import java.io.*;

public class Q3{
    public static int n;
    public static Map<Integer, int[]> adjList = new HashMap<Integer, int[]>();

    public static void main(String[] args){
    
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader("moocast.in"));
            n = Integer.parseInt(reader.readLine());
            for(int i=0; i<n; i++){
                String[] temp = reader.readLine().split(" ");
                adjList.put(i, new int[3]);

                adjList.get(i)[0] = Integer.parseInt(temp[0]);
                adjList.get(i)[1] = Integer.parseInt(temp[1]);
                adjList.get(i)[2] = Integer.parseInt(temp[2]);
            }

            //Algo
             
            int max = 0;

            for(int i=0; i<n; i++){
                max = Math.max(func(i), max);
            }

            try {
                FileWriter writer = new FileWriter("moocast.out");
                writer.write(max + System.lineSeparator());
                writer.close(); 
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null)
                reader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static int func(int start){
        Queue<Integer> bfs = new LinkedList<>();
        boolean[] visited = new boolean[n];

        int count = 0;

        bfs.add(start);

        while(!bfs.isEmpty()){

            visited[bfs.peek()]=true;

            for(Map.Entry<Integer, int[]> entry : adjList.entrySet()) {
                
                int key = entry.getKey(); 
                int[] value = entry.getValue();

                if(!visited[key]){
                    int[] curr = adjList.get(bfs.peek());

                    if(Math.pow(curr[0]-value[0], 2) + Math.pow(curr[1]-value[1], 2) <= Math.pow(curr[2],2) ){
                        bfs.add(key);
                    }
                }
            }

            bfs.remove();
        }

        for(int i=0; i<n; i++){
            if(visited[i])
                count++;
        }

        return count;
    }
}