import java.util.*;

public class GraphDataBuilder {

    public static Map<String, List<String>> buildAdjacencyList(List<SocialMediaData.User> users) {
        Map<String, List<String>> adjacencyList = new HashMap<>();

        for (SocialMediaData.User user : users) {
            String username = user.getUsername();
            adjacencyList.putIfAbsent(username, new ArrayList<>());

            for (SocialMediaData.Post post : user.getCreatedPosts()) {
                String postId = getPostId(post);

                // Ensure post exists in the map
                adjacencyList.putIfAbsent(postId, new ArrayList<>());

                // Author link (bidirectional)
                adjacencyList.get(username).add(postId);
                adjacencyList.get(postId).add(username);
            }

            for (SocialMediaData.Post post : user.getReadPosts()) {
                String postId = getPostId(post);

                adjacencyList.putIfAbsent(postId, new ArrayList<>());

                // View link (bidirectional)
                if (!adjacencyList.get(username).contains(postId))
                    adjacencyList.get(username).add(postId);
                if (!adjacencyList.get(postId).contains(username))
                    adjacencyList.get(postId).add(username);
            }
        }

        return adjacencyList;
    }

    // Helper: Generate a unique ID for a post
    private static String getPostId(SocialMediaData.Post post) {
        return "Post@" + System.identityHashCode(post);
    }
}
