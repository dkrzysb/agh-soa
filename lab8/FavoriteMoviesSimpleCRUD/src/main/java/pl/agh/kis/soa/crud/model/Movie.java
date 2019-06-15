package pl.agh.kis.soa.crud.model;

public class Movie {
    private Long id;
    private String title;
    private String url;

    public Movie() { }

    public Movie(Long id, String title, String url) {
        this.id = id;
        this.title = title;
        this.url = url;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getUrl() { return url; }

    public void setId(Long id) { this.id =id; }
    public void setTitle(String title) { this.title = title; }
    public void setUrl(String url) { this.url = url; }
}
