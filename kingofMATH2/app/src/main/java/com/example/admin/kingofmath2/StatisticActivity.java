package com.example.admin.kingofmath2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.share.ShareApi;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.squareup.picasso.Picasso;

public class StatisticActivity extends AppCompatActivity {
     TextView nomprofil,scoreadventure,scorequiz,scoreTotale;
    ImageView profilImage;
    DBHelper db;
    public static String PREFRENCE_FILNAME = "com.example.admin.kingofmath";
    SharedPreferences share;
    RoundImage roundedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DBHelper(this);
        setContentView(R.layout.activity_statistic);
        nomprofil = (TextView)findViewById(R.id.NomProfil);
        scoreadventure = (TextView)findViewById(R.id.scoreAdventure);
        scorequiz = (TextView)findViewById(R.id.scoreQuiz);
        scoreTotale = (TextView)findViewById(R.id.scoreT);
        profilImage = (ImageView)findViewById(R.id.ProfilPhoto);

        share = getSharedPreferences(PREFRENCE_FILNAME, Context.MODE_PRIVATE);
        final String login = share.getString("loginShared", "");
        String postionImage = share.getString("positionImage", "");
        String ID = share.getString("ObjectId", "");
        nomprofil.setText(login);
        final View rootView = (View) findViewById(android.R.id.content);

        if(!share.getString("Idfacebook","").equals("")){
            String profileImg = "https://graph.facebook.com/" + share.getString("Idfacebook", "") + "/picture?type=large&width=80";
            Picasso.with(getApplicationContext()).load(profileImg).transform(new CircleTransform()).into(profilImage);
        }
         if (postionImage.equals("0")) {
            Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.player0);
            roundedImage = new RoundImage(bm);
            profilImage.setImageDrawable(roundedImage);
        }
         if (postionImage.equals("1")) {
            Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.player1);
            roundedImage = new RoundImage(bm);
            profilImage.setImageDrawable(roundedImage);
        }
         if (postionImage.equals("2")) {
            Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.player2);
            roundedImage = new RoundImage(bm);
            profilImage.setImageDrawable(roundedImage);
        }
         if (postionImage.equals("3")) {
            Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.player3);
            roundedImage = new RoundImage(bm);
            profilImage.setImageDrawable(roundedImage);
        }
         if (postionImage.equals("4")) {
            Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.player4);
            roundedImage = new RoundImage(bm);
            profilImage.setImageDrawable(roundedImage);
        }

        int   scoread,scoreQ,score;
        scoread = db.Score(1, "Addition") + db.Score(2, "Addition") + db.Score(3, "Addition")
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
                + db.Score(10, "division");
        scoreQ = db.Score(10, "division") + db.Score(0, "quizz") + db.Score(1, "quizz") + db.Score(2, "quizz") + db.Score(3, "quizz")
                + db.Score(4, "quizz") + db.Score(5, "quizz") + db.Score(6, "quizz");

        score=scoreQ+scoread;
        scorequiz.setText("Score Quizz = "+scoreQ);
        scoreadventure.setText("Score Adventure = "+scoread);
        scoreTotale.setText("Score Total ="+score);



    }

    public static Bitmap getScreenShot(View view) {
        View screenView = view.getRootView();
        screenView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(screenView.getDrawingCache());
        screenView.setDrawingCacheEnabled(false);
        return bitmap;
    }
    private void showSimplePopUp() {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Success");
        helpBuilder.setMessage("Your scores are shared on facebook");
        helpBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // ptetr
                    }
                });

        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();

    }
    public void publishImage(Bitmap bitmap) {

        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(bitmap)
                .build();
        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .build();
        ShareApi.share(content, null);
        showSimplePopUp();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_statistic, menu);
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
}
