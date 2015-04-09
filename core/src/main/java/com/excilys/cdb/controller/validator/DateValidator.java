package com.excilys.cdb.controller.validator;

import java.util.Locale;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class DateValidator implements ConstraintValidator<Date, String> {
	@Autowired
	private MessageSource messageSource;

	@Override
	public void initialize(Date constraintAnnotation) {
	}

	@Override
	/**
	 * check the validity of a date
	 * @param the value you need to validate
	 * @param the context
	 * @return a boolean, true if the date is valid, false in other case.
	 */
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return false;
		}
		if (value.isEmpty()) {
			return true;
		}
		if (DateConverter.isDate(value, getDateRegex())) {
			return true;
		}
		return false;
	}

	/**
	 * get the regexe, depending of the locale context
	 * @return a regex in a String.
	 */
	private String getDateRegex() {
		Locale userLocale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("binding.date.regex", null, userLocale);
	}
}