package miniProject;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
public class OnlineMobileShoppingg {
	
	static WebDriver driver;
	static Excel ex = new Excel();
	
	//Method to create Driver
	public WebDriver createDriver(){
		driver=Writer.getWebDriver();
		driver.manage().window().maximize();
		return driver;
	}
	
	//METHOD TO VALIDATE THE TEXT BY GIVING INPUT IN THE TEXTBOX
	public String textValidate() throws IOException {
		ex.dataExtract();
		driver.findElement(By.cssSelector("#twotabsearchtextbox")).sendKeys(ex.search);
		driver.findElement(By.cssSelector("#nav-search-submit-button")).click();
		//VALIDATING THE TEXT ---->1-16 of over 3,000 results for "mobile smartphones under 30000"
		String getText = driver.findElement(By.xpath("//*[@id=\"search\"]/span[2]/div/h1/div/div[1]/div")).getText();
		String[] ge = getText.split(" ");
		return "Number of pages "+ ge[0]+"\n"+"Number of items "+ ge[2]+" "+ge[3];
 	}
	
	//METHOD TO COUNT THE LIST IN THE DROPDOWNBOX
	public String CountOfList() {
		String count="null";
		WebElement dropdown=driver.findElement(By.xpath("//*[@id=\"s-result-sort-select\"]"));
		Select select = new Select(dropdown);
		try {
			List<WebElement>osize=select.getOptions();
			count = "Count Of Element in the List: "+ osize.size();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	//METHOD TO GET ALL THE ELEMWNT FROM THE LIST
	public List<String> listTheElement() {
		WebElement dropdown=driver.findElement(By.xpath("//*[@id=\"s-result-sort-select\"]"));
		Select select = new Select(dropdown);
		List<WebElement> list1 = new ArrayList<WebElement>();
		list1 = select.getOptions();
		List<String> linkStr = new ArrayList<String>();
		linkStr.add("Available Options:");
		for(int i=0; i<list1.size(); i++){
			linkStr.add(list1.get(i).getText());
		}
		return linkStr;

		
	}
	
	//VALIDATING WHETHER THE CORRECT PAGE GOT SELECTED
	public String listValidate() {
		String validate = "null";
		String options="null";
		WebElement dropdown=driver.findElement(By.xpath("//*[@id=\"s-result-sort-select\"]"));
		Select select = new Select(dropdown);
		try {
			ex.dataExtract();
			select.selectByVisibleText(ex.option);
			List<WebElement>osize=select.getOptions();
			for(int i=0; i<osize.size();i++) {
				 options = driver.findElement(By.xpath("//*[@id=\"a-autoid-2-announce\"]")).getText();
				//System.out.println(options);
				if(options.contains((ex.option))) {
					validate= ex.option + " is Successfully Selected";
				}
			}
		}catch(Exception e) {
			//System.out.println("No element found");
			validate=(Writer.prop.getProperty("optionToBeSelected")+ " there is no such element in the list");
		}
		return validate;
	}
	
	//CLOSING THE BROWSER
	public void close() {
		driver.quit();
	}
	public static void main(String[] args) throws Exception {
		OnlineMobileShoppingg oms = new OnlineMobileShoppingg();
		oms.createDriver();
		String text = oms.textValidate();
		String count =oms.CountOfList();
		String validate = oms.listValidate();
		List<String> link = oms.listTheElement();
		oms.close();
		PrintStream ps = new PrintStream(new File("C:\\Users\\2318392\\eclipse-workspace\\MiniProject\\Result.txt"));
		System.setOut(ps);
		try {
			ps.print(text);
			System.out.println();
			ps.print(count);
			for(String s:link) {
				System.out.println();
				ps.print(s);
			}
			System.out.println();
			ps.print(validate);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
