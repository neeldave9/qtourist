package example.data;

import java.util.ArrayList;
public class FavDesArray {
    //Create array (empty)
    private ArrayList<FavDestination> favListArray;
    public FavDesArray() {favListArray = new ArrayList<>();}

    //Method to add a favourites destination to the list
    public void addFavDes(FavDestination favlist) {favListArray.add(favlist);}

    //Method to remove a favourites destination from a list
    public void removeFavDes(FavDestination favlist) {favListArray.remove(favlist);}

    //Method to retrieve the current array and display it
    public ArrayList<FavDestination> getFavDesArray() {
        return favListArray;
    }
}
