from __future__ import absolute_import, unicode_literals
import  os

from celery import Celery
from django.conf import settings
from celery.schedules import crontab

os.environ.setdefault('DJANGO_SETTINGS_MODULE', 'Eshop.settings')

app = Celery('Eshop')
app.conf.enable_utc = False

app.conf.update(timezone = 'Europe/Bucharest')

app.config_from_object(settings, namespace='CELERY')

# Celery beat settings
app.conf.beat_schedule = {
    # added for scheluder
    'send-mail-every-day-at-8':{
        'task': 'send_mail_app.tasks.send_mail_func',
        'schedule': crontab(hour=10, minute=18),
       #'args':(2)
    }

}

app.autodiscover_tasks()

@app.task(bind=True)
def debug_task(self):
    print(f'REQUEST {self.request!r}')