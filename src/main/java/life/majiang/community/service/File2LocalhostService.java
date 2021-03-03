package life.majiang.community.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

/**
 * @author: liu bin bin
 * @Date: 2021/2/25 11:21
 * @Description:
 */
@Slf4j
@Service
public class File2LocalhostService {

    public String uploadFile(InputStream inputStream, String filename) {
        String generateFileName;
        OutputStream out;
        int len;
        String sysSeparator = File.separator;

        String[] filePaths = filename.split("\\.");
        if (filePaths.length > 1){
            generateFileName = UUID.randomUUID().toString() + "." + filePaths[filePaths.length-1];
        }else {
            log.error("Generate FileName Fail>>>>filename:{}",filename);
            return null;
        }

        String osName = System.getProperty("os.name");
        String realPath;
        if (osName.toLowerCase().contains("win")){
//            realPath = "D:/community/upload/";
            realPath = "D:"+sysSeparator+"community"+sysSeparator+"upload"+sysSeparator;
        }else {
//            realPath = "/resource/community/upload";
            realPath = sysSeparator+"resource"+sysSeparator+"community"+sysSeparator+"upload";
        }
        File myFile = new File(realPath);
        if (!myFile.exists()){
            myFile.mkdirs();
        }
        byte[] bytes = new byte[1024];
        try {
            out = new FileOutputStream(realPath + File.separator + generateFileName);
            while ((len = inputStream.read(bytes)) != -1){
                out.write(bytes,0,len);
            }
            out.close();
            inputStream.close();
        } catch (Exception e) {
            log.error("文件上传异常：{}",e.toString());
        }

        return "http://127.0.0.1:"+realPath + generateFileName;
    }
}
