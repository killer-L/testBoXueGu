package cn.edu.gdmec.android.testboxuegu.adapter;

import android.os.Bundle;
import android.os.Handler;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import java.security.cert.PolicyNode;
import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.testboxuegu.bean.CourseBean;
import cn.edu.gdmec.android.testboxuegu.fragment.AdBannerFragment;
import cn.edu.gdmec.android.testboxuegu.view.CourseView;

/**
 * Created by student on 17/12/27.
 */

public class AdBannerAdapter extends FragmentStatePagerAdapter implements OnTouchListener {

    private Handler mHandler;
    private List<CourseBean> cadl;

    public AdBannerAdapter(FragmentManager fm) {
        super(fm);
        cadl = new ArrayList<CourseBean>();
    }

    public AdBannerAdapter(FragmentManager fm,Handler handler){
        super(fm);
        mHandler = handler;
        cadl = new ArrayList<CourseBean>();
    }

    public void setDatas(List<CourseBean> cadl){
        this.cadl = cadl;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int index) {
        Bundle args = new Bundle();
        if (cadl.size()>0)
            args.putString("ad",cadl.get(index % cadl.size()).icon);
        return AdBannerFragment.newInstance(args);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    public int getSize(){
        return cadl == null ? 0 : cadl.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        mHandler.removeMessages(CourseView.MSG_AD_SLID);
        return false;
    }
}
