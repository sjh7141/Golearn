package com.golearn.client;

public interface RestClient<T> {
    T get(int id);
}
