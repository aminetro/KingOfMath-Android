package drawable.Desktop.Esas.app.src.main.java.com.MiniProjet.Divers;

/**
 * Created by XssX on 01/11/2015.
 */
public class Feedex {
    private String title = "title";

    private String pubDate = "pubDate";
    private String link = "link";

    private String image ;
    private int category;

    public String getTitle() {
        return title;
    }
    public int getCategory() {
        return category;
    }


    public String getPubDate() {
        return pubDate;
    }

    public String getLink() {
        return link;
    }



    public String getImage() {
        return image;
    }


    public void setTitle(String t) {
        title = t;
    }
    public void setCategory(int t) {
        category = t;
    }

    public void setImage(String t) {
        image = t;
    }



    public void setPubDate(String t) {
        pubDate = t;
    }

    public void setLink(String t) {
        link = t;
    }


}




