package com.example.omer4.contextmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.omer4.contextmenu.R;

public class CalculatingActivity extends Activity implements AdapterView.OnItemClickListener {
    TextView tv1;
    ListView lv;
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
        Intent gi=getIntent();
        Fnum=gi.getDoubleExtra("key",0);
        Formu=gi.getDoubleExtra("keykey",0);
        bo=gi.getBooleanExtra("keykey dylm",false);
        registerForContextMenu(lv);
        lv.setOnItemClickListener(this);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        Series[0]=Fnum;
        if(bo==false){
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
        lv.setOnItemClickListener((AdapterView.OnItemClickListener) this);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.calculatingactivity,menu);
        menu.setHeaderTitle("What are you looking for?");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {

            case R.id.location:
                int x = info.position + 1;
                tv1.setText("location: "+x);
                return true;

            case R.id.sum:
                int y=info.position+1;
                if (bo==false){
                    SN=y*(2*Fnum+Formu*(y-1))/2;
                }
                else{
                    SN=Fnum*(Math.pow(Formu,(y))-1)/(Formu-1);
                }
                tv1.setText("Sn: "+SN);
        }



        return super.onContextItemSelected(item);
    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        super.openContextMenu(view);
    }




}

