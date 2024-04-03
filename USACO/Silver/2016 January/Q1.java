//Passed 10/10 cases

import java.util.*;
import java.io.*;

public class Q1{
    public static int n;
    public static int k;
    public static int[] arr;

    public static void main(String[] args){

        BufferedReader reader = null;
        try{
            //Reading
            reader = new BufferedReader(new FileReader("angry.in"));
            String[] temp = reader.readLine().split(" ");
            n = Integer.parseInt(temp[0]);
            k = Integer.parseInt(temp[1]);
            arr = new int[n];

            for(int i=0; i<n; i++){
                arr[i] = Integer.parseInt(reader.readLine());
            }

            //Algo
            Arrays.sort(arr);

            int left = 0;
            int right = arr[n-1]/2;
            int smallestPossible = Integer.MAX_VALUE;

            while(left<=right){
                int middle = (left+right)/2;
                if(func(middle)){
                    smallestPossible = middle;
                    right = middle-1;
                } else{
                    left = middle+1;
                }
            }

            try {
                //Output
                FileWriter writer = new FileWriter("angry.out");

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
    public static boolean func(int power){
        int startingInt = 0;
        int bombsLeft = k;

        for(int i=0; i<n; i++){
            if(bombsLeft <= 0){
                return false;
            }

            if(arr[i] - arr[startingInt] > 2*power){
                bombsLeft--;
                startingInt = i;
            }

        }
        return bombsLeft>0;
    }
} 