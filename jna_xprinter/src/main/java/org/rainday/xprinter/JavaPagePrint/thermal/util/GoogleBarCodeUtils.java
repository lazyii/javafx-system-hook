package org.rainday.xprinter.JavaPagePrint.thermal.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * @author super man
 * @date 2020/9/16 0016  16:10
 * @description: 条形码生成工具
 */
public class GoogleBarCodeUtils {

    /**
     * 条形码宽度
     */
    private static final int WIDTH = 100;

    /**
     * 条形码高度
     */
    private static final int HEIGHT = 30;

    /**
     * 加文字 条形码
     */
    private static final int WORD_HEIGHT = 50;

    /**
     * 条形码右下角第一段数据
     */
    private static final String RIGHT_FIRST_WORDS = "superMan";

    private static final String RIGHT_SECOND_WORDS = "Made in China";

    /**
     * 设置 条形码参数
     */
    private static Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>() {
        private static final long serialVersionUID = 1L;

        {
            // 设置编码方式
            put(EncodeHintType.CHARACTER_SET, "utf-8");
            // 容错级别 这里选择最高H级别
            put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            put(EncodeHintType.MARGIN, 1);
        }
    };

    /**
     * 生成 图片缓冲
     *
     * @param vaNumber VA 码
     * @return 返回BufferedImage
     * @author fxbin
     */
    public static BufferedImage getBarCode(String vaNumber) {
        Code128Writer writer = new Code128Writer();
        // 编码内容, 编码类型, 宽度, 高度, 设置参数
        BitMatrix bitMatrix = writer.encode(vaNumber, BarcodeFormat.CODE_128, WIDTH, HEIGHT, hints);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    /**
     *
     * @param vaNumber 生成条码的内容
     * @param words 右下角内容
     * @param time 左下角内容
     * @return
     */
    public static BufferedImage insertWords(String vaNumber, String words, String time) {
        return insertWords(getBarCode(vaNumber), words, time);
    }
    /**
     * 把带logo的二维码下面加上文字
     *
     * @param image 条形码图片
     * @param words 文字
     * @return 返回BufferedImage
     * @author fxbin
     */
    public static BufferedImage insertWords(BufferedImage image, String words, String time) {
        // 新的图片，把带logo的二维码下面加上文字
        if (words != null && words.length() > 0) {
            BufferedImage outImage = new BufferedImage(WIDTH, WORD_HEIGHT, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = outImage.createGraphics();
            // 抗锯齿
            setGraphics2D(g2d);
            // 设置白色
            setColorWhite(g2d);
            // 画条形码到新的面板
            g2d.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
            // 画文字到新的面板
            Color color = new Color(0, 0, 0);
            g2d.setColor(color);
            // 字体、字型、字号
            Font font = new Font("宋体", Font.PLAIN, 10);
            g2d.setFont(font);
            //文字长度
            int strWidth = g2d.getFontMetrics().stringWidth(words);
            //总长度减去文字长度的一半  （居中显示）
            int wordStartX = (WIDTH - strWidth) / 2;
            //height + (outImage.getHeight() - height) / 2 + 12
            int wordStartY = HEIGHT + 20;
            // time 文字长度
            int timeWidth = 1;
            // rightFirstWords 文字长度
            int rightFirstWordsWidth = WIDTH - g2d.getFontMetrics(font).stringWidth(RIGHT_FIRST_WORDS);
            // rightSecondWords 文字长度
            int rightSecondWordsWidth = WIDTH - g2d.getFontMetrics(font).stringWidth(RIGHT_SECOND_WORDS);
            // 画文字
            g2d.drawString(words, wordStartX, wordStartY);
            g2d.drawString(time, 0, image.getWidth() + 18);
            g2d.drawString(RIGHT_FIRST_WORDS, rightFirstWordsWidth, wordStartY + 18);
            g2d.drawString(RIGHT_SECOND_WORDS, rightSecondWordsWidth, wordStartY + 36);
            g2d.dispose();
            outImage.flush();
            return outImage;
        }
        return null;
    }

    /**
     * 设置 Graphics2D 属性  （抗锯齿）
     *
     * @param g2d Graphics2D提供对几何形状、坐标转换、颜色管理和文本布局更为复杂的控制
     */
    private static void setGraphics2D(Graphics2D g2d) {
        // 消除画图锯齿
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // 消除文字锯齿
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);
        Stroke s = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
        g2d.setStroke(s);
    }

    /**
     * 设置背景为白色
     *
     * @param g2d Graphics2D提供对几何形状、坐标转换、颜色管理和文本布局更为复杂的控制
     */
    private static void setColorWhite(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        //填充整个屏幕
        g2d.fillRect(0, 0, 302, 113);
        //设置笔刷
        g2d.setColor(Color.BLACK);
    }

   /* public static void main(String[] args) throws IOException {
        BufferedImage image = insertWords(getBarCode("superMan"), "superMan", "2020-09-16");
        ImageIO.write(image, "jpg", new File("D:/superMan.png"));
    }*/

}
