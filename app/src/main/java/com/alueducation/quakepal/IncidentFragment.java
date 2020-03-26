package com.alueducation.quakepal;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class IncidentFragment extends Fragment {

    private SharedViewModel mViewModel;
    private TextView dateSelect;
    private LiveData<Map<String, List>> earthquakes;

    public static IncidentFragment newInstance() {
        return new IncidentFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View incidentRoot = inflater.inflate(R.layout.incident_fragment, container, false);
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
                                String date = String.format(Locale.getDefault(),"%d/%d/%d", dayOfMonth, monthOfYear+1, year);
                                if (dateSelect != null){
                                    dateSelect.setText(date);
                                }

                            }
                        }, year, month, day);
                picker.show();

            }
        });
        return incidentRoot;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SharedViewModel.class);
        earthquakes =  mViewModel.getEarthquakes();
     }

    public TextView getDateSelect() {
        return dateSelect;
    }

    public void setDateSelect(TextView dateSelect) {
        this.dateSelect = dateSelect;
    }

}
