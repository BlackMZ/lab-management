package com.cloud.lab.management.util;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * @Author: John.ma
 * @Description:
 * @Date: 2020/4/22 11:32
 */
@Slf4j
public class RemoteCommandAndScpClient {


    private static String DEFAULT_CHART = "UTF-8";

    /**
     * 登录主机
     *
     * @return 登录成功返回true，否则返回false
     */
    public static Connection connect(String ip,
                                     int port,
                                     String userName,
                                     String userPwd) throws IOException {

        boolean flg;
        Connection conn = null;
        conn = new Connection(ip, port);
        //连接
        conn.connect();
        //认证
        flg = conn.authenticateWithPassword(userName, userPwd);
        if (flg) {
            log.info("=========登录成功=========" + conn);
            return conn;
        }
        return conn;
    }


    /**
     * 远程执行shll脚本或者命令
     *
     * @param cmd 即将执行的命令
     * @return 命令执行完后返回的结果值
     */
    public static String execute(Connection conn, String cmd) throws IOException {
        String result = "";
        if (conn != null) {
            //打开一个会话
            Session session = conn.openSession();
            //执行命令
            session.execCommand(cmd);
            result = processStdout(session.getStdout(), DEFAULT_CHART);
            //如果为得到标准输出为空，说明脚本执行出错了
            if (StringUtils.isBlank(result)) {
                log.info("得到标准输出为空,链接conn:" + conn + ",执行的命令：" + cmd);
                result = processStdout(session.getStderr(), DEFAULT_CHART);
            } else {
                log.info("执行命令成功,链接conn:" + conn + ",执行的命令：" + cmd);
            }
            conn.close();
            session.close();
        }
        return result;
    }


    /**
     * 解析脚本执行返回的结果集
     *
     * @param in      输入流对象
     * @param charset 编码
     * @return 以纯文本的格式返回
     */
    private static String processStdout(InputStream in, String charset) {
        InputStream stdout = new StreamGobbler(in);
        StringBuffer buffer = new StringBuffer();
        ;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout, charset));
            String line = null;
            while ((line = br.readLine()) != null) {
                buffer.append(line + "\n");
            }
        } catch (UnsupportedEncodingException e) {
            log.error("解析脚本出错：" + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            log.error("解析脚本出错：" + e.getMessage());
            e.printStackTrace();
        }
        return buffer.toString();
    }


    /**
     * 远程拷贝文件
     *
     * @param remoteFile           远程源文件路径
     * @param localTargetDirectory 本地存放文件路径
     */
    public void getFile(Connection connection, String remoteFile, String localTargetDirectory, boolean close) {
        Connection conn = connection;
        try {
            SCPClient client = new SCPClient(conn);
            client.get(remoteFile, localTargetDirectory);
            if (close) {
                conn.close();
            }
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
    public static void putFile(Connection connection, String localFile, String remoteTargetDirectory, boolean close) throws IOException {
        Connection conn = connection;
        SCPClient client = new SCPClient(conn);
        client.put(localFile, remoteTargetDirectory);
        if (close) {
            conn.close();
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
    public void putFile(Connection connection, String localFile, String remoteFileName, String remoteTargetDirectory, String mode) {
        Connection conn = connection;
        try {
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


    public static void close(Connection connection) {
        connection.close();
    }

    public static void main(String[] args) {
        String s = "/root/algorithm/GA_SV_0424/GA_C1_SV/";
        String[] split = s.split("/");
        System.out.println("/" + split[1] + "/" + split[2] + "/");
//        String ip = "172.16.10.168";
//        String user = "root";
//        String password = "123456";
//        Connection connect = connect(ip, user, password);
//        String command = "docker run -it --rm --net=host --gpus all -v /root/tensorflow/:/root/tensorflow/ cloudkeeper/tensorflow:2.0.1-gpu-py3 nohup python /root/tensorflow/test_speed/TF_Docker_RG_Forloop0323.py &";
////        String command = "ifconfig";
//        String ifconfig = execute(connect, command);
//        System.out.println(ifconfig);
    }
}
