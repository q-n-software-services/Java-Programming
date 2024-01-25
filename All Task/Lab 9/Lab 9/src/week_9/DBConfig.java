package week_9;


public class DBConfig {

    /*
     * Don't modify this class.
     *
     * Your code will use the db_url variable to create a connection to the database.
     * You can access this variable from elsewhere in the project with,
     *
     * DBConfig.db_url
     *
     * When the tests run, they'll find this variable and change it to the
     * location of the test database. When the tests end, they'll change it back
     * to the original value.
     *
     * You don't need to do anything in your code to make this happen,
     * except making sure that the products.db and the products_test.db
     * databases exist before the code, or the tests, run.
     *
     * See instructions in the Lab 9 Questions.md file for creating the databases.
     *
     * */


    static String db_url = "jdbc:sqlite:products.sqlite";

}

