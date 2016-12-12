package com.breadykid.searchitem;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.ListView;

import com.breadykid.searchitem.adpater.ItemAdapter;
import com.breadykid.searchitem.domain.Item;
import com.breadykid.searchitem.http.Http;
import com.breadykid.searchitem.http.MessageResponse;
import com.breadykid.searchitem.scan.decode.DecodeThread;
import com.breadykid.searchitem.util.ScanPicShow;
import com.breadykid.searchitem.util.StaticUtil;
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
import android.widget.Toast;

public class MainActivity extends Activity{

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

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("请求码", requestCode + "");
        switch (requestCode) {
            case SCANNIN_GREQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    if (null != bundle) {
                        Log.d("返回界面所得值", bundle.getString("result"));
                        final String scanCode = bundle.getString("result");
                        new ScanPicShow().showScan(bundle, getApplicationContext(), btnSearch);
                        if (scanCode.startsWith("http")) { // URL
                            listView.setAdapter(new ItemAdapter(getApplicationContext(), scanCode));
                            Toast.makeText(this, "这是个链接哟！", Toast.LENGTH_SHORT).show();
                        } else if (scanCode.length() == 8 || scanCode.length() == 13) { // 条形码
                            Http.getInstance().searchItem(scanCode, new MessageResponse() {
                                @Override
                                public void onReceivedSuccess(Object o) {
                                    Item itemO= (Item) o;
                                    saveDB(itemO);
                                }
                            });
                        }
                    }

                }
                break;
            default:
                break;
        }
    }

    private void saveDB(final Item itemSave){
        // 添加存储分类
        itemRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                dataSnapshot.child(itemSave.getCode());
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
        childRef = itemRef.child(itemSave.getCode());
        //存显示扫描到的内容
        childRef.setValue(itemSave.toString());

        // 取数据
        childRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String demo = dataSnapshot.getValue(String.class);
                if (demo != null) {
                    Log.d("测试", demo);

                    listView.setAdapter(new ItemAdapter(getApplicationContext(), demo));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    /**
     * 开启扫描
     */
    @OnClick(R.id.btn_search)
    public void scan() {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, CaptureActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
    }


}
