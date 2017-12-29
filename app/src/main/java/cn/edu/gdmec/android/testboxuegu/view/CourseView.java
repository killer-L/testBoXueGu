package cn.edu.gdmec.android.testboxuegu.view;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.testboxuegu.adapter.AdBannerAdapter;
import cn.edu.gdmec.android.testboxuegu.adapter.CourseAdapter;
import cn.edu.gdmec.android.testboxuegu.bean.CourseBean;

/**
 * Created by student on 17/12/27.
 */

public class CourseView {

    private ListView lv_list;
    private CourseAdapter adapter;
    private List<List<CourseBean>> cbl;
    private FragmentActivity mContext;
    private LayoutInflater mInflater;
    private View mCurrentView;
    private ViewPager adPager;
    private View adBannerLay;
    private AdBannerAdapter ada;
    public static final int MSG_AD_SLID = 002;
    private ViewPagerIndicator vpi;
    private MHandler mHandler;
    private List<CourseBean> cadl;
    private Object courseData;

    public CourseView(FragmentActivity context){
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    private void createView() {
        mHandler = new MHandler();
        initAdData();
        getCourseData();
        initView();
        new AdAutoSlidThread().start();
    }



    class MHandler extends Handler{
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what){
                case MSG_AD_SLID:
                    if (ada.getCount()>0){
                        adPager.setCurrentItem(adPager.getCurrentItem()+1);
                    }
                    break;
            }
        }
    }
    class AdAutoSlidThread extends Thread{
        @Override
        public void run() {
            super.run();
            while (true){
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (mHandler != null)
                    mHandler.sendEmptyMessage(MSG_AD_SLID);
            }
        }
    }

    private void initView(){

    }
    private void initAdData() {
        cadl = new ArrayList<CourseBean>();
        for (int i = 0;i<3;i++){
            CourseBean bean = new CourseBean();
            bean.id=(i+1);
            switch (i){
                case 0:
                    bean.icon="banner_1";
                    break;
                case 1:
                    bean.icon="banner_2";
                    break;
                case 2:
                    bean.icon="banner_3";
                    break;
                default:
                    break;
            }
            cadl.add(bean);
        }
    }

    private void getCourseData(){
        
    }

    public View getView(){
        if (mCurrentView==null){
            createView();
        }return mCurrentView;
    }
    public void showView(){
        if (mCurrentView==null){
            createView();
        }
        mCurrentView.setVisibility(View.VISIBLE);
    }

}
