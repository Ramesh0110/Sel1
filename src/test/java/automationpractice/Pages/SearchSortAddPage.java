package automationpractice.Pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import automationpractice.libraries.Base;


public class SearchSortAddPage extends Base {

	final static Logger logger = Logger.getLogger(SearchSortAddPage.class);

	public SearchSortAddPage TCID501() {
		try {

			driver.findElement(By.cssSelector("button[name='submit_search']")).click();
			String alert = driver.findElement(By.cssSelector("p[class='alert alert-warning']")).getText().trim();
			Assert.assertTrue(alert.contains("Please enter a search keyword"));
			Lib.customWait(3);
		} catch (Exception e) {
			logger.info("error" + e);
		}
		return this;
	}

	public SearchSortAddPage TCID502() {
		try {

			searchBox("Jacket");

			driver.findElement(By.cssSelector("button[name='submit_search']")).click();
			String alert = driver.findElement(By.cssSelector("p[class='alert alert-warning']")).getText().trim();
			Lib.customWait(3);
			Assert.assertTrue(alert.contains("No results were found for your search"));

		} catch (Exception e) {
			logger.info("error" + e);
		}
		return this;
	}

	public SearchSortAddPage TCID503() {
		try {
			searchBox("Shoes");
			Lib.clickButton(By.cssSelector("button[name='submit_search']"));
			String searchResult = driver.findElement(By.partialLinkText("Printed Summer Dress")).getText().trim();

			Assert.assertFalse(searchResult.contains("shoes"));

		} catch (Exception e) {

			// logger.info("error" + e);
		}
		return this;
	}

	public SearchSortAddPage TCID504() {
		try {

			searchBox("T SHIRTS");
			Lib.clickButton(By.cssSelector("button[name='submit_search']"));
			String searchResult = driver
					.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div/div[2]/h5/a")).getText().trim();
			Assert.assertTrue(searchResult.contains("T SHIRTS"));
		} catch (Exception e) {

			logger.info("error" + e);
		}
		return this;
	}

	public SearchSortAddPage TCID601() {
		try {
			searchBox("dress");
			Lib.clickButton(By.cssSelector("button[name='submit_search']"));
			Lib.selectDropDown(By.cssSelector("#selectProductSort"), "Price: Lowest first");
			Lib.customWait(3);

			List<WebElement> price = driver.findElements(By.cssSelector("span[itemprop='price']"));

			ArrayList<String> al = new ArrayList<String>();
			for (int i = 0; i < price.size(); i++) {
				al.add(price.get(i).getText());

			}
			ArrayList<String> copiedList = new ArrayList<String>();
			for (int i = 0; i < al.size(); i++) {
				copiedList.add(al.get(i));
			}
			Collections.sort(al, Collections.reverseOrder());
			Collections.sort(copiedList, Collections.reverseOrder());

			logger.info("this is original:" + al);
			logger.info("this is copied list:" + copiedList);

			Assert.assertEquals(al, copiedList);

		} catch (Exception e) {

			logger.info("error" + e);
		}
		return this;
	}

	public SearchSortAddPage TCID602() {
		try {

			
			searchBox("dress");
			Lib.clickButton(By.cssSelector("button[name='submit_search']"));
			Lib.selectDropDown(By.cssSelector("#selectProductSort"), "Price: Highest first");
			Lib.customWait(3);

			List<WebElement> price = driver.findElements(By.cssSelector("span[itemprop='price']"));

			ArrayList<String> al = new ArrayList<String>();
			for (int i = 0; i < price.size(); i++) {
				al.add(price.get(i).getText());

			}
			ArrayList<String> copiedList = new ArrayList<String>();
			for (int i = 0; i < al.size(); i++) {
				copiedList.add(al.get(i));
			}
			Collections.sort(al, Collections.reverseOrder());
			Collections.sort(copiedList, Collections.reverseOrder());

			logger.info("this is original:" + al);
			logger.info("this is copied list:" + copiedList);

			Assert.assertEquals(al, copiedList);

		} catch (Exception e) {

			logger.info("error" + e);
		}

		return this;
	}

	public SearchSortAddPage TCID603() {
		try {

			searchBox("dress");
			sortItemElm("Product Name: A to Z");
			Lib.customWait(3);

			List<WebElement> productName = driver.findElements(By.cssSelector("a[class='product-name']"));

			ArrayList<String> al = new ArrayList<String>();
			for (int i = 0; i < productName.size(); i++) {
				al.add(productName.get(i).getText());

			}
			logger.info(al);
			ArrayList<String> originalList = new ArrayList<String>();
			originalList.addAll(al);
			logger.info("Originial sorted A-Z value:" + originalList);

			Collections.sort(al);
			ArrayList<String> copiedList = new ArrayList<String>();

			copiedList.addAll(al);

			logger.info("After sorted original value:" + copiedList);
			Assert.assertNotSame(originalList, copiedList);

		} catch (Exception e) {

			logger.info("error" + e);

		}
		return this; 
	}

	public SearchSortAddPage TCID604() {
		try {
			searchBox("dress");
			sortItemElm("Product Name: Z to A");
			Lib.customWait(3);

			List<WebElement> productName = driver.findElements(By.cssSelector("a[class='product-name']"));

			ArrayList<String> al = new ArrayList<String>();
			for (int i = 0; i < productName.size(); i++) {
				al.add(productName.get(i).getText());

			}
			logger.info(al);
			ArrayList<String> originalList = new ArrayList<String>();
			originalList.addAll(al);
			logger.info("Originial sorted Z-A value:" + originalList);

			Collections.sort(al, Collections.reverseOrder());
			ArrayList<String> copiedList = new ArrayList<String>();

			copiedList.addAll(al);

			logger.info("After sorted original value:" + copiedList);
			Assert.assertNotEquals(originalList, copiedList);

		} catch (Exception e) {

			logger.info("error" + e);

		}
		return this;
	}

	public SearchSortAddPage TCID701() {
		try {
			searchBox("dress");
			sortItemElm("Product Name: Z to A");
			Lib.customWait(3);

			List<WebElement> addToCart = driver.findElements(By.cssSelector("a[title='Add to cart']"));
			Iterator<WebElement> it = addToCart.iterator();
			while (it.hasNext()) {
				it.next().click();
			}
			Lib.customWait(3);
			String count = driver.findElement(By.cssSelector("[class='ajax_cart_quantity']")).getText();
			Assert.assertTrue(count.contains("7"));

			driver.findElement(By.xpath("//span[contains(text(),'Proceed to checkout')]")).click();
			Lib.customWait(3);
		} catch (Exception e) {
			logger.info("error" + e);

		}
		return this;
	}
	
	
	//Helper Method~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	private static void searchBox(String searchItem) {
		try {
			WebElement enTxt = driver.findElement(By.xpath("//input[@id='search_query_top']"));
			enTxt.clear();
			enTxt.sendKeys(searchItem);
		} catch (Exception e) {

		}

	}

	private static void sortItemElm(String sortItem) {
		try {
			Lib.clickButton(By.cssSelector("button[name='submit_search']"));
			WebElement center = driver.findElement(By.cssSelector("div[id='center_column']"));
			center.findElement(By.cssSelector("i[class='icon-th-list']")).click();
			Lib.customWait(3);
			Lib.selectDropDown(By.cssSelector("#selectProductSort"), sortItem);
		} catch (Exception e) {

		}
	}


}// class closed
