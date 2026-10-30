package temp;
import java.util.Scanner;

public class Main {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
            }
            int c=0;
            for (int i=0; i<=n-2; ++i) {
                int minIndex=i;
                for (int j=i+1; j<=n-1; j++) {
                    if (arr[j]<arr[minIndex]) {
                        minIndex=j;
                    }
                }
            
            if (i != minIndex) {
                int t = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = t;
                ++c;
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i]);
            if (i < n - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
        System.out.println(c);
        scanner.close();
    }
}