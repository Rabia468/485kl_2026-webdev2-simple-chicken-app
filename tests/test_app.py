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
    assert page.locator("text=PlaywrightChicken").is_visible()

def test_delete_chicken_ui(page):
    page.goto(BASE_URL)
    first_chicken = page.locator(".chicken-item").first
    delete_button = first_chicken.locator("button:has-text('Delete')")

    if delete_button.is_visible():
        delete_button.click()
        page.wait_for_timeout(1000)  # Wait for deletion to process
        assert not first_chicken.is_visible()

