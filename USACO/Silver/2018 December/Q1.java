//10/10 test cases solved

import java.util.*;
import java.io.*;

public class Q1{
    public static void main(String[] args){

        BufferedReader reader = null;
        try{
            //Reading
            reader = new BufferedReader(new FileReader("convention.in"));
            //reader.readLine()
            String[] temp = reader.readLine().split(" ");

            int n = Integer.parseInt(temp[0]);
            int m = Integer.parseInt(temp[1]);
            int c = Integer.parseInt(temp[2]);

            String[] temp2 = reader.readLine().split(" ");

            int[] arr = new int[n];

            for(int i=0; i<n; i++){
                arr[i] = Integer.parseInt(temp2[i]);
            }
            Arrays.sort(arr);

            //Algo

            int left = 0;
            int right = arr[n-1] - arr[0];
            int middle = 0;

            while(left<=right){
                middle = (left+right)/2;
                if(func(middle, arr, m, c)){
                    right = middle-1; 
                } else{
                    left = middle+1;
                }
            }


            if(!func(middle, arr, m, c)){
                middle++;
            }

            try {
                //Output
                FileWriter writer = new FileWriter("convention.out");
                
                //writer.write()
                //System.lineSeparator()

                writer.write(middle + System.lineSeparator());

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
    public static boolean func(int t, int[] arr, int m, int c){
        int startInt = 0;
        int currWait = 0;

        for(int i=0; i<arr.length; i++){
            if(m<=0){
                return false;
            }

            currWait = arr[i] - arr[startInt];

            if(currWait>t){
                m-=1;
                startInt = i;
            } else if(i-startInt > c-1){
                m-=1;
                startInt = i;
            }
        }
        return m>0;
    }
}