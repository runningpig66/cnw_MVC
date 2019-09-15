package com.example.cnw_mvc.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cnw_mvc.App;
import com.example.cnw_mvc.R;
import com.example.cnw_mvc.Util.FavoReposHelper;
import com.example.cnw_mvc.model.Repo;
import com.example.cnw_mvc.view.UserRepoActivity;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.ViewHolder> {
    private Context mContext;
    private List<Repo> repoList;

    public RepoListAdapter(Context mContext, List<Repo> repoList) {
        this.mContext = mContext;
        this.repoList = repoList;
    }

    public void setRepoList(List<Repo> repoList) {
        this.repoList = repoList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.repo_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Repo repo = repoList.get(position);
        holder.setRepoClick(repo);
        holder.title.setText(repo.getAuthor() + " /" + repo.getName());
        if (TextUtils.isEmpty(repo.getDescription())) {
            holder.description.setVisibility(View.GONE);
        } else {
            holder.description.setText(repo.getDescription());
            holder.description.setVisibility(View.VISIBLE);
        }
        if (TextUtils.isEmpty(String.valueOf(repo.getCurrentPeriodStars()))) {
            holder.meta.setVisibility(View.GONE);
        } else {
            holder.meta.setText("CurrentPeriodStars: " + repo.getCurrentPeriodStars());
            holder.meta.setVisibility(View.VISIBLE);
        }
        if (FavoReposHelper.getInstance().contains(repo)) {
            holder.star.setImageResource(R.drawable.ic_star_checked);
        } else {
            holder.star.setImageResource(R.drawable.ic_star_unchecked);
        }

        for (int i = 0; i < holder.avatars.size(); i++) {
            if (i < repo.getBuiltBy().size()) {
                Glide.with(App.getContext()).load(repo.getBuiltBy().get(i).getAvatar()).into(holder.avatars.get(i));
                holder.avatars.get(i).setVisibility(View.VISIBLE);
            } else {
                holder.avatars.get(i).setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return repoList == null ? 0 : repoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Repo repo;
        private TextView title;
        private TextView description;
        private TextView meta;
        private ImageView star;
        private List<CircleImageView> avatars;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            meta = itemView.findViewById(R.id.meta);
            star = itemView.findViewById(R.id.star);
            avatars = new ArrayList<>();
            avatars.add(itemView.findViewById(R.id.avatar1));
            avatars.add(itemView.findViewById(R.id.avatar2));
            avatars.add(itemView.findViewById(R.id.avatar3));
            avatars.add(itemView.findViewById(R.id.avatar4));
            avatars.add(itemView.findViewById(R.id.avatar5));
            //条目点击事件
            itemView.findViewById(R.id.repo_card).setOnClickListener(this);
            //收藏点击事件
            star.setOnClickListener(this);
        }

        void setRepoClick(Repo repo) {
            this.repo = repo;
            for (int i = 0; i < repo.getBuiltBy().size(); i++) {
                avatars.get(i).setOnClickListener(this);
            }
        }

        @Override
        public void onClick(View view) {
            String name;
            switch (view.getId()) {
                case R.id.repo_card:
                    //条目的点击
                    name = repo.getAuthor();
                    openUserRepoActivity(name);
                    break;
                case R.id.star:
                    //收藏
                    if (FavoReposHelper.getInstance().contains(repo)) {
                        FavoReposHelper.getInstance().removeFavo(repo);
                        star.setImageResource(R.drawable.ic_star_unchecked);
                    } else {
                        FavoReposHelper.getInstance().addFavo(repo);
                        star.setImageResource(R.drawable.ic_star_checked);
                    }
                    break;
                case R.id.avatar1:
                    name = repo.getBuiltBy().get(0).getUsername();
                    openUserRepoActivity(name);
                    break;
                case R.id.avatar2:
                    name = repo.getBuiltBy().get(1).getUsername();
                    openUserRepoActivity(name);
                    break;
                case R.id.avatar3:
                    name = repo.getBuiltBy().get(2).getUsername();
                    openUserRepoActivity(name);
                    break;
                case R.id.avatar4:
                    name = repo.getBuiltBy().get(3).getUsername();
                    openUserRepoActivity(name);
                    break;
                case R.id.avatar5:
                    name = repo.getBuiltBy().get(4).getUsername();
                    openUserRepoActivity(name);
                    break;
            }
        }

        private void openUserRepoActivity(String name) {
            Intent intent = new Intent(mContext, UserRepoActivity.class);
            intent.putExtra("username", name);
            mContext.startActivity(intent);
        }
    }
}
