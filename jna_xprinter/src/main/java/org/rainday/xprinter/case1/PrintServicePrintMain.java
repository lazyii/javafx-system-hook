package org.rainday.xprinter.case1;

import java.awt.print.PrinterJob;
import java.nio.charset.Charset;
import java.util.SortedMap;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.SimpleDoc;

/**
 * @author wyd
 * @version 1.0 edit by wyd at 2021-05-23 12:09:24
 * @date 2021-05-23 12:09:24
 */
public class PrintServicePrintMain {

    public static void main(String[] args) throws InterruptedException {
//        PrintService printService = getPrintService("XP-58");
//
//        DocFlavor df = DocFlavor.INPUT_STREAM.AUTOSENSE;
//        Doc d = new SimpleDoc(pipedInputStream, df, null);
//
//        DocPrintJob job = printService.createPrintJob();
//        job.print(d, null);



        // find the printService of name printerName
        PrintService service = getPrintService("XP-58");
        DocPrintJob job = service.createPrintJob();
        String text = "hello  英特纳雄儿";
        try {
            DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
            byte[] textBytes;
            // important for umlaut chars
            SortedMap<String, Charset> charsetSortedMap = Charset.availableCharsets();
            textBytes = text.getBytes("gb2312");
            byte[] r = mergeBytes(textBytes, new byte[]{0x0a});
            Doc doc = new SimpleDoc(r, flavor, null);


            job.print(doc, null);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Thread.sleep(2000);

    }

    public static byte[] mergeBytes(byte[] bt1, byte[] bt2) {
        byte[] bt3 = new byte[bt1.length + bt2.length];
        System.arraycopy(bt1, 0, bt3, 0, bt1.length);
        System.arraycopy(bt2, 0, bt3, bt1.length, bt2.length);
        return bt3;
    }

    public static PrintService getPrintService(String printerName) {
        PrintService[] printServices = PrinterJob.lookupPrintServices();
        PrintService foundService = null;

        for (PrintService service : printServices) {
            if (service.getName().equalsIgnoreCase(printerName)) {
                foundService = service;
                break;
            }
        }
        return foundService;

    }

}
