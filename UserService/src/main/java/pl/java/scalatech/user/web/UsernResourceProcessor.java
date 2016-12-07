package pl.java.scalatech.user.web;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

import pl.java.scalatech.image.ImageController;
import pl.java.scalatech.user.domain.User;

@Component
public class UsernResourceProcessor implements ResourceProcessor<Resource<User>> {
    @Override
    public Resource<User> process(Resource<User> userResource) {
        User user = userResource.getContent();
        //userResource.add(linkTo(methodOn(ImageController.class).getPhoto(user.getId())).withRel("photo"));
        return userResource;
    }
}
