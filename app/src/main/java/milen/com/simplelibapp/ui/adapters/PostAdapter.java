package milen.com.simplelibapp.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import milen.com.simplelibapp.R;
import milen.com.simplelibapp.api.api_models.Post;

public class PostAdapter extends ArrayAdapter<Post> {
    private int resource;
    public PostAdapter(Context context, int resource, List<Post> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Post post = getItem(position);
        ViewHolder viewHolder;

        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(resource, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        // Populate the data into the template view using the data object
        viewHolder.title.setText(post.getTitle());
        viewHolder.body.setText(post.getBody());
        // Return the completed view to render on screen
        return convertView;
    }

    // ViewHolder must not be private because of ButterKnife lib
    static class ViewHolder {
        @BindView(R.id.tv_title) TextView title;
        @BindView(R.id.tv_body) TextView body;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
