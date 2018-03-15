package com.yijia.myview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.yijia.zhenglei.R;

import java.util.ArrayList;

public class ImagePagerActivity extends FragmentActivity {
    private static final String STATE_POSITION = "STATE_POSITION";  
    public static final String EXTRA_IMAGE_INDEX = "image_index";  
    public static final String EXTRA_IMAGE_URLS = "image_urls";  
  
    private HackyViewPager mPager;  
    private int pagerPosition;  
    private TextView indicator;
  
    @Override  
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.image_detail_pager);

        pagerPosition = getIntent().getIntExtra(EXTRA_IMAGE_INDEX, 0);  
        ArrayList<Integer> urls = getIntent().getIntegerArrayListExtra(
                EXTRA_IMAGE_URLS);  
  
        mPager = (HackyViewPager) findViewById(R.id.pager);  
        ImagePagerAdapter mAdapter = new ImagePagerAdapter(getSupportFragmentManager(), urls);
        mPager.setAdapter(mAdapter);  
        indicator = (TextView) findViewById(R.id.indicator);  
  
        CharSequence text = getString(R.string.viewpager_indicator, 1, mPager  
                .getAdapter().getCount());  
        indicator.setText(text);
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
  
            @Override  
            public void onPageScrollStateChanged(int arg0) {  
            }  
  
            @Override  
            public void onPageScrolled(int arg0, float arg1, int arg2) {  
            }  
  
            @Override  
            public void onPageSelected(int arg0) {  
                CharSequence text = getString(R.string.viewpager_indicator,  
                        arg0 + 1, mPager.getAdapter().getCount());  
                indicator.setText(text);  
            }  
  
        });  
        if (savedInstanceState != null) {  
            pagerPosition = savedInstanceState.getInt(STATE_POSITION);  
        }  
  
        mPager.setCurrentItem(pagerPosition);  
    }  
  
    @Override  
    public void onSaveInstanceState(Bundle outState) {  
        outState.putInt(STATE_POSITION, mPager.getCurrentItem());  
    }  
  
    private class ImagePagerAdapter extends FragmentStatePagerAdapter {
  
        public ArrayList<Integer> fileList;
  
        public ImagePagerAdapter(FragmentManager fm, ArrayList<Integer> fileList) {
            super(fm);  
            this.fileList = fileList;  
        }  
  
        @Override  
        public int getCount() {  
            return fileList == null ? 0 : fileList.size();  
        }  
  
        @Override  
        public Fragment getItem(int position) {
            int url = fileList.get(position);
            return ImageDetailFragment.newInstance(url);  
        }  
  
    }  
}  