from django.contrib.auth import get_user_model

from celery import shared_task
from django.core.mail import send_mail
from Eshop import settings

@shared_task(bind=True)
def send_mail_func(self):
    #send mail to all the users
    users = get_user_model().objects.all()
    for user in users:
        mail_subject = "Hi! Celery Testing"
        messages = "BUY OUR PRODUCTS PLS"
        to_email =  user.email
        to_email = user.email
        send_mail(
            subject = mail_subject,
            message=messages,
            from_email=settings.EMAIL_HOST_USER,
            recipient_list=[to_email],
            fail_silently=False,
        )
    return "SENT"