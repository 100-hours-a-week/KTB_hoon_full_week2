package content;

import enums.AgeRating;
import enums.Genre;
import utils.ContentIdGenerator;

public abstract class Content {

    private final int id;
    private final String name;
    private final Genre genre;
    private final AgeRating ageRating;
    private final int runningTime;
    private final String description;

    protected Content(String name, Genre genre, AgeRating ageRating, Integer runningTime,
            String description) {
        this.id = ContentIdGenerator.generateId();
        if(name == null || name.isEmpty() || name.isBlank()){
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if(genre == null){
            throw new IllegalArgumentException("Genre cannot be null");
        }
        if(ageRating == null){
            throw new IllegalArgumentException("AgeRating cannot be null");
        }
        if(runningTime < 0){
            throw new IllegalArgumentException("RunningTime cannot be under 0");
        }
        this.name = name;
        this.genre = genre;
        this.ageRating = ageRating;
        this.runningTime = runningTime;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Genre getGenre() {
        return genre;
    }

    public AgeRating getAgeRating() {
        return ageRating;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public String getDescription() {
        return description;
    }
}
