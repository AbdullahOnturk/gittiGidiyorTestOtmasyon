package Utils;


import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class SeleniumElement {
    public ChromeDriver driver;
    private WebDriverWait wait;

    public SeleniumElement(ChromeDriver driver) {

        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
        driver.manage().window().maximize();
    }

    public void GoPage(String link) {
        driver.get(link);
    }

    public void PageQuit() {
        driver.quit();
    }

    private WebElement waitForID(String Id) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
    }

    public void ClickID(String ID) {//  30 sn içinde id si verilen elemente tıkla.
        waitForID((ID)).click();
    }

    private WebElement waitForClassName(String ClassName) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(ClassName)));
    }

    private WebElement waitForXpath(String Xpath) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
    }

    public void ClickLinkText(String LinkText) {
        waitForLinkText(LinkText).click();
    }

    private WebElement waitForLinkText(String LinkText) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(LinkText)));
    }

    public void MouseHareket(String Xpath) {
        Actions actions = new Actions(driver);
        actions.moveToElement(waitForXpath(Xpath)).perform();

    }

    public void ClickXpathMouse(String Xpath) {
        Actions actions = new Actions(driver);
        actions.doubleClick(waitForXpath(Xpath)).perform();

    }

    public void ClickNewPage(String Link) {
        driver.findElement(By.cssSelector("Body")).sendKeys(Keys.CONTROL + "t");
        driver.get(Link);
    }

    public void AssertXpath(String ClassNamme, String ClassNamme1) {
        assertEquals(waitForClassName((ClassNamme)).getText(), waitForClassName((ClassNamme1)).getText());

    }

    public static void ValidateUrl(WebDriver driver, String URL) {
        boolean result = false;
        if (driver.getCurrentUrl().equalsIgnoreCase(URL)) {
            result = true;
            Assert.assertTrue(result);
        }
        Assert.assertTrue(result);
    }

    public void SetTextId(String Id, String Text) {//  30 sn içinde elementi bul ve beliritilen alanları doldur
        waitForID(Id).sendKeys(Text);
    }

    public void SetTextXpath(String Xpath, String Text) {//  30 sn içinde elementi bul ve beliritilen alanları doldur
        waitForXpath(Xpath).sendKeys(Text);
    }

    public void TextKontrol(String Text) {
        try {
            driver.getPageSource().contains(Text);
        } catch (Exception e) {
            System.err.println(Text + " Kontrol İşlemi Olumsuz Sonuçlandı!!!!!!!!");
        }

    }

    public void ScrollElementVisibleXpath(String Xpath) {
        Dimension dimensions = driver.manage().window().getSize();

        Double screenHeightStart = dimensions.getHeight() * 0.5;
        int scrollStart = screenHeightStart.intValue();

        Double screenHeightEnd = dimensions.getHeight() * 0.6;
        int scrollEnd = screenHeightEnd.intValue();

        boolean don = true;
        while (don) {
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(" + scrollStart + ","
                    + scrollEnd + ");");
            try {
                WebDriverWait waitScroll = new WebDriverWait(driver, 5);
                waitScroll.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(Xpath))));
                don = false;
            } catch (Exception e) {
                don = true;
            }
        }
    }


}
