package org.rainday.xprinter.JavaPagePrint.thermal;


import java.util.ArrayList;
import org.rainday.xprinter.JavaPagePrint.thermal.dao.CheckOutTicket;
import org.rainday.xprinter.JavaPagePrint.thermal.dao.ProductInfoDao;
import org.rainday.xprinter.JavaPagePrint.thermal.util.PrintUtil;

/**
 * @author mox
 * @date 2020/4/3 17:39
 */

public class ThermApplication {
    public static void main(String[] args) {
        ArrayList<ProductInfoDao> infoDao = new ArrayList<>();
        infoDao.add(new ProductInfoDao("土豆sdfsdf发士大夫士大夫士大夫士大夫士大夫士大夫士大夫大师傅士大夫", "565", "2", "10"));
        infoDao.add(new ProductInfoDao("黄瓜", "545", "2", "10"));
        infoDao.add(new ProductInfoDao("茄子", "500", "2", "10"));
        infoDao.add(new ProductInfoDao("西瓜", "100", "2", "10"));
        // 打印结算单1
        CheckOutTicket salesTicket = new CheckOutTicket(infoDao, "000001", "3", "30", "30", "余额支付");
        PrintUtil pintSale = new PrintUtil(salesTicket);
        pintSale.printSale();
    }
}
