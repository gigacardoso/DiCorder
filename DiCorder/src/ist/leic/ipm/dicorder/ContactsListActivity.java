package ist.leic.ipm.dicorder;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ContactsListActivity extends ListActivity {
	
	private int _currentContact;
	private ArrayList<String> _contacts;
	private ArrayAdapter<String> _adapter;
	static final int DIALOG_CONTACT_OPTIONS = 0;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		_contacts = getIntent().getExtras().getStringArrayList("contacts");
		_adapter = new ArrayAdapter<String>(this, R.layout.list_item, _contacts);
		setListAdapter(_adapter);
		_adapter.notifyDataSetChanged();
		ListView lv = getListView();
		lv.setTextFilterEnabled(true);
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				_currentContact = position;
				showDialog(DIALOG_CONTACT_OPTIONS);
		    }
		});
	}
	
	protected void onResume() {
		super.onResume();
		_adapter.notifyDataSetChanged();
	}
	
	protected Dialog onCreateDialog(int id) {
		AlertDialog dialog;
		switch(id){
		case DIALOG_CONTACT_OPTIONS:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage(_contacts.get(_currentContact));
			builder.setPositiveButton(getString(R.string.call), new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					Intent intent = new Intent();
					intent.setClass(ContactsListActivity.this, CallActivity.class);
					intent.putExtra("contact", _contacts.get(_currentContact));
					dialog.cancel();
					ContactsListActivity.this.startActivity(intent);					
				}
			});
			builder.setNeutralButton(getString(R.string.delete), new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
					_contacts.remove(_currentContact);
					_adapter.notifyDataSetChanged();
					
				}
			});
			builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();					
				}
			});
			dialog = builder.create();
			break;
		default:
			dialog = null;
			break;
		}
		return dialog;
	}

	protected void onPrepareDialog(int id, Dialog dialog) {
		switch(id) {
		case DIALOG_CONTACT_OPTIONS:
			((AlertDialog) dialog).setMessage(_contacts.get(_currentContact));
			break;
		default:
			break;
		}
	}

}