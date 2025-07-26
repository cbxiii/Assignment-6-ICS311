import java.util.*;

public class SocialMediaData {
    public static class User {
        String username;
        List<Post> createdPosts;
        List<Post> readPosts;
        List<String> createdComments;

        public User(String username) {
            this.username = username;
            this.createdPosts = new ArrayList<>();
            this.readPosts = new ArrayList<>();
        }

        public String getUsername() {
            return username;
        }

        public void createPost(Post post) {
            createdPosts.add(post);
        }

        public List<Post> getCreatedPosts() {
            return createdPosts;
        }

        public void readPost(Post post) {
            readPosts.add(post);
        }

        public List<Post> getReadPosts() {
            return readPosts;
        }

        public List<String> getCreatedComments() {
            return createdComments;
        }


    }    

    public static class Post {
        User author;
        String content;
        List<String> postComments;
    
        public Post(User author, String content) {
            this.author = author;
            this.content = content;
            this.postComments = new ArrayList<>();
        }

        public User getAuthor() {
            return author;
        }
    
        public String getContent() {
            return content;
        }

        public List<String> getPostComments() {
            return postComments;
        }

        public void addComment(User commentAuthor, String comment) {
            postComments.add(comment);
            commentAuthor.createdComments.add(comment);
        }
    }
}