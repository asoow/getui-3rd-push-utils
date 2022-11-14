package com.getui.gtps;

import com.getui.gtps.manufacturer.ManufacturerFactory;
import com.getui.gtps.manufacturer.ManufacturerFile;
import com.getui.gtps.manufacturer.Result;
import com.getui.gtps.manufacturer.constant.ManufacturerConstants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

/**
 * @author WuMing
 * @date 2022/11/14 5:29 下午
 */
public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        GtSDKStarter.getInstance().loadPropertyFile("/Users/cp/git/java/getui-3rd-push-utils/config/application.properties").init();
//        Main main = new Main();
//        main.testUploadIcon();
    }

    public void testUploadIcon() throws FileNotFoundException {
        Map<String, Result> resultMap = ManufacturerFactory.uploadIcon(new File("/Users/cp/git/java/getui-3rd-push-utils/image/icon.png"));

        this.printData(resultMap);
    }

    public void testUploadPic() throws FileNotFoundException {
        Map<String, Result> resultMap = ManufacturerFactory.uploadPic(new File("image/pic.jpeg"));

        this.printData(resultMap);
    }

    public void testUploadIconSeparately() {
        ManufacturerFile file1 = new ManufacturerFile(ManufacturerConstants.ManufacturerName.OPPO, "image/icon.png");
        ManufacturerFile file2 = new ManufacturerFile(ManufacturerConstants.ManufacturerName.XM, "image/icon.png");
        ManufacturerFile[] manufacturerFiles = new ManufacturerFile[]{file1, file2};
        Map<String, Result> resultMap = ManufacturerFactory.uploadIcon(manufacturerFiles);

        this.printData(resultMap);
    }

    public void testUploadPicSeparately() {
        ManufacturerFile file1 = new ManufacturerFile(ManufacturerConstants.ManufacturerName.OPPO, "image/pic.jpeg");
        ManufacturerFile file2 = new ManufacturerFile(ManufacturerConstants.ManufacturerName.XM, "image/pic.jpeg");
        ManufacturerFile[] manufacturerFiles = new ManufacturerFile[]{file1, file2};
        Map<String, Result> resultMap = ManufacturerFactory.uploadPic(manufacturerFiles);

        this.printData(resultMap);
    }

    public void testMemoryCache() throws FileNotFoundException {
        Map<String, Result> resultMap = ManufacturerFactory.uploadIcon(new File("image/icon.png"));
        this.printData(resultMap);

        Map<String, Result> resultMap2 = ManufacturerFactory.uploadIcon(new File("image/icon.png"));
        System.out.println("--------------------------------################################");
        System.out.println(resultMap.size() == resultMap2.size());
        System.out.println("--------------------------------################################2");
        System.out.println(resultMap.get("XM").getData().equals(resultMap2.get("XM").getData()));
        System.out.println("--------------------------------################################3");
        System.out.println(resultMap.get("OPPO").getData().equals(resultMap2.get("OPPO").getData()));
    }


    private void printData(Map<String, Result> resultMap) {
        System.out.println(resultMap);
        System.out.println("resultMap size : " + resultMap.size());
        Result xm = resultMap.get("XM");
        System.out.println(xm);
        System.out.println("--------------------------------");
        System.out.println(resultMap.get("XM").getCode());
        System.out.println("################################");
        System.out.println(resultMap.get("XM").getMessage());
        System.out.println("--------------------------------");
        System.out.println("################################");
        System.out.println(resultMap.get("XM").getData());
        System.out.println("666--------------------------------");
        Result op = resultMap.get("OPPO");
        System.out.println(op);
        System.out.println("--------------------------------");
        System.out.println(resultMap.get("OPPO").getCode());
        System.out.println("################################");
        System.out.println(resultMap.get("OPPO").getMessage());
        System.out.println("--------------------------------");
        System.out.println("################################");
        System.out.println(resultMap.get("OPPO").getData());
    }
}
