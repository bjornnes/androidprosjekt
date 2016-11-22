package bjornnes.prosjekt2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> ord;
    private static String valgtOrd;
    private static int antRiktigBokst;
    private static int antFeilBokst;
    private ArrayList<Character> tidl;
    private int antRiktig;
    private int antFeil;
    private TextView antRiktigTV;
    private TextView antFeilTV;
    private TextView antSpiltTV;
    private TextView brukt;
    //asdasdasdas

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ord = new ArrayList<String>();
        ord.add("asjdnakkkk kasmdkms".toUpperCase());
        ord.add("ordto tre");
        valgtOrd = ord.get(0);
        tegnUnderstreker(valgtOrd);
        antRiktigBokst = 0;
        antFeilBokst = 0;
        tidl = new ArrayList<Character>();
        antRiktig = 0;
        antFeil = 0;
        antRiktigTV = (TextView) findViewById(R.id.antRiktig);
        antFeilTV = (TextView) findViewById(R.id.antFeil);
        antSpiltTV = (TextView) findViewById(R.id.ordSpilt);
        brukt = (TextView)findViewById(R.id.brukteBokstaver);
    }

    public void tegnUnderstreker(String valgtOrd){
        TextView understrek2 = (TextView)findViewById(R.id.understrek2);
        String res = "";

        for(int i = 0; i < valgtOrd.length(); i++){
            char a = valgtOrd.charAt(i);
            if(a==' ') {
                res += " ";
            }else{
                res+="_";
            }

        }
        understrek2.setText(res);
    }

    public void gjett(View v){
        EditText et = (EditText)findViewById(R.id.input);
        String gjetning = et.getText().toString();
        TextView tv = (TextView)findViewById(R.id.understrek2);
        StringBuilder gjettet = new StringBuilder(tv.getText().toString());
        try{
            char a = gjetning.toUpperCase().charAt(0);
            System.out.println(a);
            if(a>=65 && a<=90){
                boolean sjekk = true;
                for(int j=0; j<tidl.size();j++){
                    if(tidl.get(j)==a){
                        sjekk = false;
                    }
                }
                if(sjekk) {
                    tidl.add(a);
                    System.out.println("Går inn 1");
                    if (valgtOrd.contains("" + a)) {
                        System.out.println("Contains");
                        for (int i = 0; i < valgtOrd.length(); i++) {
                            char b = valgtOrd.charAt(i);
                            if (a == b) {
                                gjettet.setCharAt(i, a);
                                antRiktigBokst++;
                            }
                        }
                        tv.setText(gjettet);

                    }else{
                        String ny ="";
                        if(brukt.getText().toString().length()== 10){
                            ny+="\n";
                        }
                        ny+=a+" ";
                        brukt.setText(brukt.getText().toString()+ny);
                        antFeilBokst++;
                    }
                    if (antRiktigBokst == valgtOrd.length() - 1) {
                        Toast.makeText(this, "Riktig", Toast.LENGTH_SHORT).show();
                        antRiktig++;
                        antRiktigTV.setText(""+antRiktig);
                        int sum = antRiktig+antFeil;
                        antSpiltTV.setText(""+sum);
                        nesteOrd();
                    }else if(antFeilBokst == 10){
                        Toast.makeText(this, "Feil", Toast.LENGTH_SHORT).show();
                        antFeil++;
                        antFeilTV.setText(""+antFeil);
                        int sum = antRiktig+antFeil;
                        antSpiltTV.setText(""+sum);
                        nesteOrd();
                    }
                }
            }else{
                Toast.makeText(this,"Please only enter letters from A-Z",Toast.LENGTH_SHORT).show();
            }
        }catch (StringIndexOutOfBoundsException e){
            Toast.makeText(this,"Please enter a letter from A-Z",Toast.LENGTH_SHORT).show();
        }
        et.setText("");
    }

    public void nesteOrd(){
        valgtOrd = ord.get(1).toUpperCase();
        System.out.println(valgtOrd);
        tegnUnderstreker(valgtOrd);
        antRiktigBokst = 0;
        antFeilBokst = 0;
        tidl = new ArrayList<Character>();
        brukt.setText("");
    }
}
