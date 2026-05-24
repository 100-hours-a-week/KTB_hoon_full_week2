package content.player;

import content.Content;
import service.PlayerRunnable;
import view.OutputView;

public class BasicPlayer implements Player {

    private boolean currentPlayingStatus = false;
    private Content currentContent;
    private int currentWatchedTime;

    @Override
    public boolean isCurrentPlaying(){
        return currentPlayingStatus;
    }

    @Override
    public void play(Content content, int watchedTime) {
        OutputView.printContentPlayStatus(content);
        currentPlayingStatus = true;
        currentContent = content;
        currentWatchedTime = watchedTime;
        Thread playerThread = new Thread(new PlayerRunnable(content, currentWatchedTime));
        playerThread.start();
    }

    @Override
    public void stop(Content content) {
        OutputView.printContentPauseStatus(content);
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
}
