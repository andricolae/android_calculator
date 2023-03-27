package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class GraphActivity extends AppCompatActivity {

    LineGraphSeries<DataPoint> series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        double x = 0, y = 0;

        Intent intent = getIntent();

        String function = intent.getStringExtra("func");

        GraphView graph = (GraphView) findViewById(R.id.graph);
        series = new LineGraphSeries<DataPoint>();

        for (int i = 0; i < 500; i++) {
            x += 0.1;
            String toEval = function.replace("x", String.valueOf(x));
            y = evaluateExpression(toEval);
            series.appendData(new DataPoint(x, y), true, 500);
        }

        graph.addSeries(series);
    }

    public static double evaluateExpression(final String str) {
        return new Object() {
            int pos = -1, chaR;

            void nextChar() {
                chaR = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean functionToPerform(int nextChar) {
                while (chaR == ' ') nextChar();
                if (chaR == nextChar) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)chaR);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (functionToPerform('+')) x += parseTerm();
                    else if (functionToPerform('-')) x -= parseTerm();
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (functionToPerform('*')) x *= parseFactor();
                    else if (functionToPerform('/')) x /= parseFactor();
                    else return x;
                }
            }

            double parseFactor() {
                if (functionToPerform('-')) return -parseFactor();

                double x;
                int startPos = this.pos;
                if (functionToPerform('(')) {
                    x = parseExpression();
                    if (!functionToPerform(')')) throw new RuntimeException("Missing ')'");
                } else if ((chaR >= '0' && chaR <= '9') || chaR == '.') {
                    while ((chaR >= '0' && chaR <= '9') || chaR == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (chaR >= 'a' && chaR <= 'z') {
                    while (chaR >= 'a' && chaR <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    if (functionToPerform('(')) {
                        x = parseExpression();
                        if (!functionToPerform(')')) throw new RuntimeException("Missing ')' after argument to " + func);
                    } else {
                        x = parseFactor();
                    }
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(x);
                    else if (func.equals("cos")) x = Math.cos(x);
                    else if (func.equals("tan")) x = Math.tan(x);
                    else if (func.equals("ctg")) x = 1 / Math.tan(x);
                    else if (func.equals("log")) x = Math.tan(Math.log10(x));
                    else if (func.equals("ln")) x = Math.tan(Math.log(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)chaR);
                }
                return x;
            }
        }.parse();
    }
}