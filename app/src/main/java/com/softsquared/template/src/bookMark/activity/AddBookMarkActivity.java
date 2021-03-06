package com.softsquared.template.src.bookMark.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

import com.softsquared.template.R;
import com.softsquared.template.src.BaseActivity;
import com.softsquared.template.src.BookMarkData;
import com.softsquared.template.src.bookMark.interfaces.AddBookMarkActivityView;
import com.softsquared.template.src.bookMark.models.BookMarkResponse;
import com.softsquared.template.src.bookMark.service.AddBookMarkActivityService;
import com.softsquared.template.src.main.MainActivity;

import java.util.ArrayList;

public class AddBookMarkActivity extends BaseActivity implements AddBookMarkActivityView {
    Button mAddBookMarkLoc;
    ArrayList<BookMarkData> list;
    ArrayList<BookMarkResponse.Result> mResultList;
    int mResponseCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actList.add(this);
        setContentView(R.layout.activity_add_book_mark);
        mAddBookMarkLoc = findViewById(R.id.btn_add_book_mark_loc);

        mAddBookMarkLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText loc = findViewById(R.id.et_input_loc);
                String locStr = loc.getText().toString();
                // locStr을 api로 보내서 정보를 받아오기
                getLocation(locStr);
            }
        });
    }

    public void getLocation(String loc) {
        showProgressDialog();
        final AddBookMarkActivityService addBookMarkActivityService = new AddBookMarkActivityService(this);
        addBookMarkActivityService.getLocationName(loc);
    }

    @Override
    public void validateSuccess(String message) {
        hideProgressDialog();
        Log.e("validateSuccess" , "message : " + message);

    }

    @Override
    public void validateFailure(String text) {
        hideProgressDialog();
        Log.e("validateSuccess" , "message : " + text);

    }

    @Override
    public void getCode(int code) {
        mResponseCode = code;
        Log.e("getRegionCode" , "message : " + code);
        if(mResponseCode != 100){
//            AlertDialog.Builder builder = new AlertDialog.Builder(AddBookMarkActivity.this);
//            builder.setTitle("경고!").setMessage("올바르지 않은 주소입니다.");
//            AlertDialog alertDialog = builder.create();
//            alertDialog.show();
        }
        else{


            String locStr = mResultList.get(0).getRegion_2depth_name() + " "  + mResultList.get(0).getRegion_3depth_name();
            Log.e("AddBookMarkActivity", "loccatio result"+ mResultList.get(0).getRegion_3depth_name());
            BookMarkData newData = new BookMarkData(locStr);
            newData.tm_x = mResultList.get(0).getTm_x().doubleValue();
            newData.tm_y = mResultList.get(0).getTm_y().doubleValue();
            list = getBookMarkList();
            list.add(newData);
            saveBookMarkList(list);
            actFinish();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("restart", true);
            startActivity(intent);

            finish();
        }
    }

    @Override
    public void getResult(ArrayList<BookMarkResponse.Result> result) {
        mResultList = result;
        Log.e("getRegionResult" , "message : " + result.size());
    }
}
