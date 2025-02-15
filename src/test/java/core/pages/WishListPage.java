package core.pages;

import core.elements.CheckPageFiltersElements;
import core.elements.WishListElements;
import core.globals.Globals;
import core.utilities.BaseInformation;
import core.utilities.BasePageObject;
import core.utilities.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class WishListPage {

    BasePageObject basePageObject = new BasePageObject(BaseInformation.getBaseInformation());
    WishListElements elements = new WishListElements();
    WebDriver driver = BaseInformation.getDriver();

    public WishListPage() {
        PageFactory.initElements(driver, elements);
    }



    public void removePriceFilter() {
        WaitUtils.waitFor(1500);
        WebElement removeBtn = basePageObject.getWaitUtils().waitForElementClickable(elements.removePriceButton);
        removeBtn.click();
        WaitUtils.waitFor(1500);
        basePageObject.getWaitUtils().waitForElementVisible(elements.totalAmountValue);
    }
    public void checkThatCountIsIncreased() {
        String totalAmountText = elements.totalAmountValue.getText().trim();
        int total;
        try {
            total = Integer.parseInt(totalAmountText);
            int cardsCount = Globals.cardsCount;

            // Check if the total amount is less than or equal to the cards count
            if (total <= cardsCount) {
                // Throw a custom exception
                throw new IllegalStateException("Total amount (" + total + ") is not greater than cards count (" + cardsCount + ")");
            }

        } catch (NumberFormatException e) {
            System.out.println("Failed to parse total amount value: " + totalAmountText);
        }
        WaitUtils.waitFor(1500);
    }

    public void clickOnProductItemAndWait() {
        // Get the product item element from the WishListElement (Assuming you have the list already)
        WebElement productItem = elements.productItems.get(0); // Adjust based on your specific product item logic
        productItem.click();
        WaitUtils.waitFor(1500);
    }

    public void clickOnAddToWishListButtonAndWait() {
        // Get the add to wish list button element from the WishListElement
        WebElement addToWishListButton = elements.addToWishListButton; // Ensure this is the correct button reference
        addToWishListButton.click();
        WaitUtils.waitFor(1500);
    }
    }

