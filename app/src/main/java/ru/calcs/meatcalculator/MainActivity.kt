package ru.calcs.meatcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.yandex.mobile.ads.banner.AdSize
import com.yandex.mobile.ads.banner.BannerAdEventListener
import com.yandex.mobile.ads.common.AdRequest
import com.yandex.mobile.ads.common.AdRequestError
import com.yandex.mobile.ads.common.ImpressionData
import com.yandex.mobile.ads.common.MobileAds
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var bottomSheetBehavior : BottomSheetBehavior<CoordinatorLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initMobileAdsYandex()
        loadAndShowBanner()
        bottomSheetBehavior = BottomSheetBehavior.from(main_bottom_sheets)







        btn_test_bottom.setOnClickListener {
            onClickBtnTestBottomSheet()
        }
    }





    fun initMobileAdsYandex() {
        MobileAds.initialize(this) { Log.d("MyLog", "SDK Initialised OK") }
    }

    fun loadAndShowBanner(){
        adViewYandex.apply {
            setAdSize(AdSize.BANNER_320x50)
            setAdUnitId(getString(R.string.yandex_banner_id_test))
        }
        val adRequest = AdRequest.Builder().build()
        adViewYandex.setBannerAdEventListener(object : BannerAdEventListener {
            override fun onAdLoaded() {
                Log.d("MyLog", "Ad Loaded Ok")
            }
            override fun onAdFailedToLoad(p0: AdRequestError) {
                Log.d("MyLog", "Banner Ad Load Fail : ${p0.toString()}")
            }
            override fun onAdClicked() {
                Log.d("MyLog", "Ad Clicked")
            }
            override fun onLeftApplication() {
            }
            override fun onReturnedToApplication() {
            }
            override fun onImpression(p0: ImpressionData?) {
            }
        })
        adViewYandex.loadAd(adRequest)
    }

    var index = 0
    fun onClickBtnTestBottomSheet(){
        when{
            index == 0 -> {bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED}
            index == 1 -> {bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED}
            index == 2 -> {bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN}
            index == 3 -> {bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED}
        }
        Log.d("MyLog", index.toString())
        index++
        if(index==4){index=0}
    }

}