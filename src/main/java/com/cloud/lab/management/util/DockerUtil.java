package com.cloud.lab.management.util;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.messages.ContainerConfig;
import com.spotify.docker.client.messages.ContainerCreation;
import com.spotify.docker.client.messages.HostConfig;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: John.ma
 * @Description: docker 配置 daemon.json 加入hosts ,  /lib/systemd/system/docker.service , 设置 /usr/bin/dockerd
 * @Date: 2020/3/24 13:56
 */
public class DockerUtil {

    public static final String URL = "http://192.168.43.128:4789";
//    public static final String URL = "http://172.16.10.163:4789";

    /**
     * 获取
     *
     * @return
     */
    public static DockerClient getDockerClient(String url){
        return DefaultDockerClient.builder()
                .uri(URI.create(url))
//                .dockerCertificates(new DockerCertificates(Paths.get("D:/certs")))
                .connectTimeoutMillis(1000 * 10)
                .build();
    }

    public static void main(String[] args) throws Exception {
//        DockerClient dockerClient = getDockerClient(URL);
//        List<Container> containers = dockerClient.listContainers(DockerClient.ListContainersParam.allContainers());
//        containers.forEach(container -> System.out.println(container.id()));
//        dockerClient.close();



        //docker run -t -i ubuntu:14.04 /bin/bash
        //docker run -it -m 16G --rm --net=host --gpus all -v /root/tensorflow/:/root/tensorflow/
        String[] command = {"/bin/bash"};
        String v1 = "/root/tensorflow1/";
        String v2 = "/root/tensorflow2/";
        String v3 = "-it";
        List<String> commands = Lists.newArrayList(Arrays.asList(command));

        DockerClient dockerClient = getDockerClient(URL);

        final HostConfig hostConfig =
                HostConfig.builder().storageOpt(ImmutableMap.of("",""))
                        .appendBinds("/root/tensorflow1:/root/tensorflow")
                        .build();
        ContainerConfig config = ContainerConfig.builder()
//                .volumes(v1)
                .image("tomcat").tty(true)
                .hostConfig(hostConfig)
                .cmd(commands)
                .build();
        ContainerCreation container = dockerClient.createContainer(config, "1111");

        String id = container.id();
        dockerClient.startContainer(id);
        System.out.println(id);

//        ContainerInfo info = dockerClient.inspectContainer("f795fa55709e");
//        ContainerState state = info.state();
//        if (!state.running()) {
//            dockerClient.startContainer("f795fa55709e");
//        }
//        dockerClient.stopContainer("f795fa55709e", 10);
//        ContainerInfo containerInfo = dockerClient.inspectContainer("f795fa55709e");
//        System.out.println(containerInfo.hostnamePath());
//        System.out.println(containerInfo.name());
//        List<Container> containers = dockerClient.listContainers(DockerClient.ListContainersParam.allContainers());
//        containers.forEach(con -> {
//            try {
//                dockerClient.startContainer(con.names().get(0));
//            } catch (DockerException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        dockerClient.close();
    }

}
