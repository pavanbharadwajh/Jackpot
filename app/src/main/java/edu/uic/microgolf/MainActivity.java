package edu.uic.microgolf;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    static final int RESULT_FOR_THREAD1 = 1;
    static final int RESULT_FOR_THREAD2 = 2;
    static final int NEAR_MISS = 3;
    static final int NEAR_GROUP =4;
    static final int BIG_MISS =5;
    static final int CATASTROPHE = 6;

    Intent startintent;

    Button start;
    Button restart;
    Handler PlayerOneHandler, PlayerTwoHandler;
    TextView textView1;
    TextView textView2;
    TextView textView;

    Boolean start_game;
    boolean check;

    ArrayList<TextView> circle;
    TextView circle1;
    TextView circle2;
    TextView circle3;
    TextView circle4;
    TextView circle5;
    TextView circle6;
    TextView circle7;
    TextView circle8;
    TextView circle9;
    TextView circle10;

    TextView circle11;
    TextView circle12;
    TextView circle13;
    TextView circle14;
    TextView circle15;
    TextView circle16;
    TextView circle17;
    TextView circle18;
    TextView circle19;
    TextView circle20;

    TextView circle21;
    TextView circle22;
    TextView circle23;
    TextView circle24;
    TextView circle25;
    TextView circle26;
    TextView circle27;
    TextView circle28;
    TextView circle29;
    TextView circle30;

    TextView circle31;
    TextView circle32;
    TextView circle33;
    TextView circle34;
    TextView circle35;
    TextView circle36;
    TextView circle37;
    TextView circle38;
    TextView circle39;
    TextView circle40;

    TextView circle41;
    TextView circle42;
    TextView circle43;
    TextView circle44;
    TextView circle45;
    TextView circle46;
    TextView circle47;
    TextView circle48;
    TextView circle49;
    TextView circle50;

    Thread thread1;
    Thread thread2;

    Random rand = new Random();
    int winner =0;

    @SuppressLint("HandlerLeak")
    private final Handler UIHandler = new Handler() {

        public void handleMessage(Message msg) {
            synchronized (this) {
                int what = msg.what;
                switch (what) {

                    //Getting the bundle from worker thread and passing it back to the thread
                    case RESULT_FOR_THREAD1:
                        if (msg.arg1 == winner) {
                            circle.get(msg.arg1-1).setTextColor(Color.parseColor("#3374FF"));
                            thread1.interrupt();
                            thread2.interrupt();
                            PlayerOneHandler.getLooper().quit();
                            PlayerTwoHandler.getLooper().quit();
                            textView1.setText("Winner");
                            textView2.setText("");
                            Log.i("valuewin1",String.valueOf(msg.arg1));
                            Toast.makeText(getApplicationContext(), "JACKPOT", Toast.LENGTH_LONG).show();
                        }

                        else {
                            String val = setview(msg.arg1,winner);
                            textView1.setText(val);
                            circle.get(new Integer(msg.arg1-1)).setTextColor(Color.parseColor("#F70707"));
                            circle.get(new Integer(msg.arg1 - 1)).setText(String.valueOf(msg.arg1));
//                            textView1.setText(String.valueOf(msg.arg1));

                            Message thread1msg = new Message();
                            switch (val){
                                case "Near Miss":
                                    thread1msg = PlayerOneHandler.obtainMessage(NEAR_MISS);
                                    break;
                                case "Near Group":
                                    thread1msg = PlayerOneHandler.obtainMessage(NEAR_GROUP);
                                    break;
                                case "Big Miss":
                                    thread1msg = PlayerOneHandler.obtainMessage(BIG_MISS);
                                    break;
                                case "Catastrophe":
                                    thread1msg = PlayerOneHandler.obtainMessage(CATASTROPHE);
                                    break;
                            }
                            thread1msg.arg1 =msg.arg1;
                            thread1msg.obj = val;
                            PlayerOneHandler.sendMessage(thread1msg);
                            Log.i("value1",String.valueOf(msg.arg1));
                        }
                        break;

                    case RESULT_FOR_THREAD2:
                        if (msg.arg1 == winner) {
                            textView1.setText("");
                            circle.get(msg.arg1-1).setTextColor(Color.parseColor("#3374FF"));
                            Log.i("value2",String.valueOf(msg.arg1));
                            thread1.interrupt();
                            thread2.interrupt();
                            PlayerOneHandler.getLooper().quit();
                            PlayerTwoHandler.getLooper().quit();
                            textView2.setText("WINNER");
                            Toast.makeText(getApplicationContext(), "JACKPOT" , Toast.LENGTH_LONG).show();

                        }

                        else {
                            String val = setview(msg.arg1,winner);
                            textView2.setText(val);
                            circle.get(msg.arg1-1).setTextColor(Color.parseColor("#FF33C7"));
                            circle.get(msg.arg1 - 1).setText(String.valueOf(msg.arg1));
                            Message thread2msg = new Message();
                            switch (val){
                                case "Near Miss":
                                    thread2msg = PlayerTwoHandler.obtainMessage(NEAR_MISS);
                                    break;
                                case "Near Group":
                                    thread2msg = PlayerTwoHandler.obtainMessage(NEAR_GROUP);
                                    break;
                                case "Big Miss":
                                    thread2msg = PlayerTwoHandler.obtainMessage(BIG_MISS);
                                    break;
                                case "Catastrophe":
                                    thread2msg = PlayerTwoHandler.obtainMessage(CATASTROPHE);
                                    break;
                            }
                            thread2msg.arg1 =msg.arg1;
                            thread2msg.obj = val;
                            PlayerTwoHandler.sendMessage(thread2msg);
                            Log.i("value2",String.valueOf(msg.arg1));
                        }

                        break;
                }
            }
        }
    };

    //On create method of the main activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startintent = getIntent();
        start_game = false;
        check =false;

        // list of numbers
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();

        //setting the view
        circle = new ArrayList<TextView>();
        circle1 = findViewById(R.id.circle1);
        circle.add(circle1);
        circle2 = findViewById(R.id.circle2);
        circle.add(circle2);
        circle3 = findViewById(R.id.circle3);
        circle.add(circle3);
        circle4 = findViewById(R.id.circle4);
        circle.add(circle4);
        circle5 = findViewById(R.id.circle5);
        circle.add(circle5);
        circle6 = findViewById(R.id.circle6);
        circle.add(circle6);
        circle7 = findViewById(R.id.circle7);
        circle.add(circle7);
        circle8 = findViewById(R.id.circle8);
        circle.add(circle8);
        circle9 = findViewById(R.id.circle9);
        circle.add(circle9);
        circle10 = findViewById(R.id.circle10);
        circle.add(circle10);

        circle11 = findViewById(R.id.circle11);
        circle.add(circle11);
        circle12 = findViewById(R.id.circle12);
        circle.add(circle12);
        circle13 = findViewById(R.id.circle13);
        circle.add(circle13);
        circle14 = findViewById(R.id.circle14);
        circle.add(circle14);
        circle15 = findViewById(R.id.circle15);
        circle.add(circle15);
        circle16 = findViewById(R.id.circle16);
        circle.add(circle16);
        circle17 = findViewById(R.id.circle17);
        circle.add(circle17);
        circle18 = findViewById(R.id.circle18);
        circle.add(circle18);
        circle19 = findViewById(R.id.circle19);
        circle.add(circle19);
        circle20 = findViewById(R.id.circle20);
        circle.add(circle20);

        circle21 = findViewById(R.id.circle21);
        circle.add(circle21);
        circle22 = findViewById(R.id.circle22);
        circle.add(circle22);
        circle23 = findViewById(R.id.circle23);
        circle.add(circle23);
        circle24 = findViewById(R.id.circle24);
        circle.add(circle24);
        circle25 = findViewById(R.id.circle25);
        circle.add(circle25);
        circle26 = findViewById(R.id.circle26);
        circle.add(circle26);
        circle27 = findViewById(R.id.circle27);
        circle.add(circle27);
        circle28 = findViewById(R.id.circle28);
        circle.add(circle28);
        circle29 = findViewById(R.id.circle29);
        circle.add(circle29);
        circle30 = findViewById(R.id.circle30);
        circle.add(circle30);

        circle31 = findViewById(R.id.circle31);
        circle.add(circle31);
        circle32 = findViewById(R.id.circle32);
        circle.add(circle32);
        circle33 = findViewById(R.id.circle33);
        circle.add(circle33);
        circle34 = findViewById(R.id.circle34);
        circle.add(circle34);
        circle35 = findViewById(R.id.circle35);
        circle.add(circle35);
        circle36 = findViewById(R.id.circle36);
        circle.add(circle36);
        circle37 = findViewById(R.id.circle37);
        circle.add(circle37);
        circle38 = findViewById(R.id.circle38);
        circle.add(circle38);
        circle39 = findViewById(R.id.circle39);
        circle.add(circle39);
        circle40 = findViewById(R.id.circle40);
        circle.add(circle40);

        circle41 = findViewById(R.id.circle41);
        circle.add(circle41);
        circle42 = findViewById(R.id.circle42);
        circle.add(circle42);
        circle43 = findViewById(R.id.circle43);
        circle.add(circle43);
        circle44 = findViewById(R.id.circle44);
        circle.add(circle44);
        circle45 = findViewById(R.id.circle45);
        circle.add(circle45);
        circle46 = findViewById(R.id.circle46);
        circle.add(circle46);
        circle47 = findViewById(R.id.circle47);
        circle.add(circle47);
        circle48 = findViewById(R.id.circle48);
        circle.add(circle48);
        circle49 = findViewById(R.id.circle49);
        circle.add(circle49);
        circle50 = findViewById(R.id.circle50);
        circle.add(circle50);

        textView = (TextView) findViewById(R.id.textView);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);

        start = findViewById(R.id.button1);
        restart=(Button)findViewById(R.id.button2);

        //Button to start the game
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!start_game){
                    start_game = true;
                    GameStart();
                }
            }
        });

        restart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                thread1 = new Thread(new Player1());
                thread2 = new Thread(new Player2());
                if(PlayerOneHandler!=null){
                    thread1.interrupt();
                    thread2.interrupt();
                    PlayerOneHandler.getLooper().quit();
                    PlayerOneHandler.removeCallbacksAndMessages(null);

                }
                if(PlayerTwoHandler!=null){
                    thread1.interrupt();
                    thread2.interrupt();
                    PlayerTwoHandler.getLooper().quit();
                    PlayerTwoHandler.removeCallbacksAndMessages(null);
                }

                if(UIHandler != null){
                    UIHandler.removeCallbacksAndMessages(null);
                }

                finish();
                startActivity(startintent);
            }
        });

    }

    //Function for starting the game
    public void GameStart() {
        winner = rand.nextInt(50) + 1;
        circle.get(winner-1).setText(String.valueOf(winner));
        circle.get(winner-1).setTextColor(Color.parseColor("#000000"));
        textView.setText("JACKPOT is "+String.valueOf(winner));
        textView1.setText("");
        textView2.setText("");
        thread1 = new Thread(new Player1 ());
        thread2 = new Thread(new Player2());
        thread1.start();
        thread2.start();
    }

    //Return the possible responses
    public String setview(int number, int winner){
        String val=" ";
        int a = ((number-1)/10)*10;
        int b = ((winner-1)/10)*10;
        int diff = Math.abs((a-b));
        switch (diff){
            case 0: return "Near Miss";
            case 10: return "Near Group";
            case 20: return "Big Miss";
            case 30: return "Big Miss";
            case 40: return "Big Miss";
        }
        return val;
    }


    //Player1 runnable
    public class Player1 implements Runnable {
        int n = 0;
        @SuppressLint("HandlerLeak")
        public void run() {
            Looper.prepare();
            synchronized (this){
                PlayerOneHandler = new Handler() {
                    public void handleMessage(Message msg) {
                        int what = msg.what;
                        switch (what) {
                            //For receiving guess
                            case NEAR_MISS: // highlight the winner
                                synchronized (this){
                                    n = sameGroup1(msg.arg1,list1);
                                    Message msgUI1 = UIHandler.obtainMessage(RESULT_FOR_THREAD1);
                                    msgUI1.arg1 = n;
                                    UIHandler.sendMessage(msgUI1);
                                    Message threard2_msg = PlayerTwoHandler.obtainMessage(CATASTROPHE);
                                    threard2_msg.arg1 = n;
                                    PlayerTwoHandler.sendMessage(threard2_msg);
                                Log.i("range",msg.obj +" Player 1"+ String.valueOf(msg.arg1));
                                }
                                break;

                            case NEAR_GROUP: // highlight the winner
                                synchronized (this) {
                                    n = nearGroup(msg.arg1);
                                    Message msgUI1 = UIHandler.obtainMessage(RESULT_FOR_THREAD1);
                                    msgUI1.arg1 = n;
                                    UIHandler.sendMessage(msgUI1);
                                    Message threard2_msg = PlayerTwoHandler.obtainMessage(CATASTROPHE);
                                    threard2_msg.arg1 = n;
                                    PlayerTwoHandler.sendMessage(threard2_msg);
                                    Log.i("range", msg.obj + " Player 1:" + String.valueOf(msg.arg1));
                                }
                                break;

                            case BIG_MISS: // highlight the winner
                                synchronized (this){
                                    n = bigMissGroup(msg.arg1);
                                    Message msgUI1 = UIHandler.obtainMessage(RESULT_FOR_THREAD1);
                                    msgUI1.arg1 = n;
                                    UIHandler.sendMessage(msgUI1);
                                    Message threard2_msg = PlayerTwoHandler.obtainMessage(CATASTROPHE);
                                    threard2_msg.arg1 = n;
                                    PlayerTwoHandler.sendMessage(threard2_msg);
                                Log.i("range",msg.obj +" Player 1"+ String.valueOf(msg.arg1));
                                }
                                break;

                            case CATASTROPHE: // highlight the winner
                                synchronized (this){
                                    if (catastropheGroup(msg.arg1, n)){
                                        Toast.makeText(getApplicationContext(),"Player 1 Wins",Toast.LENGTH_LONG).show();
                                        PlayerOneHandler.getLooper().quit();
                                        PlayerTwoHandler.getLooper().quit();
                                        thread1.interrupt();
                                        thread2.interrupt();
                                        Log.i("Disqualified", "Player1");
                                    }
                                }
                                break;

                        }
                        try {
                            sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }}

                };

                n = rand.nextInt(50) + 1;
                Message msgUI1 = UIHandler.obtainMessage(RESULT_FOR_THREAD1);
                msgUI1.arg1 = n;
                UIHandler.sendMessage(msgUI1);

                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }}

            Looper.loop();
        }

    }

    //Player2 runnable
    public class Player2 implements Runnable {
        int n = 0;
        @SuppressLint("HandlerLeak")
        public void run() {
            Looper.prepare();
            synchronized (this){
                PlayerTwoHandler = new Handler() {
                    @SuppressLint("SetTextI18n")
                    public void handleMessage(Message msg) {
                        int what = msg.what;
                        switch (what) {
                            //For receiving guess
                            case NEAR_MISS: // highlight the winner
                                synchronized (this) {
                                    n = sameGroup2(msg.arg1, list2);
                                    Message msgUI2 = UIHandler.obtainMessage(RESULT_FOR_THREAD2);
                                    msgUI2.arg1 = n;
                                    UIHandler.sendMessage(msgUI2);
                                    Message threard1_msg = PlayerOneHandler.obtainMessage(CATASTROPHE);
                                    threard1_msg.arg1 = n;
                                    PlayerOneHandler.sendMessage(threard1_msg);
                                    Log.i("range", msg.obj + "Player 2 " + String.valueOf(msg.arg1));
                                }
                                break;

                            case NEAR_GROUP: // highlight the winner
                                synchronized (this) {
                                    n = nearGroup(msg.arg1);
                                    Message msgUI2 = UIHandler.obtainMessage(RESULT_FOR_THREAD2);
                                    msgUI2.arg1 = n;
                                    UIHandler.sendMessage(msgUI2);
                                    Message threard1_msg = PlayerOneHandler.obtainMessage(CATASTROPHE);
                                    threard1_msg.arg1 = n;
                                    PlayerOneHandler.sendMessage(threard1_msg);
                                    Log.i("range", msg.obj + "Player 2 " + String.valueOf(msg.arg1));
                                }
                                break;

                            case BIG_MISS: // highlight the winner
                                synchronized (this) {
                                    n = bigMissGroup(msg.arg1);
                                    Message msgUI2 = UIHandler.obtainMessage(RESULT_FOR_THREAD2);
                                    msgUI2.arg1 = n;
                                    UIHandler.sendMessage(msgUI2);
                                    Message threard1_msg = PlayerOneHandler.obtainMessage(CATASTROPHE);
                                    threard1_msg.arg1 = n;
                                    PlayerOneHandler.sendMessage(threard1_msg);
                                    Log.i("range", msg.obj + "Player 2 " + String.valueOf(msg.arg1));
                                }
                                break;

                            case CATASTROPHE: // highlight the winner
                                synchronized (this){
                                    if (catastropheGroup(msg.arg1, n)){
                                        Toast.makeText(getApplicationContext(),"Player 1 Wins",Toast.LENGTH_LONG).show();
                                        PlayerOneHandler.getLooper().quit();
                                        PlayerTwoHandler.getLooper().quit();
                                        thread1.interrupt();
                                        thread2.interrupt();
//                                        textView1.setText("WINNER");
//                                        textView2.setText("DISQUALIFIED");
                                        Log.i("Disqualified", "Player2");
                                }
                                }
                                break;

                        }
                        try {
                            sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                };

                n = rand.nextInt(50) + 1;
                Message msgUI2 = UIHandler.obtainMessage(RESULT_FOR_THREAD2);
                msgUI2.arg1 = n;
                UIHandler.sendMessage(msgUI2);

                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }}

            Looper.loop();
        }
    }

    ArrayList<Integer> list1;
    ArrayList<Integer> list2;

    //generate the number if player in thread group
    public int nearGroup(int value){
        int n =( (value-1)/10)*10;
        RandomNumberRange rir = null;
        if(value+10 >50){
            rir = new RandomNumberRange(n-9,n);
        }
        else if(value-10 <1){
            rir = new RandomNumberRange(n+11,n+20);
        }
        else{
            rir = new RandomNumberRange(n-9,n);
            rir.addRange(n+11,n+20);
        }
        return rir.getRandom();
    }

    int flag=0;

    //generate the number if thread1 in same group
    public int sameGroup1(int value,ArrayList<Integer> list){
        int n = ((value-1)/10)*10;
        if(flag==0) {
            for (int i = n + 1; i <= n + 10; i++) {
                list.add(i);
            }
        }
        list.remove(new Integer(value));
        int num;
        if(list.size()==0)
        {
            num= value;
        }
        else {
            Collections.shuffle(list);
            num = list.get(0);
        }
        flag=1;
        return num;

    }

    int flag1=0;

    //generate the number if thread2 in same group
    public int sameGroup2(int value,ArrayList<Integer> list){
        int n = ((value-1)/10)*10;
        if(flag==0) {
            for (int i = n + 1; i <= n + 10; i++) {
                list.add(i);
            }
        }
        list.remove(new Integer(value));
        int num;
        if(list.size()==0)
        {
            num= value;
        }
        else {
            Collections.shuffle(list);
            num = list.get(0);
        }
        flag1=1;
        return num;

    }

    //generate the number if Big Miss
    public int bigMissGroup(int value){
        if(value <25){
            return new Random().nextInt(20)+31;
            }
            else{
            return new Random().nextInt(20)+1;
        }
    }

    //generate the number if Catastrophe
    public boolean catastropheGroup(int value, int num){
        if(value == num){
            return check;
        }else{
            return false;
        }
    }
}
