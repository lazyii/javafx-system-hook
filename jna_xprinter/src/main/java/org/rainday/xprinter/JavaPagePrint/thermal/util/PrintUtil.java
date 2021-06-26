package org.rainday.xprinter.JavaPagePrint.thermal.util;

import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashAttributeSet;
import javax.print.attribute.standard.PrinterName;
import org.rainday.xprinter.JavaPagePrint.thermal.dao.CheckOutTicket;

/**
 * @author mox
 * @date 2020/4/3 18:06
 */

public class PrintUtil {

    /**
     * 结算单,用于创建打印内容类
     */
    private CheckOutTicket salesTicket;

    public PrintUtil(CheckOutTicket salesTicket) {
        this.salesTicket = salesTicket;
    }

    /**
     * 打印结算单
     */
    public void printSale() {
        try {
            //Book 类提供文档的表示形式，该文档的页面可以使用不同的页面格式和页面 painter
            //要打印的文档
            Book book = new Book();

            //PageFormat类描述要打印的页面大小和方向
            //初始化一个页面打印对象
            PageFormat pf = new PageFormat();
            //设置页面打印方向，从上往下
            pf.setOrientation(PageFormat.PORTRAIT);


            // 通过Paper设置页面的空白边距和可打印区域。必须与实际打印纸张大小相符。
            Paper paper = new Paper();
            // 纸张大小
            paper.setSize(100, 30000);
            // A4(595x842)设置打印区域，其实0，0应该是72，72，因为A4纸的默认X,Y边距是72
            paper.setImageableArea(5, 0, 180, 30000);
            pf.setPaper(paper);

            book.append(salesTicket, pf);

            //指定XP-80C打印机打印总单(可以省略,放在这里是为了实现多台打印机服务)
            HashAttributeSet hs = new HashAttributeSet();
            String printerName = "XP-58";
            hs.add(new PrinterName(printerName, null));
            PrintService[] pss = PrintServiceLookup.lookupPrintServices(null, hs);
            if (pss.length == 0) {
                System.out.println("无法找到打印机:" + printerName);
                return;
            }

            // 获取打印服务对象
            PrinterJob printerJob = PrinterJob.getPrinterJob();
            // 设置打印类
            printerJob.setPageable(book);
            //添加指定的打印机
            printerJob.setPrintService(pss[0]);
            //开始打印
            printerJob.print();
        } catch (PrinterException e) {
            e.printStackTrace();
        }
    }

}
