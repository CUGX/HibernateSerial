# HibernateSerial

## 一、系统概述

​		本系统使用Zigbee协议栈对各传感器组网，传感器将数据定时传送给协调器，协调器通过串口将数据发送给Java编写上位机程序，上位机根据规则解析数据，并将数据保存到MySQL数据库，实现对室内的环境参数，如温湿度、光照强度、烟雾等的监测，并根据数值，控制室内空调（电机模拟）、灯光、蜂鸣器的工作，构建了智能家居系统的雏形。



## 二、系统使用说明

### 2.1、依赖包说明

> 要能够构建过程需要以下依赖包：

1. 支持Java串口通信操作的jar包，RXTXcomm.jar ；

   另外还需做如下配置：

   - 拷贝 rxtxSerial.dll 到 <JAVA_HOME>\jre\bin目录中； 
   - 拷贝 rxtxParallel.dll 到 <JAVA_HOME>\jre\bin目录中； 

2. JFreeChart图表绘制类库；

3. Hibernate的支持jar包。



### 2.2、传感器说明

​		不同传感器的报文格式、解析方法、计算方法均有所差异，需要根据自己所拥有的传感器具体分析，修改代码，从而适应自己的设别。



## 三、系统功能说明

### 3.1、串口通信功能

​		实现Java上位机程序与串口间的通信。

### 3.2、登录、注册功能

​		通过登录、注册功能实现基本的安全控制、权限管理。

### 3.3、温湿度、光强、烟雾、室内人员监控

​		温湿度传感器、光敏传感器、烟雾传感器、多普勒传感器通过Zigbee协议实现组网。各传感器定时将数据传递给协调器，协调器通过串口将数据发送给上位机，上位机解析并展示数据，并根据数据发送控制指令。

### 3.4、电机控制（模拟空调）功能

​		根据设定的温度值，启动“空调”，实现室内温度保持在一个舒适的范围。

### 3.5、灯光亮度调节功能

​		根据光强自动调节，保持室内亮度。

### 3.6、自动检测功能

​		实现全自动操作，而不需要通过上位机程序中的按钮实现功能。





