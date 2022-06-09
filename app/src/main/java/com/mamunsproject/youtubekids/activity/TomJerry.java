package com.mamunsproject.youtubekids.activity;

import static com.mamunsproject.youtubekids.ui.fragments.CategoryFragmentJava.API_KEY_MAINFROM_CAT;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartoontuberefinefinal.Model.ResponseVideo;
import com.example.cartoontuberefinefinal.Model.Video;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mamunsproject.youtubekids.R;
import com.mamunsproject.youtubekids.adapter.VIdeoAdapter2;
import com.mamunsproject.youtubekids.networkRequest.ApiClient;
import com.mamunsproject.youtubekids.networkRequest.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TomJerry extends AppCompatActivity {

    VIdeoAdapter2 vIdeoAdapter;
    ArrayList<Video> arrayListVideo;
    ApiInterface apiInterface;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tom_jerry);

        progressBar=findViewById(R.id.progressBarID);

        Log.d("GETPATGETOKEN", "onCreate: "+API_KEY_MAINFROM_CAT);

        FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
        DocumentReference documentReference=firebaseFirestore.
                collection("AllPlayListKEY").document( "TOM_JERRY_ID");


        documentReference
                .get().addOnSuccessListener(documentSnapshot -> {

                    if (documentSnapshot.exists()){

                        String key=documentSnapshot.getString("TOM_JERRY_ID");

                        getVideos(key);


                    }else {
                        Toast.makeText(getApplicationContext(), "Does'nt Exist", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });



        RecyclerView recyclerView =findViewById(R.id.recyclerMain);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        arrayListVideo = new ArrayList<>();
        vIdeoAdapter = new VIdeoAdapter2(getApplicationContext(), arrayListVideo);
        recyclerView.setAdapter(vIdeoAdapter);


    }


    private void getVideos(String key) {

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ResponseVideo> callVideo = apiInterface.getAllVideosFromNonSuspendFun(3000, key
                , API_KEY_MAINFROM_CAT);

        Log.d("GETPATGETOKEN", "onCreate: 2"+API_KEY_MAINFROM_CAT);

        callVideo.enqueue(new Callback<ResponseVideo>() {
            @Override
            public void onResponse(Call<ResponseVideo> call, Response<ResponseVideo> response) {

                ResponseVideo responseVideo = response.body();
                if (responseVideo != null) {
                    progressBar.setVisibility(View.GONE);
                    if (responseVideo.getItems().size() > 0) {
                        for (int i = 0; i < responseVideo.getItems().size(); i++) {
                            arrayListVideo.add(responseVideo.getItems().get(i));
                        }
                        vIdeoAdapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "There is No Video In Your Channel!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseVideo> call, Throwable t) {

            }
        });

    }


}