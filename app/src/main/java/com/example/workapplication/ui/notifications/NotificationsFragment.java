package com.example.workapplication.ui.notifications;

import android.content.Intent;
import android.location.GnssAntennaInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.workapplication.EmptyContentPage;
import com.example.workapplication.MainActivity;
import com.example.workapplication.R;
import com.example.workapplication.Release;
import com.example.workapplication.databinding.FragmentNotificationsBinding;
import com.example.workapplication.ui.EmptyContentPage2;
import com.example.workapplication.ui.WelcomeActivity;

public class NotificationsFragment extends Fragment implements View.OnClickListener {

    private NotificationsViewModel notificationsViewModel;
    private FragmentNotificationsBinding binding;
    //添加的内容
    private LinearLayout linearLayoutStar;
    private LinearLayout linearLayoutCards;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
//
//        final TextView textView = binding.textNotifications;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });
//
        linearLayoutStar=root.findViewById(R.id.linear_start);
        linearLayoutCards=root.findViewById(R.id.linear_cards);
        linearLayoutStar.setOnClickListener(this);
        linearLayoutCards.setOnClickListener(this);
        return root;
    }
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getActivity(), EmptyContentPage.class);
        startActivity(intent);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}