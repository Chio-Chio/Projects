from django.test import TestCase
import datetime
from ..models.category import Category
from ..models.customer import Customer
from ..models.orders import Order
from ..models.product import Products


class TestCategoryModel(TestCase):
    def setUp(self):
        # Set up any initial data needed for the tests
        Category.objects.create(name='Test Category 1')
        Category.objects.create(name='Test Category 2')

    def test_category_creation(self):
        category_1 = Category.objects.get(name='Test Category 1')
        category_2 = Category.objects.get(name='Test Category 2')

        self.assertEqual(category_1.name, 'Test Category 1')
        self.assertEqual(category_2.name, 'Test Category 2')

    def test_get_all_categories(self):
        # Test the get_all_categories method
        all_categories = Category.get_all_categories()

        # Assert that there are two categories created in the setUp method
        self.assertEqual(all_categories.count(), 2)

        # Assert that the names of the categories match the names set in setUp
        self.assertEqual(all_categories[0].name, 'Test Category 1')
        self.assertEqual(all_categories[1].name, 'Test Category 2')


class TestCustomerModel(TestCase):
    def setUp(self):
        # Set up any initial data needed for the tests
        Customer.objects.create(
            first_name='John',
            last_name='Doe',
            phone='1234567890',
            email='john.doe@example.com',
            password='hashed_password'
        )

    def test_customer_creation(self):
        customer = Customer.objects.get(email='john.doe@example.com')

        self.assertEqual(customer.first_name, 'John')
        self.assertEqual(customer.last_name, 'Doe')
        self.assertEqual(customer.phone, '1234567890')
        self.assertEqual(customer.password, 'hashed_password')

    def test_get_customer_by_email(self):
        customer = Customer.get_customer_by_email('john.doe@example.com')

        self.assertIsNotNone(customer)
        self.assertEqual(customer.first_name, 'John')
        self.assertEqual(customer.last_name, 'Doe')
        self.assertEqual(customer.phone, '1234567890')
        self.assertEqual(customer.password, 'hashed_password')

class TestProductsModel(TestCase):
    def setUp(self):
        # Set up any initial data needed for the tests
        category = Category.objects.create(name='Test Category')
        Products.objects.create(
            name='Test Product',
            price=100,
            category=category,
            description='Test description',
            image='path/to/test_image.jpg'
        )

    def test_get_products_by_id(self):
        product = Products.objects.get(id=1)
        products_by_id = Products.get_products_by_id([product.id])

        self.assertEqual(products_by_id.count(), 1)
        self.assertEqual(products_by_id[0].name, 'Test Product')
        self.assertEqual(products_by_id[0].price, 100)
        self.assertEqual(products_by_id[0].category.name, 'Test Category')
        self.assertEqual(products_by_id[0].description, 'Test description')
        self.assertEqual(products_by_id[0].image, 'path/to/test_image.jpg')

    def test_get_all_products(self):
        all_products = Products.get_all_products()

        self.assertEqual(all_products.count(), 1)
        self.assertEqual(all_products[0].name, 'Test Product')
        self.assertEqual(all_products[0].price, 100)
        self.assertEqual(all_products[0].category.name, 'Test Category')
        self.assertEqual(all_products[0].description, 'Test description')
        self.assertEqual(all_products[0].image, 'path/to/test_image.jpg')

    def test_get_all_products_by_category_id(self):
        category = Category.objects.get(id=1)
        products_by_category = Products.get_all_products_by_categoryid(category.id)

        self.assertEqual(products_by_category.count(), 1)
        self.assertEqual(products_by_category[0].name, 'Test Product')
        self.assertEqual(products_by_category[0].price, 100)
        self.assertEqual(products_by_category[0].category.name, 'Test Category')
        self.assertEqual(products_by_category[0].description, 'Test description')
        self.assertEqual(products_by_category[0].image, 'path/to/test_image.jpg')

class TestOrderModel(TestCase):
    def setUp(self):
        # Set up any initial data needed for the tests
        category = Category.objects.create(
            name='Test Category'
        )

        customer = Customer.objects.create(
            first_name='John',
            last_name='Doe',
            phone='1234567890',
            email='john.doe@example.com',
            password='hashed_password'
        )

        product = Products.objects.create(
            name='Test Product',
            price=100,
            category_id=1,
            description='Test description'
        )

        Order.objects.create(
            product=product,
            customer=customer,
            quantity=2,
            price=200,
            address='Test Address',
            phone='Test Phone',
            date=datetime.date.today(),
            status=False
        )

    def test_place_order(self):
        order = Order.objects.get(id=1)
        self.assertEqual(order.product.name, 'Test Product')
        self.assertEqual(order.customer.first_name, 'John')
        self.assertEqual(order.quantity, 2)
        self.assertEqual(order.price, 200)
        self.assertEqual(order.address, 'Test Address')
        self.assertEqual(order.phone, 'Test Phone')
        self.assertEqual(order.status, False)

    def test_get_orders_by_customer(self):
        customer = Customer.objects.get(id=1)
        orders = Order.get_orders_by_customer(customer.id)

        self.assertEqual(orders.count(), 1)
        self.assertEqual(orders[0].product.name, 'Test Product')
        self.assertEqual(orders[0].customer.first_name, 'John')
        self.assertEqual(orders[0].quantity, 2)
        self.assertEqual(orders[0].price, 200)
        self.assertEqual(orders[0].address, 'Test Address')
        self.assertEqual(orders[0].phone, 'Test Phone')
        self.assertEqual(orders[0].status, False)
