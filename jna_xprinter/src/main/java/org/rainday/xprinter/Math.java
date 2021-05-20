package org.rainday.xprinter;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

/**
 * @author wyd
 * @version 1.0 edit by wyd at 2021-05-19 19:23:07
 * @date 2021-05-19 19:23:07
 */
public interface Math extends Library {


    //以'/'开头直接以根路径寻找
    Math INSTANCE = Native.load("/org/rainday/xprinter/lib/" + Platform.RESOURCE_PREFIX + "/math.dll", Math.class);

    //"math.dll" 从根路径寻找  platform.resource_prefix/math.dll
    //Math INSTANCE = Native.load("math.dll", Math.class);

    int add(int a1, int a2);

    int minus(int a1, int a2);

    int multiply(int a1, int a2);

    double divide(int a1, int a2);

}
