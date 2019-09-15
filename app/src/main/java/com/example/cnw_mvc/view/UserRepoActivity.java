package com.example.cnw_mvc.view;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cnw_mvc.R;
import com.example.cnw_mvc.adapter.RepositoryAdapter;
import com.example.cnw_mvc.model.GithubService;
import com.example.cnw_mvc.model.Repository;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserRepoActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.button_search)
    ImageButton buttonSearch;
    @BindView(R.id.edit_text_username)
    EditText editTextUsername;
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.text_info)
    TextView textInfo;
    @BindView(R.id.repos_recycler_view)
    RecyclerView reposRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_repo);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        addTextListener();
        buttonSearch.setOnClickListener(this);
        String username = getIntent().getStringExtra("username");
        editTextUsername.setText(username);
        loadGithubRepos(username);
    }

    private void loadGithubRepos(String username) {
        GithubService.Factory.create().publicRepositories(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Repository>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(List<Repository> repositories) {
                        progress.setVisibility(View.GONE);
                        reposRecyclerView.setVisibility(View.VISIBLE);
                        setupRecyclerView(reposRecyclerView, repositories);
                    }

                    @Override
                    public void onError(Throwable e) {
                        progress.setVisibility(View.GONE);
                        textInfo.setVisibility(View.VISIBLE);
                        reposRecyclerView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onComplete() {
                        textInfo.setVisibility(View.GONE);
                    }
                });
    }

    private void setupRecyclerView(RecyclerView reposRecyclerView, List<Repository> repositories) {
        reposRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RepositoryAdapter adapter = new RepositoryAdapter(this, repositories);
        reposRecyclerView.setAdapter(adapter);
    }

    private void addTextListener() {
        editTextUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                buttonSearch.setVisibility(charSequence.length() > 0 ? View.VISIBLE : View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_search:
                hideSoftKeyboard();
                textInfo.setVisibility(View.GONE);
                progress.setVisibility(View.VISIBLE);
                reposRecyclerView.setVisibility(View.GONE);
                loadGithubRepos(editTextUsername.getText().toString().trim());
                break;
        }
    }

    private void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(editTextUsername.getWindowToken(), 0);
        }
    }
}
