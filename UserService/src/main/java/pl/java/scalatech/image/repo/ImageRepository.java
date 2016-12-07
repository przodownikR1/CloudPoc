package pl.java.scalatech.image.repo;

import java.io.File;
import java.io.InputStream;

public interface ImageRepository {

	String save(File file);

	InputStream load(String uuid);
}
