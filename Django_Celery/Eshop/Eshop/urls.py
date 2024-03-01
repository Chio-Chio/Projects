# Eshop/urls.py

from django.contrib import admin
from django.urls import path, include
from django.conf.urls.static import static
from . import settings

urlpatterns = [
    path('admin/', admin.site.urls),
    path('', include('store.urls')),
path('social-auth/', include('social_django.urls', namespace='socialaccount')),
    #path('accounts/', include(('allauth.socialaccount.urls', 'allauth.socialaccount'), namespace='socialaccount')),  # Use a 2-tuple
    path('accounts/', include('allauth.urls')),

] + static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)
