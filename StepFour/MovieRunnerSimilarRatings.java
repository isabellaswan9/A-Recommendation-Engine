
/**
 * 在这里给出对类 MovieRunnerSimilarRatings 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;

public class MovieRunnerSimilarRatings {
    public void printAverageRatings(){
        String moviefile = "ratedmoviesfull.csv";
        String ratingfile = "ratings.csv";
        FourthRatings ratingsHandler = new FourthRatings(ratingfile);
   
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

        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        AllFilters f = new AllFilters();
        f.addFilter(new YearAfterFilter(1990));
        f.addFilter(new GenreFilter("Drama"));
        
        String moviefile = "ratedmoviesfull.csv";
        String ratingfile = "ratings.csv";
        FourthRatings ratingsHandler = new FourthRatings(ratingfile);
   
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
    
    public void printSimilarRatings(){
        String moviefile = "ratedmoviesfull.csv";
        String ratingfile = "ratings.csv";
        FourthRatings ratingsHandler = new FourthRatings(ratingfile);
   
        int raterSize = ratingsHandler.getRaterSize();
        System.out.println("numebr of raters :" + raterSize);
        
        MovieDatabase.initialize(moviefile);
        System.out.println("numebr of movies :" + MovieDatabase.size());
        
        ArrayList<Rating> similarRatings = ratingsHandler.getSimilarRatings("71",20,5);
        for(int i = 0; i < 20; i++){
            String title = MovieDatabase.getTitle(similarRatings.get(i).getItem());
            System.out.println(title + similarRatings.get(i).getValue());
        }
        /*
        for(Rating currRating : similarRatings){
            String title = MovieDatabase.getTitle(currRating.getItem());
            System.out.println(title);
        }*/
    }
    public void printSimilarRatingsByGenre(){
        String moviefile = "ratedmoviesfull.csv";
        String ratingfile = "ratings.csv";
        FourthRatings ratingsHandler = new FourthRatings(ratingfile);
        Filter f = new GenreFilter("Mystery");
        
        int raterSize = ratingsHandler.getRaterSize();
        System.out.println("\nnumebr of raters :" + raterSize);
        
        MovieDatabase.initialize(moviefile);
        System.out.println("\nnumebr of movies :" + MovieDatabase.size());
        
        ArrayList<Rating> similarRatings = ratingsHandler.getSimilarRatingsByFilter("964",20,5,f);
        for(int i = 0; i < 20; i++){
            String title = MovieDatabase.getTitle(similarRatings.get(i).getItem());
            System.out.println(title);
        }
        /*
        for(Rating currRating : similarRatings){
            String title = MovieDatabase.getTitle(currRating.getItem());
            System.out.println(title);
        }*/
    }
    
    public void printSimilarRatingsByDirector(){
        String moviefile = "ratedmoviesfull.csv";
        String ratingfile = "ratings.csv";
        FourthRatings ratingsHandler = new FourthRatings(ratingfile);
        Filter f = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        
        int raterSize = ratingsHandler.getRaterSize();
        System.out.println("\nnumebr of raters :" + raterSize);
        
        MovieDatabase.initialize(moviefile);
        System.out.println("\nnumebr of movies :" + MovieDatabase.size());
        
        ArrayList<Rating> similarRatings = ratingsHandler.getSimilarRatingsByFilter("120",10,2,f);
        for(int i = 0; i < 20; i++){
            String title = MovieDatabase.getTitle(similarRatings.get(i).getItem());
            System.out.println(title);
        }
    }
    
    public void printSimilarRatingsByGenreAndMinutes(){
        AllFilters f = new AllFilters();
        f.addFilter(new MinutesFilter(80,160));
        f.addFilter(new GenreFilter("Drama"));
        String moviefile = "ratedmoviesfull.csv";
        String ratingfile = "ratings.csv";
        FourthRatings ratingsHandler = new FourthRatings(ratingfile);
        
        int raterSize = ratingsHandler.getRaterSize();
        System.out.println("\nnumebr of raters :" + raterSize);
        
        MovieDatabase.initialize(moviefile);
        System.out.println("\nnumebr of movies :" + MovieDatabase.size());
        
        ArrayList<Rating> similarRatings = ratingsHandler.getSimilarRatingsByFilter("168",10,3,f);
        for(int i = 0; i < 20; i++){
            String title = MovieDatabase.getTitle(similarRatings.get(i).getItem());
            System.out.println(title);
        }
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes(){
        AllFilters f = new AllFilters();
        f.addFilter(new MinutesFilter(70, 200));
        f.addFilter(new YearAfterFilter(1975));
        String moviefile = "ratedmoviesfull.csv";
        String ratingfile = "ratings.csv";
        FourthRatings ratingsHandler = new FourthRatings(ratingfile);
        
        int raterSize = ratingsHandler.getRaterSize();
        System.out.println("\nnumebr of raters :" + raterSize);
        
        MovieDatabase.initialize(moviefile);
        System.out.println("\nnumebr of movies :" + MovieDatabase.size());
        
        ArrayList<Rating> similarRatings = ratingsHandler.getSimilarRatingsByFilter("314",10,5,f);
        for(int i = 0; i < 20; i++){
            String title = MovieDatabase.getTitle(similarRatings.get(i).getItem());
            System.out.println(title);
        }
    }
}
