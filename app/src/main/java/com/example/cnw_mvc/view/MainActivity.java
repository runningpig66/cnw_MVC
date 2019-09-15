package com.example.cnw_mvc.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cnw_mvc.R;
import com.example.cnw_mvc.adapter.RepoListAdapter;
import com.example.cnw_mvc.model.GithubService;
import com.example.cnw_mvc.model.Repo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.text_description)
    TextView textDescription;
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.text_info)
    TextView textInfo;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        textDescription.setText("GitHub Java");
        loadGitHubRepo();
    }

    private void loadGitHubRepo() {
        String url = "https://github-trending-api.now.sh/repositories";
        GithubService.Factory.create().javaRepositories(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Repo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(List<Repo> repos) {
                        progress.setVisibility(View.GONE);
                        setupRecyclerView(recycler, repos);
                    }

                    @Override
                    public void onError(Throwable e) {
                        progress.setVisibility(View.GONE);
                        textInfo.setVisibility(View.VISIBLE);
                        recycler.setVisibility(View.GONE);
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        textInfo.setVisibility(View.GONE);
                    }
                });
    }

    private void setupRecyclerView(RecyclerView recyclerView, List<Repo> repos) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RepoListAdapter adapter = new RepoListAdapter(this, repos);
        recyclerView.setAdapter(adapter);

    }
}
