package service;

import content.Content;
import content.player.PlayHistory;
import content.player.Player;
import repository.PlayHistoryRepository;

public class PlayService {

    private final PlayHistoryRepository playHistoryRepository;
    private final Player player;

    public PlayService(PlayHistoryRepository playHistoryRepository, Player player) {
        this.playHistoryRepository = playHistoryRepository;
        this.player = player;
    }

    public void playContent(Content content){
        if(player.isCurrentPlaying()){
            Content currentContent = player.getCurrentContent();
            int lastWatchingTime = player.getCurrentWatchedTime();
            PlayHistory playHistory = new PlayHistory(currentContent.getId(), lastWatchingTime);
            playHistoryRepository.save(currentContent.getId(), playHistory);
        }
        int lastWatchingTime = playHistoryRepository.get(content.getId())
                .map(PlayHistory::getLastWatchingTime)
                .orElse(0);
        player.play(content, lastWatchingTime);
    }

    public void stopContent(Content content){
        player.stop();
        PlayHistory playHistory = new PlayHistory(content.getId(), 123);
        playHistoryRepository.save(content.getId(), playHistory);
    }
}
