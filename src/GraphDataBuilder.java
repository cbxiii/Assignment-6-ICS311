package src;
import java.util.*;

import src.SocialMediaData.Post;
import src.SocialMediaData.User;

public class GraphDataBuilder {
    public static Map<String, Object> buildGraphData(List<User> users, String importanceCriteria) {
        List<Map<String, Object>> nodes = new ArrayList<>();
        List<Map<String, Object>> links = new ArrayList<>();

        Set<Post> allPosts = new HashSet<>();
        for (User user : users) {
            Map<String, Object> userNode = Map.of(
                "id", user.getUsername(),
                "type", "user"
            );
            nodes.add(userNode);

            for (Post post : user.getCreatedPosts()) {
                allPosts.add(post);
                links.add(Map.of("source", user.getUsername(), "target", System.identityHashCode(post) + "", "type", "author"));
            }

            for (Post post : user.getReadPosts()) {
                allPosts.add(post);
                links.add(Map.of("source", user.getUsername(), "target", System.identityHashCode(post) + "", "type", "viewer"));
            }
        }

        for (Post post : allPosts) {
            double score = post.getImportanceScore(importanceCriteria);
            Map<String, Object> postNode = Map.of(
                "id", System.identityHashCode(post) + "",
                "type", "post",
                "importance", score,
                "views", post.getNumPostViewers(),
                "comments", post.getNumPostComments()
            );
            nodes.add(postNode);
        }

        return Map.of("nodes", nodes, "links", links);
    }
}
