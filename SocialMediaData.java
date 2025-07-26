import java.util.*;

public class SocialMediaData {
    public static class User {
        String username;
        Map<String, String> attributes;
        List<Post> createdPosts;
        List<Post> readPosts;
        List<String> createdComments;

        public User(String username) {
            this.username = username;
            this.attributes = new HashMap<>();
            this.createdPosts = new ArrayList<>();
            this.readPosts = new ArrayList<>();
            this.createdComments = new ArrayList<>();
        }

        public String getUsername() {
            return username;
        }

        public void addAttribute(String key, String value) {
            attributes.put(key, value);
        }

        public String getAttribute(String key) {
            return attributes.get(key);
        }

        public void createPost(Post post) {
            createdPosts.add(post);
        }

        public List<Post> getCreatedPosts() {
            return createdPosts;
        }

        public void readPost(Post post) {
            if (!readPosts.contains(post)) {
                readPosts.add(post);
            }
            if (!post.getPostViewers().contains(this)) {
                post.getPostViewers().add(this);
            }
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
        List<User> postViewers;
    
        public Post(User author, String content) {
            this.author = author;
            this.content = content;
            this.postComments = new ArrayList<>();
            this.postViewers = new ArrayList<>();
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

        public List<User> getPostViewers() {
            return postViewers;
        }
    }
}