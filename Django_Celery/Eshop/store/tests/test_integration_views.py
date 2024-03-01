from django.test import TestCase, Client
from django.urls import reverse
from ..models.orders import Order
from ..models.customer import Customer
from ..models.product import Products
from ..models.category import Category

class TestSignupViewIntegration(TestCase):
    def setUp(self):
        self.client = Client()

    def test_signup_view_get(self):
        response = self.client.get(reverse('signup'))
        self.assertEqual(response.status_code, 200)

    def test_signup_view_post_success(self):
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
        self.assertEqual(response.status_code, 302)
        self.assertTrue(Customer.objects.filter(email='john.doe@example.com').exists())
        redirect_url = response.url
        self.assertEqual(redirect_url, reverse('homepage'))

    def test_signup_view_post_failure(self):
        response = self.client.post(reverse('signup'))
        self.assertEqual(response.status_code, 200)
        self.assertIn('error', response.context)
        self.assertFalse(Customer.objects.exists())

class TestLoginViewIntegration(TestCase):
    def setUp(self):
        self.client = Client()
        # Create a customer for testing
        self.customer = Customer.objects.create(
            first_name='John',
            last_name='Doe',
            phone='1234567890',
            email='john.doe@example.com',
            password='hashed_password'
        )

    def test_login_view_get(self):
        response = self.client.get(reverse('login'))
        self.assertEqual(response.status_code, 200)

    def test_login_view_post_success(self):
        response = self.client.post(
            reverse('login'),
            {
                'email': 'john.doe@example.com',
                'password': 'hashed_password'
            }
        )
        self.assertEqual(response.status_code, 200)
        # Add assertions for successful login if needed

    def test_login_view_post_failure(self):
        response = self.client.post(
            reverse('login'),
            {
                'email': 'john.doe@example.com',
                'password': 'wrong_password'
            }
        )
        self.assertEqual(response.status_code, 200)
        self.assertIn('Invalid', response.context['error'])

class TestOrderViewIntegration(TestCase):
    def setUp(self):
        # Set up test data
        category = Category.objects.create(
            name='Test Category'
        )

        self.customer = Customer.objects.create(
            first_name='John', last_name='Doe', phone='1234567890', email='john@example.com', password='testpassword'
        )
        self.product = Products.objects.create(name='Test Product', price=10.0, category=category)
        self.order = Order.objects.create(
            customer=self.customer, product=self.product, price=self.product.price, quantity=1
        )
        self.client = Client()

    def test_order_view_authenticated_user(self):
        # Log in the customer
        self.client.post(reverse('login'), {'username': 'john@example.com', 'password': 'testpassword'})

        # Make a GET request to the order view
        response = self.client.get(reverse('orders'))

        # Check if the response status code is redirection
        self.assertEqual(response.status_code, 302)


    def test_order_view_unauthenticated_user(self):
        # Make a GET request to the order view without logging in
        response = self.client.get(reverse('orders'))

        # Check if the response status code is 302 (Redirect to login page)
        self.assertEqual(response.status_code, 302)



class TestCartIntegration(TestCase):
    def setUp(self):
        # Create a customer for testing
        self.customer = Customer.objects.create(
            first_name='John',
            last_name='Doe',
            phone='1234567890'
            # Add other customer details as needed
        )

    def test_cart_view(self):
        # Set the customer in the session (simulate login)
        self.client.session['customer'] = self.customer.id

        # Simulate adding products to the cart in the session
        # Adjust this based on your actual cart implementation
        self.client.session['cart'] = {'1': 2, '2': 1}

        # Make a GET request to the cart page
        response = self.client.get(reverse('cart'))

        # Check if the response status code is redirected
        self.assertEqual(response.status_code, 302)
