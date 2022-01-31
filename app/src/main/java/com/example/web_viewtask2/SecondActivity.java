package com.example.web_viewtask2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.web_viewtask2.databinding.ActivitySecondBinding;
import com.example.web_viewtask2.models.FlagQuiz;
import com.example.web_viewtask2.utils.MySharedpreference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivitySecondBinding binding;
    private MySharedpreference mySharedpreference;
    private Gson gson;
    private int index = 0;
    private List<FlagQuiz> AsiaList;
    boolean f;
    boolean g;
    private List<FlagQuiz> EuropeList;
    private List<FlagQuiz> AmericaList;
    private List<FlagQuiz> AfricaList;
    private String code = "";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mySharedpreference = MySharedpreference.getInstance(this);
        gson = new Gson();
        AsiaList = new ArrayList<>();
        EuropeList = new ArrayList<>();
        AmericaList = new ArrayList<>();
        AfricaList = new ArrayList<>();

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(SecondActivity.this, R.color.white));

        Intent intent = getIntent();
        code = intent.getStringExtra("code");

        binding.capital.setOnClickListener(view -> {
            String s = binding.coins.getText().toString();
            if (Integer.parseInt(s) >= 5) {
                setFirstCharOfCapital();
            } else {
                Toast.makeText(SecondActivity.this, "Sizda yetarli tanga mavjud emas!", Toast.LENGTH_SHORT).show();
            }
        });
        binding.country.setOnClickListener(view -> {
            toasterOfCountry();
        });

        if (Integer.parseInt(code) == 1) {
            binding.coins.setText("10");
            binding.theme.setText("Asian countries");
            String asia = mySharedpreference.getList("Asia");

            Type type = new TypeToken<List<FlagQuiz>>() {
            }.getType();
            AsiaList = gson.fromJson(asia, type);
            setQuestions(AsiaList);
        } else if (Integer.parseInt(code) == 2) {
            binding.coins.setText("10");
            binding.theme.setText("European countries");
            String europe = mySharedpreference.getList("Europe");

            Type type = new TypeToken<List<FlagQuiz>>() {
            }.getType();
            EuropeList = gson.fromJson(europe, type);
            setQuestions(EuropeList);
        } else if (Integer.parseInt(code) == 3) {
            binding.coins.setText("10");
            binding.theme.setText("American countries");
            String america = mySharedpreference.getList("America");

            Type type = new TypeToken<List<FlagQuiz>>() {
            }.getType();
            AmericaList = gson.fromJson(america, type);
            setQuestions(AmericaList);
        } else if (Integer.parseInt(code) == 4) {
            binding.coins.setText("10");
            binding.theme.setText("African countries");
            String africa = mySharedpreference.getList("Africa");

            Type type = new TypeToken<List<FlagQuiz>>() {
            }.getType();
            AfricaList = gson.fromJson(africa, type);
            setQuestions(AfricaList);
        }
    }

    private void toasterOfCountry() {
        if (Integer.parseInt(binding.coins.getText().toString()) >= 5) {
            switch (code) {
                case "1":
                    Toast.makeText(SecondActivity.this, AsiaList.get(index).getCountry(), Toast.LENGTH_SHORT).show();
                    binding.coins.setText(String.valueOf(Integer.parseInt(binding.coins.getText().toString()) - 5));
                    break;
                case "2":
                    Toast.makeText(SecondActivity.this, EuropeList.get(index).getCountry(), Toast.LENGTH_SHORT).show();
                    binding.coins.setText(String.valueOf(Integer.parseInt(binding.coins.getText().toString()) - 5));
                    break;
                case "3":
                    Toast.makeText(SecondActivity.this, AmericaList.get(index).getCountry(), Toast.LENGTH_SHORT).show();
                    binding.coins.setText(String.valueOf(Integer.parseInt(binding.coins.getText().toString()) - 5));
                    break;
                case "4":
                    Toast.makeText(SecondActivity.this, AfricaList.get(index).getCountry(), Toast.LENGTH_SHORT).show();
                    binding.coins.setText(String.valueOf(Integer.parseInt(binding.coins.getText().toString()) - 5));
                    break;
            }
        } else {
            Toast.makeText(this, "Sizda yetarli tanga mavjud emas!", Toast.LENGTH_SHORT).show();
        }
    }

    private void setFirstCharOfCapital() {
        Button btn;
        Button topButton;
        String c = "";
        boolean a1 = false;
        int a2 = -1;
        switch (code) {
            case "1":
                String capital = AsiaList.get(index).getCapital();
                String a = capital.substring(0, 1);
                int childCount = binding.layout2.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    Button btn1 = (Button) binding.layout2.getChildAt(i);
                    String text = (String) btn1.getText();
                    if (a.equals(text)) {
                        a1 = true;
                        a2 = i;
                        break;
                    }
                }
                if (a1) {
                    btn = (Button) binding.layout2.getChildAt(a2);
                    c = btn.getText().toString();
                    btn.setVisibility(View.INVISIBLE);
                    binding.coins.setText(String.valueOf(Integer.parseInt(binding.coins.getText().toString()) - 5));
                } else {
                    for (int i = 0; i < binding.layout3.getChildCount(); i++) {
                        Button btn1 = (Button) binding.layout3.getChildAt(i);
                        String text = (String) btn1.getText();
                        if (a.equals(text)) {
                            a1 = true;
                            a2 = i;
                            break;
                        }
                    }
                    btn = (Button) binding.layout3.getChildAt(a2);
                    c = btn.getText().toString();
                    btn.setVisibility(View.INVISIBLE);
                    binding.coins.setText(String.valueOf(Integer.parseInt(binding.coins.getText().toString()) - 5));
                }
                topButton = new Button(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
                topButton.setLayoutParams(layoutParams);
                topButton.setText(c);
                binding.layout1.addView(topButton);
                topButton.setOnClickListener(view1 -> {
                });
                break;
            case "2":
                String capital1 = EuropeList.get(index).getCapital();
                String a3 = capital1.substring(0, 1);
                int childCount2 = binding.layout2.getChildCount();
                for (int i = 0; i < childCount2; i++) {
                    Button btn1 = (Button) binding.layout2.getChildAt(i);
                    String text = (String) btn1.getText();
                    if (a3.equals(text)) {
                        a1 = true;
                        a2 = i;
                        break;
                    }
                }
                if (a1) {
                    btn = (Button) binding.layout2.getChildAt(a2);
                    c = btn.getText().toString();
                    btn.setVisibility(View.INVISIBLE);
                    binding.coins.setText(String.valueOf(Integer.parseInt(binding.coins.getText().toString()) - 5));
                } else {
                    for (int i = 0; i < binding.layout3.getChildCount(); i++) {
                        Button btn1 = (Button) binding.layout3.getChildAt(i);
                        String text = (String) btn1.getText();
                        if (a3.equals(text)) {
                            a1 = true;
                            a2 = i;
                            break;
                        }
                    }
                    btn = (Button) binding.layout3.getChildAt(a2);
                    c = btn.getText().toString();
                    btn.setVisibility(View.INVISIBLE);
                    binding.coins.setText(String.valueOf(Integer.parseInt(binding.coins.getText().toString()) - 5));
                }
                topButton = new Button(this);
                LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
                topButton.setLayoutParams(layoutParams1);
                topButton.setText(c);
                binding.layout1.addView(topButton);
                topButton.setOnClickListener(view1 -> {

                });
                break;
            case "3":
                String capital3 = AmericaList.get(index).getCapital();
                String a4 = capital3.substring(0, 1);
                int childCount3 = binding.layout2.getChildCount();
                for (int i = 0; i < childCount3; i++) {
                    Button btn1 = (Button) binding.layout2.getChildAt(i);
                    String text = (String) btn1.getText();
                    if (a4.equals(text)) {
                        a1 = true;
                        a2 = i;
                        break;
                    }
                }
                if (a1) {
                    btn = (Button) binding.layout2.getChildAt(a2);
                    c = btn.getText().toString();
                    btn.setVisibility(View.INVISIBLE);
                    binding.coins.setText(String.valueOf(Integer.parseInt(binding.coins.getText().toString()) - 5));
                } else {
                    for (int i = 0; i < binding.layout3.getChildCount(); i++) {
                        Button btn1 = (Button) binding.layout3.getChildAt(i);
                        String text = (String) btn1.getText();
                        if (a4.equals(text)) {
                            a1 = true;
                            a2 = i;
                            break;
                        }
                    }
                    btn = (Button) binding.layout3.getChildAt(a2);
                    c = btn.getText().toString();
                    btn.setVisibility(View.INVISIBLE);
                    binding.coins.setText(String.valueOf(Integer.parseInt(binding.coins.getText().toString()) - 5));
                }
                topButton = new Button(this);
                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
                topButton.setLayoutParams(layoutParams3);
                topButton.setText(c);
                binding.layout1.addView(topButton);
                topButton.setOnClickListener(view1 -> {

                });
                break;
            case "4":
                String capital4 = AfricaList.get(index).getCapital();
                String a5 = capital4.substring(0, 1);
                int childCount4 = binding.layout2.getChildCount();
                for (int i = 0; i < childCount4; i++) {
                    Button btn1 = (Button) binding.layout2.getChildAt(i);
                    String text = (String) btn1.getText();
                    if (a5.equals(text)) {
                        a1 = true;
                        a2 = i;
                        break;
                    }
                }
                if (a1) {
                    btn = (Button) binding.layout2.getChildAt(a2);
                    c = btn.getText().toString();
                    btn.setVisibility(View.INVISIBLE);
                    binding.coins.setText(String.valueOf(Integer.parseInt(binding.coins.getText().toString()) - 5));
                } else {
                    for (int i = 0; i < binding.layout3.getChildCount(); i++) {
                        Button btn1 = (Button) binding.layout3.getChildAt(i);
                        String text = (String) btn1.getText();
                        if (a5.equals(text)) {
                            a1 = true;
                            a2 = i;
                            break;
                        }
                    }
                    btn = (Button) binding.layout3.getChildAt(a2);
                    c = btn.getText().toString();
                    btn.setVisibility(View.INVISIBLE);
                    binding.coins.setText(String.valueOf(Integer.parseInt(binding.coins.getText().toString()) - 5));
                }
                topButton = new Button(this);
                LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
                topButton.setLayoutParams(layoutParams4);
                topButton.setText(c);
                binding.layout1.addView(topButton);
                topButton.setOnClickListener(view1 -> {

                });
                break;
        }
    }

    private void setQuestions(List<FlagQuiz> list) {
        f = false;
        g = false;
        FlagQuiz flagQuiz = list.get(index);
        binding.image.setImageResource(flagQuiz.getImage());

        String capital = flagQuiz.getCapital();
        List<String> randomList = getRandomList(capital);

        for (int i = 0; i < randomList.size() / 2; i++) {
            Button button = new Button(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
            button.setText(randomList.get(i));
            button.setBackgroundColor(Color.parseColor("#31C905"));
            button.setLayoutParams(layoutParams);
            button.setOnClickListener(this);

            binding.layout2.addView(button);
        }
        for (int i = randomList.size() / 2; i < randomList.size(); i++) {
            Button button = new Button(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
            button.setText(randomList.get(i));
            button.setBackgroundColor(Color.parseColor("#31C905"));
            button.setLayoutParams(layoutParams);
            button.setOnClickListener(this);
            binding.layout3.addView(button);
        }

    }

    private List<String> getRandomList(String capital) {
        int a = capital.length();
        String b = "ABDEFGHIJKLMNOPQRSTUVXYZOGC`";
        int c = 18 - a;
        String str = b.substring(0, c);
        String d = capital.toUpperCase().concat(str);
        List<String> charList = new ArrayList<>();
        for (int i = 0; i < d.length(); i++) {
            charList.add(d.substring(i, i + 1));
        }
        Collections.shuffle(charList);

        return charList;
    }

    @Override
    public void onClick(View v) {
        Button v1 = (Button) v;
        switch (code) {
            case "1":
                if (binding.layout1.getChildCount() != AsiaList.get(index).getCapital().length()) {
                    Button button = (Button) v;

                    String text = button.getText().toString();
                    button.setVisibility(View.INVISIBLE);

                    Button topButton = new Button(this);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
                    topButton.setBackgroundColor(Color.parseColor("#31C905"));
                    topButton.setLayoutParams(layoutParams);
                    topButton.setText(text);

                    binding.layout1.addView(topButton);
                    topButton.setOnClickListener(v2 -> {
                        binding.layout1.removeView(topButton);
                        button.setVisibility(View.VISIBLE);
                        button.setClickable(true);
                    });
                    int childCount = binding.layout1.getChildCount();
                    FlagQuiz flagQuiz = AsiaList.get(index);
                    int length = flagQuiz.getCapital().length();
                    if (childCount == length) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i = 0; i < childCount; i++) {
                            View childAt = binding.layout1.getChildAt(i);
                            Button childAt1 = (Button) childAt;
                            stringBuilder.append(childAt1.getText().toString());
                        }
                        if (stringBuilder.toString().equals(flagQuiz.getCapital().toUpperCase())) {
                            Toast.makeText(this, "Qoyil! Siz to`g`ri topdingiz!", Toast.LENGTH_SHORT).show();
                            binding.coins.setText(String.valueOf(Integer.parseInt(binding.coins.getText().toString()) + 5));
                            if (index == AsiaList.size() - 1) {
                                Intent intent = new Intent(this, DialogActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                index++;
                                binding.layout1.removeAllViews();
                                binding.layout2.removeAllViews();
                                binding.layout3.removeAllViews();
                                if (code.equals("1")) {
                                    setQuestions(AsiaList);
                                } else if (code.equals("2")) {
                                    setQuestions(EuropeList);
                                } else if (code.equals("3")) {
                                    setQuestions(AmericaList);
                                } else if (code.equals("4")) {
                                    setQuestions(AfricaList);
                                }
                            }
                        } else {
                            Toast.makeText(this, "Noto`g`ri", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
            case "2":
                if (binding.layout1.getChildCount() != EuropeList.get(index).getCapital().length()) {
                    Button button = (Button) v;

                    String text = button.getText().toString();
                    button.setVisibility(View.INVISIBLE);

                    Button topButton = new Button(this);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
                    topButton.setBackgroundColor(Color.parseColor("#31C905"));
                    topButton.setLayoutParams(layoutParams);
                    topButton.setText(text);

                    binding.layout1.addView(topButton);
                    topButton.setOnClickListener(v2 -> {
                        binding.layout1.removeView(topButton);
                        button.setVisibility(View.VISIBLE);
                        button.setClickable(true);
                    });
                    int childCount = binding.layout1.getChildCount();
                    FlagQuiz flagQuiz = EuropeList.get(index);
                    int length = flagQuiz.getCapital().length();
                    if (childCount == length) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i = 0; i < childCount; i++) {
                            View childAt = binding.layout1.getChildAt(i);
                            Button childAt1 = (Button) childAt;
                            stringBuilder.append(childAt1.getText().toString());
                        }
                        if (stringBuilder.toString().equals(flagQuiz.getCapital().toUpperCase())) {
                            Toast.makeText(this, "Qoyil! Siz to`g`ri topdingiz!", Toast.LENGTH_SHORT).show();
                            binding.coins.setText(String.valueOf(Integer.parseInt(binding.coins.getText().toString()) + 5));
                            if (index == EuropeList.size() - 1) {
                                Intent intent = new Intent(this, DialogActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                index++;
                                binding.layout1.removeAllViews();
                                binding.layout2.removeAllViews();
                                binding.layout3.removeAllViews();
                                if (code.equals("1")) {
                                    setQuestions(AsiaList);
                                } else if (code.equals("2")) {
                                    setQuestions(EuropeList);
                                } else if (code.equals("3")) {
                                    setQuestions(AmericaList);
                                } else if (code.equals("4")) {
                                    setQuestions(AfricaList);
                                }
                            }
                        } else {
                            Toast.makeText(this, "Noto`g`ri", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
            case "3":
                if (binding.layout1.getChildCount() != AmericaList.get(index).getCapital().length()) {
                    Button button = (Button) v;

                    String text = button.getText().toString();
                    button.setVisibility(View.INVISIBLE);

                    Button topButton = new Button(this);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
                    topButton.setBackgroundColor(Color.parseColor("#31C905"));
                    topButton.setLayoutParams(layoutParams);
                    topButton.setText(text);

                    binding.layout1.addView(topButton);
                    topButton.setOnClickListener(v2 -> {
                        binding.layout1.removeView(topButton);
                        button.setVisibility(View.VISIBLE);
                        button.setClickable(true);
                    });
                    int childCount = binding.layout1.getChildCount();
                    FlagQuiz flagQuiz = AmericaList.get(index);
                    int length = flagQuiz.getCapital().length();
                    if (childCount == length) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i = 0; i < childCount; i++) {
                            View childAt = binding.layout1.getChildAt(i);
                            Button childAt1 = (Button) childAt;
                            stringBuilder.append(childAt1.getText().toString());
                        }
                        if (stringBuilder.toString().equals(flagQuiz.getCapital().toUpperCase())) {
                            Toast.makeText(this, "Qoyil! Siz to`g`ri topdingiz!", Toast.LENGTH_SHORT).show();
                            binding.coins.setText(String.valueOf(Integer.parseInt(binding.coins.getText().toString()) + 5));
                            if (index == AmericaList.size() - 1) {
                                Intent intent = new Intent(this, DialogActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                index++;
                                binding.layout1.removeAllViews();
                                binding.layout2.removeAllViews();
                                binding.layout3.removeAllViews();
                                if (code.equals("1")) {
                                    setQuestions(AsiaList);
                                } else if (code.equals("2")) {
                                    setQuestions(EuropeList);
                                } else if (code.equals("3")) {
                                    setQuestions(AmericaList);
                                } else if (code.equals("4")) {
                                    setQuestions(AfricaList);
                                }
                            }
                        } else {
                            Toast.makeText(this, "Noto`g`ri", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
            case "4":
                if (binding.layout1.getChildCount() != AfricaList.get(index).getCapital().length()) {
                    Button button = (Button) v;

                    String text = button.getText().toString();
                    button.setVisibility(View.INVISIBLE);

                    Button topButton = new Button(this);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
                    topButton.setBackgroundColor(Color.parseColor("#31C905"));
                    topButton.setLayoutParams(layoutParams);
                    topButton.setText(text);

                    binding.layout1.addView(topButton);
                    topButton.setOnClickListener(v2 -> {
                        binding.layout1.removeView(topButton);
                        button.setVisibility(View.VISIBLE);
                        button.setClickable(true);
                    });
                    int childCount = binding.layout1.getChildCount();
                    FlagQuiz flagQuiz = AfricaList.get(index);
                    int length = flagQuiz.getCapital().length();
                    if (childCount == length) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i = 0; i < childCount; i++) {
                            View childAt = binding.layout1.getChildAt(i);
                            Button childAt1 = (Button) childAt;
                            stringBuilder.append(childAt1.getText().toString());
                        }
                        if (stringBuilder.toString().equals(flagQuiz.getCapital().toUpperCase())) {
                            Toast.makeText(this, "Qoyil! Siz to`g`ri topdingiz!", Toast.LENGTH_SHORT).show();
                            binding.coins.setText(String.valueOf(Integer.parseInt(binding.coins.getText().toString()) + 5));
                            if (index == AfricaList.size() - 1) {
                                Intent intent = new Intent(this, DialogActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                index++;
                                binding.layout1.removeAllViews();
                                binding.layout2.removeAllViews();
                                binding.layout3.removeAllViews();
                                if (code.equals("1")) {
                                    setQuestions(AsiaList);
                                } else if (code.equals("2")) {
                                    setQuestions(EuropeList);
                                } else if (code.equals("3")) {
                                    setQuestions(AmericaList);
                                } else if (code.equals("4")) {
                                    setQuestions(AfricaList);
                                }
                            }
                        } else {
                            Toast.makeText(this, "Noto`g`ri", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}