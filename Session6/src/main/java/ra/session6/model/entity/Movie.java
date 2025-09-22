package ra.session6.model.entity;

public class Movie {
    private int id;
    private String title;
    private String director;   // thêm
    private String genre;
    private String description;
    private int duration;
    private String language;   // thêm

    public Movie() {
    }

    public Movie(int id, String title, String director, String genre,
                 String description, int duration, String language) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.description = description;
        this.duration = duration;
        this.language = language;
    }

    // getter, setter đầy đủ
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
}
