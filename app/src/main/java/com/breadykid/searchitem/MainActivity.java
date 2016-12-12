package com.breadykid.searchitem;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.ListView;

import com.breadykid.searchitem.adpater.ItemAdapter;
import com.breadykid.searchitem.domain.Item;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends Activity {
    private final static int SCANNIN_GREQUEST_CODE = 1;

    @Bind(R.id.lv_item_info)
    ListView listView;
    @Bind(R.id.btn_search)
    ImageView btnSearch;

    // 数据分析
    private FirebaseAnalytics mFirebaseAnalytics;
    // 实时数据
    private DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference itemRef = rootRef.child("item_name");
    private DatabaseReference childRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
//        Bundle extras = getIntent().getExtras();
//        if (null != extras) {
//            Log.d("快看看看看看",extras.getString("result"));
//            int width = extras.getInt("width");
//            int height = extras.getInt("height");
//            LayoutParams lps = new LayoutParams(width, height);
//            lps.topMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics());
//            lps.leftMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
//            lps.rightMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
//            btnSearch.setLayoutParams(lps);
//            //存显示扫描到的内容
//            childRef.setValue(extras.getString("result"));
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SCANNIN_GREQUEST_CODE:
                if (resultCode==123){
                    Bundle bundle = data.getExtras();
                    Log.d("啊啊啊啊啊",bundle.getString("result"));
                    //显示
                    btnSearch.setImageBitmap((Bitmap) data.getParcelableExtra("bitmap"));
                    //存显示扫描到的内容
                    childRef.setValue(bundle.getString("result"));
                }
                break;
            default:
                break;
        }
    }

    /**
     * 开启扫描
     */
    @OnClick(R.id.btn_search)
    public void scan() {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, CaptureActivity.class);
        startActivity(intent);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
// 添加分类
        itemRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                dataSnapshot.child("商品b");
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        // 取数据
        childRef = itemRef.child("商品b");
        childRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String demo = dataSnapshot.getValue(String.class);
                if (demo != null) {
                    Log.d("测试", demo);
                    Item item = new Item(demo, demo, demo);
                    listView.setAdapter(new ItemAdapter(getApplicationContext(), item));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
