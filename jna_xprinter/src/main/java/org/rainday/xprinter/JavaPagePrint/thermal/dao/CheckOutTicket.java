package org.rainday.xprinter.JavaPagePrint.thermal.dao;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * @author mox
 * @date 2020/4/3 18:02
 */

public class CheckOutTicket implements Printable {

    private ArrayList<ProductInfoDao> list;
    /**
     * 订单编号
     */
    private String orders;
    /**
     * 总金额
     */
    private String saleSum;
    /**
     * 支付金额
     */
    private String practical;
    /**
     * 支付方式
     */
    private String payMode;
    /**
     * 字体
     */
    private Font font;

    // 构造函数
    public CheckOutTicket(ArrayList<ProductInfoDao> list, String orders, String saleNum, String sale_sum,
                          String practical, String payMode) {
        this.list = list;
        this.orders = orders;
        /**
         * 商品总数
         */
        this.saleSum = sale_sum;
        this.practical = practical;
        this.payMode = payMode;
    }

    /**
     * 打印方法
     * graphics - 用来绘制页面的上下文，即打印的图形
     * pageFormat - 将绘制页面的大小和方向，即设置打印格式，如页面大小一点为计量单位（以1/72 英寸为单位，1英寸为25.4毫米。A4纸大致为595 × 842点）
     * 小票纸宽度一般为58mm，大概为165点
     * pageIndex - 要绘制的页面从 0 开始的索引 ，即页号
     **/
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        Component c = null;
        //此 Graphics2D 类扩展 Graphics 类，以提供对几何形状、坐标转换、颜色管理和文本布局更为复杂的控制。
        //它是用于在 Java(tm) 平台上呈现二维形状、文本和图像的基础类。
        //拿到画笔
        Graphics2D g2 = (Graphics2D) graphics;
        // 设置打印颜色为黑色
        g2.setColor(Color.black);

        // 打印起点坐标
        //返回与此 PageFormat相关的 Paper对象的可成像区域左上方点的 x坐标。
        double x = pageFormat.getImageableX();
        //返回与此 PageFormat相关的 Paper对象的可成像区域左上方点的 y坐标。
        double y = pageFormat.getImageableY();

        // 虚线
        float[] dash1 = {4.0f};
        // width - 此 BasicStroke 的宽度。此宽度必须大于或等于 0.0f。如果将宽度设置为
        // 0.0f，则将笔划呈现为可用于目标设备和抗锯齿提示设置的最细线条。
        // cap - BasicStroke 端点的装饰
        // join - 应用在路径线段交汇处的装饰
        // miterlimit - 斜接处的剪裁限制。miterlimit 必须大于或等于 1.0f。
        // dash - 表示虚线模式的数组
        // dash_phase - 开始虚线模式的偏移量

        // 设置画虚线
        g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 4.0f, dash1, 0.0f));

        // 设置打印字体（字体名称、样式和磅值大小）（字体名称可以是物理或者逻辑名称）
        //Font.PLAIN:普通样式常量   Font.ITALIC:斜体样式常量   Font.BOLD:粗体样式常量。
        font = new Font("宋体", Font.PLAIN, 16);
        //设置字体
        g2.setFont(font);
        //字体高度
        float height = font.getSize2D();
        //下一行开始打印的高度
        float line = height;

        //设置小票标题
        g2.drawString("蒲子小店", (float) x, (float) y + line);


        font = new Font("宋体", Font.BOLD, 16);
        g2.setFont(font);
        //字体高度
        height = font.getSize2D();
        line += height;
        //设置小票副标题
        g2.drawString("结账单", (float) x + 65, (float) y + line);

        font = new Font("宋体", Font.PLAIN, 16);
        g2.setFont(font);
        line = line + height * 2;
        //设置小票副标题
        g2.drawString("桌号: 01号", (float) x, (float) y + line);

        font = new Font("宋体", Font.PLAIN, 10);
        // 设置字体
        g2.setFont(font);
        // 字体高度
        height = font.getSize2D();

        line += height;
        // 显示订单号
        g2.drawString("订单编号:" + orders, (float) x, (float) y + line);

        line += height;
        // 显示收银员
        g2.drawString("下单时间:" + Calendar.getInstance().getTime().toLocaleString(), (float) x, (float) y + line);

        line += height;
        g2.drawString("支付方式:" + payMode, (float) x, (float) y + line);

        line += height;
        g2.drawString("支付金额:" + practical, (float) x, (float) y + line);

        line += height;
        //绘制虚线: (在此图形上下文的坐标系中使用当前颜色在点 (x1, y1) 和 (x2, y2) 之间画一条线。)
        g2.drawLine((int) x, (int) (y + line), (int) x + 180, (int) (y + line));

        line += height;
        //显示标题
        g2.drawString("品名", (float) x, (float) y + line + 5);
        g2.drawString("单价", (float) x + 35, (float) y + line + 5);
        g2.drawString("数量", (float) x + 70, (float) y + line + 5);
        g2.drawString("金额", (float) x + 105, (float) y + line + 5);

        line = line + height * 2;
        // 显示内容
        for (ProductInfoDao infoDao : list) {
            g2.drawString(infoDao.getName(), (float) x, (float) y + line);
            g2.drawString(infoDao.getUnitPrice(), (float) x + 35, (float) y + line);
            g2.drawString(infoDao.getNum(), (float) x + 70, (float) y + line);
            g2.drawString(infoDao.getSum(), (float) x + 105, (float) y + line);
            line += height;
        }

        line += height;
        //绘制虚线
        g2.drawLine((int) x, (int) (y + line), (int) x + 180, (int) (y + line));

        line = line + height * 2;
        g2.drawString("消费总金额:" + "￥" + saleSum, (float) x + 10, (float) y + line);
        //空7行
        line = line + height * 7;
        g2.drawString("蒲子小店", (float) x + 20, (float) y + line);
        line += height;
        g2.drawString("平安广场", (float) x + 20, (float) y + line);
        line += height;
        g2.drawString("0755-28912757", (float) x + 50, (float) y + line);

        //0
        if (pageIndex == 0) {
            return PAGE_EXISTS;
        }//1
        return NO_SUCH_PAGE;
    }

}
