package org.rainday.xprinter.case2.utils;

import java.io.UnsupportedEncodingException;

public class DataForSendToPrinterPos58 {
    private static String charsetName = "gbk";

    public static void setCharsetName(String charset) {
        charsetName = charset;
    }

    public static byte[] horizontalPositioning() {
        return new byte[]{9};
    }

    public static byte[] printAndFeedLine() {
        return new byte[]{10};
    }

    public static byte[] setCharRightSpace(int n) {
        return new byte[]{27, 32, (byte) n};
    }

    public static byte[] selectPrintModel(int n) {
        return new byte[]{27, 33, (byte) n};
    }

    public static byte[] setAbsolutePrintPosition(int m, int n) {
        return new byte[]{27, 36, (byte) m, (byte) n};
    }

    public static byte[] selectOrCancleCustomChar(int n) {
        return new byte[]{27, 37, (byte) n};
    }

    public static byte[] defineuserDefinedCharacters(int c1, int c2, byte[] b) {
        return byteMerger(new byte[]{27, 38, 3, (byte) c1, (byte) c2}, b);
    }

    public static byte[] selectBmpModel(int m, int nL, int nH, byte[] b) {
        return byteMerger(new byte[]{27, 42, (byte) m, (byte) nL, (byte) nH}, b);
    }

    public static byte[] selectOrCancelUnderlineModel(int n) {
        return new byte[]{27, 45, (byte) n};
    }

    public static byte[] setDefultLineSpacing() {
        return new byte[]{27, 50};
    }

    public static byte[] setLineSpaceing(int n) {
        return new byte[]{27, 51, (byte) n};
    }

    public static byte[] cancelUserDefinedCharacters(int n) {
        return new byte[]{27, 63, (byte) n};
    }

    public static byte[] initializePrinter() {
        return new byte[]{27, 64};
    }

    public static byte[] setHorizontalmovementPosition(byte[] b) {
        return byteMerger(byteMerger(new byte[]{27, 68}, b), new byte[1]);
    }

    public static byte[] selectOrCancelBoldModel(int n) {
        return new byte[]{27, 69, (byte) n};
    }

    public static byte[] selectOrCancelDoubelPrintModel(int n) {
        return new byte[]{27, 71, (byte) n};
    }

    public static byte[] printAndFeed(int n) {
        return new byte[]{27, 74, (byte) n};
    }

    public static byte[] selectFont(int n) {
        return new byte[]{27, 77, (byte) n};
    }

    public static byte[] selectInternationalCharacterSets(int n) {
        return new byte[]{27, 82, (byte) n};
    }

    public static byte[] selectOrCancelCW90(int n) {
        return new byte[]{27, 86, (byte) n};
    }

    public static byte[] setRelativeHorizontalPrintPosition(int nL, int nH) {
        return new byte[]{27, 92, (byte) nL, (byte) nH};
    }

    public static byte[] selectAlignment(int n) {
        return new byte[]{27, 97, (byte) n};
    }

    public static byte[] allowOrForbidPressButton(int n) {
        return new byte[]{27, 99, 53, (byte) n};
    }

    public static byte[] printAndFeedForward(int n) {
        return new byte[]{27, 100, (byte) n};
    }

    public static byte[] creatCashboxContorlPulse(int m, int t1, int t2) {
        return new byte[]{27, 112, (byte) m, (byte) t1, (byte) t2};
    }

    public static byte[] selectCharacterCodePage(int n) {
        return new byte[]{27, 116, (byte) n};
    }

    public static byte[] selectOrCancelConvertPrintModel(int n) {
        return new byte[]{27, 123, (byte) n};
    }

    //打印下载到flash中的位图  当flash位图没有被定义时，该命令无效。 定义flash位图 28 113
    public static byte[] printBmpInFLASH(int n, int m) {
        return new byte[]{28, 112, (byte) n, (byte) m};
    }


    public static byte[] selectCharacterSize(int n) {
        return new byte[]{29, 33, (byte) n};
    }

    //打印下载位图  如果下载的位图没有被定义，忽略这条命令。 定义位图 29  42
    public static byte[] printDownLoadBmp(int m) {
        return new byte[]{29, 47, (byte) m};
    }

    public static byte[] selectOrCancelInvertPrintModel(int n) {
        return new byte[]{29, 66, (byte) n};
    }

    public static byte[] selectHRICharacterPrintPosition(int n) {
        return new byte[]{29, 72, (byte) n};
    }

    public static byte[] setLeftSpace(int nL, int nH) {
        return new byte[]{29, 76, (byte) nL, (byte) nH};
    }

    public static byte[] setHorizontalAndVerticalMoveUnit(int x, int y) {
        return new byte[]{29, 80, (byte) x, (byte) y};
    }

    public static byte[] setPrintAreaWidth(int nL, int nH) {
        return new byte[]{29, 87, (byte) nL, (byte) nH};
    }

