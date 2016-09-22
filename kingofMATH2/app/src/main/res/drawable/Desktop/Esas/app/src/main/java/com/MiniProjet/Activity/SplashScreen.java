package drawable.Desktop.Esas.app.src.main.java.com.MiniProjet.Activity;

/**
 * Created by XssX on 01/11/2015.
 */

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.MiniProjet.Divers.FeedHandleXML;
import com.MiniProjet.Divers.IconRoundCornerProgressBar;
import com.MiniProjet.Network.JsonParser;

import java.io.File;

public class SplashScreen extends Activity {
    private LinearLayout pBar;
    String now_playing, earned;
    private FeedHandleXML obj;
    int i = 100;
    public static String directpath;
    private boolean checknoconn = false;
    IconRoundCornerProgressBar progressOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        pBar = (LinearLayout) findViewById(R.id.pBar);
        progressOne = (IconRoundCornerProgressBar) findViewById(R.id.progress_one);
        progressOne.setMax(500);
        progressOne.setProgress(100);
        /**
         * Showing splashscreen while making network calls to download necessary
         * data before launching the app Will use AsyncTask to make http call
         */
        new PrefetchData().execute();

    }

    /**
     * Async Task to make http call
     */
    private class PrefetchData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pBar.setVisibility(View.VISIBLE);
            progressOne.setProgress(progressOne.getProgress() + 100);

            // before making http calls


        }

        @Override
        protected Void doInBackground(Void... arg0) {

            ConnectivityManager connectivityManager
                    = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo ni = connectivityManager.getActiveNetworkInfo();
            if (ni == null) {

                System.out.println("no connection");
                checknoconn = true;

            } else {

            /*
             * Will make http call here This call will download required data
             * before launching the app
             * example:
             * 1. Downloading and storing in SQLite
             * 2. Downloading images
             * 3. Fetching and parsing the xml / json
             * 4. Sending device information to server
             * 5. etc.,
             */
            /*JsonParser jsonParser = new JsonParser();
            String json = jsonParser
                    .getJSONFromUrl("http://api.androidhive.info/game/game_stats.json");

            Log.e("Response: ", "> " + json);

            if (json != null) {
                try {
                    JSONObject jObj = new JSONObject(json)
                            .getJSONObject("game_stat");
                    now_playing = jObj.getString("now_playing");
                    earned = jObj.getString("earned");

                    Log.e("JSON", "> " + now_playing + earned);

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }*/

                obj = new FeedHandleXML("http://feeds.feedburner.com/Techcrunch/microsoft?format=xml");
                obj.fetchXML();

                while (obj.parsingComplete) ;


                File devDirectory = new File("/storage/emulated/0/Devcomplier/");

                // have the object build the directory structure, if
                // needed.

                if (!devDirectory.exists()) {
                    devDirectory.mkdirs();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {



                progressOne.setProgress(progressOne.getProgress() + 200);



            if (checknoconn) {
                TextView text = (TextView) findViewById(R.id.noconnection);
                text.setText("Your device should be connected to the internet");

            } else {
                progressOne.setProgress(progressOne.getMax());

                // progressOne.setProgress(1);
                super.onPostExecute(result);

                // After completing http call
                // will close this activity and lauch main activity
                Intent i = new Intent(SplashScreen.this, SampleActivity.class);

                startActivity(i);

                // close this activity
                finish();
            }
        }

    }

}