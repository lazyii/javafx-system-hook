package org.rainday.test.keyHook;

import com.sun.javafx.tk.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

/**
 * @author wyd
 * @date 2021-03-20 5:42:18
 * @version 1.0 edit by wyd at 2021-03-20 5:42:18
 */
public class JfxMain extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        System.out.println(Platform.isKeyLocked(KeyCode.CAPS));
        
        System.out.println(Toolkit.getToolkit().isKeyLocked(KeyCode.INSERT));
        primaryStage.setTitle("haha");
        
        GridPane grid = new GridPane();//网格式布局，由行列网格控制
        grid.setAlignment(Pos.CENTER);//对齐方式，默认靠左对齐，当前设置居中对齐
        grid.setHgap(10);//设置水平间隔
        grid.setVgap(10);//设置垂直间隔
        grid.setPadding(new Insets(25, 25, 25, 25));//设置Padding，顺序是：上、右、下、左
        
        Scene scene = new Scene(grid, 300, 275);//新建Scene，并将网格式Panel置于其中
        primaryStage.setScene(scene);//设置场景
        
        Text scenetitle = new javafx.scene.text.Text("欢迎标题");
        //scenetitle.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);
        
        //创建Label对象，放到第0列，第1行
        Label userName = new Label("用户名：");
        grid.add(userName, 0, 1);
        
        //创建文本输入框，放到第1列，第1行
        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);
        
        Label pw = new Label("密 码：");
        grid.add(pw, 0, 2);
        
        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);
        
        Button btn = new Button("登录");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);//将按钮控件作为子节点
        grid.add(hbBtn, 1, 4);//将HBox pane放到grid中的第1列，第4行
        
        final Text actiontarget = new Text();//增加用于显示信息的文本
        grid.add(actiontarget, 1, 6);
        
        scene.addEventHandler(KeyEvent.ANY,event -> {
            System.out.println(event.getEventType() + "  " + event.getText() + "  " + event.getCode());
            scenetitle.setText("状态：" + (Platform.isKeyLocked(KeyCode.CAPS).get() ? "ON" : "OFF"));
        });
        
        primaryStage.getOwner().addEventHandler(WindowEvent.WINDOW_HIDDEN,event -> {
            System.out.println("窗口隐藏了");
        });
        
        
        grid.hoverProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(oldValue.booleanValue() + "  " + newValue.booleanValue());
            scenetitle.setText("状态sss：" + (Platform.isKeyLocked(KeyCode.CAPS).get() ? "ON" : "OFF"));
        });
        
        primaryStage.focusedProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(Platform.isKeyLocked(KeyCode.CAPS));
            scenetitle.setText("状态：" + (Platform.isKeyLocked(KeyCode.CAPS).get() ? "ON" : "OFF"));
        });
        
        
        //注册全局 热键
        GlobalScreen.registerNativeHook();
        GlobalScreen.setEventDispatcher(new JfxDispatchService());
        
        //jni call java.util.logger
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);
        
        // Don't forget to disable the parent handlers.
        logger.setUseParentHandlers(false);
        GlobalScreen.addNativeKeyListener(new NativeKeyListener() {
            @Override
            public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
                System.out.println("typed: "+nativeKeyEvent.getKeyChar());
                scenetitle.setText(nativeKeyEvent.getKeyChar() + " state: " + Platform.isKeyLocked(KeyCode.CAPS));
            }
            
            @Override
            public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
                System.out.println("pressed: "+nativeKeyEvent.getKeyChar());
                scenetitle.setText(nativeKeyEvent.getKeyChar() + " state: " + Platform.isKeyLocked(KeyCode.CAPS));
                
            }
            
            @Override
            public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
                System.out.println("released: "+nativeKeyEvent.getKeyChar());
                scenetitle.setText(nativeKeyEvent.getKeyChar() + " state: " + Platform.isKeyLocked(KeyCode.CAPS));
                
            }
        });
        
        
        primaryStage.show();
    
    /*
        CompletableFuture.runAsync(() -> {
            while (true) {
                try {
                   
                    
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });*/
    }
    
    
}
