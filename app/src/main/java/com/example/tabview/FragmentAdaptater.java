package com.example.tabview;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdaptater extends FragmentStateAdapter {

    public FragmentAdaptater(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 0:
                return new F01_Fragment();
            case 1:
                return new F02_Fragment();
            case 2:
                return new F03_Fragment();
        }

        return null;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
