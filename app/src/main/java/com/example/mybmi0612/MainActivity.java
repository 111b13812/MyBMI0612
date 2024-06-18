package com.example.mybmi0612;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText height;
    private EditText weight;
    private TextView show;
    private RadioGroup rgSex;
//    private RadioButton rbFemale;
//    private RadioButton rbMale;
    private CheckBox apple;
    private CheckBox banana;
    private CheckBox orange;
    private final String[] sex = {"女生", "男生"};
    private final String [] fruits = {"蘋果", "香蕉", "橘子"};
    private final boolean[] fruitsSelect = {false, false, false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        myListener();
    }

    private void myListener() {
        rgSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbFemale) {
                    show.setText("我是女生");
                } else if (checkedId == R.id.rbMale) {
                    show.setText("我是男生");
                }
            }
        });

        apple.setOnCheckedChangeListener((buttonView, isChecked) -> getFruits());
        banana.setOnCheckedChangeListener((buttonView, isChecked) -> getFruits());
        orange.setOnCheckedChangeListener((buttonView, isChecked) -> getFruits());
    }

    private void getFruits() {
        String msg ="";
        if (apple.isChecked()) {
            msg += "蘋果";
        }
        if (banana.isChecked()) {
            msg += "香蕉";
        }
        if (orange.isChecked()) {
            msg += "橘子";
        }
        show.setText("我喜歡吃" + msg);
    }

    public void showDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("BMI");
        double bmi = getBmi();
        String result = getString(R.string.strShowbmi) + bmi;
        // 顯示訊息
//        builder.setMessage(result);
        // 顯示訊息
//        builder.setSingleChoiceItems(sex, sexSelect,new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(MainActivity.this, sex[sexSelect], Toast.LENGTH_SHORT).show();
//            }
//        });

        builder.setMultiChoiceItems(fruits, fruitsSelect, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                fruitsSelect[which] = isChecked;
            }
        });
        // 顯示多選

        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(MainActivity.this, "你好", Toast.LENGTH_SHORT).show();

                    }
                });
                builder.setNegativeButton("取消",new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String msg = "";
                        for (int i = 0; i < fruitsSelect.length; i++) {
                            if (fruitsSelect[i]) {
                                msg += fruits[i] + ",";
                            }
                        }
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
        }
    public void calcBMI(View view){

        double bmi = getBmi();

        String result = "你的BMI是" + bmi;

        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
//      show.setText(result);
    }

    private double getBmi() {
        DecimalFormat df = new DecimalFormat("0.00");
        double w = Double.parseDouble(weight.getText().toString());
        double h = Double.parseDouble(height.getText().toString());
        double bmi = w / (h/100.0 * h/100.0);
        bmi = Double.parseDouble(df.format(bmi));
        return bmi;
    }

    private void findViews() {
        height = findViewById(R.id.edHeight);
        weight = findViewById(R.id.edWeight);
        show = findViewById(R.id.tvShow);
        rgSex = findViewById(R.id.rbSex1);
        apple = findViewById(R.id.cbApple);
        banana = findViewById(R.id.cbBanana);
        orange = findViewById(R.id.cbOrange);
    }

    public void GoNext(View view) {
        Intent intent = new Intent(this, ResultActivity.class);
        double bmi = getBmi();
        intent.putExtra("bmi", bmi);
        startActivity(intent);
    }
}