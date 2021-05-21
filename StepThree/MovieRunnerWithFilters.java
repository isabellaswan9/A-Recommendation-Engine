
/**
 * 在这里给出对类 MovieRunnerWithFilters 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;
public class MovieRunnerWithFilters {
    public void printAverageRatings(){
        String moviefile = "ratedmoviesfull.csv";
        String ratingfile = "ratings.csv";
        ThirdRatings ratingsHandler = new ThirdRatings(ratingfile);
   
        int raterSize = ratingsHandler.getRaterSize();
        System.out.println("\nnumebr of raters :" + raterSize);
        
        MovieDatabase.initialize(moviefile);
        System.out.println("\nnumebr of movies :" + MovieDatabase.size());
        
        ArrayList<Rating> averageRatings = ratingsHandler.getAverageRatings(35);
        System.out.println("found " + averageRatings.size() + " movies");
        //Rating lowestRating = new Rating("",-1.0);
        //String idWithLowest = "";
        for(Rating currRating : averageRatings){
            System.out.println(currRating.getValue() + " " + MovieDatabase.getTitle(currRating.getItem()));
            /*
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
            */
        }
        //System.out.println(lowestRating.getValue() + ":" + ratingsHandler.getTitle(lowestRating.getItem()));
    }
    
    public void printAverageRatingsByYear(){
        Filter f = new YearAfterFilter(2000);
        String moviefile = "ratedmoviesfull.csv";
        String ratingfile = "ratings.csv";
        ThirdRatings ratingsHandler = new ThirdRatings(ratingfile);
   
        int raterSize = ratingsHandler.getRaterSize();
        System.out.println("numebr of raters :" + raterSize);
        
        MovieDatabase.initialize(moviefile);
        System.out.println("numebr of movies :" + MovieDatabase.size());
        
        ArrayList<Rating> averageRatings = ratingsHandler.getAverageRatingsByFilter(20,f);
        System.out.println("found " + averageRatings.size() + " movies");
        for(Rating currRating : averageRatings){
            System.out.println(currRating.getValue() + " " + MovieDatabase.getTitle(currRating.getItem()));
        }
    }
    
    public void printAverageRatingsByGenre(){
        Filter f = new GenreFilter("Comedy");
        String moviefile = "ratedmoviesfull.csv";
        String ratingfile = "ratings.csv";
        ThirdRatings ratingsHandler = new ThirdRatings(ratingfile);
   
        int raterSize = ratingsHandler.getRaterSize();
        System.out.println("numebr of raters :" + raterSize);
        
        MovieDatabase.initialize(moviefile);
        System.out.println("numebr of movies :" + MovieDatabase.size());
        
        ArrayList<Rating> averageRatings = ratingsHandler.getAverageRatingsByFilter(20,f);
        System.out.println("found " + averageRatings.size() + " movies");
        for(Rating currRating : averageRatings){
            String currMovieID = currRating.getItem();
            System.out.println(currRating.getValue() + " " + MovieDatabase.getTitle(currMovieID) + "\n    "
            + MovieDatabase.getGenres(currMovieID));
        }
    }
    
    public void printAverageRatingsByMinutes(){
        Filter f = new MinutesFilter(105,135);
        String moviefile = "ratedmoviesfull.csv";
        String ratingfile = "ratings.csv";
        ThirdRatings ratingsHandler = new ThirdRatings(ratingfile);
   
        int raterSize = ratingsHandler.getRaterSize();
        System.out.println("numebr of raters :" + raterSize);
        
        MovieDatabase.initialize(moviefile);
        System.out.println("numebr of movies :" + MovieDatabase.size());
        
        ArrayList<Rating> averageRatings = ratingsHandler.getAverageRatingsByFilter(5,f);
        System.out.println("found " + averageRatings.size() + " movies");
        /*for(Rating currRating : averageRatings){
            String currMovieID = currRating.getItem();
            System.out.println(currRating.getValue() + " " + MovieDatabase.getTitle(currMovieID) + " Time:"
            + MovieDatabase.getMinutes(currMovieID));
        }*/
    }
    
    public void printAverageRatingsByDirectors(){
        Filter f = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        String moviefile = "ratedmoviesfull.csv";
        String ratingfile = "ratings.csv";
        ThirdRatings ratingsHandler = new ThirdRatings(ratingfile);
   
        int raterSize = ratingsHandler.getRaterSize();
        System.out.println("numebr of raters :" + raterSize);
        
        MovieDatabase.initialize(moviefile);
        System.out.println("numebr of movies :" + MovieDatabase.size());
        
        ArrayList<Rating> averageRatings = ratingsHandler.getAverageRatingsByFilter(4,f);
        System.out.println("found " + averageRatings.size() + " movies");
        /*for(Rating currRating : averageRatings){
            String currMovieID = currRating.getItem();
            System.out.println(currRating.getValue() + " " + MovieDatabase.getTitle(currMovieID) + " Director:"
            + MovieDatabase.getDirector(currMovieID));
        }*/
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        AllFilters f = new AllFilters();
        f.addFilter(new YearAfterFilter(1990));
        f.addFilter(new GenreFilter("Drama"));
        
        String moviefile = "ratedmoviesfull.csv";
        String ratingfile = "ratings.csv";
        ThirdRatings ratingsHandler = new ThirdRatings(ratingfile);
   
        int raterSize = ratingsHandler.getRaterSize();
        System.out.println("numebr of raters :" + raterSize);
        
        MovieDatabase.initialize(moviefile);
        System.out.println("numebr of movies :" + MovieDatabase.size());
        
        ArrayList<Rating> averageRatings = ratingsHandler.getAverageRatingsByFilter(8,f);
        System.out.println("found " + averageRatings.size() + " movies");
        /*for(Rating currRating : averageRatings){
            String currMovieID = currRating.getItem();
            System.out.println(currRating.getValue() + " " + MovieDatabase.getTitle(currMovieID) 
            + MovieDatabase.getYear(currMovieID)
            + MovieDatabase.getGenres(currMovieID));
        }*/
    }
    
    public void printAverageRatingsByDirectorsAndMinutes(){
        AllFilters f = new AllFilters();
        f.addFilter(new MinutesFilter(90,180));
        f.addFilter(new DirectorsFilter("lint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
        
        String moviefile = "ratedmoviesfull.csv";
        String ratingfile = "ratings.csv";
        ThirdRatings ratingsHandler = new ThirdRatings(ratingfile);
   
        int raterSize = ratingsHandler.getRaterSize();
        System.out.println("numebr of raters :" + raterSize);
        
        MovieDatabase.initialize(moviefile);
        System.out.println("numebr of movies :" + MovieDatabase.size());
        
        ArrayList<Rating> averageRatings = ratingsHandler.getAverageRatingsByFilter(3,f);
        System.out.println("found " + averageRatings.size() + " movies");
        /*for(Rating currRating : averageRatings){
            String currMovieID = currRating.getItem();
            System.out.println(currRating.getValue() + " " + MovieDatabase.getTitle(currMovieID) 
            + " Time:"
            + MovieDatabase.getMinutes(currMovieID)
            + MovieDatabase.getDirector(currMovieID));
        }*/
    }
}
