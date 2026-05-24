package content.player;

public class PlaySession {

    private int contentId;
    private PlayStatus playStatus = PlayStatus.PENDING;
    private int currentProgress;

    public PlaySession(int contentId, PlayStatus playStatus, int currentProgress) {
        this.contentId = contentId;
        this.playStatus = playStatus;
        this.currentProgress = currentProgress;
    }
}
