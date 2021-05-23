# javafx-system-hook

How to:
java system keyboard/mouse hook


- [jna](https://github.com/java-native-access/jna) (有用)

- [system-hook](https://github.com/kristian/system-hook) (有用)

- [jnavitehook](https://github.com/kwhat/jnativehook) (有用)
  
- jintellitype(可能由于版本问题，无法使用)



推荐使用jnative自带了很多平台的动态库，可跨平台。

system-hook不可跨平台，仅支持win

jna本身功能强大，也可以实现keyboard hook。也可以跨平台，但是macos下的如何实现hook未实践。
由于jna本身不是为了hook而实现的，其他功能丰富，但是实现hook略显麻烦。


-- javafx 17 新增了Platform.isKeyLocked(KeyCode.CAPS)（must be one of: KeyCode.CAPS or KeyCode.NUM_LOCK. ）
解决了swing toolkit加载后keyState返回值永不变的bug。
本次新增了全局监听demo，hover 事件监听， focused 等事件。可查看demo。


jna调用自己编译的dll
math.dll源码：
```c
int add(int a, int b)
{
	return a + b;
}

int minus(int a, int b)
{
	return a - b;
}

int multiply(int a, int b)
{
	return a * b;
}

double divide(int a, int b)
{
	double m = (double)a / b;
	return m;
}
```

##escpos thermal printer demo
1、增加jna调用自定义MathLibrary.dll的示例。
2、增加使用escpos-coffe库调用thermal printer的示例（需要驱动）,基于pipedoutputstream实现。
3、增加使用jna+JsPrinterDll.dll调用thermal printer的示例（不需要驱动）。
4、增加使用javafx printerService,SimpleDoc,DocFlavor.BYTE_ARRAY.AUTOSENSE 调用thermal printer 打印的示例。

本次使用的热敏打印机型号为：xprinter XP-58IIH。可以已经测试过的功能包括：
`1、打印字母，汉字，二维码`
`2、打印`
