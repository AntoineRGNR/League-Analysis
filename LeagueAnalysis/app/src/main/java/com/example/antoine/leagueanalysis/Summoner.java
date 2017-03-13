package com.example.antoine.leagueanalysis;

/**
 * Created by antoine on 3/13/17.
 */

public class Summoner {
    //Summoner Var Declaration
    private String summonerName;
    private String summonerLevel;
    private String summonerRegion;
    private String summonerLeague;
    private String summonerTier;

    public void Summoner(String summonerName, String summonerLevel, String summonerRegion,String summonerLeague, String summonerTier)
    {
        this.summonerName = summonerName;
        this.summonerLevel = summonerLevel;
        this.summonerRegion = summonerRegion;
        this.summonerLeague = summonerLeague;
        this.summonerTier = summonerTier;
    }

    public String getSummonerName()
    {
        return summonerName;
    }

    public void setSummonerName(String summonerName)
    {
        this.summonerName = summonerName;
    }

    public String getSummonerLevel()
    {
        return summonerLevel;
    }

    public void setSummonerLevel(String summonerLevel)
    {
        this.summonerLevel = summonerLevel;
    }

    public String getSummonerRegion()
    {
        return summonerRegion;
    }

    public void setSummonerRegion(String summonerRegion)
    {
        this.summonerRegion = summonerRegion;
    }

    public String getSummonerLeague()
    {
        return summonerLeague;
    }

    public void setSummonerLeague(String summonerLeague)
    {
        this.summonerLeague = summonerLeague;
    }

    public String getSummonerTier()
    {
        return summonerTier;
    }

    public void setSummonerTier(String summonerTier)
    {
        this.summonerTier = summonerTier;
    }

    @Override
    public String toString()
    {
        return summonerName + summonerLevel + summonerRegion + summonerLeague + summonerTier;
    }
}