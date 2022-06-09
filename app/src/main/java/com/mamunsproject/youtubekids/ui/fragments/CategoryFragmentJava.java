package com.mamunsproject.youtubekids.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.cartoontuberefinefinal.Model.PlayLIstModel;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mamunsproject.youtubekids.R;
import com.mamunsproject.youtubekids.activity.BanglaCartoon;
import com.mamunsproject.youtubekids.activity.Ben10;
import com.mamunsproject.youtubekids.activity.Doraemon;
import com.mamunsproject.youtubekids.activity.Hindi_Cartoon;
import com.mamunsproject.youtubekids.activity.MotuPatlu;
import com.mamunsproject.youtubekids.activity.MrBeanCartoon;
import com.mamunsproject.youtubekids.activity.Oggy_and_coakroaches;
import com.mamunsproject.youtubekids.activity.TomJerry;
import com.mamunsproject.youtubekids.adapter.AdapterForPlayList;
import com.mamunsproject.youtubekids.adapter.interfacew.OnRecyclerViewITemClickListener;

import java.util.ArrayList;
import java.util.List;


public class CategoryFragmentJava extends Fragment implements OnRecyclerViewITemClickListener {

    AdapterForPlayList adapterForPlayList;
    List<PlayLIstModel> list;
    RecyclerView recyclerView;
    String key;
    public static String API_KEY_MAINFROM_CAT;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category_java, container, false);


        FirebaseFirestore firebaseFirestore1 = FirebaseFirestore.getInstance();
        DocumentReference documentReference1 = firebaseFirestore1.
                collection("API_KEY").document("Main_Api");


        documentReference1
                .get().addOnSuccessListener(documentSnapshot -> {

            if (documentSnapshot.exists()) {


                API_KEY_MAINFROM_CAT = documentSnapshot.getString("api_field");
                Log.d("GETPATGETOKEN", "onResponse: " + API_KEY_MAINFROM_CAT);


            } else {
                Toast.makeText(getContext(), "Does'nt Exist", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(e -> {

        });


        list = new ArrayList<>();
        recyclerView = view.findViewById(R.id.rc_playlist);


        list.add(new PlayLIstModel(R.drawable.tom_and_jerrry, "Tom&Jerry"));
        list.add(new PlayLIstModel(R.drawable.motupatlu, "Moto Patlu"));
        list.add(new PlayLIstModel(R.drawable.doraemon, "Doreamon"));
        list.add(new PlayLIstModel(R.drawable.ben10, "Ben 10"));
        list.add(new PlayLIstModel(R.drawable.oggyfinal, "Oggy and CoakRoaches"));
        list.add(new PlayLIstModel(R.drawable.mrbean, "Mr Bean"));
        list.add(new PlayLIstModel(R.drawable.hindicartoon, "Hindi Cartoon"));
        list.add(new PlayLIstModel(R.drawable.banglacarton, "Bangla Cartoon"));


        adapterForPlayList = new AdapterForPlayList(list, this::onRecItemClick);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(adapterForPlayList);

        return view;

    }

    @Override
    public void onRecItemClick(int position) {


//        if (position == 0) {
//            startActivity(new Intent(getContext(), TomJerry.class));
//        }

        switch (position) {
            case 0:
                startActivity(new Intent(getContext(), TomJerry.class));
                Animatoo.animateInAndOut(getContext());
                break;

            case 1:
                startActivity(new Intent(getContext(), MotuPatlu.class));
                Animatoo.animateInAndOut(getContext());

                break;

            case 2:
                startActivity(new Intent(getContext(), Doraemon.class));
                Animatoo.animateInAndOut(getContext());

                break;

            case 3:
                startActivity(new Intent(getContext(), Ben10.class));
                Animatoo.animateInAndOut(getContext());

                break;

            case 4:
                startActivity(new Intent(getContext(), Oggy_and_coakroaches.class));
                Animatoo.animateInAndOut(getContext());

                break;
            case 5:
                startActivity(new Intent(getContext(), MrBeanCartoon.class));
                Animatoo.animateInAndOut(getContext());

                break;
            case 6:
                startActivity(new Intent(getContext(), Hindi_Cartoon.class));
                Animatoo.animateInAndOut(getContext());

                break;
            case 7:
                startActivity(new Intent(getContext(), BanglaCartoon.class));
                Animatoo.animateInAndOut(getContext());

                break;


        }

    /*    if (position == 0) {
            NavController navController = NavHostFragment.findNavController(this);


            navController.navigate(R.id.action_categoryFragmentJava2_to_tomJerryFragment,
                    null,
                    new NavOptions.Builder()
                            .setEnterAnim(R.anim.animate_slide_in_left)
                            .setExitAnim(R.anim.animate_slide_out_right)
                            .build());
        } else if (position == 1) {
            NavController navController1 = NavHostFragment.findNavController(this);

            navController1.navigate(R.id.action_categoryFragmentJava2_to_motuPatluFragment, null, new NavOptions.Builder()
                    .setEnterAnim(R.anim.animate_slide_in_left)
                    .setExitAnim(R.anim.animate_slide_out_right)
                    .build());
        } else if (position == 2) {
            NavController navController2r = NavHostFragment.findNavController(this);

            navController2r.navigate(R.id.action_categoryFragmentJava2_to_doreamonFragment, null, new NavOptions.Builder()
                    .setEnterAnim(R.anim.animate_slide_in_left)
                    .setExitAnim(R.anim.animate_slide_out_right)
                    .build());
        } else if (position == 3) {
            NavController navController3 = NavHostFragment.findNavController(this);

            navController3.navigate(R.id.action_categoryFragmentJava2_to_ben10Fragment, null, new NavOptions.Builder()
                    .setEnterAnim(R.anim.animate_slide_in_left)
                    .setExitAnim(R.anim.animate_slide_out_right)
                    .build());

        } else if (position == 4) {
            NavController navController4 = NavHostFragment.findNavController(this);

            navController4.navigate(R.id.action_categoryFragmentJava2_to_oggyAndCoakRoachFragment, null, new NavOptions.Builder()
                    .setEnterAnim(R.anim.animate_slide_in_left)
                    .setExitAnim(R.anim.animate_slide_out_right)
                    .build());
        } else if (position == 5) {
            NavController navController5 = NavHostFragment.findNavController(this);

            navController5.navigate(R.id.action_categoryFragmentJava2_to_mrBeanFragment, null, new NavOptions.Builder()
                    .setEnterAnim(R.anim.animate_slide_in_left)
                    .setExitAnim(R.anim.animate_slide_out_right)
                    .build());
        } else if (position == 6) {
            NavController navController6 = NavHostFragment.findNavController(this);

            navController6.navigate(R.id.action_categoryFragmentJava2_to_hindiCartoonFragment, null, new NavOptions.Builder()
                    .setEnterAnim(R.anim.animate_slide_in_left)
                    .setExitAnim(R.anim.animate_slide_out_right)
                    .build());

        } else if (position == 7) {
            NavController navController7 = NavHostFragment.findNavController(this);

            navController7.navigate(R.id.action_categoryFragmentJava2_to_ban, null, new NavOptions.Builder()
                    .setEnterAnim(R.anim.animate_slide_in_left)
                    .setExitAnim(R.anim.animate_slide_out_right)
                    .build());

        }*/


  /*      switch (position) {


            case 0:
                NavController navController = NavHostFragment.findNavController(this);


                navController.navigate(R.id.action_playlistFragments_to_tom_Jerry_Fragment,
                        null,
                        new NavOptions.Builder()
                                .setEnterAnim(android.R.animator.fade_in)
                                .setExitAnim(android.R.animator.fade_out)
                                .build());





            case 1:
                NavController navController1 = NavHostFragment.findNavController(this);

                navController1.navigate(R.id.action_playlistFragments_to_motuPatluFragment, null, new NavOptions.Builder()
                                .setEnterAnim(android.R.animator.fade_in)
                                .setExitAnim(android.R.animator.fade_out)
                                .build());


            case 2:
                NavController navController2r = NavHostFragment.findNavController(this);

                navController2r.navigate(R.id.action_playlistFragments_to_doaemonFragment, null, new NavOptions.Builder()
                                .setEnterAnim(android.R.animator.fade_in)
                                .setExitAnim(android.R.animator.fade_out)
                                .build());


            case 3:
                NavController navController3 = NavHostFragment.findNavController(this);

                navController3.navigate(R.id.ben10, null, new NavOptions.Builder()
                                .setEnterAnim(android.R.animator.fade_in)
                                .setExitAnim(android.R.animator.fade_out)
                                .build());


            case 4:
                NavController navController4 = NavHostFragment.findNavController(this);

                navController4.navigate(R.id.oggyAndCokroachesFragment, null, new NavOptions.Builder()
                                .setEnterAnim(android.R.animator.fade_in)
                                .setExitAnim(android.R.animator.fade_out)
                                .build());


            case 5:
                NavController navController5 = NavHostFragment.findNavController(this);

                navController5.navigate(R.id.mrBeanCartoonFragment, null, new NavOptions.Builder()
                                .setEnterAnim(android.R.animator.fade_in)
                                .setExitAnim(android.R.animator.fade_out)
                                .build());



            case 6:
                NavController navController6 = NavHostFragment.findNavController(this);

                navController6.navigate(R.id.hindiCartoonFragment, null, new NavOptions.Builder()
                                .setEnterAnim(android.R.animator.fade_in)
                                .setExitAnim(android.R.animator.fade_out)
                                .build());



            case 7:
                NavController navController7 = NavHostFragment.findNavController(this);

                navController7.navigate(R.id.banglaCartoon2, null, new NavOptions.Builder()
                                .setEnterAnim(android.R.animator.fade_in)
                                .setExitAnim(android.R.animator.fade_out)
                                .build());






        }*/

    }
}