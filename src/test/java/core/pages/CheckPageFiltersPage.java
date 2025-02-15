package core.pages;

import core.elements.CheckPageFiltersElements;
import core.globals.Globals;
import core.utilities.BaseInformation;
import core.utilities.BasePageObject;
import core.utilities.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CheckPageFiltersPage {

    BasePageObject basePageObject = new BasePageObject(BaseInformation.getBaseInformation());
    CheckPageFiltersElements elements = new CheckPageFiltersElements();
    WebDriver driver = BaseInformation.getDriver();

    public CheckPageFiltersPage() {
        PageFactory.initElements(driver, elements);
    }

    /**
     * Navigates through the dropdowns by hovering and clicking:
     * Women > Tops > Jackets.
     */
    public void navigateToJacketsPage() {
        Actions actions = new Actions(driver);

        // Hover over "Women" dropdown
        WebElement women = basePageObject.getWaitUtils().waitForElementClickable(elements.womenDropdown);
        actions.moveToElement(women).perform();

        // Hover over "Tops" dropdown
        WebElement tops = basePageObject.getWaitUtils().waitForElementClickable(elements.topsDropdown);
        actions.moveToElement(tops).perform();
        WaitUtils.waitFor(1500);
        // Wait for "Jackets" to be clickable and then click it
        WebElement jackets = basePageObject.getWaitUtils().waitForElementClickable(elements.jacketsDropdown);
        jackets.click();
    }


    /**
     * Selects the Red color filter by clicking the color panel and then the red swatch.
     */
    public void selectRedColorFilter() {
        // Wait for the Color panel to be clickable and click it
        WebElement colorPanel = basePageObject.getWaitUtils().waitForElementClickable(elements.colorPanel);
        WaitUtils.waitFor(1500);
        colorPanel.click();

        // Wait for the Red color swatch to be clickable and click it
        WebElement redSwatch = basePageObject.getWaitUtils().waitForElementClickable(elements.redColorJackets);
        WaitUtils.waitFor(1500);
        redSwatch.click();

        basePageObject.getWaitUtils().waitForElementVisible(elements.productItems.get(0));
    }

    /**
     * Verifies that every product on the page has the red swatch selected.
     * This is done by comparing the count of product items with the count of red swatches marked as selected.
     *
     * @return true if the number of red swatches equals the number of product items; false otherwise.
     */
    public boolean verifyAllProductsHaveRedColorSelected() {
        WebElement totalAmount = elements.totalAmount;
        int totalAmountValue = Integer.parseInt(totalAmount.getText().trim());
        List<WebElement> redSwatches = elements.redSwatches;
        WaitUtils.waitFor(1500);
        return totalAmountValue == redSwatches.size();

    }

    public void selectWantedPriceFilter() {
        WebElement pricePannel = basePageObject.getWaitUtils().waitForElementClickable(elements.pricePanel);
        pricePannel.click();

        // Wait for the Red color swatch to be clickable and click it
        WebElement wantedPriceRange = basePageObject.getWaitUtils().waitForElementClickable(elements.wantedPrice);
        WaitUtils.waitFor(1500);
        wantedPriceRange.click();
        basePageObject.getWaitUtils().waitForElementVisible(elements.productItems.get(0));
    }

    public boolean verifyAllProductsHaveCorrectPriceRangeSelected() {
        List<WebElement> productItems = elements.productItems;
        WebElement totalAmount = elements.totalAmount;
        int totalAmountValue = Integer.parseInt(totalAmount.getText().trim());
        Globals.cardsCount = totalAmountValue;
        WaitUtils.waitFor(3000);
        return productItems.size() == totalAmountValue;

    }

    public boolean verifyAllProductPricesInRange() {
        List<WebElement> prices =  elements.cardsPriceRange;

        // Iterate through each price element and check the price range
        for (WebElement priceElement : prices) {
            String priceText = priceElement.getText().replace("$", "").trim();
            double price;
            try {
                price = Double.parseDouble(priceText);
            } catch (NumberFormatException e) {
                System.out.println("Unable to parse price: " + priceText);
                return false;
            }

            // Check if the price is within the desired range
            if (price < 50.00 || price > 59.99) {
                System.out.println("Price out of range: " + price);
                return false;
            }
        }
        return true;
    }


}
