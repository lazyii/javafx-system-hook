package org.rainday.xprinter.case1;

import io.github.jna4usb.purejavahidapi.HidDeviceInfo;
import io.github.jna4usb.purejavahidapi.PureJavaHidApi;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.usb.UsbException;

/**
 * @author wyd
 * @version 1.0 edit by wyd at 2021-05-23 0:08:53
 * @date 2021-05-23 0:08:53
 */
public class HIDMain {

    public static void main(String[] args) throws UsbException, UnsupportedEncodingException {
        List<HidDeviceInfo> deviceInfos = PureJavaHidApi.enumerateDevices();
        for (HidDeviceInfo deviceInfo : deviceInfos) {
            System.out.printf("VID = 0x%04X PID = 0x%04X Manufacturer = %s Product = %s Path = %s\n", //
                    deviceInfo.getVendorId(), //
                    deviceInfo.getProductId(), //
                    deviceInfo.getManufacturerString(), //
                    deviceInfo.getProductString(), //
                    deviceInfo.getPath());
        }

        //Jna 获取usb设备 方案
        //   usb guid(GUID_CLASS_USB_DEVICE,GUID_DEVINTERFACE_USB_DEVICE)
        // -> device path
        // -> SetupDiGetClassDevs() 获取hDevInfoSet
        // -> SetupDiGetDeviceRegistryProperty  get Friendly Name or Device Description
        // -> SetupDiEnumDeviceInterfaces  枚舉符合該GUID的設備接口
        // -> SetupDiGetInterfaceDeviceDetail  取得该设备接口的细节(设备路径)// 设备信息集句柄// 设备接口信息 / 设备接口细节(设备路径)

    }


}
