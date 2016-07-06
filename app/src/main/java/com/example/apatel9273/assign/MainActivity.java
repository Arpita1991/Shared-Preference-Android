package com.example.apatel9273.assign;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.widget.AdapterView;

public class MainActivity extends AppCompatActivity implements OnSeekBarChangeListener {

    int year_x,month_x,day_x; // declaring variable for calender

    static final int DILOD_ID = 0;

    public static final String PREFS_NAME = "MyPrefFile"; // declaring variable with file name for shared preference

    //initialization of default constants for shared preference
    public static final String RADIOSHIRTSIZE = "shirt";
    public static final String SHOESIZE = "shoesizw";
    public static final String SHIRTSIZE = "shirtsize";
    public static final String PANTSIZE = "pantsize";
    public static final String DAY = "day";
    public static final String MONTH = "month";
    public static final String YEAR = "year";
    public static final String USERNAME = "username";
    public static final String CHECKTHINK = "checkbox";
    public static final String EYECOLOR = "1";

    // initialization of variable for checkbox
    public boolean checked;
    public String value;
    //initialization for shared preference and editor
    SharedPreferences settingsfile;
    SharedPreferences.Editor editor;

    CheckBox steadygo; // initialization of checkbox
    EditText username; // initialization of edittext username
    EditText date; // initialization for date textbox
    SeekBar slider1; // initialization of slider pant size
    SeekBar slider2; // initialization of slider shirt size
    SeekBar slider3; // initialization of slider shoes size
    RadioGroup rg; // initialization of radiobutton group
    Spinner spinner; // initialization for dropdown
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settingsfile = getSharedPreferences(PREFS_NAME, 0);
        editor = settingsfile.edit();

        // calling the findViewById function that was defined in Activity will return instance of view for that particular textbox username
        username = (EditText)findViewById(R.id.uname);
    // storing retrived data for username in variable
        String uname = username.getText().toString();
        // set the value in textbox for  username
        username.setText(uname);

        // calling the findViewById function that was defined in Activity will return instance of view for checkbox
        steadygo = (CheckBox) findViewById(R.id.steadygo);
    // check the boolean value for checkbox
        if(checked)
        {
            // if  condition is true it will setchecked for checkbox
            steadygo.setChecked(checked);
        }
        // calling the findViewById function that was defined in Activity will return instance of view for spinner control
        spinner = (Spinner) findViewById(R.id.spinner);
        // calling the findViewById function that was defined in Activity will return instance of view for radiobutton group will return id
        rg = (RadioGroup) findViewById(R.id.radioGroup1);
        // calling the findViewById function that was defined in Activity will return instance of view for slider control
        slider1 = (SeekBar) findViewById(R.id.seekBar1);
        slider2 = (SeekBar) findViewById(R.id.seekBar2);
        slider3 = (SeekBar) findViewById(R.id.seekBar3);

        // calling the findViewById function that was defined in Activity will return instance of view for that particular date textbox
        date = (EditText)findViewById(R.id.date);

      // call spinner function to show the spinner
        addItemsOnSpinner();
        addListenerOnSpinnerItemSelection();

        // call onselectRadioButton function to show the radiogroup
        onselectRadioButton();

