package com.example.admin.kingofmath2.Adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.kingofmath2.Entities.Leader;
import com.example.admin.kingofmath2.R;
import com.example.admin.kingofmath2.Utils.CircleTransform;
import com.example.admin.kingofmath2.Utils.RoundImage;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ADMIN on 02/01/2016.
 */
public class LeaderBoardAdapter extends ArrayAdapter<Leader> {
    private final static String TAG = "ledarBoard";
    private int resourceId = 0;
    public Context mContext;
    private LayoutInflater inflater;
    public static String PREFRENCE_FILNAME = "com.example.admin.kingofmath";
    SharedPreferences share;
    String ID;
    RoundImage roundedImage1;
    ImageView amine;

    public LeaderBoardAdapter(Context context, int resourceId, List<Leader> mediaItems) {
        super(context, 0, mediaItems);
        this.resourceId = resourceId;
        this.mContext = context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        share = context.getSharedPreferences(PREFRENCE_FILNAME, Context.MODE_PRIVATE);

         ID= share.getString("ObjectId", "");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        TextView score, name,rang;
        LinearLayout linearLayout;
        ProgressDialog progressDialog = null;
        ImageView image;
        RoundImage roundedImage;

        view = inflater.inflate(resourceId, parent, false);

        try {
            score = (TextView)view.findViewById(R.id.scoreL);
            name = (TextView)view.findViewById(R.id.nameL);
            rang = (TextView)view.findViewById(R.id.rangL);
            linearLayout =(LinearLayout)view.findViewById(R.id.linearLeader);
             image = (ImageView) view.findViewById(R.id.leaderImage);


        } catch( ClassCastException e ) {
            Log.e(TAG, "Your layout must provide an image and a text view.", e);
            throw e;
        }



        if(getItem(position).getId().equals(ID)){
         linearLayout.setBackgroundResource(R.drawable.backgroudleader);
        }

        if(getItem(position).getIdposition().equals("3")){
            image.getLayoutParams().height = 120;
            image.getLayoutParams().width = 0;
            //image.setBackgroundResource(R.drawable.player3);
            amine=(ImageView)view.findViewById(R.id.leaderImage);
            Bitmap bm = BitmapFactory.decodeResource(this.getContext().getResources(), R.drawable.player3);
            roundedImage1 = new RoundImage(bm);
            amine.setImageDrawable(roundedImage1);


        }
        else if(getItem(position).getIdposition().equals("2")){
            image.getLayoutParams().height = 120;
            image.getLayoutParams().width = 0;
            //image.setBackgroundResource(R.drawable.player2);
            amine=(ImageView)view.findViewById(R.id.leaderImage);
            Bitmap bm = BitmapFactory.decodeResource(this.getContext().getResources(), R.drawable.player2);
            roundedImage1 = new RoundImage(bm);
            amine.setImageDrawable(roundedImage1);
        }
        else if(getItem(position).getIdposition().equals("1")){
            image.getLayoutParams().height = 120;
            image.getLayoutParams().width = 0;
            //image.setBackgroundResource(R.drawable.player1);
            amine=(ImageView)view.findViewById(R.id.leaderImage);
            Bitmap bm = BitmapFactory.decodeResource(this.getContext().getResources(), R.drawable.player1);
            roundedImage1 = new RoundImage(bm);
            amine.setImageDrawable(roundedImage1);
        }
        else if(getItem(position).getIdposition().equals("4")){
            image.getLayoutParams().height = 120;
            image.getLayoutParams().width = 0;
            //image.setBackgroundResource(R.drawable.player4);
            amine=(ImageView)view.findViewById(R.id.leaderImage);
            Bitmap bm = BitmapFactory.decodeResource(this.getContext().getResources(), R.drawable.player4);
            roundedImage1 = new RoundImage(bm);
            amine.setImageDrawable(roundedImage1);
        }
       else if((!(getItem(position).getIdfacebook().equals("0")))){
            image.getLayoutParams().height = 120;
            image.getLayoutParams().width = 0;
            String profileImg = "https://graph.facebook.com/" +getItem(position).getIdfacebook() + "/picture?type=large&width=224";
            Picasso.with(getContext()).load(profileImg).transform(new CircleTransform()).into(image);
        }else{
            image.getLayoutParams().height = 120;
            image.getLayoutParams().width = 0;
            //image.setBackgroundResource(R.drawable.player0);
            amine=(ImageView)view.findViewById(R.id.leaderImage);
            Bitmap bm = BitmapFactory.decodeResource(this.getContext().getResources(), R.drawable.player0);
            roundedImage1 = new RoundImage(bm);
            amine.setImageDrawable(roundedImage1);
        }


        score.setText(getItem(position).getScore());
        name.setText(getItem(position).getName());
        rang.setText(""+getItem(position).getRang());




        return view;
    }

}
