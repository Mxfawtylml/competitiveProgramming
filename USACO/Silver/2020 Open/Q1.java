//Solved 10/10

import java.util.*;
import java.io.*;

public class Q1{
    public static int n;
    public static int m;
    public static long[][] arr;
    public static void main(String[] args){

        BufferedReader reader = null;
        try{
            //Reading
            reader = new BufferedReader(new FileReader("socdist.in"));

            String[] temp = reader.readLine().split(" ");
            n = Integer.parseInt(temp[0]);
            m = Integer.parseInt(temp[1]);

            arr = new long[m][2];

            for(int i=0; i<m; i++){
                temp = reader.readLine().split(" ");
                arr[i][0] = Long.parseLong(temp[0]);
                arr[i][1] = Long.parseLong(temp[1]);
            }

            //Algo
            
            Arrays.sort(arr, (a,b) -> Long.compare(a[0], b[0]));


            long left = 0;
            long right = arr[m-1][1];
            long prevpossible = 0;

            while(left<=right){
                long middle = (left+right)/2;

                if(distanceValid(middle)){
                    left = middle+1;
                    prevpossible = middle;
                } else{
                    right = middle-1;
                }
            }
               

            try {
                //Output
                FileWriter writer = new FileWriter("socdist.out");

                writer.write(prevpossible + System.lineSeparator());

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

    public static boolean distanceValid(long k){
        
        int count = n;
        long curr = 0;
        
        for(int i=0; i<m && count>0; i++){
            if(curr<arr[i][0]){
                curr = arr[i][0];
            }
            
            while(curr>=arr[i][0] && curr<=arr[i][1]){

                count--;    
                curr+=k;
            }
        }
        
        return count<=0;

    }
}
  
