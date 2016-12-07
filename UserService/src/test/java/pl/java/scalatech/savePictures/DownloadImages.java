package pl.java.scalatech.savePictures;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import org.junit.Test;

import javaslang.control.Try;

public class DownloadImages {
    @Test
    public void shouldSaveFileFromURL() throws IOException{
    URL website = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/Flag_of_Poland.svg/125px-Flag_of_Poland.svg.png");
    ReadableByteChannel rbc = Channels.newChannel(website.openStream());
    FileOutputStream fos = new FileOutputStream("pic.jpg");
    fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    }
    
    public  Try<Integer> divide2(Integer dividend, Integer divisor) {
        return Try.of(() -> dividend / divisor);
}
}
