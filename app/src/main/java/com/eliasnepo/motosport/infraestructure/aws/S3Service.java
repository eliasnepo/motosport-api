package com.eliasnepo.motosport.infraestructure.aws;

import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.eliasnepo.motosport.application.cars.create.FileStorage;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.UUID;

@Service
public class S3Service implements FileStorage {

    private static Logger LOG = LoggerFactory.getLogger(S3Service.class);
    private AmazonS3 s3client = AmazonS3Client
            .builder()
            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:4566", "sa-east-1"))
            .withPathStyleAccessEnabled(true)
            .build();
    @Value("${s3.bucket}")
    private String bucketName;

    public URL uploadFile(MultipartFile file) {
        try {
            String extension = FilenameUtils.getExtension(file.getOriginalFilename()); // Get extension from file
            String fileName = UUID.randomUUID().toString() + "." + extension;  // Generate file name with UUID

            InputStream is = file.getInputStream(); // InputStream to read the content from the file
            String contentType = file.getContentType();
            return uploadToS3(is, fileName, contentType);
        }
        catch(IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private URL uploadToS3(InputStream is, String fileName, String contentType) {
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentType(contentType);
        LOG.info("Upload started");
        s3client.putObject(bucketName, fileName, is, meta);
        LOG.info("Upload finished");
        return s3client.getUrl(bucketName, fileName);

    }
}
