package com.mamunsproject.youtubekids.activity;

import static com.mamunsproject.youtubekids.ui.fragments.CategoryFragmentJava.API_KEY_MAINFROM_CAT;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartoontuberefinefinal.Model.ResponseVideo;
import com.example.cartoontuberefinefinal.Model.Video;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mamunsproject.youtubekids.R;
import com.mamunsproject.youtubekids.adapter.VIdeoAdapter2;
import com.mamunsproject.youtubekids.networkRequest.ApiClient;
import com.mamunsproject.youtubekids.networkRequest.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BanglaCartoon extends AppCompatActivity {

    VIdeoAdapter2 vIdeoAdapter;
    ArrayList<Video> arrayListVideo;
    ApiInterface apiInterface;
    ProgressBar progressBar;
    String key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bangla_cartoon);

        progressBar = findViewById(R.id.progressBarID);



                FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
                DocumentReference documentReference = firebaseFirestore.
                        collection("AllPlayListKEY").document("BanglaCartoonPlayListKEY");


                documentReference
                        .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        if (documentSnapshot.exists()) {

                            key = documentSnapshot.getString("1");
                            getVideos(key);


                        } else {
                            Toast.makeText(getApplicationContext(), "Does'nt Exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(e -> {

                });



        RecyclerView recyclerView = findViewById(R.id.banglaCartoonRecyclerview);
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

    public void checkFunction() {


    }

}