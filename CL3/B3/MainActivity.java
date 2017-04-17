package com.example.shubhankar.calculator;


        import android.content.Context;
        import android.support.v4.app.ActivityCompat;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.Button;
        import android.widget.TextView;
        import java.io.OutputStreamWriter;
        import java.lang.Math;
        import java.text.DecimalFormat;
        import java.util.ArrayList;
        import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    String DIGITS = "0123456789";
    String OPERATORS = "+-*/";
    TextView wew;
    TextView Result;
    double result;
    List<String> exp;
    String num;
    Double mem=0.0;
    DecimalFormat df = new DecimalFormat("@###########");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button0).setOnClickListener(this);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
        findViewById(R.id.button9).setOnClickListener(this);
        findViewById(R.id.buttonAdd).setOnClickListener(this);
        findViewById(R.id.buttonSubtract).setOnClickListener(this);
        findViewById(R.id.buttonMultiply).setOnClickListener(this);
        findViewById(R.id.buttonDivide).setOnClickListener(this);
        findViewById(R.id.buttonClear).setOnClickListener(this);
        findViewById(R.id.buttonDecimal).setOnClickListener(this);
        findViewById(R.id.buttonEquals).setOnClickListener(this);
        findViewById(R.id.buttonSin).setOnClickListener(this);
        findViewById(R.id.buttonCos).setOnClickListener(this);
        findViewById(R.id.buttontan).setOnClickListener(this);
        findViewById(R.id.buttonMplus).setOnClickListener(this);
        findViewById(R.id.buttonMminus).setOnClickListener(this);
        findViewById(R.id.buttonMR).setOnClickListener(this);
        findViewById(R.id.buttonMC).setOnClickListener(this);
        findViewById(R.id.buttonToFile).setOnClickListener(this);

        wew = (TextView) findViewById(R.id.textView);
        Result = (TextView) findViewById(R.id.Result);

        num = "";
        exp = new ArrayList<>();


        df.setMinimumFractionDigits(0);
        df.setMinimumIntegerDigits(1);
        df.setMaximumIntegerDigits(8);
    }

    public void onClick(View v) {
        String text = ((Button) v).getText().toString();
        if ((DIGITS.contains(text)) || (OPERATORS.contains(text)) || text.equals("sin") || text.equals("cos") || text.equals("tan")){
            wew.append(text);}

        if (DIGITS.contains(text) || text.equals(".")) {
            if (num.equals("") && text.equals(".")) {
                num = "0"+text;
            } else {
                num = num.concat(text);
            }
        } else if (OPERATORS.contains(text) || text.equals("sin") || text.equals("cos") || text.equals("tan")) {
            if (exp.size()!=1 && !text.equals("sin") && !text.equals("cos") && !text.equals("tan")) {
                exp.add(num);
                num = "";
            }
            exp.add(text);
        } else if (text.equals("=")) {
            exp.add(num);
            num="";
            exp = evaluate_exp(exp);
            result = Double.parseDouble(exp.get(0));
            Result.setText(Double.toString(result));
        } else if (text.equals("C")) {
            wew.setText("");
            Result.setText("");
            num = "";
            exp = new ArrayList<>();
        } else if(text.equals("M+"))
        {
            CharSequence cs=Result.getText();
            String s= cs.toString();
            double temp=Double.parseDouble(s);
            mem=mem+temp;
        }
        else if(text.equals("MR"))
        {
            wew.setText(Double.toString(mem));
        }
        else if(text.equals("M-"))
        {
            CharSequence cs=Result.getText();
            String s= cs.toString();
            double temp=Double.parseDouble(s);
            mem=mem-temp;
        }
        else if(text.equals("MC"))
        {
            mem=0.0;
        } else if (text.equals("To File")){
            writetoFile("hello.txt");
        }

    }

    public void writetoFile(String filename){
        try {
            OutputStreamWriter os=new OutputStreamWriter(openFileOutput(filename, Context.MODE_PRIVATE));
            os.write("\n"+wew.getText()+"="+Result.getText()+"\nMemory="+Double.toString(mem));
            os.close();

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> evaluate_exp(List<String> expr) {
        int i;
        double temp;
        while(expr.contains("+") || expr.contains("-") || expr.contains("*") || expr.contains("/") || expr.contains("sin") || expr.contains("cos") || expr.contains("tan")) {
            if (expr.contains("sin")) {
                i = expr.indexOf("sin");
                temp = Math.sin(Math.toRadians(Double.parseDouble(expr.get(i + 1))));
                expr.set(i, String.valueOf(temp));
                expr.remove(i+1);
            } else if (expr.contains("cos")) {
                i = expr.indexOf("cos");
                temp = Math.cos(Math.toRadians(Double.parseDouble(expr.get(i + 1))));
                expr.set(i, String.valueOf(temp));
                expr.remove(i+1);
            } else if (expr.contains("tan")) {
                i = expr.indexOf("tan");
                temp = Math.tan(Math.toRadians(Double.parseDouble(expr.get(i + 1))));
                expr.set(i, String.valueOf(temp));
                expr.remove(i+1);
            } else if (expr.contains("*")) {
                i = expr.indexOf("*");
                temp = Double.parseDouble(expr.get(i-1))*Double.parseDouble(expr.get(i+1));
                expr.set(i, String.valueOf(temp));
                expr.remove(i-1);
                expr.remove(i);
            } else if (expr.contains("/")) {
                i = expr.indexOf("/");
                temp = Double.parseDouble(expr.get(i-1))/Double.parseDouble(expr.get(i+1));
                expr.set(i, String.valueOf(temp));
                expr.remove(i-1);
                expr.remove(i);
            } else if (expr.contains("-")) {
                i = expr.indexOf("-");
                temp = Double.parseDouble(expr.get(i-1))-Double.parseDouble(expr.get(i+1));
                expr.set(i, String.valueOf(temp));
                expr.remove(i-1);
                expr.remove(i);
            } else if (expr.contains("+")) {
                i = expr.indexOf("+");
                temp = Double.parseDouble(expr.get(i-1))+Double.parseDouble(expr.get(i+1));
                expr.set(i, String.valueOf(temp));
                expr.remove(i-1);
                expr.remove(i);
            }
        }
        return expr;
    }

}
