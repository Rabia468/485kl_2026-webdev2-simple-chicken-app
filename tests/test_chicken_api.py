import pytest
import requests

BASE_URL = "http://localhost:8080/api/chickens"

@pytest.fixture
def test_chicken():
    return {"NAME": "TestChicken", "AGE": 2, "WEIGHT": 3.5}


def test_create_chicken(test_chicken):
    response = requests.post(BASE_URL, json=test_chicken)
    assert response.status_code == 200 or response.status_code == 201
    chicken = response.json()
    assert chicken["NAME"] == test_chicken["NAME"]
    assert int(chicken["AGE"]) == int(test_chicken["AGE"])  # Ensure integer conversion
    assert float(chicken["WEIGHT"]) == float(test_chicken["WEIGHT"])  # Ensure float conversion
    return chicken


def test_get_chickens():
    response = requests.get(BASE_URL)
    assert response.status_code == 200
    chickens = response.json()
    assert isinstance(chickens, list)
    assert len(chickens) >= 0  # At least empty list is returned

def test_delete_chicken(test_chicken):
    created_chicken = test_create_chicken(test_chicken)
    print(f"created: {created_chicken}")
    chicken_id = created_chicken["ID"]
    response = requests.delete(f"{BASE_URL}/{chicken_id}")
    assert response.status_code == 200 or response.status_code == 204
    response = requests.get(f"{BASE_URL}/{chicken_id}")
    assert response.status_code == 404  # Chicken should no longer exist
