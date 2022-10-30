package id.ac.umn.leocandraseldi_44308_tugas1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    Button c, ce, one, two, three, four, five, six, seven, eight, nine, zero, tambah, kali, kurang, slash, plusminus, koma, back, hasil;
    TextView input, cara;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c = findViewById(R.id.c);
        ce = findViewById(R.id.ce);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        zero = findViewById(R.id.zero);
        tambah = findViewById(R.id.tambah);
        kurang = findViewById(R.id.kurang);
        kali = findViewById(R.id.kali);
        slash =  findViewById(R.id.slash);
        plusminus = findViewById(R.id.plusminus);
        koma = findViewById(R.id.koma);
        back = findViewById(R.id.back);
        hasil = findViewById(R.id.hasil);

        input = findViewById(R.id.input);
        cara = findViewById(R.id.cara);

        one.setOnClickListener(v -> {
            String val = input.getText().toString();
            input.setText(val + one.getText().toString());
        });
        two.setOnClickListener(v -> {
            String val = input.getText().toString();
            input.setText(val + two.getText().toString());
        });
        three.setOnClickListener(v -> {
            String val = input.getText().toString();
            input.setText(val + three.getText().toString());
        });
        four.setOnClickListener(v -> {
            String val = input.getText().toString();
            input.setText(val + four.getText().toString());
        });
        five.setOnClickListener(v -> {
            String val = input.getText().toString();
            input.setText(val + five.getText().toString());
        });
        six.setOnClickListener(v -> {
            String val = input.getText().toString();
            input.setText(val + six.getText().toString());
        });
        seven.setOnClickListener(v -> {
            String val = input.getText().toString();
            input.setText(val + seven.getText().toString());
        });
        eight.setOnClickListener(v -> {
            String val = input.getText().toString();
            input.setText(val + eight.getText().toString());
        });
        nine.setOnClickListener(v -> {
            String val = input.getText().toString();
            input.setText(val + nine.getText().toString());
        });
        zero.setOnClickListener(v -> {
            String val = input.getText().toString();
            input.setText(val + zero.getText().toString());
        });
        kali.setOnClickListener(v -> {
            String val = input.getText().toString();
            input.setText(val+ kali.getText().toString());
        });
        plusminus.setOnClickListener(v ->{
            String val = input.getText().toString();
            input.setText(kurang.getText().toString()+ "("+val+")");
        });

        ce.setOnClickListener(v -> {
            input.setText("");
            cara.setText("");
        });
        c.setOnClickListener(v -> {
            String val = input.getText().toString();
            if (!val.equals("")) {
                val = val.substring(0, val.length() - 1);
                input.setText(val);
            }

        });
        back.setOnClickListener(v -> {
            String val = input.getText().toString();
            if (!val.equals("")) {
                val = val.substring(0, val.length() - 1);
                input.setText(val);
            }

        });
        kurang.setOnClickListener(v -> {
            String val = input.getText().toString();
            input.setText(val + kurang.getText().toString());
        });
        tambah.setOnClickListener(v -> {
            String val = input.getText().toString();
            input.setText(val + tambah.getText().toString());
        });
        koma.setOnClickListener(v -> {
            String val = input.getText().toString();
            input.setText(val + koma.getText().toString());
        });
        slash.setOnClickListener(v -> {
            String val = input.getText().toString();
            input.setText(val + slash.getText().toString());
        });
        hasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = input.getText().toString();
                String replacedString = val.replace('÷', '/').replace('X', '*');
                double result = hitung(replacedString);
                String r = String.valueOf(result);
                input.setText(r);
                cara.setText(val);
            }

            private double hitung(final String str) {
                return new Object() {
                    int pos = -1, ch;
                    void nextChar() {
                        ch = (++pos < str.length()) ? str.charAt(pos) : -1;
                    }
                    boolean eat(int charToEat) {
                        while (ch == ' ') nextChar();
                        if (ch == charToEat) {
                            nextChar();
                            return true;
                        }
                        return false;
                    }

                    double parse() {
                        nextChar();
                        double x = parse2();
                        if (pos < str.length())
                            throw new RuntimeException("Unexpected: " + (char) ch);
                        return x;
                    }

                    double parse2() {
                        double x = parse3();
                        for (; ; ) {
                            if (eat('+')) x += parse3(); //
                            else if (eat('-')) x -= parse3();
                            else return x;
                        }
                    }

                    double parse3() {
                        double x = parse4();
                        for (; ; ) {
                            if (eat('*')) x *= parse4();
                            else if (eat('/')) x /= parse4();
                            else return x;
                        }
                    }

                    double parse4() {
                        if (eat('+')) return parse4();
                        if (eat('-')) return -parse4();

                        double x;
                        int startPos = this.pos;
                        if (eat('(')) { // parentheses
                            x = parse2();
                            eat(')');
                        } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                            while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                            x = Double.parseDouble(str.substring(startPos, this.pos));
                        }  else {
                            throw new RuntimeException("Unexpected: " + (char)ch);
                        }

                        if (eat('^')) x = Math.pow(x, parse4());

                        return x;
                    }


                }.parse();


            }
        });
    }
}




