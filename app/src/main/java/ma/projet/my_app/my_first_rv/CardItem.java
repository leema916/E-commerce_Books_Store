package ma.projet.my_app.my_first_rv;

public class CardItem {
    private int imageResId;
    private String title;

    public CardItem(int imageResId, String title) {
        this.imageResId = imageResId;
        this.title = title;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getTitle() {
        return title;
    }
}

