package com.yijia.myview;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.yijia.zhenglei.R;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by DELL on 2017/4/19.
 */
public class ImageDetailFragment extends Fragment {
    private int imageUrl;
    private ImageView imageView;
    private ProgressBar progressBar;
    private PhotoViewAttacher photoViewAttacher;
    public static ImageDetailFragment newInstance(int imageUrl){
        final ImageDetailFragment f=new ImageDetailFragment();
        final Bundle bundle=new Bundle();
        bundle.putInt("url",imageUrl);
        f.setArguments(bundle);
        return f;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageUrl = getArguments()!=null ? getArguments().getInt("url"):null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.image_detail_fragment,container,false);
        imageView = (ImageView) view.findViewById(R.id.image);
        photoViewAttacher = new PhotoViewAttacher(imageView);
        photoViewAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {

            @Override
            public void onPhotoTap(View arg0, float arg1, float arg2) {
                getActivity().finish();
            }

            @Override
            public void onOutsidePhotoTap() {

            }
        });
        progressBar = (ProgressBar) view.findViewById(R.id.loading);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        imageView.setImageResource(imageUrl);
//        ImageLoader.getInstance().displayImage(imageUrl, imageView, new SimpleImageLoadingListener() {
//            @Override
//            public void onLoadingStarted(String imageUri, View view) {
//                progressBar.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
//                String message = null;
//                switch (failReason.getType()) {
//                    case IO_ERROR:
//                        message = "下载错误";
//                        break;
//                    case DECODING_ERROR:
//                        message = "图片无法显示";
//                        break;
//                    case NETWORK_DENIED:
//                        message = "网络有问题，无法下载";
//                        break;
//                    case OUT_OF_MEMORY:
//                        message = "图片太大无法显示";
//                        break;
//                    case UNKNOWN:
//                        message = "未知的错误";
//                        break;
//                }
//                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
//                progressBar.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
//                progressBar.setVisibility(View.GONE);
//                photoViewAttacher.update();
//            }
//        });
    }
}