    public static byte[] selectHRIFont(int n) {
        return new byte[]{29, 102, (byte) n};
    }

    public static byte[] setBarcodeHeight(int n) {
        return new byte[]{29, 104, (byte) n};
    }

    public static byte[] printBarcode(int m, String content) {
        return byteMerger(byteMerger(new byte[]{29, 107, (byte) m}, strTobytes(content)), new byte[1]);
    }

    public static byte[] printBarcode(int m, int n, String content) {
        return byteMerger(new byte[]{29, 107, (byte) m, (byte) n}, strTobytes(content));
    }

    public static byte[] printcode128(String content) {
        return byteMerger(byteMerger(new byte[]{29, 107, 73, 10, 123, 65, 48, 49, 50, 51, 52, 53, 54, 55}, strTobytes(content)), new byte[]{13, 10});
    }

    public static byte[] setBarcodeWidth(int n) {
        return new byte[]{29, 119, (byte) n};
    }

    public static byte[] setChineseCharacterModel(int n) {
        return new byte[]{28, 33, (byte) n};
    }

    public static byte[] selectChineseCharModel() {
        return new byte[]{28, 38};
    }

    public static byte[] selectOrCancelChineseCharUnderLineModel(int n) {
        return new byte[]{28, 45, (byte) n};
    }

    public static byte[] CancelChineseCharModel() {
        return new byte[]{28, 46};
    }

    public static byte[] definedUserDefinedChineseChar(int c2, byte[] b) {
        return byteMerger(new byte[]{28, 50, -2, (byte) c2}, b);
    }

    public static byte[] setChineseCharLeftAndRightSpace(int n1, int n2) {
        return new byte[]{28, 83, (byte) n1, (byte) n2};
    }

    public static byte[] selectOrCancelChineseCharDoubleWH(int n) {
        return new byte[]{28, 87, (byte) n};
    }

    public static byte[] queryPrinterState() {
        return new byte[]{27, 118};
    }

    public static byte[] openOrCloseLableModelInReceip(Boolean open) {
        if (open.booleanValue()) {
            byte[] data = new byte[8];
            data[0] = 31;
            data[1] = 27;
            data[2] = 31;
            data[4] = 1;
            data[5] = 1;
            data[6] = Byte.MIN_VALUE;
            data[7] = 1;
            return data;
        }
        byte[] data2 = new byte[8];
        data2[0] = 31;
        data2[1] = 27;
        data2[2] = 31;
        data2[4] = 1;
        data2[5] = 1;
        data2[6] = Byte.MIN_VALUE;
        return data2;
    }

    public static byte[] endOfLable() {
        byte[] data = new byte[7];
        data[0] = 31;
        data[1] = 27;
        data[2] = 31;
        data[4] = 1;
        data[5] = 1;
        data[6] = -127;
        return data;
    }

    public static byte[] checkLableAndGap() {
        byte[] data = new byte[7];
        data[0] = 31;
        data[1] = 27;
        data[2] = 31;
        data[4] = 1;
        data[5] = 1;
        data[6] = -126;
        return data;
    }

    public static byte[] setTheLableWidth(int width) {
        byte[] data = new byte[8];
        data[0] = 31;
        data[1] = 27;
        data[2] = 31;
        data[4] = 1;
        data[5] = 1;
        data[6] = -125;
        data[7] = (byte) width;
        return data;
    }

    public static byte[] selectFontB() {
        return new byte[]{31, 27, 31, 48, 1};
    }

    public static byte[] seletFontA() {
        byte[] data = new byte[3];
        data[0] = 27;
        data[1] = 33;
        return data;
    }

    public static byte[] setSpeed(int n) {
        return new byte[]{31, 27, 31, 47, (byte) n};
    }

    public static byte[] setDormancyTime(int n) {
        byte[] data = new byte[8];
        data[0] = 31;
        data[1] = 27;
        data[2] = 31;
        data[5] = 1;
        data[6] = -94;
        data[7] = (byte) n;
        return data;
    }

    public static byte[] setOffTime(int n) {
        byte[] data = new byte[8];
        data[0] = 31;
        data[1] = 27;
        data[2] = 31;
        data[5] = 1;
        data[6] = -93;
        data[7] = (byte) n;
        return data;
    }

    private static byte[] byteMerger(byte[] byte_1, byte[] byte_2) {
        byte[] byte_3 = new byte[(byte_1.length + byte_2.length)];
        System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
        System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
        return byte_3;
    }

    private static byte[] strTobytes(String str) {
        boolean z = true;
        try {
            byte[] b = str.getBytes("utf-8");
            boolean z2 = charsetName == null;
            if (charsetName != "") {
                z = false;
            }
            if (z || z2) {
                charsetName = "gbk";
            }
            return new String(b, "utf-8").getBytes(charsetName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
