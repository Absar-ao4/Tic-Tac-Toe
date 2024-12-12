package com.absar.tic_tac_toe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    boolean gameActive=true;
    //0 for X
    //1 for O
    int activep=0;
    int counter=0;
    int xp=0;
    int op=0,f=0;
    int gamestate[]={2,2,2,2,2,2,2,2,2};
    //0 for X
    //1 for O
    //2 for blank

    int winpos[][] = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
    };

    public void ptap(View view)
    {

        ImageView img=(ImageView) view;
        int tappedimg=Integer.parseInt(img.getTag().toString());
        if(!gameActive)
        {
            gameReset(view);
        }
        if(gamestate[tappedimg]==2)
        {
            gamestate[tappedimg]=activep;
            img.setTranslationY(-1000f);
            if(activep==0)
            {
                img.setImageResource(R.drawable.x);
                activep=1;
                TextView status=findViewById(R.id.status);
                status.setText("O's turn-tap to play");
            }
            else{
                img.setImageResource(R.drawable.o);
                activep=0;
                TextView status=findViewById(R.id.status);
                status.setText("X's turn-tap to play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        for(int[] winp:winpos)
        {
            if((gamestate[winp[0]]==gamestate[winp[1]])&&(gamestate[winp[1]]==gamestate[winp[2]])&&(gamestate[winp[0]]!=2))
            {
                gameActive=false;
                String winstr;
                if(gamestate[winp[0]]==0)
                {
                    winstr="X has won";
                    TextView status=findViewById(R.id.status);
                    status.setText(winstr);
                    TextView textView6=findViewById(R.id.textView6);
                    textView6.setText(++xp+"");
                    f=1;
                }
                else{
                    winstr="0 has won";
                    TextView status=findViewById(R.id.status);
                    status.setText(winstr);
                    TextView textView7=findViewById(R.id.textView7);
                    textView7.setText(++op+"");
                    f=1;
                }
                break;
            }
        }
        if(xp==5)
        {
            String winstr="X has won the series";
            TextView status=findViewById(R.id.status);
            status.setText(winstr);
            xp=0;
            op=0;
            TextView textView6=findViewById(R.id.textView6);
            textView6.setText(xp+"");
            TextView textView7=findViewById(R.id.textView7);
            textView7.setText(op+"");
        }
        else if(op==5)
        {
            String winstr="O has won the series";
            TextView status=findViewById(R.id.status);
            status.setText(winstr);
            xp=0;
            op=0;
            TextView textView6=findViewById(R.id.textView6);
            textView6.setText(xp+"");
            TextView textView7=findViewById(R.id.textView7);
            textView7.setText(op+"");
        }
        counter++;
        if(counter==9&&f==0)
        {
           String winstr="Draw";
            TextView status=findViewById(R.id.status);
            status.setText(winstr);
        }
    }
    public void gameReset(View view){
        gameActive=true;
        activep=0;
        for (int i=0;i<gamestate.length;i++)
        {
            gamestate[i]=2;
        }
        counter=0;
        f=0;
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        TextView status=findViewById(R.id.status);
        status.setText("X's turn-tap to play");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}