package repository;

import content.player.PlayHistory;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PlayHistoryRepository {

    private final Map<Integer, PlayHistory> playHistoryMap = new HashMap<>();

    public void save(int contentId, PlayHistory playHistory) {
        playHistoryMap.put(contentId, playHistory);
    }

    public Optional<PlayHistory> get(int contentId) {
        return Optional.ofNullable(playHistoryMap.get(contentId));
    }
}
