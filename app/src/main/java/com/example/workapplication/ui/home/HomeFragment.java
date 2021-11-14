package com.example.workapplication.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.workapplication.Adapter;
import com.example.workapplication.Photo;
import com.example.workapplication.R;
import com.example.workapplication.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment<FragmentHomeBinding> extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    private List<Photo> photoList = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home,container,false);
        RecyclerView recyclerView;
        initPeakys();
//        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView = view.findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        Adapter adapter = new Adapter(photoList);
        recyclerView.setAdapter(adapter);
        return view;



//        homeViewModel =
//                new ViewModelProvider(this).get(HomeViewModel.class);
//
//        binding = FragmentHomeBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
//
//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
//        return root;
    }


    private void initPeakys() {
        for (int i = 0; i < 10; i++) {
            Photo a = new Photo("a", R.drawable.a);
            photoList.add(a);
            Photo b = new Photo("b", R.drawable.b);
            photoList.add(b);
            Photo c = new Photo("c", R.drawable.c);
            photoList.add(c);
            Photo d = new Photo("d", R.drawable.d);
            photoList.add(d);
            Photo e = new Photo("d", R.drawable.e);
            photoList.add(e);
            Photo f = new Photo("f", R.drawable.f);
            photoList.add(f);
            Photo g = new Photo("g", R.drawable.g);
            photoList.add(g);
            Photo h = new Photo("h", R.drawable.h);
            photoList.add(h);
            Photo j = new Photo("i", R.drawable.j);
            photoList.add(j);
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}