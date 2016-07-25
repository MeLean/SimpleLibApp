package milen.com.simplelibapp.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import milen.com.simplelibapp.R;
import milen.com.simplelibapp.realm.models.PostRealmObject;

public class PostRealmObjectAdapter extends RecyclerView.Adapter<PostRealmObjectAdapter.ViewHolder>{

    private List<PostRealmObject> postList;

    public PostRealmObjectAdapter(List<PostRealmObject> postList) {
        this.postList = postList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.post_title_row)        TextView titleHolder;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);


        }
    }
}
