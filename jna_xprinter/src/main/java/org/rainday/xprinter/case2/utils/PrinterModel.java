package org.rainday.xprinter.case2.utils;

/**
 * @author wyd
 * @version 1.0 edit by wyd at 2021-05-21 22:30:59
 * @date 2021-05-21 22:30:59
 */
public enum PrinterModel {

    XP_58IIH(0x0483,0x070B);

    private int vid;
    private int pid;

    PrinterModel(int vid, int pid) {
        this.vid = vid;
        this.pid = pid;
    }

    public int getVid() {
        return vid;
    }

    public int getPid() {
        return pid;
    }
}
