import java.util.*; //11/11

public class Q1{
    public static int n;
    public static int tc;
    public static int tm;
    public static long[][] arr;

    public static void main (String[] args){
        Scanner scan = new Scanner(System.in);
        
        int t = scan.nextInt();
        scan.nextLine();

        for(int asdf = 0; asdf<t; asdf++){
            scan.nextLine();

            n = scan.nextInt();
            tc = scan.nextInt();
            tm = scan.nextInt();
            scan.nextLine();

            arr = new long[n][3];

            for(int i=0; i<n; i++){
                arr[i][0] = scan.nextLong();
                arr[i][1] = scan.nextLong();
                arr[i][2] = scan.nextLong();
                scan.nextLine();
            }

            //algo

            long left = 0;
            long right = tc+tm-2;

            while(left<=right){
                long middle = (left+right)/2;

                if(canAfford(middle)){
                    right = middle-1;
                } else{
                    left = middle+1;
                }
            }

            System.out.println(left);
        }

        scan.close();
    }  

    public static boolean canAfford(long money){
        
        long lx = Math.max(1, tc-money);
        long hx = Math.min(tc+tm - money - 1, tc);

        for(int i=0; i<n; i++){
            long a = arr[i][0];
            long b = arr[i][1];
            long c = arr[i][2];
            long d = tc+tm-money;

            if(b-a >0){
                lx = Math.max(lx, (-c + b * d + (b-a-1))/(b-a));
            } else if(a-b>0){
                hx = Math.min(hx, (c-b*d)/(a-b));
            } else{
                if(a*d>c){
                    return false;
                }
            }
        }

        return lx<=hx;

    }
}


