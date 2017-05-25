package com.toros.testapp;

import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private Preview mPreview;
    private Camera camera;

    private FrameLayout cameraPreviewLayout;
    private ImageView capturedImageHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get our FrameLayout, used for containing an "Preview" that will be used to display a preview of what the camera sees.
        cameraPreviewLayout = (FrameLayout) findViewById(R.id.camera_preview);
        // get our activity's "ImageView", this will be used to display the images the user takes by tapping the "take picture" button.
        capturedImageHolder = (ImageView) findViewById(R.id.captured_image);

        camera = openDeviceCamera();

        if(camera != null) {
            // instantiate a new instance of our Preview class.
            mPreview = new Preview(MainActivity.this, camera);
            // add that instance of the Preview class to the FrameLayout of our activity.
            cameraPreviewLayout.addView(mPreview);

            // get our activity's "take picture" button
            Button captureButton = (Button) findViewById(R.id.button);
            // call the "takePicture()" method of our camera instance whenever this button is clicked, also pass our "pictureCallback" class as a parameter to that method.
            captureButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    camera.takePicture(null, null, pictureCallback);
                }
            });
        }
    }


    // used to open the camera
    private Camera openDeviceCamera(){

        Camera mCamera = null;

            try {
                // open front camera
                mCamera = Camera.open(1);
                // set the orientation of the preview to portrait
                mCamera.setDisplayOrientation(90);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }

        return mCamera;
    }


    // this class' "onPictureTaken" method is invoked whenever the "take picture" button is tapped
    PictureCallback pictureCallback = new PictureCallback() {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            if(bitmap == null){
                Toast.makeText(MainActivity.this, "Captured image is empty", Toast.LENGTH_LONG).show();
                return;
            }
            else {
                // set the taken picture as the bitmap of our activity's ImageView
                capturedImageHolder.setImageBitmap(bitmap);
            }
        }

    };



}