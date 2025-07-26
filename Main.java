import java.util.*;

public class Main {
    public static void main(String[] args) {
        SocialMediaData.User alice = new SocialMediaData.User("Alice");
        SocialMediaData.User bob = new SocialMediaData.User("Bob");
        SocialMediaData.User carol = new SocialMediaData.User("Carol");

        SocialMediaData.Post post1 = new SocialMediaData.Post(alice, "Post A");
        SocialMediaData.Post post2 = new SocialMediaData.Post(alice, "Post B");

        alice.createPost(post1);
        alice.createPost(post2);

        bob.readPost(post1);
        carol.readPost(post1);
        post1.addComment(bob, "Great post!");
        post1.addComment(carol, "I agree!");

        bob.readPost(post2);

        List<SocialMediaData.User> users = List.of(alice, bob, carol);
        Map<String, List<String>> adjList = GraphDataBuilder.buildAdjacencyList(users);

    }
}
