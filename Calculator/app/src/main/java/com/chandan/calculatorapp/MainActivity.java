package com.chandan.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView inputNumber = findViewById(R.id.inputNumber);
        final TextView result = findViewById(R.id.result);

        final Button button0 = findViewById(R.id.btn0);
        final Button button00 = findViewById(R.id.btn00);
        final Button button1 = findViewById(R.id.btn1);
        final Button button2 = findViewById(R.id.btn2);
        final Button button3 = findViewById(R.id.btn3);
        final Button button4 = findViewById(R.id.btn4);
        final Button button5 = findViewById(R.id.btn5);
        final Button button6 = findViewById(R.id.btn6);
        final Button button7 = findViewById(R.id.btn7);
        final Button button8 = findViewById(R.id.btn8);
        final Button button9 = findViewById(R.id.btn9);

        final Button buttonDot = findViewById(R.id.btnDot);
        final Button buttonEqual = findViewById(R.id.btnEqual);
        final Button buttonAdd = findViewById(R.id.btnAdd);
        final Button buttonMinus = findViewById(R.id.btnMinus);
        final Button buttonMultiply = findViewById(R.id.multiplyBtn);
        final Button buttonDivide = findViewById(R.id.divideBtn);

        final Button buttonBack = findViewById(R.id.backBtn);
        final Button buttonClear = findViewById(R.id.clearBtn);
        final Button buttonP = findViewById(R.id.btnP);



        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumber.setText(inputNumber.getText()+"0");
            }
        });
        button00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumber.setText(inputNumber.getText()+"00");
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumber.setText(inputNumber.getText()+"1");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumber.setText(inputNumber.getText()+"2");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumber.setText(inputNumber.getText()+"2");
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumber.setText(inputNumber.getText()+"3");
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumber.setText(inputNumber.getText()+"4");
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumber.setText(inputNumber.getText()+"5");
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumber.setText(inputNumber.getText()+"6");
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumber.setText(inputNumber.getText()+"7");
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumber.setText(inputNumber.getText()+"8");
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumber.setText(inputNumber.getText()+"9");
            }
        });
        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumber.setText(inputNumber.getText()+".");
            }
        });
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumber.setText("");
                result.setText("");
            }
        });
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = inputNumber.getText().toString();
                val = val.substring(0, val.length() - 1);
                inputNumber.setText(val);
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumber.setText(inputNumber.getText()+"+");
            }
        });
        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumber.setText(inputNumber.getText()+"-");
            }
        });
        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumber.setText(inputNumber.getText()+"*");
            }
        });
        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumber.setText(inputNumber.getText()+"÷");
            }
        });
        buttonP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumber.setText(inputNumber.getText()+"%");
            }
        });
        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = inputNumber.getText().toString();
                String rep = val.replace('÷', '/').replace('*', '*');
                double res = eval(rep);
                inputNumber.setText(String.valueOf(res));
                result.setText(val);
            }
        });



    }

    public static double eval(final String str){
        return new Object(){
            int pos = -1, ch;
            void nextChar(){
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }
            boolean eat (int charToEat){
                while (ch == ' ') nextChar();
                if (ch == charToEat){
                    nextChar();
                    return true;
                }
                return false;
            }
            double parse(){
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }
            double parseExpression(){
                double x = parseTerm();
                for (;;){
                    if (eat('+')) x += parseTerm(); //Addition
                    else if (eat('-')) x -= parseTerm(); //Subtraction
                    else return x;

                }
            }
            double parseTerm(){
                double x = parseFactor();
                for (;;){
                    if (eat('*')) x *= parseTerm(); //Multiplication
                    else if (eat('/')) x /= parseTerm(); //Division
                    else return x;
                }
            }
            double parseFactor(){
                if (eat('+')) return parseFactor(); //unary plus
                if (eat('-')) return -parseFactor(); //unary minus
                double x = 0;
                int startPos = this.pos;
                if ((ch >= '0' && ch <= '9') || ch == '.'){
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') {
                    while (ch >= 'a' && ch <= 'z') nextChar();
                }
                else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }
                return x;
            }

        }.parse();
    }
}