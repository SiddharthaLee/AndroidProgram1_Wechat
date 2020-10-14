package com.hubuandroid.mywechat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout mTabWeChat;
    private LinearLayout mTabFriend;
    private LinearLayout mTabHappy;
    private LinearLayout mTabSettings;

    private ImageButton mImageWeChat;
    private ImageButton mImageFriend;
    private ImageButton mImageHappy;
    private ImageButton mImageSettings;

    private Fragment mTab01 = new WeChatFragment();
    private Fragment mTab02 = new FriendFragment();
    private Fragment mTab03 = new HappyFragment();
    private Fragment mTab04 = new SettingFragment();

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉界面title
        setContentView(R.layout.activity_main);

        initView();
        initEvent();
        initFragment();
        selectFragment(0);
    }

    private void initFragment(){
    //fragmentManager = getFragmentManager();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.content,mTab01);
        transaction.add(R.id.content,mTab02);
        transaction.add(R.id.content,mTab03);
        transaction.add(R.id.content,mTab04);
        transaction.commit();
    }


    private void initView(){
        mTabWeChat = (LinearLayout) findViewById(R.id.id_tab_weixin);
        mTabFriend = (LinearLayout) findViewById(R.id.id_tab_friend);
        mTabHappy = (LinearLayout) findViewById(R.id.id_tab_contact);
        mTabSettings = (LinearLayout) findViewById(R.id.id_tab_settings);

        mImageWeChat = (ImageButton) findViewById(R.id.id_tab_weixin_img);
        mImageFriend = (ImageButton) findViewById(R.id.id_tab_friend_img);
        mImageHappy = (ImageButton) findViewById(R.id.id_tab_contact_img);
        mImageSettings = (ImageButton) findViewById(R.id.id_tab_settings_img);
    }

    private void hideFragment(FragmentTransaction transaction){
        transaction.hide(mTab01);
        transaction.hide(mTab02);
        transaction.hide(mTab03);
        transaction.hide(mTab04);
    }

    private void selectFragment(int i){//i是一个选择器，用于选择显示哪个界面
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragment(transaction);//首先隐藏所有页面

        switch (i){
            case 0:
                transaction.show(mTab01);
                mImageWeChat.setImageResource(R.drawable.tab_weixin_exp);
                break;
            case 1:
                transaction.show(mTab02);
                mImageFriend.setImageResource(R.drawable.tab_frie_exp);
                break;
            case 2:
                transaction.show(mTab03);
                mImageHappy.setImageResource(R.drawable.tab_adr_exp);
                break;
            case 3:
                transaction.show(mTab04);
                mImageSettings.setImageResource(R.drawable.tab_set_expe);
                break;
            default:
                break;
        }
        transaction.commit();
    }

    @Override
    public void onClick(View view) {
        //Log.d("onClick","1");
        resetImg();
        switch (view.getId()){
            case R.id.id_tab_weixin:
                selectFragment(0);
                break;
            case R.id.id_tab_friend:
                selectFragment(1);
                break;
            case R.id.id_tab_contact:
                selectFragment(2);
                break;
            case R.id.id_tab_settings:
                selectFragment(3);
                break;
            default:
                break;
        }
    }

    @SuppressLint("ResourceAsColor")
    private void resetImg(){
        mImageWeChat.setImageResource(R.drawable.tab_weixin_normal);
        mImageFriend.setImageResource(R.drawable.tab_find_frd_normal);
        mImageHappy.setImageResource(R.drawable.tab_address_normal);
        mImageSettings.setImageResource(R.drawable.tab_settings_normal);

    }
    //仅仅对bottom的四个linerlayout监听
    private void initEvent(){
        mTabWeChat.setOnClickListener(this);
        mTabFriend.setOnClickListener(this);
        mTabHappy.setOnClickListener(this);
        mTabSettings.setOnClickListener(this);
    }
}
