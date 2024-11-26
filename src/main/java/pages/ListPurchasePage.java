package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ListPurchasePage extends BasePage {
    @FindBy(xpath = "//table[@id='POData']/tbody/tr")
    private List<WebElement> lstPurchasesElement;
    //
    private List<String> lstStringDate = new ArrayList<>();
    private List<String> lstReference = new ArrayList<>();
    @FindBy(xpath = "//a[normalize-space()='Next >']")
    private WebElement nextbottomButton;

    public ListPurchasePage(WebDriver d) {
        super(d);
    }

    //Method to check datetime Format: dd/MM/yyyy HH:mm:ss
    public static boolean isValidDateTime(String format, String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        try {
            LocalDateTime.parse(dateTimeString, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    //Method to check list String DESC
    private static boolean isSortedDescending(List<String> listCheck) {
        for (int i = 1; i < listCheck.size(); i++) {
            if (listCheck.get(i - 1).compareTo(listCheck.get(i)) < 0) {
                return false;
            }
        }
        return true;
    }

    //Method to check list String ASC
    private static boolean isSortedAscending(List<String> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1).compareTo(list.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    public boolean verifyListPurchasesDisplay(String numOfPurchase) {
        System.out.println("Number of purchases displayed: " + lstPurchasesElement.size());
        return lstPurchasesElement.size() == Integer.parseInt(numOfPurchase);
    }

    public boolean verifyTypeOfDatePurchases(String datetimeFormatVerify) throws InterruptedException {
        boolean flag = true;
        for (int i = 1; i <= lstPurchasesElement.size(); i++) {
            WebElement dateElement = getDriver().findElement(By.xpath("//table[@id='POData']/tbody/tr[" + i + "]/td[2]"));
            lstStringDate.add(dateElement.getText());
        }
        System.out.println("List of Date Purchases: ");
        for (String s : lstStringDate) {
            flag = flag && isValidDateTime(datetimeFormatVerify, s);
            System.out.println(s + " " + flag);
        }
        return flag;
    }

    public boolean VerifyTypeOfSortReferencePurchases(String orderType) {
        boolean flag = true;
        for (int i = 1; i <= lstPurchasesElement.size(); i++) {
            WebElement referenceElement = getDriver().findElement(By.xpath("//table[@id='POData']/tbody/tr[" + i + "]/td[3]"));
            lstReference.add(referenceElement.getText());
        }
        if (orderType.equals("DESC")) {
            flag = flag && isSortedDescending(lstReference);
        } else if (orderType.equals("ASC")) {
            flag = flag && isSortedAscending(lstReference);
        }
        return flag;
    }

//    public void Test() {
//        System.out.println(lstPurchasesElement.size());
//        for (int i = 1; i <= lstPurchasesElement.size(); i++) {
//            WebElement dateElement = getDriver().findElement(By.xpath("//table[@id='POData']/tbody/tr[" + i + "]/td[2]"));
//            System.out.println(dateElement.getText() + " " + isValidDateTime("dd/MM/yyyy HH:mm:ss", dateElement.getText()));
//        }
//    }
}
