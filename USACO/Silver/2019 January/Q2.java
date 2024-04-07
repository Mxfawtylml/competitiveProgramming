//11/11 Cases Solved

import java.util.*;
import java.io.*;

public class Q2{
    public static int Perimeter;
    public static int maxArea;
    public static int n;
    public static char[][] blobs;
    public static boolean[][] visited;

    public static void main(String[] args){

        BufferedReader reader = null;
        try{
            //Reading
            reader = new BufferedReader(new FileReader("perimeter.in"));

            n = Integer.parseInt(reader.readLine());
            blobs = new char[n][n];
            visited = new boolean[n][n];

            for(int i=0; i<n; i++){
                blobs[i] = reader.readLine().toCharArray();
            }

            //Algo

            maxArea = 0;
            Perimeter = 0;

            for(int r=0; r<n; r++){
                for(int c=0; c<n; c++){
                    if(blobs[r][c] == '#' && !visited[r][c]){
                        floodFill(r, c);
                    }
                }
            }

            try {
                FileWriter writer = new FileWriter("perimeter.out");

                writer.write(maxArea + " " + Perimeter + System.lineSeparator());

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

    public static void floodFill(int r, int c){
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{r, c});
        visited[r][c] = true;

        int currPerimeter = 0;
        int currArea = 0;

        while(!stack.isEmpty()){
            int[] curr = stack.pop();
            int row = curr[0];
            int col = curr[1];

            currArea++;
            currPerimeter += func(row, col);

            if (row > 0 && !visited[row - 1][col] && blobs[row - 1][col] == '#') {
                stack.push(new int[]{row - 1, col});
                visited[row-1][col] = true;
            }
            if (col > 0 && !visited[row][col - 1] && blobs[row][col - 1] == '#') {
                stack.push(new int[]{row, col - 1});
                visited[row][col-1] = true;
            }
            if (row < n - 1 && !visited[row + 1][col] && blobs[row + 1][col] == '#') {
                stack.push(new int[]{row + 1, col});
                visited[row+1][col] = true;
            }
            if (col < n - 1 && !visited[row][col + 1] && blobs[row][col + 1] == '#') {
                stack.push(new int[]{row, col + 1});
                visited[row][col+1] = true;
            } 
            
        }

        if(currArea>maxArea){
            Perimeter = currPerimeter;
            maxArea = currArea;
        } else if(currArea == maxArea){
            Perimeter = Math.min(Perimeter, currPerimeter);
        }

    }

    public static int func(int r, int c){
        int count = 4;

        if (r > 0 && blobs[r-1][c] == '#'){
            count--;
        } 
        if (c > 0 && blobs[r][c-1] == '#'){
            count--;
        }
        if (r < n-1 && blobs[r+1][c] == '#'){
            count--;
        }
        if (c < n-1 && blobs[r][c+1] == '#'){
            count--;
        }
        return count; 

    }

}