package com.exchange.activity;

import java.util.ArrayList;
import java.util.List;

import com.exchange.activity.R;
import com.exchange.bean.PublishedItem;
import com.exchange.tool.PublishItemAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class InSchoolFragment extends Fragment {
	PublishItemAdapter adapter;
	View view;
	/**
     * publish_List
     */
   	List<PublishedItem> publish_List;
   	
   	private ListView published_listView;
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
		view = inflater.inflate(R.layout.test_fragment_school_in, container, false);
		initList();
		adapter = new PublishItemAdapter(getActivity(), R.layout.publish_listview_item, publish_List);
		published_listView = (ListView) view.findViewById(R.id.publish_listview);
		published_listView.setAdapter(adapter);
		return view;
	}
	private void initList()
	{
		publish_List = new ArrayList<PublishedItem>();
		PublishedItem item= new PublishedItem("测试用户", "测试商品", "$199");
		for(int i = 0; i<=4; i++)
		{
			publish_List.add(item);
		}
	}
}
