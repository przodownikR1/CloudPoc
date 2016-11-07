package pl.java.scalatech.image;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;

import pl.java.scalatech.user.domain.User;
import pl.java.scalatech.user.web.UserController;

public class ImageResource extends ResourceSupport{
    public interface Projection{        }
    
   //  @JsonView(ImageResource.Projection.class)
     Image image;
    
    //@JsonFilter("imageFilter")
    public Image getImage() {
        return this.image;
    }
  
    @JsonCreator
    public ImageResource(Image image) {
        this.image = image;        
        this.add(linkTo(methodOn(ImageController.class).getImage(image.getUUID())).withRel("image"));
        this.add(linkTo(methodOn(ImageController.class).getRegister()).withSelfRel());                
    }
    
    ImageResource addUser(User user){
        this.add(linkTo(methodOn(UserController.class).getUser(user.getId())).withRel("owner"));
        return this;
    }
}
