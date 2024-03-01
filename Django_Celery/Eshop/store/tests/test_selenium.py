from django.test import LiveServerTestCase
from django.urls import reverse
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
class TestSeleniumSignupView(LiveServerTestCase):
    def setUp(self):
        self.browser = webdriver.Chrome()  # You can use other browsers and drivers

    def tearDown(self):
        self.browser.quit()

    def test_signup_view(self):
        self.browser.get(self.live_server_url + reverse('signup'))

        # Simulate filling out the signup form
        self.browser.find_element(By.NAME, 'firstname').send_keys('John')
        self.browser.find_element(By.NAME, 'lastname').send_keys('Doe')
        self.browser.find_element(By.NAME, 'phone').send_keys('1234567890')
        self.browser.find_element(By.NAME, 'email').send_keys('john.doe@example.com')
        self.browser.find_element(By.NAME, 'password').send_keys('testpassword')

        # Simulate submitting the form
        self.browser.find_element(By.CSS_SELECTOR, 'button[type="submit"]').click()

        # Update this line
        self.assertEqual(self.browser.current_url, self.live_server_url + '/store')

        # to
        self.assertEqual(self.browser.current_url, self.live_server_url + '/store')
        # Optionally, add a brief pause to visually inspect the result (for demonstration purposes)
        import time
        time.sleep(3)

class TestSeleniumLoginView(LiveServerTestCase):
    def setUp(self):
        self.browser = webdriver.Chrome()

    def tearDown(self):
        self.browser.quit()

    def test_signup_and_login(self):
        # Assuming you have a URL named 'signup' for the signup page
        self.browser.get(self.live_server_url + reverse('signup'))

        # Simulate filling out the signup form
        self.browser.find_element(By.NAME, 'firstname').send_keys('John')
        self.browser.find_element(By.NAME, 'lastname').send_keys('Doe')
        self.browser.find_element(By.NAME, 'phone').send_keys('1234567890')
        self.browser.find_element(By.NAME, 'email').send_keys('john.doe@example.com')
        self.browser.find_element(By.NAME, 'password').send_keys('testpassword')

        # Simulate submitting the signup form
        self.browser.find_element(By.CSS_SELECTOR, 'button[type="submit"]').click()

        # Check if the user is redirected to the store page after signup
        self.assertEqual(self.browser.current_url, self.live_server_url + '/store')

        # Assuming you have a URL named 'login' for the login page
        self.browser.get(self.live_server_url + reverse('login'))

        # Simulate filling out the login form
        self.browser.find_element(By.NAME, 'email').send_keys('john.doe@example.com')
        self.browser.find_element(By.NAME, 'password').send_keys('testpassword')

        # Simulate submitting the login form
        self.browser.find_element(By.CSS_SELECTOR, 'button[type="submit"]').click()

        # Check if the user is redirected to the store page after login
        self.assertEqual(self.browser.current_url, self.live_server_url + '/store')

        # Optionally, add a brief pause to visually inspect the result (for demonstration purposes)
        import time
        time.sleep(3)
