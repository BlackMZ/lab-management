package com.cloud.lab.management.util;

import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.Container;
import com.spotify.docker.client.messages.ContainerInfo;
import com.spotify.docker.client.messages.ContainerState;

import java.net.URI;
import java.util.List;

/**
 * @Author: John.ma
 * @Description:
 * @Date: 2020/3/24 13:56
 */
public class DockerUtil {

    public static final String URL = "http://192.168.43.128:4789";

    /**
     * 获取
     * @return
     */
    public static DockerClient getDockerClient() {
        return DefaultDockerClient.builder()
                .uri(URI.create(URL))
                .connectTimeoutMillis(1000 * 10)
                .build();
    }

    public static void main(String[] args) throws Exception {
        DockerClient dockerClient = getDockerClient();
        ContainerInfo info = dockerClient.inspectContainer("f795fa55709e");
        ContainerState state = info.state();
        if (!state.running()) {
            dockerClient.startContainer("f795fa55709e");
        }
//        dockerClient.stopContainer("f795fa55709e", 10);
        ContainerInfo containerInfo = dockerClient.inspectContainer("f795fa55709e");
        System.out.println(containerInfo.hostnamePath());
        System.out.println(containerInfo.name());
        List<Container> containers = dockerClient.listContainers(DockerClient.ListContainersParam.allContainers());
        containers.forEach(con -> {
            try {
                dockerClient.startContainer(con.names().get(0));
            } catch (DockerException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        dockerClient.close();

    }

}
