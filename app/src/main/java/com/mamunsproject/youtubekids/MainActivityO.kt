package com.mamunsproject.youtubekids

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.facebook.ads.Ad
import com.facebook.ads.AdError
import com.facebook.ads.InterstitialAd
import com.facebook.ads.InterstitialAdListener
import com.mamunsproject.youtubekids.databinding.ActivityMainOBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivityO : AppCompatActivity() {

    private var interstitialAd: InterstitialAd? = null

    private lateinit var binding: ActivityMainOBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainOBinding.inflate(layoutInflater);
        setContentView(binding.root)
        //initRecyclerview()
        Log.d("INADAPTERSITUATION", "onCreate ")



        val navController=findNavController(R.id.fragmentContainerView)
        binding.bottomNavigation.setupWithNavController(navController)


//===============================================FB INTERSTITIAL AD============================================


//===============================================FB INTERSTITIAL AD============================================
        interstitialAd = InterstitialAd(applicationContext, "761984221190315_1033375524051182")


        val interstitialAdListener: InterstitialAdListener = object : InterstitialAdListener {
            override fun onInterstitialDisplayed(ad: Ad) {
                // Interstitial ad displayed callback
                Log.e("TAGFDSF", "Interstitial ad displayed.")
            }

            override fun onInterstitialDismissed(ad: Ad) {
                // Interstitial dismissed callback
                Log.e("TAGFDSF", "Interstitial ad dismissed.")
            }

            override fun onError(ad: Ad, adError: AdError) {
                // Ad error callback
                Log.e("TAGFDSF", "Interstitial ad failed to load: " + adError.errorMessage)
            }

            override fun onAdLoaded(ad: Ad) {
                // Interstitial ad is loaded and ready to be displayed
                Log.d("TAGFDSF", "Interstitial ad is loaded and ready to be displayed!")
                // Show the ad
                interstitialAd!!.show()
            }

            override fun onAdClicked(ad: Ad) {
                // Ad clicked callback
                Log.d("TAGFDSF", "Interstitial ad clicked!")
            }

            override fun onLoggingImpression(ad: Ad) {
                // Ad impression logged callback
                Log.d("TAGFDSF", "Interstitial ad impression logged!")
            }
        }

        // For auto play video ads, it's recommended to load the ad
        // at least 30 seconds before it is shown

        // For auto play video ads, it's recommended to load the ad
        // at least 30 seconds before it is shown
        interstitialAd!!.loadAd(
            interstitialAd!!.buildLoadAdConfig()
                .withAdListener(interstitialAdListener)
                .build()
        )


        val scheduledExecutorService = Executors.newSingleThreadScheduledExecutor()

        scheduledExecutorService.scheduleAtFixedRate({
            runOnUiThread {
                if (interstitialAd!!.isAdInvalidated()) {
                    interstitialAd!!.loadAd()
                }
            }
        }, 210, 210, TimeUnit.SECONDS)


//===============================================FB INTERSTITIAL AD============================================


    }
}