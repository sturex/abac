# Simple [ABAC model] with Spring Security

[ABAC model]: <https://en.wikipedia.org/wiki/Attribute-based_access_control>


### The motivation
* The code will allow you to reduce boilerplate permissions checking inside methods of service layer in your application.
* All policy rules will now concentrate in a single config file allowing to use concise annotation-based programming model.

### Consider the following scenario
* Delete a post authored by some user by current user.
* Also we want to comply to the policy rule demanding only posts author is allowed to delete the posts.

### Show me how to use it!
The _**@PreAuthorize**_ annotation with the required parameters just put above service (or controller) method. Hereby we ask Spring Security to check the method access security.
```java
@PreAuthorize("hasPermission(#post, 'delete')")
public Post deletePost(Post post) {
    return postRepository.deletePost(post);
}
```

The action _**delete**_ applying to the instance object _**post**_ is configured in separate config class.
    
```java
AbacPermissionContainer container = new AbacPermissionContainer();
container.put("delete", Post.class, environment -> {
    Post post = environment.getTargetObject();
    User user = environment.getUser();
    return post.getAuthor().equals(user);
});
return container;
```

### What kind of API here?
Just a several GraphQL queries and mutations available on 8080 port right after running the Spring application
