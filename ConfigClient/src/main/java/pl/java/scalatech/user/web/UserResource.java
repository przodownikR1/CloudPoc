package pl.java.scalatech.user.web;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonView;

import pl.java.scalatech.image.ImageController;
import pl.java.scalatech.user.domain.User;

public class UserResource extends ResourceSupport{
    public interface Projection{        }
    
   // @JsonView(UserResource.Projection.class)
    User user;
    
  //  @JsonFilter("userFilter")
    public User getUser() {
        return this.user;
    }

    
    @JsonCreator
    public UserResource(User user) {
        this.user = user;
        this.add(linkTo(methodOn(UserController.class).getUser(user.getId())).withSelfRel());
        this.add(linkTo(methodOn(UserController.class).getUserByName(user.getLogin(),"fff")).withRel("byName"));
        this.add(linkTo(methodOn(UserController.class).deleteUser(user.getId())).withRel("delete"));
        this.add(linkTo(methodOn(ImageController.class).getImage(user.getLogin())).withRel("image"));
     
        
        
    }

}
