package drawable.Desktop.Esas.app.src.main.java.com.MiniProjet.Divers;

import android.graphics.drawable.Drawable;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by XssX on 05/11/2015.
 */
public  class Utils {


    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");

            return d;
        } catch (Exception e) {
            return null;
        }
    }
}
