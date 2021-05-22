
/**
 * 在这里给出对类 FourthRatings 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;

public class FourthRatings {
    private ArrayList<Rater> myRaters;
    private ArrayList<String> myMovies;
    public FourthRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public FourthRatings(String ratingsfile){
        RaterDatabase.initialize(ratingsfile);
        myRaters = RaterDatabase.getRaters();
        myMovies = MovieDatabase.filterBy(new TrueFilter());
    }

    public int getRaterSize(){
        //ArrayList<Rater> myRaters = RaterDatabase.getRaters();
        return myRaters.size();
    }
    
    public double getAverageByID(String id, int minimalRaters){
        int numRated = 0;
        double totalRatings = 0;
        //ArrayList<Rater> myRaters = RaterDatabase.getRaters();
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
        //ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> averageRatings = new ArrayList<Rating>();
        for(String movie_id : myMovies){
            double averageRating = getAverageByID(movie_id, minimalRaters);
            if(averageRating != 0.0){
                averageRatings.add(new Rating(movie_id, averageRating));
            }
        }
        return averageRatings;
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
    
    private double dotProduct(Rater me, Rater r){
        ArrayList<String> ratedByMe = me.getItemsRated();
        double dotProduct = 0;
        for(String rated : ratedByMe){
            if(r.hasRating(rated)){
                dotProduct += (me.getRating(rated) - 5) * (r.getRating(rated) - 5);
            }
        }
        return dotProduct;
    }
    
    private ArrayList<Rating> getSimilarities(String rater_id){
        //ArrayList<Rater> myRaters = RaterDatabase.getRaters();
        ArrayList<Rating> raterWithDotProduct = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(rater_id);
        for(Rater currRater : myRaters){
            String currID = currRater.getID();
            if(!currID.equals(rater_id)){
                double dotProduct = dotProduct(me, currRater);
                if(dotProduct > 0){
                    raterWithDotProduct.add(new Rating(currID, dotProduct));
                }
            }
        }
        //System.out.println("sort raters before:/n" + raterWithDotProduct);
        Collections.sort(raterWithDotProduct,Collections.reverseOrder());
        //System.out.println("sort raters before:/n" + raterWithDotProduct);
        return raterWithDotProduct;
    }
    
     public ArrayList<Rating> getSimilarRatingsOne(String id, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> weightedRatings = new ArrayList<Rating> ();
        ArrayList<Rating> similarRaters = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        HashMap<String,Double> accumulatedRating = new HashMap<String,Double> ();
        HashMap<String,Integer> accumulatedCount = new HashMap<String,Integer> ();
        
        for (String movieID : movies) {
            double currRating = 0.0;
            int currCount = 0;
            
            for (int k=0; k < numSimilarRaters; k++) {
                Rating r = similarRaters.get(k);
                String raterID = r.getItem();
                double weight = r.getValue();
                
                Rater rater = RaterDatabase.getRater(raterID);
                
                if (rater.hasRating(movieID)) {
                    double rating = rater.getRating(movieID) * weight;
                    currRating += rating;
                    currCount += 1;
                }
            }
            
            if (currCount >= minimalRaters) {
                accumulatedRating.put(movieID, currRating);
                accumulatedCount.put(movieID, currCount);
            }
        }
        
        for (String movieID : accumulatedRating.keySet()) {
            double weightedRating = Math.round((accumulatedRating.get(movieID) / accumulatedCount.get(movieID)) * 100.0) / 100.0;
            Rating rating = new Rating (movieID, weightedRating);
            weightedRatings.add(rating);
        }
        
        Collections.sort(weightedRatings, Collections.reverseOrder());
        return weightedRatings;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        ArrayList<Rating> similarties = getSimilarities(id);
        ArrayList<Rating> moviesWithRating = new ArrayList<Rating>();
        //ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());
        

        int realNumSimilarRaters = numSimilarRaters;
        if(similarties.size() < numSimilarRaters){
            realNumSimilarRaters = similarties.size();
        }
        for(String movie_id : myMovies){
            int totalraters = 0;
            double totalRatings = 0.0;
            
            for(int k = 0; k < realNumSimilarRaters; k++){
                Rating r = similarties.get(k);
                
                Rater currRater = RaterDatabase.getRater(r.getItem());
                
                if(currRater.hasRating(movie_id)){
                    totalRatings += (r.getValue() * currRater.getRating(movie_id));
                    totalraters++;
                }
            }
            
            if(totalraters >= minimalRaters){
                double weightedRating = Math.round((totalRatings/totalraters)* 100.0) / 100.0;
                Rating rating = new Rating(movie_id, weightedRating);
                moviesWithRating.add(rating);
            }
            
        }
        //System.out.println("sort movies before:\n" + moviesWithRating);
        Collections.sort(moviesWithRating,Collections.reverseOrder());
        //System.out.println("sort movies after:\n" + moviesWithRating);
        return moviesWithRating;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters,Filter filterCriteria){
        myMovies = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> similarties = getSimilarities(id);
        ArrayList<Rating> moviesWithRating = new ArrayList<Rating>();
        //ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());

        int realNumSimilarRaters = numSimilarRaters;
        if(similarties.size() < numSimilarRaters){
            realNumSimilarRaters = similarties.size();
        }
        
        for(String movie_id : myMovies){
            
            int totalraters = 0;
            double totalRatings = 0.0;
            
            for(int k = 0; k < realNumSimilarRaters; k++){
                Rating r = similarties.get(k);
                
                Rater currRater = RaterDatabase.getRater(r.getItem());
                
                if(currRater.hasRating(movie_id)){
                    totalRatings += (r.getValue() * currRater.getRating(movie_id));
                    totalraters++;
                }
            }
            
            if(totalraters >= minimalRaters){
                double weightedRating = Math.round((totalRatings/totalraters)* 100.0) / 100.0;
                Rating rating = new Rating(movie_id, weightedRating);
                moviesWithRating.add(rating);
            }
        }
        //System.out.println("sort movies before:\n" + moviesWithRating);
        Collections.sort(moviesWithRating,Collections.reverseOrder());
        //System.out.println("sort movies after:\n" + moviesWithRating);
        return moviesWithRating;
    }
}


