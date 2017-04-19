package com.example.admin.kingofmath2.Acitivities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.admin.kingofmath2.R;
import com.example.admin.kingofmath2.Utils.CircleTransform;
import com.example.admin.kingofmath2.Utils.DBHelper;
import com.example.admin.kingofmath2.Utils.Defaults;
import com.example.admin.kingofmath2.Utils.RoundImage;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    public static String PREFRENCE_FILNAME = "com.example.admin.kingofmath";
    SharedPreferences share;
    ImageView imgProfil;
    TextView scoreTotale, nomProfil;
    int scorevalue;
    ImageButton adventure, tutorial, quiz, multiplayer,statistics,leaderboard;
    String objectId = null;
    DBHelper db;
    Bitmap bm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Backendless.setUrl( Defaults.SERVER_URL );
        Backendless.initApp( getApplicationContext(), Defaults.APPLICATION_ID, Defaults.API_KEY );


        setContentView(R.layout.activity_menu);
        db = new DBHelper(this);

        RoundImage roundedImage;
        imgProfil = (ImageView) findViewById(R.id.imgProfil);
        nomProfil = (TextView) findViewById(R.id.NomProfil);
        scoreTotale = (TextView) findViewById(R.id.ScoreTotal);

        adventure = (ImageButton) findViewById(R.id.adventure);
        quiz = (ImageButton) findViewById(R.id.quiz);
        multiplayer = (ImageButton) findViewById(R.id.multiplayer);
        tutorial = (ImageButton) findViewById(R.id.tutorial);
        statistics = (ImageButton) findViewById(R.id.statistics);
        leaderboard = (ImageButton) findViewById(R.id.Leaderboard);

        share = getSharedPreferences(PREFRENCE_FILNAME, Context.MODE_PRIVATE);
        final String login = share.getString("loginShared", "");
        String postionImage = share.getString("positionImage", "");
        String ID = share.getString("ObjectId", "");
        nomProfil.setText(login);

         if (postionImage.equals("0")) {
             bm = BitmapFactory.decodeResource(getResources(), R.drawable.player0);
            roundedImage = new RoundImage(bm);
            imgProfil.setImageDrawable(roundedImage);
        }
         if (postionImage.equals("1")) {
             bm = BitmapFactory.decodeResource(getResources(), R.drawable.player1);
            roundedImage = new RoundImage(bm);
            imgProfil.setImageDrawable(roundedImage);
        }
         if (postionImage.equals("2")) {
             bm = BitmapFactory.decodeResource(getResources(), R.drawable.player2);
            roundedImage = new RoundImage(bm);
            imgProfil.setImageDrawable(roundedImage);
        }
         if (postionImage.equals("3")) {
             bm = BitmapFactory.decodeResource(getResources(), R.drawable.player3);
            roundedImage = new RoundImage(bm);
            imgProfil.setImageDrawable(roundedImage);
        }
         if (postionImage.equals("4")) {
             bm = BitmapFactory.decodeResource(getResources(), R.drawable.player4);
            roundedImage = new RoundImage(bm);
            imgProfil.setImageDrawable(roundedImage);
        }
        if(!share.getString("Idfacebook","").equals("")&& share.getString("choice","").equals("FB")){

            String profileImg = "https://graph.facebook.com/" + share.getString("Idfacebook", "") + "/picture?type=large&width=80";
            Picasso.with(getApplicationContext()).load(profileImg).transform(new CircleTransform()).into(imgProfil);
        }

        scorevalue = CalculScore();scoreTotale.setText(scorevalue + " pts");

