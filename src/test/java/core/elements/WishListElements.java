package core.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class WishListElements {

    @FindBy(xpath = "//a[@class='action remove' and contains(@href, 'color=58')]")
    public WebElement removePriceButton;

    @FindBy(xpath = "//p[@class='toolbar-amount' and @id='toolbar-amount']//span[@class='toolbar-number']")
    public WebElement totalAmountValue;


    @FindBy(xpath = "//li[contains(@class,'product-item')]")
    public List<WebElement> productItems;

    @FindBy(xpath ="(//a[contains(@class, 'towishlist')]//span[text() = \"Add to Wish List\"])[1]")
    public WebElement addToWishListButton;


}
