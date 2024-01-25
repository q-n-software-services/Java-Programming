package week_4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static input.InputUtils.stringInput;
import static input.InputUtils.yesNoInput;


public class Question_3_Movie_Watch_List {
    static List<String> watchListOfMovies = new ArrayList<>();
    public static void main(String[] args) {



        do {
            String newMovie = stringInput("Enter name of the movie to wish to watch");
            addMovie(newMovie, watchListOfMovies);
        } while (yesNoInput("Do you want to Add more movies?"));

        String nextMovie = getNextMovie(watchListOfMovies);
        if (nextMovie != null) {
            System.out.println("Next movie to watch is " + nextMovie);
        } else {
            System.out.println("There are no movies in the watchlist.");
        }

        String randomMovie = getRandomMovieFromWatchList(watchListOfMovies);
        if (randomMovie != null) {
            System.out.println("A random movie from the Watch-list is " + randomMovie);
        } else {
            System.out.println("No movies in the watchlist.");
        }

        String movieToRemove = stringInput("Enter the name of a movie to remove");
        removeMovie(movieToRemove, watchListOfMovies);

        System.out.println("\n ** All movies, in order to watch **\n");
        printMoviesInWatchListOrder(watchListOfMovies);

        System.out.println("\n ** All movies, in name order **\n");
        printMoviesInNameOrder(watchListOfMovies);
    }


    public static void addMovie(String movie, List<String> movies) {



        String lowercaseMovie = movie.toLowerCase();

        if (movies.contains(lowercaseMovie)) {
            System.out.println("Movie exists in watchlist!");
        } else {
            movies.add(movie);
            System.out.println("Added the Movie ");
        }
    }


    public static String getNextMovie(List<String> movies) {


        if (movies == null || movies.isEmpty()) {
            return null;
        }

        return movies.get(0);
    }
    public static void removeMovie(String movie, List<String> movies) {

        if (movies == null || movies.isEmpty()) {
            System.out.println("Movie not found!");
            return;
        }

        boolean removed = movies.removeIf(m -> m.equalsIgnoreCase(movie));

        if (removed) {
            System.out.println("Movie removed Successfully!");
        } else {
            System.out.println("Movie not found!");
        }
    }




    public static String getRandomMovieFromWatchList(List<String> movies) {
/*
         Part 4.

        TODO finish this method.

         Return the name of a random movie from the movies list.

         Don't modify the movies list.

         If the movies list is null, or empty, return null.
         Hint: check if the list is null or empty first.

         */


    // Check if the watchlist is empty
        if  (watchListOfMovies.isEmpty()){
            return null; } else {
            // Select a random index in the range of the watchlist
            Random random = new Random();
            int randomIndex = random.nextInt(watchListOfMovies.size());


            // Return the movie title at the random index
            return watchListOfMovies.get(randomIndex);
        }

    }


    public static void printMoviesInNameOrder(List<String> movies) {

        if (movies == null || movies.isEmpty()) {
            System.out.println("No movies in list");
        } else {
            List<String> MoviesSorted = new ArrayList<>(movies);
            MoviesSorted.sort(String.CASE_INSENSITIVE_ORDER);
            for (String s : MoviesSorted) {
                System.out.println(s);
            }
        }
    }

    public static void printMoviesInWatchListOrder(List<String> movies) {

        if (movies == null || movies.isEmpty()) {
            System.out.println("No movies in list");
        } else {
            for (String s : movies) {
                System.out.println(s);
            }
        }
    }
}


