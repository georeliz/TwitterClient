package edu.galileo.twitterclient.hashtags.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.galileo.twitterclient.R;

/**
 * Created by Lab1 on 20/06/2016.
 */
public class HashtagListAdapater extends RecyclerView.Adapter<HashtagListAdapater.ViewHolder> {
    private List<String> items;

    public HashtagListAdapater(ArrayList<String> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_hashtag_text, parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtHashtags.setText(items.get(position));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.txtHashtags)
        TextView txtHashtags;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
