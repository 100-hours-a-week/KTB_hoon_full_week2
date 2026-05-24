package content.series;

import content.Content;
import enums.AgeRating;
import enums.Genre;
import enums.SeriesType;

public class Series extends Content {

    private final int seasonNumber;
    private final int episodeNumber;
    private final SeriesType seriesType;

    public Series(
            String name,
            Genre genre,
            AgeRating ageRating,
            Integer runningTime,
            String description,
            int seasonNumber,
            int episodeNumber,
            SeriesType seriesType) {
        super(name, genre, ageRating, runningTime, description);
        if(seasonNumber < 0) {
            throw new IllegalArgumentException("The season number cannot be negative");
        }
        if(episodeNumber < 0) {
            throw new IllegalArgumentException("The episode number cannot be negative");
        }
        if(seriesType == null) {
            throw new IllegalArgumentException("The series type cannot be null");
        }
        this.seasonNumber = seasonNumber;
        this.episodeNumber = episodeNumber;
        this.seriesType = seriesType;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public SeriesType getSeriesType() {
        return seriesType;
    }
}
