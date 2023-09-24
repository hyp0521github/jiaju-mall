package com.yz.furn.test;

import java.util.Scanner;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class Test2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String choice = scanner.next();
            System.out.println("choice=" + choice);
            if (choice.equals("double")) {
                double a, b, c;
                a = scanner.nextDouble();
                b = scanner.nextDouble();
                c = scanner.nextDouble();
                System.out.printf("%-5.2f,%5.2f,%.2f\n", a, b, c);
            } else if (choice.equals("int")) {
                int a, b, c;
                a = scanner.nextInt();
                b = scanner.nextInt();
                c = scanner.nextInt();
                System.out.println(a + b + c);
            } else if (choice.compareTo("str") == 0) {
                scanner.nextLine();
                String str = scanner.nextLine();
                String[] arr = str.split("\\s+");
                //用str.split(" ")也行
                for (int i = 2; i >= 0; i--) {
                    System.out.print(arr[i]);
                }
                System.out.println();
            } else if (choice.equals("line")) {
                scanner.nextLine();
                String str = scanner.nextLine();
                System.out.println(str.toUpperCase());
            } else {
                System.out.println("other");
            }
        }
    }
}
