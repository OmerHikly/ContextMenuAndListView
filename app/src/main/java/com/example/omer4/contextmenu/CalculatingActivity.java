package com.example.omer4.contextmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;


public class CalculatingActivity extends Activity implements   AdapterView.OnItemClickListener {
    TextView tv1;
    TextView tv2;
    ListView lv;
    Space spacegone;

    Boolean bo;
    double Fnum;
    double Formu;
    double SN;
    double []Series=new double[20];
    String[] st=new String[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculating);
        tv1=(TextView) findViewById(R.id.tv1);
        lv=(ListView) findViewById(R.id.lv);
        tv2=(TextView)findViewById(R.id.tv2);
        spacegone=(Space)findViewById(R.id.spacegone);

        Intent gi=getIntent();
        Fnum=gi.getDoubleExtra("key",0);
        Formu=gi.getDoubleExtra("keykey",0);
        bo=gi.getBooleanExtra("keykey dylm",false);

        registerForContextMenu(lv);

        lv.setOnItemClickListener(this);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        Series[0]=Fnum;
        if(!bo){
            for (int i=1;i<20;i++){
                Series[i]=Series[i-1]+Formu;
            }
        }
        else{
            for (int i=1;i<20;i++) {
                Series[i] = Series[i - 1] * Formu;

            }
        }
        for(int j=0;j<20;j++) {
            st[j] = String.valueOf(Series[j]);
        }

        ArrayAdapter<String> adp;
        adp = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,st);
        lv.setAdapter(adp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("credits");
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("What are you looking for");
        menu.add("location");
        menu.add("sum");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        tv2.setVisibility(View.GONE);
        spacegone.setVisibility(View.GONE);
        String st2 = (String) item.getTitle();
        switch (st2) {

            case "location":
                int x = info.position + 1;
                tv1.setText("location: " + x);
                return true;

            case "sum":
                double y = info.position + 1;
                if (!bo) {
                    SN = y * (2 * Fnum + Formu * (y - 1)) / 2;
                } else {
                    SN = Fnum * (Math.pow(Formu, (y)) - 1) / (Formu - 1);
                }
                tv1.setText("Sn: " + SN);
        }
                return super.onContextItemSelected(item);

    }

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        tv1.setText("First number is "+Fnum);
        spacegone.setVisibility(View.VISIBLE);
        tv2.setVisibility(View.VISIBLE);
        if(!bo) {
            tv2.setText("The differnece is " + Formu);
        }
            else{
                tv2.setText("The mutlitplier is"+Formu);

            }
        }
//
    }


