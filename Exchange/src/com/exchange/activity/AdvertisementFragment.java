package com.exchange.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.exchange.activity.R;
import com.exchange.activity.R.drawable;
import com.exchange.activity.R.id;
import com.exchange.activity.R.layout;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class AdvertisementFragment extends Fragment {
	
	 private View mView;  
	    private ViewPager mViewPaper;  
	    private List<ImageView> images;  
	    private List<View> dots;  
	    private int currentItem;  //记录当前的页面
	    //记录上一次点的位置  
	    private int oldPosition = 0;  
	    //存放图片的id  
	    private int[] imageIds = new int[]{  
	            R.drawable.game1,  
	            R.drawable.game2,  
	            R.drawable.game3,  
	            R.drawable.game1,  
	            R.drawable.game2,
	            R.drawable.game3
	    };  
	    //存放图片的标题  
//	    private String[] titles = new String[]{  
//	            "轮播1",  
//	            "轮播2",  
//	            "轮播3",  
//	            "轮播4",  
//	            "轮播5",
//	            "轮播6",
//	    };  
//	    private TextView title;  									//轮播图的标题，可能需要用
	    private ViewPagerAdapter adapter;  							//动画轮播的适配器
	    private ScheduledExecutorService scheduledExecutorService;  //轮播的时间任务服务
	  
	    @Override  
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {  
	  
	        mView=inflater.inflate(R.layout.pic_fragment, container, false);  
	        setView();  
	        return mView;  
	    }  
	    private void setView(){  
	        mViewPaper = (ViewPager)mView.findViewById(R.id.vp);  
	  
	        //显示的图片，代码添加ImageView  
	        images = new ArrayList<ImageView>();  
	        for(int i = 0; i < imageIds.length; i++){  
	            ImageView imageView = new ImageView(getActivity());  
	            imageView.setBackgroundResource(imageIds[i]);  
	            images.add(imageView);  
	        }  
	        //显示的小点  
	        dots = new ArrayList<View>();  
	        dots.add(mView.findViewById(R.id.dot_0));  
	        dots.add(mView.findViewById(R.id.dot_1));  
	        dots.add(mView.findViewById(R.id.dot_2));  
	        dots.add(mView.findViewById(R.id.dot_3));  
	        dots.add(mView.findViewById(R.id.dot_4));
	        dots.add(mView.findViewById(R.id.dot_5));
	        
//	        title = (TextView) mView.findViewById(R.id.title);  
//	        title.setText(titles[0]);  
	  
	        adapter = new ViewPagerAdapter();  
	        mViewPaper.setAdapter(adapter);  
	  
	        mViewPaper.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {  
	  
	  
	            @Override  
	            public void onPageSelected(int position) {  
//	                title.setText(titles[position]);  
	                dots.get(position).setBackgroundResource(R.drawable.dot_yes);  
	                dots.get(oldPosition).setBackgroundResource(R.drawable.dot_no);  
	  
	                oldPosition = position;  
	                currentItem = position;  
	            }  
	  
	            @Override  
	            public void onPageScrolled(int arg0, float arg1, int arg2) {  
	  
	            }  
	  
	            @Override  
	            public void onPageScrollStateChanged(int arg0) {  
	  
	            }  
	        });  
	    }  
	  
	    /*定义的适配器*/  
	    public class ViewPagerAdapter extends PagerAdapter{  
	  
	        @Override  
	        public int getCount() {  
	            return images.size();  
	        }  
	  
	        @Override  
	        public boolean isViewFromObject(View arg0, Object arg1) {  
	            return arg0 == arg1;  
	        }  
	  
	        @Override  
	        public void destroyItem(ViewGroup view, int position, Object object) {  
	            // TODO Auto-generated method stub  
//	          super.destroyItem(container, position, object);  
//	          view.removeView(view.getChildAt(position));  
//	          view.removeViewAt(position);  
	            view.removeView(images.get(position));  
	        }  
	  
	        @Override  
	        public Object instantiateItem(ViewGroup view, int position) {  
	            // TODO Auto-generated method stub  
	            view.addView(images.get(position));  
	            return images.get(position); 
	        }  
	  
	    }  
	  
	    
	    //利用线程池定时执行动画轮播 ,即下面是具体化的Runnable
	    @Override  
	    public void onStart() {  
	        // TODO Auto-generated method stub  
	        super.onStart();  
	        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();  
	        scheduledExecutorService.scheduleWithFixedDelay(  
	                new ViewPageTask(),  
	                5,  
	                5,  
	                TimeUnit.SECONDS);  
	    }  
	  
	  
	    //图片轮播任务 
	    private class ViewPageTask implements Runnable{  
	  
	        @Override  
	        public void run() {  
	            currentItem = (currentItem + 1) % imageIds.length;  //如果等于最后页面，则返回0
	            mHandler.sendEmptyMessage(0);  
	        }  
	    }  
	  
	     
	     // 接收子线程传递过来的数据 
	    private Handler mHandler = new Handler(){  
	        public void handleMessage(android.os.Message msg) {  
	            mViewPaper.setCurrentItem(currentItem);  	//设置当前选中的页面
	        };  
	    };  
	    @Override  
	    public void onStop() {  
	        // TODO Auto-generated method stub  
	        super.onStop();  
	        if(scheduledExecutorService != null){  
	            scheduledExecutorService.shutdown();  
	            scheduledExecutorService = null;  
	        }  
	    }  
	    
	}
