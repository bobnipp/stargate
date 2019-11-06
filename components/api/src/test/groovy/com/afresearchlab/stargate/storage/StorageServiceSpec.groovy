package com.afresearchlab.stargate.storage

import com.amazonaws.services.s3.AmazonS3
import spock.lang.Specification

class StorageServiceSpec extends Specification {

    def service
    def mockAmazonS3

    def setup() {
        service = new StorageService('http://test:4444', 'stargate', 'notused', 'notused', 'us-east2')
        mockAmazonS3 = Mock(AmazonS3)
        service.setS3client(mockAmazonS3)
    }

    def 'upload a file'() {
        given:
        def file = new File("filename.txt")

        when:
        service.upload(file, '111')

        then:
        1 * mockAmazonS3.doesBucketExistV2('stargate') >> true
        1 * mockAmazonS3.putObject(_)
        !file.exists()
    }

    def 'delete a file'() {
        when:
        service.delete('filename.txt', '111')

        then:
        1 * mockAmazonS3.deleteObject(_)
    }
}
