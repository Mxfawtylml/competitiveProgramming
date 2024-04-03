//Solved 10/10 Cases

import java.util.*;
import java.io.*;

public class Q3{
    public static int n;
    public static boolean[][] horizontal;
    public static boolean[][] vertical;

    public static void main(String[] args){

        BufferedReader reader = null;
        try{
            //Reading
            reader = new BufferedReader(new FileReader("countcross.in"));
            String[] s1 = reader.readLine().split(" ");
            n = Integer.parseInt(s1[0]);
            int k = Integer.parseInt(s1[1]);
            int r = Integer.parseInt(s1[2]);

            int[][] cows = new int[k][2];
            horizontal = new boolean[n-1][n];
            vertical = new boolean[n][n-1];

            for(int i=0; i<r; i++){
                String[] s2 = reader.readLine().split(" ");
                int x1 = Integer.parseInt(s2[0])-1;
                int y1 = Integer.parseInt(s2[1])-1;
                int x2 = Integer.parseInt(s2[2])-1;
                int y2 = Integer.parseInt(s2[3])-1;

                if(x1==x2){
                    vertical[x1][Math.min(y1, y2)] = true;
                } else{
                    horizontal[Math.min(x1, x2)][y1] = true;
                }
            }

            for(int i=0; i<k; i++){
                String[] s3 = reader.readLine().split(" ");
                cows[i][0] = Integer.parseInt(s3[0])-1;
                cows[i][1] = Integer.parseInt(s3[1])-1;
            }

            //Algo

            int count = 0;

            for(int i=0; i<k; i++){
                for(int j=i+1; j<k; j++){
                    if(!connected(cows[i], cows[j])){
                        count++;
                    }
                }
            }

            try {
                //Output
                FileWriter writer = new FileWriter("countcross.out");

                writer.write(count+System.lineSeparator());

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

    public static boolean connected(int[] start, int[] end){
        Stack<int[]> stack = new Stack<>();
        stack.push(start);
        boolean[][] visited = new boolean[n][n];
        visited[start[0]][start[1]] = true;

        while(!stack.isEmpty()){
            int[] curr = stack.pop();
            if(Arrays.equals(curr, end)){
                return true;
            }

            int r = curr[0];
            int c = curr[1];

            if (r > 0 && !visited[r - 1][c] && !horizontal[r-1][c]) {
                stack.push(new int[]{r - 1, c});
                visited[r-1][c] = true;
            }
            if (c > 0 && !visited[r][c - 1] && !vertical[r][c-1]) {
                stack.push(new int[]{r, c - 1});
                visited[r][c-1] = true;
            }
            if (r < n - 1 && !visited[r + 1][c] && !horizontal[r][c]) {
                stack.push(new int[]{r + 1, c});
                visited[r+1][c] = true;
            }
            if (c < n - 1 && !visited[r][c + 1] && !vertical[r][c]) {
                stack.push(new int[]{r, c + 1});
                visited[r][c+1] = true;
            } 
        }

        return false;
    }
}