package drawable.Desktop.Esas.app.src.main.java.com.MiniProjet.Activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.MiniProjet.ActionBarDrawerToggle;
import com.MiniProjet.Divers.CustomListAdapter;
import com.MiniProjet.Divers.FeedHandleXML;
import com.MiniProjet.Divers.Feedex;
import com.MiniProjet.Divers.Utils;
import com.MiniProjet.DrawerArrowDrawable;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.realm.internal.Util;


public class SampleActivity extends Activity {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerArrowDrawable drawerArrow;
    private boolean drawerArrowColor;
    List<String> titles = new ArrayList<>();
    List<String> pubdate = new ArrayList<>();
    List<String> images = new ArrayList<>();
    HashMap<String, String> map;
    ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        ActionBar ab = getActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.navdrawer);


        drawerArrow = new DrawerArrowDrawable(this) {
            @Override
            public boolean isLayoutRtl() {
                return false;
            }
        };
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                drawerArrow, R.string.drawer_open,
                R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();


        String[] values = new String[]{

                "New file",
                "Create new file",
                "Load old file",
                "Template",

        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch (position) {
                    case 0:
                        Intent i = new Intent(SampleActivity.this, SampleActivity.class);

                        startActivity(i);
                        break;
                    case 1:
                        Intent newi = new Intent(SampleActivity.this, Editeurtext.class);

                        startActivity(newi);
                        break;
                    case 2:
                        //load old file
                        break;
                    case 3:
                        //Tempate
                        break;
                    case 4:
                        //Quizz
                        break;
                    case 5:
                       /*Other app*/
                        break;
                    case 6:
                       /* String appUrl = "https://play.google.com/store/apps/details?id=" + getPackageName();
                        Intent rateIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(appUrl));
                        startActivity(rateIntent);*/
                        break;
                }

            }
        });


        map = new HashMap<String, String>();
        System.out.println(FeedHandleXML.listfeed.size());


        for (Feedex e : FeedHandleXML.listfeed) {
            titles.add(e.getTitle().toString());
            pubdate.add(e.getPubDate().toString());
            images.add(e.getImage());




          /*  map = new HashMap<String, String>();
            map.put("title", e.getTitle().toString());

            map.put("datePub", e.getPubDate().toString());

            map.put("image",Utils.LoadImageFromWebOperations("https://tctechcrunch2011.files.wordpress.com/2014/07/microsoft-cortana-build-2014-000.jpg"));


            data.add(map);*/


        }









/*
        SimpleAdapter adapterfeed = new SimpleAdapter(this,
                data,
                R.layout.onefeed,
                new String[]{"title","datePub","image"},
                new int[]{R.id.titlefeed,R.id.pubDate,R.id.imageback});
                    listView.setAdapter(adapterfeed);
                    */
        ListView listView = (ListView) findViewById(R.id.lista);
        CustomListAdapter adapterfeed = new CustomListAdapter(this, titles, pubdate, images);

        listView.setAdapter(adapterfeed);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(FeedHandleXML.listfeed.get(arg2).getLink()));
                startActivity(browserIntent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
                mDrawerLayout.closeDrawer(mDrawerList);
            } else {
                mDrawerLayout.openDrawer(mDrawerList);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
        
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}
