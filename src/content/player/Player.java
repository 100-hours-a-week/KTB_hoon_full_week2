package content.player;

import content.Content;

public interface Player {
    boolean isCurrentPlaying();
    void play(Content content, int watchedTime);
    void stop();
    Content getCurrentContent();
    int getCurrentWatchedTime();
    void reportProgress(int currentWatchedTime);
}
