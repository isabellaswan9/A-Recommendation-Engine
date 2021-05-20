
/**
 * 在这里给出对类 FirstRatings 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.util.*;

public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename){
        FileResource fr = new FileResource(filename);
        ArrayList<Movie> movieArr = new ArrayList<Movie>();
        for(CSVRecord movieMessage : fr.getCSVParser()){
            String id = movieMessage.get("id");
            String title = movieMessage.get("title");
            String year = movieMessage.get("year");
            String country = movieMessage.get("country");
            String genre = movieMessage.get("genre");
            String director = movieMessage.get("director");
            int minutes = Integer.parseInt(movieMessage.get("minutes"));
            String poster = movieMessage.get("poster");
            Movie movie = new Movie(id, title, year, genre, director, country, poster, minutes);
            movieArr.add(movie);
        }
        return movieArr;
    }
    public void testLoadMovies(){
        ArrayList<Movie> testMovieArr = loadMovies("data/ratedmoviesfull.csv");
        //System.out.println(testMovieArr);
        System.out.println("There are " + testMovieArr.size() + " movies");
        int countGenre = 0;
        String queryGenre = "Comedy";
        int countTime = 0;
        int queryTime = 150;
        HashMap<String,Integer> directorToMoviesNumber = new HashMap<String,Integer>();
        for(Movie movie : testMovieArr){
            if(movie.getGenres().contains(queryGenre)){
                countGenre++;
            }
            if(movie.getMinutes() > queryTime){
                countTime++;
            }
            String director = movie.getDirector();
            String[] directors = director.split(",");
            for(int i = 0; i < directors.length; i++){
                if(directorToMoviesNumber.containsKey(directors[i])){
                    directorToMoviesNumber.put(directors[i],directorToMoviesNumber.get(directors[i])+1);
                    
                }
                else{
                    directorToMoviesNumber.put(directors[i],1);
                }
            }
        }
        int maxNumMovies = 0;
        String directorWithMax = "";
        for(String currDirector : directorToMoviesNumber.keySet()){
            int numMovies = directorToMoviesNumber.get(currDirector);
            if(numMovies > maxNumMovies){
                maxNumMovies = numMovies;
                directorWithMax = currDirector;
            }
        }
        //String directorWithMax = directorToMoviesNumber.getKey(maxNumMovies);
        System.out.println(countGenre + " movies include the " + queryGenre + " Genre");
        System.out.println(countTime + " movies are greater than " + queryTime + " minutes in length");
        System.out.println("the maximum number of films:" + maxNumMovies + " directed by " + directorWithMax);
    }
    public ArrayList<Rater> loadRaters(String filename){
        FileResource fr = new FileResource(filename);
        ArrayList<Rater> raterArr = new ArrayList<Rater>();
        for(CSVRecord raterMessage : fr.getCSVParser()){
            String rater_id = raterMessage.get("rater_id");
            String movie_id = raterMessage.get("movie_id");
            Double rater = Double.parseDouble(raterMessage.get("rating"));
            boolean contains = false;
            for(Rater currRater : raterArr){
                if(currRater.getID().equals(rater_id)){
                    currRater.addRating(movie_id, rater);
                    contains = true;
                }
            }
            if(!contains){
                Rater myRater = new Rater(rater_id);
                myRater.addRating(movie_id, rater);
                raterArr.add(myRater);
            }
        }
        return raterArr;
    }
    public void testLoadRaters(){
        ArrayList<Rater> testRaterArr = loadRaters("data/ratings.csv");
        
        //System.out.println(testRaterArr);
        System.out.println("There are " + testRaterArr.size() + " raters");
        int countRatings = 0;
        String queryRaterID = "193";
        String queryMovie = "1798709";
        int countMovieRated = 0;
        HashMap<String,Integer> countEveryMovie = new HashMap<String,Integer>();
        int maxRatings = 0;
        //HashMap<String,Integer> raterWithNumRatings = new HashMap<String,Integer>();
        for(Rater rater : testRaterArr){
            //System.out.println("id:" + rater.getID() + rater.getRating());
            ArrayList<String> itemsRated = rater.getItemsRated();
            if(rater.getID().equals(queryRaterID)){
                countRatings = rater.numRatings();
            }
            
            if(rater.hasRating(queryMovie)){
                countMovieRated++;
            }
            
            for(String item : itemsRated){
                if(countEveryMovie.containsKey(item)){
                    countEveryMovie.put(item,countEveryMovie.get(item)+1);
                }  
                else{
                    countEveryMovie.put(item,1);
                }
            }
            int numRatings = rater.numRatings();
            if(numRatings > maxRatings){
                maxRatings = numRatings;
            }
            
        }
        ArrayList<String> raterWithMaxRatings = new ArrayList<String>();
        for(Rater rater : testRaterArr){
            if(rater.numRatings() == maxRatings){
                raterWithMaxRatings.add(rater.getID());
            }
        }
        //System.out.println("There are " + countRatings + " raters");
        System.out.println(countMovieRated + " raters rated the movie " + queryMovie);
        System.out.println("rater " + queryRaterID + " has " + countRatings + " rating");
        System.out.println(countEveryMovie.size() + " different movies have been rated by all these raters.");
        System.out.println("the maximum number of ratings:" 
        + maxRatings + " and those raters are:" + raterWithMaxRatings);
    }
}
