package org.rainday.xprinter.case2.utils;

public class BitmapToByteData {
    public static enum AlignType {
        Left,
        Center,
        Right;

        private AlignType() {
        }
    }

}
/*public class BitmapToByteData {
    private static *//* synthetic *//* int[] $SWITCH_TABLE$net$posprinter$utils$BitmapToByteData$AlignType;
    private static *//* synthetic *//* int[] $SWITCH_TABLE$net$posprinter$utils$BitmapToByteData$BmpType;

    public enum AlignType {
        Left,
        Center,
        Right
    }

    public enum BmpType {
        Dithering,
        Threshold,
        Grey
    }

    static *//* synthetic *//* int[] $SWITCH_TABLE$net$posprinter$utils$BitmapToByteData$AlignType() {
        int[] iArr = $SWITCH_TABLE$net$posprinter$utils$BitmapToByteData$AlignType;
        if (iArr == null) {
            iArr = new int[AlignType.values().length];
            try {
                iArr[AlignType.Center.ordinal()] = 2;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[AlignType.Left.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[AlignType.Right.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $SWITCH_TABLE$net$posprinter$utils$BitmapToByteData$AlignType = iArr;
        }
        return iArr;
    }

    static *//* synthetic *//* int[] $SWITCH_TABLE$net$posprinter$utils$BitmapToByteData$BmpType() {
        int[] iArr = $SWITCH_TABLE$net$posprinter$utils$BitmapToByteData$BmpType;
        if (iArr == null) {
            iArr = new int[BmpType.values().length];
            try {
                iArr[BmpType.Dithering.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[BmpType.Grey.ordinal()] = 3;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[BmpType.Threshold.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            $SWITCH_TABLE$net$posprinter$utils$BitmapToByteData$BmpType = iArr;
        }
        return iArr;
    }

    public static byte[] rasterBmpToSendData(int m, Bitmap mBitmap, BmpType bmpType, AlignType alignType, int pagewidth) {
        Bitmap bitmap;
        byte[] newdata;
        switch ($SWITCH_TABLE$net$posprinter$utils$BitmapToByteData$BmpType()[bmpType.ordinal()]) {
            case 1:
                bitmap = convertGreyImg(mBitmap);
                break;
            case 2:
                bitmap = convertGreyImgByFloyd(mBitmap);
                break;
            case 3:
                bitmap = getGreyBitmap(mBitmap);
                break;
            default:
                bitmap = convertGreyImg(mBitmap);
                break;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] pixels = new int[(width * height)];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        byte[] data = getbmpdata(pixels, width, height);
        int n = (width + 7) / 8;
        int x = (height + 23) / 24;
        List<Byte> list = new ArrayList<>();
        byte[] head = new byte[8];
        head[0] = 29;
        head[1] = 118;
        head[2] = 48;
        head[3] = (byte) m;
        head[4] = (byte) (n % 256);
        head[5] = (byte) (n / 256);
        head[6] = 24;
        int mL = 0;
        int mH = 0;
        if (width >= pagewidth) {
            alignType = AlignType.Left;
        }
        switch ($SWITCH_TABLE$net$posprinter$utils$BitmapToByteData$AlignType()[alignType.ordinal()]) {
            case 1:
                mL = 0;
                mH = 0;
                break;
            case 2:
                mL = ((pagewidth - width) / 2) % 256;
                mH = ((pagewidth - width) / 2) / 256;
                break;
            case 3:
                mL = (pagewidth - width) % 256;
                mH = (pagewidth - width) / 256;
                break;
        }
        byte[] aligndata = DataForSendToPrinterPos80.setAbsolutePrintPosition(mL, mH);
        for (int i = 0; i < x; i++) {
            if (i != x - 1) {
                newdata = new byte[(n * 24)];
                System.arraycopy(data, i * 24 * n, newdata, 0, n * 24);
            } else if (height % 24 == 0) {
                head[6] = 24;
                newdata = new byte[(n * 24)];
                System.arraycopy(data, i * 24 * n, newdata, 0, n * 24);
            } else {
                head[6] = (byte) (height % 24);
                newdata = new byte[((height % 24) * n)];
                System.arraycopy(data, i * 24 * n, newdata, 0, (height % 24) * n);
            }
            if (alignType != AlignType.Left) {
                for (byte b : aligndata) {
                    list.add(Byte.valueOf(b));
                }
            }
            for (byte b2 : head) {
                list.add(Byte.valueOf(b2));
            }
            for (byte b3 : newdata) {
                list.add(Byte.valueOf(b3));
            }
        }
        byte[] byteData = new byte[list.size()];
        for (int i2 = 0; i2 < byteData.length; i2++) {
            byteData[i2] = list.get(i2).byteValue();
        }
        return byteData;
    }

    public static byte[] rasterBmpToSendData(int m, Bitmap mBitmap, BmpType bmpType) {
        Bitmap bitmap;
        byte[] newdata;
        Bitmap bitmap2 = toGrayscale(mBitmap);
        switch ($SWITCH_TABLE$net$posprinter$utils$BitmapToByteData$BmpType()[bmpType.ordinal()]) {
            case 1:
                bitmap = convertGreyImg(bitmap2);
                break;
            case 2:
                bitmap = convertGreyImgByFloyd(bitmap2);
                break;
            default:
                bitmap = convertGreyImg(bitmap2);
                break;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] pixels = new int[(width * height)];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        byte[] data = getbmpdata(pixels, width, height);
        int n = (width + 7) / 8;
        int x = (height + 23) / 24;
        List<Byte> list = new ArrayList<>();
        byte[] head = new byte[8];
        head[0] = 29;
        head[1] = 118;
        head[2] = 48;
        head[3] = (byte) m;
        head[4] = (byte) (n % 256);
        head[5] = (byte) (n / 256);
        head[6] = 24;
        for (int i = 0; i < x; i++) {
            if (i != x - 1) {
                newdata = new byte[(n * 24)];
                System.arraycopy(data, i * 24 * n, newdata, 0, n * 24);
            } else if (height % 24 == 0) {
                head[6] = 24;
                newdata = new byte[(n * 24)];
                System.arraycopy(data, i * 24 * n, newdata, 0, n * 24);
            } else {
                head[6] = (byte) (height % 24);
                newdata = new byte[((height % 24) * n)];
                System.arraycopy(data, i * 24 * n, newdata, 0, (height % 24) * n);
            }
            for (byte b : head) {
                list.add(Byte.valueOf(b));
            }
            for (byte b2 : newdata) {
                list.add(Byte.valueOf(b2));
            }
        }
        byte[] byteData = new byte[list.size()];
        for (int i2 = 0; i2 < byteData.length; i2++) {
            byteData[i2] = list.get(i2).byteValue();
        }
        return byteData;
    }

    public static byte[] flashBmpToSendData(Bitmap mBitmap, BmpType bmpType) {
        Bitmap bitmap;
        Bitmap bitmap2 = toGrayscale(convertBmp(mBitmap));
        switch ($SWITCH_TABLE$net$posprinter$utils$BitmapToByteData$BmpType()[bmpType.ordinal()]) {
            case 1:
                bitmap = convertGreyImg(bitmap2);
                break;
            case 2:
                bitmap = convertGreyImgByFloyd(bitmap2);
                break;
            default:
                bitmap = convertGreyImg(bitmap2);
                break;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int n = (width + 7) / 8;
        int h = (height + 7) / 8;
        int[] pixels = new int[(width * height)];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        return byteMerger(new byte[]{(byte) (n % 256), (byte) (n / 256), (byte) (h % 256), (byte) (h / 256)}, getbmpdata(pixels, width, height));
    }

    public static byte[] downLoadBmpToSendTSCdownloadcommand(Bitmap mBitmap) {
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();
        int[] pixels = new int[(width * height)];
        mBitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        byte[] send = new byte[(width * height)];
        for (int i = 0; i < pixels.length; i++) {
            send[i] = (byte) pixels[i];
        }
        return getbmpdataTsc(pixels, width, height);
    }

    public static byte[] downLoadBmpToSendTSCData(Bitmap mBitmap, BmpType bmpType) {
        Bitmap bitmap;
        Bitmap bitmap2 = toGrayscale(mBitmap);
        switch ($SWITCH_TABLE$net$posprinter$utils$BitmapToByteData$BmpType()[bmpType.ordinal()]) {
            case 1:
                bitmap = convertGreyImg(bitmap2);
                break;
            case 2:
                bitmap = convertGreyImgByFloyd(bitmap2);
                break;
            default:
                bitmap = convertGreyImg(bitmap2);
                break;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i = (width + 7) / 8;
        int i2 = (height + 7) / 8;
        int[] pixels = new int[(width * height)];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        return getbmpdataTsc(pixels, width, height);
    }

    public static byte[] downLoadBmpToSendData(Bitmap mBitmap, BmpType bmpType) {
        Bitmap bitmap;
        Bitmap bitmap2 = toGrayscale(convertBmp(mBitmap));
        switch ($SWITCH_TABLE$net$posprinter$utils$BitmapToByteData$BmpType()[bmpType.ordinal()]) {
            case 1:
                bitmap = convertGreyImg(bitmap2);
                break;
            case 2:
                bitmap = convertGreyImgByFloyd(bitmap2);
                break;
            default:
                bitmap = convertGreyImg(bitmap2);
                break;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] pixels = new int[(width * height)];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        return byteMerger(new byte[]{(byte) ((width + 7) / 8), (byte) ((height + 7) / 8)}, getbmpdata(pixels, width, height));
    }

    private static Bitmap toGrayscale(Bitmap bmpOriginal) {
        Bitmap bmpGrayscale = Bitmap.createBitmap(bmpOriginal.getWidth(), bmpOriginal.getHeight(), Bitmap.Config.RGB_565);
        Canvas c = new Canvas(bmpGrayscale);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0.0f);
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        c.drawBitmap(bmpOriginal, 0.0f, 0.0f, paint);
        return bmpGrayscale;
    }

    private static Bitmap convertGreyImg(Bitmap img) {
        int blue;
        int width = img.getWidth();
        int height = img.getHeight();
        int[] pixels = new int[(width * height)];
        img.getPixels(pixels, 0, width, 0, 0, width, height);
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
        Bitmap mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        mBitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return mBitmap;
    }

    private static Bitmap convertGreyImgByFloyd(Bitmap img) {
        int e;
        int width = img.getWidth();
        int height = img.getHeight();
        int[] pixels = new int[(width * height)];
        img.getPixels(pixels, 0, width, 0, 0, width, height);
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
        Bitmap mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        mBitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return mBitmap;
    }

    public static Bitmap getGreyBitmap(Bitmap bitmap) {
        int e;
        if (bitmap == null) {
            return null;
        }
        System.currentTimeMillis();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] pixels = new int[(width * height)];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
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
        Bitmap mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mBitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return mBitmap;
    }

    private static byte[] bagetbmpdata(int[] b, int w, int m) {
        byte[] head = {27, 42, (byte) m, (byte) (w % 256), (byte) (w / 256)};
        byte[] end = {27, 74, 16};
        byte[] perdata = new byte[w];
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < 8; y++) {
                if (((b[(y * w) + x] & 16711680) >> 16) != 0) {
                    perdata[x] = (byte) (perdata[x] | ((byte) (1 << (7 - y))));
                }
            }
        }
        for (int i = 0; i < perdata.length; i++) {
            perdata[i] = (byte) (perdata[i] ^ -1);
        }
        return byteMerger(byteMerger(head, perdata), end);
    }

    public static byte[] baBmpToSendData(int m, Bitmap mBitmap, BmpType bmpType) {
        Bitmap bitmap;
        Bitmap bitmap2 = toGrayscale(mBitmap);
        switch ($SWITCH_TABLE$net$posprinter$utils$BitmapToByteData$BmpType()[bmpType.ordinal()]) {
            case 1:
                bitmap = convertGreyImg(bitmap2);
                break;
            case 2:
                bitmap = convertGreyImgByFloyd(bitmap2);
                break;
            default:
                bitmap = convertGreyImg(bitmap2);
                break;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] pixels = new int[(width * height)];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        int n = (height + 7) / 8;
        ArrayList<Byte> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int[] perPix = new int[(width * 8)];
            for (int j = 0; j < perPix.length; j++) {
                if ((i * 8 * width) + j < pixels.length - 1) {
                    perPix[j] = pixels[(i * 8 * width) + j];
                } else {
                    perPix[j] = -1;
                }
            }
            for (byte b : bagetbmpdata(perPix, width, m)) {
                list.add(Byte.valueOf(b));
            }
        }
        byte[] newdata = new byte[list.size()];
        for (int i2 = 0; i2 < newdata.length; i2++) {
            newdata[i2] = list.get(i2).byteValue();
        }
        return newdata;
    }

    private static byte[] getbmpdata(int[] b, int w, int h) {
        int n = (w + 7) / 8;
        byte[] data = new byte[(n * h)];
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < n * 8; x++) {
                if (x < w) {
                    if (((b[(y * w) + x] & 16711680) >> 16) != 0) {
                        int i = (y * n) + (x / 8);
                        data[i] = (byte) (data[i] | ((byte) (1 << (7 - (x % 8)))));
                    }
                } else if (x >= w) {
                    int i2 = (y * n) + (x / 8);
                    data[i2] = (byte) (data[i2] | ((byte) (1 << (7 - (x % 8)))));
                }
            }
        }
        for (int i3 = 0; i3 < data.length; i3++) {
            data[i3] = (byte) (data[i3] ^ -1);
        }
        return data;
    }

    private static byte[] getbmpdataTsc(int[] b, int w, int h) {
        int n = (w + 7) / 8;
        byte[] data = new byte[(n * h)];
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < n * 8; x++) {
                if (x < w) {
                    if (((b[(y * w) + x] & 16711680) >> 16) != 0) {
                        int i = (y * n) + (x / 8);
                        data[i] = (byte) (data[i] | ((byte) (1 << (7 - (x % 8)))));
                    }
                } else if (x >= w) {
                    int i2 = (y * n) + (x / 8);
                    data[i2] = (byte) (data[i2] | ((byte) (1 << (7 - (x % 8)))));
                }
            }
        }
        return data;
    }

    private static byte[] byteMerger(byte[] byte_1, byte[] byte_2) {
        byte[] byte_3 = new byte[(byte_1.length + byte_2.length)];
        System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
        System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
        return byte_3;
    }

    private static Bitmap convertBmp(Bitmap bmp) {
        int w = bmp.getWidth();
        int h = bmp.getHeight();
        Bitmap convertBmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas cv = new Canvas(convertBmp);
        Matrix matrix = new Matrix();
        matrix.postScale(-1.0f, 1.0f);
        matrix.postRotate(-90.0f);
        Bitmap newBmp = Bitmap.createBitmap(bmp, 0, 0, w, h, matrix, true);
        cv.drawBitmap(newBmp, new Rect(0, 0, newBmp.getWidth(), newBmp.getHeight()), new Rect(0, 0, w, h), (Paint) null);
        return convertBmp;
    }
}*/
