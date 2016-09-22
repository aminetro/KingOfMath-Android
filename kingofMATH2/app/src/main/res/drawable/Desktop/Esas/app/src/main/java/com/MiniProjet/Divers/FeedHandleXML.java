package drawable.Desktop.Esas.app.src.main.java.com.MiniProjet.Divers;

/**
 * Created by XssX on 01/11/2015.
 */
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class FeedHandleXML {
    private String title = "title";
    private String description = "description";
    private String pubDate = "pubDate";
    private String link = "link";
    private String urlString = null;
    private XmlPullParserFactory xmlFactoryObject;
    public volatile boolean parsingComplete = true;

    public FeedHandleXML(String url){
        this.urlString = url;
    }

    public static List<Feedex>  listfeed = new ArrayList();;

    public  List<Feedex> getListfeed(){
        return listfeed;
    }

    public void parseXMLAndStoreIt(XmlPullParser parser)throws XmlPullParserException,IOException {
        int event;
        String text=null;
        int k=0;


        int eventType = parser.getEventType();
        Feedex currentProduct = null;


        while (eventType != XmlPullParser.END_DOCUMENT ){
            String name = null;


            switch (eventType){
                case XmlPullParser.START_DOCUMENT:


                    break;
                case XmlPullParser.START_TAG:
                    name = parser.getName();


                    if (name.equals("item")){


                        currentProduct = new Feedex();
                    } else if (currentProduct != null){
                        if (name.equals("title")){
                            currentProduct.setTitle(readTitle(parser));
                        } else if (name.equals("description")){
                            currentProduct.setImage(readImage(parser));

                        }else if (name.equals("pubDate")){
                            currentProduct.setPubDate(readpubDate(parser));
                        }
                        else if (name.equals("link")){
                            currentProduct.setLink(readLink(parser));
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:
                    name = parser.getName();
                    if (name.equalsIgnoreCase("item") && currentProduct != null){

                        listfeed.add(currentProduct);



                    }
            }
            eventType = parser.next();
        }
        parsingComplete = false;










    }



    private String readTitle(XmlPullParser parser) throws IOException,
            XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, null, "title");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, null, "title");
        return title;
    }

    private String readLink(XmlPullParser parser) throws IOException,
            XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, null, "link");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, null, "link");
        return title;
    }

    private String readImage(XmlPullParser parser) throws IOException,
            XmlPullParserException {

        parser.require(XmlPullParser.START_TAG, null, "description");
        String title = readText(parser);
        title = title.substring(title.indexOf("http"));
        title= title.substring(0,title.indexOf("\""));
        Log.i("\\\\\\\\\\\\\\", title);
        parser.require(XmlPullParser.END_TAG, null, "description");
        return title;
    }

    private String readpubDate(XmlPullParser parser) throws IOException,
            XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, null, "pubDate");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, null, "pubDate");
        return title;
    }

    private String readText(XmlPullParser parser) throws IOException,
            XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    public void fetchXML(){
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();

                    conn.setReadTimeout(10000 /* milliseconds */);
                    conn.setConnectTimeout(15000 /* milliseconds */);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream stream = conn.getInputStream();
                    xmlFactoryObject = XmlPullParserFactory.newInstance();
                    XmlPullParser myparser = xmlFactoryObject.newPullParser();

                    myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                    myparser.setInput(stream, null);

                    parseXMLAndStoreIt(myparser);
                    stream.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}