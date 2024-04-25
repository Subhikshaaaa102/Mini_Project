package miniProject;
			import java.io.FileInputStream;
			import java.time.Duration;
			import java.util.Properties;
			import java.util.Scanner;

			import org.openqa.selenium.WebDriver;
			import org.openqa.selenium.chrome.ChromeDriver;
			import org.openqa.selenium.firefox.FirefoxDriver;

			public class Writer {
				 static WebDriver driver;
				 static Properties prop = new Properties();
				 
				public static WebDriver getWebDriver(){
					Scanner sc = new Scanner(System.in);
					System.out.println("Please select the browser:\n1.Chrome\n2.Firefox");
					String browser = sc.next();
					sc.close();
					try {
						FileInputStream fis = new FileInputStream("C:\\Users\\2318392\\eclipse-workspace\\MiniProject\\DriverProperties.properties");
						prop.load(fis);
						fis.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					//System.out.println(prop.getProperty("browser"));
					if(browser.equalsIgnoreCase("chrome")) {
						driver = new ChromeDriver();
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
						driver.get(prop.getProperty("baseUrl"));;
						return driver;
					}
					
					else if(browser.equalsIgnoreCase("firefox")) {
						driver = new FirefoxDriver();
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
						driver.get(prop.getProperty("baseUrl"));
						return driver;
					}
					else {
						System.out.println("No browser found");
						return driver;
					}

				}
			}