package com.example.multiple_language;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView language_dialog, helloworldtext;
    boolean lang_selected = true;
    Context context;
    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        language_dialog = (TextView) findViewById(R.id.dialog_language);
        helloworldtext = (TextView) findViewById(R.id.textview);

        language_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] language = {"ENGLISH", "हिंदी"};

                int checkedItem;

                if(lang_selected){
                    checkedItem = 0;
                } else {
                    checkedItem = 1;
                }

                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("Select a language")
                        .setSingleChoiceItems(language, checkedItem, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                language_dialog.setText(language[i]);
                                if(language[i].equals("ENGLISH"))
                                {
                                    context = LocaleHelper.setLocale(MainActivity.this,"en");
                                    resources = context.getResources();

                                    helloworldtext.setText(resources.getString(R.string.language));
                                }
                                if(language[i].equals("हिंदी")){
                                    context = LocaleHelper.setLocale(MainActivity.this,"hi");
                                    resources = context.getResources();

                                    helloworldtext.setText(resources.getString(R.string.language));
                                }

                            }
                        })
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });

                builder.create().show();

            }
        });

    }
}