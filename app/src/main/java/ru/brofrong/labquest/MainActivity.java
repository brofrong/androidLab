package ru.brofrong.labquest;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Activity {


    String data[][] = {
            {"Что из это языки програмирования:","Java<YES>","чай<NO>","аниме<NO>","С<YES>","Python<YES>"},
            {"Самая большая страна в мире<R>","США<NO>","Китай<NO>","Россия<YES>"},
            {"Имена Главных герое аниме Evangelion","Кодзима гений<YES>","Аска Лэнгли Сорью<YES>","Синдзи Икари<YES>","Владир Путин<NO>","Эдуард Эдуардович<NO>"},
            {"2+2=<R>","4<YES>","0<NO>","-2<NO>","62<NO>"},
            {"В чём сила?","В правде<YES>","F<YES>","в книгах<NO>"},
            {"Сколько суток составляют високосный год?<R>","366<NO>","365<YES>","367<NO>"}
    };

    int questNumber;
    int currentAnsvers;
    LinearLayout questBox;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        questBox  = findViewById(R.id.questionBox);

        Login();

    }


    public void Login(){
        Button button = findViewById(R.id.login_btn);
        button.setOnClickListener(new View.OnClickListener() {
            EditText login = findViewById(R.id.username);
            EditText password = findViewById(R.id.password);
            boolean flag=true;
            @Override
            public void onClick(View v) {
                flag= true;
                Log.d("$$$$$$$$$$$$$$$$","logIn");
                Log.d("###########",login.getText().toString());
                if (!login.getText().toString().equals("root")){
                    flag=false;
                    Log.d("$$$$$$$$$$$$$$$$","username");
                }
                if(!password.getText().toString().equals("1234")){
                    flag=false;
                    Log.d("$$$$$$$$$$$$$$$$","pass");
                }

                if(flag){
                    LinearLayout mainvindow = findViewById(R.id.main_window);
                    LinearLayout loginform = findViewById(R.id.login_form);
                    mainvindow.removeView(loginform);
                    createPage();
                }
            }
        });



    }

    void createPage(){

        TextView qust = new TextView(this);
        qust.setText("Вопрос номер: "+ (questNumber+1));
        qust.setTextSize(35);
        questBox.addView(qust);

        TextView tv = new TextView(this);
        tv.setText(Html.fromHtml(data[questNumber][0]));
        tv.setTextSize(20);
        questBox.addView(tv);

        if(data[questNumber][0].contains("<R>")){
            addRadio();
        }else{
            addChech();
        }


    }

    private void addRadio(){
        final RadioButton ch[] = new RadioButton[data[questNumber].length-1];
        RadioGroup radioGroup = new RadioGroup(this);

        for(int i=0;i<ch.length;i++){
            ch[i] = new RadioButton(this);
            ch[i].setText(Html.fromHtml(data[questNumber][i+1]));
            ch[i].setTextSize(20);
            ch[i].setId(100+i);
            radioGroup.addView(ch[i]);
        }
        questBox.addView(radioGroup);

        Button btn=new Button(this);
        btn.setText("дальше >");
        questBox.addView(btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean flag = true;

                for(int i=0;i<ch.length;i++){
                    if(!ch[i].isChecked() && data[questNumber][i+1].contains("<YES>")){
                        flag =false;
                    }else if(ch[i].isChecked() && data[questNumber][i+1].contains("<NO>")){
                        flag = false;
                    }
                }

                if(flag){
                    currentAnsvers++;
                }
                questNumber++;

                questBox.removeAllViews();

                if(questNumber<data.length){
                    createPage();
                }else{
                    finalScreen();
                }
            }
        });
    }

    private void addChech(){
        final CheckBox ch[] = new CheckBox[data[questNumber].length-1];

        for(int i=0;i<ch.length;i++){
            ch[i] = new CheckBox(this);
            ch[i].setText(Html.fromHtml(data[questNumber][i+1]));
            ch[i].setTextSize(20);
            questBox.addView(ch[i]);
        }

        Button btn=new Button(this);
        btn.setText("дальше >");
        questBox.addView(btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean flag = true;

                for(int i=0;i<ch.length;i++){
                    if(!ch[i].isChecked() && data[questNumber][i+1].contains("<YES>")){
                        flag =false;
                    }else if(ch[i].isChecked() && data[questNumber][i+1].contains("<NO>")){
                        flag = false;
                    }
                }

                if(flag){
                    currentAnsvers++;
                }
                questNumber++;

                questBox.removeAllViews();

                if(questNumber<data.length){
                    createPage();
                }else{
                    finalScreen();
                }


            }
        });
    }

    public void finalScreen(){
        TextView result = new TextView(this);
        result.setText("тест пройден, ваш результат:\n"+currentAnsvers+"/"+data.length);
        result.setGravity(Gravity.CENTER);
        result.setTextSize(25);
        result.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        result.setHeight(400);

        questBox.addView(result);

        Button btn = new Button(this);
        btn.setText("Повторить тест");

        questBox.addView(btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questNumber=0;
                questBox.removeAllViews();
                createPage();
            }
        });


    }

}
