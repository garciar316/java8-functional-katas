package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.BoxArt;
import model.MovieList;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
    Goal: Retrieve id, title, and a 150x200 box art url for every video
    DataSource: DataUtil.getMovieLists()
    Output: List of ImmutableMap.of("id", "5", "title", "Bad Boys", "boxart": BoxArt)
*/
public class Kata4 {

    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();
        return movieLists.stream().flatMap(movie ->
            movie.getVideos().stream().map(video ->
                ImmutableMap.of(video.getId(), 5, "title", video.getTitle(), "boxart", new BoxArt(150, 200, video.getUri()))
            )
        ).collect(Collectors.toUnmodifiableList());
    }
}
