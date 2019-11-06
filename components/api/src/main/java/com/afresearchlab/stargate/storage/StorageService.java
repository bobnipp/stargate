package com.afresearchlab.stargate.storage;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Service
public class StorageService {

    private AmazonS3 s3client;

    private String bucketName;

    public StorageService(
        @Value("${s3.endpointUrl}") String endpointUrl,
        @Value("${s3.bucketName}") String bucketName,
        @Value("${s3.accessKey}") String accessKey,
        @Value("${s3.secretKey}") String secretKey,
        @Value("${s3.region}") String region) {

        this.bucketName = bucketName;

        AmazonS3ClientBuilder builder = AmazonS3ClientBuilder.standard();
        if (endpointUrl != null && endpointUrl.length() > 0) { // localstack
            builder.setEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpointUrl, region));
        } else { // external
            builder.setCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)));
            builder.setRegion(region);
        }
        builder.enablePathStyleAccess();

        this.s3client = builder.build();
    }

    public void setS3client(AmazonS3 s3client) {
        this.s3client = s3client;
    }

    public void upload(File file, String recordId) {
        if (this.s3client == null) {
            file.delete();
            throw new RuntimeException("Storage for media does not exist");
        }

        try {
            if (!this.s3client.doesBucketExistV2(this.bucketName)) {
                this.s3client.createBucket(this.bucketName);
            }

            this.s3client.putObject(new PutObjectRequest(this.bucketName, String.format("%s:%s", recordId, file.getName()), file)
                .withCannedAcl(CannedAccessControlList.PublicRead));

            file.delete();
        } catch (Exception ex) {
            file.delete();
            throw ex;
        }
    }

    public byte[] get(String recordId, String fileName) {
        byte[] bytes = null;

        try (InputStream in = s3client.getObject(new GetObjectRequest(this.bucketName, String.format("%s:%s", recordId, fileName))).getObjectContent()) {
            bytes = IOUtils.toByteArray(in);
        } catch (IOException ex) {
            System.out.println(String.format("Error handling S3 object for %s:%s", recordId, fileName));
        }

        return bytes;
    }

    public void delete(String recordId, String fileName) {
        s3client.deleteObject(new DeleteObjectRequest(this.bucketName, String.format("%s:%s", recordId, fileName)));
    }
}