package com.cloud.lab.management.util;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
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
public class RemoteCommandClient {


    private static String DEFAULTCHART = "UTF-8";



    /**
     * 登录主机
     *
     * @return 登录成功返回true，否则返回false
     */
    public static Connection connect(String ip,
                                   String userName,
                                   String userPwd) {

        boolean flg = false;
        Connection conn = null;
        try {
            conn = new Connection(ip);
            //连接
            conn.connect();
            //认证
            flg = conn.authenticateWithPassword(userName, userPwd);
            if (flg) {
                log.info("=========登录成功=========" + conn);
                return conn;
            }
        } catch (IOException e) {
            log.error("=========登录失败=========" + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }


    /**
     * 远程执行shll脚本或者命令
     *
     * @param cmd 即将执行的命令
     * @return 命令执行完后返回的结果值
     */
    public static String execute(Connection conn, String cmd) {
        String result = "";
        try {
            if (conn != null) {
                //打开一个会话
                Session session = conn.openSession();
                //执行命令
                session.execCommand(cmd);
                result = processStdout(session.getStdout(), DEFAULTCHART);
                //如果为得到标准输出为空，说明脚本执行出错了
                if (StringUtils.isBlank(result)) {
                    log.info("得到标准输出为空,链接conn:" + conn + ",执行的命令：" + cmd);
                    result = processStdout(session.getStderr(), DEFAULTCHART);
                } else {
                    log.info("执行命令成功,链接conn:" + conn + ",执行的命令：" + cmd);
                }
                conn.close();
                session.close();
            }
        } catch (IOException e) {
            log.info("执行命令失败,链接conn:" + conn + ",执行的命令：" + cmd + "  " + e.getMessage());
            e.printStackTrace();
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

    public static void main(String[] args) {
        String ip = "172.16.10.168";
        String user = "root";
        String password = "123456";
        Connection connect = connect(ip, user, password);
        String command = "docker run -it --rm --net=host --gpus all -v /root/tensorflow/:/root/tensorflow/ cloudkeeper/tensorflow:2.0.1-gpu-py3 nohup python /root/tensorflow/test_speed/TF_Docker_RG_Forloop0323.py &";
//        String command = "ifconfig";
        String ifconfig = execute(connect, command);
        System.out.println(ifconfig);
    }
}
