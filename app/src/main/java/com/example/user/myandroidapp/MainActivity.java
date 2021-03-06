package com.example.user.myandroidapp;

import android.content.Context;
import android.hardware.input.InputManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, EditText.OnEditorActionListener {

    static final String[] pokemonNames = {"小火龍","傑尼龜","妙蛙種子"};
    TextView infoText;
    EditText nameEditText;
    RadioGroup optionsGroup;
    Button confirmBtn;
    //add a checkbox member variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_main);

        //find UIs by their ids
        infoText = (TextView)findViewById(R.id.infoText);
        nameEditText = (EditText)findViewById(R.id.nameEditText);
        nameEditText.setOnEditorActionListener(this);
        nameEditText.setImeOptions(EditorInfo.IME_ACTION_DONE);

        optionsGroup = (RadioGroup)findViewById(R.id.optionsGroup);
        confirmBtn = (Button)findViewById(R.id.confirmButton);
        confirmBtn.setOnClickListener(MainActivity.this);

        //find the checkBox UI here

    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if(viewId == R.id.confirmButton) {
            //confirmButton was clicked
            Log.d("buttonTest","confirm-button was clicked");
            String name = nameEditText.getText().toString();

            int selectedRadioButtonId = optionsGroup.getCheckedRadioButtonId();
            View selectedRadioButtonView = optionsGroup.findViewById(selectedRadioButtonId);

            int selectedIndex = optionsGroup.indexOfChild(selectedRadioButtonView);
            String pokemonName = pokemonNames[selectedIndex];

            //if your radio button has text on itself.
//            RadioButton selectedRadioButton = (RadioButton)selectedRadioButtonView;
//            String radioBtnText = selectedRadioButton.getText().toString();

            String welcomeMessage = String.format(
                    "你好, 訓練家%s 歡迎來到神奇寶貝的世界 你的第一個夥伴是%s",
                    name,
                    pokemonName
            );

            infoText.setText(welcomeMessage);
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(actionId == EditorInfo.IME_ACTION_DONE) {
            //dismiss virtual keyboard
            InputMethodManager inm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            inm.hideSoftInputFromWindow(v.getWindowToken(), 0);

            //simulate button clicked
            confirmBtn.performClick();
            return true;
        }
        return false;
    }
}
