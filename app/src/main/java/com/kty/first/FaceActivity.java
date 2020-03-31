package com.kty.first;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.common.FirebaseVisionPoint;
import com.google.firebase.ml.vision.face.FirebaseVisionFace;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceContour;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetector;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceLandmark;

import java.util.List;

public class FaceActivity extends Activity {
    Context mContext;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext=this;
        final Point p= new Point();
        Display display=   getWindowManager().getDefaultDisplay();
        display.getSize(p);
       int x=   p.x;
        int y= p.y;


        setContentView(R.layout.act_face);

        final Bitmap bitmap= BitmapFactory.decodeResource(mContext.getResources(),R.drawable.abc);
        FirebaseVisionFaceDetectorOptions options =
                new FirebaseVisionFaceDetectorOptions.Builder()
                        .setPerformanceMode(FirebaseVisionFaceDetectorOptions.ACCURATE)
                        .setLandmarkMode(FirebaseVisionFaceDetectorOptions.ALL_LANDMARKS)
                        .setClassificationMode(FirebaseVisionFaceDetectorOptions.ALL_CLASSIFICATIONS)
                        .build();
        FirebaseVisionFaceDetector detector = FirebaseVision.getInstance()
                .getVisionFaceDetector(options);

        FirebaseVisionImage image =FirebaseVisionImage.fromBitmap(bitmap);
        Task<List<FirebaseVisionFace>> result =
                detector.detectInImage(image)
                        .addOnSuccessListener(
                                new OnSuccessListener<List<FirebaseVisionFace>>() {
                                    @Override
                                    public void onSuccess(List<FirebaseVisionFace> faces) {
                                        final RelativeLayout relativeLayout_main=findViewById(R.id.RelativeLayout_main);

                                        // Task completed successfully
                                        for (FirebaseVisionFace face : faces) {

                                            // If landmark detection was enabled (mouth, ears, eyes, cheeks, and
                                            // nose available):
                                            FirebaseVisionFaceLandmark leftEye = face.getLandmark(FirebaseVisionFaceLandmark.LEFT_EYE);
                                            float lex = leftEye.getPosition().getX();
                                            float ley = leftEye.getPosition().getY();

                                            FirebaseVisionFaceLandmark leftCheek = face.getLandmark(FirebaseVisionFaceLandmark.LEFT_CHEEK);
                                            float lcx = leftCheek.getPosition().getX();
                                            float lcy = leftCheek.getPosition().getY();

                                            FirebaseVisionFaceLandmark rightCheek = face.getLandmark(FirebaseVisionFaceLandmark.RIGHT_CHEEK);
                                            float rex = rightCheek.getPosition().getX();
                                            float rey = rightCheek.getPosition().getY();

                                            ImageView imageLE = new ImageView(mContext);
                                            imageLE.setImageResource(R.drawable.wine);
                                            imageLE.setX(p.x * lex / bitmap.getWidth()- 100);
                                            imageLE.setY(p.y * ley / bitmap.getHeight()- 100);

                                            imageLE.setLayoutParams(new RelativeLayout.LayoutParams(200, 200));

                                            relativeLayout_main.addView(imageLE);
                                            ImageView imageLC = new ImageView(mContext);
                                            imageLC.setImageResource(R.drawable.wine);
                                            imageLC.setX(p.x * lcx / bitmap.getWidth() - 100);
                                            imageLC.setY(p.y * lcy / bitmap.getHeight() - 100);
                                            imageLC.setLayoutParams(new RelativeLayout.LayoutParams(200, 200));
                                            relativeLayout_main.addView(imageLC);
                                            ImageView imageRC = new ImageView(mContext);
                                            imageRC.setImageResource(R.drawable.wine2);
                                            relativeLayout_main.addView(imageRC);
                                            imageRC.setX(p.x * rex / bitmap.getWidth()- 100);
                                            imageRC.setY(p.y * rey / bitmap.getHeight()- 100);
                                            imageRC.setLayoutParams(new RelativeLayout.LayoutParams(200, 200));
                                        }
                                    }
                                })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Task failed with an exception
                                        // ...
                                    }
                                });
    }
}
