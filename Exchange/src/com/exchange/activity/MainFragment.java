/**
 * 
 */
package com.exchange.activity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.exchange.tool.ConfigTool;
import com.exchange.tool.FragmentAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author Abbey
 *
 */
public class MainFragment extends Fragment {
	private View mView;
	AdvertisementFragment advertisementFragment;
	ConfigTool tool = new ConfigTool();
	private TextView SchoolInTextView;		 //“校内的”那个TextView
	private TextView SchoolOutTextView;		 //“校外的”那个TextView
	private ImageView mLineImageView; 		 //下划线的ImageView
	private ViewPager mViewPager;		 //用于手动滑动的适配器
	private InSchoolFragment ISF;			 //用于测试在校内的容器
	private OutSchoolFragment OSF;			 //用于测试在校外的容器
	private List<Fragment> MFragmentList;    //放校内的内容和校外的内容那个fragment
	private FragmentAdapter mFragmentAdapter;
	private View advertisement;			 //广告的Fragment
	
	private EditText searchEditView;
	private ListView published_listview;
	private boolean mIsTitleHide = false;  
    private boolean mIsAnim = false;  
    private float lastX = 0;  
    private float lastY = 0; 
    
    
	/** 
     * ViewPager的当前选中页 
     */
    private int currentIndex = 0; 
    /**
     * 屏幕的宽度
     */
    private int screenWidth;
    
    
    
	  public MainFragment() {
		super();
		// TODO Auto-generated constructor stub
		//init();
		/*MFragmentList = new ArrayList<Fragment>();
		MFragmentList.add(ISF);
		MFragmentList.add(OSF);*/
		
	}
	
	@Override  
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {  
			mView=inflater.inflate(R.layout.fragment_index, container, false);  
	        mViewPager = (ViewPager) mView.findViewById(R.id.school_area_vp);
			SchoolInTextView = (TextView) mView.findViewById(R.id.in_school);
			SchoolOutTextView = (TextView) mView.findViewById(R.id.out_school);
			mLineImageView = (ImageView) mView.findViewById(R.id.tab_line_iv);
			mViewPager = (ViewPager) mView.findViewById(R.id.school_area_vp);
			searchEditView = (EditText) mView.findViewById(R.id.searcheditView);
			
			published_listview = (ListView) mView.findViewById(R.id.publish_listview);
			
			advertisement = (View) mView.findViewById(R.id.advertisement_fragement);
			init();
			
	        return mView;  
	    } 
	
	/*@Override
	public void onDestroyView() {
		super.onDestroyView();

	 	if(advertisementFragment != null){
			FragmentManager f = getFragmentManager();
			if(f != null && !f.isDestroyed()){
				FragmentTransaction ft = f.beginTransaction();
				if(ft != null){
					ft.remove(advertisementFragment).commit(); 
				}
			}
	 	}
	}*/
	  public void init()
		{
		    System.out.println("123456789");
			ISF = new InSchoolFragment();
			OSF = new OutSchoolFragment();
			SchoolInTextView.setTextColor(SchoolInTextView.getResources().getColor(R.color.BtColor));
			MFragmentList = new ArrayList<Fragment>();
			MFragmentList.add(ISF);
			MFragmentList.add(OSF);
			mFragmentAdapter = new FragmentAdapter(getActivity().getSupportFragmentManager(), MFragmentList);
			mViewPager.setAdapter(mFragmentAdapter); 
			mViewPager.setCurrentItem(0);
			mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
				
				@Override
				public void onPageSelected(int position) {
					// TODO Auto-generated method stub
					resetTextView();
	                switch (position) {
	                case 0:
	                    SchoolInTextView.setTextColor(SchoolInTextView.getResources().getColor(R.color.BtColor));
	                    break;
	                case 1:
	                    SchoolOutTextView.setTextColor(SchoolOutTextView.getResources().getColor(R.color.BtColor));
	                    break;
	                }
	                currentIndex = position;
				}
				
				@Override
				public void onPageScrolled(int position, float offset, int offsetPixels) {
					// TODO Auto-generated method stub
					DisplayMetrics mPhoneSize = tool.getPhoneSize(getActivity());
					LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mLineImageView.getLayoutParams();
					lp.leftMargin = initLineMargin() + ((mPhoneSize.widthPixels - lp.width)/2)*position + offsetPixels/2 + lp.width*position/2;
					mLineImageView.setLayoutParams(lp);
				}
				
				@Override
				public void onPageScrollStateChanged(int state) {
					// TODO Auto-generated method stub
					
				}
			});
		}
	  
	  public int initLineMargin()
		{
			
			DisplayMetrics mPhoneSize = tool.getPhoneSize(getActivity());
			LinearLayout.LayoutParams lp1 = (LinearLayout.LayoutParams) mLineImageView.getLayoutParams();
			lp1.leftMargin = ((mPhoneSize.widthPixels/2) - lp1.width)/2;
			return lp1.leftMargin;
		}
		private void resetTextView() {
	        SchoolInTextView.setTextColor(0xff8c8c8c);
	        SchoolOutTextView.setTextColor(0xff8c8c8c);
	    }
		@Override
		public void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			if(mViewPager != null)
			{
				ISF = new InSchoolFragment();
				OSF = new OutSchoolFragment();
				MFragmentList = new ArrayList<Fragment>();
				MFragmentList.add(ISF);
				MFragmentList.add(OSF);
			}/*else
			{
				mViewPager = (ViewPager) mView.findViewById(R.id.school_area_vp);
				ISF = new InSchoolFragment();
				OSF = new OutSchoolFragment();
				MFragmentList = new ArrayList<Fragment>();
				MFragmentList.add(ISF);
				MFragmentList.add(OSF);
				initViewPager();
			}*/
		}
}
