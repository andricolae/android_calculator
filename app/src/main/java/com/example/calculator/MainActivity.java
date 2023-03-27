package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button but1, but2, but3, but4, but5, but6, but7, but8, but9, but0, butDot, butGraph, butEquals, butPlus, butMin, butMul, butDiv, butInv, butSqRt, butPower2, butFact, butLog, butCtg, butTan, butCos, butSin,butLn, butBracketOpen, butBracketClose, butC, butX;
    TextView tvMain,tvHist;

    //final String PI = "3.1415";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        but1 = findViewById(R.id.but1);
        but2 = findViewById(R.id.but2);
        but3 = findViewById(R.id.but3);
        but4 = findViewById(R.id.but4);
        but5 = findViewById(R.id.but5);
        but6 = findViewById(R.id.but6);
        but7 = findViewById(R.id.but7);
        but8 = findViewById(R.id.but8);
        but9 = findViewById(R.id.but9);
        but0 = findViewById(R.id.but0);
        butGraph = findViewById(R.id.butGraph);
        butDot = findViewById(R.id.butDot);
        butEquals = findViewById(R.id.butEquals);
        butPlus = findViewById(R.id.butPlus);
        butMin = findViewById(R.id.butMin);
        butMul = findViewById(R.id.butMul);
        butDiv = findViewById(R.id.butDiv);
        butInv = findViewById(R.id.butInv);
        butSqRt = findViewById(R.id.butSqRt);
        butPower2 = findViewById(R.id.butPower2);
        butFact = findViewById(R.id.butFact);
        butLn = findViewById(R.id.butLn);
        butLog = findViewById(R.id.butLog);
        butCtg = findViewById(R.id.butCtg);
        butTan = findViewById(R.id.butTan);
        butSin = findViewById(R.id.butSin);
        butCos = findViewById(R.id.butCos);
        butBracketOpen = findViewById(R.id.butBracketOpen);
        butBracketClose = findViewById(R.id.butBracketClose);
        butC = findViewById(R.id.butC);
        butX = findViewById(R.id.butX);
        tvMain = findViewById(R.id.tvMain);
        tvHist = findViewById(R.id.tvHist);

        but1.setOnClickListener(v -> tvMain.setText(tvMain.getText() + "1"));
        but2.setOnClickListener(v -> tvMain.setText(tvMain.getText() + "2"));
        but3.setOnClickListener(v -> tvMain.setText(tvMain.getText() + "3"));
        but4.setOnClickListener(v -> tvMain.setText(tvMain.getText() + "4"));
        but5.setOnClickListener(v -> tvMain.setText(tvMain.getText() + "5"));
        but6.setOnClickListener(v -> tvMain.setText(tvMain.getText() + "6"));
        but7.setOnClickListener(v -> tvMain.setText(tvMain.getText() + "7"));
        but8.setOnClickListener(v -> tvMain.setText(tvMain.getText() + "8"));
        but9.setOnClickListener(v -> tvMain.setText(tvMain.getText() + "9"));
        but0.setOnClickListener(v -> tvMain.setText(tvMain.getText() + "0"));
        butDot.setOnClickListener(v -> tvMain.setText(tvMain.getText() + "."));

        butX.setOnClickListener(v -> {
            tvMain.setText(tvMain.getText() + "x");
        });
        butC.setOnClickListener(v -> {
            if (tvMain.getText().equals(""))
                return;
            String val = tvMain.getText().toString();
            val = val.substring(0, val.length() - 1);
            tvMain.setText(val);
        });

        butPlus.setOnClickListener(v -> tvMain.setText(tvMain.getText() + "+"));
        butMin.setOnClickListener(v -> tvMain.setText(tvMain.getText() + "-"));
        butMul.setOnClickListener(v -> tvMain.setText(tvMain.getText() + "*"));
        butDiv.setOnClickListener(v -> tvMain.setText(tvMain.getText() + "/"));

        butSqRt.setOnClickListener(v -> {
            String val = tvMain.getText().toString();
            if (val.contains("x")){
                tvHist.setText("sqrt" + val);
            } else {
                double r = Math.sqrt(Double.parseDouble(val));
                tvMain.setText(String.valueOf(r));
                tvHist.setText("sqrt" + val);
            }
        });

        butBracketOpen.setOnClickListener(v -> tvMain.setText(tvMain.getText() + "("));
        butBracketClose.setOnClickListener(v -> tvMain.setText(tvMain.getText() + ")"));

        butGraph.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), GraphActivity.class);
            String function = (String) tvHist.getText();
            intent.putExtra("func", function);
            view.getContext().startActivity(intent);
        });

        butSin.setOnClickListener(v -> {
            String valS = tvMain.getText().toString();
            if (valS.contains("x")){
                tvHist.setText("sin(" + valS + ")");
            } else {
                tvMain.setText(String.valueOf(Math.sin(Double.parseDouble(valS))));
                tvHist.setText(tvHist.getText()  + "sin" + valS);
            }
        });
        butCos.setOnClickListener(v -> {
            String valS = tvMain.getText().toString();
            if (valS.contains("x")){
                tvHist.setText("cos(" + valS + ")");
            } else {
                tvMain.setText(String.valueOf(Math.cos(Double.parseDouble(valS))));
                tvHist.setText(tvHist.getText()  + "cos" + valS);
        }});
        butTan.setOnClickListener(v -> {
            String valS = tvMain.getText().toString();
            if (valS.contains("x")){
                tvHist.setText("tan(" + valS + ")");
            } else {
                tvMain.setText(String.valueOf(Math.tan(Double.parseDouble(valS))));
                tvHist.setText(tvHist.getText()  + "tan" + valS);
        }});
        butCtg.setOnClickListener(v -> {
            String valS = tvMain.getText().toString();
            if (valS.contains("x")){
                tvHist.setText("ctg(" + valS + ")");
            } else {
                tvMain.setText(String.valueOf(1 / Math.tan(Double.parseDouble(valS))));
                tvHist.setText(tvHist.getText()  + "ctg" + valS);
        }});

        butInv.setOnClickListener(v -> {
            String valS = tvMain.getText().toString();
            if (valS.contains("x")){
                tvHist.setText("1/" + valS);
            } else {
                tvMain.setText(String.valueOf(1 / Double.parseDouble(valS)));
                tvHist.setText(tvHist.getText()  + "1/" + valS);
        }});
        butFact.setOnClickListener(v -> {
            double val = Double.parseDouble(tvMain.getText().toString());
            double fact = factorial(val);
            tvMain.setText(String.valueOf(fact));
            tvHist.setText(val + "!");
        });
        butPower2.setOnClickListener(v -> {
            String valS = tvMain.getText().toString();
            if (valS.contains("x")){
                tvHist.setText("2^" + valS);
            } else {
                double d = Double.parseDouble(tvMain.getText().toString());
                double aux = d;
                double square = 1;
                while (aux > 0) {
                    square *= 2;
                    aux--;
                }
                tvMain.setText(String.valueOf(square));
                tvHist.setText(2 + "^" + d);
        }});
        butLn.setOnClickListener(v -> {
            String valS = tvMain.getText().toString();
            if (valS.contains("x")){
                tvHist.setText("ln(" + valS + ")");
            } else {
                tvMain.setText(String.valueOf(Math.log(Double.parseDouble(valS))));
                tvHist.setText(tvHist.getText()  + "ln" + valS);
            }
        });
        butLog.setOnClickListener(v -> {
            String valS = tvMain.getText().toString();
            if (valS.contains("x")){
                tvHist.setText("log(" + valS + ")");
            } else {
                tvMain.setText(String.valueOf(Math.log10(Double.parseDouble(valS))));
                tvHist.setText(tvHist.getText()  + "log" + valS);
            }
        });

        butEquals.setOnClickListener(v -> {
            String val = tvMain.getText().toString();
            if (val.contains("x")) {
                tvHist.setText(val);

            } else {
                double result = GraphActivity.evaluateExpression(val);
                tvMain.setText(String.valueOf(result));
                tvHist.setText(val);
            }
        });
    }

    double factorial(double n) {
        return (n == 1 || n == 0) ? 1 : n * factorial(n-1);
    }

    /*
    public static double evaluateExpression(final String str) {
        return new Object() {
            int pos = -1, chaR;
            void nextChar() {
                chaR = (++pos < str.length()) ? str.charAt(pos) : -1;
            }
            boolean functionToPerform(int nextCharacter) {
                while (chaR == ' ')
                    nextChar();
                if (chaR == nextCharacter) {
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
                double x = 0;
                int startPos = this.pos;

                if (functionToPerform('(')) {
                    x = parseExpression();
                    functionToPerform(')');
                }
                else if ((chaR >= '0' && chaR <= '9') || chaR == '.') {
                    while ((chaR >= '0' && chaR <= '9') || chaR == '.')
                        nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                }
                return x;
            }
        }.parse();
    }
    */
}