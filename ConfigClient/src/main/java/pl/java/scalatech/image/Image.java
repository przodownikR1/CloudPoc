package pl.java.scalatech.image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Image {

    private String name;
    private long size;
    private String UUID;
    private String owner;
    private int width;
    private int height;
    
}
