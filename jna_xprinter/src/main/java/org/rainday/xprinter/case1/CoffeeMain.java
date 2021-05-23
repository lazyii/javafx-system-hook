package org.rainday.xprinter.case1;

import com.github.anastaciocintra.escpos.EscPos;
import com.github.anastaciocintra.escpos.image.BitImageWrapper;
import com.github.anastaciocintra.escpos.image.BitonalThreshold;
import com.github.anastaciocintra.escpos.image.CoffeeImageImpl;
import com.github.anastaciocintra.escpos.image.EscPosImage;
import com.github.anastaciocintra.escpos.image.RasterBitImageWrapper;
import com.github.anastaciocintra.output.PrinterOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.print.PrintService;

/**
 * @author wyd
 * @version 1.0 edit by wyd at 2021-05-23 0:08:53
 * @date 2021-05-23 0:08:53
 */
public class CoffeeMain {

    public static void main(String[] args) throws IOException {
        PrintService printService = PrinterOutputStream.getPrintServiceByName("XP-58");
        PrinterOutputStream printerOutputStream = new PrinterOutputStream(printService);

        EscPos escpos = new EscPos(printerOutputStream);
        escpos.setCharsetName("gb2312");//gbk gb2312均可  gbk = cp936
        escpos.writeLF("Hello Wold卢卡斯京东方来解释劳动法空间路上的风景了了接受得了附件里是的房间里");
        escpos.feed(5);
        escpos.cut(EscPos.CutMode.FULL);



        //compare dithering 第二张图对比阈值;  Shows how to work with BitonalThreshold and BitonalOrderedDither
        BitonalThreshold algorithm1 = new BitonalThreshold(60);
        // creating the EscPosImage, need buffered image and algorithm.
        BufferedImage bufferedImage1 = ImageIO.read(new File("C:\\Users\\admin\\Pictures\\avatar.png"));
        EscPosImage escposImage1 = new EscPosImage(new CoffeeImageImpl(bufferedImage1), algorithm1);
        escpos.writeLF("RasterBitImageWrapper BitonalThreshold（60黑色浅），（150 会加深黑色）");
        RasterBitImageWrapper rasterBitImageWrapper1 = new RasterBitImageWrapper();
        escpos.write(rasterBitImageWrapper1, escposImage1);

        // specify the algorithm that defines what and how "print or not print" on each coordinate of the BufferedImage.
        // in this case, threshold 127
        BitonalThreshold algorithm2 = new BitonalThreshold(150);
        // creating the EscPosImage, need buffered image and algorithm.
        BufferedImage bufferedImage2 = ImageIO.read(new File("C:\\Users\\admin\\Pictures\\avatar.png"));
        EscPosImage escposImage2 = new EscPosImage(new CoffeeImageImpl(bufferedImage2), algorithm2);

        //print RasterBitImageWrapper  raster(光栅) bitmap 正确打印
        // this wrapper uses esc/pos sequence: "GS 'v' '0'"
        escpos.writeLF("RasterBitImageWrapper");
        RasterBitImageWrapper rasterBitImageWrapper = new RasterBitImageWrapper();
        escpos.write(rasterBitImageWrapper, escposImage2);

        //print  BitImageWrapper
        // this wrapper uses esc/pos sequence: "ESC '*'"  正确打印
        BitonalThreshold algorithm3 = new BitonalThreshold(230);
        // creating the EscPosImage, need buffered image and algorithm.
        BufferedImage bufferedImage3 = ImageIO.read(new File("C:\\Users\\admin\\Pictures\\avatar.png"));
        EscPosImage escposImage3 = new EscPosImage(new CoffeeImageImpl(bufferedImage3), algorithm3);
        BitImageWrapper bitImageWrapper3 = new BitImageWrapper();
        escpos.writeLF("BitImageWrapper");
        escpos.write(bitImageWrapper3, escposImage3);
        escpos.close();


    }

}
