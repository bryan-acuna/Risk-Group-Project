package com.teamB;

// Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0 (For details, see https://github.com/awsdocs/amazon-s3-developer-guide/blob/master/LICENSE-SAMPLECODE.)

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class UploadObject {
    UploadObject(){

    }

    public static String scan_For_Key_From_Local_File(){
        try{

            Scanner sc = new Scanner(new File("C:\\Users\\acer\\Desktop\\propertiesfolder\\propertyFolder.txt"));
            return sc.nextLine();
        }
        catch(Exception e){
            System.out.println("file not found");
        }
        return "";
    }
    public static String scan_For_Secret_Key_From_Local_File(){
        try{

            Scanner sc = new Scanner(new File("C:\\Users\\acer\\Desktop\\propertiesfolder\\propertyFolder.txt"));
            sc.nextLine();
            return sc.nextLine();

        }
        catch(Exception e){
            System.out.println("file not found");
        }
        return "";
    }


    public static void upload(String accessKey, String secretKey, String nameOfBucket, String whatToNameFile, String pathToFile){
        //(String accessKey, String secretKey, String nameOfBucket, String whatToNameFile, String )
        BasicAWSCredentials creds = new BasicAWSCredentials(accessKey, secretKey);




        //Bucket we are uploading to
        String bucketName = nameOfBucket;
        //Name of a text file we are uploading; this just has the message "file uploaded", not important for now
        String stringObjKeyName = "textFileCreatedOnSuccessUpload";


        //What we want to name the file we are uploading
        String fileObjKeyName = whatToNameFile;
        //File path to the thing we are trying to upload
        String fileName = pathToFile;


        try {
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(creds))
                    .withRegion("us-east-2")
                    .build();


            // Upload a text string as a new object.
            s3Client.putObject(bucketName, stringObjKeyName, "Uploaded String Object");

            // Upload a file as a new object with ContentType and title specified.
            PutObjectRequest request = new PutObjectRequest(bucketName, fileObjKeyName, new File(fileName));
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("plain/text");
            metadata.addUserMetadata("x-amz-meta-title", "someTitle");
            request.setMetadata(metadata);
            s3Client.putObject(request);
        }
        catch(AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            e.printStackTrace();
        }
        catch(SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            e.printStackTrace();
        }
    }




//    public static void main(String[] args) throws IOException {
//        BasicAWSCredentials creds = new BasicAWSCredentials("AKIAJWL5S5AINZ7HLBYA", "axuMqHPe+pYAOX7M+vl8ZJrrLUGrJAgTSh+/hYia");
//
//
//        //Bucket we are uploading to
//        String bucketName = "mikebitest05012018";
//        //Name of a text file we are uploading; this just has the message "file uploaded"
//        String stringObjKeyName = "textFileCreatedOnSuccessUpload";
//        //Actual file object name
//        String fileObjKeyName = "problems";
//        //File path to the thing we are trying to upload
//        String fileName = "C:\\Users\\acer\\Desktop\\problemsRanIntoOnProject.txt";
//        //String fileName = "test";
//
//        try {
//            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
//                    .withCredentials(new AWSStaticCredentialsProvider(creds))
//                    .withRegion("us-east-2")
//                    .build();
//
//
//            // Upload a text string as a new object.
//            s3Client.putObject(bucketName, stringObjKeyName, "Uploaded String Object");
//
//            // Upload a file as a new object with ContentType and title specified.
//            PutObjectRequest request = new PutObjectRequest(bucketName, fileObjKeyName, new File(fileName));
//            ObjectMetadata metadata = new ObjectMetadata();
//            metadata.setContentType("plain/text");
//            metadata.addUserMetadata("x-amz-meta-title", "someTitle");
//            request.setMetadata(metadata);
//            s3Client.putObject(request);
//        }
//        catch(AmazonServiceException e) {
//            // The call was transmitted successfully, but Amazon S3 couldn't process
//            // it, so it returned an error response.
//            e.printStackTrace();
//        }
//        catch(SdkClientException e) {
//            // Amazon S3 couldn't be contacted for a response, or the client
//            // couldn't parse the response from Amazon S3.
//            e.printStackTrace();
//        }
//    }
}

