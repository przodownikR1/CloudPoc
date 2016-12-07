package pl.java.scalatech.user.web;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import pl.java.scalatech.user.domain.User;

@Component
public class UserResourceAssembler extends ResourceAssemblerSupport<User, Resource> {


    public UserResourceAssembler() {
        super(UserController.class, Resource.class);
    }

    @Override
    public Resource<User> toResource(User user) {
        return new Resource<User>(user, ControllerLinkBuilder.linkTo(UserController.class).
                slash(user.getId()).withSelfRel());
    }



}