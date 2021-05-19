package org.rainday.example.javafx_listening;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;

public class JfxDispatchService extends AbstractExecutorService {
    private boolean running = false;

    public JfxDispatchService() {
        this.running = true;
    }

    public void shutdown() {
        this.running = false;
    }

    public List<Runnable> shutdownNow() {
        this.running = false;
        return new ArrayList(0);
    }

    public boolean isShutdown() {
        return !this.running;
    }

    public boolean isTerminated() {
        return !this.running;
    }

    public boolean awaitTermination(long var1, TimeUnit var3) throws InterruptedException {
        return true;
    }

    public void execute(Runnable var1) {
        Platform.runLater(var1);
    }
}
