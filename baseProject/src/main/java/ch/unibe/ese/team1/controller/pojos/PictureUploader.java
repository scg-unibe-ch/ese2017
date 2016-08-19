package ch.unibe.ese.team1.controller.pojos;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.web.multipart.MultipartFile;

import ch.unibe.ese.team1.model.PictureMeta;

/**
 * This class handles uploading any number of pictures of type
 * {@link MultipartFile} to the server.
 *
 */
public class PictureUploader {

	private static final int EXTENSION_LENGTH = 4;

	private String absoluteFilePath;
	private String relativePath;
	private List<PictureMeta> uploadedPictureMetas;

	/**
	 * Creates a new PictureUploader that will upload to the directory specified
	 * by directory.
	 * 
	 * @param absolutePath
	 *            the file path of the directory that should be uploaded to
	 * @param relativePath
	 *            the file path the uploaded pictures will have in the servlet
	 *            context
	 */
	public PictureUploader(String absolutePath, String relativePath) {
		this.absoluteFilePath = absolutePath;
		this.relativePath = relativePath;
		LinkedList<PictureMeta> unsyncUploadedPictures = new LinkedList<>();
		uploadedPictureMetas = Collections
				.synchronizedList(unsyncUploadedPictures);
	}

	/**
	 * Uploads the given list of pictures to the saved directory. The pictures
	 * are named in ascending order with the filenames specified by the list of
	 * Strings returned.
	 * 
	 * @param pictures
	 *            the pictures to upload
	 * @return the filenames the pictures were uploaded as
	 */
	public List<PictureMeta> upload(List<MultipartFile> pictures) {
		File directory = new File(absoluteFilePath);

		// create the directory if it does not exist yet
		if (!directory.exists()) {
			directory.mkdirs();
		}

		PictureMeta pictureMeta;

		for (MultipartFile file : pictures) {
			if (!file.isEmpty()) {
				// create file meta data that will be passed to the client side
				// jQuery
				pictureMeta = new PictureMeta();
				pictureMeta.setName(file.getOriginalFilename());
				pictureMeta.setSize(file.getSize() / 1024 + " KB");
				pictureMeta.setType(file.getContentType());

				try {
					byte[] bytes = file.getBytes();
					String originalFileName = file.getOriginalFilename();
					String extension = originalFileName.substring(
							originalFileName.length() - EXTENSION_LENGTH)
							.toLowerCase(Locale.ROOT);
					// check filetypes
					if (!(extension.equals(".jpg") || extension.equals(".png")
							|| extension.equals(".jpeg") || extension
								.equals(".bmp"))) {
						// invalid file format, skip file
						continue;
					}
					UUID randomUUID = UUID.randomUUID();
					String fileName = randomUUID.toString();
					
					String absoluteFileName = absoluteFilePath + "/" + fileName
							+ extension;
					String relativeFileName = relativePath + "/" + fileName
							+ extension;
					pictureMeta.setUrl(relativeFileName);
					BufferedOutputStream outStream = new BufferedOutputStream(
							new FileOutputStream(new File(absoluteFileName)));
					outStream.write(bytes);
					outStream.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				synchronized (uploadedPictureMetas) {
					uploadedPictureMetas.add(pictureMeta);
				}
			}
		}
		return uploadedPictureMetas;
	}

	/**
	 * Deletes the picture with the given url.
	 * 
	 * @param url
	 *            the relative filepath of the picture (relative to the
	 *            webserver root)
	 * @param absoluteFilePath
	 *            the absolute filepath of the picture that should be deleted
	 */
	public void deletePicture(String url, String absoluteFilePath) {
		Path filePath = Paths.get(absoluteFilePath);
		try {
			Files.delete(filePath);
		} catch (IOException e) {
			// not that tragic, since we want to delete the file anyway
			e.printStackTrace();
		}
		// remove file from uploaded file list
		synchronized (uploadedPictureMetas) {
			uploadedPictureMetas.removeIf(picture -> picture.getUrl().equals(
					url));
		}
	}

	/** Returns the relative file paths of the pictures that were uploaded. */
	public List<String> getFileNames() {
		return uploadedPictureMetas.stream()
				.map(pictureMeta -> pictureMeta.getUrl())
				.collect(Collectors.toList());
	}

	/**
	 * Returns a list of metadata about the pictures that were already uploaded
	 * with this picture uploader.
	 */
	public List<PictureMeta> getUploadedPictureMetas() {
		return uploadedPictureMetas;
	}

}
