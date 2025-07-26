import java.util.*;

import src.GraphDataBuilder;
import src.SocialMediaData;
import src.SocialMediaData.Post;
import src.SocialMediaData.User;

public class Main {
    public static void main(String[] args) {
        SocialMediaData.User userAlice = new SocialMediaData.User("Alice");
        SocialMediaData.User userBob = new SocialMediaData.User("Bob");
        SocialMediaData.User userCharlie = new SocialMediaData.User("Charlie");
        SocialMediaData.User userDiana = new SocialMediaData.User("Diana");

        // Add some attributes to users (new feature from provided code)
        userAlice.addAttribute("country", "USA");
        userBob.addAttribute("country", "Canada");
        userCharlie.addAttribute("interest", "Tech");

        List<SocialMediaData.User> users = new ArrayList<>();
        users.add(userAlice);
        users.add(userBob);
        users.add(userCharlie);
        users.add(userDiana);

        // Alice creates a post
        SocialMediaData.Post post1 = new SocialMediaData.Post(userAlice, "Just had a great coffee!");
        userAlice.createPost(post1);

        // Bob creates a post
        SocialMediaData.Post post2 = new SocialMediaData.Post(userBob, "Working on a new Java project.");
        userBob.createPost(post2);

        // Charlie creates a post
        SocialMediaData.Post post3 = new SocialMediaData.Post(userCharlie, "Excited about the upcoming hackathon!");
        userCharlie.createPost(post3);

        // Diana creates a post
        SocialMediaData.Post post4 = new SocialMediaData.Post(userDiana, "Beautiful day for a walk.");
        userDiana.createPost(post4);

        // Alice views posts and comments
        userAlice.readPost(post2);
        post2.addComment(userAlice, "Sounds interesting, Bob!");
        userAlice.readPost(post3);
        userAlice.readPost(post4);

        // Bob views posts and comments
        userBob.readPost(post1);
        post1.addComment(userBob, "Where did you get it, Alice?");
        userBob.readPost(post3);

        // Charlie views posts and comments
        userCharlie.readPost(post1);
        post1.addComment(userCharlie, "I love coffee too!");
        userCharlie.readPost(post2);
        post2.addComment(userCharlie, "Good luck with it!");
        userCharlie.readPost(post4);
        post4.addComment(userCharlie, "Enjoy!");

        // Diana views posts
        userDiana.readPost(post1);
        userDiana.readPost(post2);

        Map<String, SocialMediaData.Post> posts = new LinkedHashMap<>(); // Using LinkedHashMap for consistent order
        posts.put("post_" + post1.hashCode(), post1); // Using hashCode as a simple unique ID for demonstration
        posts.put("post_" + post2.hashCode(), post2);
        posts.put("post_" + post3.hashCode(), post3);
        posts.put("post_" + post4.hashCode(), post4);

        // The new SocialMediaData.Post.getImportanceScore supports "views", "comments", and "blend"
        String importanceCriteria = "blend"; // Let's use the new "blend" criteria
        String outputFilename = "social_graph_updated.dot";

        System.out.println("Generating graph data with updated SocialMediaData...");
        GraphDataBuilder.exportToDot(users, posts, outputFilename, importanceCriteria);
        System.out.println("Graph data generation complete. Check " + outputFilename + " for the output.");
    }
}
