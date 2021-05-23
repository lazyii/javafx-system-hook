package org.rainday.xprinter.case2.utils;

public class BitmapProcess {}
/*public class BitmapProcess {
    private static *//* synthetic *//* int[] $SWITCH_TABLE$net$posprinter$utils$BitmapProcess$PrinterWidth;
    private static *//* synthetic *//* int[] $SWITCH_TABLE$net$posprinter$utils$BitmapProcess$RotateType;

    *//* access modifiers changed from: package-private *//*
    public enum PrinterWidth {
        Pos80,
        Pos76,
        Pos58
    }

    *//* access modifiers changed from: package-private *//*
    public enum RotateType {
        Rotate90,
        Rotate180,
        Rotate270
    }

    static *//* synthetic *//* int[] $SWITCH_TABLE$net$posprinter$utils$BitmapProcess$PrinterWidth() {
        int[] iArr = $SWITCH_TABLE$net$posprinter$utils$BitmapProcess$PrinterWidth;
        if (iArr == null) {
            iArr = new int[PrinterWidth.values().length];
            try {
                iArr[PrinterWidth.Pos58.ordinal()] = 3;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[PrinterWidth.Pos76.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[PrinterWidth.Pos80.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            $SWITCH_TABLE$net$posprinter$utils$BitmapProcess$PrinterWidth = iArr;
        }
        return iArr;
    }

    static *//* synthetic *//* int[] $SWITCH_TABLE$net$posprinter$utils$BitmapProcess$RotateType() {
        int[] iArr = $SWITCH_TABLE$net$posprinter$utils$BitmapProcess$RotateType;
        if (iArr == null) {
            iArr = new int[RotateType.values().length];
            try {
                iArr[RotateType.Rotate180.ordinal()] = 2;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[RotateType.Rotate270.ordinal()] = 3;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[RotateType.Rotate90.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            $SWITCH_TABLE$net$posprinter$utils$BitmapProcess$RotateType = iArr;
        }
        return iArr;
    }

    public static Bitmap compressBmpByPrinterWidth(Bitmap bitmap, PrinterWidth printerWidth) {
        int w;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        switch ($SWITCH_TABLE$net$posprinter$utils$BitmapProcess$PrinterWidth()[printerWidth.ordinal()]) {
            case 1:
                w = 576;
                break;
            case 2:
                w = 508;
                break;
            case 3:
                w = 384;
                break;
            default:
                w = 576;
                break;
        }
        if (width <= w) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(((float) w) / ((float) width), ((float) ((height * w) / width)) / ((float) height));
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    public static Bitmap compressBmpByYourWidth(Bitmap bitmap, int w) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= w) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(((float) w) / ((float) width), ((float) ((height * w) / width)) / ((float) height));
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    public static Bitmap rotateBmp(Bitmap bitmap, RotateType rotateType) {
        Matrix matrix = new Matrix();
        float degrees = 0.0f;
        switch ($SWITCH_TABLE$net$posprinter$utils$BitmapProcess$RotateType()[rotateType.ordinal()]) {
            case 1:
                degrees = 90.0f;
                break;
            case 2:
                degrees = 180.0f;
                break;
            case 3:
                degrees = 270.0f;
                break;
        }
        matrix.postRotate(degrees);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static List<Bitmap> cutBitmap(int h, Bitmap bitmap) {
        Bitmap b;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        boolean full = height % h == 0;
        int n = height % h == 0 ? height / h : (height / h) + 1;
        List<Bitmap> bitmaps = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (full) {
                b = Bitmap.createBitmap(bitmap, 0, i * h, width, h);
            } else if (i == n - 1) {
                b = Bitmap.createBitmap(bitmap, 0, i * h, width, height - (i * h));
            } else {
                b = Bitmap.createBitmap(bitmap, 0, i * h, width, h);
            }
            bitmaps.add(b);
        }
        return bitmaps;
    }

    public static Bitmap resizeImage(Bitmap bitmap, int w, boolean isOriginal) {
        Bitmap resizedBitmap;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= w) {
            return bitmap;
        }
        if (!isOriginal) {
            Matrix matrix = new Matrix();
            matrix.postScale(((float) w) / ((float) width), ((float) ((height * w) / width)) / ((float) height));
            resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        } else {
            resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, w, height);
        }
        return resizedBitmap;
    }
}*/
