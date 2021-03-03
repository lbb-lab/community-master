package life.majiang.community.controller;

import life.majiang.community.dto.FileDTO;
import life.majiang.community.service.File2LocalhostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by codedrinker on 2019/6/26.
 */
@Controller
@Slf4j
public class File2LocalhostController {

    @Autowired
    private File2LocalhostService file2LocalhostService;

    @RequestMapping("/file/upload2localhost")
    @ResponseBody
    public FileDTO upload(HttpServletRequest request) throws Exception {
       MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
       MultipartFile file = multipartRequest.getFile("editormd-image-file");
        InputStream in = file.getInputStream();
        FileDTO fileDTO = new FileDTO();
        try {
            String fileName = file2LocalhostService.uploadFile(file.getInputStream(),file.getOriginalFilename());
            fileDTO.setMessage("success");
            fileDTO.setSuccess(1);
            fileDTO.setUrl(fileName);
            return fileDTO;
        } catch (IOException e) {
            log.error("upload fail", e);
            fileDTO.setSuccess(0);
            fileDTO.setMessage("上传失败");
            return fileDTO;
        }
    }
}
