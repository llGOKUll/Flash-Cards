package com.omega.Fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.omega.Adaptors.GroupsAdaptor;
import com.omega.R;
import com.omega.Util.EqualSpaceItemDecoration;
import com.omega.Util.FlashCardViewModel;

public class CheckoutFlashCardFragment extends Fragment {

    FlashCardViewModel flashCardViewModel;
    GroupsAdaptor groupsAdaptor;

    public CheckoutFlashCardFragment(){

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        flashCardViewModel = ViewModelProviders.of(this).get(FlashCardViewModel.class);
        flashCardViewModel.getAllGroups().observe(this,groups -> {
            groupsAdaptor.setDataSet(groups);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewGroup = inflater.inflate(R.layout.fragment_check_out_flash_cards, container, false);
        initializeVariables(viewGroup);
        return viewGroup;
    }

    private void initializeVariables(View viewGroup) {
        RecyclerView rvGroups = viewGroup.findViewById(R.id.recycler_view_group);

        groupsAdaptor = new GroupsAdaptor(getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvGroups.setLayoutManager(linearLayoutManager);
        rvGroups.setAdapter(groupsAdaptor);
        rvGroups.addItemDecoration(new EqualSpaceItemDecoration(40));
    }
}