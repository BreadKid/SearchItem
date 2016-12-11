package com.breadykid.searchitem;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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

public class MainActivity extends Activity {

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
    protected void onStart() {
        super.onStart();

    }

    @OnClick(R.id.btn_search)
    public void scan(){
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
        childRef = itemRef.child("商品b");
        childRef.setValue("apple");// 存
        // 取
        childRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String demo = dataSnapshot.getValue(String.class);
                if (demo!=null){
                    Log.d("测试",demo);
                    Item item = new Item(demo,demo,demo);
                    listView.setAdapter(new ItemAdapter(getApplicationContext(),item));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
