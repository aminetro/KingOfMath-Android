package com.example.admin.kingofmath2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ImageButton player1,player2,player3,player4;
    Button connect;
    RoundImage roundedImage1,roundedImage2,roundedImage3,roundedImage4;
    int positionImage=0;
    EditText editText;
    public static String PREFRENCE_FILNAME = "com.example.admin.kingofmath";
    SharedPreferences share;
    DBHelper db;
    private CallbackManager mcallbackManager;
    String login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        player1=(ImageButton)findViewById(R.id.player1);
        player2=(ImageButton)findViewById(R.id.player2);
        player3=(ImageButton)findViewById(R.id.player3);
        player4=(ImageButton)findViewById(R.id.player4);
        connect=(Button)findViewById(R.id.button);

        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.player1);
        roundedImage1 = new RoundImage(bm);
        player1.setImageDrawable(roundedImage1);

        Bitmap bm2 = BitmapFactory.decodeResource(getResources(), R.drawable.player2);
        roundedImage2 = new RoundImage(bm2);
        player2.setImageDrawable(roundedImage2);

        Bitmap bm3 = BitmapFactory.decodeResource(getResources(), R.drawable.player3);
        roundedImage3 = new RoundImage(bm3);
        player3.setImageDrawable(roundedImage3);

        Bitmap bm4 = BitmapFactory.decodeResource(getResources(), R.drawable.player4);
        roundedImage4 = new RoundImage(bm4);
        player4.setImageDrawable(roundedImage4);

        player1.setBackgroundDrawable(null);player2.setBackgroundDrawable(null);player3.setBackgroundDrawable(null);
        player4.setBackgroundDrawable(null);


        player1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player2.setBackgroundDrawable(null);
                player3.setBackgroundDrawable(null);
                player4.setBackgroundDrawable(null);
                player1.setBackgroundResource(R.drawable.some_selector_name);
                positionImage = 0;
                positionImage = positionImage + 1;
            }
        });

        player2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player1.setBackgroundDrawable(null);player3.setBackgroundDrawable(null);
                player4.setBackgroundDrawable(null);
                player2.setBackgroundResource(R.drawable.some_selector_name);
                positionImage=0;
                positionImage=positionImage+2;
            }
        });

        player3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player1.setBackgroundDrawable(null);player2.setBackgroundDrawable(null);
                player4.setBackgroundDrawable(null);
                player3.setBackgroundResource(R.drawable.some_selector_name);
                positionImage=0;
                positionImage=positionImage+3;
            }
        });

        player4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player1.setBackgroundDrawable(null);player3.setBackgroundDrawable(null);
                player2.setBackgroundDrawable(null);
                player4.setBackgroundResource(R.drawable.some_selector_name);
                positionImage=0;
                positionImage=positionImage+4;
            }
        });

        editText = (EditText) findViewById(R.id.editText);
        //TODO: Get Preferences
        share= getSharedPreferences(PREFRENCE_FILNAME, Context.MODE_PRIVATE);
        final String[] login = {share.getString("loginShared", "")};

        if (!login[0].equals("")) {
            startActivity(new Intent(getApplicationContext(), MenuActivity.class));
        }

        mcallbackManager = CallbackManager.Factory.create();
        final LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.registerCallback(mcallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getApplicationContext(), "login Success", Toast.LENGTH_SHORT).show();
                GraphRequest request = GraphRequest.newMeRequest(
                        AccessToken.getCurrentAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {
                                String id = object.optString("id");
                                String name = object.optString("name");
                                share= getSharedPreferences(PREFRENCE_FILNAME, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor=share.edit();
                                editor.putString("loginShared", name);
                                editor.putString("Idfacebook", id);
                                editor.putString("positionImage",String.valueOf(0));
                                editor.commit();
                                startActivity(new Intent(getApplication(), MenuActivity.class));
                                Log.d("Name :    ", name);
                                Log.d("id :     ", object.optString("id"));

                            }

                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,link,location");
                request.setParameters(parameters);

                request.executeAsync();
                db = new DBHelper(getApplication());
                if(share.getString("loginShared","").equals("")) {
                    Player c = new Player(1, "Addition", 0);
                    Player c1 = new Player(2, "Addition", 0);
                    Player c2 = new Player(3, "Addition", 0);
                    Player c3 = new Player(4, "Addition", 0);
                    Player c4 = new Player(5, "Addition", 0);
                    Player c5 = new Player(6, "Addition", 0);
                    Player c6 = new Player(7, "Addition", 0);
                    Player c7 = new Player(8, "Addition", 0);
                    Player c8 = new Player(9, "Addition", 0);
                    Player c9 = new Player(10, "Addition", 0);

                    Player c10 = new Player(1, "soustraction", 0);
                    Player c11 = new Player(2, "soustraction", 0);
                    Player c12 = new Player(3, "soustraction", 0);
                    Player c13 = new Player(4, "soustraction", 0);
                    Player c14 = new Player(5, "soustraction", 0);
                    Player c15 = new Player(6, "soustraction", 0);
                    Player c16 = new Player(7, "soustraction", 0);
                    Player c17 = new Player(8, "soustraction", 0);
                    Player c18 = new Player(9, "soustraction", 0);
                    Player c19 = new Player(10, "soustraction", 0);

                    Player c20 = new Player(1, "multiplication", 0);
                    Player c21 = new Player(2, "multiplication", 0);
                    Player c22 = new Player(3, "multiplication", 0);
                    Player c23 = new Player(4, "multiplication", 0);
                    Player c24 = new Player(5, "multiplication", 0);
                    Player c25 = new Player(6, "multiplication", 0);
                    Player c26 = new Player(7, "multiplication", 0);
                    Player c27 = new Player(8, "multiplication", 0);
                    Player c28 = new Player(9, "multiplication", 0);
                    Player c29 = new Player(10, "multiplication", 0);

                    Player c30 = new Player(1, "division", 0);
                    Player c31 = new Player(2, "division", 0);
                    Player c32 = new Player(3, "division", 0);
                    Player c33 = new Player(4, "division", 0);
                    Player c34 = new Player(5, "division", 0);
                    Player c35 = new Player(6, "division", 0);
                    Player c36 = new Player(7, "division", 0);
                    Player c37 = new Player(8, "division", 0);
                    Player c38 = new Player(9, "division", 0);
                    Player c39 = new Player(10, "division", 0);
                    Player c40 = new Player(0, "quizz", 0);
                    Player c41 = new Player(1, "quizz", 0);
                    Player c42 = new Player(2, "quizz", 0);
                    Player c43 = new Player(3, "quizz", 0);
                    Player c44 = new Player(4, "quizz", 0);
                    Player c45 = new Player(5, "quizz", 0);
                    Player c46 = new Player(6, "quizz", 0);


                    db.insertCandidat(c);
                    db.insertCandidat(c1);
                    db.insertCandidat(c2);
                    db.insertCandidat(c3);
                    db.insertCandidat(c4);
                    db.insertCandidat(c5);
                    db.insertCandidat(c6);
                    db.insertCandidat(c7);
                    db.insertCandidat(c8);
                    db.insertCandidat(c9);
                    db.insertCandidat(c10);
                    db.insertCandidat(c11);
                    db.insertCandidat(c12);
                    db.insertCandidat(c13);
                    db.insertCandidat(c14);
                    db.insertCandidat(c15);
                    db.insertCandidat(c16);
                    db.insertCandidat(c17);
                    db.insertCandidat(c18);
                    db.insertCandidat(c19);
                    db.insertCandidat(c20);
                    db.insertCandidat(c21);
                    db.insertCandidat(c22);
                    db.insertCandidat(c23);
                    db.insertCandidat(c24);
                    db.insertCandidat(c25);
                    db.insertCandidat(c26);
                    db.insertCandidat(c27);
                    db.insertCandidat(c28);
                    db.insertCandidat(c29);

                    db.insertCandidat(c30);
                    db.insertCandidat(c31);
                    db.insertCandidat(c32);
                    db.insertCandidat(c33);
                    db.insertCandidat(c34);
                    db.insertCandidat(c35);
                    db.insertCandidat(c36);
                    db.insertCandidat(c37);
                    db.insertCandidat(c38);
                    db.insertCandidat(c39);
                    db.insertCandidat(c40);
                    db.insertCandidat(c41);
                    db.insertCandidat(c42);
                    db.insertCandidat(c43);
                    db.insertCandidat(c44);
                    db.insertCandidat(c45);
                    db.insertCandidat(c46);
                }
                startActivity(new Intent(getApplicationContext(), MenuActivity.class));

            }


            @Override
            public void onCancel() {
                Log.e("onCancel", "facebook login canceled");
            }

            @Override
            public void onError(FacebookException e) {
                Log.e("onError", "facebook login failed error");
            }
        });




        editText.setText(login[0]);
        connect.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
                case R.id.button:
                     login = editText.getText().toString();
                    System.out.println(login);
                    if (!login.equals("")) {

                        //TODO Set preferences Value

                        db = new DBHelper(this);
                        if(share.getString("loginShared","").equals("")) {
                            Player c = new Player(1, "Addition", 0);Player c1 = new Player(2, "Addition", 0);
                            Player c2 = new Player(3, "Addition", 0);Player c3 = new Player(4, "Addition", 0);
                            Player c4 = new Player(5, "Addition", 0);Player c5 = new Player(6, "Addition", 0);
                            Player c6 = new Player(7, "Addition", 0);Player c7 = new Player(8, "Addition", 0);
                            Player c8 = new Player(9, "Addition", 0);Player c9 = new Player(10, "Addition", 0);

                            Player c10 = new Player(1, "soustraction", 0);Player c11 = new Player(2, "soustraction", 0);
                            Player c12 = new Player(3, "soustraction", 0);Player c13 = new Player(4, "soustraction", 0);
                            Player c14 = new Player(5, "soustraction", 0);Player c15 = new Player(6, "soustraction", 0);
                            Player c16 = new Player(7, "soustraction", 0);Player c17 = new Player(8, "soustraction", 0);
                            Player c18 = new Player(9, "soustraction", 0);Player c19 = new Player(10, "soustraction", 0);

                            Player c20 = new Player(1, "multiplication", 0);Player c21 = new Player(2, "multiplication", 0);
                            Player c22 = new Player(3, "multiplication", 0);Player c23 = new Player(4, "multiplication", 0);
                            Player c24 = new Player(5, "multiplication", 0);Player c25 = new Player(6, "multiplication", 0);
                            Player c26 = new Player(7, "multiplication", 0);Player c27 = new Player(8, "multiplication", 0);
                            Player c28 = new Player(9, "multiplication", 0);Player c29 = new Player(10, "multiplication", 0);

                            Player c30 = new Player(1, "division", 0);Player c31 = new Player(2, "division", 0);
                            Player c32 = new Player(3, "division", 0);Player c33 = new Player(4, "division", 0);
                            Player c34 = new Player(5, "division", 0);Player c35 = new Player(6, "division", 0);
                            Player c36 = new Player(7, "division", 0);Player c37 = new Player(8, "division", 0);
                            Player c38 = new Player(9, "division", 0);
                            Player c39 = new Player(10, "division", 0);Player c40 = new Player(0, "quizz", 0);
                            Player c41 = new Player(1, "quizz", 0);Player c42 = new Player(2, "quizz", 0);
                            Player c43 = new Player(3, "quizz", 0);Player c44 = new Player(4, "quizz", 0);
                            Player c45 = new Player(5, "quizz", 0);Player c46 = new Player(6, "quizz", 0);


                            db.insertCandidat(c);db.insertCandidat(c1);db.insertCandidat(c2);
                            db.insertCandidat(c3);db.insertCandidat(c4);db.insertCandidat(c5);
                            db.insertCandidat(c6);db.insertCandidat(c7);db.insertCandidat(c8);
                            db.insertCandidat(c9);db.insertCandidat(c10);db.insertCandidat(c11);
                            db.insertCandidat(c12);db.insertCandidat(c13);db.insertCandidat(c14);
                            db.insertCandidat(c15);db.insertCandidat(c16);db.insertCandidat(c17);
                            db.insertCandidat(c18);db.insertCandidat(c19);db.insertCandidat(c20);
                            db.insertCandidat(c21);db.insertCandidat(c22);db.insertCandidat(c23);
                            db.insertCandidat(c24);db.insertCandidat(c25);db.insertCandidat(c26);
                            db.insertCandidat(c27);db.insertCandidat(c28);db.insertCandidat(c29);

                            db.insertCandidat(c30);db.insertCandidat(c31);db.insertCandidat(c32);
                            db.insertCandidat(c33);db.insertCandidat(c34); db.insertCandidat(c35);
                            db.insertCandidat(c36);db.insertCandidat(c37);db.insertCandidat(c38);
                            db.insertCandidat(c39);db.insertCandidat(c40);db.insertCandidat(c41);
                            db.insertCandidat(c42);db.insertCandidat(c43);db.insertCandidat(c44);
                            db.insertCandidat(c45);db.insertCandidat(c46);
                        }
                        share= getSharedPreferences(PREFRENCE_FILNAME, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=share.edit();
                        editor.putString("loginShared", login);
                        editor.putString("positionImage", String.valueOf(positionImage));

                        editor.commit();
                        startActivity(new Intent(this, MenuActivity.class));


                    }
                    else {
                        Toast.makeText(getApplicationContext(), "please, tap Name ", Toast.LENGTH_SHORT).show();
                    }
                    break;


        }

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mcallbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
