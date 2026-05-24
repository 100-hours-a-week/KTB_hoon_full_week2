package content.player;

import content.Content;
import service.PlayerRunnable;
import view.OutputView;

public class BasicPlayer implements Player {

    private boolean currentPlayingStatus = false;
    private Content currentContent;
    private int currentWatchedTime;
    private Thread playerThread;

    @Override
    public boolean isCurrentPlaying() {
        return currentPlayingStatus;
    }

    @Override
    public void play(Content content, int watchedTime) {
        if (playerThread != null && playerThread.isAlive()) { // 현재 재생 스레드가 이미 재생 중이라면
            playerThread.interrupt();
            try {
                playerThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        OutputView.printContentPlayStatus(content);
        currentPlayingStatus = true;
        currentContent = content;
        currentWatchedTime = watchedTime;
        playerThread = new Thread(new PlayerRunnable(this, content, watchedTime));
        playerThread.start();
    }

    @Override
    public void stop() {
        if (!currentPlayingStatus) {
            return;
        }
        if (playerThread != null) {
            playerThread.interrupt();
            try {
                playerThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        OutputView.printContentStopStatus(currentContent);
        currentPlayingStatus = false;
    }

    @Override
    public Content getCurrentContent() {
        return currentContent;
    }

    @Override
    public int getCurrentWatchedTime() {
        return currentWatchedTime;
    }

    @Override
    public void reportProgress(int currentWatchedTime) {
        this.currentWatchedTime = currentWatchedTime;
    }
}