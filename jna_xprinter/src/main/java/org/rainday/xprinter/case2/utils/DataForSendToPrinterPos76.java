package org.rainday.xprinter.case2.utils;

public class DataForSendToPrinterPos76 {
    public static byte[] horizontalPositioning() {
        return new byte[]{9};
    }

    public static byte[] printAndFeedLine() {
        return new byte[]{10};
    }

    public static byte[] PrintAndCarriageReturn() {
        return new byte[]{13};
    }

    public static byte[] sendRealtimestatus(int n) {
        return new byte[]{16, 4, (byte) n};
    }

    public static byte[] requestRealtimeForPrint(int n) {
        return new byte[]{16, 5, (byte) n};
    }

    public static byte[] setCharRightSpace(int n) {
        return new byte[]{27, 32, (byte) n};
    }

    public static byte[] selectPrintModel(int n) {
        return new byte[]{27, 33, (byte) n};
    }

    public static byte[] selectOrCancleCustomChar(int n) {
        return new byte[]{27, 37, (byte) n};
    }

    public static byte[] defineuserDefinedCharacters(int c1, int c2, byte[] b) {
        return byteMerger(new byte[]{27, 38, 3, (byte) c1, (byte) c2}, b);
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

    public static byte[] printHeadReplaceEnter() {
        return new byte[]{27, 60};
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

    public static byte[] printAndBackFeed(int n) {
        return new byte[]{27, 75, (byte) n};
    }

    public static byte[] selectFont(int n) {
        return new byte[]{27, 77, (byte) n};
    }

    public static byte[] selectInternationalCharacterSets(int n) {
        return new byte[]{27, 82, (byte) n};
    }

    public static byte[] selectOrCancelUnidirectionPrint(int n) {
        return new byte[]{27, 85, (byte) n};
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

    public static byte[] printAndFeedUnidirection(int n) {
        return new byte[]{27, 101, (byte) n};
    }

    public static byte[] creatCashboxContorlPulse(int m, int t1, int t2) {
        return new byte[]{27, 112, (byte) m, (byte) t1, (byte) t2};
    }

    public static byte[] selectPrintColor(int n) {
        return new byte[]{27, 114, (byte) n};
    }

    public static byte[] selectCharacterCodePage(int n) {
        return new byte[]{27, 116, (byte) n};
    }

    public static byte[] selectOrCancelConvertPrintModel(int n) {
        return new byte[]{27, 123, (byte) n};
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

    public static byte[] selectCutPagerModerAndCutPager(int m) {
        return new byte[]{29, 86, (byte) m};
    }

    public static byte[] selectCutPagerModerAndCutPager(int m, int n) {
        if (m != 66) {
            return new byte[0];
        }
        return new byte[]{29, 86, (byte) m, (byte) n};
    }

    public static byte[] openOrCloseAutoReturnPrintState(int n) {
        return new byte[]{29, 97, (byte) n};
    }

    public static byte[] returnState(int n) {
        return new byte[]{29, 114, (byte) n};
    }

    public static byte[] setConnectWaitTime(int t1, int t2) {
        return new byte[]{29, 122, 48, (byte) t1, (byte) t2};
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

    public static byte[] cancelChineseCharModel() {
        return new byte[]{28, 46};
    }

    public static byte[] definedUserDefinedChineseChar(int c2, byte[] b) {
        return byteMerger(new byte[]{28, 50, -2, (byte) c2}, b);
    }

    public static byte[] cancelUserDefinedChineseChar(int c2) {
        return new byte[]{28, 63, -2, (byte) c2};
    }

    public static byte[] setChineseCharLeftAndRightSpace(int n1, int n2) {
        return new byte[]{28, 83, (byte) n1, (byte) n2};
    }

    public static byte[] selectOrCancelChineseCharDoubleWH(int n) {
        return new byte[]{28, 87, (byte) n};
    }

    public static byte[] setBlackPositionRecord(int a, int m, int nL, int nH) {
        byte[] data = new byte[9];
        data[0] = 29;
        data[1] = 40;
        data[2] = 70;
        data[3] = 4;
        data[5] = (byte) a;
        data[6] = (byte) m;
        data[7] = (byte) nL;
        data[8] = (byte) nH;
        return data;
    }

    public static byte[] feedBlackPaperToPrintPosition() {
        return new byte[]{29, 12};
    }

    public static byte[] setRollBackLength(int n) {
        return new byte[]{27, 94, (byte) n};
    }

    public static byte[] setOrderLength(int nL, int nH) {
        return new byte[]{27, 126, (byte) nL, (byte) nH};
    }

    public static byte[] feedpaperToOrderEnd() {
        return new byte[]{27, Byte.MAX_VALUE};
    }

    public static byte[] printHeaderRecordAndFeedToPrintStartPosition() {
        return new byte[]{29, 60};
    }

    private static byte[] byteMerger(byte[] byte_1, byte[] byte_2) {
        byte[] byte_3 = new byte[(byte_1.length + byte_2.length)];
        System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
        System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
        return byte_3;
    }
}
