package com.example.antoine.leagueanalysis;

import android.content.DialogInterface;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener
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
        Log.d(this.getString(R.string.TAGMA), "onCreate: Start");
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
        Log.d(this.getString(R.string.TAGMA), "onCreate: End");
    }

    private void doRefresh()
    {
        Log.d(this.getString(R.string.TAGMA), "doRefresh: Start");
        swiper.setRefreshing(false);
        Log.d(this.getString(R.string.TAGMA), "doRefresh: End");
    }

    @Override
    public boolean onLongClick(View v)
    {
        Log.d(this.getString(R.string.TAGMA), "onLongClick");
        //Getting the Stock Position
        int pos = recyclerView.getChildLayoutPosition(v);
        //Returning the position of the Stock to backDialog method
        deleteDialog(summonerList.get(pos));
        return true;
    }

    public void deleteDialog(final Summoner summoner)
    {
        Log.d(this.getString(R.string.TAGMA), "deleteDialog");
        //AlertDialog Declaration And Initialization
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Delete Summoner " + summoner.getSummonerName() + "?");
        builder.setTitle(R.string.delete_summoner);
        //builder.setIcon(R.drawable.ic_delete_black);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                summonerList.remove(summoner);
                summonerAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                //Nothing to do if User press NO
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
