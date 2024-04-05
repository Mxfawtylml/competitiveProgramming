//Solved 10/10 Cases

import java.util.*; 
import java.io.*;


public class Q1{
    public static void main(String[] args){

        BufferedReader reader = null;
        try{
            //Reading
            reader = new BufferedReader(new FileReader("cowdance.in"));
            //reader.readLine()

            String[] temp = reader.readLine().split(" ");
            int n = Integer.parseInt(temp[0]);
            int tMax = Integer.parseInt(temp[1]);

            int[] d = new int[n];
            for(int i=0; i<n; i++){
                d[i] = Integer.parseInt(reader.readLine());
            }
            
            //Algo (Binary Search)

            int left = 1; 
            int right = n;
            int smallestPossible = Integer.MAX_VALUE;

            while(left<=right){
                int middle = (left+right)/2;
                
                if(possible(middle, tMax, d)){
                    smallestPossible = Math.min(middle, smallestPossible);
                    right = middle-1; 
                } else{
                    left = middle+1;
                }
            }

            try {
                //Output
                FileWriter writer = new FileWriter("cowdance.out");
                
                //writer.write()
                //System.lineSeparator() 

                // writer.write(n + System.lineSeparator());
                // writer.write(tMax + System.lineSeparator());
                // writer.write(Arrays.toString(d));

                writer.write(smallestPossible + System.lineSeparator());
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

    public static boolean possible(int k, int tMax, int[] d){
        int t = 0;
        PriorityQueue<Integer> stage = new PriorityQueue<Integer>();

        //Fill up stage
        for(int i=0; i<k; i++){
            stage.add(d[i]);
        }

        //Calculate time
        for(int i=k; i<d.length; i++){
            int curr = stage.poll()-t;
            t += curr;
            stage.add(d[i] + t);
        }

        //Empty stage
        for(int i=0; i<k; i++){
            int curr = stage.poll()-t;
            t += curr;
        }

        return t<=tMax;

    }
}