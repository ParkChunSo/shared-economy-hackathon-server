package com.hackathon.sharedeconomy.utill;

import com.hackathon.sharedeconomy.model.entity.GoodsImage;
import com.hackathon.sharedeconomy.model.enums.ImageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

public class ImageUtils {
    private static final Logger logger = LoggerFactory.getLogger(ImageUtils.class);

    public static GoodsImage uploadFile(String uploadPath, MultipartFile file) throws Exception {

        //겹쳐지지 않는 파일명을 위한 유니크한 값 생성
        UUID uid = UUID.randomUUID();

        //원본파일 이름과 UUID 결합
        String savedName = uid.toString() + "_" + file.getOriginalFilename();

        //파일을 저장할 폴더 생성(년 월 일 기준)
        String savedPath = calcPath(uploadPath);

        //저장할 파일준비
        File target = new File(uploadPath + savedPath, savedName);

        //파일을 저장
        file.transferTo(target);

        return GoodsImage.builder()
                .fileName(savedName)
                .path(savedPath + File.separator + savedName)
                .uri("/goods/image/" + savedName)
                .type(ImageType.NONE)
                .build()
                ;
    }

    //폴더 생성 함수
    @SuppressWarnings("unused")
    private static String calcPath(String uploadPath) {

        Calendar cal = Calendar.getInstance();

        String yearPath = File.separator + cal.get(Calendar.YEAR);

        String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);

        String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));

        makeDir(uploadPath, yearPath, monthPath, datePath);

        logger.info(datePath);

        return datePath;
    }//calcPath

    //폴더 생성 함수
    private static void makeDir(String uploadPath, String... paths) {

        if(new File(uploadPath + paths[paths.length -1]).exists()) {
            return;
        }//if

        for(String path : paths) {

            File dirPath = new File(uploadPath + path);

            if(!dirPath.exists()) {
                dirPath.mkdir();
            }//if

        }//for

    }//makeDi
}
