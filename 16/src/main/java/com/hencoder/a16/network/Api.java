package com.hencoder.a16.network;

import com.hencoder.a16.model.Repo;
import com.hencoder.a16.model.User;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    @GET("users/{username}/repos")
    Single<List<Repo>> getRepos(@Path("username") String username);

    @GET("users/{username}/{repo_name}/forks")
    Single<List<Repo>> getForks(@Path("username") String username, @Path("repo_name") String repoName);

    @GET("repos/rengwuxian/{repo_name}")
    Single<Repo> getRepo(@Path("repo_name") String repoName);

    @GET("users")
    Single<List<User>> getUsers();

    @GET("users/{username}")
    Single<User> getUser(@Path("username") String username);
}
