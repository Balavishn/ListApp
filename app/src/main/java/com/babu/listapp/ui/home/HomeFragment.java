package com.babu.listapp.ui.home;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.babu.listapp.Adapter;
import com.babu.listapp.Bug_report;
import com.babu.listapp.Frag_Bug_report;
import com.babu.listapp.R;
import com.babu.listapp.loading;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    PackageManager packageManager;
    static View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView=root.findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        packageManager=root.getContext().getPackageManager();
        loading lode=new loading(getActivity());
        lode.startdio();
        List<ApplicationInfo> listapp=packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
        Collections.sort(listapp,new sortname());
        Adapter adapter=new Adapter(listapp,packageManager,root,new HomeFragment());

        recyclerView.setAdapter(adapter);
        lode.dismiss();

        return root;
    }

    class sortname implements Comparator<ApplicationInfo> {

        @Override
        public int compare(ApplicationInfo applicationInfo, ApplicationInfo t1) {

            return Collator.getInstance().compare(applicationInfo.loadLabel(packageManager),t1.loadLabel(packageManager));
        }
    }
}

