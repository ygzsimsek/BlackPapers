package com.blackwater.blackpapers.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.blackwater.blackpapers.Activities.AboutActivity;
import com.blackwater.blackpapers.R;
import com.blackwater.blackpapers.Activities.SettingsActivty;
import com.blackwater.blackpapers.Activities.UploadActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppFragment extends Fragment implements View.OnClickListener {

    Button btnUpload,btnFirebase,btnHistory,btnDownloaded,btnSettings,btnInfo;
    private static AppFragment INSTANCE=null;
    public AppFragment() {
        // Required empty public constructor
    }

    public static AppFragment getInstance() {
        if(INSTANCE == null)
            INSTANCE = new AppFragment();
        return INSTANCE;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_app, container, false);
        btnUpload = (Button)view.findViewById(R.id.btn_upload);
        btnFirebase = (Button)view.findViewById(R.id.btn_firebase);
        btnHistory = (Button)view.findViewById(R.id.btn_history);
        btnDownloaded = (Button)view.findViewById(R.id.btn_downloaded);
        btnSettings = (Button)view.findViewById(R.id.btn_settings);
        btnInfo = (Button)view.findViewById(R.id.btn_info);

        btnUpload.setOnClickListener(this);
        btnFirebase.setOnClickListener(this);
        btnHistory.setOnClickListener(this);
        btnDownloaded.setOnClickListener(this);
        btnSettings.setOnClickListener(this);
        btnInfo.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_upload:
                Intent uploadIntent = new Intent(getActivity(),UploadActivity.class);
                startActivity(uploadIntent);
                break;
            case R.id.btn_firebase:
                Toast.makeText(getActivity(), "Launch FirebaseAdminConsole", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_history:
                Toast.makeText(getActivity(), "Launch HistoryActivity", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_downloaded:
                Toast.makeText(getActivity(), "Launch DownloadActivity", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_settings:
                Intent settingsIntent = new Intent(getActivity(),SettingsActivty.class);
                startActivity(settingsIntent);
                break;
            case R.id.btn_info:
                Intent infoIntent = new Intent(getActivity(),AboutActivity.class);
                startActivity(infoIntent);
                break;
        }
    }
}
