package com.accesshq.Models;

import com.microsoft.playwright.*;

public class FormPage {
    Page page;
    public FormPage(Page page) { this.page = page; }

    public void clickFormType(String formName){
        page.locator("text= "+formName+" ").click();
    }

    public Locator getInputField(String inputName){
        return page.locator("input#"+inputName);
    }

    //Modern

    public void clickState(String state){
        page.locator("div[role=\"button\"]:has-text(\"Statearrow_drop_down\")").click();
        page.locator("text=" + state).click();
    }

    public void clickAgree(){
        page.locator("text=check_box_outline_blankDo you agree? >> div").nth(1).click();
    }

    public void clickSubmit(){
        page.locator("button:has-text(\"submit\")").click();
    }

    //Traditional

    public void selectGender(String gender){
        page.locator("select#gender").selectOption(String.valueOf(gender.charAt(0)));
    }

    public void clickAllow(){
            page.locator("#allow").check();
    }

    public void clickReset(){
        page.locator("input:has-value(\"Reset\")");
    }


}
