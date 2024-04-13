//15/15 test cases solved

import java.util.*;

public class Q2{
    
    public static int n;
    public static int k;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        k = scan.nextInt();

        Set<Integer> cycleSet = new HashSet<>();

        int[] a = new int[k];
        Map<Integer, Integer> b = new HashMap<>();
        Map<Integer, Integer> bRev = new HashMap<>();

        for (int i = 0; i < k; i++) {
            a[i] = scan.nextInt();
            cycleSet.add(a[i]);
        }

        scan.nextLine();

        for (int i = 0; i < k; i++) {
            int x = scan.nextInt();
            b.put(x, i);
            bRev.put(x, k-i-1);
            cycleSet.add(x);
        }

        scan.close();

        //Algo

        int notInCycle = n-cycleSet.size();

        int[] shiftCount = new int[k];
        int[] revShiftCount = new int[k];

        for(int i=0; i<k; i++) {
            if(b.containsKey(a[i])) {
                int j = b.get(a[i]);
                if(i <= j) {
                    shiftCount[j-i]++;
                } else {
                    shiftCount[j-i+k]++;
                }
            }

            if(bRev.containsKey(a[i])) {
                int j = bRev.get(a[i]);
                if(i <= j) {
                    revShiftCount[j-i]++;
                } else {
                    revShiftCount[j-i+k]++;
                }
            }
        }

        int max = 0;

        for(int i=0; i<k; i++){
            max = Math.max(max, Math.max(shiftCount[i], revShiftCount[i]));
        }

        System.out.println(notInCycle + max);

    }

}