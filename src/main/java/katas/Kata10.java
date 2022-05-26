package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Kata10 {
    public static List<Map> execute() {
        List<Map> lists = DataUtil.getLists();
        List<Map> videos = DataUtil.getVideos();
        return ImmutableList.of(ImmutableMap.of("name", "someName", "videos", ImmutableList.of(
                ImmutableMap.of("id", 5, "title", "The Chamber"),
                ImmutableMap.of("id", 3, "title", "Fracture")
        )));
    }

    public static void main(String[] args) {
        List<Map> lists = DataUtil.getLists();

        List<Map> videos = DataUtil.getVideos();
        var v = lists.stream().map(list -> ImmutableMap.of(
                        "name", list.get("name"),
                        "videos", ImmutableMap.of("id", videos.stream()
                            .filter(video->video.get("listId").equals(list.get("id")))
                            .map(video -> video.get("id"))
                            .collect(Collectors.toList()))
                        )).collect(Collectors.toList());
        System.out.println(lists);
        System.out.println(videos);
        System.out.println(v);
    }
}
