/**
 * @author wyd
 * @date 2021-05-19 10:26:22
 * @version 1.0 edit by wyd at 2021-05-19 10:26:22
 */
module java.keyhook {

    requires javafx.graphics;
    requires javafx.controls;
    requires java.logging;

    requires com.sun.jna;
    requires com.sun.jna.platform;

    requires jnativehook;
    requires system.hook;

    exports org.rainday.example.javafx_listening to javafx.graphics;
}
