package drawable.Desktop.Esas.app.src.main.java.com.MiniProjet.Divers;

/**
 * Created by XssX on 05/11/2015.
 */
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.MiniProjet.Activity.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final List<String> itemname;
    private final List<String> pubdate;
    private final List<String> imgid;

    public CustomListAdapter(Activity context, List<String> title,  List<String> pubdate, List<String> imgid) {
        super(context, R.layout.onefeed, title);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=title;
        this.pubdate=pubdate;
        this.imgid=imgid;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.onefeed, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.titlefeed);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageback);
        TextView extratxt = (TextView) rowView.findViewById(R.id.pubDate);

        txtTitle.setText(itemname.get(position));

        Picasso.with(context)
                .load(imgid.get(position))

                .into(imageView);
        LinearLayout ll = (LinearLayout) rowView.findViewById(R.id.lin);
        ll.setBackgroundDrawable(Utils.LoadImageFromWebOperations(imgid.get(position)));
        extratxt.setText(pubdate.get(position));
        return rowView;

    };
}