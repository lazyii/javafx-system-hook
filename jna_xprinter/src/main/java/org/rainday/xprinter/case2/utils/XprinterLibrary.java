package org.rainday.xprinter.case2.utils;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * @author wyd
 * @version 1.0 edit by wyd at 2021-05-19 19:23:07
 * @date 2021-05-19 19:23:07
 */
public interface XprinterLibrary extends Library {

    //以'/'开头直接以根路径寻找
    //XprinterLibrary INSTANCE = Native.load("/org/rainday/xprinter/lib/" + Platform.RESOURCE_PREFIX + "/JsPrinterDll.dll", XprinterLibrary.class);
    //默认会寻找 对应 系统文件夹下的 JsPrinterDll.dll 文件
    XprinterLibrary INSTANCE = Native.load("JsPrinterDll.dll", XprinterLibrary.class);

    /**
     * xp-58iih - vid:0x0483 pid:0x070B 打开打印机
     *
     * @return fs: 返回U口打印机的句柄  成功:大于等于1  失败：小于等于0
     */
    int uniOpenUsb();

    /**
     * 指定vid,pid 打开打印机
     *
     * @param vid
     * @param pid
     * @return fs: 返回指定VID, PID的U口打印机的句柄  成功:大于等于1  失败：小于等于0
     */
    int uniOpenUsbByVidPid(int vid, int pid);

    default int uniOpenUsbByVidPid(PrinterModel model) {
        return uniOpenUsbByVidPid(model.getVid(), model.getPid());
    }

    /**
     * 封装好的QRcode打印指令<br>
     *
     * @param n 单元大小，默认值为n=3
     * @param errLevel 纠错等级 48-51；
     * @param code 二维码内容字符串
     */
    @Deprecated
    int uniprintQRcode(int fs, int n, int errLevel, String code);

    /**
     * DLE EOT n<br> 实时状态传送
     *
     * @param n 取 1，2，3，4
     * @return int
     */
    int unisendRealtimestatus(int fs, int n);

    /**
     * 返回状态
     * @param fs
     * @param n 范围：1,2,49,50;1,49返回传感器状态,2,50返回钱箱状态;
     * @return
     */
    int unireturnState(int fs, int n);

    /**
     * 打印换行符
     *
     * @param fs
     * @return
     */
    int uniprintAndFeedLine(int fs);


    int uniWrite(int fs, byte[] data, int dataSize);

    //    int uniWrite(int fs, byte[] data, int dataSize);
    default int uniWrite(int fs, byte[] data) {
        return uniWrite(fs, data, data.length);
    }

    int uniRead(int fs, byte[] data, int dataSize);

    //    int uniRead(int fs, byte[] data, int dataSize);
    default int uniRead(int fs, byte[] data) {
        return uniWrite(fs, data, data.length);
    }

    /**
     * 关闭一个现有的打印机连接句柄
     *
     * @param fs 连接成功的句柄
     * @return 成功:大于等于1  失败：小于等于0
     */
    boolean uniClose(int fs);
}
