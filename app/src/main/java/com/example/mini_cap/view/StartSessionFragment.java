package com.example.mini_cap.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mini_cap.R;
import com.example.mini_cap.controller.CustomStartAdapter;
import com.example.mini_cap.controller.DBHelper;
import com.example.mini_cap.controller.SelectListener;
import com.example.mini_cap.model.Preset;

import java.util.ArrayList;
import java.util.List;

public class StartSessionFragment extends DialogFragment implements SelectListener {

    protected TextView titleTextView;
    protected ImageView closeFragBTN;
    protected RecyclerView listOfPresets;
    private DBHelper dbHelper;
    private CustomStartAdapter customStartAdapter;

    /**
     * Required empty public constructor
     */
    public StartSessionFragment() {

    }

    /**
     * The onCreateView which inflates the layout and determines the context which the fragment
     * was called from and manages the view accordingly
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_start_session, container, false);

        dbHelper = new DBHelper(getContext());

        titleTextView = view.findViewById(R.id.selectPresetTextView);
        closeFragBTN = view.findViewById(R.id.closeImgBTN);
        listOfPresets = view.findViewById(R.id.recyclerViewPresets);

        setUpRecyclerView();

        closeFragBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return view;

    }
    /**
     * This method sets up the RecyclerView in the fragment by populating it with the available strings
     */
    public void setUpRecyclerView(){

        ArrayList<Preset> presets = dbHelper.getAllPresets();
        listOfPresets.setLayoutManager(new GridLayoutManager(getContext(), 1));
        customStartAdapter = new CustomStartAdapter(getContext(), presets, this);
        listOfPresets.setAdapter(customStartAdapter);
    }

    @Override
    public void onItemClicked(Preset preset) {
        ((SessionActivity)getActivity()).fetchPresetStartSession(preset);
        dismiss();
    }
}