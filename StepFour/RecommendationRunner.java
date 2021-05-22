
/**
 * 在这里给出对类 RecommendationRunner 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;
public class RecommendationRunner implements Recommender{
    public ArrayList<String> getItemsToRate(){
        //MovieDatabase.initialize("ratedmoviesfull.csv");
        //ArrayList<String> ret = MovieDatabase.getRandomMovies(10);
        Filter f = new TrueFilter();
        ArrayList<String> ourMovies = MovieDatabase.filterBy(f);
        ArrayList<String> ret = new ArrayList<String>();
        Random random = new Random();
        //String[] moviesID = ourMovies.keySet().toArray(new String[0]);
        for(int i = 0; i < 10; i++){
            String randomMovie = ourMovies.get(random.nextInt(ourMovies.size()));
            while(ret.contains(randomMovie)){
                randomMovie = ourMovies.get(random.nextInt(ourMovies.size()));
            }
            ret.add(randomMovie);
        }
        
        return ret;
    }
    public void printRecommendationsFor(String webRaterID){
        
        FourthRatings ratingsHandler = new FourthRatings();
        //Filter f = new GenreFilter("Action");
        ArrayList<Rating> similarRatings = ratingsHandler.getSimilarRatings(webRaterID,20,5);
        if(similarRatings.size() == 0){
            System.out.println("<p>No movies Recommended</p>");
        }
        else{
            System.out.println("<p>There are recommended movies for you.<p>");
            if(similarRatings.size() < 10){
                for(int i = 0; i < similarRatings.size(); i++){
                    String currMovieID = similarRatings.get(i).getItem();
                    System.out.println("<p>Top " + i + " :" + MovieDatabase.getTitle(currMovieID) + "</p>");
                }
            }
            else{
                for(int i = 0; i < 10; i++){
                    String currMovieID = similarRatings.get(i).getItem();
                    System.out.println("<p>Top " + i + " :" + MovieDatabase.getYear(currMovieID) + " " + 
                    MovieDatabase.getTitle(currMovieID) + " Rating:" +  similarRatings.get(i).getValue() + "</p>");
                }
            }
        }
    }
    
}
