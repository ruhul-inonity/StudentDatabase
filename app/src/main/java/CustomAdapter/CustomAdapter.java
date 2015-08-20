package CustomAdapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;


public class CustomAdapter extends ArrayAdapter {
    public CustomAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
    }
}
