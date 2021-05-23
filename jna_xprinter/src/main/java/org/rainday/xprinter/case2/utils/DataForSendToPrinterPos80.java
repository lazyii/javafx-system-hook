package org.rainday.xprinter.case2.utils;

import java.io.UnsupportedEncodingException;

public class DataForSendToPrinterPos80 {
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

    public static byte[] printAndBackStandardmodel() {
        return new byte[]{12};
    }

    public static byte[] PrintAndCarriageReturn() {
        return new byte[]{13};
    }

    public static byte[] canclePrintDataByPagemodel() {
        return new byte[]{24};
    }

    public static byte[] sendRealtimestatus(int n) {
        return new byte[]{16, 4, (byte) n};
    }

    public static byte[] requestRealtimeForPrint(int n) {
        return new byte[]{16, 5, (byte) n};
    }

    public static byte[] openCashboxRealtime(int m, int t) {
        return new byte[]{16, 20, 1, (byte) m, (byte) t};
    }

    public static byte[] printByPagemodel() {
        return new byte[]{27, 12};
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

    public static byte[] selectPrinter(int n) {
        return new byte[]{27, 61, (byte) n};
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

    public static byte[] selectPageModel() {
        return new byte[]{27, 76};
    }

    public static byte[] selectFont(int n) {
        return new byte[]{27, 77, (byte) n};
    }

    public static byte[] selectInternationalCharacterSets(int n) {
        return new byte[]{27, 82, (byte) n};
    }

    public static byte[] selectStandardModel() {
        return new byte[]{27, 83};
    }

    public static byte[] selectPrintDirectionUnderPageModel(int n) {
        return new byte[]{27, 84, (byte) n};
    }

    public static byte[] selectOrCancelCW90(int n) {
        return new byte[]{27, 86, (byte) n};
    }

    public static byte[] setPrintAreaUnderPageModel(int xL, int xH, int yL, int yH, int dxL, int dxH, int dyL, int dyH) {
        return new byte[]{27, 87, (byte) xL, (byte) xH, (byte) yL, (byte) yH, (byte) dxL, (byte) dxH, (byte) dyL, (byte) dyH};
    }

    public static byte[] setRelativeHorizontalPrintPosition(int nL, int nH) {
        return new byte[]{27, 92, (byte) nL, (byte) nH};
    }

    public static byte[] selectAlignment(int n) {
        return new byte[]{27, 97, (byte) n};
    }

    public static byte[] selectPrintTransducerOutPutPageOutSignal(int n) {
        return new byte[]{27, 99, 51, (byte) n};
    }

    public static byte[] selectPrintTransducerStopPrint(int n) {
        return new byte[]{27, 99, 52, (byte) n};
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

    public static byte[] printBmpInFLASH(int n, int m) {
        return new byte[]{28, 112, (byte) n, (byte) m};
    }


    public static byte[] selectCharacterSize(int n) {
        return new byte[]{29, 33, (byte) n};
    }

    public static byte[] setAbsolutePositionUnderPageModel(int nL, int nH) {
        return new byte[]{29, 36, (byte) nL, (byte) nH};
    }

    public static byte[] executePrintDataSaveByTransformToHex() {
        byte[] data = new byte[7];
        data[0] = 29;
        data[1] = 40;
        data[2] = 65;
        data[3] = 2;
        data[6] = 1;
        return data;
    }

    public static byte[] printDownLoadBmp(int m) {
        return new byte[]{29, 47, (byte) m};
    }

    public static byte[] startOrStopMacrodeFinition() {
        return new byte[]{29, 58};
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

    public static byte[] selectCutPagerModerAndCutPager(int m) {
        return new byte[]{29, 86, (byte) m};
    }

    public static byte[] selectCutPagerModerAndCutPager(int m, int n) {
        if (m != 66) {
            return new byte[0];
        }
        return new byte[]{29, 86, (byte) m, (byte) n};
    }

    public static byte[] setPrintAreaWidth(int nL, int nH) {
        return new byte[]{29, 87, (byte) nL, (byte) nH};
    }

    public static byte[] setVerticalRelativePositionUnderPageModel(int nL, int nH) {
        return new byte[]{29, 92, (byte) nL, (byte) nH};
    }

    public static byte[] executeMacrodeCommand(int r, int t, int m) {
        return new byte[]{29, 94, (byte) r, (byte) t, (byte) m};
    }

    public static byte[] openOrCloseAutoReturnPrintState(int n) {
        return new byte[]{29, 97, (byte) n};
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

    public static byte[] printBarcode(int alignment, int HRI, int width, int height, int type, int size, String content) {
        byte[] data1 = {27, 97, (byte) alignment};
        byte[] data2 = {29, 72, (byte) HRI};
        byte[] data3 = {29, 119, (byte) width};
        byte[] data4 = {29, 104, (byte) height};
        return byteMerger(byteMerger(byteMerger(byteMerger(data1, data2), data3), data4), byteMerger(new byte[]{29, 107, (byte) type, (byte) size}, strTobytes(content)));
    }

    public static byte[] returnState(int n) {
        return new byte[]{29, 114, (byte) n};
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

    public static byte[] printerOrderBuzzingHint(int n, int t) {
        return new byte[]{27, 66, (byte) n, (byte) t};
    }

    public static byte[] printerOrderBuzzingAndWarningLight(int m, int t, int n) {
        return new byte[]{27, 67, (byte) m, (byte) t, (byte) n};
    }

    public static byte[] SetsTheNumberOfColumnsOfTheDataAreaForPDF417(int n) {
        byte[] data = new byte[8];
        data[0] = 29;
        data[1] = 40;
        data[2] = 107;
        data[3] = 3;
        data[5] = 48;
        data[6] = 65;
        data[7] = (byte) n;
        return data;
    }

    public static byte[] SetsTheNumberOfRowsOfTheDataAreaForPDF417(int n) {
        byte[] data = new byte[8];
        data[0] = 29;
        data[1] = 40;
        data[2] = 107;
        data[3] = 3;
        data[5] = 48;
        data[6] = 66;
        data[7] = (byte) n;
        return data;
    }

    public static byte[] SetsTheModuleWidthOfPDF417(int n) {
        byte[] data = new byte[8];
        data[0] = 29;
        data[1] = 40;
        data[2] = 107;
        data[3] = 3;
        data[5] = 48;
        data[6] = 67;
        data[7] = (byte) n;
        return data;
    }

    public static byte[] SetsTheModuleHeightForPDF417(int n) {
        byte[] data = new byte[8];
        data[0] = 29;
        data[1] = 40;
        data[2] = 107;
        data[3] = 3;
        data[5] = 48;
        data[6] = 68;
        data[7] = (byte) n;
        return data;
    }

    public static byte[] SetsTheErrorCorrectionLevelForPDF417(int m, int n) {
        byte[] data = new byte[9];
        data[0] = 29;
        data[1] = 40;
        data[2] = 107;
        data[3] = 4;
        data[5] = 48;
        data[6] = 69;
        data[7] = (byte) m;
        data[8] = (byte) n;
        return data;
    }

    public static byte[] SpecifiesOrCancelsVariousPDF417SymbolOptions(int m) {
        byte[] data = new byte[8];
        data[0] = 29;
        data[1] = 40;
        data[2] = 107;
        data[3] = 3;
        data[5] = 48;
        data[6] = 70;
        data[7] = (byte) m;
        return data;
    }

    public static byte[] StoresSymbolDataInThePDF417SymbolStorageArea(int pL, int pH, byte[] b) {
        return byteMerger(new byte[]{29, 40, 107, (byte) pL, (byte) pH, 48, 80, 48}, b);
    }

    public static byte[] PrintsThePDF417SymbolDataInTheSymbolStorageArea() {
        byte[] data = new byte[8];
        data[0] = 29;
        data[1] = 40;
        data[2] = 107;
        data[3] = 3;
        data[5] = 48;
        data[6] = 81;
        data[7] = 48;
        return data;
    }

    public static byte[] TransmitsTheSizeOfTheSymbolDataInTheSymbolStorageAreaPDF417() {
        byte[] data = new byte[8];
        data[0] = 29;
        data[1] = 40;
        data[2] = 107;
        data[3] = 3;
        data[5] = 48;
        data[6] = 82;
        data[7] = 48;
        return data;
    }

    public static byte[] SetsTheSizeOfTheQRCodeSymbolModule(int n) {
        return new byte[]{29, 40, 107, 48, 103, (byte) n};
    }

    public static byte[] SetsTheErrorCorrectionLevelForQRCodeSymbol(int n) {
        return new byte[]{29, 40, 107, 48, 105, (byte) n};
    }

    public static byte[] StoresSymbolDataInTheQRCodeSymbolStorageArea(String code) {
        int pH;
        int pL;
        byte[] b = strTobytes(code);
        int a = b.length;
        if (a <= 255) {
            pL = a;
            pH = 0;
        } else {
            pH = a / 256;
            pL = a % 256;
        }
        return byteMerger(new byte[]{29, 40, 107, 48, Byte.MIN_VALUE, (byte) pL, (byte) pH}, b);
    }

    public static byte[] PrintsTheQRCodeSymbolDataInTheSymbolStorageArea() {
        return new byte[]{29, 40, 107, 48, -127};
    }

    public static byte[] printQRcode(int n, int errLevel, String code) {
        int nH;
        int nL;
        byte[] b = strTobytes(code);
        int a = b.length;
        if (a <= 255) {
            nL = a;
            nH = 0;
        } else {
            nH = a / 256;
            nL = a % 256;
        }
        return byteMerger(byteMerger(new byte[]{29, 40, 107, 48, 103, (byte) n, 29, 40, 107, 48, 105, (byte) errLevel, 29, 40, 107, 48, Byte.MIN_VALUE, (byte) nL, (byte) nH}, b), new byte[]{29, 40, 107, 48, -127});
    }

    public static byte[] TransmitsTheSizeOfTheSymbolDataInTheSymbolStorageAreaQRCode() {
        byte[] data = new byte[8];
        data[0] = 29;
        data[1] = 40;
        data[2] = 107;
        data[3] = 3;
        data[5] = 49;
        data[6] = 82;
        data[7] = 48;
        return data;
    }

    public static byte[] SpecifiesTheModeForMaxiCodeSymbol(int n) {
        byte[] data = new byte[8];
        data[0] = 29;
        data[1] = 40;
        data[2] = 107;
        data[3] = 3;
        data[5] = 50;
        data[6] = 65;
        data[7] = (byte) n;
        return data;
    }

    public static byte[] StoresSymbolDataInItheMaxiCodeSymbolStorageArea(int pL, int pH, byte[] b) {
        return byteMerger(new byte[]{29, 40, 107, (byte) pL, (byte) pH, 50, 80, 48}, b);
    }

    public static byte[] PrintsTheMaxiCodeSymbolDataInTheSymbolStorageArea() {
        byte[] data = new byte[8];
        data[0] = 29;
        data[1] = 40;
        data[2] = 107;
        data[3] = 3;
        data[5] = 50;
        data[6] = 81;
        data[7] = 48;
        return data;
    }

    /* renamed from: TransmitsTheSizeOfTheEncodedSymbolDataInTheSymbolStorageAreaMaxiCode */
    public static byte[] m0x4ecedcbb() {
        byte[] data = new byte[8];
        data[0] = 29;
        data[1] = 40;
        data[2] = 107;
        data[3] = 3;
        data[5] = 50;
        data[6] = 82;
        data[7] = 48;
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
