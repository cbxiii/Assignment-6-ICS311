package src;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GraphDataBuilder {

    public static void exportToDot(List<SocialMediaData.User> users, Map<String, SocialMediaData.Post> posts, String filename, String importanceCriteria) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("digraph SocialGraph {\n");

            // Write user nodes with fixed color
            for (SocialMediaData.User user : users) {
                String color = "#9b59b6"; // Purple for users
                writer.write(String.format("  \"%s\" [shape=circle, style=filled, fillcolor=\"%s\"];\n", user.getUsername(), color));
            }

            int i = 0;
            for (Map.Entry<String, SocialMediaData.Post> entry : posts.entrySet()) {
                SocialMediaData.Post post = entry.getValue();
                String postId = "post_" + i++;

                // Calculate importance and determine color
                double importance = post.getImportanceScore(importanceCriteria);
                String postColor = getColorByImportance(importance);

                writer.write(String.format("  \"%s\" [label=\"Post\", shape=box, style=filled, fillcolor=\"%s\"];\n", postId, postColor));
                writer.write(String.format("  \"%s\" -> \"%s\";\n", post.getAuthor().getUsername(), postId));

                for (SocialMediaData.User viewer : post.getPostViewers()) {
                    writer.write(String.format("  \"%s\" -> \"%s\" [style=dashed];\n", viewer.getUsername(), postId));
                }
            }

            writer.write("}\n");
            System.out.println("Graph with highlights exported to " + filename);
        } catch (IOException e) {
            System.err.println("Error writing .dot file: " + e.getMessage());
        }
    }

    private static String getColorByImportance(double score) {
        if (score >= 7) return "#e74c3c"; // red
        else if (score >= 4) return "#f39c12"; // orange
        else return "#3498db"; // blue
    }

}

