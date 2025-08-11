package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage {

    @FindBy(xpath = "//span[text()='Admin']")
    private WebElement adminTab;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public void goToAdmin() {
        adminTab.click();
    }
}
