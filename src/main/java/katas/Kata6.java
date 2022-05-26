package katas;

import model.BoxArt;
import model.Movie;
import util.DataUtil;

import java.util.List;
import java.util.stream.Collectors;

/*
    Goal: Retrieve the url of the largest boxart using map() and reduce()
    DataSource: DataUtil.getMovieLists()
    Output: String
*/
public class Kata6 {

    public static String execute() {
        List<Movie> movies = DataUtil.getMovies();
        return movies.stream().flatMap(movie -> movie.getBoxarts().stream())
                .collect(Collectors.toList())
                .stream().reduce(new BoxArt(0, 0, ""), (count, element) ->
                        ((element.getWidth() * element.getHeight()) > (count.getWidth() * count.getHeight())) ?
                                element : count
                ).getUrl();
    }
}
