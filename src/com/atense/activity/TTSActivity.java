/*
 *  TestActivity.java
 *
 *  Created by admin on 2015年6月23日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.activity;

import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.atense.R;

/**
 * 
 * @author Admin
 */
public class TTSActivity extends Activity implements OnClickListener {

	EditText edit;
	Button speech, record;
	TextToSpeech tts;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tts);

		tts = new TextToSpeech(this, new OnInitListener() {
			@Override
			public void onInit(int status) {
				if (status == TextToSpeech.SUCCESS) {
					int i = tts.setLanguage(Locale.US);
					if (i != TextToSpeech.LANG_COUNTRY_AVAILABLE
							&& i != TextToSpeech.LANG_AVAILABLE) {
						Toast.makeText(TTSActivity.this, "不支持该语言",
								Toast.LENGTH_LONG).show();
					} else {
						Toast.makeText(TTSActivity.this, "支持该语言",
								Toast.LENGTH_LONG).show();
					}
				}
			}
		});

		edit = (EditText) findViewById(R.id.edit);
		speech = (Button) findViewById(R.id.speech);
		record = (Button) findViewById(R.id.record);
		speech.setOnClickListener(this);
		record.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.speech) {
			tts.speak(edit.getText().toString(), TextToSpeech.QUEUE_ADD, null);
		} else if (v.getId() == R.id.record) {
			tts.synthesizeToFile(edit.getText().toString(), null,
					"mnt/sdcard/sound.wav");
		}
	}

	public void onDestroy() {
		if (tts != null) {
			tts.shutdown();
		}
	}

}
