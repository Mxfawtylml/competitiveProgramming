//Solved 10/10 cases
import java.util.*;
import java.io.*;

public class Q2{
    public static int n;
    public static int m;
    public static int[][] elevation;
    public static ArrayList<int[]> waypoint;

    public static void main(String[] args){

        BufferedReader reader = null;
        try{
            //Reading
            reader = new BufferedReader(new FileReader("ccski.in"));
            //reader.readLine()
            String[] temp = reader.readLine().split(" ");
            m = Integer.parseInt(temp[0]);
            n = Integer.parseInt(temp[1]);

            elevation = new int[m][n];

            for(int i=0; i<m; i++){
                String[] s = reader.readLine().split(" ");
                for(int j=0; j<n; j++){
                    elevation[i][j] = Integer.parseInt(s[j]);
                }
            }

            waypoint = new ArrayList<int[]>();

            for(int i=0; i<m; i++){
                String[] s = reader.readLine().split(" ");
                for(int j=0; j<n; j++){
                    if(s[j].equals("1")){
                        waypoint.add(new int[]{i,j});
                    }
                }
            }

            //Algo
            int left = 0;
            int right = 1000000000;
            int ans = 0;

            while(left<=right){
                int middle = (left+right)/2;

                if(floodfill(middle)){
                    right = middle-1;
                    ans = middle;
                } else{
                    left = middle+1;
                }
            }
            
            try {
                //Output
                FileWriter writer = new FileWriter("ccski.out");

                writer.write(ans + System.lineSeparator());

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
    public static boolean floodfill(int d){
        
        Stack<int[]> stack = new Stack<>();
        int[] start = waypoint.get(0);
        stack.push(start);
        boolean[][] visited = new boolean[m][n];

        visited[start[0]][start[1]] = true;
        while(!stack.isEmpty()){
            int[] curr = stack.pop();
            int r = curr[0];
            int c = curr[1];


            if (r > 0 && !visited[r - 1][c] && Math.abs(elevation[r-1][c] - elevation[r][c]) <= d) {
                stack.push(new int[]{r - 1, c});
                visited[r-1][c] = true;
            }
            if (c > 0 && !visited[r][c - 1] && Math.abs(elevation[r][c-1] - elevation[r][c]) <= d) {
                stack.push(new int[]{r, c - 1});
                visited[r][c-1] = true;
            }
            if (r < m - 1 && !visited[r + 1][c] && Math.abs(elevation[r+1][c] - elevation[r][c]) <= d) {
                stack.push(new int[]{r + 1, c});
                visited[r+1][c] = true;
            }
            if (c < n - 1 && !visited[r][c + 1] && Math.abs(elevation[r][c+1] - elevation[r][c]) <= d) {
                stack.push(new int[]{r, c + 1});
                visited[r][c+1] = true;
            } 
            
        }

        for(int[] w: waypoint){
            if(!visited[w[0]][w[1]]){
                return false;
            }
        }

        return true;
    }
}