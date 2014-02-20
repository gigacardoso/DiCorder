package ist.leic.ipm.dicorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {
    private Context _context;

    public ImageAdapter(Context c) {
        _context = c;
    }

    public int getCount() {
        return _thumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
        	LayoutInflater li = ((android.app.Activity) _context).getLayoutInflater();
        	view = li.inflate(R.layout.grid_item, null);
        	ImageView imageView = (ImageView) view.findViewById(R.id.grid_item_image);
            imageView.setImageResource(_thumbIds[position]);
            TextView textView = (TextView) view.findViewById(R.id.grid_item_text);
            textView.setText(_stringIds[position]);
        }
        return view;
    }
    
    private Integer[] _thumbIds = {
            R.drawable.ic_contacts, R.drawable.ic_map,
            R.drawable.ic_catalog, R.drawable.ic_multimedia,
            R.drawable.ic_camera, R.drawable.ic_laser
    };
    
    private Integer[] _stringIds = {
    		R.string.contacts, R.string.map,
    		R.string.catalog, R.string.multimedia,
    		R.string.camera, R.string.laser
    };
   
}