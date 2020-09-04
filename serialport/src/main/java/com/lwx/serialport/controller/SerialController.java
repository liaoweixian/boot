package com.lwx.serialport.controller;

import gnu.io.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/serial")
public class SerialController {

    private static SerialPort SERIAL = null;

    /**
     * 打开串口连接
     * @return
     * @throws NoSuchPortException
     */
    @GetMapping("/connection")
    @ResponseBody
    public Boolean connection() throws NoSuchPortException {
        String strSerial = "COM3";
        try {
            // 获取到指定端口
            CommPortIdentifier targetSerial = CommPortIdentifier.getPortIdentifier(strSerial);
            // 打开端口
            CommPort open = targetSerial.open(strSerial, 2000);
            if (!(open instanceof SerialPort)) {
                System.out.println("这个不是串口");
                return false;
            }
            SerialPort serial = (SerialPort) open;
            // 设置一下串口的波特率等参数
            // 波特率 数据位  停止位 流量（奇偶校验）
            serial.setSerialPortParams(2000000, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.FLOWCONTROL_NONE);

            SERIAL = serial;
            return false;
        } catch (PortInUseException e) {
            e.printStackTrace();
            return false;
        } catch (UnsupportedCommOperationException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 断开串口
     * @return
     */
    @GetMapping("/disconnect")
    @ResponseBody
    public Boolean disconnect() {
        if (SERIAL != null) {
            // 断开窗口
            SERIAL.close();
            SERIAL = null;
        }
        return true;
    }

    

}
