package org.rainday.xprinter.case2;

import com.google.zxing.WriterException;
import org.rainday.xprinter.case2.utils.PrinterModel;
import org.rainday.xprinter.case2.utils.XprinterLibrary;
import org.rainday.xprinter.case2.utils.BitmapToByteData.AlignType;
import org.rainday.xprinter.case2.utils.DataForSendToPrinterPos58;
import org.rainday.xprinter.case2.utils.ZXingUtil;

/**
 * @author wyd
 * @version 1.0 edit by wyd at 2021-05-23 12:04:10
 * @date 2021-05-23 12:04:10
 */
public class XprinterJna_JsPrinter_dll_Main {

    public static void main(String[] args) throws WriterException {

        XprinterLibrary xprinter = XprinterLibrary.INSTANCE;
        int fs = xprinter.uniOpenUsbByVidPid(PrinterModel.XP_58IIH);
        System.out.println("-------------实时状态----------------------");
        xprinter.uniprintAndFeedLine(fs);
        byte[] r1 = new byte[8];
        System.out.println(xprinter.uniRead(fs, r1));
        System.out.println(xprinter.unisendRealtimestatus(fs, 1));
        System.out.println(xprinter.uniRead(fs, r1));
        System.out.println(xprinter.unisendRealtimestatus(fs, 2));
        System.out.println(xprinter.uniRead(fs, r1));
        System.out.println(xprinter.unisendRealtimestatus(fs, 3));
        System.out.println(xprinter.uniRead(fs, r1));
        System.out.println(xprinter.unisendRealtimestatus(fs, 4));
        System.out.println(xprinter.uniRead(fs, r1));
        System.out.println("-------------查询状态----------------------");
        System.out.println("xprinter.unireturnState  " + xprinter.unireturnState(fs, 1));
        System.out.println("xprinter.unireturnState  " + xprinter.unireturnState(fs, 49));
        System.out.println("xprinter.unireturnState  " + xprinter.unireturnState(fs, 2));
        System.out.println("xprinter.unireturnState  " + xprinter.unireturnState(fs, 50));
        System.out.println("--------------  feedline  ---------------------");
        System.out.println(xprinter.uniprintAndFeedLine(fs));
        System.out.println(xprinter.uniprintAndFeedLine(fs));
        System.out.println(xprinter.uniprintAndFeedLine(fs));
        System.out.println(xprinter.uniprintAndFeedLine(fs));
        System.out.println("-------------queryPrinterState----------------------");

        System.out.println("xprinter.uniWrite init  " + xprinter.uniWrite(fs, DataForSendToPrinterPos58.initializePrinter()));
        System.out.println("xprinter.uniWrite queryState " + xprinter.uniWrite(fs, DataForSendToPrinterPos58.queryPrinterState()));
        byte[] rr = new byte[8];
        System.out.println("xprinter.uniRead  " + xprinter.uniRead(fs, rr));
        System.out.println("-------------打印二维码----------------------");
        System.out.println(xprinter.uniWrite(fs, DataForSendToPrinterPos58.initializePrinter()));
        System.out.println(xprinter.uniWrite(fs, ZXingUtil.strToSendData(0,"你好", AlignType.Left,560,255,"utf-8")));
        xprinter.uniClose(fs);

    }

}
