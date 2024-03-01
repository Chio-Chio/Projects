from django.test import TestCase, Client
from django.urls import reverse
from ..models.category import Category
from ..models.customer import Customer
from ..models.orders import Order
from ..models.product import Products
from bs4 import BeautifulSoup

class TestLoginTemplate(TestCase):
    def setUp(self):
        self.client = Client()

    def test_login_template_content(self):
        # Make a GET request to the login page
        response = self.client.get(reverse('login'))

        # Check if the response status code is 200 (OK)
        self.assertEqual(response.status_code, 200)

        # Use BeautifulSoup to parse the HTML content
        soup = BeautifulSoup(response.content, 'html.parser')

        # Check if the login form exists
        form = soup.find('form', {'action': '/login'})
        self.assertIsNotNone(form)

        # Check if the email input field exists
        email_input = form.find('input', {'name': 'email'})
        self.assertIsNotNone(email_input)
        self.assertEqual(email_input['placeholder'], 'abc@gmail.com')

        # Check if the password input field exists
        password_input = form.find('input', {'name': 'password'})
        self.assertIsNotNone(password_input)

        # Check if the Facebook login button exists
        facebook_login_button = form.find('button', {'class': 'btn-primary'})
        self.assertIsNotNone(facebook_login_button)


class TestSignupTemplate(TestCase):
    def test_signup_template_content(self):
        # Make a GET request to the signup page
        response = self.client.get(reverse('signup'))

        # Check if the response status code is 200 (OK)
        self.assertEqual(response.status_code, 200)

        # Check if the form elements and labels are present in the rendered HTML
        self.assertContains(response, '<form action="/signup" method="POST">', html=False)
        self.assertContains(response, '<input type="text" name="firstname" id="" value="" class="form-control form-control-sm" placeholder="">', html=True)
        self.assertContains(response, '<input type="text" name="lastname" id="" value="" class="form-control form-control-sm">', html=True)
        self.assertContains(response, '<input type="text" name="phone" id="" class="form-control form-control-sm" value="" placeholder="9876543210">', html=True)
        self.assertContains(response, '<input required type="email" name="email" id="" value="" class=" form-control-sm form-control" placeholder="abc@gmail.com">', html=True)
        self.assertContains(response, '<input type="password" name="password" id="" class="form-control form-control-sm">', html=True)
        self.assertContains(response, '<input type="checkbox" class="form-check-input" id="exampleCheck1">', html=True)
        self.assertContains(response, '<button type="submit" class="btn btn-sm btn-success col-lg-12">Create an account</button>', html=True)

