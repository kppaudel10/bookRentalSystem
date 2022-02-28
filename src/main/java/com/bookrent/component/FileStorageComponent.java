//package com.bookrent.component;
//
//import com.bookrent.dto.ResponseDto;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.io.FilenameUtils;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.UUID;
//
//@Slf4j
//@Component
//public class FileStorageComponent {
//
//    public ResponseDto storeFile(MultipartFile multipartFile) throws IOException {
//        String directoryPath = System.getProperty("user.name")+ File.separator+"WICC";
//        File directoryFile = new File(directoryPath);
//
//        if (!directoryFile.exists()){
//            directoryFile.mkdir();
//        }else {
//            log.info("already exits");
//        }
//        String ext = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
//        if (ext.equalsIgnoreCase("jpg")
//                || ext.equalsIgnoreCase("png")
//       || ext.equalsIgnoreCase("jpeg")){
//                    //store file
//            UUID uuid = UUID.randomUUID();
//            String filePath = directoryPath + File.separator+uuid +multipartFile.getOriginalFilename();
//            File fileStore = new File(filePath);
//            multipartFile.transferTo(fileStore);
//            return ResponseDto.builder().status(true).
//                    message(filePath)
//                    .build();
//        }
//        else {
//            return ResponseDto.builder().status(false).
//                    message("invalid extension")
//                    .build();
//        }
//    }
//}
