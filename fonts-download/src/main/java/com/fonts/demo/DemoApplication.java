package com.fonts.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws Exception {
//        String fontFolder = "Open Sans Regular,Arial Unicode MS Regular";
//        String fontFolder = "黑体";
//        String fontFolder = "Arial Unicode MS Bold";
          String fontFolder = "DIN Offc Pro Medium,Arial Unicode MS Regular";
//        String fontFolder = "Metropolis Regular,Noto Sans Regular";
//        String fontFolder = "Metropolis Light Italic,Noto Sans Italic";
        //String fontFolder = "Metropolis Light,Noto Sans Regular";


//        String link = "http://47.97.24.100:8899/fonts/"+fontFolder+"/{start}-{end}.pbf";
//        String link ="https://api.mapbox.com/fonts/v1/mapbox/"+fontFolder+"/{start}-{end}.pbf?access_token=pk.eyJ1IjoibHhxanNzIiwiYSI6ImNqY3NkanRjajB1MWwzM3MwcnA0dDJrYngifQ.ApTVfmm2zBM_kF22DvtowQ";
        String link = "https://free.tilehosting.com/fonts/"+fontFolder+"/{start}-{end}.pbf?key=yzgDrAunRjJjwhG6D3u7";
        for (int i = 0; i <= 255; i++) {
            int start = 255*i+i;
            int end = 255*i+i+255;
            try {
                URL url = new URL(link.replace("{start}", start + "").replace("{end}", end + ""));
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(5000);
                conn.connect();

                InputStream in = conn.getInputStream();
                File dir = new File("tileFont/"+fontFolder);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File file = new File("tileFont/"+fontFolder+"/" +start+"-"+end+ ".pbf");
                if (!file.exists()) {
                    file.createNewFile();
                }
                OutputStream out = new FileOutputStream(file);
                byte[] bytes = new byte[1024 * 20];
                int len = 0;
                while ((len = in.read(bytes)) != -1) {
                    out.write(bytes, 0, len);
                }
                out.close();
                in.close();
                System.out.println("已成功下载:" +start+"-"+end+ ".pbf");
            } catch (Exception err) {
                System.out.println("err"+err);
            }
        }
    }

}
