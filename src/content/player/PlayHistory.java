package content.player;

public class PlayHistory {

    private final int contentId;
    private int lastWatchingTime;

    public PlayHistory(int contentId, int lastWatchingTime) {
        this.contentId = contentId;
        this.lastWatchingTime = lastWatchingTime;
    }

    public int getContentId() {
        return contentId;
    }

    public int getLastWatchingTime() {
        return lastWatchingTime;
    }
}
