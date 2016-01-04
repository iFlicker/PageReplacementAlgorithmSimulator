package com.fffire.pagereplacementalgorithmsimulator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private Button btn;
    private TextView evbs;
    private TextView evps;
    private TextView evp;
    private int bSize;
    private int pSize;
    private String page_test;
    private String pages[];
    private String OPT;
    private String FIFO;
    private String LRU;
    private String Clock;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        evbs = (TextView) findViewById(R.id.editText_bSize);
        evps = (TextView) findViewById(R.id.editText_pSize);
        evp = (TextView) findViewById(R.id.editText_page);

        btn = (Button) findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {   //Button设置Click监听方法
            @Override
            public void onClick(View v) {

                //从textView获取数据
                bSize = Integer.parseInt(evbs.getText().toString());
                pSize = Integer.parseInt(evps.getText().toString());
                page_test = evp.getText().toString();
                pages = page_test.split(" ");
                int page[] = new int[pages.length];
                for (int i = 0; i < pages.length; i++) {
                    page[i] = Integer.parseInt(pages[i]);
                }

                //计算,并get结果放入字符串
                pras tp = new pras(bSize, pSize, page);
                OPT = tp.show("OPT");
                FIFO = tp.show("FIFO");
                LRU = tp.show("LRU");
                Clock = tp.show("Clock");

                //扔进context全局变量
                ((ApplicationHelper) getApplicationContext()).setOpt(OPT);
                ((ApplicationHelper) getApplicationContext()).setFifo(FIFO);
                ((ApplicationHelper) getApplicationContext()).setLru(LRU);
                ((ApplicationHelper) getApplicationContext()).setClock(Clock);

                //扔进Bundle包
                /*
                Bundle bd = new Bundle();
                bd.putString("opt",OPT);
                bd.putString("fifo",FIFO);
                bd.putString("lru",LRU);
                bd.putString("clock",Clock);
                */
                Intent itt = new Intent(MainActivity.this, ResultActivity.class);
                //itt.putExtra("data", bd);

                startActivity(itt);
            }
        });

    }
}
