import pytest
import requests

BASE_URL = "http://localhost:8080/api/"


def test_get_farm():
    response = requests.get(BASE_URL+"farms/2")
    assert response.status_code == 200
    farm = response.json()
    assert farm.get("name") == "Happy Henhouse", "Le nom n'est pas 'Happy Henhouse'"

def test_get_cow_():
    response = requests.get(BASE_URL+"farms/2")
    assert response.status_code == 200
    farm = response.json()
    print(farm)
    assert farm.get("name")=="Happy Henhouse"
    assert "cow" in farm and farm["cow"].get("name") == "poupette", "La vache ne s'appelle pas 'poupette' ou est absente"