        // creating object for calender by getting instace
        final Calendar cal = Calendar.getInstance();
        // getting value of year, month and day  from calender and storing in variable
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);
        // call function to show the calender
        showDaialogOnButtonClick();
        // call setOnSeekBarChangeListener to show slider for pant size,shirt size,shoes size
        slider1.setOnSeekBarChangeListener(this);
        slider2.setOnSeekBarChangeListener(this);
        slider3.setOnSeekBarChangeListener(this);

        // getting the value from shred preference for checkbox and storing in checkbox
        checked = settingsfile.getBoolean(CHECKTHINK, false);
        // checking the boolean value for checkbox
        if(checked) {
            // if condition become true it will show all the value for all the fields
            // setting checbox true by using object.
            steadygo.setChecked(true);
            //retruving username from shred pref and setting textvalue  for username
        username.setText(settingsfile.getString(USERNAME," "));
            // getting value for pant size and set the value by setprogress
        slider1.setProgress(settingsfile.getInt(PANTSIZE, 0 ));
            // getting value for shirt size and set the value by setprogress
            slider2.setProgress(settingsfile.getInt(SHIRTSIZE, 0 ));
            // getting value for shoes size and set the value by setprogress
            slider3.setProgress(settingsfile.getInt(SHOESIZE, 0 ));
       // storing year,month,day value for calender in variables
        year_x = settingsfile.getInt(YEAR,2016);
        month_x = settingsfile.getInt(MONTH,0);
        day_x = settingsfile.getInt(DAY,15);
            // getting spinner value and setting selected value in dropdown
        spinner.setSelection(settingsfile.getInt(EYECOLOR,0));
            // getting shirtsize value and set selection for radiobutton
        rg.check(settingsfile.getInt(RADIOSHIRTSIZE,R.id.M));
// settext function to set the calender value in textbox
        assert date != null;
        date.setText(month_x + "-" + day_x + "-" + year_x);
        }
        //   Toast.makeText(getBaseContext(), eyecolor, Toast.LENGTH_SHORT).show();

        // button onclick event
        Button SaveData = (Button)findViewById(R.id.SaveData);
        assert SaveData != null;
        // call teh ssavedata function on onclick of save datas function
        SaveData.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v)
                    {
                        // getting value of username edittext value and storing in uname variable
                        String uname = username.getText().toString();
                        username.setText(uname);
                        // putstring function to save value of uname  in username constant in shared pref using editor object
                        editor.putString(USERNAME, uname);
                        // putstring function to save value of checkbox  in CHECKTHINK constant in shared pref using editor object
                        editor.putBoolean(CHECKTHINK,steadygo.isChecked());
                        // putstring function to save value of selected dropdown  in EYECOLOR constant in shared pref using editor object
                        editor.putInt(EYECOLOR,spinner.getSelectedItemPosition());
                        // putstring function to save value of year in YEAR constant in shared pref using editor object
                        editor.putInt(YEAR, year_x);
                        // putstring function to save value of month in MONTH constant in shared pref using editor object
                        editor.putInt(MONTH, month_x);
                        // putstring function to save value of day_x in DAY constant in shared pref using editor object
                        editor.putInt(DAY, day_x);
                        // putstring function to save value of selected radiobutton in RADIOSHIRTSIZE constant in shared pref using editor object
                        editor.putInt(RADIOSHIRTSIZE, rg.getCheckedRadioButtonId());
                        // putstring function to save value of slider1.getProgress() in PANTSIZE constant in shared pref using editor object
                        editor.putInt(PANTSIZE, slider1.getProgress());
                        // putstring function to save value of slider2.getProgress() in PANTSIZE constant in shared pref using editor object
                        editor.putInt(SHIRTSIZE, slider2.getProgress());
                        // putstring function to save value of progressbar in shoessize constant in shared pref using editor object
                        editor.putInt(SHOESIZE, slider3.getProgress());
                        // commit function is used to save the data in share prefe editor
                        editor.commit();
                        //Toast function to shaow the message in pop up
                        Toast.makeText(MainActivity.this,"Your Data is saved!!",Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }


    public void onselectRadioButton()
    {
        //http://stackoverflow.com/questions/18179124/android-getting-value-from-selected-radiobutton

        // calling the findViewById function that was defined in Activity will return instance of view for that radiogroup id
        rg = (RadioGroup) findViewById(R.id.radioGroup1);
        // on the change of radiogroup option will call onchangelistener
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
            // storing the radioigroup tex value in value variable
              value = ((RadioButton)findViewById(rg.getCheckedRadioButtonId() )).getText().toString();
           //      Toast.makeText(getBaseContext(), value, Toast.LENGTH_SHORT).show();
            }
        });
    }



    public void showDaialogOnButtonClick()
    {
        // calling the findViewById function that was defined in Activity will return instance of view for that calender
        Button CalenderButton = (Button) findViewById(R.id.calender);
        assert CalenderButton != null;
        // on the click of claneder it will call setonclciklistener
        CalenderButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // call pass the dilod_ID vlaue to open the calender dialog box
                showDialog(DILOD_ID);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id){
        if(id == DILOD_ID)
            // call DatePickerDialog on on select of date
            return new DatePickerDialog(this,dpickerlistener , year_x, month_x,day_x );
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerlistener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            // string selected value of year , month and date in variable
            year_x = year;
            month_x = monthOfYear + 1;
            day_x = dayOfMonth;
  //          Toast.makeText(MainActivity.this,year_x + "/" + month_x + "/" + day_x,Toast.LENGTH_LONG).show();
         // getting instance of date edittextbox
            date = (EditText)findViewById(R.id.date);
            assert date != null;
            // setting selected date in date textbox
            date.setText(month_x + "-" + day_x + "-" + year_x);

        }
    };


    public void addItemsOnSpinner() {
        // spinner control http://www.mkyong.com/android/android-spinner-drop-down-list-example/
        // initializing list for array to store eyecolor
        final List<String> list = new ArrayList<String>();
        // addfunction to add value in list
        list.add("Brown");
        list.add("Blue");
        list.add("Black");
        list.add("Gray");
        list.add("Amber");
        // creating object for arrayadapter fro dropdown
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        // setDropDownViewResource function to set the list in dataadapter
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        assert spinner != null;
        // setadapter function to set in spinner
        spinner.setAdapter(dataAdapter);
       // spinner.setSelection(eyecolor);

    }

    public void addListenerOnSpinnerItemSelection() {
        // initilization of dropdown
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        assert spinner != null;
        // call the onItemSelected function to get the value of selected dropdown on change of selection of dropdown from CustomOnItemSelectedListener class
        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }


    @Override
    public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2)
    {
        // switch case for selection of  slider
        switch (arg0.getId())
        {
            // case for selection of pant size
            case R.id.seekBar1:
                // setprogress  function to set the vlaue pant size of progressbar
                slider1.setProgress(arg1);
                // getting instance view of pansize
                TextView pantsize = (TextView) findViewById(R.id.pantsize);
                // settext funtion to set the slider value with text in textview to s how user
                pantsize.setText("Selected size for Pant :"+Integer.toString(arg1)+"/"+slider1.getMax());
                break;
            //  case for selection of shirt size slider
            case R.id.seekBar2:
                // setprogress  function to set the vlaue of shirt size progressbar
                slider2.setProgress(arg1);
                TextView shirtsize = (TextView) findViewById(R.id.shirtsize);
                // settext funtion to set the shirt size value with text in textview to show user
                shirtsize.setText("Selected size for Shirt :"+Integer.toString(arg1)+"/"+slider2.getMax());
                break;
            //  case for selection of shoes size slider
            case R.id.seekBar3:
                // setprogress  function to set the vlaue of shoes size progressbar
                slider3.setProgress(arg1);
                TextView shoessize = (TextView) findViewById(R.id.shoessize);
                // settext funtion to set the shoes size value with text in textview to show user
                shoessize.setText("Selected size for Shoes :"+Integer.toString(arg1)+"/"+slider3.getMax());
                break;
        }


    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
