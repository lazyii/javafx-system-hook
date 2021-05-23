package org.rainday.xprinter.case2;


import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import org.rainday.xprinter.case2.utils.PrinterModel;
import org.rainday.xprinter.case2.utils.XprinterLibrary;

public final class ImagePrintMain {


    public static void main(String[] args) throws Exception {

        BufferedImage img = ImageIO.read(new File("d:/aaa.qr.bmp"));
        XprinterLibrary xprinter = XprinterLibrary.INSTANCE;
        int fs = xprinter.uniOpenUsbByVidPid(PrinterModel.XP_58IIH);

        int width = img.getWidth();
        int height = img.getHeight();
        int[] a = null;
        int[] pixels = img.getRaster().getPixels(0, 0, width, height, a);
        //int[] pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
        // 下面这里按照二维码的算法，逐个生成二维码的图片， 两个for循环是图片横列扫描的结果
        pixels = convertGreyImgByFloyd(pixels, width, height);
        //将像素转化成字节数据
        byte[] data = getBmpData(pixels, width, height);
        int n = (width + 7) / 8;
        byte xL = (byte)(n % 256);
        byte xH = (byte)(n / 256);
        int x = (height + 23) / 24;
        List<Byte> list = new ArrayList();
        byte[] head = new byte[]{29, 118, 48, (byte)0, xL, xH, 24, 0};

        for(int i = 0; i < x; ++i) {
            byte[] newdata;
            if (i == x - 1) {
                if (height % 24 == 0) {
                    head[6] = 24;
                    newdata = new byte[n * 24];
                    System.arraycopy(data, 24 * i * n, newdata, 0, 24 * n);
                } else {
                    head[6] = (byte)(height % 24);
                    newdata = new byte[height % 24 * n];
                    System.arraycopy(data, 24 * i * n, newdata, 0, height % 24 * n);
                }
            } else {
                newdata = new byte[n * 24];
                System.arraycopy(data, 24 * i * n, newdata, 0, 24 * n);
            }

            byte[] var19 = head;
            int var18 = head.length;

            byte b;
            int var17;
            for(var17 = 0; var17 < var18; ++var17) {
                b = var19[var17];
                list.add(b);
            }

            var19 = newdata;
            var18 = newdata.length;

            for(var17 = 0; var17 < var18; ++var17) {
                b = var19[var17];
                list.add(b);
            }
        }

        byte[] byteData = new byte[list.size()];

        for(int i = 0; i < byteData.length; ++i) {
            byteData[i] = (Byte)list.get(i);
        }


        xprinter.uniWrite(fs, byteData);
    }

    private static byte[] getbmpdata(int[] b, int w, int h) {
        int n = (w + 7) / 8;
        byte[] data = new byte[n * h];
        byte mask = 1;

        int y;
        for(y = 0; y < h; ++y) {
            for(int x = 0; x < n * 8; ++x) {
                if (x < w) {
                    if ((b[y * w + x] & 16711680) >> 16 != 0) {
                        data[y * n + x / 8] |= (byte)(mask << 7 - x % 8);
                    }
                } else if (x >= w) {
                    data[y * n + x / 8] |= (byte)(mask << 7 - x % 8);
                }
            }
        }

        for(y = 0; y < data.length; ++y) {
            data[y] = (byte)(~data[y]);
        }

        return data;
    }

    private static int[] convertGreyImgByFloyd(int[] pixels, int width,int height) {
        int e;
        int[] gray = new int[(height * width)];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                gray[(width * i) + j] = (16711680 & pixels[(width * i) + j]) >> 16;
            }
        }
        for (int i2 = 0; i2 < height; i2++) {
            for (int j2 = 0; j2 < width; j2++) {
                int g = gray[(width * i2) + j2];
                if (g >= 128) {
                    pixels[(width * i2) + j2] = -1;
                    e = g - 255;
                } else {
                    pixels[(width * i2) + j2] = -16777216;
                    e = g + 0;
                }
                if (j2 < width - 1 && i2 < height - 1) {
                    int i3 = (width * i2) + j2 + 1;
                    gray[i3] = gray[i3] + ((e * 3) / 8);
                    int i4 = ((i2 + 1) * width) + j2;
                    gray[i4] = gray[i4] + ((e * 3) / 8);
                    int i5 = ((i2 + 1) * width) + j2 + 1;
                    gray[i5] = gray[i5] + (e / 4);
                } else if (j2 == width - 1 && i2 < height - 1) {
                    int i6 = ((i2 + 1) * width) + j2;
                    gray[i6] = gray[i6] + ((e * 3) / 8);
                } else if (j2 < width - 1 && i2 == height - 1) {
                    int i7 = (width * i2) + j2 + 1;
                    gray[i7] = gray[i7] + (e / 4);
                }
            }
        }
        return gray;
    }
    private static int[] convertGreyImg(int[] pixels, int width,int height) {
        int blue;
        double redSum = 0.0d;
        double total = (double) (width * height);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                redSum += (double) ((16711680 & pixels[(width * i) + j]) >> 16);
            }
        }
        int m = (int) (redSum / total);
        for (int i2 = 0; i2 < height; i2++) {
            for (int j2 = 0; j2 < width; j2++) {
                int grey = pixels[(width * i2) + j2];
                int i3 = (65280 & grey) >> 8;
                int i4 = grey & 255;
                if (((16711680 & grey) >> 16) >= m) {
                    blue = 255;
                } else {
                    blue = 0;
                }
                pixels[(width * i2) + j2] = (blue << 16) | -16777216 | (blue << 8) | blue;
            }
        }
        return pixels;
    }
    private static int[] getGrayPixels(int[] pixels, int width,int height) {
        int e;
        int[] gray = new int[(height * width)];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                gray[(width * i) + j] = (16711680 & pixels[(width * i) + j]) >> 16;
            }
        }
        for (int i2 = 0; i2 < height; i2++) {
            for (int j2 = 0; j2 < width; j2++) {
                int g = gray[(width * i2) + j2];
                if (g >= 128) {
                    pixels[(width * i2) + j2] = -1;
                    e = g - 255;
                } else {
                    pixels[(width * i2) + j2] = -16777216;
                    e = g + 0;
                }
                if (j2 < width - 1 && i2 < height - 1) {
                    int i3 = (width * i2) + j2 + 1;
                    gray[i3] = gray[i3] + ((e * 3) / 8);
                    int i4 = ((i2 + 1) * width) + j2;
                    gray[i4] = gray[i4] + ((e * 3) / 8);
                    int i5 = ((i2 + 1) * width) + j2 + 1;
                    gray[i5] = gray[i5] + (e / 4);
                } else if (j2 == width - 1 && i2 < height - 1) {
                    int i6 = ((i2 + 1) * width) + j2;
                    gray[i6] = gray[i6] + ((e * 3) / 8);
                } else if (j2 < width - 1 && i2 == height - 1) {
                    int i7 = (width * i2) + j2 + 1;
                    gray[i7] = gray[i7] + (e / 4);
                }
            }
        }
        return gray;
    }

    private static byte[] getBmpData(int[] b, int w, int h) {
        int n = (w + 7) / 8;
        byte[] data = new byte[n * h];
        byte mask = 1;
        for (int y = 0; y < h; y++) {//行
            for (int x = 0; x < n * 8; x++) {//列
                if (x < w) {
                    if ((b[(y * w + x)] & 0x00FF0000) >> 16 == 0) {
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
}
