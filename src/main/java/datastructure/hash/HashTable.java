package datastructure.hash;

public interface HashTable<T> {
    public void insert(String key, T value);

    public T search(String key);

    public void delete(String key);

    public String toString();
}
