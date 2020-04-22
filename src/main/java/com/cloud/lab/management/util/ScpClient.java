package com.cloud.lab.management.util;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.Session;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.net.URI;

/**
 * @Author: John.ma
 * @Description:
 * @Date: 2020/3/25 11:43
 */
@Slf4j
public class ScpClient {
    private String ip;
    private int port;
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    static private ScpClient instance;

    static synchronized public ScpClient getInstance(String IP, int port,
                                                     String username, String passward) {
        if (instance == null) {
            instance = new ScpClient(IP, port, username, passward);
        }
        return instance;
    }

    public ScpClient(String IP, int port, String username, String passward) {
        this.ip = IP;
        this.port = port;
        this.username = username;
        this.password = passward;
    }

    /**
     * 远程拷贝文件
     *
     * @param remoteFile           远程源文件路径
     * @param localTargetDirectory 本地存放文件路径
     */
    public void getFile(String remoteFile, String localTargetDirectory) {
        Connection conn = new Connection(ip, port);
        try {
            conn.connect();
            boolean isAuthenticated = conn.authenticateWithPassword(username,
                    password);
            if (!isAuthenticated) {
                log.info("authentication failed");
            }
            SCPClient client = new SCPClient(conn);
            client.get(remoteFile, localTargetDirectory);
            conn.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }


    /**
     * 远程上传文件
     *
     * @param localFile             本地文件路径
     * @param remoteTargetDirectory 远程存放文件路径
     */
    public void putFile(String localFile, String remoteTargetDirectory) {
        Connection conn = new Connection(ip, port);
        try {
            conn.connect();
            boolean isAuthenticated = conn.authenticateWithPassword(username,
                    password);
            if (!isAuthenticated) {
                log.error("authentication failed");
            }
            SCPClient client = new SCPClient(conn);
            client.put(localFile, remoteTargetDirectory);
            conn.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 远程上传文件并对上传文件重命名
     *
     * @param localFile             本地文件路径
     * @param remoteFileName        远程文件名
     * @param remoteTargetDirectory 远程存放文件路径
     * @param mode                  默认"0600"，length=4
     */
    public void putFile(String localFile, String remoteFileName, String remoteTargetDirectory, String mode) {
        Connection conn = new Connection(ip, port);
        try {
            conn.connect();
            boolean isAuthenticated = conn.authenticateWithPassword(username,
                    password);
            if (!isAuthenticated) {
                log.error("authentication failed");
            }
            SCPClient client = new SCPClient(conn);
            if ((mode == null) || (mode.length() == 0)) {
                mode = "0600";
            }
            client.put(localFile, remoteFileName, remoteTargetDirectory, mode);
            //重命名
            Session sess = conn.openSession();
            String tmpPathName = remoteTargetDirectory + File.separator + remoteFileName;
            String newPathName = tmpPathName.substring(0, tmpPathName.lastIndexOf("."));
            sess.execCommand("mv " + remoteFileName + " " + newPathName);
            conn.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public static void main(String[] args) {
        String ip = "192.168.43.128";
        Integer port = 22;
        String username = "root";
        String password = "root";
        String localPath = "D:\\zout\\kfmoih.DESC";
        String remoteName = "kfmoih.DESC";
        String remotePath = "/usr/tmp/";
        for (int i = 0; i < 2; i++) {
            ScpClient scp = ScpClient.getInstance(ip, port, username, password);
            scp.putFile(localPath, remotePath);
        }

        //        scp.putFile(localPath, remoteName, remotePath, null);

//        scp.getFile(remoteFile, localTargetDirectory);

        String url = "http://192.168.43.128:22/aa/b";
        URI uri = URI.create(url);
        System.out.println(uri.getHost());
        System.out.println(uri.getPort());
        System.out.println(uri.getPath());
    }

}
