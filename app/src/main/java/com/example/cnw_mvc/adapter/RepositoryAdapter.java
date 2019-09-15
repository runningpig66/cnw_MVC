package com.example.cnw_mvc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cnw_mvc.R;
import com.example.cnw_mvc.model.Repository;

import java.util.List;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder> {
    private Context mContext;
    private List<Repository> repositoryList;

    public RepositoryAdapter(Context mContext, List<Repository> repositoryList) {
        this.mContext = mContext;
        this.repositoryList = repositoryList;
    }

    @NonNull
    @Override
    public RepositoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_repo, parent, false);
        return new RepositoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepositoryViewHolder holder, int position) {
        Repository repository = repositoryList.get(position);
        holder.text_repo_title.setText(repository.getName());
        holder.text_repo_description.setText(repository.getDescription());
        holder.text_watchers.setText(mContext.getResources().getString(R.string.text_watchers, repository.getWatchers()));
        holder.text_stars.setText(mContext.getResources().getString(R.string.text_stars, repository.getStargazers_count()));
        holder.text_forks.setText(mContext.getResources().getString(R.string.text_forks, repository.getForks()));
    }

    @Override
    public int getItemCount() {
        return repositoryList == null ? 0 : repositoryList.size();
    }

    class RepositoryViewHolder extends RecyclerView.ViewHolder {
        TextView text_repo_title;
        TextView text_repo_description;
        TextView text_watchers;
        TextView text_stars;
        TextView text_forks;

        RepositoryViewHolder(@NonNull View itemView) {
            super(itemView);
            text_repo_title = itemView.findViewById(R.id.text_repo_title);
            text_repo_description = itemView.findViewById(R.id.text_repo_description);
            text_watchers = itemView.findViewById(R.id.text_watchers);
            text_stars = itemView.findViewById(R.id.text_stars);
            text_forks = itemView.findViewById(R.id.text_forks);
        }
    }
}
