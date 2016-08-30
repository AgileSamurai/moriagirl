package com.sample.agilesamurai.moriagirl;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    static List<String> name = new ArrayList();
    Button syokaiButton;
    MemberManager memberManager;
    Speeching speeching;
    Greeting greeting;

    public enum State {
        DisplayInputMessage,
        InputName,
        SelfIntroduction,
        ByeBye;
    }
    State state = State.DisplayInputMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        syokaiButton = (Button)findViewById(R.id.syokai);
        speeching = new Speeching(this);
        memberManager = new MemberManager(this);
        name = memberManager.getNames();
        greeting = new Greeting(this);

        displayMessage(greeting.randomGreetingReturn());
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        speeching.shutDown();
    }

    public void displayInputMessage(){
        displayMessage(getString(R.string.inputName));
        state = State.InputName;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // メニューの要素を追加して取得
        MenuItem actionItem = menu.add("Action Button Return title");

        // アイコンを設定
        actionItem.setIcon(android.R.drawable.ic_menu_revert);

        // SHOW_AS_ACTION_ALWAYS:常に表示
        actionItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Toast.makeText(this, "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();

        AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("タイトル画面へ戻りますか？");
        alertDialogBuilder.setIcon(R.drawable.asd);

        alertDialogBuilder.setPositiveButton("戻る", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                finish();
            }

        });

        alertDialogBuilder.setNegativeButton(R.string.cancel ,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

            }


        });

        alertDialogBuilder.create().show();

        //finish();
        return true;
    }

    // TODO: いつタップされても入力できちゃうから直す
    public void inputName() {
        boolean apply;
        apply = memberManager.inputName();
        System.out.println(apply);
    }

    public void selfIntroduction(View view){
        state = State.SelfIntroduction;
        SelfIntroduction selfIntroduction = new SelfIntroduction(name, this);
        selfIntroduction.introduction();
    }

    public void onClick(View view){
        switch(state) {
            case DisplayInputMessage:
                displayInputMessage();
                break;
            case InputName:
                inputName();
                break;
            case SelfIntroduction:
                selfIntroduction(view);
                break;
        }
    }

    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.mikuText);
        priceTextView.setText(message);
        speech(message);
    }

    private void speech(String string){
        speeching.speechText(string);
    }
}
