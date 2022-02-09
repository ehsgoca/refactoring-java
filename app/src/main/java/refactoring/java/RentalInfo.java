package refactoring.java;

import java.util.HashMap;
import refactoring.java.model.Customer;
import refactoring.java.model.Movie;
import refactoring.java.model.MovieCategory;
import refactoring.java.model.MovieRental;

public class RentalInfo {

  public String statement(Customer customer) {
    HashMap<String, Movie> movies = new HashMap();
    movies.put("F001", new Movie("You've Got Mail", MovieCategory.REGULAR));
    movies.put("F002", new Movie("Matrix", MovieCategory.REGULAR));
    movies.put("F003", new Movie("Cars", MovieCategory.CHILDRENS));
    movies.put("F004", new Movie("Fast & Furious X", MovieCategory.NEW));

    double totalAmount = 0;
    int frequentEnterPoints = 0;
    String result = "Rental Record for " + customer.getName() + "\n";
    for (MovieRental r : customer.getRentals()) {
      double thisAmount = 0;

      // determine amount for each movie
      if (movies.get(r.getMovieId()).getCategory() == MovieCategory.REGULAR) {
        thisAmount = 2;
        if (r.getDays() > 2) {
          thisAmount = ((r.getDays() - 2) * 1.5) + thisAmount;
        }
      }
      if (movies.get(r.getMovieId()).getCategory() == MovieCategory.NEW) {
        thisAmount = r.getDays() * 3;
      }
      if (movies.get(r.getMovieId()).getCategory() == MovieCategory.CHILDRENS) {
        thisAmount = 1.5;
        if (r.getDays() > 3) {
          thisAmount = ((r.getDays() - 3) * 1.5) + thisAmount;
        }
      }

      //add frequent bonus points
      frequentEnterPoints++;
      // add bonus for a two day new release rental
      if (movies.get(r.getMovieId()).getCategory() == MovieCategory.NEW && r.getDays() > 2) frequentEnterPoints++;

      //print figures for this rental
      result += "\t" + movies.get(r.getMovieId()).getTitle() + "\t" + thisAmount + "\n";
      totalAmount = totalAmount + thisAmount;
    }
    // add footer lines
    result += "Amount owed is " + totalAmount + "\n";
    result += "You earned " + frequentEnterPoints + " frequent points\n";

    return result;
  }
}
