package com.example.web_viewtask2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.web_viewtask2.databinding.ActivityMainBinding;
import com.example.web_viewtask2.models.FlagQuiz;
import com.example.web_viewtask2.utils.MySharedpreference;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MySharedpreference mySharedpreference;
    private List<FlagQuiz> flagAsia;
    private List<FlagQuiz> flagEurope;
    private List<FlagQuiz> flagAmerica;
    private List<FlagQuiz> flagAfrica;
    private List<FlagQuiz> flagQuizList;
    private String rules;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mySharedpreference = MySharedpreference.getInstance(this);
        gson = new Gson();

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.white));

        loadFlags();
        rules = "   Assalomu alaykum, aziz foydalanuvchi! Ushbu dasturimiz sizga ma`qul keladi degan umiddamiz." + "\n" + "   Ushbu dasturdan foydalanishdan oldin o`yin qoidalari bilan tanishib chiqishni maslahat beramiz." + "\n" + "   1.Siz asosiy oynada o`zingiz xohlagan bitta qit`a nomini tanlashingiz kerak bo`ladi." + "\n" + "   2.Sizga tanlagan qit`angizga mos davlat bayrog`laridan biri ko`rsatiladi. Va siz shu davlat poytaxti nomini topib, pastda berilgan tugmalarni bosish orqali poytaxt nomini kiritishingiz kerak bo`ladi." + "\n" + "   3.Agar siz poytaxt nomini to`g`ri topsangiz sizga 5 tanga miqdorida ball beriladi. " + "\n" + "   4.Bunda sizga ba`zi yordamlar mavjud. Ya`ni siz bayroq rasmi pastidagi chapda turgan so`roq belgisini bossangiz, sizga poytaxt nomi yoziladigan joyda poytaxt nomining birinchi harfi ko`rsatib beriladi. Shuningdek, agarda siz bayroq qaysi davlatga tegishli ekanligini bilmoqchi bo`lsangiz, so`roq belgisining o`ng tomonidagi lupa ustiga bosganingizda sizga TOAST da bayroq tegishli bo`lgan davlat nomi ko`rsatiladi. Shunga mos ravishda har ikkala tugma bir marotaba bosilganda ballingizdan 5 tanga miqdorida ball olib qolinadi" + "\n" + "   5.Siz barcha qit`alarga tegishli poytaxt nomlarini to`gri topib jami 25 ball to`plashingiz kerak bo`ladi." + "\n" + "   6.Sizda internet aloqasi mavjud bo`lishi shart, chunki rasmlar internet saytlaridan olinadi." + "\n" + "   7.Sizga omad yor bo`lsin!!!";

        binding.rules1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Rules.class);
                startActivity(intent);
            }
        });
        binding.asia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("code", "1");
                startActivity(intent);
                finish();
            }
        });
        binding.europe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("code", "2");
                startActivity(intent);
                finish();
            }
        });
        binding.america.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("code", "3");
                startActivity(intent);
                finish();
            }
        });
        binding.africa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("code", "4");
                startActivity(intent);
                finish();
            }
        });
    }

    private void loadFlags() {
        flagAsia = new ArrayList<>();
        flagAsia.add(new FlagQuiz("Anqara", R.drawable.turkey, "Turkiya"));
        flagAsia.add(new FlagQuiz("Nyu Deli", R.drawable.india, "Hindiston"));
        flagAsia.add(new FlagQuiz("Tehron", R.drawable.iran, "Eron"));
        flagAsia.add(new FlagQuiz("Seul", R.drawable.korea, "Janubiy Koreya"));
        flagAsia.add(new FlagQuiz("Toshkent", R.drawable.uzbekistan, "O`zbekiston"));

        flagEurope = new ArrayList<>();
        flagEurope.add(new FlagQuiz("London", R.drawable.great_britain, "Buyuk Britaniya"));
        flagEurope.add(new FlagQuiz("Kopengagen", R.drawable.denmark, "Daniya"));
        flagEurope.add(new FlagQuiz("Afina", R.drawable.greece, "Gretsiya"));
        flagEurope.add(new FlagQuiz("Parij", R.drawable.france, "Fransiya"));
        flagEurope.add(new FlagQuiz("Lissabon", R.drawable.portugal, "Portugaliya"));

        flagAmerica = new ArrayList<>();
        flagAmerica.add(new FlagQuiz("Vashington", R.drawable.usa, "AQSH"));
        flagAmerica.add(new FlagQuiz("Ottava", R.drawable.canada, "Kanada"));
        flagAmerica.add(new FlagQuiz("Mexiko", R.drawable.mexico, "Meksika"));
        flagAmerica.add(new FlagQuiz("Brazilia", R.drawable.brazilia, "Braziliya"));
        flagAmerica.add(new FlagQuiz("Buenos Ayres", R.drawable.argentina, "Argentina"));

        flagAfrica = new ArrayList<>();
        flagAfrica.add(new FlagQuiz("Qohira", R.drawable.misr, "Misr"));
        flagAfrica.add(new FlagQuiz("Jazoir", R.drawable.jazoir, "Jazoir"));
        flagAfrica.add(new FlagQuiz("Antananarivu", R.drawable.madagaskar, "Madagaskar"));
        flagAfrica.add(new FlagQuiz("Rabot", R.drawable.marokash, "Marokash"));
        flagAfrica.add(new FlagQuiz("Abuja", R.drawable.nigeria, "Nigeriya"));

        String asia = gson.toJson(flagAsia);
        String europe = gson.toJson(flagEurope);
        String america = gson.toJson(flagAmerica);
        String africa = gson.toJson(flagAfrica);

        mySharedpreference.setList("Asia", asia);
        mySharedpreference.setList("Europe", europe);
        mySharedpreference.setList("America", america);
        mySharedpreference.setList("Africa", africa);
    }
}