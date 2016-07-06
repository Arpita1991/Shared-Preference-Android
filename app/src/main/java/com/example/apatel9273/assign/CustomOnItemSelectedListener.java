package com.example.apatel9273.assign;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

/**
 * Created by Arpita on 2016-06-08.
 */
public class CustomOnItemSelectedListener implements android.widget.AdapterView.OnItemSelectedListener {

    // work on the selection of Item in dropdown
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

//        Toast.makeText(parent.getContext(),"OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
