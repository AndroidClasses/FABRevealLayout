/*
 * Copyright (C) 2015 Tomás Ruiz-López.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.truizlop.fabreveallayoutsample2;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.truizlop.fabreveallayout.FABRevealLayout;
import com.truizlop.fabreveallayout.OnRevealChangeListener;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.fab_reveal_layout)
    FABRevealLayout fabRevealLayout;

    @Bind(R.id.container_switch_panels)
    RelativeLayout mContainerPanels;

    @Bind(R.id.container_control_buttons)
    LinearLayout mContainerControlButtons;

    @OnClick(R.id.switch_pick_filter)
    void onPickFilterClicked(View view) {
        activateSwitchLayout(view.getId(), R.id.panel_filter);
        fabRevealLayout.revealMainView();
    }

    @OnClick(R.id.switch_pick_text)
    void onPickTextClicked(View view) {
        activateSwitchLayout(view.getId(), R.id.panel_text);
        fabRevealLayout.revealMainView();
    }

    @OnClick(R.id.switch_pick_audio)
    void onPickAudioClicked(View view) {
        activateSwitchLayout(view.getId(), R.id.panel_audio);
        fabRevealLayout.revealMainView();
    }

    @OnClick(R.id.video_preview_theme_volume)
    void onThemeVolumeClicked(View view) {
        Snackbar.make(getWindow().getDecorView(), "Theme volume trigger.", Snackbar.LENGTH_SHORT).show();
    }

    @OnClick(R.id.video_preview_video_volume)
    void onThemeVideoClicked(View view) {
        Snackbar.make(getWindow().getDecorView(), "Origin source volume trigger.", Snackbar.LENGTH_SHORT).show();
    }

    private void activateSwitchLayout(int selectedId, int activeId) {
        setViewSelectState(mContainerControlButtons, selectedId);
        setViewVisibleState(mContainerPanels, activeId);
    }

    private void setViewSelectState(ViewGroup parent, int viewId) {
            int childrenCount = parent.getChildCount();
        View view;
            for (int index = 0; index < childrenCount; index++) {
                view = parent.getChildAt(index);
                view.setSelected(viewId == view.getId());
            }
    }

    private void setViewVisibleState(ViewGroup parent, int viewId) {
        int childrenCount = parent.getChildCount();
        View view;
        for (int index = 0; index < childrenCount; index++) {
            view = parent.getChildAt(index);
            int visibility = viewId == view.getId() ? View.VISIBLE : View.GONE;
            view.setVisibility(visibility);
        }
    }

    @OnClick(R.id.game_cover_image)
    void onCoverClicked(View view) {
        fabRevealLayout.revealMainView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
//        FABRevealLayout fabRevealLayout = (FABRevealLayout) findViewById(R.id.fab_reveal_layout);
        configureFABReveal(fabRevealLayout);

        activateSwitchLayout(R.id.switch_pick_filter, R.id.panel_filter);
    }

    private void configureFABReveal(FABRevealLayout fabRevealLayout) {
        fabRevealLayout.setOnRevealChangeListener(new OnRevealChangeListener() {
            @Override
            public void onMainViewAppeared(FABRevealLayout fabRevealLayout, View mainView) {}

            @Override
            public void onSecondaryViewAppeared(final FABRevealLayout fabRevealLayout, View secondaryView) {
//                prepareBackTransition(fabRevealLayout);
            }
        });
    }

    private void prepareBackTransition(final FABRevealLayout fabRevealLayout) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                fabRevealLayout.revealMainView();
            }
        }, 2000);
    }

    @Override
    public void onBackPressed() {
        if (fabRevealLayout.onBackPressed()) {
            // do nothing but return directory
            return;
        }
        super.onBackPressed();
    }
}
