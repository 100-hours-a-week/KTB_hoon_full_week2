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

        // TODO : 재생 기록 null 여부 확인 부분 수정 필요
        // 기존 재생 정보 있으면 가져와야 함
        final PlayHistory history = playHistoryRepository.get(content.getId()).orElse(null);
        player.play(content, history != null ? history.getLastWatchingTime() : 0);
    }

    public void stopContent(Content content){
        player.stop(content);
        PlayHistory playHistory = new PlayHistory(content.getId(), 123);
        playHistoryRepository.save(content.getId(), playHistory);
    }
}
