package edu.neu.madcourse.numad21s_eliasmitsolides;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LinkViewAdapter extends RecyclerView.Adapter<LinkViewHolder> {

    private final ArrayList<LinkCard> linkList;
    private LinkClickListener listener;

    //Constructor
    public LinkViewAdapter(ArrayList<LinkCard> linkList) {
        this.linkList = linkList;
    }

    public void setOnLinkClickListener(LinkClickListener listener) {
        this.listener = listener;
    }

    @Override
    public LinkViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.link_card, parent, false);
        return new LinkViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull LinkViewHolder holder, int position) {
        LinkCard currentItem = linkList.get(position);

        holder.itemUri.setText(currentItem.getItemURL());
        holder.itemName.setText(currentItem.getItemName());

    }

    @Override
    public int getItemCount() {
        return linkList.size();
    }
}
