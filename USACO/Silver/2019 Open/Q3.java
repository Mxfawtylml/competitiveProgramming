//Template for file output
//10 out of 10 cases solved

import java.util.*;
import java.io.*;

public class Q3{
    public static void main(String[] args){

        BufferedReader reader = null;
        try{
            //Reading
            reader = new BufferedReader(new FileReader("fenceplan.in"));
            //reader.readLine()

            String[] temp = reader.readLine().split(" ");

            int n = Integer.parseInt(temp[0]);
            int m = Integer.parseInt(temp[1]);

            int[][] cowPos = new int[n][2];

            ArrayList<ArrayList<Integer>> cowGroup = new ArrayList<ArrayList<Integer>>();

            for(int i=0; i<n; i++){
                cowGroup.add(new ArrayList<Integer>());
            }


            for(int i=0; i<n; i++){

                temp = reader.readLine().split(" ");

                cowPos[i][0] = Integer.parseInt(temp[0]);
                cowPos[i][1] = Integer.parseInt(temp[1]);
            }        

            for(int i=0; i<m; i++){
                temp = reader.readLine().split(" ");

                int a = Integer.parseInt(temp[0])-1;
                int b = Integer.parseInt(temp[1])-1;

                cowGroup.get(a).add(b);
                cowGroup.get(b).add(a);
            }

            //Algo

            boolean[] visited = new boolean[n];
            
            int start = next(visited);
            int minPerimeter = Integer.MAX_VALUE;

            while(start != -1){
                
                int minx = cowPos[start][0];
                int miny = cowPos[start][1];
                int maxx = cowPos[start][0];
                int maxy = cowPos[start][1];
    
                Queue<Integer> bfs = new LinkedList<>();

                bfs.add(start);

                while(!bfs.isEmpty()){
                    
                    visited[bfs.peek()] = true;

                    for(Integer k : cowGroup.get(bfs.peek())){
                        if(!visited[k]){
                            bfs.add(k);

                            minx = Math.min(minx, cowPos[k][0]);
                            miny = Math.min(miny, cowPos[k][1]);
                            maxx = Math.max(maxx, cowPos[k][0]);
                            maxy = Math.max(maxy, cowPos[k][1]);

                        }
                    }
                    
                    bfs.remove();

                }

                // System.out.println(minx);
                // System.out.println(maxx);
                // System.out.println(miny);
                // System.out.println(maxy);


                minPerimeter = Math.min(minPerimeter, 2*(maxx-minx) + 2*(maxy-miny));

                start = next(visited);
            }
             

            try {
                //Output
                FileWriter writer = new FileWriter("fenceplan.out");
                
                //writer.write()
                //System.lineSeparator()

                writer.write(minPerimeter + System.lineSeparator());

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

    public static int next(boolean[] arr){
        for(int i=0; i<arr.length; i++){
            if(!arr[i]){
                return i;
            }
        }
        return -1;
    }
}