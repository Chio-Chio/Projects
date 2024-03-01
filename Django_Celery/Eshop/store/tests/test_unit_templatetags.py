from django.test import TestCase
from django.template import Template, Context
from ..templatetags.custom_filter import currency, multiply

class TestTemplateFilters(TestCase):
    def test_currency_filter(self):
        result = currency(100)
        self.assertEqual(result, '100 $')

    def test_multiply_filter(self):
        result = multiply(5, 3)
        self.assertEqual(result, 15)

    def test_template_rendering(self):
        # Test the filters in a template
        template_str = "{% load custom_filter %}{{ some_number|currency }} {{ number1|multiply:number2 }}"
        template = Template(template_str)
        context = Context({'some_number': 50, 'number1': 4, 'number2': 7})
        rendered = template.render(context)

        # Verify the rendered output
        self.assertIn('50 $', rendered)
        self.assertIn('28', rendered)

        # Note: Adjust the import paths based on your project structure
