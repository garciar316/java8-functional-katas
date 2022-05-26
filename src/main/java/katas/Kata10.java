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
        return lists.stream().map(list -> ImmutableMap.of(
                "name", list.get("name"),
                "videos", videos.stream()
                        .filter(video -> video.get("listId").equals(list.get("id")))
                        .map(video -> ImmutableMap.of(
                                "id", video.get("id"),
                                "title", video.get("title")
                        ))
                        .collect(Collectors.toList())
        )).collect(Collectors.toList());
    }
}
