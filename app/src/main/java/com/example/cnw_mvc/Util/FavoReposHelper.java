package com.example.cnw_mvc.Util;

import android.app.Application;
import android.content.Context;

import com.example.cnw_mvc.model.Repo;

public class FavoReposHelper {
    private static FavoReposHelper instance;
    private String favoReposJson;
    private Context context;

    public static synchronized FavoReposHelper getInstance() {
        return instance;
    }

    public static void init(Application application) {
        instance = new FavoReposHelper(application);
    }

    private FavoReposHelper(Context context) {
        this.context = context;
        favoReposJson = PreferenceManager.getString(context, "favo_repos", "");
    }

    public boolean contains(Repo repo) {
        if (repo != null) {
            return favoReposJson.contains(repo.getUrl());
        }
        return false;
    }

    public void addFavo(Repo repo) {
        favoReposJson = favoReposJson + "," + repo.getUrl();
        saveToPref();
    }

    public void removeFavo(Repo repo) {
        favoReposJson = favoReposJson.replace(repo.getUrl(), "");
        saveToPref();
    }

    private void saveToPref() {
        PreferenceManager.putString(context, "favo_repos", favoReposJson);
    }
}
