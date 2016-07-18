package com.liuwei.rxjava.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.liuwei.rxjava.BaseActivity;
import com.liuwei.rxjava.R;
import com.liuwei.rxjava.fragment.FragmentOne;
import com.liuwei.rxjava.fragment.FragmentThree;
import com.liuwei.rxjava.fragment.FragmentTwo;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

public class BigHandActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    //
    private ViewPager mViewPager;
    //    private Toolbar toolBar;
    private TabLayout tabLayout;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_hand);
        initView();
        initListener();
        //set actionbar
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        //
        tabLayout.setTabTextColors(Color.rgb(108, 108, 108), Color.rgb(206, 61, 58));
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return 6;
            }

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new FragmentOne();
                    case 1:
                        return new FragmentTwo();
                    case 2:
                        return new FragmentThree();
                    case 3:
                        return new FragmentThree();
                    case 4:
                        return new FragmentThree();
                    case 5:
                        return new FragmentThree();
                    default:
                        return null;
                }
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "头条";
                    case 1:
                        return "科技";
                    case 2:
                        return "手机";
                    case 3:
                        return "数码";
                    case 4:
                        return "段子";
                    case 5:
                        return "网易号";
                    default:
                        return null;
                }
            }
        });
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        tabLayout = (TabLayout) findViewById(android.R.id.tabs);
    }

    private void initListener() {
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
//                d1();
                d1Easy();
                break;
            case R.id.button2:
//                d2();
                d2Convert();
                break;
            case R.id.button3:
                d3();
//                d3Url();
                break;
            case R.id.button4:
                d4();
                break;
        }
    }


    private void d4() {
        Observable.just("hello word")
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onNext(String s) {
                        Log.e("onNext", ">" + s);
                    }

                    @Override
                    public void onCompleted() {
                        Log.e("onCompleted", ">");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("onError", ">" + e.getMessage());
                    }
                });
    }


    /**
     * test flatMap
     */
    Observable<List<String>> query(String text) {
        return null;
    }

    Observable<String> getTitle(String URL) {
        return null;
    }

    private void d3Url() {
        query("Hello word")
                .flatMap(new Func1<List<String>, Observable<String>>() {
                    @Override
                    public Observable<String> call(List<String> strings) {
                        return Observable.from(strings);
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.e("-", "-" + s);
                    }
                });
    }

    /**
     * The from be used for receive a array.
     * <p/>
     * You can output all value in the array.
     */
    private void d3() {
        String[] array = {"A", "B", "C"};
        Observable.from(array)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.e(">>", ">>" + s);
                    }
                });
    }

    private void d2Convert() {
        Observable.just("hello word")
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return s.hashCode();
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.e(">>", "convert>>" + integer.toString());
                    }
                });
    }

    private void d2() {
        Observable.just("hello word")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + " by liuwei";
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.e(">>", "s>" + s);
                    }
                });
    }

    private void d1Easy() {
        Observable.just("hello word easy")
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.e("easy", ">" + s);
                    }
                });
    }

    /**
     * Observables（被观察者，事件源）
     * Subscribers（观察者）
     * Observables发出一系列事件，Subscribers处理这些事件
     */
    private void d1() {
        //被观察(订阅者)
        Observable<String> mObservable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext("hello word");
                        subscriber.onCompleted();
                    }
                }
        );
        //观察
        Subscriber<String> mSubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e(">", ">" + s);
            }
        };
        //关联(subscribe订阅)
        mObservable.subscribe(mSubscriber);
    }
}
