package org.rainday.xprinter.case2.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Hashtable;
import java.util.List;
import javax.imageio.ImageIO;
import org.rainday.xprinter.case2.utils.BitmapToByteData.AlignType;

/**
 * @author admin
 * @version 1.0 edit by admin at 2021-05-17 15:48:10
 * @date 2021-05-17 15:48:10
 */
public class ZXingUtil {

    //二维码颜色
    private static final int BLACK = 0xFF000000;//0xFFFF0000，红色
    //二维码背景色
    private static final int WHITE = 0xFFFFFFFF;//0xFF0000FF，蓝色

    //二维码格式参数
    private static final EnumMap<EncodeHintType, Object> hints = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);

    private static String DEFAULT_CHARSET = "utf-8";

    public static byte[] strToSendData(String content, int qrcodeSize) throws WriterException {
        return strToSendData(0, content, AlignType.Center, 400, qrcodeSize, DEFAULT_CHARSET);
    }

    /**
     * 使用光栅图方式打印二维码
     *
     * @param m ： 0,48正常，1,49倍宽   2,50倍高  3,51 倍宽 倍高
     * @param content 二维码内容
     * @param alignType 打印位置
     * @param pageWidth 纸张宽度
     * @param qrcodeSize 二维码大小
     * @return
     * @throws WriterException
     */
    public static byte[] strToSendData(int m, String content, AlignType alignType, int pageWidth, int qrcodeSize, String charset)
            throws WriterException {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        hints.put(EncodeHintType.CHARACTER_SET, charset);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, qrcodeSize, qrcodeSize, hints);

        //将二维码写入文件

        ZXingUtil.saveBitMatrix(bitMatrix, Paths.get("d:/aaa.qr.bmp"));

        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        int[] pixels = new int[width * height];
        // 下面这里按照二维码的算法，逐个生成二维码的图片， 两个for循环是图片横列扫描的结果
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (bitMatrix.get(x, y)) {
                    pixels[y * width + x] = 0xff000000;
                } else {
                    pixels[y * width + x] = 0xffffffff;
                }
            }
        }
        //将像素转化成字节数据
        byte[] data = getBmpData(pixels, width, height);

        int n = (width + 7) / 8;
        byte xL = (byte) (n % 256);
        byte xH = (byte) (n / 256);
        List list = new ArrayList<>();

        //打印光栅位图命令
        //ascii   GS  V 0   m  xl xh yl yh  d1.....dk
        //hex     1D 76 30  m  xl xh yl yh  d1.....dk
        //十进制     1D 76 30  m  xl xh yl yh  d1.....dk
        byte[] head = {29, 118, 48, (byte) m, xL, xH, (byte) height};

        int mL = 0;
        int mH = 0;
        /*if (width >= pageWidth) {
            alignType = AlignType.Left;
        }*/
        switch (alignType) {
            case Left:
                mL = 0;
                mH = 0;
                break;
            case Center:
                mL = (pageWidth - width) / 2 % 256;
                mH = (pageWidth - width) / 2 / 256;
                break;
            case Right:
                mL = (pageWidth - width) % 256;
                mH = (pageWidth - width) / 256;
                break;
        }

        byte[] aligndata = DataForSendToPrinterPos80.setAbsolutePrintPosition(mL, mH);
        byte[] newdata = new byte[n * height];
        System.arraycopy(data, 0, newdata, 0, height * n);

        for (byte b : aligndata) {
            list.add(Byte.valueOf(b));
        }
        for (byte b : head) {
            list.add(Byte.valueOf(b));
        }
        for (byte b : newdata) {
            list.add(Byte.valueOf(b));
        }

        byte[] byteData = new byte[list.size()];
        for (int i = 0; i < byteData.length; i++) {
            byteData[i] = ((Byte) list.get(i)).byteValue();
        }
        return byteData;
    }

    /**
     * 像素转化成字节数据
     *
     * @param b bitmap 数据
     * @param w 宽度
     * @param h 高度
     * @return
     */
    private static byte[] getBmpData(int[] b, int w, int h) {
        int n = (w + 7) / 8;
        byte[] data = new byte[n * h];
        byte mask = 1;
        for (int y = 0; y < h; y++) {//行
            for (int x = 0; x < n * 8; x++) {//列
                if (x < w) {
                    if ((b[(y * w + x)] & 0xFF0000) >> 16 == 0) {
                        continue;
                    }
                    int tmp66_65 = (y * n + x / 8);
                    byte[] tmp66_54 = data;
                    tmp66_54[tmp66_65] = (byte) (tmp66_54[tmp66_65] | (byte) (mask << 7 - x % 8));
                } else if (x >= w) {
                    int tmp104_103 = (y * n + x / 8);
                    byte[] tmp104_92 = data;
                    tmp104_92[tmp104_103] = (byte) (tmp104_92[tmp104_103] | (byte) (mask << 7 - x % 8));
                }
            }
        }

        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (data[i] ^ 0xFFFFFFFF);
        }
        return data;
    }


    private static void saveBitMatrix(BitMatrix bitMatrix, Path path) {
        BufferedImage bufferedImage = encodeImg(bitMatrix);
        //写入到文件
        try {
            ImageIO.write(bufferedImage, "bmp", path.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static BufferedImage encodeImg(BitMatrix bitMatrix) {
        /*二维码的纠错级别(排错率),4个级别：
         L (7%)、
         M (15%)、
         Q (25%)、
         H (30%)(最高H)
         纠错信息同样存储在二维码中，纠错级别越高，纠错信息占用的空间越多，那么能存储的有用讯息就越少；共有四级；
         选择M，扫描速度快。
         */
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 二维码边界空白大小 1,2,3,4 (4为默认,最大)
        hints.put(EncodeHintType.MARGIN, 1);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MAX_SIZE, 350);
        hints.put(EncodeHintType.MIN_SIZE, 150);

        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = null;
        try {
            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    image.setRGB(x, y, bitMatrix.get(x, y) ? BLACK : WHITE);
                }
            }
        } catch (Exception e) {
            System.out.println("生成二维码失败" + e.getMessage());
        }
        return image;
    }

}
