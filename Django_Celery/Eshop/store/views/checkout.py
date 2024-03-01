from django.shortcuts import render, redirect
from django.contrib.auth.hashers import check_password
from django.views import View
from django.core.mail import send_mail
from Eshop import settings
from ..models.customer import Customer
from ..models.product import Products
from ..models.orders import Order

email_host_user = settings.EMAIL_HOST_USER

class CheckOut(View):
    def post(self, request):
        # Extracting data from the POST request
        address = request.POST.get('address')
        phone = request.POST.get('phone')
        customer = request.session.get('customer')
        cart = request.session.get('cart')
        products = Products.get_products_by_id(list(cart.keys()))

        customer_id2 = request.session.get('customer')
        customer2 = Customer.objects.get(pk=customer_id2)
        email2= Customer.get_email(customer2)

        # Constructing the order confirmation message
        message = f"Dear Customer {customer2.first_name} {customer2.last_name},\nYour order has been placed!\nOrder details: \n"
        for product in products:
            print(cart.get(str(product.id)))
            order = Order(
                customer=Customer(id=customer),
                product=product,
                price=product.price,
                address=address,
                phone=phone,
                quantity=cart.get(str(product.id))
            )
            message += f"-> {product.name}, price: {product.price} $, quantity:{order.quantity}\n"

            # Saving the order to the database
            order.save()

        # Sending order confirmation email
        subject = "Order placed"
        recipient_list = [email2]  # Assuming customer.email is the recipient's email
        send_mail(subject, message, email_host_user, recipient_list, fail_silently=True)

        # Clearing the cart in the session
        request.session['cart'] = {}

        # Redirecting to the 'cart' page
        return redirect('cart')

    # Additional method for sending mail to all (commented out)
    # def send_mail_to_all(request):
    #     send_mail_func.delay()
    #     return HttpResponse("Sent")