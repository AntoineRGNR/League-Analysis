package com.example.antoine.leagueanalysis;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{

    //Var ArrayList Declaration and Initialization
    private ArrayList<Summoner> summonerList = new ArrayList<>();

    //Var RecyclerVIew Declaration
    private RecyclerView recyclerView;

    //Var SwipeRefreshLayout Declaration
    private SwipeRefreshLayout swiper;

    //Var StockAdapter Declaration
    private SummonerAdapter summonerAdapter;

    private String name = "CLG Bigfatlk";
    private String lvl = "30";
    private String region = "EUW";
    private String league = "LA MERE A RAYAN";
    private String tier = "Challenger";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        summonerAdapter = new SummonerAdapter(summonerList, this);

        recyclerView.setAdapter(summonerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Make some data - not always needed - used to fill list
        for (int i = 0; i < 20; i++) {
            summonerList.add(new Summoner(name, lvl, region, league, tier));
        }
    }
}
