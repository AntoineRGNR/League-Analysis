package com.example.antoine.leagueanalysis;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by antoine on 3/13/17.
 */

public class SummonerAdapter extends RecyclerView.Adapter<SummonerListHolder>
{
    //Var TAG Declaration for log
    private static final String TAG = "SummonerAdapter";

    //Var List Declaration
    private List<Summoner> summonerList;

    //Var MainActivity Declaration for onClickListener and onLongClickListener
    private MainActivity mainAct;

    public SummonerAdapter(List<Summoner> empList, MainActivity ma)
    {
        //Initializing stockList as an empty list
        this.summonerList = empList;
        //Initializing MainActivity Var
        mainAct = ma;
    }

    @Override
    public SummonerListHolder onCreateViewHolder(final ViewGroup parent, int viewType)
    {
        Log.d(TAG, "onCreateViewHolder: MAKING NEW");
        //Initializing the view holder to hold stock_list_row
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.summoner_list_row, parent, false);
        //itemView.setOnClickListener(mainAct);
        //itemView.setOnLongClickListener(mainAct);
        return new SummonerListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SummonerListHolder holder, int position)
    {
        Log.d("Stock Adapter", "onBindViewHolder: BINDING");
        Summoner summoner = summonerList.get(position);
        holder.summonerName.setText(summoner.getSummonerName());
        Log.d("Stock Adapter", "onBindViewHolder: " + summoner.getSummonerName());
        holder.summonerLevel.setText("level: " + summoner.getSummonerLevel());
        Log.d("Stock Adapter", "onBindViewHolder: " + summoner.getSummonerLevel());
        holder.summonerRegion.setText(summoner.getSummonerRegion());
        Log.d("Stock Adapter", "onBindViewHolder: " + summoner.getSummonerRegion());
        holder.summonerLeague.setText(summoner.getSummonerLeague());
        Log.d("Stock Adapter", "onBindViewHolder: " + summoner.getSummonerLeague());
        holder.summonerTier.setText(summoner.getSummonerTier());
        Log.d("Stock Adapter", "onBindViewHolder: " + summoner.getSummonerTier());
    }

    @Override
    public int getItemCount()
    {
        return summonerList.size();
    }

}