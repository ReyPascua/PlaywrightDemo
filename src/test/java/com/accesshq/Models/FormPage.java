package com.accesshq.Models;

import com.microsoft.playwright.*;

public class FormPage {
    Page page;
    public FormPage(Page page) { this.page = page; }

    public void enterName(String name){
        page.locator("input[name=\"name\"]").fill(name);
    }

    public void enterEmail(String email){
        page.locator("input[name=\"email\"]").fill(email);
    }

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
}
