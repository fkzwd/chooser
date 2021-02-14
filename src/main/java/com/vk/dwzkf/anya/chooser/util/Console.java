package com.vk.dwzkf.anya.chooser.util;

import com.sun.jna.Function;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;

public class Console {
    private static final String CLEAR_LINE;

    static {
        setConsoleMode();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 50; i++) {
            sb.append("\n");
        }
        CLEAR_LINE = sb.toString();
    }

    public static void setConsoleMode() {
        if (!System.getProperty("os.name").toLowerCase().contains("windows")) {
            return;
        }
        Function GetStdHandle = Function.getFunction("kernel32", "GetStdHandle");
        WinDef.DWORD STD_OUTPUT_HANDLE = new WinDef.DWORD(-11);
        WinNT.HANDLE hOut = (WinNT.HANDLE) GetStdHandle.invoke(WinNT.HANDLE.class, new Object[]{STD_OUTPUT_HANDLE});
        WinDef.DWORDByReference p_dwMode = new WinDef.DWORDByReference(new WinDef.DWORD(0));
        Function GetConsoleMode = Function.getFunction("kernel32", "GetConsoleMode");
        GetConsoleMode.invoke(WinDef.BOOL.class, new Object[]{hOut, p_dwMode});

        int ENABLE_VIRTUAL_TERMINAL_PROCESSING = 4;
        WinDef.DWORD dwMode = p_dwMode.getValue();
        dwMode.setValue(dwMode.intValue() | ENABLE_VIRTUAL_TERMINAL_PROCESSING);
        Function SetConsoleMode = Function.getFunction("kernel32", "SetConsoleMode");
        SetConsoleMode.invoke(WinDef.BOOL.class, new Object[]{hOut, dwMode});
    }

    public static void moveCursor(int row, int col) {
        char escCode = 0x1B;
        System.out.printf("%c[%d;%df",escCode,row,col);
    }

    public static void clear() {
        moveCursor(0,0);
        System.out.println(CLEAR_LINE);
        moveCursor(0,0);
    }

    public static void print(Object o) {
        System.out.print(o);
    }
}
