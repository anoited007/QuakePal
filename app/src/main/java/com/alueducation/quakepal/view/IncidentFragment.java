/*
 * Copyright (c) 2020.
 * Name: Emmanuel Sackey
 * Matric: S1719015
 * Programme: Bsc(Hons) Computing
 *
 */

package com.alueducation.quakepal.view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alueducation.quakepal.model.Earthquake;
import com.alueducation.quakepal.adapter.EarthquakeAdapter;
import com.alueducation.quakepal.R;
import com.alueducation.quakepal.model.SharedViewModel;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class IncidentFragment extends Fragment {

    private SharedViewModel mViewModel;
    private TextView dateSelect;
    private RecyclerView incidents;
    private Button filterByDate;

    public static IncidentFragment newInstance() {
        return new IncidentFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View incidentRoot = inflater.inflate(R.layout.incident_fragment, container, false);
        dateSelect = incidentRoot.findViewById(R.id.TextView_SearchDate);
//        Open Date picker when the text view is selected
        dateSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog picker;
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                String date = String.format(Locale.getDefault(),"%d %d %d", dayOfMonth, monthOfYear, year);
                                if (dateSelect != null){
                                    dateSelect.setText(getDateString(date));
                                }

                            }
                        }, year, month, day);
                picker.show();

            }
        });
        incidents = incidentRoot.findViewById(R.id.RecyclerView_Incidents);
        filterByDate = incidentRoot.findViewById(R.id.Button_FilterByDate);
        filterByDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = dateSelect.getText().toString();
//                check if the user hasn't selected any date.
                if ("Tap to Enter Date".equals(key)){
                   final AlertDialog.Builder emptyDateAlert = new AlertDialog.Builder(getContext());
                   emptyDateAlert.setTitle(R.string.empty_date_alert_title);
                   emptyDateAlert.setMessage(R.string.empty_date_alert_msg);
                   emptyDateAlert.setCancelable(false);
                   emptyDateAlert.setIcon(android.R.drawable.ic_dialog_alert);
                   emptyDateAlert.setPositiveButton(R.string.empty_date_alert_ok, new DialogInterface.OnClickListener(){
                       public void onClick(DialogInterface dialog, int which){
                          dialog.dismiss();
                       }
                   });
                   emptyDateAlert.show();
                }
                else {
                    List<Earthquake> earthquakeList = mViewModel.getEarthquakes().get(key);
                    TextView noEarthquake = incidentRoot.findViewById(R.id.TextView_NoEarthquake);
                    if (earthquakeList == null) {
                        noEarthquake.setText(R.string.no_earthquakes);
                    }
                    else {
                        noEarthquake.setText("");
                        if (mViewModel.getSortedByDepth() != null || mViewModel.getSortedByMagnitude() != null){
                            try {
                                earthquakeList.add(mViewModel.getSortedByMagnitude().get(mViewModel.getSortedByMagnitude().size()-1));
                                earthquakeList.add(mViewModel.getSortedByDepth().get(mViewModel.getSortedByDepth().size()-1));
                            }catch (Exception ex){
                                Log.e("Incident fragment", ex.toString());
                            }

                        }
                        EarthquakeAdapter earthquakeAdapter = new EarthquakeAdapter(earthquakeList);
                        incidents.setAdapter(earthquakeAdapter);
                        incidents.setLayoutManager(new LinearLayoutManager(getContext()));
                    }

                }
            }
        });
        return incidentRoot;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SharedViewModel.class);
       Map<String, List<Earthquake>> earthquakes = mViewModel.getEarthquakes();
        if (earthquakes != null) {
            Collection<List<Earthquake>> earthquakeList = mViewModel.getEarthquakes().values();
            EarthquakeAdapter earthquakeAdapter = new EarthquakeAdapter(toList(earthquakeList));
            incidents.setAdapter(earthquakeAdapter);
        }
     }

     public static List<Earthquake> toList(Collection<List<Earthquake>> collection){
        List<Earthquake> combinedList = new ArrayList<>();
        for (List<Earthquake> list : collection){
            combinedList.addAll(list);
        }
        return combinedList;
     }

     private String getDateString(String date){
        String[] dateArray = date.split(" ");
        String month =  new DateFormatSymbols().getShortMonths()[Integer.parseInt(dateArray[1])];
        String day = Integer.parseInt(dateArray[0]) < 10 ? "0" + dateArray[0] : dateArray[0];
        return String.format(Locale.getDefault(), "%s %s %s", day, month, dateArray[2]);
     }


}
