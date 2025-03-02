package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.AuthConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackDriver implements WebDriverProvider {

    private static final AuthConfig config = ConfigFactory.create(AuthConfig.class);


    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {

        MutableCapabilities caps = new MutableCapabilities();

        // Set your access credentials
        caps.setCapability("browserstack.user", config.userName());
        caps.setCapability("browserstack.key", config.password());

        // Set URL of the application under test
        caps.setCapability("app", config.app());

        // Specify device and os_version for testing
        caps.setCapability("device", config.device());
        caps.setCapability("os_version", config.os_version());

        // Set other BrowserStack capabilities
        caps.setCapability("project", "First Java Project");
        caps.setCapability("build", "browserstack-build-1");
        caps.setCapability("name", "first_test");


        try {
            return new RemoteWebDriver(
                    new URL(config.browserStackUrl()), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}