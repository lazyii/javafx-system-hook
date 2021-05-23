package org.rainday.xprinter;

/**
 * @author wyd
 * @version 1.0 edit by wyd at 2021-05-19 19:20:25
 * @date 2021-05-19 19:20:25
 */
public class MathJnaMain {

    public static void main(String[] args) throws Exception {
//        Math math = new Math();
//        System.out.println(math.add(1, 2));
        int r = MathLibrary.INSTANCE.add(1, 2);
        System.out.println(r);
        System.out.println(MathLibrary.INSTANCE.minus(1, 2));
        System.out.println(MathLibrary.INSTANCE.multiply(3, 2));
        System.out.println(MathLibrary.INSTANCE.divide(9,2));


    }

}
