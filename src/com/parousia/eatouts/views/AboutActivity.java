package com.parousia.eatouts.views;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.parousia.eatouts.R;

public class AboutActivity extends SherlockFragmentActivity {

	@Override
	protected void onCreate(Bundle saveInstanceState) {

		super.onCreate(saveInstanceState);
		setContentView(R.layout.activity_about);

		TextView versionText = (TextView) findViewById(R.id.version_text);
		TextView feedbackText = (TextView) findViewById(R.id.feedback_link_text);

		PackageInfo pInfo;
		String version = "";
		try {
			pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
			version = pInfo.versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		versionText.setText("Version - " + version);
		feedbackText.setText("Send your feedbacks to: myselfrajesh@gmail.com");

		feedbackText.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				prepareEmail();
			}
		});

	}

	protected void prepareEmail() {
		Intent emailintent = new Intent(android.content.Intent.ACTION_SEND);
		emailintent.setType("plain/text");
		emailintent.putExtra(android.content.Intent.EXTRA_EMAIL,
				new String[] { "myselfrajesh@gmail.com" });
		emailintent.putExtra(android.content.Intent.EXTRA_SUBJECT,
				getString(R.string.FEEDBACK_EMAIL_SUBJECT));
		startActivity(Intent.createChooser(emailintent, "Send mail.."));

	}

}
