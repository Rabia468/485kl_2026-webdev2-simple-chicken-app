from playwright.sync_api import sync_playwright
import pytest

BASE_URL = "http://localhost:8080"

@pytest.fixture(scope="session")
def browser():
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=True)
        yield browser
        browser.close()

@pytest.fixture(scope="function")
def page(browser):
    context = browser.new_context()
    page = context.new_page()
    yield page
    page.close()

def test_load_chicken_manager_page(page):
    page.goto(BASE_URL)
    assert page.locator("h1").inner_text() == "Chicken Manager"

def test_add_new_chicken(page):
    page.goto(BASE_URL)
    page.fill("input[placeholder='NAME']", "PlaywrightChicken")
    page.fill("input[placeholder='AGE']", "2")
    page.fill("input[placeholder='WEIGHT']", "3.5")
    page.click("button:has-text('Add')")
    page.wait_for_selector("text=PlaywrightChicken")
    assert page.locator("text=PlaywrightChicken").first.is_visible()

def test_chicken_name(page):
    page.goto(BASE_URL)
    chicken_name_element = page.locator('span.font-bold[x-text="chicken.name"]').first
    assert chicken_name_element.inner_text() == 'Cracotte', "Le texte de l'élément ne correspond pas."

def test_chicken_age(page):
    page.goto(BASE_URL)
    element = page.locator('[x-text="chicken.age"]').first
    assert element.is_visible(), "L'élément avec x-text='chicken.age' n'est pas visible sur la page."

def test_chicken_weight(page):
    page.goto(BASE_URL)
    element = page.locator('[x-text="chicken.weight"]').first
    assert element.is_visible(), "L'élément avec x-text='chicken.weight' n'est pas visible sur la page."

