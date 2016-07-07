package com.liuwei.rxjava;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.liuwei.rxjava.bean.Stduent;
import com.liuwei.rxjava.ui.BigHandActivity;
import com.liuwei.rxjava.ui.ListViewActivity;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    private Context mContext;
    private Button button;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private ImageView imageView;
    DrawerLayout mDrawerLayout;
    LinearLayout llLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        Log.e("test", "addc");
    }

    private void initView() {
        mContext = MainActivity.this;
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        imageView = (ImageView) findViewById(R.id.imageView);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.mDrawerLayout);
        llLeft = (LinearLayout) findViewById(R.id.llLeft);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(">>", "onDestroy");
    }

    @Override
    protected boolean isSupportSwipeBack() {
        return false;
    }

    private void initListener() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "click");
                splitArray();
                View view = findViewById(R.id.llLeft);
                mDrawerLayout.openDrawer(view);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "click2");
                loadImage();
                if (imageView.getVisibility() == View.VISIBLE) {
                    imageView.setVisibility(View.GONE);
                } else {
                    imageView.setVisibility(View.VISIBLE);
                }
            }
        });
        //线程控制
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "click3");
                scheduler();
            }
        });
        //
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "click4");
                printStudent();
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "click5");
                Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
                startActivity(intent);
            }
        });
        //!
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "click6");
                Intent intent = new Intent(MainActivity.this, BigHandActivity.class);
                startActivity(intent);
            }
        });
    }


    private void printStudent() {
        //set date
        Stduent s = new Stduent();
        s.setName("张三");
        Stduent s2 = new Stduent();
        s2.setName("李四");
        Stduent[] stduents = {s, s2};
        //
        Observable.from(stduents)
                .map(new Func1<Stduent, String>() {
                    @Override
                    public String call(Stduent stduent) {
                        return stduent.getName();
                    }
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onNext(String s) {
                        Log.e(TAG, "student->" + s);
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
    }

    private void scheduler() {
        Observable.just(1, 2, 3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.e(TAG, "callback>>" + integer);
                    }
                });
    }

    private void loadImage() {
        final int imageResource = R.mipmap.test;
        //被观察
        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Drawable mDrawable = getResources().getDrawable(imageResource);
                subscriber.onNext(mDrawable);
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())   //subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Observer<Drawable>() {
                    @Override
                    public void onNext(Drawable drawable) {
                        //加载图片
                        imageView.setImageDrawable(drawable);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(mContext, "图片加载Error!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * 分割字符串
     */
    private void splitArray() {
        final List<String> list = new ArrayList<String>();
        String array[] = {"例子1", "例子2", "例子3"};
        Observable.from(array)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.e(TAG, s);
                        list.add(s);
                    }
                });

        for (int i = 0; i < list.size(); i++) {
            Log.e(TAG, "list>>>" + list.get(i));
        }
    }


    private void example() {
        //1.观察者
        Observer<String> mObserver = new Observer<String>() {
            @Override
            public void onNext(String s) {
                Log.e(TAG, "Item: " + s);
            }

            @Override
            public void onCompleted() {
                Log.e(TAG, "Completed!");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "Error!");
            }

        };
        //.subscribeOn() 指定观察者线程

        //2.被观察者    (Observable||Subscribe)
        Observable mObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello");
                subscriber.onCompleted();
            }
        });
        //.observeOn() 指定被观察者线程

        //订阅
        mObservable.subscribe(mObserver);
    }
}
