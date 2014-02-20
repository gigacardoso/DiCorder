package ist.leic.ipm.dicorder;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TabHost;

public class ContactsActivity extends TabActivity {
	
	static final int CONTACTS = 0;
	static final int MAP = 1;
	static final int CATALOG = 2;
	static final int MULTIMEDIA = 3;
	static final int CAMERA = 4;
	static final int LASER = 5;
	
	private static ArrayList<String> _contacts = new ArrayList<String>();
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_layout);
    	String filename = "contacts";
        if(savedInstanceState != null) {
        	_contacts = savedInstanceState.getStringArrayList("contacts");
        }
        else {
	        try {
		    	FileInputStream fis = openFileInput(filename);
		    	ObjectInputStream ois = new ObjectInputStream(fis);
		    	_contacts = (ArrayList<String>) ois.readObject();
		    	ois.close();
	        }
	        catch(IOException e) {
	        	//do nothing
	        }
	        catch(ClassNotFoundException e) {
	        	//do nothing
	        }
        }
        Resources res = getResources();
        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec;
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("contacts", _contacts);
        Intent intent;
        intent = new Intent().setClass(this, ContactsListActivity.class);
        intent.putExtras(bundle);
        spec = tabHost.newTabSpec("contacts").setIndicator(res.getString(R.string.list),res.getDrawable(R.drawable.ic_contacts_list)).setContent(intent);
        tabHost.addTab(spec);
        intent = new Intent().setClass(this, ContactsSearchActivity.class);
        intent.putExtras(bundle);
        spec = tabHost.newTabSpec("localize").setIndicator(res.getString(R.string.search),res.getDrawable(R.drawable.ic_search)).setContent(intent);
        tabHost.addTab(spec);
        tabHost.setCurrentTab(0);
    }
    
    protected void onSaveInstanceState(Bundle outState) {
    	super.onSaveInstanceState(outState);
    	outState.putStringArrayList("contacts", _contacts);
    }
    
    protected void onPause() {
    	String filename = "contacts";
    	try {
	    	FileOutputStream fos = openFileOutput(filename ,Context.MODE_PRIVATE);
	    	ObjectOutputStream oos = new ObjectOutputStream(fos);
	    	oos.writeObject(_contacts);
	    	oos.close();
    	}
    	catch(IOException e) {
    		//do nothing
    	}
    	super.onPause();
    }
    
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.contacts_menu, menu);
    	return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
    	Intent intent = new Intent();
    	switch(item.getItemId()) {
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
    		finish();
    	default:
    		return super.onOptionsItemSelected(item);	
    	}
    }

}