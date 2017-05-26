package com.toros.testapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

    Scene scene_a;
    Scene scene_b;

    ViewGroup scene_root;

    Transition fade_transition;

    Button main_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Create the scene root for the scenes in this app
        scene_root = (ViewGroup) findViewById(R.id.scene_root);

        // Create the scenes
        scene_a = Scene.getSceneForLayout(scene_root, R.layout.a_scene, this);
        scene_b = Scene.getSceneForLayout(scene_root, R.layout.b_scene, this);

        fade_transition = new Fade(Fade.OUT);
        fade_transition.setDuration(5000);

        main_button = (Button) findViewById(R.id.main_button);

        main_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.go(scene_b , fade_transition);
            }
        });

    }





}