/*
        if((ID.equals(""))&&(!(share.getString("positionImage","").equals("")))&&((share.getString("Idfacebook","").equals("")))) {

            scorevalue = CalculScore();scoreTotale.setText(scorevalue + " pts");
            HashMap testObject = new HashMap<>();

            if(!share.getString("ObjectId","").equals("")){


                testObject.put("objectId",share.getString("ObjectId",""));
            }

                testObject.put( "name", login);
                testObject.put( "idfacebook", share.getString("Idfacebook", "") );
                testObject.put( "idposition", share.getString("positionImage","") );
                testObject.put( "score", scorevalue);
                Backendless.Persistence.of( "Leader" ).save(testObject, new AsyncCallback<Map>() {
                    @Override
                    public void handleResponse(Map response) {
                        SharedPreferences.Editor editor=share.edit();
                        editor.putString("ObjectId", (String) response.get("objectId"));
                        editor.commit();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Log.e( "MYAPP", "Server reported an error " + fault.getMessage() );
                    }
                });




        }
        if((ID.equals(""))&&(!(share.getString("Idfacebook","").equals("")))) {
            scorevalue = CalculScore();scoreTotale.setText(scorevalue + " pts");
            HashMap testObject = new HashMap<>();

            if(!share.getString("ObjectId","").equals("")){


                testObject.put("objectId",share.getString("ObjectId",""));
            }

                testObject.put( "name", login);
                testObject.put( "idfacebook", share.getString("Idfacebook", "") );
                testObject.put( "idposition", share.getString("positionImage","") );
                testObject.put( "score", scorevalue);
                Backendless.Persistence.of( "Leader" ).save(testObject, new AsyncCallback<Map>() {
                    @Override
                    public void handleResponse(Map response) {
                        SharedPreferences.Editor editor=share.edit();
                        editor.putString("ObjectId", (String) response.get("objectId"));
                        editor.commit();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Log.e( "MYAPP", "Server reported an error " + fault.getMessage() );
                    }
                });


        }

    if(!ID.equals("")&&(!(share.getString("positionImage","").equals("")))&&((share.getString("Idfacebook","").equals("")))){


        scorevalue = CalculScore();scoreTotale.setText(scorevalue + " pts");
        HashMap testObject = new HashMap<>();

        if(!share.getString("ObjectId","").equals("")){

            testObject.put("objectId",share.getString("ObjectId",""));
        }

            testObject.put( "name", login);
            testObject.put( "idfacebook", share.getString("Idfacebook", "") );
            testObject.put( "idposition", share.getString("positionImage","") );
            testObject.put( "score", scorevalue);
            Backendless.Persistence.of( "Leader" ).save(testObject, new AsyncCallback<Map>() {
                @Override
                public void handleResponse(Map response) {
                    SharedPreferences.Editor editor=share.edit();
                    editor.putString("ObjectId", (String) response.get("objectId"));
                    editor.commit();
                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    Log.e( "MYAPP", "Server reported an error " + fault.getMessage() );
                }
            });



        }
        if((!(share.getString("Idfacebook","").equals("")))&&(!ID.equals(""))){
*/
            scorevalue = CalculScore();scoreTotale.setText(scorevalue + " pts");
            HashMap testObject = new HashMap<>();

            if(!share.getString("ObjectId","").equals("")){

                testObject.put("objectId",share.getString("ObjectId",""));
            }

                testObject.put( "name", login);
                testObject.put( "idfacebook", share.getString("Idfacebook", "") );
                testObject.put( "idposition", share.getString("positionImage","") );
                testObject.put( "score", scorevalue);
                Backendless.Persistence.of( "Leader" ).save(testObject, new AsyncCallback<Map>() {
                    @Override
                    public void handleResponse(Map response) {
                        SharedPreferences.Editor editor=share.edit();
                        editor.putString("ObjectId", (String) response.get("objectId"));
                        editor.commit();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Log.e( "MYAPP", "Server reported an error " + fault.getMessage() );
                    }
                });


       // }


        multiplayer.setOnClickListener(this);
        quiz.setOnClickListener(this);
        leaderboard.setOnClickListener(this);
        adventure.setOnClickListener(this);
        statistics.setOnClickListener(this);
        tutorial.setOnClickListener(this);


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.multiplayer:
                startActivity(new Intent(this, MultiplayerActivity.class));
                finish();
                System.exit(0);
                break;
            case R.id.quiz:
                Intent ve=new Intent(getApplicationContext(),ListquizActivity.class);
                startActivity(ve);
                finish();
                System.exit(0);
                break;
            case R.id.Leaderboard:
                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if(cm.getActiveNetworkInfo()!=null) {

                    Toast.makeText(getApplicationContext(), "Feature not ready yet",
                            Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, LeaderBoardActivity.class));
                    finish();
                    System.exit(0);
                }else{
                    Toast.makeText(getApplicationContext(), "No internet connexion",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.adventure:
                Intent ve1=new Intent(getApplicationContext(),AdventureActivity.class);
                startActivity(ve1);
                finish();
                System.exit(0);
                break;
            case R.id.statistics:
                startActivity(new Intent(this, StatisticActivity.class));
                finish();
                System.exit(0);
                break;
            case R.id.tutorial:
                startActivity(new Intent(this, Tutorial_Activity.class));
                finish();
                System.exit(0);
                break;
            default:
                break;

        }
    }
    private int CalculScore(){

        int score = db.Score(1, "Addition") + db.Score(2, "Addition") + db.Score(3, "Addition")
                + db.Score(4, "Addition") + db.Score(5, "Addition") + db.Score(6, "Addition")
                + db.Score(7, "Addition") + db.Score(8, "Addition") + db.Score(9, "Addition") + db.Score(10, "Addition")
                + db.Score(1, "soustraction") + db.Score(2, "soustraction") + db.Score(3, "soustraction")
                + db.Score(4, "soustraction") + db.Score(5, "soustraction") + db.Score(6, "soustraction")
                + db.Score(7, "soustraction") + db.Score(8, "soustraction") + db.Score(9, "soustraction") + db.Score(10, "soustraction")
                + db.Score(1, "multiplication") + db.Score(2, "multiplication") + db.Score(3, "multiplication")
                + db.Score(4, "multiplication") + db.Score(5, "multiplication") + db.Score(6, "multiplication")
                + db.Score(7, "multiplication") + db.Score(8, "multiplication") + db.Score(9, "multiplication") + db.Score(10, "multiplication")
                + db.Score(1, "division") + db.Score(2, "division") + db.Score(3, "division")
                + db.Score(4, "division") + db.Score(5, "division") + db.Score(6, "division")
                + db.Score(7, "division") + db.Score(8, "division") + db.Score(9, "division")
                + db.Score(10, "division") + db.Score(0, "quizz") + db.Score(1, "quizz") + db.Score(2, "quizz") + db.Score(3, "quizz")
                + db.Score(4, "quizz") + db.Score(5, "quizz") + db.Score(6, "quizz");


        return score;

    }

}