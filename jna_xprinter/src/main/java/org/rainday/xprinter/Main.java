package org.rainday.xprinter;

import java.io.IOException;

/**
 * @author wyd
 * @version 1.0 edit by wyd at 2021-05-19 19:20:25
 * @date 2021-05-19 19:20:25
 */
public class Main {

    public static void main(String[] args) throws IOException {
//        Math math = new Math();
//        System.out.println(math.add(1, 2));
        int r = Math.INSTANCE.add(1, 2);
        System.out.println(r);
        System.out.println(Math.INSTANCE.minus(1, 2));
        System.out.println(Math.INSTANCE.multiply(3, 2));
        System.out.println(Math.INSTANCE.divide(9,2));
    }

}
