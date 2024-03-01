from django.test import TestCase
from ..models.category import Category
from ..models.customer import Customer
from ..models.orders import Order
from ..models.product import Products
import datetime

class TestOrderIntegration(TestCase):
    def setUp(self):
        # Set up data needed for the tests
        self.category = Category.objects.create(name='Test Category')

        self.customer = Customer.objects.create(
            first_name='John',
            last_name='Doe',
            phone='1234567890',
            email='john.doe@example.com',
            password='hashed_password'
        )

        self.product = Products.objects.create(
            name='Test Product',
            price=100,
            category=self.category,
            description='Test description'
        )

    def test_place_order_integration(self):
        # Create an order and save it to the database
        order = Order.objects.create(
            product=self.product,
            customer=self.customer,
            quantity=2,
            price=200,
            address='Test Address',
            phone='Test Phone',
            date=datetime.date.today(),
            status=False
        )

        # Retrieve the order from the database and assert its attributes
        retrieved_order = Order.objects.get(id=order.id)
        self.assertEqual(retrieved_order.product.name, 'Test Product')
        self.assertEqual(retrieved_order.customer.first_name, 'John')
        self.assertEqual(retrieved_order.quantity, 2)
        self.assertEqual(retrieved_order.price, 200)
        self.assertEqual(retrieved_order.address, 'Test Address')
        self.assertEqual(retrieved_order.phone, 'Test Phone')
        self.assertEqual(retrieved_order.status, False)

    def test_get_orders_by_customer_integration(self):
        # Create additional orders for the customer
        order1 = Order.objects.create(
            product=self.product,
            customer=self.customer,
            quantity=3,
            price=300,
            address='Test Address 2',
            phone='Test Phone 2',
            date=datetime.date.today(),
            status=True
        )

        order2 = Order.objects.create(
            product=self.product,
            customer=self.customer,
            quantity=1,
            price=100,
            address='Test Address 3',
            phone='Test Phone 3',
            date=datetime.date.today(),
            status=False
        )

        # Retrieve orders by customer and assert the attributes of the retrieved orders
        orders = Order.get_orders_by_customer(self.customer.id)
        self.assertEqual(orders.count(), 2)

        # Assert the attributes of the first order
        self.assertEqual(orders[0].product.name, 'Test Product')
        self.assertEqual(orders[0].customer.first_name, 'John')
        self.assertEqual(orders[0].quantity, 3)
        self.assertEqual(orders[0].price, 300)
        self.assertEqual(orders[0].address, 'Test Address 2')
        self.assertEqual(orders[0].phone, 'Test Phone 2')
        self.assertEqual(orders[0].status, True)

        # Assert the attributes of the second order
        self.assertEqual(orders[1].product.name, 'Test Product')
        self.assertEqual(orders[1].customer.first_name, 'John')
        self.assertEqual(orders[1].quantity, 1)
        self.assertEqual(orders[1].price, 100)
        self.assertEqual(orders[1].address, 'Test Address 3')
        self.assertEqual(orders[1].phone, 'Test Phone 3')
        self.assertEqual(orders[1].status, False)

