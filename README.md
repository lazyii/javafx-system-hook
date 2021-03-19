# javafx-system-hook

How to:
java system keyboard/mouse hook


- jna(有用)

- system-hook(有用)

- jnavitehook(有用)
  
- jintellitype(可能由于版本问题，无法使用)



推荐使用jnative自带了很多平台的动态库，可跨平台。

system-hook不可跨平台，仅支持win

jna本身功能强大，也可以实现keyboard hook。也可以跨平台，但是macos下的如何实现hook未实践。
由于jna本身不是为了hook而实现的，其他功能丰富，但是实现hook略显麻烦。


-- javafx 17 新增了Platform.isKeyLocked(KeyCode.CAPS)（must be one of: KeyCode.CAPS or KeyCode.NUM_LOCK. ）
解决了swing toolkit加载后keyState返回值永不变的bug。
本次新增了全局监听demo，hover 事件监听， focused 等事件。可查看demo。
