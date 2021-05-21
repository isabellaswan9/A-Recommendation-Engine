/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviesfile, String ratingsfile){
        FirstRatings ratingHandler = new FirstRatings();
        myMovies = ratingHandler.loadMovies(moviesfile);
        myRaters = ratingHandler.loadRaters(ratingsfile);
    }
    
    public int getMovieSize(){
        return myMovies.size();
    }
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    public double getAverageByID(String id, int minimalRaters){
        int numRated = 0;
        double totalRatings = 0;
        for(Rater rater : myRaters){
            if(rater.hasRating(id)){
                numRated++;
                totalRatings += rater.getRating(id);
            }
        }
        if(numRated >= minimalRaters){
            return totalRatings/numRated;
        }
        return 0.0;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> averageRatings = new ArrayList<Rating>();
        for(Movie movie : myMovies){
            String id = movie.getID();
            double averageRating = getAverageByID(id, minimalRaters);
            if(averageRating != 0.0){
                averageRatings.add(new Rating(id, averageRating));
            }
        }
        return averageRatings;
    }
    
    public String getTitle(String id){
        for(Movie movie : myMovies){
            if(movie.getID().equals(id)){
                return movie.getTitle();
            }
        }
        return "This ID was not found.";
    }
    
    public String getID(String title){
        for(Movie movie : myMovies){
            if(movie.getTitle().equals(title)){
                return movie.getID();
            }
        }
        return "NO SUCH TITLE";
    }
    
    public HashMap<String,Integer> countEveryMovie(){
        //ArrayList<Rater> testRaterArr = ratingHandler.loadRaters("fileName");
        HashMap<String,Integer> countEveryMovieWithRatings = new HashMap<String,Integer>();
        for(Rater rater : myRaters){
            ArrayList<String> itemsRated = rater.getItemsRated();
            for(String item : itemsRated){
                if(countEveryMovieWithRatings.containsKey(item)){
                    countEveryMovieWithRatings.put(item,countEveryMovieWithRatings.get(item)+1);
                }  
                else{
                    countEveryMovieWithRatings.put(item,1);
                }
            }
        }
        return countEveryMovieWithRatings;
    }
}
