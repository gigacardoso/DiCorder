package ist.leic.ipm.dicorder;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class PicturesAdapter extends BaseAdapter {
    private Context _context;    
    private ArrayList<Integer> _thumbIds;

    public PicturesAdapter(Context c, ArrayList<Integer> pictures) {
        _context = c;
        _thumbIds = pictures;
    }

    public int getCount() {
        return _thumbIds.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
    	ImageView view;
        if (convertView == null) {
        	view = new ImageView(_context);
        	view.setLayoutParams(new GridView.LayoutParams(100, 100));
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setPadding(8, 8, 8, 8);
        }
        else {
        	view = (ImageView) convertView;
        }
        view.setImageResource(_thumbIds.get(position));
        return view;
    }

}
