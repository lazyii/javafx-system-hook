package org.rainday.xprinter.case1;

import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.usb.UsbDevice;
import javax.usb.UsbDeviceDescriptor;
import javax.usb.UsbException;
import javax.usb.UsbHostManager;
import javax.usb.UsbHub;
import javax.usb.UsbServices;

/**
 * @author wyd
 * @version 1.0 edit by wyd at 2021-05-23 0:08:53
 * @date 2021-05-23 0:08:53
 */
public class UsbxMain {

    public static void main(String[] args) throws UsbException, UnsupportedEncodingException {
        UsbServices usbServices = UsbHostManager.getUsbServices();

        UsbHub rootUsbHub = usbServices.getRootUsbHub();

        dealDevice(rootUsbHub);

        dump(rootUsbHub, 0);
    }

    public static void dump(UsbDevice device, int level) {
        for (int i = 0; i < level; i += 1) {
            System.out.print("  ");
        }
        if (device.isUsbHub()) {
            final UsbHub hub = (UsbHub) device;
            for (UsbDevice child : (List<UsbDevice>) hub.getAttachedUsbDevices()) {
                dump(child, level + 1);
            }
        } else {
            System.out.println(device);
        }
    }


    public static void dealDevice(UsbDevice device) throws UnsupportedEncodingException, UsbException {
        if (device instanceof UsbHub) {
            List<UsbDevice> childDevices = ((UsbHub) device).getAttachedUsbDevices();
            for (UsbDevice childDevice : childDevices) {
                dealDevice(childDevice);
            }
        } else {
            // Read the string descriptor indices from the device descriptor.
            // If they are missing then ignore the device.
            final UsbDeviceDescriptor desc = device.getUsbDeviceDescriptor();
            final byte iManufacturer = desc.iManufacturer();
            final byte iProduct = desc.iProduct();
            if (iManufacturer == 0 || iProduct == 0) {
                return;
            }

            System.out.println(desc.idVendor() + "  " + desc.iProduct() + "  " + desc.bDeviceClass() + " " + desc.bDeviceSubClass());
            // Dump the device name
            //System.out.println(device.getString(iManufacturer) + " " + device.getString(iProduct));
        }
    }

}
