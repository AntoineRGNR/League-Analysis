package com.example.antoine.leagueanalysis;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by antoine on 3/13/17.
 */

public class AsyncLoadSummonerInformations extends AsyncTask<String, Integer, String>
{
    //Var MainActivity Declaration
    MainActivity mainActivity;

    //Var String Declaration
    private String TAGAS = "AsyncLoadSI";

    //Var Integer Declaration
    private int count;

    //Var URL Declaration
    private String http = "https://";
    private String URL = ".api.pvp.net/api/lol/euw/v2.5/league/by-summoner/";
    private String apiKey = "?api_key=RGAPI-11966b21-f3b8-47a7-8f83-465a404365e7";

    //Var Summoner Declaration
    private String name;
    private String id;
    private String level;
    private String region;

    public AsyncLoadSummonerInformations(MainActivity ma)
    {
        mainActivity = ma;
    }

    @Override
    protected void onPreExecute()
    {
        // I have nothing useful to do here so leaving it empty
        Log.d(TAGAS, "onPreExecute");
    }

    @Override
    protected String doInBackground(String... params)
    {
        Log.d(TAGAS, "doInBackground: " + params[0] + " " + params[1]);

        region = params[0];

        Uri dataUri = Uri.parse(http + params[0] + URL);
        String urlToUse = dataUri.toString() + params[1] + apiKey;
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

    @Override
    protected void onPostExecute(String string)
    {
        Log.d(TAGAS, "onPostExecute: ");
        parseJSON(string);
        new AsyncLoadSummonerTier(mainActivity).execute(region, name, id, level);
    }

    private void parseJSON(String s)
    {
        Log.d(TAGAS, "parseJSON: Start: " + s);
        try
        {
            JSONArray jObjMain = new JSONArray(s);
            count = jObjMain.length();

            if(count != 0){
                for (int i = 0; i < jObjMain.length(); i++)
                {
                    JSONObject jStock = (JSONObject) jObjMain.get(i);
                    id = jStock.getString("id");
                    Log.d(TAGAS, "parseJSON: " + id);
                    name = jStock.getString("name");
                    Log.d(TAGAS, "parseJSON: " + name);
                    level = jStock.getString("summonerLevel");
                    Log.d(TAGAS, "parseJSON: " + level);
                }
                Log.d(TAGAS, "parseJSON: Return");
            }
            else
            {
                //errorStockDownload();
            }

        } catch (Exception e) {
            Log.d(TAGAS, "parseJSON: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
