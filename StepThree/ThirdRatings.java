
/**
 * 在这里给出对类 ThirdRatings 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile){
        FirstRatings ratingHandler = new FirstRatings();
        myRaters = ratingHandler.loadRaters(ratingsfile);
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
        ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> averageRatings = new ArrayList<Rating>();
        for(String movie_id : myMovies){
            double averageRating = getAverageByID(movie_id, minimalRaters);
            if(averageRating != 0.0){
                averageRatings.add(new Rating(movie_id, averageRating));
            }
        }
        return averageRatings;
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
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> ratingByFilter = new ArrayList<Rating>();
        ArrayList<String> myMovies = MovieDatabase.filterBy(filterCriteria);
        for(String movie_id : myMovies){
            double averageRating = getAverageByID(movie_id, minimalRaters);
            if(averageRating != 0.0){
                ratingByFilter.add(new Rating(movie_id, averageRating));
            }
        }
        return ratingByFilter;
    }
}

