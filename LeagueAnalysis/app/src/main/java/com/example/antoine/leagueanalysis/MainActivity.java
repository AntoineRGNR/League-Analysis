package com.example.antoine.leagueanalysis;

import android.content.DialogInterface;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

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

    private String items[] =    {"Brazil",
                                    "Europe Nordic & East",
                                    "Europe West",
                                    "Latin America North",
                                    "Latin America South",
                                    "North America",
                                    "Oceania",
                                    "Russia",
                                    "Turkey",
                                    "South East Asia",
                                    "Republic of Korea"
                                };

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //Adding Menu to MainActivity
        Log.d(this.getString(R.string.TAGMA), "onCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Log.d(this.getString(R.string.TAGMA), "onOptionsItemSelected");
        //Doing Action if Menu Icon is selected
        switch (item.getItemId()) {
            case R.id.menuAdd:
                regionChoose();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

    public void regionChoose()
    {
        Log.d(this.getString(R.string.TAGMA), "regionChoose: Start");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose a Region");
        builder.setItems(items, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                if (items[which].toString().equals("Brazil"))
                {
                    searchDialog("BR");
                }
                else if(items[which].toString().equals("Europe Nordic & East"))
                {
                    searchDialog("EUNE");
                }
                else if(items[which].toString().equals("Europe West"))
                {
                    searchDialog("EUW");
                }
                else if(items[which].toString().equals("Latin America North"))
                {
                    searchDialog("LAN");
                }
                else if(items[which].toString().equals("Latin America South"))
                {
                    searchDialog("LAS");
                }
                else if(items[which].toString().equals("North America"))
                {
                    searchDialog("NA");
                }
                else if(items[which].toString().equals("Oceania"))
                {
                    searchDialog("OCE");
                }
                else if(items[which].toString().equals("Russia"))
                {
                    searchDialog("RU");
                }
                else if(items[which].toString().equals("Turkey"))
                {
                    searchDialog("TR");
                }
                else if(items[which].toString().equals("South East Asia"))
                {
                    searchDialog("JP");
                }
                else if(items[which].toString().equals("Republic of Korea"))
                {
                    searchDialog("KR");
                }
            }
        });
        builder.setNegativeButton("Nevermind", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void searchDialog(String rgn)
    {
        final String region = rgn;
        Log.d(this.getString(R.string.TAGMA), "searchDialog: region: " + region);
        //AlertDialog Declaration and Initialization
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //EditText Declaration and Initialization
        final EditText et = new EditText(this);
        et.setGravity(Gravity.CENTER_HORIZONTAL);

        builder.setView(et);

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                startAsyncLoad(region, et.getText().toString());
                Log.d("MainActivity", "searchDialog: summoner: " + et.getText().toString());
            }
        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {

            }
        });

        builder.setMessage("Please enter a Stock Symbol:");
        builder.setTitle("Stock Selection");

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void startAsyncLoad(String region, String summoner)
    {
        new AsyncLoadSummonerInformations(this).execute(region, summoner);
    }
}
