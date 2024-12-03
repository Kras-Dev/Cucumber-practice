package definitions;

import definitions.hooks.UIHooks;
import io.cucumber.java.ru.*;
import pages.FoodPage;
import pages.HomePage;
/**
 * AddFoodUIDefinitions содержит определения шагов для сценариев, связанных с добавлением продуктов
 * тестового стенда. Этот класс использует Cucumber для определения шагов, которые будут
 * выполняться в процессе тестирования.
 *
 * Он включает методы для открытия страниц, взаимодействия с элементами пользовательского интерфейса
 * и проверки состояния компонентов после выполнения действий.
 */
public class AddFoodUIDefinitions {
    private FoodPage foodPage;
    private HomePage homePage;

    @Допустим("открыт стенд по адресу {string}")
    public void openUrl(String url){
        UIHooks.openPage(url);
        foodPage = new FoodPage();
        homePage = new HomePage();
    }
    @Допустим("выполнен переход в раздел Песочница - Товары")
    public void navigateToSandboxFoodPage(){
        homePage.clickSandBoxMenu()
                .clickProductSubMenu();
        foodPage.verifyPageTitle("Список товаров");
    }
    @Допустим("выполнено нажатие на Добавить")
    public void clickAddBtn(){
        foodPage.clickAddButton();
    }
    @Допустим("открыта форма {string}")
    public void openAddFoodForm(String title){
        foodPage.verifyModalMenuLabel(title);
    }
    @Допустим("ввод значения Наименование: {string}")
    public void enterProductName(String name) {
        foodPage.fillNameField(name);
    }
    @Допустим("выбор продукта Тип: {string}")
    public void selectProductType(String type) {
        foodPage.selectFoodType(type);
    }
    @Допустим("выбор Экзотичности: {string}")
    public void selectExotic(String exotic){
        boolean isExotic = false;
        if (exotic.equalsIgnoreCase("Экзотический")) {
            isExotic = true;
        }else {
            isExotic = Boolean.parseBoolean(exotic);
        }
        foodPage.setExoticCheckBox(isExotic);
    }
    @Допустим("выполнено нажатие на Сохранить")
    public void clickSaveBtn(){
        foodPage.clickSaveButton();
    }
    @Допустим("проверка отображения добавленного продукта: {string}, {string}, {string}")
    public void verifyAddedProduct(String name, String type, String isExotic){
        foodPage.verifyLastRow(name, type, isExotic);
    }
    @Допустим("выполнено нажатие на Песочница")
    public void clickSandboxBtn() {
        homePage.clickSandBoxMenu();
    }
    @Допустим("выполнено нажатие на Сброс данных")
    public void clickResetDataBtn() {
        homePage.clickResetSubMenu();
    }
    @Допустим("проверка отсутствия добавленного товара: {string}, {string}, {string}")
    public void verifyProductDeleted(String productName, String foodType, String isExotic){
        foodPage.verifyProductNotInTable(productName, foodType, isExotic);
    }
}
