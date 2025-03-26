package Automation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.IOException;

public class Datatest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        try {
            // Set up WebDriver
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

            // Navigate to GitHub login page
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
            System.out.println("✅ Opened orangehrm login page.");
        } catch (Exception e) {
            System.out.println("❌ WebDriver setup failed: " + e.getMessage());
        }
    }
    
    @DataProvider(name = "LoginData")
    public Object[][] getLoginData() throws IOException {
		String filePath = System.getProperty("user.dir") + "/src/test/resources/testdata.xlsx";
        Exceldata.loadExcel(filePath, "Sheet1");
        int rowCount = Exceldata.getRowCount();
        Object[][] data = new Object[rowCount-1][2];
        for(int i=1;i<rowCount;i++) {
        	data[i-1][0] = Exceldata.getCellData(i, 0);
        	data[i-1][1] = Exceldata.getCellData(i, 1);
        }      
        return data;
    }
    
    @Test(dataProvider = "LoginData")
    public void testLogin(String username, String password) {
        System.out.println("🔹 Testing with Username: " + username + " | Password: " + password);

        // Simulating login process (Replace with actual WebDriver logic)
        boolean loginSuccess = performLogin(username, password);

        // Asserting login success
        Assert.assertTrue(loginSuccess, "❌ Login failed for: " + username);
    }

    

    private boolean performLogin(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("✅ Browser closed successfully.");
        }
    }
}


