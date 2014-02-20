package ist.leic.ipm.dicorder;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TabHost;

public class MultimediaActivity extends TabActivity {
	
	static final int CONTACTS = 0;
	static final int MAP = 1;
	static final int CATALOG = 2;
	static final int MULTIMEDIA = 3;
	static final int CAMERA = 4;
	static final int LASER = 5;
	
	private static MediaStorage _media;

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multimedia_layout);
        
        if(savedInstanceState == null) {
        	_media = (MediaStorage) getIntent().getSerializableExtra("media");
        }
        else {
        	_media = (MediaStorage) savedInstanceState.getSerializable("media");
        }
        
        Resources res = getResources();
        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;
        intent = new Intent().setClass(this, PicturesActivity.class);
        intent.putExtra("media", _media);
        spec = tabHost.newTabSpec("pictures").setIndicator(res.getString(R.string.pictures),res.getDrawable(R.drawable.ic_picture)).setContent(intent);
        tabHost.addTab(spec);
        intent = new Intent().setClass(this, VideoActivity.class);
        intent.putExtra("media", _media);
        spec = tabHost.newTabSpec("video").setIndicator(res.getString(R.string.video),res.getDrawable(R.drawable.ic_video)).setContent(intent);
        tabHost.addTab(spec);
        intent = new Intent().setClass(this, AudioActivity.class);
        intent.putExtra("media", _media);
        spec = tabHost.newTabSpec("audio").setIndicator(res.getString(R.string.audio),res.getDrawable(R.drawable.ic_music)).setContent(intent);
        tabHost.addTab(spec);
        tabHost.setCurrentTab(0);
    }
	
	protected void onSaveInstanceState(Bundle outState) {
    	super.onSaveInstanceState(outState);
		outState.putSerializable("media", _media);
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.multimedia_menu, menu);
    	return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
    	Intent intent = new Intent();
    	intent.putExtra("media", _media);
    	switch(item.getItemId()) {
    	case R.id.contacts:
    		intent.putExtra("launch_code", CONTACTS);
    		setResult(RESULT_OK, intent);
    		finish();    		
    		return true;
    	case R.id.map:
    		intent.putExtra("launch_code", MAP);
    		setResult(RESULT_OK, intent);
    		finish();    		
    		return true;
    	case R.id.catalog:
    		intent.putExtra("launch_code", CATALOG);
    		setResult(RESULT_OK, intent);
    		finish();
    		return true;
    	case R.id.camera:
    		intent.putExtra("launch_code", CAMERA);
    		setResult(RESULT_OK, intent);
    		finish();
    		return true;
    	case R.id.laser:
    		intent.putExtra("launch_code", LASER);
    		setResult(RESULT_OK, intent);
    		finish();
    		return true;
    	case R.id.home_menu:
    		setResult(RESULT_OK, intent);
    		finish();
    	default:
    		return super.onOptionsItemSelected(item);	
    	}
    }
    
    public void finishFromChild(Activity child) {
    	Intent intent = child.getIntent();
    	intent.putExtra("media", _media);
    	setResult(RESULT_OK, intent);
    	super.finishFromChild(child);
    }
	
}