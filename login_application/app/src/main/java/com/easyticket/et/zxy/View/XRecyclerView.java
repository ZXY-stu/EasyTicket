package com.easyticket.et.zxy.View;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.easyticket.et.zxy.R;

/**
 * Created by Administrator on 2018/4/9.
 */

 public  class  XRecyclerView extends  RecyclerView {

       private static  int status = 1 ;

    public static  int getStatus() {
        return status;
    }



    public XRecyclerView(Context context){
        this(context,null);
        init();
    }

    public XRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public XRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    private  void init(){
        addOnScrollListener(new ScrollStatusListener());
    }

    public class ScrollStatusListener extends OnScrollListener {
        public ScrollStatusListener() {
            super();
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
      //      Toast.makeText(getContext(),"yes"+newState,Toast.LENGTH_LONG).show();
            switch (newState) {
                case SCROLL_STATE_IDLE:
                    try {
                     if(getContext()!=null) {
                        status = 1;
                        Glide.with(recyclerView.getContext()).resumeRequests();


                     }
                    } catch (Exception e) {

                    }
                    break;
                case SCROLL_STATE_DRAGGING:
                    try {
                        if (getContext()!=null) {
                            Glide.with(recyclerView.getContext()).pauseRequests();
                            status =0;


                        }
                    }catch (Exception e){

                    }
                    break;
                case SCROLL_STATE_SETTLING:
                    try {
                        status =0;
                        if (getContext()!=null) {
                            Glide.with(recyclerView.getContext()).pauseRequests();

                        }
                    }catch (Exception e){

                    }
                    break;
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }
    }

}
