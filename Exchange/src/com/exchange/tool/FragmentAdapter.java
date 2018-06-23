package com.exchange.tool;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;

public class FragmentAdapter extends FragmentPagerAdapter {
	List<Fragment> fragmentList = new ArrayList<Fragment>();
	public FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
		super(fm);
		// TODO Auto-generated constructor stub
		this.fragmentList = fragmentList;
	}

	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub
		return fragmentList.get(position);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return fragmentList.size();
	}
	@Override
	  public Object instantiateItem(ViewGroup container, int position) {
		Fragment f = (Fragment) super.instantiateItem(container, position);
	    //Fragment f = fragmentList.get(position);
	    return f;
	}
	 
	@Override
	public int getItemPosition(Object object) {
	     return PagerAdapter.POSITION_NONE;
	}
}
