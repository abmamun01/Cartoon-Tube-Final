package com.mamunsproject.youtubekids.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cartoontuberefinefinal.Model.TypeThumbnail;
import com.example.cartoontuberefinefinal.Model.Video;
import com.facebook.ads.InterstitialAd;
import com.mamunsproject.youtubekids.PlayerActivity;
import com.mamunsproject.youtubekids.R;

import java.util.ArrayList;

public class VIdeoAdapter2 extends RecyclerView.Adapter<VIdeoAdapter2.VideoHolder> {

    Context context;
    ArrayList<Video> arrayListVideo;
    View containerView;
    public static InterstitialAd mInterstitialAd;


    public VIdeoAdapter2(Context context, ArrayList<Video> arrayListVideo) {
        this.context = context;
        this.arrayListVideo = arrayListVideo;
    }

    @NonNull
    @Override
    public VideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.video_layout, parent, false);
        return new VideoHolder(view);
    }


    @Override
    public int getItemViewType(int position) {

        if (position == 4) {

        }

        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoHolder holder, int position) {



        Video video = arrayListVideo.get(position);
        if (video != null) {
            holder.textViewTitle.setText(video.getSnippet().getTitle());
            if (video.getSnippet().getThumbnails() != null) {
                TypeThumbnail t = video.getSnippet().getThumbnails().getHigh();
                if (t == null) t = video.getSnippet().getThumbnails().getMedium();
                if (t == null) t = video.getSnippet().getThumbnails().getStandard();


                if (t == null) {

                    Log.d("TAG", "onBindViewHolder: " + "Some video are deleted!");
                } else {
                    Glide.with(context).load(t.getUrl()).into(holder.imageViewThumbnail);
                }

                holder.itemView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        String id_ = video.getContentDetails().getVideoId();
                        Intent intent = new Intent(context, PlayerActivity.class);
                        intent.putExtra("videoid", id_);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);

//                        Pair[] pairs=new Pair[1];
//                        pairs[0]=new Pair<View,String>(holder.relativeTransitionId,"playerTransition");
//                        ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation((Activity) context,pairs);
//                                              context.startActivity(intent,options.toBundle());

                    }
                });


            }
        }
    }

    @Override
    public int getItemCount() {
        return arrayListVideo.size();
    }

    public class VideoHolder extends RecyclerView.ViewHolder {
//        @BindView(R.id.tv_item_title)
//        TextView textViewTitle;
//        @BindView(R.id.iv_item_cover)
//        ImageView imageViewThumbnail;

        TextView textViewTitle;
        ImageView imageViewThumbnail;
        RelativeLayout relativeTransitionId;


        public VideoHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.tv_item_title);
            imageViewThumbnail = itemView.findViewById(R.id.iv_item_cover);
            relativeTransitionId = itemView.findViewById(R.id.relativeTranstionId);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }


}
