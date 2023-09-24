package com.yz.furn.test;

import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String strReg = "^[a-zA-Z_$ | \u4e00-\u9fa5][\\w_$ | \u4e00-\u9fa5]{0,9}$";
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            System.out.println(str.matches(strReg));
        }
    }
}
