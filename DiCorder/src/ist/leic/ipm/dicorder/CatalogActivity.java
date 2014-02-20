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

public class CatalogActivity extends TabActivity {
	
	static final int CONTACTS = 0;
	static final int MAP = 1;
	static final int CATALOG = 2;
	static final int MULTIMEDIA = 3;
	static final int CAMERA = 4;
	static final int LASER = 5;
	
	private static Registry _registry;

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multimedia_layout);
        
        if(savedInstanceState == null) {
        	_registry = (Registry) getIntent().getSerializableExtra("registry");
        }
        else {
        	_registry = (Registry) savedInstanceState.getSerializable("registry");
        }
        
        Resources res = getResources();
        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;
        intent = new Intent().setClass(this, AddRegistryActivity.class);
        intent.putExtra("registry", _registry);
        intent.putExtra("media", getIntent().getSerializableExtra("media"));
        spec = tabHost.newTabSpec("add").setIndicator(res.getString(R.string.add_registry),res.getDrawable(R.drawable.ic_add)).setContent(intent);
        tabHost.addTab(spec);
        intent = new Intent().setClass(this, SearchActivity.class);
        intent.putExtra("registry", _registry);
        spec = tabHost.newTabSpec("search").setIndicator(res.getString(R.string.catalog_search),res.getDrawable(R.drawable.ic_search)).setContent(intent);
        tabHost.addTab(spec);
        tabHost.setCurrentTab(0);
    }
	
	protected void onSaveInstanceState(Bundle outState) {
    	super.onSaveInstanceState(outState);
		outState.putSerializable("registry", _registry);
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.catalog_menu, menu);
    	return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
    	Intent intent = new Intent();
    	intent.putExtra("registry", _registry);
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
    	case R.id.multimedia:
    		intent.putExtra("launch_code", MULTIMEDIA);
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
    	intent.putExtra("registry", _registry);
    	setResult(RESULT_OK, intent);
    	super.finishFromChild(child);
    }

}