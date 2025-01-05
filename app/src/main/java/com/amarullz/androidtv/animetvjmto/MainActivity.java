package com.amarullz.androidtv.animetvjmto;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private InterstitialAd interstitialAd;
    private boolean episodeIsLocked = true;  // متغير لتحديد إذا كانت الحلقة مغلقة أم لا
    private Button watchEpisodeButton; // إضافة زر لتشغيل الحلقة بعد فك القفل

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // تعيين واجهة المستخدم الخاصة بالنشاط

        // ربط الزر بالواجهة
        watchEpisodeButton = findViewById(R.id.watchEpisodeButton);
        
        // تحميل الإعلان
        loadAd();

        // التحقق من حالة الحلقة: إذا كانت مغلقة، عرض الإعلان، وإلا تشغيل الحلقة مباشرة
        if (episodeIsLocked) {
            showAd();  // إذا كانت الحلقة مغلقة، عرض الإعلان
        } else {
            playEpisode();  // إذا كانت الحلقة مفتوحة، تشغيل الحلقة مباشرة
        }

        // إضافة حدث عند الضغط على الزر لبدء تشغيل الحلقة
        watchEpisodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (episodeIsLocked) {
                    showAd();  // عرض الإعلان إذا كانت الحلقة مغلقة
                } else {
                    playEpisode();  // تشغيل الحلقة إذا كانت مفتوحة
                }
            }
        });
    }

    // تحميل الإعلان
    private void loadAd() {
        interstitialAd = new InterstitialAd(this);  // إنشاء كائن للإعلان
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712"); // معرف الإعلان التجريبي
        interstitialAd.loadAd(new AdRequest.Builder().build());  // تحميل الإعلان
    }

    // عرض الإعلان
    private void showAd() {
        if (interstitialAd.isLoaded()) {  // التحقق إذا كان الإعلان جاهزًا
            interstitialAd.show();  // عرض الإعلان
            interstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    // بعد إغلاق الإعلان، فك قفل الحلقة وتشغيلها
                    unlockEpisode();
                    playEpisode();
                }
            });
        } else {
            // إذا كان الإعلان غير جاهز، تشغيل الحلقة مباشرة
            playEpisode();
        }
    }

    // فك قفل الحلقة
    private void unlockEpisode() {
        episodeIsLocked = false;  // تغيير حالة الحلقة إلى مفتوحة
    }

    // تشغيل الحلقة
    private void playEpisode() {
        // هنا يمكن إضافة الكود الخاص بتشغيل الحلقة
        // على سبيل المثال: تشغيل فيديو أو تحميل المحتوى
    }
}
