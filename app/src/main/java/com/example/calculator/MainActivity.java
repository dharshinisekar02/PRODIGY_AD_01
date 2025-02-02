package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTxt,SolutionTxt;
    MaterialButton btnC,btnOpenbracket,btnClosebracket;

    MaterialButton btnDot,btnAC,btnadd,btnsub,btndiv,btnmul,btneq;
    MaterialButton btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        resultTxt =findViewById(R.id.resultTxt);
        SolutionTxt=findViewById(R.id.SolutionTxt);
        btnId(btnC,R.id._button_c);
        btnId(btnOpenbracket,R.id._button_openbracket);
        btnId(btnClosebracket,R.id._button_closebracket);
        btnId(btn0,R.id._button_0);
        btnId(btn1,R.id._button_1);
        btnId(btn2,R.id._button_2);
        btnId(btn3,R.id._button_3);
        btnId(btn4,R.id._button_4);
        btnId(btn5,R.id._button_5);
        btnId(btn6,R.id._button_6);
        btnId(btn7,R.id._button_7);
        btnId(btn8,R.id._button_8);
        btnId(btn9,R.id._button_9);
        btnId(btnDot,R.id._button_dot);
        btnId(btnadd,R.id._button_add);
        btnId(btnsub,R.id._button_subtract);
        btnId(btndiv,R.id._button_divide);
        btnId(btnmul,R.id._button_multiply);
        btnId(btnAC,R.id._button_allclear);
        btnId(btneq,R.id._button_equals);




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;});
    }
    void btnId(MaterialButton btn,int id){

    btn=findViewById(id);
    btn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        MaterialButton button=(MaterialButton) v;
        String buttonTxt=button.getText().toString();
        String dataConcat=SolutionTxt.getText().toString();

        if(buttonTxt.equals("AC")){
            SolutionTxt.setText("");
            resultTxt.setText("0");
            return;
        }
        if(buttonTxt.equals("=")){
            SolutionTxt.setText(resultTxt.getText()); return;
        }

        if(buttonTxt.equals("C")){
            dataConcat=dataConcat.substring(0,dataConcat.length()-1);
        }
        else{
            dataConcat=dataConcat+buttonTxt;
        }

        SolutionTxt.setText(dataConcat);
        String finalResult=getResult(dataConcat);
        if(!finalResult.equals("Err")){
            resultTxt.setText(finalResult);
        }


    }
    String getResult(String data){
        try{
            Context context= Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initSafeStandardObjects();
            String finalResult =context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")) {
                finalResult = finalResult.replace(".0", "");

            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }
}