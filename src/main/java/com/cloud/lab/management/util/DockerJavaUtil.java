package com.cloud.lab.management.util;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.core.DockerClientBuilder;

import java.util.List;

/**
 * @Author: John.ma
 * @Description:
 * @Date: 2020/4/13 12:02
 */
public class DockerJavaUtil {

    public static void main(String[] args) {
//        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
//                .withDockerHost("tcp://192.168.43.128:4789")
//                .withDockerTlsVerify(true)
//                .withDockerCertPath("D:/certs")
//                .withDockerConfig("C:/Users/Administrator/.dockercfg")
////                .withApiVersion("1.30") // optional
////                .withRegistryUrl("https://index.docker.io/v1/")
////                .withRegistryUsername("MZ")
////                .withRegistryPassword("ilovedocker")
////                .withRegistryEmail("3120002793@qq.com")
//                .build();
        DockerClient dockerClient
                = DockerClientBuilder.getInstance("tcp://172.16.10.163:4789").build();


        List<Container> containers = dockerClient.listContainersCmd().exec();
        containers.forEach(container -> System.out.println(container.getNames()));
        String id = "6960b27c3191";
    }

}
