package org.rainday.example.xprinter;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 *
 * 芯烨(xprinter)打印机dll调用测试
 *
 * @author wyd
 * @version 1.0 edit by wyd at 2021-05-19 14:09:30
 * @date 2021-05-19 14:09:30
 */
public class Main {

    public interface XprinterLibrary extends Library {
        XprinterLibrary INSTANCE = Native.load("JsPrinterDll_64.dll", XprinterLibrary.class);

        void printf(String format, Object... args);
    }

    public static void main(String[] args) {
        XprinterLibrary.INSTANCE.printf("Hello, World\n");
    }



}
