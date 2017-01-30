package graph.cool.instagramhttpexample;

public class Post {
    private String id;
    private String description;
    private String imageUrl;

    public Post() {

    }

    public Post(String id, String description, String imageUrl) {
        this.id = id;
        this.description = description;
        this.imageUrl = imageUrl;
    }


    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return imageUrl;
    }
}
