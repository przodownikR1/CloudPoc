package pl.java.scalatech.user.web;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.codahale.metrics.annotation.Counted;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Metric;
import com.codahale.metrics.annotation.Timed;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.user.domain.User;
import pl.java.scalatech.user.repo.UserRepo;
import pl.java.scalatech.web.GenericController;

@RestController
@Slf4j
@RequestMapping("/api/user")
public class UserController extends GenericController<User> {

    private final UserRepo userRepo;

    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private UserResourceAssembler userResourceAssembler;

    public UserController(JpaRepository<User, Long> repo) {
        super(repo);
        this.userRepo = (UserRepo) repo;

    }

    @GetMapping("/id/{id}")
    @Timed(name = "getUserId(8)")
    @Counted(name = "userIdCounted")
    @Metric(name = "userIdMetric")
    @HystrixCommand(fallbackMethod = "userIdHandler")
    @ExceptionMetered(name = "get-userId(8)-exception-meter")
    UserResource getById(@PathVariable Long id) {
        Optional<User> user = Optional.of(userRepo.findOne(id));
        return user.map(UserResource::new).orElseThrow(() -> new IllegalArgumentException("resource not found"));
    }

    UserResource userIdHandler(Long id) {
        return new UserResource(new User());
    }

    @GetMapping(value = "/name/{name}", produces = APPLICATION_JSON_VALUE)
    @ExceptionMetered(name = "get-username-exception-meter")
    @Timed(name = "getUserName")
    @Counted(name = "userNameCounted")
    @Metric(name = "userNameMetric")
    public UserResource getUserByName(@PathVariable("name") String name, @RequestHeader(value = "X-SelfToken", required = false) String token) {
        log.info("+++ token : {}", token);
        return userRepo.findByLogin(name).map(UserResource::new).orElseThrow(() -> new IllegalArgumentException("resource not found"));
    }

    @Timed(name = "getUserId")
    @ExceptionMetered(name = "get-userId-exception-meter")
    @RequestMapping(value = "/{userId}",method=RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public UserResource getUser(@PathVariable("userId") Long userId) {
        
        
       // ResponseEntity<String> responseEntity = this.restTemplate.getForEntity("http://search-app/role", String.class);
        //log.info("+++++++++++++++++++++++++++++ userID role from simple-app : {} ",responseEntity.getBody());
        return Optional.ofNullable(userRepo.findOne(userId)).map(t -> new UserResource(t))
                .orElseThrow(() -> new IllegalArgumentException("resource not found"));

    }

    @Timed(name = "userTimed")
    @Counted(name = "userCounted")
    @Metric(name = "userMetric")
    @ExceptionMetered(name = "exceptionUserMetered")
    // @JsonView(UserResource.Projection.class)
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<UserResource> getUsers() {
        List<UserResource> result = userRepo.findAll().stream().map(t -> new UserResource(t)).collect(toList());
        return result;
    }

    @GetMapping(value = "/pagingUser", produces = "application/json")
    @Timed(name = "getUserPage")
    @Counted(name = "userPageCounted")
    @Metric(name = "userPageMetric")
    public HttpEntity<PagedResources<User>> getRestaurants(Pageable pageable, PagedResourcesAssembler pagedResourcesAssembler) {
        Page<User> userPage = userRepo.findAll(pageable);
        return new ResponseEntity<>(pagedResourcesAssembler.toResource(userPage, userResourceAssembler), HttpStatus.OK);
    }

    @DeleteMapping("/id/{id}")
    public HttpHeaders deleteUser(Long id) {
        return super.deleteResource(id);
    }

    @Override
    public Class<?> getDomainClass() {
        return User.class;
    }
}
