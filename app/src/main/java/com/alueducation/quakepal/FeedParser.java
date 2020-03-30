
/*
 * Copyright (c) 2020.
 * Name: Emmanuel Sackey
 * Matric: S1719015
 * Programme: Bsc(Hons) Computing
 *
 */

package com.alueducation.quakepal;

import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeedParser extends AsyncTask<URL, Void, Map<String, List<Earthquake>>> {
    @Override
    protected Map<String, List<Earthquake>> doInBackground(URL... urls) {
        int argLength = urls.length;
        Map<String, List<Earthquake>> parsedData = new HashMap<>();
        Earthquake earthquake = null;
        List<Earthquake> earthquakes;
        String key = "";

        if (argLength > 0){
            final String TAG = "RSSFEED";
            try {
                HttpURLConnection urlConnection = (HttpURLConnection)urls[0].openConnection();
                XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
                xmlPullParserFactory.setNamespaceAware(false);
                XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
                xmlPullParser.setInput(urlConnection.getInputStream(), "UTF-8");
                int eventType  = xmlPullParser.getEventType();
//                Using the boolean to ensure that I am getting item from the main body.
                boolean mainBody = false;
                while (eventType != XmlPullParser.END_DOCUMENT){
                    if(eventType == XmlPullParser.START_TAG){
                        if (xmlPullParser.getName().equalsIgnoreCase("item")){
                            mainBody = true;
                            earthquake = new Earthquake();
                        }

                        else if (xmlPullParser.getName().equalsIgnoreCase("title")){
                            if (mainBody){
                                earthquake.setTitle(xmlPullParser.nextText());
                            }
                        }

                        else if (xmlPullParser.getName().equalsIgnoreCase("description")){
                            if (mainBody){
                                earthquake.setDescription(xmlPullParser.nextText());
                            }
                        }

                        else if (xmlPullParser.getName().equalsIgnoreCase("Category")){
                            if (mainBody){
                                earthquake.setCategory(xmlPullParser.nextText());
                            }
                        }

                        else if (xmlPullParser.getName().equalsIgnoreCase("link")){
                            if (mainBody){
                                earthquake.setLink(xmlPullParser.nextText());
                            }
                        }

                        else if (xmlPullParser.getName().equalsIgnoreCase("pubDate")){
                            if (mainBody){
                                key = xmlPullParser.nextText();
                                key = makeKey(key);
                            }
                        }
                    }

                    else if (eventType == XmlPullParser.END_TAG && xmlPullParser.getName().equalsIgnoreCase("item")){
                        mainBody = false;
                        if (parsedData.containsKey(key)){
                            Earthquake newEarthquake = splitDescription(earthquake);
                            parsedData.get(key).add(newEarthquake);
                        }
                        else {
                            earthquakes = new ArrayList<>();
                            Earthquake newEarthquake = splitDescription(earthquake);
                            earthquakes.add(newEarthquake);
                            parsedData.put(key, earthquakes);
                        }
                    }
                    eventType = xmlPullParser.next();
                }
                urlConnection.disconnect();
            } catch (IOException e) {
                Log.e(TAG, e.toString());
            } catch (XmlPullParserException e) {
                Log.d(TAG, e.toString());
            }
        }
        return parsedData;
    }

    @Override
    protected void onPostExecute(Map map) {
        super.onPostExecute(map);
        SharedViewModel.earthquakes = map;
        System.out.println(map);
    }

    private Earthquake splitDescription(@NonNull Earthquake earthquake){
        String description = earthquake.getDescription();
        String[] descArray = description.split(";");
        // The datetime string returned has Origin date/time: attached to the values so we split again to get
        // only the Origin date/time.
        // Local variables are used here to make the code simple and easy to read.
        String dateTime = descArray[0].split(":")[1];
        earthquake.setDateTime(dateTime);
        String location = descArray[1].split(":")[1];
        earthquake.setLocation(location);
        String coordinates = descArray[2].split(":")[1];
        earthquake.setGeoCoordinates(coordinates);
        String depth = descArray[3].split(":")[1];
        earthquake.setDepth(depth);
        float magnitude = Float.parseFloat(descArray[4].split(":")[1]);
        earthquake.setMagnitude(magnitude);

        return earthquake;
    }

    private String makeKey(String key){
//        The string starts from 5 and ends on 15
       return key.substring(5, 16);
    }
}
