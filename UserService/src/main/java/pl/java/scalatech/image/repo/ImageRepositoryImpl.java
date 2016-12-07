package pl.java.scalatech.image.repo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;

@Repository
public class ImageRepositoryImpl implements ImageRepository {

	@Autowired
	GridFsOperations op;

	@Override
	public String save(File file) {
		String name = UUID.randomUUID().toString();
		try {
			op.store(new FileInputStream(file), name, MediaType.IMAGE_JPEG);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return name;
	}

	@Override
	public InputStream load(String uuid) {
		try {
			return op.getResource(uuid).getInputStream();
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
