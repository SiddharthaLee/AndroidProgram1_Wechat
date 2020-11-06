package com.hubuandroid.mywechat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.hubuandroid.mywechat.adapers.ListViewAdapter;
import com.hubuandroid.mywechat.beans.Datas;
import com.hubuandroid.mywechat.beans.ItemBean;

import java.util.ArrayList;
import java.util.List;

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
    private RecyclerView mList;
    private List<ItemBean> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉界面title
        setContentView(R.layout.activity_main);

        initView();
        initEvent();
        initFragment();
        selectFragment(0);

        //找到控件
        mList = (RecyclerView) this.findViewById(R.id.recycler_view);
        //准备数据
        initData();
    }

    /**
     * 这个方法用于模拟数据
     */
    private void initData() {
        //创建模拟数据

        //创建数据集合
        mData = new ArrayList<>();

        for (int i = 0; i < Datas.icons.length; i++){
            //创建数据对象
            ItemBean data = new ItemBean();
            data.icon = Datas.icons[i];
            data.title = "我是第"+i+"个条目";
            //添加到集合中
            mData.add(data);
        }

        //RecyclerView需要设置样式（布局管理器）
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        mList.setLayoutManager(layoutManager);

        //创建适配器
        ListViewAdapter adapter = new ListViewAdapter(mData);
        //设置到recycler view中去
        mList.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();
        switch (itemId){
            //ListView部分
            case R.id.list_view_vertical_standard:
                break;
            case R.id.list_view_vertical_reverse:
                break;
            case R.id.list_view_horizontal_standard:
                break;
            case R.id.list_view_horizontal_reverse:
                break;
            //GridView部分
            case R.id.grid_view_vertical_standard:
                break;
            case R.id.grid_view_vertical_reverse:
                break;
            case R.id.grid_view_horizontal_standard:
                break;
            case R.id.grid_view_horizontal_reverse:
                break;
            //瀑布流部分
            case R.id.stagger_view_vertical_standard:
                break;
            case R.id.stagger_view_vertical_reverse:
                break;
            case R.id.stagger_view_horizontal_standard:
                break;
            case R.id.stagger_view_horizontal_reverse:
                break;
        }


        return super.onOptionsItemSelected(item);
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
