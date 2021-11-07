package ru.netology;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryForm {

    public String getDate(int days, String formattedDate) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(formattedDate));
    }

    @Test
    void shouldCheckRegistrationForm() {
        String DateOfMeeting = getDate(3, "dd.MM.yyyy");

        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Воронеж");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(DateOfMeeting);
        $("[data-test-id=name] input").setValue("Иван Иванов");
        $("[data-test-id=phone] input").setValue("+79170000000");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(20));
    }
}
