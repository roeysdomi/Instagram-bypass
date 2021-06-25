import java.util.List;
import java.util.Map;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class insta {
    private Map<String, String> data;
    private WebDriver driver;
    private int timeout = 15;

    @FindBy(css = "a[href='/about/us/']")
    @CacheLookup
    private WebElement aboutUs;

    @FindBy(css = "a[href='/developer/']")
    @CacheLookup
    private WebElement api;

    @FindBy(css = "a[href='https://play.google.com/store/apps/details?id=com.instagram.android&referrer=utm_source%3Dinstagramweb%26utm_campaign%3DloginPage%26ig_mid%3DW4AncAALAAH_eyWt7nLVFeOeqOjA%26utm_medium%3Dbadge']")
    @CacheLookup
    private WebElement availableOnGooglePlay;

    @FindBy(css = "a[href='https://itunes.apple.com/app/instagram/id389801252?pt=428156&ct=igweb.loginPage.badge&mt=8']")
    @CacheLookup
    private WebElement availableOnTheAppStore;

    @FindBy(css = "a[href='/explore/locations/']")
    @CacheLookup
    private WebElement directory;

    @FindBy(css = "a._2Lks6")
    @CacheLookup
    private WebElement forgotPassword;

    @FindBy(css = "a[href='https://www.microsoft.com/en-us/store/apps/instagram/9nblggh5l9xt']")
    @CacheLookup
    private WebElement getItFromMicrosoft;

    @FindBy(css = "a[href='/directory/hashtags/']")
    @CacheLookup
    private WebElement hashtags;

    @FindBy(css = "a[href='/about/jobs/']")
    @CacheLookup
    private WebElement jobs;

    @FindBy(css = "button._5f5mN.jIbKX.KUBKM.pm766")
    @CacheLookup
    private WebElement logIn;

    private final String pageLoadedText = "Phone number, username, or email";

    private final String pageUrl = "/accounts/login/";

    @FindBy(id = "f3f378e5936ac68")
    @CacheLookup
    private WebElement password1;

    @FindBy(css = "select.hztqj")
    @CacheLookup
    private WebElement password2;

    @FindBy(id = "f11bf511aebd5b8")
    @CacheLookup
    private WebElement phoneNumberUsernameOrEmail;

    @FindBy(css = "a[href='https://instagram-press.com/']")
    @CacheLookup
    private WebElement press;

    @FindBy(css = "a[href='/legal/privacy/']")
    @CacheLookup
    private WebElement privacy;

    @FindBy(css = "a[href='/directory/profiles/']")
    @CacheLookup
    private WebElement profiles;

    @FindBy(css = "a[href='/accounts/emailsignup/']")
    @CacheLookup
    private WebElement signUp;

    @FindBy(css = "a[href='https://help.instagram.com/']")
    @CacheLookup
    private WebElement support;

    @FindBy(css = "a.l93RR._vfM2")
    @CacheLookup
    private WebElement terms;

    public insta() {
    }

    public insta(WebDriver driver) {
        this();
        this.driver = driver;
    }

    public insta(WebDriver driver, Map<String, String> data) {
        this(driver);
        this.data = data;
    }

    public insta(WebDriver driver, Map<String, String> data, int timeout) {
        this(driver, data);
        this.timeout = timeout;
    }

    /**
     * Click on About Us Link.
     *
     * @return the insta class instance.
     */
    public insta clickAboutUsLink() {
        aboutUs.click();
        return this;
    }

    /**
     * Click on Api Link.
     *
     * @return the insta class instance.
     */
    public insta clickApiLink() {
        api.click();
        return this;
    }

    /**
     * Click on Available On Google Play Link.
     *
     * @return the insta class instance.
     */
    public insta clickAvailableOnGooglePlayLink() {
        availableOnGooglePlay.click();
        return this;
    }

    /**
     * Click on Available On The App Store Link.
     *
     * @return the insta class instance.
     */
    public insta clickAvailableOnTheAppStoreLink() {
        availableOnTheAppStore.click();
        return this;
    }

    /**
     * Click on Directory Link.
     *
     * @return the insta class instance.
     */
    public insta clickDirectoryLink() {
        directory.click();
        return this;
    }

    /**
     * Click on Forgot Password Link.
     *
     * @return the insta class instance.
     */
    public insta clickForgotPasswordLink() {
        forgotPassword.click();
        return this;
    }

    /**
     * Click on Get It From Microsoft Link.
     *
     * @return the insta class instance.
     */
    public insta clickGetItFromMicrosoftLink() {
        getItFromMicrosoft.click();
        return this;
    }

    /**
     * Click on Hashtags Link.
     *
     * @return the insta class instance.
     */
    public insta clickHashtagsLink() {
        hashtags.click();
        return this;
    }

    /**
     * Click on Jobs Link.
     *
     * @return the insta class instance.
     */
    public insta clickJobsLink() {
        jobs.click();
        return this;
    }

    /**
     * Click on Log In Button.
     *
     * @return the insta class instance.
     */
    public insta clickLogInButton() {
        logIn.click();
        return this;
    }

    /**
     * Click on Press Link.
     *
     * @return the insta class instance.
     */
    public insta clickPressLink() {
        press.click();
        return this;
    }

    /**
     * Click on Privacy Link.
     *
     * @return the insta class instance.
     */
    public insta clickPrivacyLink() {
        privacy.click();
        return this;
    }

    /**
     * Click on Profiles Link.
     *
     * @return the insta class instance.
     */
    public insta clickProfilesLink() {
        profiles.click();
        return this;
    }

    /**
     * Click on Sign Up Link.
     *
     * @return the insta class instance.
     */
    public insta clickSignUpLink() {
        signUp.click();
        return this;
    }

    /**
     * Click on Support Link.
     *
     * @return the insta class instance.
     */
    public insta clickSupportLink() {
        support.click();
        return this;
    }

    /**
     * Click on Terms Link.
     *
     * @return the insta class instance.
     */
    public insta clickTermsLink() {
        terms.click();
        return this;
    }

    /**
     * Fill every fields in the page.
     *
     * @return the insta class instance.
     */
    public insta fill() {
        setPhoneNumberUsernameOrEmailTextField();
        setPassword1DropDownListField();
        setPassword2DropDownListField();
        return this;
    }

    /**
     * Fill every fields in the page and submit it to target page.
     *
     * @return the insta class instance.
     */
    public insta fillAndSubmit() {
        fill();
        return submit();
    }

    /**
     * Set default value to Password Drop Down List field.
     *
     * @return the insta class instance.
     */
    public insta setPassword1DropDownListField() {
        return setPassword1DropDownListField(data.get("PASSWORD_1"));
    }

    /**
     * Set value to Password Drop Down List field.
     *
     * @return the insta class instance.
     */
    public insta setPassword1DropDownListField(String password1Value) {
        password1.sendKeys(password1Value);
        return this;
    }

    /**
     * Set default value to Password Drop Down List field.
     *
     * @return the insta class instance.
     */
    public insta setPassword2DropDownListField() {
        return setPassword2DropDownListField(data.get("PASSWORD_2"));
    }

    /**
     * Set value to Password Drop Down List field.
     *
     * @return the insta class instance.
     */
    public insta setPassword2DropDownListField(String password2Value) {
        new Select(password2).selectByVisibleText(password2Value);
        return this;
    }

    /**
     * Set default value to Phone Number Username Or Email Text field.
     *
     * @return the insta class instance.
     */
    public insta setPhoneNumberUsernameOrEmailTextField() {
        return setPhoneNumberUsernameOrEmailTextField(data.get("PHONE_NUMBER_USERNAME_OR_EMAIL"));
    }

    /**
     * Set value to Phone Number Username Or Email Text field.
     *
     * @return the insta class instance.
     */
    public insta setPhoneNumberUsernameOrEmailTextField(String phoneNumberUsernameOrEmailValue) {
        phoneNumberUsernameOrEmail.sendKeys(phoneNumberUsernameOrEmailValue);
        return this;
    }

    /**
     * Submit the form to target page.
     *
     * @return the insta class instance.
     */
    public insta submit() {
        clickLogInButton();
        return this;
    }

    /**
     * Unset default value from Password Drop Down List field.
     *
     * @return the insta class instance.
     */
    public insta unsetPassword2DropDownListField() {
        return unsetPassword2DropDownListField(data.get("PASSWORD_2"));
    }

    /**
     * Unset value from Password Drop Down List field.
     *
     * @return the insta class instance.
     */
    public insta unsetPassword2DropDownListField(String password2Value) {
        new Select(password2).deselectByVisibleText(password2Value);
        return this;
    }

    /**
     * Verify that the page loaded completely.
     *
     * @return the insta class instance.
     */
    public insta verifyPageLoaded() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getPageSource().contains(pageLoadedText);
            }
        });
        return this;
    }

    /**
     * Verify that current page URL matches the expected URL.
     *
     * @return the insta class instance.
     */
    public insta verifyPageUrl() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getCurrentUrl().contains(pageUrl);
            }
        });
        return this;
    }
}
