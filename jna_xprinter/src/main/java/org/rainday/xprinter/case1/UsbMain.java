package org.rainday.xprinter.case1;

import java.io.IOException;
import org.usb4java.ConfigDescriptor;
import org.usb4java.Context;
import org.usb4java.DescriptorUtils;
import org.usb4java.Device;
import org.usb4java.DeviceDescriptor;
import org.usb4java.DeviceList;
import org.usb4java.Interface;
import org.usb4java.InterfaceDescriptor;
import org.usb4java.LibUsb;
import org.usb4java.LibUsbException;

/**
 * @author wyd
 * @version 1.0 edit by wyd at 2021-05-23 0:08:53
 * @date 2021-05-23 0:08:53
 */
public class UsbMain {

    public static void main(String[] args) throws IOException {

        //参考资料 https://blog.csdn.net/u012028275/article/details/109280001
        // https://www.cnblogs.com/utank/p/4685316.html
        Context context = new Context();
        int init = LibUsb.init(context);
        // Read the USB device list
        DeviceList list = new DeviceList();
        int result = LibUsb.getDeviceList(context, list);
        if (result < 0) {
            throw new LibUsbException("Unable to get device list", result);
        }

        try {
            // Iterate over all devices and scan for the right one
            // DeviceDescriptor-> ConfigDescriptor -> Interface -> InterfaceDescriptor 参考连接如下：
            // https://github.com/usb4java/usb4java/blob/master/src/test/java/org/usb4java/LibUsbDeviceTest.java
            for (Device device : list) {
                DeviceDescriptor deviceDescriptor = new DeviceDescriptor();
                result = LibUsb.getDeviceDescriptor(device, deviceDescriptor);
                //basic class = bDeviceClass = usbclass
                String basicClass = DescriptorUtils.getUSBClassName(deviceDescriptor.bDeviceClass());
                System.out.println(deviceDescriptor);
                System.out.println(basicClass);

//                int vid = deviceDescriptor.idVendor();
//                int pid = deviceDescriptor.idProduct();
                final ConfigDescriptor config = new ConfigDescriptor();
                if (LibUsb.getActiveConfigDescriptor(device, config) >= 0) {
                    try {
//                        byte configValue = config.bConfigurationValue();
                        for (int j = 0; j < config.bNumInterfaces(); j++) {
                            final Interface iface = config.iface()[j];
                            for (int k = 0; k < iface.numAltsetting(); k++) {
                                final InterfaceDescriptor ifaceDescriptor = iface.altsetting()[k];
                                System.out.println(ifaceDescriptor);
                                int busNum = LibUsb.getBusNumber(device);

                                /*if (ifaceDescriptor.bNumEndpoints() > 0) {
                                    byte endpoint = ifaceDescriptor.endpoint()[0].bEndpointAddress();
                                    LibUsb.refDevice(device);
                                }*/
                            }
                        }
                    } finally {
                        LibUsb.freeConfigDescriptor(config);
                    }
                }
            }

        } finally {
            // Ensure the allocated device list is freed
            LibUsb.freeDeviceList(list, true);
        }

    }

}
