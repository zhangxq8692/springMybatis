package com.zhangxq.modules.common.tool;


import java.io.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhangxq
 * @version: 1.0
 * @date: 2018/3/16 15:43
 * @description:
 */
public class HashSerializable {
    public byte[] objectToBytes(Object o){
        ByteArrayOutputStream byteOs = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        byte[] bytes =null;
        try {
            oos = new ObjectOutputStream(byteOs);
            oos.writeObject(o);
            bytes = byteOs.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                byteOs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bytes;
    }
    public Object byteToObject(byte[] bytes){
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = null;
        Object o = null;
        try {
            ois = new ObjectInputStream(bis);
            o = ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return o;
    }
}
