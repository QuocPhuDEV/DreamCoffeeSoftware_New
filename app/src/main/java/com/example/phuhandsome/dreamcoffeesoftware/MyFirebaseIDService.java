package com.example.phuhandsome.dreamcoffeesoftware;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseIDService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();
        SaveTokenToDatabase(token);
    }

    // Lưu token vào cơ sở dữ liệu riêng
    private void SaveTokenToDatabase(String token) {
        new FireBaseIDTask().execute(token);
    }

}
