package content.player;

import content.Content;

public interface Player {
    boolean isCurrentPlaying();
    void play(Content content, int watchedTime);
    void stop(Content content);
    Content getCurrentContent();
    int getCurrentWatchedTime();
}
