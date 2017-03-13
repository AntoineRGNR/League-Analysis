package com.example.antoine.leagueanalysis;

import android.view.View;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

/**
 * Created by antoine on 3/13/17.
 */

public class SummonerListHolder extends RecyclerView.ViewHolder
{

    //Var TextView Declaration
    public TextView summonerName;
    public TextView summonerLevel;
    public TextView summonerRegion;
    public TextView summonerLeague;
    public TextView summonerTier;

    public SummonerListHolder(View view)
    {
        super(view);
        //Var TextView Initialization
        summonerName = (TextView)view.findViewById(R.id.SummonerName);
        summonerLevel = (TextView)view.findViewById(R.id.SummonerLevel);
        summonerRegion = (TextView)view.findViewById(R.id.SummonerRegion);
        summonerLeague = (TextView)view.findViewById(R.id.SummonerLeague);
        summonerTier = (TextView)view.findViewById(R.id.SummonerTier);
    }

}
