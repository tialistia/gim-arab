package com.example.client.dragdrop;

import android.content.ClipData;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Level2 extends AppCompatActivity {

    TextView text2, text2drop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2);

        text2 = (TextView) findViewById(R.id.ayat2);
        text2drop = (TextView) findViewById(R.id.dropDua1);

        text2.setOnLongClickListener(longClickListener);
        text2drop.setOnDragListener(dragListener);

        Button level3 = (Button) findViewById(R.id.goToLevel3);
        level3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Level3.class);
                startActivityForResult(myIntent, 0);
            }

        });

    }

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

                    if(view.getId() == R.id.ayat2) {
                        text2drop.setText("Berhasil");
                        status = status + 1;
                    }

                    if(status == 1) {
                        //MediaPlayer music1 = MediaPlayer.create(Level2.this, R.raw.alfatiha1);
                        //music1.start();

                        View text = findViewById(R.id.ayat2);
                        View b = findViewById(R.id.goToLevel3);

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
