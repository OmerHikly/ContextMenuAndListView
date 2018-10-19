package com.example.omer4.contextmenu;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.omer4.contextmenu.R;

import static android.app.ProgressDialog.show;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    ToggleButton tb;
    EditText et1;
    EditText et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tb = (ToggleButton) findViewById(R.id.toggleb);
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        tb.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) this);
    }
    boolean bo=false;
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked) {
            et2.setHint("Type here the multiplier of the series...");
            bo=true;
        } else {
            et2.setHint("Type here the difference of the series...");
            bo=false;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       menu.add("credits");
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this,"מאת עומר היקלי",Toast.LENGTH_LONG).show();
        return super.onOptionsItemSelected(item);
    }

    public void go(View view) {

        Context context = getApplicationContext();
        CharSequence text="One of the values is incorrect or missing...";
        int duration = Toast.LENGTH_SHORT;

        String FirstNum = et1.getText().toString();
        String Formula = et2.getText().toString();
        if(FirstNum.equals("")||(Formula.equals("")||FirstNum.equals(".")||FirstNum.equals("-")|| FirstNum.equals("-.")||Formula.equals(".")||Formula.equals("-")||Formula.equals("-."))){
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        else {
            double Fn= Double.parseDouble(FirstNum);
            double Fo= Double.parseDouble(Formula);
            Intent t = new Intent(this, CalculatingActivity.class);
            t.putExtra("key",Fn);
            t.putExtra("keykey",Fo);
            t.putExtra("keykey dylm",bo);
            startActivity(t);
        }


    }
}
