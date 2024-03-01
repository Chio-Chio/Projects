from django.test import TestCase, Client
from django.urls import reverse
from ..models.category import Category
from ..models.customer import Customer
from ..models.product import Products

class TestSignupView(TestCase):
    def setUp(self):
        self.client = Client()

    def test_signup_view_get(self):
        # Simulate a GET request to the Signup view
        response = self.client.get(reverse('signup'))

        # Check that the response status code is 200 (OK)
        self.assertEqual(response.status_code, 200)


    def test_signup_view_post_success(self):
        # Simulate a POST request to the Signup view with valid data
        response = self.client.post(
            reverse('signup'),
            {
                'firstname': 'John',
                'lastname': 'Doe',
                'phone': '1234567890',
                'email': 'john.doe@example.com',
                'password': 'testpassword'
            }
        )

        # Check that the response status code is a redirect (302)
        self.assertEqual(response.status_code, 302)

        # Check that the customer has been created in the database
        self.assertTrue(Customer.objects.filter(email='john.doe@example.com').exists())

        # Get the URL the view redirected to
        redirect_url = response.url

        # Check that the user is redirected to the homepage
        self.assertEqual(redirect_url, reverse('homepage'))


    def test_signup_view_post_failure(self):
        # Simulate a POST request to the Signup view with invalid data
        response = self.client.post(reverse('signup'))

        # Check that the response status code is 200 (OK)
        self.assertEqual(response.status_code, 200)

        # Check that the error message is present in the response context
        self.assertIn('error', response.context)

        # Check that the customer has not been created in the database
        self.assertFalse(Customer.objects.exists())


class TestLoginView(TestCase):
    def setUp(self):
        # Create a customer for testing
        self.customer = Customer.objects.create(
            first_name='John',
            last_name='Doe',
            phone='1234567890',
            email='john.doe@example.com',
            password='hashed_password'
        )

    def test_login_view_get(self):
        # Simulate a GET request to the Login view
        response = self.client.get(reverse('login'))

        # Check that the response status code is 200 (OK)
        self.assertEqual(response.status_code, 200)

    def test_login_view_post_success(self):
        # Simulate a POST request to the Login view with valid credentials
        response = self.client.post(
            reverse('login'),
            {
                'email': 'john.doe@example.com',
                'password': 'hashed_password'
            }
        )

        # Check that the response status code is 200 (OK)
        self.assertEqual(response.status_code, 200)



    def test_login_view_post_failure(self):
        # Simulate a POST request to the Login view with invalid credentials
        response = self.client.post(
            reverse('login'),
            {
                'email': 'john.doe@example.com',
                'password': 'wrong_password'
            }
        )

        # Check that the response status code is 200 (OK)
        self.assertEqual(response.status_code, 200)

        # Check that an error message is present in the response context
        self.assertIn('Invalid', response.context['error'])

class TestIndexView(TestCase):
    def setUp(self):
        # Initialize an empty cart in the session
        self.client.session['cart'] = {}

        # Create a category
        self.category = Category.objects.create(name='Test Category')

        # Create a product
        self.product = Products.objects.create(
            name='Test Product',
            price=100,
            description='Test description',
            category=self.category
        )

    def test_index_view_get(self):
        # Simulate a GET request to the Index view
        response = self.client.get(reverse('homepage'))

        # Check that the response is a redirect (302)
        self.assertEqual(response.status_code, 302)


    def test_index_view_post(self):
        # Simulate a POST request to the Index view with product data
        response = self.client.post(
            reverse('homepage'),
            {
                'product': self.product.id,
            }
        )

        # Check that the response is a redirect (302)
        self.assertEqual(response.status_code, 302)