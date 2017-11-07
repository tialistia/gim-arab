package com.example.client.dragdrop;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView text1, text1drop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = (TextView) findViewById(R.id.ayat1);
        text1drop = (TextView) findViewById(R.id.ayatDrop1);

        text1.setOnLongClickListener(longClickListener);
        //text1drop.setOnLongClickListener(longClickListener);

        text1drop.setOnDragListener(dragListener);

        Button level2 = (Button) findViewById(R.id.goToLevel2);
        level2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Level2.class);
                startActivityForResult(myIntent, 0);
            }

        });
    }

/*    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View vv) {
            Intent leve2 = new Intent(vv.getContext(), Level2.class);
            startActivityForResult(leve2, 0);
        }
    };*/

    View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {

            ClipData data = ClipData.newPlainText("","");
            View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data,myShadowBuilder,v,0);

            return false;
        }
    };


    View.OnDragListener dragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int dragEvent = event.getAction();

            switch (dragEvent) {
                case DragEvent.ACTION_DRAG_ENTERED:
                    int status = 0;

                    final  View view = (View) event.getLocalState();

                    if(view.getId() == R.id.ayat1) {
                        text1drop.setText("Berhasil");
                        status = status + 1;
                    }

                    if(status == 1) {
                        MediaPlayer music1 = MediaPlayer.create(MainActivity.this, R.raw.alfatiha1);
                        music1.start();
                        View b = findViewById(R.id.goToLevel2);
                        View text = findViewById(R.id.ayat1);

                        text.setVisibility(View.INVISIBLE);
                        b.setVisibility(View.VISIBLE);
                    }

                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    break;
            }

            return true;
        }
    };
}
