package org.rainday.xprinter.case1;

import com.github.anastaciocintra.escpos.EscPos;
import com.github.anastaciocintra.output.PrinterOutputStream;
import java.io.IOException;
import javax.print.PrintService;

/**
 * @author wyd
 * @version 1.0 edit by wyd at 2021-05-23 0:08:53
 * @date 2021-05-23 0:08:53
 */
public class CoffeeStateMain {

    public static void main(String[] args) throws IOException {
        PrintService printService = PrinterOutputStream.getPrintServiceByName("XP-58");
        PrinterOutputStream printerOutputStream = new PrinterOutputStream(printService);

        EscPos escpos = new EscPos(printerOutputStream);
        escpos.initializePrinter();
        byte[] queryState = new byte[]{27, 118};
        escpos.write(queryState, 0, queryState.length);
        escpos.close();
    }

}
