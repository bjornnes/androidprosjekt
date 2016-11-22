package bjornnes.prosjekt2;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.Locale;

public class Hangman extends AppCompatActivity {
    private ImageButton ib;
    private Locale locale;
    //private LocaleList locales;
    //private String[] supportedLocales = {"no","en"};
    private Configuration conf;
    private Resources res;



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        ib = (ImageButton) findViewById(R.id.changeLang);
        res = getBaseContext().getResources();
        conf = res.getConfiguration();
        //locales = conf.getLocales();
        //locale = locales.getFirstMatch(supportedLocales);
        locale = conf.locale;
        setLocaleIcon(locale);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onChangeLang(View view){
        if(locale.toString().equalsIgnoreCase("nb_NO")||locale.toString().equalsIgnoreCase("no")){
            locale = new Locale("en");
            ib.setImageDrawable(getDrawable(R.drawable.rsz_englishflag2png));
            conf.setLocale(locale);
            res.updateConfiguration(conf, res.getDisplayMetrics());
        }else if(locale.toString().equalsIgnoreCase("en_US")||locale.toString().equalsIgnoreCase("en")){
            locale = new Locale("no");
            ib.setImageDrawable(getDrawable(R.drawable.rsznorwegianflagsmall));
            conf.setLocale(locale);
            res.updateConfiguration(conf, res.getDisplayMetrics());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setLocaleIcon(Locale locale){
        if(locale.getCountry().equalsIgnoreCase("no")){
            ib.setImageDrawable(getDrawable(R.drawable.rsznorwegianflagsmall));
        }else if(locale.getCountry().equalsIgnoreCase("en")){
            ib.setImageDrawable(getDrawable(R.drawable.rsz_englishflag2png));
        }
    }
}
