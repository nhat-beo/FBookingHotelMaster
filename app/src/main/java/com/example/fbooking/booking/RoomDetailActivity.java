package com.example.fbooking.booking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fbooking.MainActivity;
import com.example.fbooking.R;
import com.example.fbooking.room.Room;
import com.example.fbooking.room.RoomPhoto;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class RoomDetailActivity extends AppCompatActivity {
    private ImageView imgRoomDetail1, imgRoomDetail2, imgRoomDetail3, imgRoomDetail4;
    private TextView tvRoomNumberDetail, tvRankDetail, tvRoomTypeDetail, tvPeopleDetail,
            tvDescriptionDetai, tvPriceDetail;
    private AppCompatButton btnCancelDetail, btnBookingDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);

        initUi();

        showRoomDetail();

        onClickButton();
    }

    private void showRoomDetail() {

        Room room = (Room) getIntent().getExtras().get("data");

        NumberFormat formatter = new DecimalFormat("#,###");
        String formattedMoney = formatter.format(room.getPriceRoom());

        tvRoomNumberDetail.setText(RoomDetailActivity.this.getString(R.string.phong_detail, room.getRoomNumber()));
        tvRankDetail.setText(RoomDetailActivity.this.getString(R.string.hang_detail, room.getRankRoom()));
        tvRoomTypeDetail.setText(RoomDetailActivity.this.getString(R.string.loai_phong_detail, String.valueOf(room.getTypeRoom())));
        tvPeopleDetail.setText(RoomDetailActivity.this.getString(R.string.so_nguoi_detail, String.valueOf(room.getPeopleRoom())));
        tvDescriptionDetai.setText(RoomDetailActivity.this.getString(R.string.mo_ta_detail, room.getDescription()));

//        String path = room.getRoomPhoto().get(0).getFilename();
//        Glide.with(getApplicationContext()).load(RoomDetailActivity.this.getString(R.string.path, path)).into(imgRoomDetail1);

        try {
            for (int i = 0; i <= room.getRoomPhoto().size(); i++) {
                if (i == 0) {
                    Glide.with(getApplicationContext()).load(RoomDetailActivity.this.getString(R.string.path, room.getRoomPhoto().get(i).getFilename())).into(imgRoomDetail1);
                } else if (i == 1) {
                    Glide.with(RoomDetailActivity.this).load(RoomDetailActivity.this.getString(R.string.path, room.getRoomPhoto().get(i).getFilename())).into(imgRoomDetail2);
                } else if (i == 2) {
                    Glide.with(RoomDetailActivity.this).load(RoomDetailActivity.this.getString(R.string.path, room.getRoomPhoto().get(i).getFilename())).into(imgRoomDetail4);
                } else if (i == 3) {
                    Glide.with(RoomDetailActivity.this).load(RoomDetailActivity.this.getString(R.string.path, room.getRoomPhoto().get(i).getFilename())).into(imgRoomDetail4);
                }
            }
        } catch (IndexOutOfBoundsException ex) {
            Log.d("EXCEPTION", ex.getMessage());
        }

        tvPriceDetail.setText(RoomDetailActivity.this.getString(R.string.vnd, formattedMoney));
    }

    private void onClickButton() {
        btnCancelDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnBookingDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RoomDetailActivity.this, FillInformationActivity.class));
            }
        });
    }

    private void initUi() {
        imgRoomDetail1 = findViewById(R.id.img_room_detail_1);
        imgRoomDetail2 = findViewById(R.id.img_room_detail_2);
        imgRoomDetail3 = findViewById(R.id.img_room_detail_3);
        imgRoomDetail4 = findViewById(R.id.img_room_detail_4);

        tvRoomNumberDetail = findViewById(R.id.tv_room_number_detail);
        tvRankDetail = findViewById(R.id.tv_rank_detail);
        tvRoomTypeDetail = findViewById(R.id.tv_room_type_detail);
        tvPeopleDetail = findViewById(R.id.tv_people_detail);
        tvDescriptionDetai = findViewById(R.id.tv_description_detail);
        tvPriceDetail = findViewById(R.id.tv_price_detail);

        btnCancelDetail = findViewById(R.id.btn_cancel_booking_detail);
        btnBookingDetail = findViewById(R.id.btn_open_booking_detail);

        int height = Resources.getSystem().getDisplayMetrics().heightPixels;
        imgRoomDetail1.getLayoutParams().height = height / 5;
        imgRoomDetail2.getLayoutParams().height = height / 6;
        imgRoomDetail3.getLayoutParams().height = height / 6;
        imgRoomDetail4.getLayoutParams().height = height / 6;
    }
}