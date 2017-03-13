package com.example.antoine.leagueanalysis;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by antoine on 3/13/17.
 */

public class AsyncLoadSummonerTier extends AsyncTask<String, Integer, String>
{
    //Var MainActivity Declaration
    MainActivity mainActivity;

    //Var String Declaration
    private String TAGAS = "AsyncLoadST";

    //Var URL Declaration
    private String http = "https://";
    private String URL = ".api.pvp.net/api/lol/euw/v1.4/summoner/by-name/";
    private String apiKey = "?api_key=RGAPI-11966b21-f3b8-47a7-8f83-465a404365e7";

    //Var Summoner Declaration
    private String name;
    private String id;
    private String level;
    private String region;

    public AsyncLoadSummonerTier(MainActivity ma)
    {
        mainActivity = ma;
    }

    @Override
    protected String doInBackground(String... params)
    {
        Log.d(TAGAS, "doInBackground: " + params[0]);

        region = params[0];
        name = params[1];
        id = params[2];
        level = params[3];

        Uri dataUri = Uri.parse(http + region + URL);
        String urlToUse = dataUri.toString() + name + apiKey;
        Log.d(TAGAS, "doInBackground: " + urlToUse);

        StringBuilder sb = new StringBuilder();
        try
        {
            URL url = new URL(urlToUse);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader((new InputStreamReader(is)));

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
                Log.d(TAGAS, "doInBackground: " + sb.toString());
            }
        }
        catch (Exception e)
        {
            Log.e(TAGAS, "doInBackground: ", e);
            return null;
        }

        Log.d(TAGAS, "NE doInBackground: " + sb.toString());

        return sb.toString();
    }

}
