package com.zhangxq.modules.common.tool;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhangxq
 * @version: 1.0
 * @date: 2018/2/10 14:02
 * @description: 基本工具包类
 */
public class IDGen{
    /**
     * 创建一个新的ID
     * @return
     */
    public static String uuid(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-","");
    }
}
