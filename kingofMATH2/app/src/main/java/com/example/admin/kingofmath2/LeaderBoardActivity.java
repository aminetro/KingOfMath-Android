package com.example.admin.kingofmath2;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class LeaderBoardActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    List<Leader> leaderBoard = null;
    ListView maListViewLeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);
        maListViewLeader = (ListView) findViewById(R.id.leaderboardList);


        leaderBoard = new ArrayList<Leader>();
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        System.out.println("***************Verif***************" + cm.getActiveNetworkInfo());
        if(cm.getActiveNetworkInfo()!=null) {
            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("TestObject");
            query.addDescendingOrder("score");
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> list, ParseException e) {
                    int i = 0;
                    for (ParseObject p : list) {
                        Log.d("--", p.getObjectId());
                        Log.d("--", (String) p.get("name"));
                        Log.d("--", (String) p.get("idposition"));
                        Log.d("--", String.valueOf((Integer) p.get("score")));
                        i = i + 1;

                    leaderBoard.add(new Leader(p.getObjectId(), (String) p.get("name"), String.valueOf((Integer) p.get("score")), i, (String) p.get("idfacebook"), (String) p.get("idposition")));
                    maListViewLeader.setAdapter(new LeaderBoardAdapter(getBaseContext(), R.layout.oneleader, leaderBoard));

                    }
                }
            });
        }
        else {
            Toast.makeText(getApplicationContext(),"No internet connexion",
                    Toast.LENGTH_SHORT).show();

        }


        /*public boolean isOnline() {
            ConnectivityManager connectivity = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
            if(connectivity !=null){
                NetworkInfo[] info = connectivity.getAllNetworkInfo();
                if (info != null) {
                    for (int i = 0; i < info.length; i++) {
                        if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }*/



        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_leader_board, menu);
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
