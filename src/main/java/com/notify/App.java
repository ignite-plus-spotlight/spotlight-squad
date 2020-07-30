package com.notify;

import com.notify.util.SendEmail;
import com.notify.util.VelToPDF;

import java.io.File;

public class App {
    public static void main(String[] args) {
        try {

            CertGenerator certGenerator = new CertGenerator();
            String htmlData = certGenerator.certGenerate();
            VelToPDF.velocityToPdf(htmlData);
           // SendEmail.sendmail("btsignit99@gmail.com","yashu2020","btsignite99@gmail.com","yashaswiniraju99@gmail.com", new File("Test.pdf"));

        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
