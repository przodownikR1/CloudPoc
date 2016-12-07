package pl.java.scalatech.image;

import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.io.ByteStreams;

import lombok.SneakyThrows;
import pl.java.scalatech.image.config.RegisterMap;
import pl.java.scalatech.image.repo.ImageRepository;
import pl.java.scalatech.user.repo.UserRepo;
@RestController
@RequestMapping("/api/picture")
public class ImageController {
    
    @Resource
    private org.springframework.core.io.Resource picture;
    
    private final ImageRepository imageRepository;
    
    private final RegisterMap registerImages;
    
    private final UserRepo userRepo;
    
    private final Random r = new Random();
        
    public ImageController(ImageRepository imageRepository,RegisterMap registerImages, UserRepo userRepo) {
      this.imageRepository = imageRepository; 
      this.registerImages = registerImages;
      this.userRepo = userRepo;
    }
    
    @RequestMapping(value = "/image/{uuid}",headers = "Accept=image/jpeg, image/jpg, image/png, image/gif")
    @SneakyThrows(IOException.class)
    public HttpEntity<byte[]> getImage(@PathVariable String uuid) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.IMAGE_JPEG);
        byte[] userPhotoBytes = ByteStreams.toByteArray(imageRepository.load(uuid));                 
        if (userPhotoBytes == null) {
            userPhotoBytes = new byte[(int) picture.contentLength()];
            picture.getInputStream().read(userPhotoBytes);
        }
        
        return new ResponseEntity<>(userPhotoBytes, httpHeaders, HttpStatus.OK);
    }
    
    
    
    @RequestMapping("/")
    List<ImageResource> getRegister(){       
        Set<Entry<String,String>> sets = registerImages.getRegister().entrySet();
        //TODO
        return sets.stream().map(t -> new Image(t.getValue(),0l,t.getKey(),"owner",1,1)).map(ImageResource::new).peek(t -> t.addUser(userRepo.findOne((long)r.nextInt(20))))
        .collect(Collectors.toList());         
    }
    
}
