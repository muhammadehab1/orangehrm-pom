package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class AdminPage extends BasePage {

    @FindBy(xpath = "//label[text()='Username']/following::input[1]")
    private WebElement usernameSearchInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@role='rowgroup']/div")
    private List<WebElement> tableRows;

    public AdminPage(WebDriver driver) {
        super(driver);
    }

    public void searchByUsername(String username) {
        usernameSearchInput.clear();
        usernameSearchInput.sendKeys(username);
        searchButton.click();
    }

    public int getResultCount() {
        int count = 0;
        for (WebElement r : tableRows) {
            String role = r.getAttribute("role");
            if (r.findElements(By.xpath(".//div[@role='cell']")).size() > 0) {
                count++;
            }
        }
        return count;
    }

    public String getUsernameFromRow(int dataRowIndex) {
        int seen = -1;
        for (WebElement r : tableRows) {
            if (r.findElements(By.xpath(".//div[@role='cell']")).size() > 0) {
                seen++;
                if (seen == dataRowIndex) {
                    return r.findElement(By.xpath(".//div[@role='cell'][2]")).getText();
                }
            }
        }
        throw new RuntimeException("Data row not found"); 
    }

    public String getUserRoleFromRow(int dataRowIndex) {
        int seen = -1;
        for (WebElement r : tableRows) {
            if (r.findElements(By.xpath(".//div[@role='cell']")).size() > 0) {
                seen++;
                if (seen == dataRowIndex) {
                    return r.findElement(By.xpath(".//div[@role='cell'][3]")).getText();
                }
            }
        }
        throw new RuntimeException("Data row not found"); 
    }

    public String getStatusFromRow(int dataRowIndex) {
        int seen = -1;
        for (WebElement r : tableRows) {
            if (r.findElements(By.xpath(".//div[@role='cell']")).size() > 0) {
                seen++;
                if (seen == dataRowIndex) {
                    return r.findElement(By.xpath(".//div[@role='cell'][5]")).getText();
                }
            }
        }
        throw new RuntimeException("Data row not found"); 
    }

    public void clickDeleteForRow(int dataRowIndex) {
        int seen = -1;
        for (WebElement r : tableRows) {
            if (r.findElements(By.xpath(".//div[@role='cell']")).size() > 0) {
                seen++;
                if (seen == dataRowIndex) {
                    // locate delete button (trash icon) inside the row
                    WebElement del = r.findElement(By.xpath(".//button[contains(@class,'oxd-icon-button')]"));
                    del.click();
                    return;
                }
            }
        }
        throw new RuntimeException("Delete button not found for row"); 
    }

    public void assertDeletionBlocked() {
           boolean errorShown = driver.findElements(By.xpath("//p[contains(text(),'Cannot delete') or contains(text(),'cannot') or contains(text(),'Error')]")).size() > 0;
        int results = getResultCount();
        Assert.assertTrue(errorShown || results > 0, "Deletion was unexpectedly allowed and user disappeared.");
    }
}
