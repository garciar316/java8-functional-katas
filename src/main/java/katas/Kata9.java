package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.*;
import util.DataUtil;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
    Goal: Retrieve each video's id, title, middle interesting moment time, and smallest box art url
    DataSource: DataUtil.getMovies()
    Output: List of ImmutableMap.of("id", 5, "title", "some title", "time", new Date(), "url", "someUrl")
*/
public class Kata9 {

    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();
        return   movieLists.stream().flatMap(
                        movies -> movies.getVideos().stream().map(
                                video -> ImmutableMap.of(
                                        "id", video.getId(),
                                        "title", video.getTitle(),
                                        "time", video.getInterestingMoments().stream().filter(
                                          interestingMoment -> interestingMoment.getType().equals("Middle"))
                                                .findFirst().orElseThrow().getTime(),
                                        "url", video.getBoxarts().stream().min(Comparator.comparing(
                                                boxArt -> boxArt.getWidth() * boxArt.getHeight()))
                                                .map(BoxArt::getUrl).orElseThrow()
                                ))).collect(Collectors.toUnmodifiableList());
    }

    @FunctionalInterface
    public interface InterestingMoment {
        Date getTime(Stream<InterestingMoment> stream);
    }
}
