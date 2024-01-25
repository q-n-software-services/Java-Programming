package week_4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static input.InputUtils.stringInput;
import static input.InputUtils.yesNoInput;

/**

 Finish the methods in this program that creates and manages a movie watchlist.
 Follow the instructions in the methods.
 */
public class Question_3_Movie_Watch_List {
    public static void main(String[] args) {

        // Create new ArrayList to contain names of movies to watch
        List<String> movieWatchList = new ArrayList<>();

        // Adding movies
        do {
            String newMovieName = stringInput("Enter name of movie to watch");
            addMovie(newMovieName, movieWatchList);
        } while (yesNoInput("Add more movies?"));

        // Get the next movie to watch
        String nextMovie = getNextMovie(movieWatchList);
        if (nextMovie != null) {
            System.out.println("The next movie to watch is " + nextMovie);
        } else {
            System.out.println("There are no movies in the watchlist.");
        }

        // Getting a random movie
        String randomMovie = getRandomMovieFromWatchList(movieWatchList);
        if (randomMovie != null) {
            System.out.println("A random movie from the list is " + randomMovie);
        } else {
            System.out.println("There are no movies in the watchlist.");
        }

        // Removing a movie - you've watched it, or don't want to watch it
        String movieToRemove = stringInput("Enter the name of a movie to remove");
        removeMovie(movieToRemove, movieWatchList);

        // Display all movies in order added
        System.out.println("\n ** All movies, in order to watch **\n");
        printMoviesInWatchListOrder(movieWatchList);

        // Display all movies in name order
        System.out.println("\n ** All movies, in name order **\n");
        printMoviesInNameOrder(movieWatchList);
    }


    public static void addMovie(String movie, List<String> movies) {

    /* Part 1.

    TODO finish this method.

    This method should add the String movie to the END of the movies List,
    but only if the movie is not in the list.

    Don't change the case of the movie string when adding it to the movies list.
    If the movie is 'WALL-E' then add this exact string.
    If the movie is 'Star Wars: Episode IV – A New Hope' add this exact string.

    If the movies list contains ['Up', 'Jaws', 'Spiderman']
    and the movie String is 'Rocky' then it should be added to the end of the list.
    The movies list will become ['Up', 'Jaws', 'Spiderman', 'Rocky']
    Print the message "Movie added!"


    Don't add the movie if it is already in the movies list.
    Your check should be case-insensitive.
    If the movies list contains ['Up', 'Jaws', 'Spiderman']
    and if the movie String is 'Up' then it should NOT be added.
    or, if the movie String is 'up' then it should NOT be added.
    or, if the movie String is 'UP' then it should NOT be added.

    If the movie is already in the list, print the message "This movie is already in your watchlist!"

    This method does not need to return anything.

     */

        // convert movie string to lowercase for case-insensitive comparison
        String lowercaseMovie = movie.toLowerCase();

        // check if movie is already in the list
        if (movies.contains(lowercaseMovie)) {
            System.out.println("This movie is already in your watchlist!");
        } else {
            // add movie to the end of the list
            movies.add(movie);
            System.out.println("Movie added!");
        }
    }


    public static String getNextMovie(List<String> movies) {

        /* Part 2.
        TODO finish this method.
         If the movies list is not null, and has as at least one movie in it,
         return the first movie in the list.
         Don't modify the movies list.
         If the movies list is null, or empty, return null.
         Hint: check if the list is null or empty first.
         */
        // Check if the list is null or empty
        if (movies == null || movies.isEmpty()) {
            return null;
        }

        // Return the first movie in the list
        return movies.get(0);
    }
    public static void removeMovie(String movie, List<String> movies) {
/* Part 3.

         TODO finish this method.

         Remove the movie from the movies list.
         Your check should be case-insensitive.

         If the movie is in the movies list, remove that movie and print the
         message "Movie removed!"

         If the movies list contains ['Up', 'Jaws', 'Spiderman']
         and the movie String is 'Jaws' then the 'Jaws' entry in the list should be removed.
         or if the movie String is 'jaws' then the 'Jaws' entry in the list should be removed.
         or if the movie String is 'JAWS' then the 'Jaws' entry in the list should be removed.

         Print the message "Movie removed!"

         If the movies list contains ['Up', 'Jaws', 'Spiderman']
         and the movie String is 'Rocky' then don't modify the movies list
         Print the message "Movie not found!"

         If the movies list is null, or empty, print the message "Movie not found!"
         Hint: check if the list is null or empty first.

         */
        // If the list is null or empty
        if (movies == null || movies.isEmpty()) {
            System.out.println("Movie not found!");
            return;
        }

        // Remove the movie from the movies list
        boolean removed = movies.removeIf(m -> m.equalsIgnoreCase(movie));

        if (removed) {
            System.out.println("Movie removed!");
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

        if  (movies.isEmpty()){
            return null; } else {
            // Select a random index in the range of the watchlist
            Random random = new Random();
            int randomIndex = random.nextInt(movies.size());


            // Return the movie title at the random index
            return movies.get(randomIndex);
        }

    }


    public static void printMoviesInNameOrder(List<String> movies) {
/* Part 5.

        TODO finish this method.

         Print the movie names in alphabetical order, one movie per line.

         ** Don't modify the original movies list! **

         If the movies list contains ['Up', 'Jaws', 'Spiderman'] you will
         print

         Jaws
         Spiderman
         Up

         You should sort the movies using Java's default sort order for strings,
         and print the exact text of the movie names from the list.

         If the movies list contains ['Up', 'jaws', 'Spiderman']
         Note 'jaws' has lowercase 'j' and lowercase letters are sorted after
         uppercase letters.

         you will print

         Spiderman
         Up
         jaws


         If the movies list is empty or null, print the message 'No movies'

         This method will not return anything.
         */
        if (movies == null || movies.isEmpty()) {
            System.out.println("No movies in list");
        } else {
            List<String> sortedMovies = new ArrayList<>(movies);
            sortedMovies.sort(String.CASE_INSENSITIVE_ORDER);
            for (String s : sortedMovies) {
                System.out.println(s);
            }
        }
    }

    public static void printMoviesInWatchListOrder(List<String> movies) {
/* Part 6.

        TODO Print the movie names in watchlist order, one movie per line.
        Include a number to indicate the movie's position in the watch list.

         ** Don't modify the original movies list! **

         Don't print anything else - only the names of movies and the numbers.

         If the movies list contains ['Up', 'Jaws', 'Spiderman'] you will print

         1. Up
         2. Jaws
         3. Spiderman

         If the movies list is empty or null, print the message 'No movies'

        This method does not return anything.
          */
        if (movies == null || movies.isEmpty()) {
            System.out.println("No movies in list");
        } else {
            for (String s : movies) {
                System.out.println(s);
            }
        }
    }
}


