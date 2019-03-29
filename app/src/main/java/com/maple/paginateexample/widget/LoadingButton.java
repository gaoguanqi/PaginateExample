package com.maple.paginateexample.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.maple.paginateexample.R;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * author: gaogq
 * time: 2019/3/22 17:15
 * description:
 */
public class LoadingButton extends FrameLayout {
    private final String TGA = "LoadingButton";

    private ProgressBar progressBar;
    private ImageView imageView;
    private TextView textView;
    private View view;

    private TypedArray array;

    private int bg = R.drawable.ic_bg_blue_buttons_style;
    private int successBg = R.drawable.ic_bg_blue_buttons_style;
    private int errorBg = R.drawable.ic_bg_red_buttons_style;
    private int successIcon = R.drawable.ic_done_white_24dp;
    private int errorIcon = R.drawable.ic_warning;

    public LoadingButton(@NonNull Context context) {
        this(context,null);
    }

    public LoadingButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadingButton( @NonNull Context context,  @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context,attrs,defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {

        if(context != null){
            view = inflate(context, R.layout.layout_loading_button_lb, null);
            if(view != null){
                progressBar = view.findViewById(R.id.pb);
                imageView = view.findViewById(R.id.img);
                textView = view.findViewById(R.id.tvText);
                addView(view);
            }
            array = context.obtainStyledAttributes(attrs, R.styleable.app, defStyleAttr, 0);
            if(array != null){
                //view
                int mBg = array.getResourceId(R.styleable.app_background, 0);
                if (mBg != 0) {
                    setBackgroundResource(mBg);
                    bg = mBg;
                }

                int mSuccessBg = array.getResourceId(R.styleable.app_successBackground, 0);
                if (mSuccessBg != 0) {
                    successBg = mSuccessBg;
                }
                int mErrorBg = array.getResourceId(R.styleable.app_errorBackground, 0);
                if (mErrorBg != 0) {
                    errorBg = mErrorBg;
                }

                int mSuccessIcon = array.getResourceId(R.styleable.app_successIcon, 0);
                if (mSuccessIcon != 0) {
                    successIcon = mSuccessIcon;
                }

                int mErrorIcon = array.getResourceId(R.styleable.app_errorIcon, 0);
                if (mErrorIcon != 0) {
                    errorIcon = mErrorIcon;
                }

                //pb
                int pbSize = array.getInteger(R.styleable.app_progressBarSize, 32);
                pbSize = Math.round(pbSize * (Resources.getSystem().getDisplayMetrics().density));
                LayoutParams pbParams = new LayoutParams(WRAP_CONTENT, pbSize);
                pbParams.gravity = Gravity.CENTER;

                if(progressBar != null){
                    progressBar.getIndeterminateDrawable();
                    Drawable d = progressBar.getIndeterminateDrawable();
                    if(d != null){
                        d.setColorFilter( array.getColor(R.styleable.app_progressColor, context.getResources().getColor(R.color.colorAccent)),
                                PorterDuff.Mode.SRC_ATOP);
                    }
                    progressBar.setLayoutParams(pbParams);
                }

                //tv
                if(textView != null){
                    //m
                    textView.setText(TGA);
                    float size = array.getDimension(R.styleable.app_textSize, 14f * Resources.getSystem().getDisplayMetrics().density);
                    Typeface tf = null;
                    CharSequence text = array.getText(R.styleable.app_text);
                    if(text != null){

                    }

                }
                

                array.recycle();
            }
        }


    }


}
