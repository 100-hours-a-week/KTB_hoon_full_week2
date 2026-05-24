package service;

import content.Content;
import content.player.Player;
import view.OutputView;

public class PlayerRunnable implements Runnable {

    private final Player player;
    private final Content content;
    private int currentWatchedTime;

    public PlayerRunnable(Player player, Content content, int currentWatchedTime) {
        this.player = player;
        this.content = content;
        this.currentWatchedTime = currentWatchedTime;
    }

    @Override
    public void run() {
        final int totalRunningTime = content.getRunningTime();
        final int intervalSeconds = 10;
        final int minutesPerInterval = 10;
        while (currentWatchedTime < totalRunningTime) {
            try {
                Thread.sleep(intervalSeconds * 1000L); // 명시적으로 대기 시킴.
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            currentWatchedTime += minutesPerInterval;
            if (currentWatchedTime > totalRunningTime) {
                currentWatchedTime = totalRunningTime;
            }
            player.reportProgress(currentWatchedTime);
            OutputView.printContentProgress(content, currentWatchedTime);
        }

        OutputView.printMessage("[재생 완료: " + content.getName() + "]");
    }
}