package week_12.q2_github;


public class GitHubUser {

    public String login;
    public String name;
    public String location;

    /*
        TODO finish this class.
            This should contain fields for a
            GitHub user's login, their name, and their location.

        TODO complete the GitHubUser class
        *  GitHubUser should have public fields for
        *  The login  (the name used to log into GitHub, AKA username)
        *  The user's location
        *  The user's name  (their human name)
        *
        * Ensure the names and types of these fields match the data returned in an API response.
        *
        * TODO make a request to the API
        *  Convert the response to a GitHubUser object.
        *  This object will store data from the API response.
        *  Return the GitHubUser object.

        TODO Use public fields. Don't use private fields with getters and setters.

        For example, in this response,
        https://api.github.com/users/gvanrossum
        The login is "gvanrossum"
        The name is "Guido van Rossum"
        The location is "San Francisco Bay Area"
    */

    public static GitHubUser getUserInformation(String gitHubUserName) {
        String url = "https://api.github.com/users/" + gitHubUserName;

        // Make the request to the GitHub API
        HttpResponse<GitHubUser> response = Unirest.get(url)
                .header("Accept", "application/vnd.github.v3+json")
                .asObject(GitHubUser.class);

        // Check the response status
        if (response.isSuccess()) {
            // Get the user information from the response body
            GitHubUser user = response.getBody();
            return user;
        } else {
            // Handle the request failure
            System.out.println("Failed to retrieve user information from GitHub");
            return null;
        }
    }


}


