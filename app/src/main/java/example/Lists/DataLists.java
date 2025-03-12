package example.Lists;

import java.util.ArrayList;

public interface DataLists<T> {
    public void addToList(T item);
    public void removeFromList(T item);
    public ArrayList<T> getList();
    public ArrayList<T> getByTouristDestination(String touristDestinationName);
}
