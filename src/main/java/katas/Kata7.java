package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.Bookmark;
import model.BoxArt;
import model.Movie;
import model.MovieList;
import util.DataUtil;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Retrieve the id, title, and smallest box art url for every video
    DataSource: DataUtil.getMovieLists()
    Output: List of ImmutableMap.of("id", "5", "title", "Bad Boys", "boxArt": "url)
*/
public class Kata7 {

    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();
        return movieLists.stream().flatMap(
                        movies -> movies.getVideos().stream().map(
                                video -> ImmutableMap.of(
                                        "id", video.getId(),
                                        "title", video.getTitle(),
                                        "boxArt",
                                        video.getBoxarts().stream().min(Comparator.comparing(
                                                boxArt -> boxArt.getWidth() * boxArt.getHeight()))
                                                .map(BoxArt::getUrl).orElseThrow())))
                        .collect(Collectors.toUnmodifiableList());
    }
}
