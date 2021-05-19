package org.rainday.example;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HINSTANCE;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinUser.KBDLLHOOKSTRUCT;
import com.sun.jna.platform.win32.WinUser.LowLevelKeyboardProc;

public class KeyHookJNA {


    public static void main(String[] args) throws Exception {
        /*LowLevelKeyboardProc extends HOOKPROC */
        LowLevelKeyboardProc hookProc = new LowLevelKeyboardProc() {
            @Override
            public LRESULT callback(int nCode, WPARAM wParam, KBDLLHOOKSTRUCT lParam) {
                System.err.println("callback nCode: " + nCode + " value : " + lParam.vkCode);
                return new LRESULT(0);
            }
        };
        HINSTANCE hMod = Kernel32.INSTANCE.GetModuleHandle(null);
        User32.HHOOK hHook = User32.INSTANCE.SetWindowsHookEx(User32.WH_KEYBOARD_LL, hookProc, hMod, 0);

        User32.MSG msg = new User32.MSG();
        System.err.println("Please press any key ....");
        User32.INSTANCE.GetMessage(msg, null, 0, 0);

        User32.INSTANCE.UnhookWindowsHookEx(hHook);
    }
}
