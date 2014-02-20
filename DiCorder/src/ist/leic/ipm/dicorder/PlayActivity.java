package ist.leic.ipm.dicorder;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class PlayActivity extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.play_layout);
		
		int imageId = getIntent().getIntExtra("imageId", 0);
		
		ImageView image = (ImageView) findViewById(R.id.play_image);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(200, 200);
		params.addRule(RelativeLayout.CENTER_IN_PARENT);
		image.setLayoutParams(params);
        image.setScaleType(ImageView.ScaleType.CENTER_CROP);
		image.setImageResource(imageId);
		
		Button b = (Button) findViewById(R.id.back_button);
		b.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
	}

}