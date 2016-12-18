package com.breadykid.searchitem.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.breadykid.searchitem.scan.decode.DecodeThread;

/**
 * Created by breadykid on 2016/12/12.
 */
public class ScanPicShow {

    public void showScan(Bundle bundle, Context context, ImageView btnSearch){
//        int width = bundle.getInt("width");
//        int height = bundle.getInt("height");
//        LinearLayout.LayoutParams lps = new LinearLayout.LayoutParams(width, height);
//        lps.topMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, context.getResources().getDisplayMetrics());
//        lps.leftMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, context.getResources().getDisplayMetrics());
//        lps.rightMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, context.getResources().getDisplayMetrics());
//        btnSearch.setLayoutParams(lps);
        Bitmap barcode = null;
        byte[] compressedBitmap = bundle.getByteArray(DecodeThread.BARCODE_BITMAP);
        if (compressedBitmap != null) {
            barcode = BitmapFactory.decodeByteArray(compressedBitmap, 0, compressedBitmap.length, null);
            // Mutable copy:
            barcode = barcode.copy(Bitmap.Config.RGB_565, true);
        }
        btnSearch.setImageBitmap(barcode);// 显示图片
    }
}
