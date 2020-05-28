package com.example.projectandroid;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.Expression;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    Unbinder unbinder;
    @BindView(R.id.view_rezultat)

    TextView rezultatFinal;
    @BindView(R.id.view_prevEcuatie)

    TextView input;
    @BindView(R.id.view_radDegSwitch)

    TextView tastaSwitchRadDeg;
    @BindView(R.id.backspace)

    TextView tastaResetCalc;
    @BindView(R.id.tastaReset)

    TextView tastaC;

    private int count = 0;
    private boolean radOrDeg = false;
    private String placeHolder = "";
    private String expresieFinala = "";
    private String radOrDegValue = "deg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this); // am folosit butterknife pentru a views-urile cu ce avem in backend

        Log.d("CALCULATOR - STATUS", "DESCHIS");

        rezultatFinal.setText("0");
    }

    // Folosirea bind-urilor de pe taste pentru cifre

    @OnClick({R.id.tasta0}) void tasta0() {
        input.setText(input.getText() + "0");
        functieCalcule();
    }

    @OnClick({R.id.tasta1}) void tasta1() {
        if (prezentaParanteza()) {
            input.setText(input.getText() + "x1");
        } else
            input.setText(input.getText() + "1");
        functieCalcule();
    }

    @OnClick({R.id.tasta2}) void tasta2() {
        if (prezentaParanteza()) {
            input.setText(input.getText() + "x2");
        } else
            input.setText(input.getText() + "2");
        functieCalcule();
    }

    @OnClick({R.id.tasta3}) void tasta3() {
        if (prezentaParanteza()) {
            input.setText(input.getText() + "x3");
        } else
            input.setText(input.getText() + "3");
        functieCalcule();
    }

    @OnClick({R.id.tasta4}) void tasta4() {
        if (prezentaParanteza()) {
            input.setText(input.getText() + "x4");
        } else
            input.setText(input.getText() + "4");
        functieCalcule();
    }

    @OnClick({R.id.tasta5}) void tasta5() {
        if (prezentaParanteza()) {
            input.setText(input.getText() + "x5");
        } else
            input.setText(input.getText() + "5");
        functieCalcule();
    }

    @OnClick({R.id.tasta6}) void tasta6() {
        if (prezentaParanteza()) {
            input.setText(input.getText() + "x6");
        } else
            input.setText(input.getText() + "6");
        functieCalcule();
    }

    @OnClick({R.id.tasta7}) void tasta7() {
        if (prezentaParanteza()) {
            input.setText(input.getText() + "x7");
        } else
            input.setText(input.getText() + "7");
        functieCalcule();
    }

    @OnClick({R.id.tasta8}) void tasta8() {
        if (prezentaParanteza()) {
            input.setText(input.getText() + "x8");
        } else
            input.setText(input.getText() + "8");
        functieCalcule();
    }

    @OnClick({R.id.tasta9}) void tasta9() {
        if (prezentaParanteza()) {
            input.setText(input.getText() + "x9");
        } else
            input.setText(input.getText() + "9");
        functieCalcule();
    }

    // Operatii matematice simple

    @OnClick({R.id.adunare}) void adunare() {
        operatorFunction("+");
    }

    @OnClick({R.id.scadere}) void scadere() {
        operatorFunction("-");
    }

    @OnClick({R.id.procent}) void procent() {
        input.setText(input.getText() + "%");
        functieCalcule();
    }

    @OnClick({R.id.inmultire}) void inmultire() { operatorFunction("x"); }

    @OnClick({R.id.impartire}) void impartire() { operatorFunction("÷"); }

    @OnClick({R.id.egal}) void egal() {
        if (input.getText().toString().length() != 0) {
            functieCalcule();
        }
    }

    // Paranteze

    @OnClick({R.id.parantezaDeschisa}) void parantezaDeschisa(){
        if (precedatDeCaracter()) {
            input.setText(input.getText() + "x(");
        } else
            input.setText(input.getText() + "(");
    }

    @OnClick({R.id.parantezaInchisa}) void parantezaInchisa(){
        input.setText(input.getText() + ")");
        functieCalcule();
    }

    // Functii trigonometrice
    @OnClick({R.id.sinus}) void sinus(){
        if (precedatDeCaracter()) {
            input.setText(input.getText() + "xsin(");
        } else
            input.setText(input.getText() + "sin(");
    }

    @OnClick({R.id.cosinus}) void cosinus(){
        if (precedatDeCaracter()) {
            input.setText(input.getText() + "xcos(");
        } else
            input.setText(input.getText() + "cos(");
    }

    @OnClick({R.id.tangenta}) void tangenta(){
        if (precedatDeCaracter()) {
            input.setText(input.getText() + "xtan(");
        } else
            input.setText(input.getText() + "tan(");
    }

    @OnClick({R.id.view_radDegSwitch}) void radDegSwitch(){
        if (radOrDeg) {
            tastaSwitchRadDeg.setText("rad");
            radOrDegValue = "rad";
            radOrDeg = false;
        } else {
            tastaSwitchRadDeg.setText("deg");
            radOrDegValue = "deg";
            radOrDeg = true;
        }

        //re-calculeaza input-ul daca acesta contine functii trigonometrice
        String placeHolder = input.getText().toString();
        if (placeHolder.contains("sin(") || placeHolder.contains("cos(") || placeHolder.contains("tan(")) {
            functieCalcule();
        }
    }


    // Alte functii matematice

    @OnClick({R.id.pi}) void pi(){
        if (precedatDeCaracter()) {
            input.setText(input.getText() + "xπ");
        } else
            input.setText(input.getText() + "π");
        functieCalcule();
    }

    @OnClick({R.id.unuSupraX}) void unuSupraX(){
        input.setText(input.getText() + "\u207B\u00B9");
        functieCalcule();
    }

    @OnClick({R.id.putere}) void putere(){
        input.setText(input.getText() + "\u005E");
    }

    @OnClick({R.id.logaritm}) void logaritm(){
        if (precedatDeCaracter()) {
            input.setText(input.getText() + "xlg(");
        } else
            input.setText(input.getText() + "lg(");
    }

    @OnClick({R.id.logaritmNatural}) void logaritmNatural(){
        if (precedatDeCaracter()) {
            input.setText(input.getText() + "xln(");
        } else
            input.setText(input.getText() + "ln(");
    }

    @OnClick({R.id.tastaE}) void tastaE(){
        if (precedatDeCaracter()) {
            input.setText(input.getText() + "xe^");
        } else
            input.setText(input.getText() + "e^");
        functieCalcule();
    }

    @OnClick({R.id.radical}) void radical(){
        if (precedatDeCaracter()) {
            input.setText(input.getText() + "x√");
        } else
            input.setText(input.getText() + "√");
    }

    @OnClick({R.id.factorial}) void factorial(){
        input.setText(input.getText() + "!");
        functieCalcule();
    }

    // alte taste

    @OnClick({R.id.virgula}) void virgula(){
        if (count == 0 && input.length() != 0) {
            input.setText(input.getText() + ".");
            count++;
        }
    }

    @OnClick({R.id.backspace}) void backspace(){
        backspaceFunction();
    }

    @OnClick({R.id.tastaReset}) void tastaReset(){
        input.setText("");
        rezultatFinal.setText("0");
    }

    public boolean precedatDeCaracter() {
        String placeHolder = input.getText().toString();

        if (placeHolder.length() != 0) {
            String last = placeHolder.substring(placeHolder.length() - 1);

            if (last.equals(")")
                    || last.equals("1")
                    || last.equals("2")
                    || last.equals("3")
                    || last.equals("4")
                    || last.equals("5")
                    || last.equals("6")
                    || last.equals("7")
                    || last.equals("8")
                    || last.equals("9")) {
                return true;
            }
        }
        return false;
    }

    public boolean prezentaParanteza() {
        String placeHolder = input.getText().toString();

        if (placeHolder.length() != 0) {

            String last = placeHolder.substring(placeHolder.length() - 1);

            if (last.equals(")") || last.equals("π") || last.equals("%")) {
                return true;
            }
        }

        return false;
    }

    private void operatorFunction(String operation) {
        String placeHolder = input.getText().toString();

        if (placeHolder.length() != 0) {
            placeHolder += operation;

            final char c = placeHolder.charAt(placeHolder.lastIndexOf(operation) - 1);
            if (c == 'x' || c == '+' || c == '÷' || c == '-') {
                placeHolder = placeHolder.substring(0, placeHolder.length() - 2) + operation;
            }

            input.setText(placeHolder);
            count = 0;
        }
    }

    private void backspaceFunction() {

        placeHolder = input.getText().toString();

        if (placeHolder.length() > 0) {

            if (placeHolder.endsWith(".")) {
                count = 0;
            }

            String newText = placeHolder;

            if (newText.endsWith("sin(") || newText.endsWith("cos(") || newText.endsWith("tan(") || newText.endsWith("log("))
                newText = newText.substring(0, newText.length() - 4);

            else if (newText.endsWith("ln(") || newText.endsWith("lg("))
                newText = newText.substring(0, newText.length() - 3);

            else if (newText.endsWith("e^"))
                newText = newText.substring(0, newText.length() - 2);

            else
                newText = placeHolder.substring(0, placeHolder.length() - 1);

            input.setText(newText);

            if (newText.isEmpty())
                rezultatFinal.setText("0");
        }
    }

    private void functieCalcule() {
        expresieFinala = input.getText().toString();

        if (expresieFinala.length() != 0) {

            if (expresieFinala.contains("\u207B\u00B9")) {
                expresieFinala = expresieFinala.replace("\u207B\u00B9", "^-1");
            }

            if (expresieFinala.contains("lg")) {
                expresieFinala = expresieFinala.replace("lg(", "log10(");
            }

            if (expresieFinala.contains("\u02B8")) {
                expresieFinala = expresieFinala.replaceAll("\u02B8", "^");
            }

            if (expresieFinala.contains("π"))
                expresieFinala = expresieFinala.replaceAll("π", "pi");

            if (expresieFinala.endsWith("e^"))
                expresieFinala = expresieFinala.replace("e^", "e");

            if (expresieFinala.contains("x")) {
                expresieFinala = expresieFinala.replaceAll("x", "*");
            }

            if (expresieFinala.contains("÷")) {
                expresieFinala = expresieFinala.replaceAll("÷", "/");
            }

            if (expresieFinala.contains("√")) {
                expresieFinala = expresieFinala.replaceAll("√", "sqrt(");
            }

            if (expresieFinala.contains("sqrt("))
                expresieFinala = getCorrectSqrtFormat(expresieFinala);

            if (expresieFinala.contains("sin(") || expresieFinala.contains("cos(") || expresieFinala.contains("tan(")) {
                switch (radOrDegValue) {
                    case "rad":
                        expresieFinala = getResult_RadOrDegree(expresieFinala, "*[rad]");
                        break;
                    case "deg":
                        expresieFinala = getResult_RadOrDegree(expresieFinala, "*[deg]");
                        break;
                }
            }

            if (expresieFinala.contains("(") && !expresieFinala.substring(expresieFinala.lastIndexOf("("), expresieFinala.length()).contains(")")) {
                expresieFinala += ")";
            }

            Log.d("CALCULATOR - ECUATIE", expresieFinala);

            // Facem calculele utilizand mxparser
            Expression e1 = new Expression(expresieFinala);
            String answer = String.valueOf(e1.calculate());

            Log.d("CALCULATOR - RASPUNS", answer);

            if (answer.equals("NaN"))
                answer = "Error :(";

            rezultatFinal.setText(" = " + answer);
        }
    }

    public String getCorrectSqrtFormat(String expression) {
        int lastFoundedSqrtPosition = 0;
        char[] operatori = {'+', '-', '*', '/'};

        for (int i = 0; i < expression.length(); i++) {
            for (int j = 0; j < operatori.length; j++) {

                if (expression.charAt(i) == operatori[j]) {
                    String expression_substring = expression.substring(lastFoundedSqrtPosition, i);

                    if (expression_substring.contains("sqrt(")) {
                        String radicalHolder = expression_substring + ")";
                        expression = expression.replace(expression_substring, radicalHolder);
                    }
                    lastFoundedSqrtPosition = i;
                }
            }
        }
        return expression;
    }

    public String getResult_RadOrDegree(String expression, String operation) {
        String[] functiiTrigonometrice = {"sin(", "cos(", "tan("};
        int lastFoundedTrigonometricalFunctionPos = -1;
        String placeHolder = expression;

        for (int i = 0; i < expression.length(); i++)
        {
            for (int j = 0; j < functiiTrigonometrice.length; j++)
            {
                if (placeHolder.indexOf(functiiTrigonometrice[j]) == i) {
                    String expression_substring = placeHolder.substring(i);

                    if ((expression_substring.contains("sin(")
                            || expression_substring.contains("cos(")
                            || expression_substring.contains("tan("))
                            && i > lastFoundedTrigonometricalFunctionPos) {

                        if (expression_substring.contains(")")) {

                            String substring_functieTrig = expression_substring.substring(0, expression_substring.indexOf(")"));
                            expression = expression.replace(substring_functieTrig, substring_functieTrig + operation);

                        } else if (!expression_substring.contains(")")) {

                            String substring_functieTrig = expression_substring.substring(0);
                            expression = expression.replace(substring_functieTrig, substring_functieTrig + operation);

                        }

                        lastFoundedTrigonometricalFunctionPos = i;
                    }
                }
            }
        }
        return expression;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        Log.d("CALCULATOR - STATUS", "DESTROY");

    }
}