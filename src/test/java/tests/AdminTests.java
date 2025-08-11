package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AdminPage;
import pages.DashboardPage;
import pages.LoginPage;

public class AdminTests extends BaseTest {

    @Test
    public void verifyAdminUserSearchAndDeletionNotAllowed() {
        LoginPage login = new LoginPage(driver);
        DashboardPage dash = new DashboardPage(driver);
        AdminPage admin = new AdminPage(driver);

        // Login
        login.login("Admin", "admin123");

        // Navigate to Admin
        dash.goToAdmin();

        // Search user
        admin.searchByUsername("Admin");

        // Assertions
        int results = admin.getResultCount();
        Assert.assertTrue(results >= 1, "Expected at least one result, found: " + results);

        String username = admin.getUsernameFromRow(0);
        String role = admin.getUserRoleFromRow(0);
        String status = admin.getStatusFromRow(0);

        Assert.assertEquals(username, "Admin", "Username mismatch");
        Assert.assertEquals(role, "Admin", "Role mismatch");
        Assert.assertEquals(status, "Enabled", "Status mismatch");

        // Try delete and verify not allowed
        admin.clickDeleteForRow(0);
        admin.assertDeletionBlocked();
    }
}
