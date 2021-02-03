package WebDriver;

import Utils.SeleniumElement;
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Random;

import org.apache.log4j.Logger;

public class GittiGidiyor {
    SeleniumElement seleniumElement;
    private static final String LOG_FILE = "log4j.properties";
    Logger log = Logger.getLogger(GittiGidiyor.class);
    Properties properties = new Properties();

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dell\\Desktop\\TestAraçları\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        seleniumElement = new SeleniumElement(new ChromeDriver(options));

        try {
            properties.load(new FileInputStream(LOG_FILE));
            PropertyConfigurator.configure(properties);
        } catch (Exception e) {
            System.out.println("HATA MESAJI " + e.getMessage());

        }

    }

    @Test
    public void gittiGidiyor() {

        seleniumElement.GoPage("https://www.gittigidiyor.com/");
        seleniumElement.TextKontrol("gittigidiyor");
        log.info("Sayfaya giriş yaptı ve anasayfayı kontol etti");
        seleniumElement.MouseHareket("//*[@id=\"main-header\"]/div[3]/div/div/div[1]/div[3]/div/div[1]/div/div[2]/div");
        seleniumElement.ClickLinkText("Ücretsiz Üye Ol");
        seleniumElement.ClickLinkText("Zaten Üyeyim!");
        seleniumElement.SetTextId("L-UserNameField", "testinium65@gmail.com");
        seleniumElement.SetTextId("L-PasswordField", "Testinium.65");
        seleniumElement.ClickID("gg-login-enter");
        log.info("Kayıt ekranına geldi ve giriş yaptı");

        seleniumElement.SetTextXpath("//*[@id=\"main-header\"]/div[3]/div/div/div/div[2]/form/div/div[1]/div[2]/input", "Bilgisayar");
        seleniumElement.ClickXpathMouse("//*[@id=\"main-header\"]/div[3]/div/div/div/div[2]/form/div/div[2]/button");
        log.info("Arama çubuğuna bilgisayar yazdı ve arama yaptı");

        Random rn = new Random();
        int answer = rn.nextInt(9) + 1;
        seleniumElement.ClickNewPage("https://www.gittigidiyor.com/dizustu-laptop-notebook-bilgisayar/asus-vivobook-x505za-bq838_spp_824060?id=640562267");
        log.info("Listelenen herhangi bir ürün yeni sekmede açıldı rastgele açılacaktı ama id ler sıralı değil karma şekilde listelenmiş");

        seleniumElement.ValidateUrl(seleniumElement.driver, "https://www.gittigidiyor.com/dizustu-laptop-notebook-bilgisayar/asus-vivobook-x505za-bq838_spp_824060?id=640562267");
        log.info("Açılan sayfa kontol edildi");
        seleniumElement.ScrollElementVisibleXpath("//*[@id=\"user-interested-products\"]/div[1]");
        seleniumElement.ClickXpathMouse("/html/body/div[3]/div[2]/div/div[1]/div[1]/div[2]/div[2]/div[1]/div/div[3]/div[2]/div[2]/div[1]/div/ul/li[3]/a");
        seleniumElement.ClickID("add-to-basket");
        log.info("Sepete eklendi");
        seleniumElement.MouseHareket("//*[@id=\"header_wrapper\"]/div[4]/div[3]/a/div[1]");
        seleniumElement.ClickLinkText("Sepete Git");
        seleniumElement.ClickXpathMouse("/html/body/div[1]/div[2]/div/div[1]/form/div/div/div[2]/div[1]/div[2]/div[6]/div[2]/div[2]/div[4]/div/span[2]");
        seleniumElement.AssertXpath("real-discounted-price", "new-price");
        log.info("Ssepette ürün sayısı arttırıldı ");
        seleniumElement.ClickLinkText("Sil");
        seleniumElement.TextKontrol("Sepetinizde ürün bulunmamaktadır.");
        log.info("Sepetteki ürünlerin fiyatı kontrol edildi ve ürünler silindi");


    }

    @After
    public void closePage() {
        seleniumElement.PageQuit();
    }

}
