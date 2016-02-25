package ca.co.rufus.androidboilerplate.ui.main;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ca.co.rufus.androidboilerplate.R;
import ca.co.rufus.androidboilerplate.data.model.RepoOwnerJoin;
import ca.co.rufus.androidboilerplate.data.model.Repository;
import ca.co.rufus.androidboilerplate.ui.misc.Truss;

public class GithubRepoAdapter extends RecyclerView.Adapter<GithubRepoAdapter.RibotViewHolder> {

    private List<RepoOwnerJoin> repositories;
    private final int descriptionColor;


    public GithubRepoAdapter(Context context) {
        repositories = new ArrayList<>();
        TypedValue outValue = new TypedValue();
        context.getTheme().resolveAttribute(android.R.attr.textColorSecondary, outValue, true);
        descriptionColor = outValue.data;
    }

    public void setRepos(List<RepoOwnerJoin> repos) {
        repositories = repos;
    }

    @Override
    public RibotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repo, parent, false);

        return new RibotViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RibotViewHolder holder, int position) {
        RepoOwnerJoin repoOwnerJoin = repositories.get(position);



//        Glide.with(holder.avatarView.getContext())
//                .load(repository.owner().avatar_url())
//                .centerCrop()
//                .crossFade()
//                .into(holder.avatarView);

        holder.nameView.setText(repoOwnerJoin.name());
        holder.starsView.setText(String.valueOf(repoOwnerJoin.star()));
        holder.forksView.setText(String.valueOf(repoOwnerJoin.fork()));

        Truss description = new Truss();
//        description.append(repository.owner().login());

        if (!TextUtils.isEmpty(repoOwnerJoin.description())) {
            description.pushSpan(new ForegroundColorSpan(descriptionColor));
            description.append(" — ");
            description.append(repoOwnerJoin.description());
            description.popSpan();
        }

        holder.descriptionView.setText(description.build());
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }

    class RibotViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.trending_repository_avatar)
        ImageView avatarView;
        @Bind(R.id.trending_repository_name)
        TextView nameView;
        @Bind(R.id.trending_repository_description)
        TextView descriptionView;
        @Bind(R.id.trending_repository_stars)
        TextView starsView;
        @Bind(R.id.trending_repository_forks)
        TextView forksView;

        public RibotViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
