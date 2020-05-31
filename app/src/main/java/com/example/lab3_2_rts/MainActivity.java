package com.example.lab3_2_rts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static java.lang.Math.pow;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button calc = findViewById(R.id.button);
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input1 = findViewById(R.id.learningRate);
                EditText input2 = findViewById(R.id.deadline);
                EditText input3 = findViewById(R.id.iterNum);

                double learningRate = Double.parseDouble(input1.getText().toString());
                double deadline = Double.parseDouble(input2.getText().toString());
                int iterNum = Integer.parseInt(input3.getText().toString());
                String result = perceptron(learningRate, deadline, iterNum);

                TextView resultOutput = findViewById(R.id.Result);
                resultOutput.setText(result);
            }
        });
    }

    public static String perceptron(double learningRate, double deadline, int iterNum){
        int iter = 0;
        int P = 4;
        int [][] points = {{0,6}, {1,5}, {3,3}, {2,4}};
        double w1 = 0, w2 = 0;
        double y, delta;
        String res ="";
        long start = System.nanoTime();
        long deltaTime;
        while (iter < iterNum && ((System.nanoTime() - start)/pow(10,9) < deadline)){
            y = points[iter%4][0] * w1 + points[iter%4][1] * w2;
            delta = P - y;
            w1 += delta * points[iter%4][0] * learningRate;
            w2 += delta * points[iter%4][1] * learningRate;
            iter++;
            if((points[0][0] * w1 + points[0][1] * w2) > P &&
                    (points[2][0] * w1 + points[2][1] * w2) < P &&
                    (points[1][0] * w1 + points[1][1] * w2) > P &&
                    (points[3][0] * w1 + points[3][1] * w2) < P){
                deltaTime = System.nanoTime() - start;
                res = "w1 = " + w1 + "\nw2 = " + w2 + "\ntime = " + deltaTime/pow(10,9) + "s";
                return res;
            }
        }
        return "Розв'язок не знайдено";
    }
}
