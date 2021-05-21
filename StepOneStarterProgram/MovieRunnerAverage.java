
/**
 * 在这里给出对类 MovieRunnerAverage 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;
public class MovieRunnerAverage {
    public void printAverageRatings(){
        String moviefile = "data/ratedmoviesfull.csv";
        String ratingfile = "data/ratings.csv";
        SecondRatings ratingsHandler = new SecondRatings(moviefile,ratingfile);
        int movieSize = ratingsHandler.getMovieSize();
        int raterSize = ratingsHandler.getRaterSize();
        System.out.println("numebr of movies :" + movieSize + "\nnumebr of raters :" + raterSize);
        
        ArrayList<Rating> averageRatings = ratingsHandler.getAverageRatings(12);
        Rating lowestRating = new Rating("",-1.0);
        //String idWithLowest = "";
        for(Rating currRating : averageRatings){
            if(lowestRating.getValue() == -1.0){
                lowestRating = currRating;
            }
            else{
                if(currRating.getValue() < lowestRating.getValue()){
                    lowestRating = currRating;
                    //idWithLowest = currRating.getItem();
                }
            }
            System.out.println(currRating.getValue() + " " + ratingsHandler.getTitle(currRating.getItem()));
        }
        System.out.println(lowestRating.getValue() + ":" + ratingsHandler.getTitle(lowestRating.getItem()));
    }
    
    public void getAverageRatingOneMovie(){
        String moviefile = "data/ratedmoviesfull.csv";
        String ratingfile = "data/ratings.csv";
        SecondRatings ratingsHandler = new SecondRatings(moviefile,ratingfile);
        String movieTitle = "Vacation";
        String movieID = ratingsHandler.getID(movieTitle);
        System.out.println(ratingsHandler.getAverageByID(movieID,3));
        int countLargerRatings = 0;
        int numRatings = 50;
        HashMap<String,Integer> countEveryMovie = ratingsHandler.countEveryMovie();
        for(String movie_id : countEveryMovie.keySet()){
            if(countEveryMovie.get(movie_id) >= numRatings){
                countLargerRatings++;
            }
        }
        System.out.println(countLargerRatings + " movies have "+ numRatings + " or more ratings");
    }
    
    
}
