package com.rsschool.android2021;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements FirstFragment.FirstFragmentInterface,
        SecondFragment.SecondFragmentInterface {
    private Boolean isSecondFragmentOn;
    private int m_randomNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openFirstFragment(0);
    }

    public void openFirstFragment(int previousNumber) {
        isSecondFragmentOn = false;
        final Fragment firstFragment = FirstFragment.newInstance(previousNumber);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, firstFragment);
        transaction.commit();
    }

    public void openSecondFragment(int randomNumber) {
        isSecondFragmentOn = true;
        m_randomNumber = randomNumber;
        final Fragment secondFragment = SecondFragment.newInstance(randomNumber);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, secondFragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (isSecondFragmentOn) {
            openFirstFragment(m_randomNumber);
        } else super.onBackPressed();
    }
}
