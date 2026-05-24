package service;

import content.Content;
import view.OutputView;

public class PlayerRunnable implements Runnable{

    private final Content content;
    private int currentWatchedTime;

    public PlayerRunnable(Content content, int currentWatchedTime) {
        this.content = content;
        this.currentWatchedTime = currentWatchedTime;
    }

    public void run() {
        final int totalRunningTime = content.getRunningTime();
        final int intervalSeconds = 10;
        final int minutesPerInterval = 10;
        while (currentWatchedTime < totalRunningTime) {
            try {
                Thread.sleep(intervalSeconds * 1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            currentWatchedTime += minutesPerInterval;
            if (currentWatchedTime > totalRunningTime) {
                currentWatchedTime = totalRunningTime;
            }
            OutputView.printContentProgress(content, currentWatchedTime);
        }
        OutputView.printMessage("[재생 완료: " + content.getName() + "]");
    }
}
