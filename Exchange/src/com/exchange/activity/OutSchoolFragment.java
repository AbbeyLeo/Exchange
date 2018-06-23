package com.exchange.activity;

import com.exchange.activity.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class OutSchoolFragment extends Fragment {
	View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (view != null) {  
            ViewGroup parent = (ViewGroup) view.getParent();  
            if (parent != null) {  
                parent.removeView(view);  
            }  
            return view;  
        }  
		view = (View) inflater.inflate(R.layout.test_fragment_school_out, container, false);
		return view;
	}

}
